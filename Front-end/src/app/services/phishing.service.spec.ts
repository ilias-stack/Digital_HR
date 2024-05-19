import { TestBed } from '@angular/core/testing';

import { PhishingService } from './phishing.service';

describe('PhishingService', () => {
  let service: PhishingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PhishingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
