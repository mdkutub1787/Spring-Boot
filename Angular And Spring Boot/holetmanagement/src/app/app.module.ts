
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ViewhotelComponent } from './component/viewhotel/viewhotel.component';
import { CreatehotelComponent } from './component/createhotel/createhotel.component';
import { CreatelocationComponent } from './component/createlocation/createlocation.component';
import { ViewroomComponent } from './component/viewroom/viewroom.component';
import { CreateroomComponent } from './component/createroom/createroom.component';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { ViewlocationComponent } from './component/viewlocation/viewlocation.component';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RoomByHotelComponent } from './component/room-by-hotel/room-by-hotel.component';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    ViewhotelComponent,
    CreatehotelComponent,
    CreatelocationComponent,
    ViewroomComponent,
    CreateroomComponent,
    ViewlocationComponent,
    RoomByHotelComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [

    provideHttpClient(
      withFetch(),
    ),
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
