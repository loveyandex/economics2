package com.god.economics.crawllers.digikala;

import com.github.instagram4j.instagram4j.IGClient;
import com.god.economics.crawllers.instagram.login.LoginWith;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Abolfazl
 */
public class Tik {

    public static void main(String[] args) throws Exception {




//        IGClient igClient = incredibleOffersStory.igClient();
        String takhfifelon = "takhfifelon";
        String godisgreat = "godisgreat";

//        IGClient igClient = LoginWith.loginwithusernamepass(takhfifelon, godisgreat);

        String file_url = "https://v16-web.tiktok.com/video/tos/useast2a/tos-useast2a-ve-0068c002/f9a33b3129a54f6688d84dbe662acc04/?a=1988&br=2466&bt=1233&cd=0%7C0%7C1&ch=0&cr=0&cs=0&cv=1&dr=0&ds=3&er=&expire=1638067357&ft=wUyFfFF3kag3-I&l=202111272042260102230841630E71C65B&lr=tiktok_m&mime_type=video_mp4&net=0&pl=0&policy=3&qs=0&rc=amR4aTM6ZjtqODMzNzczM0ApOjxnN2Y7aTw2NzRnZGVlZmdmMWtucjRfa2NgLS1kMTZzczYzMC1iLzE0NjMyNC8xMi06Yw%3D%3D&signature=a92600a748c75b67767e51156753739d&tk=7031829931550540805&vl=&vr=";

        InputStream read = read(file_url);


        down(file_url
        ,"D:\\apps\\economics\\src\\main\\java\\com\\god\\economics\\crawllers\\digikala\\kos.mp4");



//





    }
    private static InputStream read( String FILE_URL) {
        try {
            HttpURLConnection httpcon = (HttpURLConnection)  new URL(FILE_URL).openConnection();
            httpcon.addRequestProperty("User-Agent", "Mozilla/4.0");

            return httpcon.getInputStream();
        } catch (IOException e) {
            String error = e.toString();
            throw new RuntimeException(e);
        }
    }




    public static void down(String FILE_URL, String FILE_NAME) throws IOException {
        InputStream in1 = new URL(FILE_URL).openStream();

        try (BufferedInputStream in = new BufferedInputStream(in1);
             FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME)) {
            byte dataBuffer[] = new byte[512];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
