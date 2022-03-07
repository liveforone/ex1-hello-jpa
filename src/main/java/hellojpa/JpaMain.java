package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();  //트랜잭션 시작

        try {  //에러가 없을시

            Member member1 = new Member();
            member1.setUsername("A");

            Member member2 = new Member();
            member2.setUsername("B");

            Member member3 = new Member();
            member3.setUsername("C");

            System.out.println("==========");

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            System.out.println("member1 = " + member1.getId());
            System.out.println("member2 = " + member2.getId());
            System.out.println("member3 = " + member3.getId());

            System.out.println("==========");

            tx.commit();  //커밋해줌
        } catch (Exception e) {  //에러발생하면 롤백
            tx.rollback();
        } finally {
            em.clear();  //엔티티매니저 닫음
        }

        emf.close();  //엔티티 팩토리 닫음
    }
}
