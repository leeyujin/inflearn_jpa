package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Inheritance(strategy = InheritanceType.JOINED) // default = 단일테이블
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn // DType 생성 - 자식 타입(Album,Movie,Book) 들어감
// 추상클래스를 붙여줘야 InheritanceType.TABLE_PER_CLASS 일 때 ITEM table이 생성안됨 (붙이지 않으면 해당 클래스 자체를 사용하는 경우가 존재하여 테이블 생성됨)
public abstract class Item extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

}
