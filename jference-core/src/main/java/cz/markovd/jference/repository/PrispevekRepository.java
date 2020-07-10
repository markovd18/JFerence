package cz.markovd.jference.repository;

import cz.markovd.jference.domain.Prispevek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrispevekRepository extends JpaRepository<Prispevek, Integer> {
}
