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
            Member member = em.find(Member.class, 150L);
            member.setName("zzzz");

            // 데이터 update 시 persist를 호출하지않아도 update됨
//            em.persist(member);

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
