package com.hotel.admin.controller;

import com.hotel.admin.query.ApartmentQuery;
import com.hotel.admin.service.ApartmentService;
import com.hotel.common.ApiResponse;
import com.hotel.model.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/apartment")
public class ApartmentController {

    @Autowired
    private ApartmentService apartmentService;

    @GetMapping(value = "list")
    public ApiResponse apartmentList() {
        return ApiResponse.ofSuccess(apartmentService.list());
    }

    @PostMapping(value = "create")
    public ApiResponse create(@Valid @RequestBody ApartmentQuery apartmentQuery) {
        Apartment apartment = apartmentService.createApartment(apartmentQuery);
        return ApiResponse.ofSuccess(apartment);
    }

    @PostMapping(value = "update")
    public ApiResponse update(@RequestBody Apartment apartment) {
         apartmentService.updateApartment(apartment);
        return ApiResponse.ofSuccess();
    }

    @PostMapping(value = "delete")
    public ApiResponse deleteApartment(@RequestBody Apartment apartment) {
        apartmentService.deleteApartment(apartment.getId());
        return ApiResponse.ofSuccess();
    }




}
