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

            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member = new Member();
            member.setUsername("관리자1");
            member.setAge(10);
            member.setTeam(teamA);
            member.setType(MemberType.ADMIN);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            member2.setAge(10);
            member2.setTeam(teamA);
            member2.setType(MemberType.ADMIN);

            Member member3 = new Member();
            member3.setUsername("관리자3");
            member3.setAge(10);
            member3.setTeam(teamB);
            member3.setType(MemberType.ADMIN);

            em.persist(member);
            em.persist(member2);
            em.persist(member3);

            // FLUSH 자동 호출
            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();

            // 10으로 나옴 -> 벌크연산은 영속성컨택스트를 거치지않고 DB에 직접 연산 수행
            // System.out.println(member.getAge());

            // 따라서, 벌크연산 이후 em.clear() 이후 em.find로 다시 가져오자
            em.clear();
            Member findMember = em.find(Member.class, member.getId());
            System.out.println("member1 = " + findMember.getAge());

            System.out.println("resultCount = " + resultCount);


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
