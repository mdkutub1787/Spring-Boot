import { Component, OnInit } from '@angular/core';
import { MarineDetailsModel } from '../../model/MarineDetailsModel';
import { MarinedetailsService } from '../../service/marinedetails.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-createmarineinsurancedetails',
  templateUrl: './createmarineinsurancedetails.component.html',
  styleUrl: './createmarineinsurancedetails.component.css'
})

export class CreatemarineinsurancedetailsComponent implements OnInit{

  marineinsurancedetails: MarineDetailsModel = new MarineDetailsModel();
  errorMessage: string = '';
  submitted = false;
  exchangeRate: number = 1; 

  constructor(
    private marinedetailsService: MarinedetailsService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const today = new Date();
    const formattedDate = today.toISOString().split('T')[0];
    this.marineinsurancedetails.date = formattedDate;
    this.marineinsurancedetails.coverage = 'Lorry Risk Only';
    this.marinedetailsService.getExchangeRate().subscribe({
      next: (data) => {
        this.exchangeRate = data.rates.BDT;
        console.log('Exchange rate fetched:', this.exchangeRate);
      },
      error: (err) => {
        console.error('Error fetching exchange rate:', err);
        this.errorMessage = 'Could not fetch exchange rate. Defaulting to 1.';
      }
    });
  }

  createMarineList() {
    this.marineinsurancedetails.sumInsured *= this.exchangeRate;

    this.marinedetailsService.createMarinedetails(this.marineinsurancedetails)
      .subscribe({
        next: (data) => {
          console.log('Marine insurance created successfully', data);
          this.router.navigate(['/viewmarinelist']);
        },
        error: (err) => {
          console.error('Error occurred while creating marine details', err);
          this.errorMessage = 'There was an error creating the marine details. Please try again.';
        }
      });
  }

}
