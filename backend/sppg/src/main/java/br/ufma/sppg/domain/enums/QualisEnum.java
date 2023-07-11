package br.ufma.sppg.domain.enums;

public enum QualisEnum {
    A1(0),
    A2(1),
    A3(2),
    A4(3),
    B1(4),
    B2(5),
    B3(6),
    B4(7);

    private int code;

    private QualisEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public QualisEnum valueOf(int code) {
        for (QualisEnum value : QualisEnum.values()) {
            if (value.getCode() == code)
                return value;
        }

        throw new IllegalArgumentException("Invalid OrderStatus code");
    }

}
