package com.etiya.rentACar.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ordered_additional_services")
public class OrderedAdditionalService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "rental_id")//araba kiralarken bir çok sipariş verilebilir.
    private Rental rental;

    @ManyToOne
    @JoinColumn(name = "additional_service_id")//bir siparişte birden fazla hizmet alınabilir.
    private AdditionalService additionalService;
}
