package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();  //트랜잭션 시작

        try {  //에러가 없을시
            Member member = em.find(Member.class, 1L);  //조회
            member.setName("HelloJPA");

//            em.persist(member);  //JPA에 저장

            tx.commit();  //커밋해줌
        } catch (Exception e) {  //에러발생하면 롤백
            tx.rollback();
        } finally {
            em.clear();  //엔티티매니저 닫음
        }

        emf.close();  //엔티티 팩토리 닫음
    }
}