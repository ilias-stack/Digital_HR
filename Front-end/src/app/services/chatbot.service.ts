import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ChatbotService {


  private apiUrl = 'http://localhost:8084/rag';

  constructor(private http: HttpClient) { }

  getResponse(query: string): Observable<string> {
    return this.http.get<string>(this.apiUrl +"query="+encodeURIComponent(query), { responseType: 'text' as 'json' });
  }
}
