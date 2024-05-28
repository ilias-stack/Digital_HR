import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {PhishingService} from "../services/phishing.service";
import {PhishingModel} from "../models/phishing.model";

@Component({
  selector: 'app-phishing',
  templateUrl: './phishing.component.html',
  styleUrl: './phishing.component.css'
})
export class PhishingComponent implements OnInit{
  formGroup!: FormGroup;
  isSpam!:boolean;
  constructor(private fb:FormBuilder,private phishingService:PhishingService) {
  }
  ngOnInit(): void {
    this.formGroup=this.fb.group({
    url : [""]}
    )
  }

  handleScanUrl() {
    this.phishingService.scanUrl(this.formGroup.value.url).subscribe({
      next : (value:PhishingModel) => {
        this.isSpam=value.spam
      },
      error : err => {
        console.log(err);
      }
    })
  }
}
