package com.god.economics.instagram;

import com.god.economics.instagram.exceptions.ExceptionallyHandler;
import kotlin.Pair;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * created By gOD on 12/18/2020 9:34 PM
 */

@Data
public class IGClient {
    @Accessors(chain = true)
    private transient OkHttpClient httpClient;
    private ExceptionallyHandler exceptionallyHandler;


    public <T extends IGResponse> CompletableFuture<T> sendRequest(@NonNull IGRequest<T> req) {
        CompletableFuture<Pair<Response, String>> respFuture = new CompletableFuture<>();

        this.httpClient.newCall(req.formRequest(this)).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                respFuture.completeExceptionally(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    respFuture.complete(new Pair<>(response, responseBody.string()));
                }
            }
        });

        return respFuture.thenApply(req::parseResponse)
                .exceptionally(throwable -> {
                    return this.exceptionallyHandler.handle(throwable, req.getResponseType());
                })
        ;
    }
}
