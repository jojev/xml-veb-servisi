import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VaccineStatusViewAndUpdateComponent } from './vaccine-status-view-and-update.component';

describe('VaccineStatusViewAndUpdateComponent', () => {
  let component: VaccineStatusViewAndUpdateComponent;
  let fixture: ComponentFixture<VaccineStatusViewAndUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VaccineStatusViewAndUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VaccineStatusViewAndUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
