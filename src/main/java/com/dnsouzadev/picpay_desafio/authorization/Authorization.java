package com.dnsouzadev.picpay_desafio.authorization;

public record Authorization(
    String status
) {
    public boolean isAuthorized() {
        return status.equals("success");
    }
}
