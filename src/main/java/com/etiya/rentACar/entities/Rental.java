package com.etiya.rentACar.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data//Getter ve Setter oluşturur.
@AllArgsConstructor//parametreli constructor
@NoArgsConstructor//parametresiz constructor
@Entity//Sen bir entitiysin
@Table(name = "rentals")//tablo ismi
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "rent_date")
    private LocalDate rentDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "beforeRentalKilometer")
    private int beforeRentalKilometer;

    @Column(name = "afterRentalKilometer")
    private int afterRentalKilometer;

    @ManyToOne
    @JoinColumn(name = "rent_city_id", referencedColumnName = "id")//bunu versek nolur vermesek nolur?
    private City rentCityId;

    @ManyToOne
    @JoinColumn(name = "return_city_id", referencedColumnName = "id")
    private City returnCityId;

    @Column(name = "daily_price")
    private double dailyPrice;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "rental")
    private List<OrderedAdditionalService> orderedAdditionalServices;

    @OneToMany(mappedBy = "rental")
    private List<Invoice> invoices;

    @OneToOne(mappedBy = "rental")
    private Payment payment;


}
