package jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
public class Delivery extends BaseEntity{

    @Id @GeneratedValue
    @Column(name="DELIVERY_ID")
    long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

}
