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
            /* 등록
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");
            em.persist(member);
            */
            /*  수정

            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");
            */
            List<Member> result = em.createQuery("select m from Member as m ", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(2)
                    .getResultList();
            for(Member member : result){
                System.out.println(member.getName());
            }
            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        }finally {
            // db connection을 물고감
            em.close();
        }

        emf.close();
    }
}
