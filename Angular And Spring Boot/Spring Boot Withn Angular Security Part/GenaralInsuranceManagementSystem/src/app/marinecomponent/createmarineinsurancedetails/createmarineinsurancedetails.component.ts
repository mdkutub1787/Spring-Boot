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
  originalSumInsured: number = 0; 

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
        this.originalSumInsured = this.marineinsurancedetails.sumInsured;
        console.log('Marine details data retrieved:', data);
      },
      error: (err) => {
        this.errorMessage = 'Could not fetch marine details. Please try again.';
        console.error('Error fetching marine details data:', err);
      }
    });
  }

 
  createOrUpdateMarineList() {
    if (!this.isEditMode || this.marineinsurancedetails.sumInsured !== this.originalSumInsured) {
      this.marineinsurancedetails.sumInsured *= this.exchangeRate;
    }

    if (this.isEditMode) {
    
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
