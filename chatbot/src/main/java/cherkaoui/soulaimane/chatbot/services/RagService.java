package cherkaoui.soulaimane.chatbot.services;

import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class RagService {
    @Autowired
    private VectorStore vectorStore;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String askllm(String query) {
        List<Document> documents = vectorStore.similaritySearch(SearchRequest.query(query));
        String systemMessageTemplate = """
                Answer the following question based only the provided CONTEXT.
                CONTEXT:
                  {CONTEXT}
                """;
        //String content = documents.stream().map(d -> d.getContent()).collect(Collectors.joining("\n"));
        Message systemMessage = new SystemPromptTemplate(systemMessageTemplate)
                .createMessage(Map.of("CONTEXT",documents));
        UserMessage userMessage = new UserMessage(query);
        Prompt prompt=new Prompt(List.of(systemMessage,userMessage));
        OpenAiApi openAiApi = new OpenAiApi("sk-proj-GqCD1Sx6bBl2CN9r8C8dT3BlbkFJ7BkZ4smYEk6RMyq9rbfd");
        OpenAiChatOptions options = OpenAiChatOptions.builder()
                .withModel("gpt-3.5-turbo")
                .withMaxTokens(800)
                .build();
        OpenAiChatClient openAiChatClient = new OpenAiChatClient(openAiApi,options);
        ChatResponse reponse = openAiChatClient.call(prompt);
        String responseContent = reponse.getResult().getOutput().getContent();
return responseContent;
    }

    public void textEmbedding(Resource[] pdfs) {
        jdbcTemplate.update("delete from vector_store");
        PdfDocumentReaderConfig pdfDocumentReaderConfig = PdfDocumentReaderConfig.defaultConfig();
        String content = "";
        for(Resource ressource:pdfs){
            //decouper le pdf en pages:
            PagePdfDocumentReader pagePdfDocumentReader=new PagePdfDocumentReader(ressource,pdfDocumentReaderConfig);
            List<Document> documentList=pagePdfDocumentReader.get();
            content = content + documentList.stream().map(document -> document.getContent()).collect(Collectors.joining("\n")) + "\n";

        }

        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
        List<String> chunks = tokenTextSplitter.split(content,1000);
        List<Document> chunksDocuments = chunks.stream().map(chunk -> new Document(chunk)).collect(Collectors.toList());
        vectorStore.accept(chunksDocuments);
    }
    public void textEmbeddingOne(Resource pdf) {
        jdbcTemplate.update("delete from vector_store");
        PdfDocumentReaderConfig pdfDocumentReaderConfig = PdfDocumentReaderConfig.defaultConfig();


        //decouper le pdf en pages:
        PagePdfDocumentReader pagePdfDocumentReader=new PagePdfDocumentReader(pdf,pdfDocumentReaderConfig);
        List<Document> documentList=pagePdfDocumentReader.get();
        String content = documentList.stream().map(document -> document.getContent()).collect(Collectors.joining("\n")) ;



        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
        List<String> chunks = tokenTextSplitter.split(content,1000);
        List<Document> chunksDocuments = chunks.stream().map(chunk -> new Document(chunk)).collect(Collectors.toList());
        vectorStore.accept(chunksDocuments);
    }
}
