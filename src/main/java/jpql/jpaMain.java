package jpql;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class jpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("TeamA");
            member.setAge(10);
            member.setType(MemberType.ADMIN);

            em.persist(member);

            em.flush();
            em.clear();

            String query = "select m.username, true, 'HELL''o' from Member m where m.type = :memberType";

            List<Object[]> resultList = em.createQuery(query)
                    .setParameter("memberType", MemberType.ADMIN)
                    .getResultList();

            for (Object[] objects : resultList) {
                System.out.println("objects[0] = " + objects[0]);
                System.out.println("objects[1] = " + objects[1]);
                System.out.println("objects[2] = " + objects[2]);
            }


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
