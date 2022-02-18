import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePotvrdaComponent } from './create-potvrda.component';

describe('CreatePotvrdaComponent', () => {
  let component: CreatePotvrdaComponent;
  let fixture: ComponentFixture<CreatePotvrdaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatePotvrdaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatePotvrdaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
