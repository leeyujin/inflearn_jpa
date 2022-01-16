package jpabook.jpashop;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.AddressEntity;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Period;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class jpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Member member = new Member();
            member.setName("hello");
            member.setHomeAddress(new Address("homeCity","street","zipcode"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1","street","zipcode"));
            member.getAddressHistory().add(new AddressEntity("old2","street","zipcode"));




            em.persist(member);

            em.flush();
            em.clear();


            System.out.println("==========================");
            Member findMember = em.find(Member.class, member.getId());

           // homeCity -> newCity
            findMember.setHomeAddress(new Address("newCity","street","zipcode"));

             /*
            // 치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");
            */

            // old1 -> new1
            // 전제조건 : remove 수행을 위해선 해당 객체의 equals 가 구현(override) 돼있어야한다.
            // 주의, 값 타입 컬렉션 사용시 delete 전체 이후 insert 전체 수행. ( delete 하나, insert 하나 가 아님 )
            findMember.getAddressHistory().remove(new Address("old1","street","zipcode"));
            findMember.getAddressHistory().add(new AddressEntity("new1","street","zipcode"));


            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
