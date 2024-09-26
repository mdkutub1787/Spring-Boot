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
}
