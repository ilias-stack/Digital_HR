import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';

@Injectable({
  providedIn: 'root',
})
export class SkillsService {
  private skillsSubject = new BehaviorSubject<string[]>([]);

  public readonly wantedSkills$ = this.skillsSubject.asObservable();

  public getWantedSkills(): string[] {
    return this.skillsSubject.getValue();
  }

  public addSkill(skill: string) {
    const currentSkills = this.skillsSubject.getValue();
    this.skillsSubject.next([...currentSkills, skill]);
  }

  public removeSkillAt(index: number) {
    const currentSkills = this.skillsSubject.getValue();
    currentSkills.splice(index, 1);
    this.skillsSubject.next([...currentSkills]);
  }
}
