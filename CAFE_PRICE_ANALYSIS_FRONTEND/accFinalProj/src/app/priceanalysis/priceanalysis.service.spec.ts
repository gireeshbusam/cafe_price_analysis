import { TestBed } from '@angular/core/testing';

import { PriceanalysisService } from './priceanalysis.service';

describe('PriceanalysisService', () => {
  let service: PriceanalysisService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PriceanalysisService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
