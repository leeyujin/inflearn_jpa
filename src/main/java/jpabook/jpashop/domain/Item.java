package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public class Item {

    @Id
    @GeneratedValue
    private Long id;
    private int price;
    private int stockQuantity;

    @ManyToMany
    @JoinColumn(name = "CATEGORY_ID")
    List<Category> categories = new ArrayList<>();
}
