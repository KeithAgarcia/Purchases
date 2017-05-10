package com.theironyard;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Keith on 5/10/17.
 */
public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
    List<Purchase> findByCategory(String Category);




}
