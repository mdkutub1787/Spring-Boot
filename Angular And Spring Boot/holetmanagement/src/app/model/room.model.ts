import { HotelModel } from "./hotel.model";

export class RoomModel {
    id!: number;
    roomType!: string;
    image!: string;
    price!: number;
    adultNo!: number;
    childNo!: number;
    availability!: boolean;
    hotel!: HotelModel
}