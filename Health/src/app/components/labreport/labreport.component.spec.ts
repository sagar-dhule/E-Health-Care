import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LabReportComponent } from './labreport.component';

describe('LabReportComponent', () => {
  let component: LabReportComponent;
  let fixture: ComponentFixture<LabReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LabReportComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LabReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
