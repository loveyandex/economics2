package com.god.economics.crawllers.instagram.api.story;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.google.gson.Gson;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

/**
 * created By gOD on 12/15/2020 5:16 AM
 */

public class run {

    public static void main(String[] args) {
        try {
            IGClient igClient = IGClient
                    .builder()
                    .username("galleyall")
                    .password("godisgreat")
                    .login();
            String url = "https://instagram.fmct3-2.fna.fbcdn.net/v/t51.2885-15/e35/p1080x1080/130703543_229234338811808_1572435485771580944_n.jpg?_nc_ht=instagram.fmct3-2.fna.fbcdn.net&_nc_cat=1&_nc_ohc=5I-OO9wciS4AX-xWEJU&tp=1&oh=4bdf7bf777271f7eecb28b0d23e2f007&oe=6001E16A";

            BufferedImage bufferedImageu = ImageIO.read(new URL(url));
            ImageIO.write(bufferedImageu, "jpg", new File("output.jpg"));
            System.out.println();


// get DataBufferBytes from Raster
            WritableRaster raster = bufferedImageu.getRaster();
            DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
            byte[] data1 = data.getData();

            CompletableFuture<String> completableFuture = igClient.actions()
                    .timeline()
                    .uploadPhoto(data1, "god is great ")
                    .thenApply(mediaConfigureTimelineResponse -> {
                        System.out.println(new Gson().toJson(mediaConfigureTimelineResponse));
                        return mediaConfigureTimelineResponse.toString();
                    })
                    .exceptionally(throwable -> {
                        System.err.println(throwable.getCause());
                        return null;
                    });
            completableFuture.join();








        } catch (IGLoginException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
