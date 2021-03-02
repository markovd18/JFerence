package cz.markovd.jference.repository;

import cz.markovd.jference.domain.Uzivatel;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface UzivatelRepository /*extends JpaRepository<Uzivatel, Integer>*/ {
    Uzivatel findByLogin(String login);

    /* ***************************
        METODY Z JPA-REPO PRO BĚH BEZ DB
       ***************************
     */

    Uzivatel save(Uzivatel uzivatel);

    List<Uzivatel> findAll();

    List<Uzivatel> findAll(Sort var1);

    List<Uzivatel> findAllById(Iterable<Integer> var1);

    List<Uzivatel> saveAll(Iterable<Uzivatel> var1);

    void flush();

    Uzivatel saveAndFlush(Uzivatel var1);

    void deleteInBatch(Iterable<Uzivatel> var1);

    void deleteAllInBatch();

    Uzivatel getOne(Integer var1);

    List<Uzivatel> findAll(Example<Uzivatel> var1);

    List<Uzivatel> findAll(Example<Uzivatel> var1, Sort var2);
}
