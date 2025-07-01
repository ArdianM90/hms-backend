package com.project.hms.reservation.controller;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class AddReservationRequest {
    private String clientId;
    private String apiKey;
    private String name;
    private String surname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateEnd;

    public AddReservationRequest(String clientId, String apiKey, String name, String surname,
                                 LocalDate dateStart, LocalDate dateEnd) {
        this.clientId = clientId;
        this.apiKey = apiKey;
        this.name = name;
        this.surname = surname;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }
}
