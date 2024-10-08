import { Component, OnInit } from '@angular/core';
import { HotelService } from '../../service/hotel.service';
import { LocationService } from '../../service/location.service';
import { RoomService } from '../../service/room.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-viewhotel',
  templateUrl: './viewhotel.component.html',
  styleUrl: './viewhotel.component.css'
})
export class ViewhotelComponent implements OnInit{

  locations: any;
  hotels: any;
  rooms:any;

  constructor(
    private hotelService: HotelService,
    private locationService: LocationService,
    private roomService: RoomService,
    private router: Router

  ) { }

  ngOnInit(): void {

    this.loadLocations();
  }


  loadLocations() {

    this.locations = this.locationService.getAllLocations();
    this.hotelService.getAllHotel().subscribe({

      next: res => {
        this.hotels = res;
      },

      error: err => {
        console.log(err)

      }

    });


  }
  viewRooms(hotelId: string): void {
    
    this.router.navigate(['/room', hotelId]);
  }


}
