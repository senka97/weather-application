package com.hybrid.weatherappbackend.controller;

import com.hybrid.weatherappbackend.dto.CityShowDTO;
import com.hybrid.weatherappbackend.mapper.CityMapper;
import com.hybrid.weatherappbackend.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/api/cities")
public class CityController {

    public final CityService service;
    public final CityMapper mapper;

    public CityController(CityService service, CityMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Get all cities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returning all cities",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = CityShowDTO.class))) }) })
    @GetMapping()
    public ResponseEntity<List<CityShowDTO>> getAll(){
        return ResponseEntity.ok()
                        .body(service.getAll()
                        .stream()
                        .map(mapper::toDTO)
                        .collect(Collectors.toList()));
    }

    @Operation(summary = "Get all cities sorted ascending by average temperature for next 5 days")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returning all cities sorted",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = CityShowDTO.class))) }) })
    @GetMapping(value = "/sort")
    public ResponseEntity<List<CityShowDTO>> getAllSorted(){
        return ResponseEntity.ok()
                        .body(service.getAllSorted()
                        .stream()
                        .map(mapper::toDTO)
                        .collect(Collectors.toList()));
    }

}
