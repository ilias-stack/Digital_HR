import { Component, Input, OnInit } from '@angular/core';
import { RelevancyItem } from '../../../models/relevancy.model';

@Component({
  selector: 'app-top-relevant',
  templateUrl: './top-relevant.component.html',
  styleUrl: './top-relevant.component.css',
})
export class TopRelevantComponent {
  @Input() items!: RelevancyItem[];
  @Input() title!: string;

  calculateRemainingTime(endDate: string): string {
    const now = new Date();
    const end = new Date(endDate);
    const timeDiff = end.getTime() - now.getTime();
    const daysLeft = Math.ceil(timeDiff / (1000 * 3600 * 24));
    return daysLeft > 0 ? `${daysLeft} days left` : 'Due date passed';
  }
}
