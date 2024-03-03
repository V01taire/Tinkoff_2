package edu.java.clients;

import org.springframework.web.reactive.function.client.WebClient;

public interface Client {

    WebClient getWebClient();
}
