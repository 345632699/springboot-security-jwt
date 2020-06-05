package com.hotel.admin.service;

import com.hotel.admin.query.ApartmentQuery;
import com.hotel.model.Apartment;

import java.util.List;

public interface ApartmentService {
    List<Apartment> list();
    Apartment createApartment(ApartmentQuery apartmentQuery);
    void updateApartment(Apartment apartment);
    void deleteApartment(Integer id);
}
