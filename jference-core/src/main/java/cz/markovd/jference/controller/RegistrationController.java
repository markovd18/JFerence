package cz.markovd.jference.controller;

import cz.markovd.jference.domain.Uzivatel;
import cz.markovd.jference.service.UzivatelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registrace")
public class RegistrationController {

    private Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UzivatelService uzivatelService;

    @RequestMapping(value = "/registruj", method = RequestMethod.POST)
    public void registerUser(@RequestBody Uzivatel uzivatel) {
        logger.info("Registrace uživatele: " + uzivatel.toString());
        uzivatelService.registrujUzivatele(uzivatel);
    }


}
