import { Component, OnInit } from '@angular/core';
import { SkillsService } from '../../../services/skills.service';

@Component({
  selector: 'app-skills-banner',
  templateUrl: './skills-banner.component.html',
  styleUrl: './skills-banner.component.css',
})
export class SkillsBannerComponent implements OnInit {
  wantedSkills: string[] = [];

  constructor(private skillsService: SkillsService) {}

  ngOnInit() {
    this.skillsService.wantedSkills$.subscribe((skills) => {
      this.wantedSkills = skills;
    });
  }

  removeSkill(index: number) {
    this.skillsService.removeSkillAt(index);
  }
}
