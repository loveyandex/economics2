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
            checkPhoneNumberWhatsappTel(bio, externalUrl);
            System.out.println("------------------------------------------------------------------------");


        }


    }

    private String checkPhoneNumberWhatsappTel(String bio, String externalUrl) {
        String leabasMaj = "لباس مجلسي،لباس مجلسى،لباس مجلسی،لباس";
        String manto = "مانتو،manto،Manto";

        String kif = "کیف زنانه،كيف و كفش ،کیف و کفش ،کيف و کفش ،کیف ،کيف ";
        String kiflebas = "لباس و كيف و كفش";
        String kafsh = "کفش ،";
        String roosari = "روسری،روسری،روسري،روسری";
        String roosari1 = "شال و روسري";
        String accesssory = "اکسسوری،اکسسوری،اکسسوري";
        String shal = "شال";

        String kif2 = "کيف،";
        String kif3 = "کیف،";
        String shabtork = "لباس شب ترک،";
        String designllebas = "طراح لباس";
        String lebas = "لباس";
        String mezon = "مزون";
        String designAndDookht = "طراحی و دوخت";
        String designAndDookht2 = "طراحي و دوخت";
        String designAndDookht23 = "Design and sewing clothes";
        String designAndDookht23e = "Design and sewing";
        String design2 = "طراحي";
        String designAndDookht3 = "طراحى و دوخت";
        String designAndDookht33 = "دوخت و طراحی";


        String tanpoosh = "تن پوش";
        String tanpooshha = "تن پوش های زنانه";
        String srockmardane = "استوک مردانه";
        String tshirt = "تیشرت";
        String womanwear = "woman wear";
        String dookht = "دوخت";
        String lebastork = "لباس_ترک";
        String pooshakzanane = "پوشاک زنانه";
        String einak = "عینک";
        String einak1 = "عىنک";
        String einak2 = "عينک";
        String einak32 = "عينك";
        String FashionDesigner = "Fashion Designer";
        String fashionstyle = "fashion style";
        String fashion = "fashion";
        String Lifestyle = "Lifestyle";
        String Beauty = "Beauty";

        String scarf = "scarf";
        String shawl = "scarf";
        String manteau = "manteau";


        String POOSHAKROOZDONYA = "پوشاك روزدنيا";
        String POOSHAKROOZDONYA2 = "پوشاك";
        String POOSHAKROOZDONYA22 = "پوشاک";
        String mezonaroos = "مزون عروس";

        String KHABLEBAS = "لباسخواب";
        String KHABLEBAS2 = "لباس خواب";
        String BOOOTIK = "بوتیک";
        String PAZSOOKHT = "پذیرش سفارش دوخت";
        String TORKIYE = "ترکیه";
        String shiktarinmodels = "شیک\u200Cترین مدل\u200Cها";
        String jinpooshak = "پوشاک جین";
        String lebasshab = "لباس_شب";
        String ajnas = "اجناس";
        String zananelebas = "لباس هاي زنانه";
        String zananelebas2 = " لباس هاي زنانه";
        String tarahlebas = "طراح\u200Cلباس";
        String tarahlebas2 = "طراح باس";
        String Butic = "Butic";
        String mantodookhtshoomiz = "دوخت مانتو وشومیز";

        //country and selign base none have a reasonal name
        String istanbul = "استانبول";
        String eourpandturkey = "برندهای اروپا و ترکیه";
        String directsellingturkey = "خريد مستقيم از تركيه";
        String FOROSH = "فروش تنها به صورت آنلاین";
        String BRANDROK = "برندهای ترک";
        String TORK = "ترک";
        String sefareshonline = "سفارش انلاین";
        String TORKd = "shop from turkey";
        String kharid = "خرید به صورت تک وعمده";
        String Orderonline = "Order online";
        String online= "فروشگاه هاى معتبر دبى";
        String gallery= "gallery";

        String[] types = {leabasMaj, kiflebas, manto, kif, kafsh, roosari, accesssory, shal};

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
            else if (bio.contains(designAndDookht2)) solution += designAndDookht + "";
            else if (bio.contains(designAndDookht3)) solution += designAndDookht + "";
            else if (bio.contains(lebas)) solution += lebas + "";
            else if (bio.contains(tanpoosh)) solution += tanpoosh + "";
            else if (bio.contains(pooshakzanane)) solution += pooshakzanane + "";
            else if (bio.contains(lebastork)) solution += lebastork + "";
            else if (bio.contains(tanpooshha)) solution += tanpooshha + "";
            else if (bio.contains(srockmardane)) solution += srockmardane + "";
            else if (bio.contains(womanwear)) solution += womanwear + "";
            else if (bio.contains(dookht)) solution += dookht + "";
            else if (bio.contains(mezon)) solution += mezon + "";


        }
        System.out.println("solution: "+solution);
        return solution;//لباس_ترک , عینکwooooof1551 رید کالا از ترکیه پوشاک زنانه


    }
}
