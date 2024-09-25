import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { HotelModel } from '../model/hotel.model';

@Injectable({
  providedIn: 'root'
})
export class HotelService {

  baseUrl:string="http://localhost:8080/api/hotel/";

  constructor(
    private http: HttpClient
  ) { }


  getAllHotel():Observable<any>{

    return this.http.get(this.baseUrl);

  }

  getHotelById(hotelId: string): Observable<any> {
    return this.http.get<any>(this.baseUrl+hotelId);
  }


  private handleError(error:any) {
    console.error('An error occurred:', error);
    return throwError(() => new Error('test'));
  }


  createHotel(hotel: HotelModel, image: File): Observable<HotelModel> {

    const formData = new FormData();

    formData.append('hotel', new Blob([JSON.stringify(hotel)], { type: 'application/json' }));

    // Append image file
    formData.append('image', image);

    return this.http.post<HotelModel>(this.baseUrl + "save", formData);

  }

  deleteHotel(id: string): Observable<any> {
    return this.http.delete(this.baseUrl + "/" + id);
    
  }

  updateHotel(id: string, hotel: HotelModel): Observable<any> {

    return this.http.put(this.baseUrl + "/" + id, hotel);

  }

  getById(id: string): Observable<any> {

    return this.http.get(this.baseUrl + "/" + id);

  }
}
