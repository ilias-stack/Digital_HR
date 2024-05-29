import {Component, inject, OnInit} from '@angular/core';
import { ChatbotService } from "../../services/chatbot.service";

interface Message {
  text: string;
  sender: 'user' | 'bot';
}

@Component({
  selector: 'app-chatbot',
  templateUrl: './chatbot.component.html',
  styleUrls: ['./chatbot.component.css'] // Correction ici
})
export class ChatbotComponent implements OnInit{
  attachedFile: File | null = null;
  loadingAnswer = false;
  loadingFile = false;
  message = "";
  assistant = "assistant";
  inputSelectedFiles: File[] = [];
  messages: Message[] = [];
  newMessage: string = '';
  response: string = 'Bonjour, je suis votre assistant virtuel. Je suis là pour répondre à vos questions et vous aider dans vos tâches. \n' +
    '    N\'hésitez pas à me poser des questions ou à demander de l\'aide. Comment puis-je vous assister aujourd\'hui ?';

  constructor(private chatbotService: ChatbotService) {}
  ngOnInit(): void {
    this.botReply();
  }
  sendMessage() {
    if (this.newMessage.trim()) {
      this.messages.push({ text: this.newMessage, sender: 'user' });
      this.chatbotService.getResponse(this.newMessage).subscribe({
        next: value => {
          this.response = value;
          console.log(value);
          this.newMessage = ''; // Vider le message après avoir obtenu la réponse
          this.botReply();
        },
        error: err => {
          console.log(err);
        }
      });
    }
  }

  botReply() {

    this.messages.push({ text: this.response, sender: 'bot' });

  }



  async attachFile(event: any) {
    this.loadingFile = true;

    if (!event.target.files) return;
    const files = event.target.files;
    // Store the selected files
    this.inputSelectedFiles.push(...files);
    // send request
    const formData = new FormData();
    formData.append("file", this.inputSelectedFiles[0]);
    this.chatbotService.postFile(formData).subscribe();
    this.loadingFile = false;
  }
}
