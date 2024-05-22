import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CvScannerComponent } from './cv-scanner.component';

describe('CvScannerComponent', () => {
  let component: CvScannerComponent;
  let fixture: ComponentFixture<CvScannerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CvScannerComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CvScannerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
