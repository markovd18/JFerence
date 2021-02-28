package cz.markovd.jference;

import cz.markovd.jference.controller.vo.EnumUzivatelPravo;
import cz.markovd.jference.controller.vo.UzivatelVO;
import cz.markovd.jference.domain.Uzivatel;
import org.springframework.stereotype.Component;

/**
 * Továrna pro tvorbu VO
 *
 * @author David Markov
 * @since 28.2.2021
 */
@Component
public class VOFactory {

    public UzivatelVO createUzivatelVO(final Uzivatel uzivatel) {
        if (uzivatel == null) {
            return null;
        }

        UzivatelVO uzivatelVO = new UzivatelVO();
        uzivatelVO.setJmeno(uzivatel.getJmeno());
        uzivatelVO.setLogin(uzivatel.getLogin());
        uzivatelVO.setEmail(uzivatelVO.getEmail());
        uzivatelVO.setPravo(EnumUzivatelPravo.getValueOf(uzivatel.getPravo()));

        return uzivatelVO;
    }
}
