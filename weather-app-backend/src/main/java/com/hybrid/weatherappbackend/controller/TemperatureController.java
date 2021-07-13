package com.hybrid.weatherappbackend.controller;

import com.hybrid.weatherappbackend.dto.TemperatureResponseDTO;
import com.hybrid.weatherappbackend.mapper.TemperatureMapper;
import com.hybrid.weatherappbackend.service.TemperatureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/temperatures")
public class TemperatureController {

    public final TemperatureService service;
    public final TemperatureMapper mapper;

    public TemperatureController(TemperatureService service, TemperatureMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Operation(summary = "Get daily average temperature for all cities for chosen number of days starting today")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returning average temperature for all cities",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = TemperatureResponseDTO.class))) }) })
    @GetMapping
    public ResponseEntity<List<TemperatureResponseDTO>> getAll(@RequestParam Map<String, String> queryParams){
        return ResponseEntity.ok().body(
                service.getForAllCities(Integer.parseInt(queryParams.get("numOfDays"))));
    }

    @Operation(summary = "Get daily average temperature for city for chosen number of days starting today")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returning average temperature for city",
                    content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TemperatureResponseDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "City not found",
                    content = @Content) })
    @GetMapping(value = "/city")
    public ResponseEntity<List<TemperatureResponseDTO>> get(@RequestParam Map<String, String> queryParams) {

        return ResponseEntity.ok().body(
                service.getForCity(queryParams.get("name"),
                        Integer.parseInt(queryParams.get("numOfDays")),
                        LocalDate.now()));
    }

    /**
     * TODO Get temperatures for chosen city and day on every third hour
     */

}
