package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();  //트랜잭션 시작

        try {  //에러가 없을시

            Address address = new Address("city", "street", "zipcode");  //기존 값

            Member member = new Member();
            member.setUsername("hello");
            member.setHomeAddress(address);
            em.persist(member);

            Address newAddress = new Address("NewCity", address.getStreet(), address.getZipcode());  //새값
            member.setHomeAddress(newAddress);  //다시 통째로 집어넣어야함

            tx.commit();  //커밋해줌
        } catch (Exception e) {  //에러발생하면 롤백
            tx.rollback();
        } finally {
            em.clear();  //엔티티매니저 닫음
        }

        emf.close();  //엔티티 팩토리 닫음
    }
}
