import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { RoomModel } from '../model/room.model';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  baseUrl:string="http://localhost:8080/api/room/";

  constructor(
    private http: HttpClient
  ) { }


  getAllRooms():Observable<any>{

    return this.http.get(this.baseUrl);

  }

  getRoomByHotel(id:string): Observable<RoomModel[]> {

    return this.http.get<RoomModel[]>(this.baseUrl+"r/searchroombyid?hotelid="+id)
      .pipe(
        catchError(this.handleError)
    )

  }


  private handleError(error:any) {
    console.error('An error occurred:', error);
    return throwError(() => new Error('test'));
  }


  createRoom(room: RoomModel): Observable<any> {

    return this.http.post(this.baseUrl+"/save", room);


  }

  deleteRoom(id: number): Observable<any> {
    return this.http.delete(this.baseUrl + "/" + id);
    
  }

  updateRoom(id: string, room: RoomModel): Observable<any> {

    return this.http.put(this.baseUrl + "/" + id, room);

  }

  getById(id: string): Observable<any> {

    return this.http.get(this.baseUrl + "/" + id);

  }
}
