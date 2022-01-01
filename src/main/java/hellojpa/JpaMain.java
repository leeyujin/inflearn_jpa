package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // application 기동 시 서버당 하나만 생성됨
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 고객의 request가 올때마다 생성됨 -> thread마다 공유하면 안됨
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            em.persist(member1);
            em.persist(member2);
            System.out.println("============");

            // commit 시점에 db에 insert sql을 보낸다
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            // db connection을 물고감
            em.close();
        }

        emf.close();
    }
}
