import { Component, OnInit } from '@angular/core';
import { MarineBillModel } from '../../model/MarineBill.Model';
import { MarinebillService } from '../../service/marinebill.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-marineinsurancebill-details',
  templateUrl: './marineinsurancebill-details.component.html',
  styleUrl: './marineinsurancebill-details.component.css'
})
export class MarineinsurancebillDetailsComponent implements OnInit{

  id!: number;
  marinebill!: MarineBillModel;

  constructor(
    private marinebillService: MarinebillService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.marinebill = new MarineBillModel();
    this.id = this.route.snapshot.params['id'];
    this.marinebillService.getByMarineBillId(this.id).subscribe({
      next: (data) => {
        console.log('Marine details data retrieved:', data);
        this.marinebill = data;
      },
      error: (err) => {
        console.error('Error fetching Marine details data:', err);
      }
    });
  }

 

}
