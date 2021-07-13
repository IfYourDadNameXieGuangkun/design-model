package com.aye.webflux.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class WebFluxBeanController {
    @Bean
    public RouterFunction<ServerResponse> hellWebFlux() {
        return RouterFunctions.route()
                .GET("/webflux/{name}", serverRequest -> {
                    String name = serverRequest.pathVariable("name");
                    return ServerResponse.ok().bodyValue(name);
                })
                .POST("/webflux/hello", serverRequest -> {
                    String name = serverRequest.exchange().getRequest().getQueryParams().getFirst("name");
                    assert name != null;
                    return ServerResponse.ok().bodyValue(name);
                })
                .build();
    }
}
