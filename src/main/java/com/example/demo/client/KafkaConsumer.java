package com.example.demo.client;

import com.example.demo.service.Database;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class KafkaConsumer {

    @Inject Database database;

    @Incoming("input")
    @Outgoing("output")
    public CompletionStage<JsonObject> process(JsonObject json) {
        return database.transmogrify(json.getString("input"))
                .thenApply(result -> toJson(json, result));
    }

    private JsonObject toJson(JsonObject json, String result) {
        return new JsonObject().put("id", json.getString("id")).put("result", result);
    }

}
