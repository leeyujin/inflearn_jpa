package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Album extends Item{
    private String artist;
    private String etc;

}
