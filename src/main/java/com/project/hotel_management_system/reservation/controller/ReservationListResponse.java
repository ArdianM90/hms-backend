package com.project.hotel_management_system.reservation.controller;

import com.project.hotel_management_system.reservation.repository.ReservationEntity;

import java.util.List;

public class ReservationListResponse {

    private List<ReservationEntity> reservationList;

    public List<ReservationEntity> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<ReservationEntity> reservationList) {
        this.reservationList = reservationList;
    }
}
