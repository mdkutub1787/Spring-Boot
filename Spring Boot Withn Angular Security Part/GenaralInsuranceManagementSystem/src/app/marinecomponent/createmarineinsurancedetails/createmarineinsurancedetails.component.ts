import { Component, OnInit } from '@angular/core';
import { MarineDetailsModel } from '../../model/MarineDetailsModel';
import { MarinedetailsService } from '../../service/marinedetails.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-createmarineinsurancedetails',
  templateUrl: './createmarineinsurancedetails.component.html',
  styleUrls: ['./createmarineinsurancedetails.component.css']
})
export class CreatemarineinsurancedetailsComponent implements OnInit {

  marineinsurancedetails: MarineDetailsModel = new MarineDetailsModel();
  errorMessage: string = '';
  isEditMode: boolean = false;
  exchangeRate: number = 1;
  originalSumInsured: number = 0; // এক্সচেঞ্জ রেট প্রয়োগ করার আগে sumInsured সংরক্ষণ করতে ব্যবহার হবে

  constructor(
    private marinedetailsService: MarinedetailsService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.isEditMode = true;
        this.getDetails(params['id']);
      } else {
        this.initializeForm();
      }
    });

    this.marinedetailsService.getExchangeRate().subscribe({
      next: (data) => {
        this.exchangeRate = data.rates.BDT || 1;
        console.log('Exchange rate fetched:', this.exchangeRate);
      },
      error: (err) => {
        console.error('Error fetching exchange rate:', err);
        this.errorMessage = 'Could not fetch exchange rate. Defaulting to 1.';
        this.exchangeRate = 1;
      }
    });
  }

  initializeForm() {
    const today = new Date();
    const formattedDate = today.toISOString().split('T')[0];
    this.marineinsurancedetails.date = formattedDate;
    this.marineinsurancedetails.coverage = 'Lorry Risk Only';
  }

  getDetails(id: number) {
    this.marinedetailsService.getByMarineDetailsId(id).subscribe({
      next: (data) => {
        this.marineinsurancedetails = data;
        this.originalSumInsured = this.marineinsurancedetails.sumInsured; // ডাটাবেস থেকে প্রাপ্ত মূল মান সংরক্ষণ
        console.log('Marine details data retrieved:', data);
      },
      error: (err) => {
        this.errorMessage = 'Could not fetch marine details. Please try again.';
        console.error('Error fetching marine details data:', err);
      }
    });
  }

  // কনভার্শন শুধুমাত্র তখন হবে যখন ফর্ম সাবমিট করা হবে এবং ডলার পরিবর্তন করা হয়েছে
  createOrUpdateMarineList() {
    // যদি নতুন এন্ট্রি হয় বা এডিট মোডে থাকলেও ডলার পরিবর্তন করা হয়
    if (!this.isEditMode || this.marineinsurancedetails.sumInsured !== this.originalSumInsured) {
      // নতুন বা পরিবর্তিত ডলারের উপর ভিত্তি করে টাকার কনভারশন
      this.marineinsurancedetails.sumInsured *= this.exchangeRate;
    }

    if (this.isEditMode) {
      // এডিট মোডে আপডেট করা হবে
      this.marinedetailsService.updateMarineList(this.marineinsurancedetails.id, this.marineinsurancedetails)
        .subscribe({
          next: (data) => {
            console.log('Marine insurance updated successfully', data);
            this.router.navigate(['/viewmarinelist']);
          },
          error: (err) => {
            this.errorMessage = 'There was an error updating the marine details. Please try again.';
            console.error('Error occurred while updating marine details', err);
          }
        });
    } else {
      // নতুন এন্ট্রি তৈরি করা হবে
      this.marinedetailsService.createMarinedetails(this.marineinsurancedetails)
        .subscribe({
          next: (data) => {
            console.log('Marine insurance created successfully', data);
            this.router.navigate(['/viewmarinelist']);
          },
          error: (err) => {
            this.errorMessage = 'There was an error creating the marine details. Please try again.';
            console.error('Error occurred while creating marine details', err);
          }
        });
    }
  }
}
