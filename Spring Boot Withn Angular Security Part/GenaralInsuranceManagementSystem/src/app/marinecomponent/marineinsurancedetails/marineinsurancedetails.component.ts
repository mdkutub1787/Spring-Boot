import { Component } from '@angular/core';
import { MarineDetailsModel } from '../../model/MarineDetailsModel';
import { MarinedetailsService } from '../../service/marinedetails.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-marineinsurancedetails',
  templateUrl: './marineinsurancedetails.component.html',
  styleUrl: './marineinsurancedetails.component.css'
})
export class MarineinsurancedetailsComponent {


            
  marinedetails!: MarineDetailsModel[]; 
  

  constructor(
    private marinedetailsService: MarinedetailsService,
    private router: Router
  ) { }

  ngOnInit() {
    this.loadMarineDetails(); 
  }

  loadMarineDetails() {
    this.marinedetailsService.getMarinedetails().subscribe((data: MarineDetailsModel[]) => {
      this.marinedetails = data;             
      this.marinedetails = [...this.marinedetails]; 
    });
  }

  
  deleteMarineDetails(id: number) {
    this.marinedetailsService.deleteMarineDetails(id).subscribe({
      next: res => {
        console.log(res);
        this.loadMarineDetails()
        this.router.navigate(['/viewmarinedeails']);
      },
      error: error => {
        console.log(error);
      }
    });
  }
}
