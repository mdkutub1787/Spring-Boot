import { Injectable } from '@angular/core';
import { MarineDetailsModel } from '../model/MarineDetailsModel';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class MarinedetailsService {

  baseUrl: string = "http://localhost:8080/api/marinepolicy/";

  private exchangeRateApiUrl = 'https://api.exchangerate-api.com/v4/latest/USD';

  private newpolicy: MarineDetailsModel[] = [];

  constructor(private http: HttpClient, private authService: AuthService) {}

  // Get all MarineDetails
  getMarinedetails(): Observable<any> {
    return this.http.get(this.baseUrl);
  }

  // Get exchange rate from external API
  getExchangeRate(): Observable<any> {
    return this.http.get(this.exchangeRateApiUrl);
  }

  // Create MarineDetails
  createMarinedetails(marinelist: MarineDetailsModel): Observable<MarineDetailsModel> {
    const formData = new FormData();
    formData.append('marinelist', new Blob([JSON.stringify(marinelist)], { type: 'application/json' }));
    
    const token = this.authService.getToken();
    console.log('Token:', token); // Verify token
  
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
  
    return this.http.post<MarineDetailsModel>(this.baseUrl + "save", formData, { headers });
  }

  // Delete a policy by ID
  deleteMarineList(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + "delete/" + id);
  }

  // Update a policy by ID
  updateMarineList(id: number, marinelist: MarineDetailsModel): Observable<any> {
    return this.http.put(this.baseUrl + "update/" + id, marinelist);
  }

  // Get a MarineDetails by ID
  getByMarineDetailsId(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}${id}`);
  }

  // View all policies for a bill
  viewAllMarineListForMarineBill(): Observable<MarineDetailsModel[]> {
    return this.http.get<MarineDetailsModel[]>(this.baseUrl);
  }

  // Filter policies by policyholder, bankName, or ID
  searchByPolicyHolderAndBankNameAndId(searchTerm: string): MarineDetailsModel[] {
    const lowerCaseSearchTerm = searchTerm.trim().toLowerCase();
    if (!lowerCaseSearchTerm) {
      return this.newpolicy;
    }

    return this.newpolicy.filter(item =>
      (item.policyholder?.toLowerCase().includes(lowerCaseSearchTerm) ||
       item.bankName?.toLowerCase().includes(lowerCaseSearchTerm) ||
       item.id?.toString().includes(lowerCaseSearchTerm))
    );
  }
}
