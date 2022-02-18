import { TestBed } from '@angular/core/testing';

import { PrikazService } from './prikaz.service';

describe('PrikazService', () => {
  let service: PrikazService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PrikazService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
