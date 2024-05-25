import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';

@Injectable({
  providedIn: 'root',
})
export class SkillsService {
  constructor() {
    const savedSkills = localStorage.getItem('skills');
    if (savedSkills) {
      this.skillsSubject.next(JSON.parse(savedSkills));
    }
  }

  public saveCurrentSkills() {
    localStorage.setItem('skills', JSON.stringify(this.getWantedSkills()));
  }

  private skillsSubject = new BehaviorSubject<string[]>([]);

  public readonly wantedSkills$ = this.skillsSubject.asObservable();

  public getWantedSkills(): string[] {
    return this.skillsSubject.getValue();
  }

  public addSkill(skill: string) {
    const currentSkills = this.skillsSubject.getValue();
    this.skillsSubject.next([...currentSkills, skill]);
    this.saveCurrentSkills();
  }

  public removeSkillAt(index: number) {
    const currentSkills = this.skillsSubject.getValue();
    currentSkills.splice(index, 1);
    this.skillsSubject.next([...currentSkills]);
    this.saveCurrentSkills();
  }
}
