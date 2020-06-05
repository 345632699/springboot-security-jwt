package com.hotel.admin.controller;

import com.hotel.admin.query.PageQuery;
import com.hotel.admin.query.SpecsItemQuery;
import com.hotel.admin.query.SpecsQuery;
import com.hotel.admin.service.SpecsService;
import com.hotel.common.ApiResponse;
import com.hotel.common.PageModel;
import com.hotel.model.RoomSpecs;
import com.hotel.model.RoomSpecsItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/sepcs")
public class SpecsController {

    @Autowired
    SpecsService specsService;

    public ApiResponse index() {
        return ApiResponse.ofSuccess();
    }

    @GetMapping("/list")
    public ApiResponse specsList(@RequestBody PageQuery pageQuery) {
        PageModel<RoomSpecs> specsPageModel = specsService.specList(pageQuery);
        return ApiResponse.ofSuccess(specsPageModel);
    }

    @GetMapping("/all")
    public ApiResponse allSpecs() {
        List<RoomSpecs> roomSpecs = specsService.allSpecs();
        return ApiResponse.ofSuccess(roomSpecs);
    }

    @PostMapping(value = "/create")
    public ApiResponse create(@Valid @RequestBody SpecsQuery specs) {
        RoomSpecs spec = specsService.createSpecs(specs);
        return ApiResponse.ofSuccess(spec);
    }

    @PostMapping(value = "/update")
    public ApiResponse update(@Valid @RequestBody RoomSpecs specs) {
        specsService.updateSpecs(specs);
        return ApiResponse.ofSuccess();
    }

    @PostMapping(value = "delete")
    public ApiResponse deleteSpecsRecord(@RequestBody RoomSpecs specs) {
        specsService.deleteSpecsRecord(specs.getId());
        return ApiResponse.ofSuccess();
    }

    @GetMapping(value = "itemList/{id}")
    public ApiResponse specItemLis(@PathVariable Integer id) {
        List<RoomSpecsItem> specsItemList = specsService.findSpecsItemListById(id);
        return ApiResponse.ofSuccess(specsItemList);
    }

    @PostMapping(value = "/createItem")
    public ApiResponse createItem(@Valid @RequestBody SpecsItemQuery specsItemQuery) {
        RoomSpecsItem specsItem = specsService.createItem(specsItemQuery);
        return ApiResponse.ofSuccess(specsItem);
    }

    @PostMapping(value = "/updateItem")
    public ApiResponse updateItem(@Valid @RequestBody RoomSpecsItem specsItem) {
        specsService.updateItem(specsItem);
        return ApiResponse.ofSuccess();
    }

    @PostMapping(value = "/deleteItem")
    public ApiResponse deleteSpecsItemRecord(@RequestBody RoomSpecsItem specsItem) {
        specsService.deleteSpecsItemRecord(specsItem.getId());
        return ApiResponse.ofSuccess();
    }
}
