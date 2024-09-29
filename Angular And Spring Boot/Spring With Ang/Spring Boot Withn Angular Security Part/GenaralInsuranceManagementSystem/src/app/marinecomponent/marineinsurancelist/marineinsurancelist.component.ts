import { Component, OnInit } from '@angular/core';
import { MarineDetailsModel } from '../../model/MarineDetailsModel';
import { MarinedetailsService } from '../../service/marinedetails.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-marineinsurancelist',
  templateUrl: './marineinsurancelist.component.html',
  styleUrls: ['./marineinsurancelist.component.css'] 
})
export class MarineinsurancelistComponent implements OnInit {

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
  this.marinedetailsService.deleteMarineList(id).subscribe({
    next: res => {
      console.log('Marine detail deleted successfully', res);
      this.loadMarineDetails();
      this.router.navigate(['viewmarinelist']); 
    },
    error: error => {
      console.error('Error deleting marine detail', error);
    }
  });
}



  editMarineInsurance(id: number) {
    this.router.navigate(['updatemarinelist', id]);
  }

  detailsMarineInsurance(id: number) {
    this.router.navigate(['marinedetails', id]);
  }

  navigateToAddMarineList() {
    this.router.navigateByUrl('/createmarinelist');
  }

  navigateToAddMarineBill() {
    this.router.navigateByUrl('/createmarinebill');
  }
}
