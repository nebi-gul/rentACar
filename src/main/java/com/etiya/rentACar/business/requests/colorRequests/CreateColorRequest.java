package com.etiya.rentACar.business.requests.colorRequests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateColorRequest {
    @JsonIgnore//request ve response de id 'yi göstermek istemiyorum.
    private int id;

    private String name;

}
