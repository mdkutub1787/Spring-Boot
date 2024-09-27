import { Component, OnInit } from '@angular/core';
import { MarineBillModel } from '../../model/MarineBill.Model';
import { MarinebillService } from '../../service/marinebill.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-marineinsurancebill',
  templateUrl: './marineinsurancebill.component.html',
  styleUrl: './marineinsurancebill.component.css'
})
export class MarineinsurancebillComponent implements OnInit{
  
  marinebill!: MarineBillModel[]; 
  

  constructor(
    private marinebillService: MarinebillService,
    private router: Router
  ) { }

  ngOnInit() {
    this.loadMarineDetails(); 
  }

  loadMarineDetails() {
    this.marinebillService.getMarineBill().subscribe((data: MarineBillModel[]) => {
      this.marinebill = data;             
      this.marinebill = [...this.marinebill]; 
    });
  }

  deleteMarineBill(id: number) {
    this.marinebillService.deleteMarineBill(id).subscribe({
      next: res => {
        console.log(res);
        this.loadMarineDetails()
        this.router.navigate(['/viewmarinebill']);
      },
      error: error => {
        console.log(error);
      }
    });
  }

 
  editMarineBill(id: number) {
    this.router.navigate(['updatemarinebill', id]);
  }

  detailsMarineBill(id: number) {
    this.router.navigate(['marinebilldetails', id]);
  }

  navigateToAddMarineBill() {
    this.router.navigateByUrl('/createmarinebill');
  }

  round(value: number): number {
    return Math.round(value);
  }

  
}
