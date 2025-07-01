package com.project.hms.reservation;

import com.project.hms.reservation.controller.ReservationListResponse;
import com.project.hms.reservation.repository.ReservationCrudDao;
import com.project.hms.reservation.repository.ReservationEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    private ReservationCrudDao reservationCrudDao;

    public ReservationService(ReservationCrudDao reservationCrudDao) {
        this.reservationCrudDao = reservationCrudDao;
    }

    public void createReservation(String clientName, String clientSurname, LocalDate reservationDateStart, LocalDate reservationDateEnd) {
        ReservationEntity reservation = new ReservationEntity(clientName, clientSurname, reservationDateStart, reservationDateEnd);
        reservationCrudDao.save(reservation);
        System.out.println("----reservation created----");
    }

    public ReservationListResponse getAllReservations() {
        List<ReservationEntity> reservations = new ArrayList<>();
        reservationCrudDao.findAll().forEach(reservations::add);
        ReservationListResponse response = new ReservationListResponse();
        response.setReservationList(reservations);
        return response;
    }
}
