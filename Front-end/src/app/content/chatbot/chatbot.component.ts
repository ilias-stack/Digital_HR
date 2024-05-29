import {Component, OnInit} from '@angular/core';
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

  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      // Faire quelque chose avec le fichier sélectionné, comme l'envoyer au backend
      console.log('Fichier sélectionné :', file);
    }
  }

  uploadFile() {

  }
}
