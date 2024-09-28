import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrintMoneyReceiptComponent } from './print-money-receipt.component';

describe('PrintMoneyReceiptComponent', () => {
  let component: PrintMoneyReceiptComponent;
  let fixture: ComponentFixture<PrintMoneyReceiptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PrintMoneyReceiptComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrintMoneyReceiptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
