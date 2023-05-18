package com.global.reactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static java.time.Duration.ofSeconds;

@RestController
@RequestMapping("/api/v1")
public class ReactiveController {

    @GetMapping(path = "/demo")
    public Mono<String> greeting() {
        return computeMessage()
                .zipWith(getNameFromDB())
                .map(t -> t.getT1() + t.getT2());
    }

    private Mono<String> computeMessage() {
        return Mono.just("Hello there this is reactive in use by ")
                .delayElement(ofSeconds(5));
    }

    private Mono<String> getNameFromDB() {
        return Mono.just("Samy Mohsen")
                .delayElement(ofSeconds(5));
    }
}
