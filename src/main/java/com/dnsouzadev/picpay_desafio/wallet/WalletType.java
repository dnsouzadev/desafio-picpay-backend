package com.dnsouzadev.picpay_desafio.wallet;

public enum WalletType {
    COMUM(1), LOJISTA(2);

    private final int value;

    private WalletType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
