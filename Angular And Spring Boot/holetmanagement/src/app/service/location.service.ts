import { HttpClient } from '@angular/common/http';

import { catchError, Observable, throwError } from 'rxjs';
import { LocationModel } from '../model/location.model';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  baseUrl: string = "http://localhost:8080/api/location/";


  constructor(private http: HttpClient) { }


  getAllLocations():Observable<any>{

    return this.http.get(this.baseUrl);

  }


 


}
