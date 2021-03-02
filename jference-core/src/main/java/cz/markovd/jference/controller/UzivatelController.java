package cz.markovd.jference.controller;

import cz.markovd.jference.InvalidStateException;
import cz.markovd.jference.controller.vo.UserLoginVO;
import cz.markovd.jference.controller.vo.UzivatelVO;
import cz.markovd.jference.domain.Uzivatel;
import cz.markovd.jference.service.UzivatelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller pro práci s požadavky na uživatele.
 *
 * @author David Markov
 * @since 28.2.2021
 */
@RestController
@RequestMapping("/api/uzivatele")
public class UzivatelController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

//    @Autowired
    private UzivatelService uzivatelService;

//    public UzivatelController(UzivatelService uzivatelService) {
//        this.uzivatelService = uzivatelService;
//    }

    @RequestMapping(value = "/registruj", method = RequestMethod.POST)
    public void registerUser(@RequestBody Uzivatel uzivatel) {
        logger.info("Registrace uživatele: {}", uzivatel.getLogin());
        try {
            uzivatelService.registrujUzivatele(uzivatel);
        } catch (InvalidStateException e) {
            logger.error("Chyba při pokusu o registraci uživatele {}", uzivatel, e);
        }
    }

    @RequestMapping(value = "/login")
    public UzivatelVO loginUser(@RequestBody UserLoginVO userLogin) {
        logger.info("Probíhá přihlášení uživatele {}", userLogin);
        return uzivatelService.prihlasUzivatele(userLogin);
    }

//    public void updateUser(RequestBody Uz uziv)

    /* *****************************
            AUTOWIRING
    ****************************
    */

    @Autowired
    public void setUzivatelService(UzivatelService uzivatelService) {
        this.uzivatelService = uzivatelService;
    }
}
