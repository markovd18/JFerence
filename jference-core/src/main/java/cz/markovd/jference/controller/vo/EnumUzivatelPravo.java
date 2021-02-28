package cz.markovd.jference.controller.vo;

import cz.markovd.jference.domain.dos.UzivatelDO;

public enum EnumUzivatelPravo {
    ADMIN(20),
    RECENZENT(10),
    AUTOR(5);

    private final int vaha;

    EnumUzivatelPravo(int vaha) {
        this.vaha = vaha;
    }

    public int getVaha() {
        return vaha;
    }

    public static EnumUzivatelPravo getValueOf(final UzivatelDO.EnumPravo enumPravo) {
        if (enumPravo == null) {
            return null;
        }

        return valueOf(enumPravo.name());
    }
}
