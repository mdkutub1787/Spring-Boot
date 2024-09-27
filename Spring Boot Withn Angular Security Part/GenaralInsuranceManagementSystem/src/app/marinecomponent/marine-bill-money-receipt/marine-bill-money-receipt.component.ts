import { Component, OnInit } from '@angular/core';
import { MarineMoneyReceiptModel } from '../../model/MarineMoneyReceipt.Model';
import { MarineBillMoneyreceiptService } from '../../service/marine-bill-moneyreceipt.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-marine-bill-money-receipt',
  templateUrl: './marine-bill-money-receipt.component.html',
  styleUrls: ['./marine-bill-money-receipt.component.css'] // Fixed 'styleUrl' to 'styleUrls'
})
export class MarineBillMoneyReceiptComponent implements OnInit {

  marinebill!: MarineMoneyReceiptModel[]; 

  constructor(
    private marineBillMoneyreceiptService: MarineBillMoneyreceiptService,
    private router: Router
  ) { }

  ngOnInit() {
    this.loadMarineMoneyReceipt(); 
  }

  // Load all Marine Money Receipts
  loadMarineMoneyReceipt() {
    this.marineBillMoneyreceiptService.getMarineManyReceipt()
    .subscribe((data: MarineMoneyReceiptModel[]) => {
      this.marinebill = data;
    });
  }

  // Delete a specific Marine Money Receipt by ID
  deleteMarineManyReceipt(id: number) {
    this.marineBillMoneyreceiptService.deleteMarineManyReceipt(id)
    .subscribe({
      next: res => {
        console.log(res);
        this.loadMarineMoneyReceipt(); 
        this.router.navigate(['/viewmarinemoneyreceipt']);// Refresh the list after deletion
      },
      error: error => {
        console.log(error);
      }
    });
  }

  // Navigate to edit the Marine Money Receipt
  editMarineMoneyReceipt(id: number) {
    this.router.navigate(['updatemarinebill', id]);
  }

  // Navigate to view details of Marine Money Receipt
  detailsMarineMoneyReceipt(id: number) {
    this.router.navigate(['marinebilldetails', id]);
  }

  // Navigate to add a new Marine Money Receipt
  navigateToAddMarineMoneyReceipt() {
    this.router.navigateByUrl('/createmarinebill');
  }

  // Round a value
  round(value: number): number {
    return Math.round(value);
  }

}
