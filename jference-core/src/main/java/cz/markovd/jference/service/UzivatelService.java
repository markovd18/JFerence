package cz.markovd.jference.service;

import cz.markovd.jference.domain.Uzivatel;
import cz.markovd.jference.repository.UzivatelRepository;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UzivatelService {

    @Autowired
    private PasswordEncryptor passwordEncryptor;

    @Autowired
    private UzivatelRepository uzivatelRepository;

    public void registrujUzivatele(Uzivatel uzivatel) {
        if (uzivatel == null) {
            return;
        }
        String encryptedPassword = passwordEncryptor.encryptPassword(uzivatel.getHeslo());
        uzivatel.setHeslo(encryptedPassword);

        uzivatelRepository.save(uzivatel);
    }
}
