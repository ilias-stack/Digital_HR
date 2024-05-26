import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  handleSubmit() {
    this.router.navigateByUrl('/admin/dashboard')
  }
}
