
import { RouterModule, Routes } from '@angular/router';
import { ViewlocationComponent } from './component/viewlocation/viewlocation.component';
import { NgModule } from '@angular/core';
import { ViewhotelComponent } from './component/viewhotel/viewhotel.component';
import { RoomByHotelComponent } from './component/room-by-hotel/room-by-hotel.component';
import { CreatehotelComponent } from './component/createhotel/createhotel.component';

const routes: Routes = [
  { path: 'location', component: ViewlocationComponent },
  { path: 'hotel', component: ViewhotelComponent },
  {path: 'room/:hotelId', component:RoomByHotelComponent},
  {path: 'createhotel', component:CreatehotelComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
