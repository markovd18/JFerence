package cz.markovd.jference.repository;

import cz.markovd.jference.domain.Uzivatel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UzivatelRepository extends JpaRepository<Uzivatel, Integer> {
    Uzivatel findByLogin(String login);
}
