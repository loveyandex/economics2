package com.god.economics.crawllers.instagram.login;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.actions.users.UserAction;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.utils.IGUtils;
import com.god.economics.crawllers.instagram.api.story.SerializableCookieJar;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import org.junit.Assert;

import java.io.*;
import java.util.concurrent.CompletableFuture;

import static com.god.economics.crawllers.instagram.api.story.run.saveigClient;

public class LoginWith {


    @SneakyThrows
    public static void serialize(Object o, File to) {
        FileOutputStream file = new FileOutputStream(to);
        ObjectOutputStream out = new ObjectOutputStream(file);

        out.writeObject(o);
        out.close();
        file.close();
    }

    @SneakyThrows
    public static <T> T deserialize(File file, Class<T> clazz) {
        InputStream in = new FileInputStream(file);
        ObjectInputStream oIn = new ObjectInputStream(in);

        T t = clazz.cast(oIn.readObject());

        in.close();
        oIn.close();

        return t;
    }

    public static IGClient igClient() throws Exception {
        File to = new File("igclient.ser"),
                cookFile = new File("cookie.ser");

        OkHttpClient build = IGUtils.defaultHttpClientBuilder().cookieJar(deserialize(cookFile, SerializableCookieJar.class)).build();
        return IGClient.from(new FileInputStream(to), build);


    }


    public static IGClient loginwithusernamepass(String username, String password) throws IOException, ClassNotFoundException {
        try {
            IGClient igClient = igClient();
            CompletableFuture<UserAction> instagram = igClient.actions().users().findByUsername("instagram");


        } catch (Exception e) {
        }
        File to = new File("igclient.ser"),
                cookFile = new File("cookie.ser");
        SerializableCookieJar jar = new SerializableCookieJar();
        IGClient lib = new IGClient.Builder()
                .username(username)
                .password(password)
                .client(formTestHttpClient(jar))
                .onLogin((cli, lr) -> Assert.assertEquals("ok", lr.getStatus()))
                .login();
        serialize(lib, to);
        serialize(jar, cookFile);
        IGClient saved = IGClient.from(new FileInputStream(to),
                formTestHttpClient(deserialize(cookFile, SerializableCookieJar.class)));
        return lib;

    }

    public static IGClient loginwithusernamepass(String username, String password, String userfilename) throws IOException, ClassNotFoundException {
        try {
            IGClient igClient = igClient();
            CompletableFuture<UserAction> instagram = igClient.actions().users().findByUsername("instagram");


        } catch (Exception e) {
        }
        File to = new File(String.format("igclient_%s.ser", userfilename)),
                cookFile = new File(String.format("cookie_%s.ser", userfilename));
        SerializableCookieJar jar = new SerializableCookieJar();
        IGClient lib = new IGClient.Builder()
                .username(username)
                .password(password)
                .client(formTestHttpClient(jar))
                .onLogin((cli, lr) -> Assert.assertEquals("ok", lr.getStatus()))
                .login();
        serialize(lib, to);
        serialize(jar, cookFile);
        IGClient saved = IGClient.from(new FileInputStream(to),
                formTestHttpClient(deserialize(cookFile, SerializableCookieJar.class)));
        return lib;

    }


    public static OkHttpClient formTestHttpClient(SerializableCookieJar jar) {
        return IGUtils.defaultHttpClientBuilder().cookieJar(jar).build();
    }


}
