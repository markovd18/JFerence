package cz.markovd.jference.repository;

import cz.markovd.jference.domain.Uzivatel;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Repository pro Uživatele bez připojení k DB
 */
@Component
public class UzivatelRepositoryImpl implements UzivatelRepository {

    private final List<Uzivatel> uzivatele = new ArrayList<>();

    @Override
    public Uzivatel findByLogin(String login) {
        return uzivatele.stream().filter(uzivatel -> uzivatel.getLogin().equals(login)).findFirst().orElse(null);
    }

    public List<Uzivatel> findAll() {
        return new ArrayList<>(uzivatele);
    }

    public List<Uzivatel> findAll(Sort sort) {
        throw new NotImplementedException();
    }

    public Page<Uzivatel> findAll(Pageable pageable) {
        throw new NotImplementedException();
    }

    public List<Uzivatel> findAllById(Iterable<Integer> iterable) {
        throw new NotImplementedException();
    }

    public long count() {
        return uzivatele.size();
    }

    public void deleteById(Integer integer) {
        uzivatele.removeIf(uzivatel -> uzivatel.getIdUzivatel().equals(integer));
    }

    public void delete(Uzivatel uzivatel) {
        uzivatele.removeIf(uziv -> uziv.equals(uzivatel));
    }

    public void deleteAll(Iterable<? extends Uzivatel> iterable) {
        for (Uzivatel uzivatel : iterable) {
            uzivatele.removeIf(uziv -> uziv.equals(uzivatel));
        }
    }

    public void deleteAll() {
        uzivatele.clear();
    }

    public Uzivatel save(Uzivatel s) {
        uzivatele.add(s);
        return s;
    }


    public List<Uzivatel> saveAll(Iterable<Uzivatel> iterable) {
        List<Uzivatel> list = new ArrayList<>((Collection<? extends Uzivatel>) iterable);
        uzivatele.addAll(list);
        return list;
    }


    public Optional<Uzivatel> findById(Integer integer) {
        return Optional.empty();
    }


    public boolean existsById(Integer integer) {
        return false;
    }


    public void flush() {

    }


    public Uzivatel saveAndFlush(Uzivatel s) {
        return null;
    }


    public void deleteInBatch(Iterable<Uzivatel> iterable) {

    }


    public void deleteAllInBatch() {

    }


    public Uzivatel getOne(Integer integer) {
        return null;
    }


    public Optional<Uzivatel> findOne(Example<Uzivatel> example) {
        return Optional.empty();
    }


    public List<Uzivatel> findAll(Example<Uzivatel> example) {
        return null;
    }


    public List<Uzivatel> findAll(Example<Uzivatel> example, Sort sort) {
        return null;
    }


    public <S extends Uzivatel> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }


    public <S extends Uzivatel> long count(Example<S> example) {
        return 0;
    }


    public <S extends Uzivatel> boolean exists(Example<S> example) {
        return false;
    }
}
