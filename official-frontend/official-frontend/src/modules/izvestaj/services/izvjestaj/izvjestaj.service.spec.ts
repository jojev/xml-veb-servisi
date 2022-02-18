import { TestBed } from '@angular/core/testing';

import { IzvjestajService } from './izvjestaj.service';

describe('IzvjestajService', () => {
  let service: IzvjestajService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IzvjestajService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
