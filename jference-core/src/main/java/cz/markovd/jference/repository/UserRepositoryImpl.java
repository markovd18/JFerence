package cz.markovd.jference.repository;

import cz.markovd.jference.domain.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * User repository for running without DB connection.
 */
//@Component
public class UserRepositoryImpl /*implements UserRepository*/ {

//    private final List<User> uzivatele = new ArrayList<>();
//
//    @Override
//    public User findByLogin(String login) {
//        return uzivatele.stream().filter(uzivatel -> uzivatel.getLogin().equals(login)).findFirst().orElse(null);
//    }
//
//    public List<User> findAll() {
//        return new ArrayList<>(uzivatele);
//    }
//
//    public List<User> findAll(Sort sort) {
//        return null;
//    }
//
//    public Page<User> findAll(Pageable pageable) {
//        return null;
//    }
//
//    public List<User> findAllById(Iterable<Integer> iterable) {
//        return null;
//    }
//
//    public long count() {
//        return uzivatele.size();
//    }
//
//    public void deleteById(Integer integer) {
//        uzivatele.removeIf(uzivatel -> uzivatel.getIdUser().equals(integer));
//    }
//
//    public void delete(User uzivatel) {
//        uzivatele.removeIf(uziv -> uziv.equals(uzivatel));
//    }
//
//    public void deleteAll(Iterable<? extends User> iterable) {
//        for (User uzivatel : iterable) {
//            uzivatele.removeIf(uziv -> uziv.equals(uzivatel));
//        }
//    }
//
//    public void deleteAll() {
//        uzivatele.clear();
//    }
//
//    public User save(User s) {
//        uzivatele.add(s);
//        return s;
//    }
//
//
//    public List<User> saveAll(Iterable<User> iterable) {
//        List<User> list = new ArrayList<>((Collection<? extends User>) iterable);
//        uzivatele.addAll(list);
//        return list;
//    }
//
//
//    public Optional<User> findById(Integer integer) {
//        return Optional.empty();
//    }
//
//
//    public boolean existsById(Integer integer) {
//        return false;
//    }
//
//
//    public void flush() {
//
//    }
//
//
//    public User saveAndFlush(User s) {
//        return null;
//    }
//
//
//    public void deleteInBatch(Iterable<User> iterable) {
//
//    }
//
//
//    public void deleteAllInBatch() {
//
//    }
//
//
//    public User getOne(Integer integer) {
//        return null;
//    }
//
//
//    public Optional<User> findOne(Example<User> example) {
//        return Optional.empty();
//    }
//
//
//    public List<User> findAll(Example<User> example) {
//        return null;
//    }
//
//
//    public List<User> findAll(Example<User> example, Sort sort) {
//        return null;
//    }
//
//
//    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
//        return null;
//    }
//
//
//    public <S extends User> long count(Example<S> example) {
//        return 0;
//    }
//
//
//    public <S extends User> boolean exists(Example<S> example) {
//        return false;
//    }
}
