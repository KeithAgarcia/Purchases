package com.theironyard;

import javax.persistence.*;

/**
 * Created by Keith on 5/10/17.
 */

@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String date;

    @Column(nullable = false)
    int credit_card;

    @Column(nullable = false)
    int cvv;

    @Column(nullable = false)
    String category;

    @ManyToOne
    Customer customer;

    public Purchase() {
    }

    public Purchase(String date, int credit_card, int cvv, String category, Customer customer) {
        this.date = date;
        this.credit_card = credit_card;
        this.cvv = cvv;
        this.category = category;
        this.customer = customer;
    }
}
