import { Component, OnInit } from '@angular/core';
import { HotelModel } from '../../model/hotel.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HotelService } from '../../service/hotel.service';
import { Router } from '@angular/router';
import { LocationService } from '../../service/location.service';
import { LocationModel } from '../../model/location.model';

@Component({
  selector: 'app-createhotel',
  templateUrl: './createhotel.component.html',
  styleUrl: './createhotel.component.css'
})
export class CreatehotelComponent implements OnInit{

  image: File | null = null;
  hotel: HotelModel = new HotelModel();
  locations: LocationModel[] = []; 
  formGroup!: FormGroup;


  constructor(
    private hotelService: HotelService,  
    private formBuilder: FormBuilder,
    private router: Router,
    private locationService: LocationService
  ) { }

  ngOnInit(): void {
    this.loadLocations();

    this.formGroup = this.formBuilder.group({
      name: ['', Validators.required],
      address: ['', Validators.required],
      maxPrice: ['', [Validators.required, Validators.min(0)]],
      minPrice: ['', [Validators.required, Validators.min(0)]],
      rating: ['', [Validators.required, Validators.pattern('[0-5](\\.\\d{1,2})?')]],  
      location: [null, Validators.required]  
    });
  }

  onFileSelected(event: any) {
    this.image = event.target.files[0];
  }

  loadLocations() {
    this.locationService.getAllLocations().subscribe({
      next: res => {
        this.locations = res;
        console.log(this.locations);
      },
      error: err => {
        console.error('Error fetching locations:', err);
      }
    });
  }

  onSubmit() {
    if (this.formGroup.invalid) {
      alert('Please fill out all required fields.');
      return;
    }

    if (this.image) {
      const hotel: HotelModel = {
        ...this.formGroup.value,
        location: { id: this.formGroup.value.location } as LocationModel
      };

      this.hotelService.createHotel(hotel, this.image).subscribe({
        next: res => {
          console.log('Hotel added successfully', res);
          this.router.navigate(['/hotel']);
        },
        error: err => {
          console.error('Error adding hotel:', err);
        }
      });
    } else {
      alert('Please select an image.');
    }
  }
}
