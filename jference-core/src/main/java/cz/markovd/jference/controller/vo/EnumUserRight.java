package cz.markovd.jference.controller.vo;

import cz.markovd.jference.domain.dos.UserDO;

public enum EnumUserRight {
    ADMIN(20),
    REVIEWER(10),
    AUTHOR(5);

    private final int weight;

    EnumUserRight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public static EnumUserRight getValueOf(final UserDO.EnumRightLevel enumRightLevel) {
        if (enumRightLevel == null) {
            return null;
        }

        return valueOf(enumRightLevel.name());
    }
}
