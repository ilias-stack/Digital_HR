import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {PhishingModel} from "../models/phishing.model";

@Injectable({
  providedIn: 'root'
})
export class PhishingService {

  constructor(private http : HttpClient) { }

  scanUrl(value:string) {
    return this.http.get<PhishingModel>("http://localhost:8085/phishing?url="+value);
  }
}
