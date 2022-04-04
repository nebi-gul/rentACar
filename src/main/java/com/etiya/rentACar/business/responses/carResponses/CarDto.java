package com.etiya.rentACar.business.responses.carResponses;

import com.etiya.rentACar.entities.CarStates;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private int id;

    private double dailyPrice;

    private String description;

    private double modelYear;

    private  int brandId;

    private  int colorId;

    private int cityId;

    private String brandName;

    private String colorName;

    private CarStates carStateName;

    private  double kilometerInfo;


}
