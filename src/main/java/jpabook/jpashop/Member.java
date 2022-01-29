package jpabook.jpashop;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    List<Order> order = new ArrayList<>();

}
