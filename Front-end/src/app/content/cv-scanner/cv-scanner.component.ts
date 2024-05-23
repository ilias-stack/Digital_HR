import { Component, ViewChild } from '@angular/core';
import { SkillsBannerComponent } from './skills-banner/skills-banner.component';
import { SkillsService } from '../../services/skills.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-cv-scanner',
  templateUrl: './cv-scanner.component.html',
  styleUrl: './cv-scanner.component.css',
})
export class CvScannerComponent {
  newSkill: string = '';
  files!: FileList;
  isLoading = false;
  API_URL = 'http://localhost:5000';

  analysisResult: { [key: string]: number } | undefined;

  constructor(private skillsService: SkillsService) {}

  handleAddSkill() {
    const trimmedSkill = this.newSkill.trim();
    if (trimmedSkill) {
      this.skillsService.addSkill(trimmedSkill);
      this.newSkill = '';
    }
  }
  handleFileInput(event: any): void {
    this.files = event.target.files;
  }

  async handleScan() {
    const formData = new FormData();
    this.isLoading = true;
    this.skillsService.getWantedSkills().forEach((skill) => {
      formData.append('skills', skill);
    });

    if (this.files) {
      for (let i = 0; i < this.files.length; i++) {
        formData.append('files[]', this.files[i]);
      }
    }

    const response = await fetch(this.API_URL + '/analyse_cvs', {
      method: 'POST',
      body: formData,
    });

    if (response.ok) {
      const result = await response.json();
      this.analysisResult = result;
    } else {
      const errorMessage = await response.text();
      alert(JSON.parse(errorMessage)['error']);
    }
    this.isLoading = false;
  }
}
