package com.god.economics.crawllers.instagram.login;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.utils.IGUtils;
import com.god.economics.crawllers.instagram.api.story.SerializableCookieJar;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;

import java.io.*;

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
        return IGClient.from(new FileInputStream(to),build);


    }


    public static IGClient loginwithusernamepass(String username, String password) throws IGLoginException {
        return IGClient.builder().username(username)
                .password(password)
                .login();

    }







}
