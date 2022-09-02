package com.restaurantreservation.aruaru.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Reservation_select {
	
    String reservation_seat;			//좌석종류
    String can_people;					//수용인수
    int restaurant_num;					//식당번호
}
