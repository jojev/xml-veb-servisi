import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LogicalSearchComponent } from './logical-search.component';

describe('LogicalSearchComponent', () => {
  let component: LogicalSearchComponent;
  let fixture: ComponentFixture<LogicalSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LogicalSearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LogicalSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
