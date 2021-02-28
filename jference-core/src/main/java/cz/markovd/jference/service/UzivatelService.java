package cz.markovd.jference.service;

import cz.markovd.jference.InvalidStateException;
import cz.markovd.jference.VOFactory;
import cz.markovd.jference.controller.vo.UserLoginVO;
import cz.markovd.jference.controller.vo.UzivatelVO;
import cz.markovd.jference.domain.Uzivatel;
import cz.markovd.jference.repository.UzivatelRepository;
import org.jasypt.util.password.PasswordEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

/**
 * Serviska pro práci s uživateli.
 *
 * @author David Markov
 * @since 10.7. 2020
 */
@Service
public class UzivatelService {

    private static final Logger logger = LoggerFactory.getLogger(UzivatelService.class);

//    @Autowired
    private PasswordEncryptor passwordEncryptor;

//    @Autowired
    private UzivatelRepository uzivatelRepository;

//    @Autowired
    private VOFactory voFactory;

//    public UzivatelService(PasswordEncryptor passwordEncryptor, UzivatelRepository uzivatelRepository, VOFactory voFactory) {
//        this.passwordEncryptor = passwordEncryptor;
//        this.uzivatelRepository = uzivatelRepository;
//        this.voFactory = voFactory;
//    }

    /**
     * Registruje předaného uživatele.
     * @param uzivatel uživatel, kterého chceme zaregistrovat
     * @throws InvalidStateException pokud uživatel s předaným loginem již existuje
     */
    public void registrujUzivatele(Uzivatel uzivatel) throws InvalidStateException {
        if (uzivatel == null) {
            return;
        }

        if (uzivatelRepository.findByLogin(uzivatel.getLogin()) != null) {
            throw new InvalidStateException("Uživatel s loginem " + uzivatel.getLogin() + " již existuje!");
        }

        String encryptedPassword = passwordEncryptor.encryptPassword(uzivatel.getHeslo());
        uzivatel.setHeslo(encryptedPassword);

        uzivatelRepository.save(uzivatel);
    }

    /**
     * Provede přihlášení předaného uživatele.
     * @param userLogin uživatel s vyplněným loginem a heslem
     * @return objekt registrovaného uživatele
     */
    public UzivatelVO prihlasUzivatele(UserLoginVO userLogin) {
        if (userLogin == null) {
            return null;
        }

        Uzivatel registrovany = uzivatelRepository.findByLogin(userLogin.getLogin());
        if (registrovany.getHeslo().equals(passwordEncryptor.encryptPassword(userLogin.getHeslo()))) {
            return voFactory.createUzivatelVO(registrovany);
        }

        logger.error("Zadáno nesprávné heslo při přihlášení uživatele {}", userLogin);
        return null;
    }

    @Autowired
    public void setPasswordEncryptor(PasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    @Autowired
    public void setUzivatelRepository(UzivatelRepository uzivatelRepository) {
        this.uzivatelRepository = uzivatelRepository;
    }

    @Autowired
    public void setVoFactory(VOFactory voFactory) {
        this.voFactory = voFactory;
    }
}
