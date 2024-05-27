package com.bluefox.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleModelData(@JsonAlias("nome") String vehicleModel) {

    // public getVehicleModel() {
    //     return vehicleModel;
    // }
}
