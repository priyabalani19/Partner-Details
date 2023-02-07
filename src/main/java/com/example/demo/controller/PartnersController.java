package com.example.demo.controller;


import com.example.demo.domain.PartnersDetail;
import com.example.demo.domain.Response;
import com.example.demo.service.PartnersService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/retrieveDetails")
public class PartnersController {

    @Autowired
    public PartnersService partnersService;

    @ApiResponses({@ApiResponse(code = 200, message = " Partners details retrieved successfully" ),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Let us know we will help you out")})
    @GetMapping
    public ResponseEntity<List<PartnersDetail>> retrieveDetails(){
        return ResponseEntity.status(HttpStatus.OK).body(partnersService.retrieveAllDetails());
    }

//    @GetMapping("/outputResponse")
//    public ResponseEntity<Response> details() {
//        if (ObjectUtils.isEmpty(retrieveDetails())) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } else
//            return ResponseEntity.status(HttpStatus.OK).body(partnersService.response());
//    }
//
//    @PostMapping("/addPartnerDetails")
//    public ResponseEntity<Response> addDetails(@RequestBody PartnersDetail detail){
//        return ResponseEntity.status(HttpStatus.OK).body(partnersService.addPartner(detail));
//    }

    @PostMapping(value = "/computeAttendeeList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> addDetails(@RequestBody List<PartnersDetail> partners){
        return ResponseEntity.status(HttpStatus.OK).body(partnersService.getAttendeeList(partners));
    }
}
