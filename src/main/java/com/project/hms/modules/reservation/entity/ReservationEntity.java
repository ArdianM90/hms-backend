package com.project.hms.modules.reservation.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String clientName;
    private String clientSurname;
    private LocalDate reservationDateStart;
    private LocalDate reservationDateEnd;

    public ReservationEntity() {
    }

    public ReservationEntity(String clientName, String clientSurname, LocalDate reservationDateStart, LocalDate reservationDateEnd) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.reservationDateStart = reservationDateStart;
        this.reservationDateEnd = reservationDateEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public LocalDate getReservationDateStart() {
        return reservationDateStart;
    }

    public void setReservationDateStart(LocalDate reservationDateStart) {
        this.reservationDateStart = reservationDateStart;
    }

    public LocalDate getReservationDateEnd() {
        return reservationDateEnd;
    }

    public void setReservationDateEnd(LocalDate reservationDateEnd) {
        this.reservationDateEnd = reservationDateEnd;
    }
}
