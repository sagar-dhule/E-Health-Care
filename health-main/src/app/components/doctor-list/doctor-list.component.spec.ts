import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DocotorListComponent } from './doctor-list.component';

describe('DocotorListComponent', () => {
  let component: DocotorListComponent;
  let fixture: ComponentFixture<DocotorListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DocotorListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DocotorListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
