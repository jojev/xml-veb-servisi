import { TestBed } from '@angular/core/testing';

import { VaccineStatusService } from './vaccine-status.service';

describe('VaccineStatusService', () => {
  let service: VaccineStatusService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VaccineStatusService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
