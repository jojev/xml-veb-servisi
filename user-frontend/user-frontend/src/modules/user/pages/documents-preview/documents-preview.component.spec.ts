import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DocumentsPreviewComponent } from './documents-preview.component';

describe('DocumentsPreviewComponent', () => {
  let component: DocumentsPreviewComponent;
  let fixture: ComponentFixture<DocumentsPreviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DocumentsPreviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DocumentsPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
