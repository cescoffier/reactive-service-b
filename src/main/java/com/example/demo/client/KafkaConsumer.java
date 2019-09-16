package com.example.demo.client;

import com.example.demo.service.Database;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.eclipse.microprofile.reactive.streams.operators.ProcessorBuilder;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class KafkaConsumer {

    @Inject Database database;

    @Incoming("input")
    @Outgoing("output")
    public ProcessorBuilder<JsonObject, JsonObject> process() {
        return ReactiveStreams.<JsonObject>builder()
                .map(json -> {
                    String in = json.getString("input");
                    return toJson(json, in);
                });
    }

    private JsonObject toJson(JsonObject json, String result) {
        return new JsonObject().put("id", json.getString("id")).put("result", result);
    }

}
