package com.theironyard;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Keith on 5/10/17.
 */

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
     Customer findFirstByName(String name);
}
