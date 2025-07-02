package com.project.hms.modules.reservation.dto;

import com.project.hms.modules.reservation.entity.ReservationEntity;

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
