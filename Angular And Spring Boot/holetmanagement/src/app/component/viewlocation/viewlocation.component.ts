import { Component } from '@angular/core';
import { LocationService } from '../../service/location.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-viewlocation',
  templateUrl: './viewlocation.component.html',
  styleUrl: './viewlocation.component.css'
})
export class ViewlocationComponent {


  locations: any ;
  
  constructor(
    private locationService: LocationService,
    private router: Router,
    private httpClient: HttpClient
  ) { }

  ngOnInit(): void {

    this.locationService.getAllLocations().subscribe({
      next: (res) => {
        console.log(res); 
      },
      error: (err) => {
        console.error('Error fetching locations:', err); 
      }
    });
  }
}
