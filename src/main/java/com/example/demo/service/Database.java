package com.example.demo.service;

import io.vertx.axle.core.Vertx;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class Database {

    @Inject Vertx vertx;

    private boolean fail;
    private boolean slow;

    private final Random  random = new Random();

    public CompletionStage<String> transmogrify(String input) {
        if (misbehave()) {
            throw new IllegalStateException("boom!");
        }

        CompletableFuture<String> future = new CompletableFuture<>();
        if (isSlow()) {
            vertx.setTimer(delay(), x -> {
                future.complete(compute(input));
            });
        } else {
            future.complete(compute(input));
        }

        return future;
    }

    private boolean misbehave() {
        return fail && random.nextBoolean();
    }

    public boolean isFail() {
        return fail;
    }

    public boolean isSlow() {
        return slow;
    }

    public boolean toggleFail() {
        this.fail = ! this.fail;
        return this.fail;
    }

    private int delay() {
        return 1000 + random.nextInt(3000);
    }

    private String compute(String input) {
        return new StringBuilder(input.toUpperCase()).reverse().toString();
    }

    public boolean toggleSlow() {
        this.slow = ! this.slow;
        return this.slow;
    }
}
