package com.god.economics.crawllers.instagram.comment;

import com.god.economics.PinstaUserRepo;
import com.god.economics.crawllers.Reqs;
import com.god.economics.crawllers.instagram.comment.models.PinstaUser;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * created By gOD on 1/26/2020 8:59 AM
 */
@RestController
public class USernameProccess {

    @Autowired
    private PinstaUserRepo pinstaUserRepo;

    @GetMapping("/bio2")
    public void main() throws IOException {

        Scanner scanner = new Scanner(new File("usernames.txt"));
        int ap = 0;
        while (scanner.hasNext()) {

            String resp = null;
            try {
                String username = scanner.nextLine();
                String url = "https://www.instagram.com/" + username + "/?__a=1";
                resp = Reqs.getReq(url);

                JSONObject jsonObject = new JSONObject(resp);

                Object graphql = jsonObject.get("graphql");


                Object bio0 = (((JSONObject) (((JSONObject) graphql).get("user")))
                        .get("biography"));


                String bio = bio0.toString();

                Object o = ((JSONObject) (((JSONObject) graphql).get("user"))).get("external_url");
                String externalurl = o.toString();

                String full_name = (String) ((JSONObject) (((JSONObject) graphql).get("user")))
                        .get("full_name");

                int edge_followed_by = ((int) ((JSONObject) ((JSONObject) (((JSONObject) graphql).get("user")))
                        .get("edge_followed_by")).get("count"));

                int edge_follow = ((int) ((JSONObject) ((JSONObject) (((JSONObject) graphql).get("user")))
                        .get("edge_follow")).get("count"));

                PinstaUser pinstaUser = new PinstaUser(username, username, bio, externalurl, full_name, edge_followed_by, edge_follow);
                pinstaUserRepo.save(pinstaUser);
                System.out.println(new Gson().toJson(pinstaUser));
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(resp);
                e.printStackTrace();
            }

        }


    }

    @GetMapping("/ana")
    public void madin() throws IOException {
        List<PinstaUser> pinstaUserAll = pinstaUserRepo.findAll();

        for (PinstaUser pinstaUser : pinstaUserAll) {
            String bio = pinstaUser.getBio();
            String externalUrl = pinstaUser.getExternalUrl();
            System.out.println(bio);
            System.out.println(pinstaUser.getUsername());
            System.out.println("------------------------------------------------------------------------");
            checkPhoneNumberWhatsappTel(bio, externalUrl);


        }


    }

    private String checkPhoneNumberWhatsappTel(String bio, String externalUrl) {
        String leabasMaj = "لباس مجلسي،لباس مجلسى،لباس مجلسی";
        String manto = "مانتو،manto،Manto";

        String kif = "كيف و كفش ،کیف و کفش ،کيف و کفش ،کیف ،کيف ";
        String kafsh = "کفش ،";
        String roosari = "روسری،روسری،روسري،روسری";
        String accesssory = "اکسسوری،اکسسوری،اکسسوري";
        String shal = "شال";

        String kif2 = "کيف،";
        String kif3 = "کیف،";
        String lebas = "لباس";
        String mezon = "مزون";
        String designAndDookht = "طراحی و دوخت";
        String tanpoosh = "تن پوش";
        String tanpooshha = "تن پوش های زنانه";
        String srockmardane="استوک مردانه";
        String tshirt="تیشرت";
        String womanwear="woman wear";
        String dookht="دوخت";


        String[] types = {leabasMaj, manto, kif, kafsh, roosari, accesssory, shal};

        String solution = "";
        for (String type : types) {
            for (String subtype : type.split("،")) {
                boolean contains = bio.contains(subtype);
                if (contains) {
                    solution += subtype + " ,";
                    break;
                }

            }
        }

        boolean contains = bio.contains(kif2);
        if (contains) {
            solution += kif2 + " ,";
        }
        contains = bio.contains(kif3);
        if (contains) {
            solution += kif2 + " ,";
        }
        if (solution.equals("")) {
            if (bio.contains(designAndDookht)) solution += designAndDookht + "";
            else if (bio.contains(lebas)) solution += lebas + "";
            else if (bio.contains(tanpoosh)) solution += tanpoosh + "";
            else if (bio.contains(tanpooshha)) solution += tanpooshha + "";
            else if (bio.contains(srockmardane)) solution += srockmardane + "";
            else if (bio.contains(womanwear)) solution += womanwear + "";
            else if (bio.contains(dookht)) solution += dookht + "";
            else if (bio.contains(mezon)) solution += mezon + "";


        }

        return solution;//لباس_ترک , عینکwooooof1551 رید کالا از ترکیه پوشاک زنانه


    }
}
