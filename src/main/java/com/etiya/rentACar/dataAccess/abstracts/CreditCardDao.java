package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CreditCardDao extends JpaRepository<CreditCard,Integer> {
}
