package com.god.economics.crawllers.instagram.api.story;

import com.abfzl.J;
import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.actions.feed.FeedIterable;
import com.github.instagram4j.instagram4j.actions.timeline.TimelineAction;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.models.user.Profile;
import com.github.instagram4j.instagram4j.requests.friendships.FriendshipsFeedsRequest;
import com.github.instagram4j.instagram4j.requests.media.MediaConfigureSidecarRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.responses.feed.FeedUsersResponse;
import com.github.instagram4j.instagram4j.responses.users.UsersSearchResponse;
import com.github.instagram4j.instagram4j.utils.IGUtils;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.String;
import java.util.concurrent.CompletableFuture;

/**
 * created By gOD on 12/15/2020 5:16 AM
 */

@Slf4j
public class run {

    public static OkHttpClient formTestHttpClient() {
        return IGUtils.defaultHttpClientBuilder()
                .build();
    }

    public static OkHttpClient formTestHttpClient(SerializableCookieJar jar) {
        return IGUtils.defaultHttpClientBuilder().cookieJar(jar).build();
    }


    @Test
    // Run SerializeTestUtil.serializeLogin first to generate saved sessions
    public void testAlbum() throws Exception {
        File to = new File("igclient.ser"),
                cookFile = new File("cookie.ser");
        SerializableCookieJar jar = new SerializableCookieJar();
        IGClient lib = new IGClient.Builder()
                .username("gallerygall")
                .password("godisgreat")
                .client(formTestHttpClient(jar))
                .onLogin((cli, lr) -> Assert.assertEquals("ok", lr.getStatus()))
                .login();
        log.debug("Serializing. . .");
        serialize(lib, to);
        serialize(jar, cookFile);
        log.debug("Deserializing. . .");
        IGClient saved = IGClient.from(new FileInputStream(to),
                formTestHttpClient(deserialize(cookFile, SerializableCookieJar.class)));
        log.debug(lib.toString());
    }


    public static IGClient igClient() throws Exception {
        File to = new File("igclient.ser"),
                cookFile = new File("cookie.ser");
        SerializableCookieJar jar = new SerializableCookieJar();

        return IGClient.from(new FileInputStream(to),
                formTestHttpClient(deserialize(cookFile, SerializableCookieJar.class)));


    }


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

    public static void main(String[] args) {
        try {
//            IGClient igClient = IGClient
//                    .builder()
//                    .username("galleyall")
//                    .password("godisgreat")
//                    .login();


            IGClient igClient = igClient();
//            String gallery_ordibeheshtt = igClient.actions().users().findByUsername("gallery_ordibeheshtt")
//                    .thenApply(userAction -> {
//
//                        FeedIterable<FriendshipsFeedsRequest, FeedUsersResponse>
//                                feedUsersResponses = userAction.followersFeed();
//                        feedUsersResponses.stream().forEach(feedUsersResponse -> {
//                            feedUsersResponse.getUsers().forEach(profile -> {
//                                System.out.println(profile.getUsername());
//                            });
//
//                        });
//
//                        return "";
//                    }).join();


            igClient.actions().users().search("مزون").thenApply(
                    usersSearchResponse -> {
                        List<UsersSearchResponse.User> users = usersSearchResponse.getUsers();
                        users.forEach(user -> {
                        });


                        System.out.println(new Gson().toJson(usersSearchResponse));
                        return null;
                    }
            ).join();


            String url = "https://instagram.fmct3-2.fna.fbcdn.net/v/t51.2885-15/e35/p1080x1080/130703543_229234338811808_1572435485771580944_n.jpg?_nc_ht=instagram.fmct3-2.fna.fbcdn.net&_nc_cat=1&_nc_ohc=5I-OO9wciS4AX-xWEJU&tp=1&oh=4bdf7bf777271f7eecb28b0d23e2f007&oe=6001E16A";

            BufferedImage bufferedImageu = ImageIO.read(new URL(url));
            File output = new File("output.jpg");
            ImageIO.write(bufferedImageu, "jpg", output);
            System.err.println("uploading ");



            List<TimelineAction.SidecarInfo> infos = new ArrayList<>();
            infos.add(TimelineAction.SidecarPhoto.from(output));
//            infos.add(TimelineAction.SidecarPhoto.from(output));
//            infos.add(TimelineAction.SidecarPhoto.from(output));


            CompletableFuture<String> completableFuture = igClient.actions()
                    .timeline()
                    .uploadAlbum(infos, "king in the north")
//                    .uploadPhoto(output, "god is great ")
                    .thenApply(mediaConfigureTimelineResponse -> {
                        System.out.println(new Gson().toJson(mediaConfigureTimelineResponse));
                        return mediaConfigureTimelineResponse.toString();
                    })
                    .exceptionally(throwable -> {
                        throwable.printStackTrace();
                        return null;
                    });
            completableFuture.join();


        } catch (IGLoginException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
