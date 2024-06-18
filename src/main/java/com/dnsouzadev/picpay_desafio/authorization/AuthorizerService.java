package com.dnsouzadev.picpay_desafio.authorization;



import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.dnsouzadev.picpay_desafio.transaction.Transaction;

@Service
public class AuthorizerService {
    private RestClient restClient;

    public AuthorizerService(RestClient.Builder builder) {
        this.restClient = builder
        .baseUrl("https://util.devi.tools/api/v2/authorize")
        .build();
    }


    public void authorize(Transaction transaction) {
        var response = restClient.get()
            .retrieve()
            .toEntity(Authorization.class);

            if(response.getStatusCode().isError() || !response.getBody().isAuthorized()) {
                throw new UnauthorizedTransactionException("Transaction not authorized");
            }
    }
}
