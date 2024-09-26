import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MarinebillService {

  baseUrl: string = "http://localhost:8080/api/marinebill/";


  constructor(private http: HttpClient) { }

  
  getMarineBill(): Observable<any> {
    return this.http.get(this.baseUrl);
  }
}
