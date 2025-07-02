package com.project.hms.modules.hotel_list.dto;

import com.project.hms.common.CountryEnum;

public class HotelResponse {
    private int id;
    private String name;
    private String city;
    private CountryEnum country;
    private String address;
    private double price;

    public HotelResponse() {
    }

    public HotelResponse(int id, String name, String city, CountryEnum country, String address, double price) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.country = country;
        this.address = address;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CountryEnum getCountry() {
        return country;
    }

    public void setCountry(CountryEnum country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
