package com.god.economics.crawllers.digikala.insta.IncridibleOffers;

import com.github.instagram4j.instagram4j.IGClient;
import com.god.economics.crawllers.instagram.login.LoginWith;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * @author Abolfazl
 */
public class IncridibleOffers {
    String takhfifelon = "syselondon";
    String godisgreat = "godisgreat";
    IGClient igClient;

    public IncridibleOffers() throws IOException, ClassNotFoundException {
     igClient= LoginWith.loginwithusernamepass(takhfifelon, godisgreat);

    }
    public static void main(String[] args) {

        try {
            new IncridibleOffers().sendBulkImages("d:/sadeghs");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public  void  sendBulkImages(String dirnaem) throws IOException, ExecutionException, ClassNotFoundException, InterruptedException {
        File dir = new File(dirnaem);
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isFile()){
                    sendImagePost(file);

                }
            }
        }


    }


    public   void sendImagePost(   File output) throws IOException, ClassNotFoundException, ExecutionException, InterruptedException {




        String caption0 = "نام کالا:" + "\n"   + "\n" +
                "قیمت اصلی: "

             ;
        String t = caption0;



        t = t + "#میناکاری"+ "\n";
        t = t + "#takhfif"+ "\n";
        t = t + "#"+"تخفیف"+ "\n";



        String caption = caption0 + "\n\n\n\n" + t;


        System.out.println(caption);
        igClient.getActions().timeline().uploadPhoto(output,
                caption).get();





    }


    
}
