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
            Movie movie = new Movie();
            movie.setDirector("a");
            movie.setActor("b");
            movie.setName("바람과 함께 사라지다");
            movie.setPrice(10000);

            em.persist(movie);

            em.flush();
            em.clear();

            Movie findMovie = em.find(Movie.class, movie.getId());

            tx.commit();  //커밋해줌
        } catch (Exception e) {  //에러발생하면 롤백
            tx.rollback();
        } finally {
            em.clear();  //엔티티매니저 닫음
        }

        emf.close();  //엔티티 팩토리 닫음
    }
}
