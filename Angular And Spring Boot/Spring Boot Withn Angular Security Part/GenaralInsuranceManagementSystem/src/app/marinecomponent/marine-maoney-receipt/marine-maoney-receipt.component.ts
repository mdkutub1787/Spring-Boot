import { Component, OnInit } from '@angular/core';
import { MarineMoneyReceiptModel } from '../../model/MarineMoneyReceipt.Model';
import { MarineBillMoneyreceiptService } from '../../service/marine-bill-moneyreceipt.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-marine-maoney-receipt',
  templateUrl: './marine-maoney-receipt.component.html',
  styleUrl: './marine-maoney-receipt.component.css'
})
export class MarineMaoneyReceiptComponent implements OnInit{

  marinebill!: MarineMoneyReceiptModel[];

  filteredMoneyReceipts: MarineMoneyReceiptModel[] = []; 
  searchTerm: string = '';            
  sortBy: 'policyholder' | 'id' | 'bankName' = 'policyholder'; 

  constructor(
    private marineBillMoneyreceiptService: MarineBillMoneyreceiptService,
    private router: Router
  ) { }

  ngOnInit() {
    this.loadMarineMoneyReceipt();
  }

  // Load all Marine Money Receipts
  loadMarineMoneyReceipt() {
    this.marineBillMoneyreceiptService.getMarineMoneyReceipts()
      .subscribe((data: MarineMoneyReceiptModel[]) => {
        this.marinebill = data;
      });
  }

  // Delete a specific Marine Money Receipt by ID
  deleteMarineManyReceipt(id?: number) {
    if (id !== undefined) {
      this.marineBillMoneyreceiptService.deleteMarineMoneyReceipt(id).subscribe({
        next: res => {
          console.log(res);
          this.loadMarineMoneyReceipt();
          this.router.navigate(['/viewmarinemoneyreceipt']);
        },
        error: error => {
          console.log(error);
        }
      });
    } else {
      console.error('Invalid id');
    }
  }

  editMarineMoneyReceipt(id?: number) {
    if (id !== undefined) {
      this.router.navigate(['updatemarinemoneyreceipt', id]);
    } else {
      console.error('Invalid id');
    }
  }


  printMarineMoneyReceipt(id: number | undefined) {
    if (id !== undefined) {
      this.router.navigate(['printmarinemoney', id]);
    } else {
      console.error('ID is undefined');
    }
  }
  

  // Navigate to add a new Marine Money Receipt
  navigateToAddMarineMoneyReceipt() {
    this.router.navigateByUrl('/createmarinemoneyreceipt');
  }

  // Round a value
  round(value: number): number {
    return Math.round(value);
  }

  searchMoneyReceipt(): void {
    const lowerCaseSearchTerm = this.searchTerm.toLowerCase();
  
    this.filteredMoneyReceipts = this.marinebill.filter(item =>
      item.marinebill?.marineDetails.policyholder?.toLowerCase().includes(lowerCaseSearchTerm) ||
      item.marinebill?.marineDetails.bankName?.toLowerCase().includes(lowerCaseSearchTerm) ||
      item.marinebill?.marineDetails.id?.toString().includes(lowerCaseSearchTerm)
    );
  }

}
