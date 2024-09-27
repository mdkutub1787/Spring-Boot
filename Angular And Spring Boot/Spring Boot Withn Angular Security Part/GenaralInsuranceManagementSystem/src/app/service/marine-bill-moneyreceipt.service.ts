import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MarineMoneyReceiptModel } from '../model/MarineMoneyReceipt.Model';

@Injectable({
  providedIn: 'root'
})
export class MarineBillMoneyreceiptService {

  baseUrl: string = "http://localhost:8080/api/marinemoneyreceipt/";


  constructor(private http: HttpClient) { }

  
  getMarineManyReceipt(): Observable<any> {
    return this.http.get(this.baseUrl);
  }

  createMarineManyReceipt(marinebill: MarineMoneyReceiptModel): Observable<any> {
    return this.http.post(this.baseUrl + "save", marinebill);
  }

 
  deleteMarineManyReceipt(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + "delete/" + id);
  }

  
  updateMarineManyReceipt(id: number, marinebill: MarineMoneyReceiptModel): Observable<any> {
    return this.http.put(this.baseUrl + "update/" + id, marinebill);
  }


  getByMarineManyReceiptId(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}${id}`);
  }
}
