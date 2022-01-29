package jpabook.jpashop;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    Member member;

    @OneToOne(fetch = FetchType.LAZY)
    Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
