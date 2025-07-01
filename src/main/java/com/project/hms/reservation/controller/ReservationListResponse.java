package com.project.hms.reservation.controller;

import com.project.hms.reservation.repository.ReservationEntity;

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
