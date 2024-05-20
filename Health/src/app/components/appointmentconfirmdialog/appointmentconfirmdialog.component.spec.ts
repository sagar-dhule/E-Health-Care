import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentconfirmdialogComponent } from './appointmentconfirmdialog.component';

describe('AppointmentconfirmdialogComponent', () => {
  let component: AppointmentconfirmdialogComponent;
  let fixture: ComponentFixture<AppointmentconfirmdialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppointmentconfirmdialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AppointmentconfirmdialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
