import {RoomInfo} from "./room-info";
import {Reservation} from "./reservation";

export interface SeatPanelInput {
  roomInfo: RoomInfo;
  reservations: Reservation[];
}
