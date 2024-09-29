import { Component, OnInit } from '@angular/core';
import { MarineMoneyReceiptModel } from '../../model/MarineMoneyReceipt.Model';
import { MarineBillMoneyreceiptService } from '../../service/marine-bill-moneyreceipt.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-update-marine-money-receipt',
  templateUrl: './update-marine-money-receipt.component.html',
  styleUrls: ['./update-marine-money-receipt.component.css']
})
export class UpdateMarineMoneyReceiptComponent implements OnInit {

  moneyReceiptForm!: FormGroup;
  receiptId: number = 0;
  marineDetails: any[] = [];

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private marineMoneyReceiptService: MarineBillMoneyreceiptService,
    private router: Router
  ) { }

  ngOnInit(): void {
    // Initialize the form
    this.initializeForm();

    // Get the receipt ID from the route
    this.receiptId = +this.route.snapshot.paramMap.get('id')!;

    // Load the Marine Money Receipt data by ID
    this.loadMarineMoneyReceipt();
  }

  private initializeForm(): void {
    this.moneyReceiptForm = this.fb.group({
      issuingOffice: ['', Validators.required],
      id: [null, Validators.required],
      classOfInsurance: ['', Validators.required],
      date: ['', Validators.required],
      modeOfPayment: ['', Validators.required],
      issuedAgainst: ['', Validators.required],
      bill: this.fb.group({
        policies: this.fb.group({
          policyholder: ['', Validators.required],
          bankName: ['', Validators.required]
        })
      })
    });
  }

  private loadMarineMoneyReceipt(): void {
    this.marineMoneyReceiptService.getMarineMoneyReceiptById(this.receiptId).subscribe(receipt => {
      this.moneyReceiptForm.patchValue(receipt);
      // Patch the nested form group if needed
      if (receipt.marinebill && receipt.marinebill.marineDetails) {
        this.moneyReceiptForm.get('bill.policies')?.patchValue(receipt.marinebill.marineDetails);
      }
    }, error => {
      console.error('Error fetching Marine Money Receipt', error);
      // You could also navigate back or show a user-friendly message here
    });
  }

  onSubmit(): void {
    if (this.moneyReceiptForm.valid) {
      const updatedReceipt: MarineMoneyReceiptModel = this.moneyReceiptForm.value;

      // Call the service to update Marine Money Receipt
      this.marineMoneyReceiptService.updateMarineMoneyReceipt(this.receiptId, updatedReceipt)
        .subscribe(response => {
          console.log('Marine Money Receipt updated successfully', response);
          this.router.navigate(['/viewmarinemoneyreceipt']);  // Redirect to the list page after success
        }, error => {
          console.error('Error updating Marine Money Receipt', error);
          // You could also show an error message to the user here
        });
    } else {
      console.warn('Form is invalid. Please check the fields.');
    }
  }
}
