package cz.markovd.jference.repository;

import cz.markovd.jference.domain.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);

//    /* ***************************
//        METODY Z JPA-REPO PRO BĚH BEZ DB
//       ***************************
//     */
//
//    User save(User uzivatel);
//
//    List<User> findAll();
//
//    List<User> findAll(Sort var1);
//
//    List<User> findAllById(Iterable<Integer> var1);
//
//    List<User> saveAll(Iterable<User> var1);
//
//    void flush();
//
//    User saveAndFlush(User var1);
//
//    void deleteInBatch(Iterable<User> var1);
//
//    void deleteAllInBatch();
//
//    User getOne(Integer var1);
//
//    List<User> findAll(Example<User> var1);
//
//    List<User> findAll(Example<User> var1, Sort var2);
}
