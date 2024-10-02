import { Injectable } from '@angular/core';
import { MarineDetailsModel } from '../model/MarineDetailsModel';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MarinedetailsService {

  baseUrl: string = "http://localhost:8080/api/marinepolicy/";

  private exchangeRateApiUrl = 'https://api.exchangerate-api.com/v4/latest/USD';

  // Initialize the array for storing policies
  private newpolicy: MarineDetailsModel[] = [];

  constructor(private http: HttpClient) { }

  // Get all MarineDetails from the server
  getMarinedetails(): Observable<MarineDetailsModel[]> {
    return this.http.get<MarineDetailsModel[]>(this.baseUrl);
  }

  // Get exchange rate from external API
  getExchangeRate(): Observable<any> {
    return this.http.get(this.exchangeRateApiUrl);
  }

  // Create a new marine policy
  createMarinedetails(marinelist: MarineDetailsModel): Observable<any> {
    return this.http.post(this.baseUrl + "save", marinelist);
  }

  // Delete a marine policy by ID
  deleteMarineList(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + "delete/" + id);
  }

  // Update a marine policy by ID
  updateMarineList(id: number, marinelist: MarineDetailsModel): Observable<any> {
    return this.http.put(this.baseUrl + "update/" + id, marinelist);
  }

  // Get a MarineDetails by ID
  getByMarineDetailsId(id: number): Observable<MarineDetailsModel> {
    return this.http.get<MarineDetailsModel>(`${this.baseUrl}${id}`);
  }

  // View all policies for bill (typed Observable)
  viewAllMarineListForMarineBill(): Observable<MarineDetailsModel[]> {
    return this.http.get<MarineDetailsModel[]>(this.baseUrl);
  }

  // Filter policies by policyholder, bankName, or ID  
  searchByPolicyHolderAndBankNameAndId(searchTerm: string): MarineDetailsModel[] {
    const lowerCaseSearchTerm = searchTerm.trim().toLowerCase();
    
    // Filter on newpolicy data if search term is provided
    return this.newpolicy.filter(item =>
      item.policyholder?.toLowerCase().includes(lowerCaseSearchTerm) ||
      item.bankName?.toLowerCase().includes(lowerCaseSearchTerm) ||
      item.id?.toString().includes(lowerCaseSearchTerm)
    );
  }

  // Load marine details to `newpolicy` (utility method)
  loadMarineDetails(): void {
    this.getMarinedetails().subscribe(data => {
      this.newpolicy = data;  // Populate newpolicy with the latest data
    });
  }
}
