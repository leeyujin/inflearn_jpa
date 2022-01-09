package jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
public class Delivery {

    @Id @GeneratedValue
    @Column(name="DELIVERY_ID")
    long id;

    @OneToOne(mappedBy = "delivery")
    Order order;

    private String city;
    private String street;
    private String zipcode;
    private DeliveryStatus status;

}
