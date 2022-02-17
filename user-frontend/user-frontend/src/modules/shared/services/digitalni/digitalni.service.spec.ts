import { TestBed } from '@angular/core/testing';

import { DigitalniService } from './digitalni.service';

describe('DigitalniService', () => {
  let service: DigitalniService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DigitalniService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
