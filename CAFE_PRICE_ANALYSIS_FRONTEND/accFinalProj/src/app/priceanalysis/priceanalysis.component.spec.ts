import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PriceanalysisComponent } from './priceanalysis.component';

describe('PriceanalysisComponent', () => {
  let component: PriceanalysisComponent;
  let fixture: ComponentFixture<PriceanalysisComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PriceanalysisComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PriceanalysisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
