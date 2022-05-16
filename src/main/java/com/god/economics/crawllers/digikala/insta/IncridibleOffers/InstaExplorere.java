package com.god.economics.crawllers.digikala.insta.IncridibleOffers;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.actions.media.MediaAction;
import com.github.instagram4j.instagram4j.models.discover.SectionalItem;
import com.github.instagram4j.instagram4j.models.discover.SectionalMediaGridItem;
import com.github.instagram4j.instagram4j.models.media.timeline.TimelineMedia;
import com.github.instagram4j.instagram4j.requests.discover.DiscoverTopicalExploreRequest;
import com.github.instagram4j.instagram4j.responses.IGResponse;
import com.github.instagram4j.instagram4j.responses.discover.DiscoverTopicalExploreResponse;
import com.god.economics.crawllers.instagram.login.LoginWith;
import com.god.economics.joobin.functional.Func;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Abolfazl
 */
public class InstaExplorere {
    String takhfifelon = "atreandarzgoo";
    String godisgreat = "godisgreat18";
    IGClient igClient;

    public InstaExplorere() throws IOException, ClassNotFoundException {
        igClient = LoginWith.loginwithusernamepass(takhfifelon, godisgreat);

        CompletableFuture<DiscoverTopicalExploreResponse> discoverTopicalExploreResponseCompletableFuture = igClient.sendRequest(new DiscoverTopicalExploreRequest(null, "explore_all:0", false));


        try {
            DiscoverTopicalExploreResponse discoverTopicalExploreResponse = discoverTopicalExploreResponseCompletableFuture
                    .exceptionally(new Func<Throwable, DiscoverTopicalExploreResponse>() {
                        @Override
                        public DiscoverTopicalExploreResponse apply(Throwable throwable) {
                            return null;
                        }
                    })
                    .get();

            List<SectionalItem> sectional_items = discoverTopicalExploreResponse.getSectional_items();
            sectional_items.forEach(sectionalItem -> {
                System.out.println(sectionalItem.getLayout_type());
                System.out.println(sectionalItem.getFeed_type());
                if (sectionalItem.getLayout_type().equals("media_grid")){

                    List<TimelineMedia> medias = ((SectionalMediaGridItem) sectionalItem).getMedias();
                    for (TimelineMedia media : medias) {
                        System.out.println("https://www.instagram.com/p/"+media .getCode());
                        System.out.println( media .getCaption());
                        System.out.println( media .getMedia_type());
                        System.out.println( media .getLike_count());


//                        comment(media);
//                        waitsome();


                    }
                }




            });

            while (discoverTopicalExploreResponse.isMore_available()) {
                discoverTopicalExploreResponseCompletableFuture = igClient.sendRequest(
                        new DiscoverTopicalExploreRequest(discoverTopicalExploreResponse.getNext_max_id(), "explore_all:0", false));

                discoverTopicalExploreResponse = discoverTopicalExploreResponseCompletableFuture
                        .exceptionally((Func<Throwable, DiscoverTopicalExploreResponse>) throwable -> null)
                        .get();

            sectional_items = discoverTopicalExploreResponse.getSectional_items();
                sectional_items.forEach(sectionalItem -> {
                    System.out.println(sectionalItem.getLayout_type());
                    System.out.println(sectionalItem.getFeed_type());
                    if (sectionalItem.getLayout_type().equals("media_grid")){

                        List<TimelineMedia> medias = ((SectionalMediaGridItem) sectionalItem).getMedias();
                        for (TimelineMedia media : medias) {
                            System.out.println("https://www.instagram.com/p/"+media .getCode());

//                            comment(media);
                            waitsome();
                            System.out.println();


                        }
                    }


                });
            }


            System.out.println();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


    }

    private void waitsome() {
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void comment(TimelineMedia media) {

        MediaAction mediaAction = new MediaAction(igClient, "" + media.getPk());
        CompletableFuture<IGResponse> comment = mediaAction.comment("عطری که دوست دارین رو به ما بگین توو دایرکت");

        try {
            IGResponse igResponse = comment.get();
            System.out.println();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {

        try {
            new InstaExplorere();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void sendBulkImages(String dirnaem) throws IOException, ExecutionException, ClassNotFoundException, InterruptedException {
        File dir = new File(dirnaem);
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    sendImagePost(file);

                }
            }
        }


    }


    public void sendImagePost(File output) throws IOException, ClassNotFoundException, ExecutionException, InterruptedException {


        String caption0 = "نام کالا:" + "\n" + "\n" +
                "قیمت اصلی: ";
        String t = caption0;


        t = t + "#میناکاری" + "\n";
        t = t + "#takhfif" + "\n";
        t = t + "#" + "تخفیف" + "\n";


        String caption = caption0 + "\n\n\n\n" + t;


        System.out.println(caption);
        igClient.getActions().timeline().uploadPhoto(output,
                caption).get();


    }


}
