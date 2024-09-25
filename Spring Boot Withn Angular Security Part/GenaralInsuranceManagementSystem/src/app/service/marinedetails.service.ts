import { Injectable } from '@angular/core';
import { MarineDetailsModel } from '../model/MarineDetailsModel';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MarinedetailsService {

  baseUrl: string = "http://localhost:8080/api/marine/";

  private exchangeRateApiUrl = 'https://api.exchangerate-api.com/v4/latest/USD'; 

  private newpolicy: MarineDetailsModel[] = []; 

  constructor(private http: HttpClient) { }

  
  getMarinedetails(): Observable<any> {
    return this.http.get(this.baseUrl);
  }

  getExchangeRate(): Observable<any> {
    return this.http.get(this.exchangeRateApiUrl);
  }

  // Create a new policy
  createMarinedetails(policy: MarineDetailsModel): Observable<any> {
    return this.http.post(this.baseUrl + "save", policy);
  }

  // Delete a policy by ID
  deleteMarineDetails(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + "delete/" + id);
  }

  // Update a policy by ID
  updatePolicy(id: number, policy: MarineDetailsModel): Observable<any> {
    return this.http.put(this.baseUrl + "update/" + id, policy);
  }

  // Get a policy by ID
  getByPolicyId(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}${id}`);
  }

  // View all policies for bill (typed Observable)
  viewAllPolicyForBill(): Observable<MarineDetailsModel[]> {
    return this.http.get<MarineDetailsModel[]>(this.baseUrl);
  }

}
