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

  constructor(
    private marinedetailsService: MarinedetailsService,
    private router: Router
  ) {}


  ngOnInit(): void {
    const today = new Date();
    const formattedDate = today.toISOString().split('T')[0]; 
    this.marineinsurancedetails.date = formattedDate;
    this.marineinsurancedetails.riskCovered = 'Lorry Risk Only'; 
    
}


  createMarineDetails() {
    this.marinedetailsService.createMarinedetails(this.marineinsurancedetails)
      .subscribe({
        next: (data) => {
          console.log('marice insurance  created successfully', data);
          this.router.navigate(['/viewmarinedeails']);
        },
        error: (err) => {
          console.error('Error occurred while creating policy', err);
          this.errorMessage = 'There was an error creating the policy. Please try again.';
        }
      });
  }



}
