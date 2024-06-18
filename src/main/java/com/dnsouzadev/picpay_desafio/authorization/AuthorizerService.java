package com.dnsouzadev.picpay_desafio.authorization;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.dnsouzadev.picpay_desafio.transaction.Transaction;

@Service
public class AuthorizerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizerService.class);
    private RestClient restClient;

    public AuthorizerService(RestClient.Builder builder) {
        this.restClient = builder
        .baseUrl("https://util.devi.tools/api/v2/authorize")
        .build();
    }


    public void authorize(Transaction transaction) {
        LOGGER.info("Authorizing transaction {}", transaction);
        var response = restClient.get()
            .retrieve()
            .toEntity(Authorization.class);

            if(response.getStatusCode() == HttpStatus.FORBIDDEN)
                throw new UnauthorizedTransactionException("Transaction not authorized");

            LOGGER.info("Transaction authorized: {}", transaction);


    }
}
