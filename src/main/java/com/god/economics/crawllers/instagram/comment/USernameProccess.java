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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
//            System.out.println(pinstaUser.getUsername());
//            String checkPhoneNumberWhatsappTel = type(pinstaUser.getFullName(), bio);
            phoneNumbers(bio, externalUrl);
            telID(bio, externalUrl);
            System.out.println("------------------------------------------------------------------------");


        }


    }

    private String type(String fullname, String bio) {
        String leabasMaj = "لباس مجلسي،لباس مجلسى،لباس مجلسی،لباس";
        String manto = "مانتو،manto،Manto";

        String kif = "کیف زنانه،كيف و كفش ،کیف و کفش ،کيف و کفش ،کیف ،کيف ";
        String kiflebas = "لباس و كيف و كفش";
        String kafsh = "کفش ،";
        String roosari = "روسری،روسری،روسري،روسری";
        String roosari1 = "شال و روسري";
        String accesssory = "اکسسوری،اکسسوری،اکسسوري";
        String shal = "شال";
        String WomenWear = "Women's Wear";


        String kif2 = "کيف،";
        String kif3 = "کیف،";
        String lebashamaj = "لباسهای مجلسی";
        String lebashamaj2 = "لباس هاي شب و مجلسي";
        String ww = "لباس های عروس";
        String aroslebas = "لباس عروس";
        String shablebas = "لباس شب";
        String shabtork = "لباس شب ترک،";
        String designllebas = "طراح لباس";
        String designlleba2s = "طراحی_دوخت_لباس_عروس";
        String designAndDookht = "طراحی و دوخت";
        String lebas = "لباس";
        String mezon = "مزون";
        String mexon = "mezon";
        String designAndDookht2 = "طراحي و دوخت";
        String designAndDookht23 = "Design and sewing clothes";
        String designAndDookht23e = "Design and sewing";
        String design2 = "طراحي";
        String design52 = "طراح";
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
        String shawlandscarf = "shawl and scarf";

        String scarf = "scarf";
        String shawl = "scarf";
        String manteau = "manteau";
        String POOSHAKROOZDONYA = "پوشاك روزدنيا";
        String POOSHAKROOZDONYA2 = "پوشاك";
        String POOSHAKROOZDONYA22 = "پوشاک";
        String mezonaroos = "مزون عروس";
        String KHABLEBAS = "لباسخواب";
        String KHABLEBAS2 = "لباسخواب زنان";
        String KHABLEBAS22 = "لباس_مجلسی";
        String KHABLEBAS322 = "لباس خواب";
        String KHABLEBAS222 = "طراحی و دوخت لباس عروس";
        String KHABLEBASkk = "پوشاك زنانه";
        String BOOOTIK = "بوتیک";
        String BOOOTI2K = "مد و لباس";
        String PAZSOOKHT = "پذیرش سفارش دوخت";
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
        String pooshaak = "پوشاک";
        String style = "استایل";
        String pooshaak12 = "تولیدی";

        //country and selign base none have a reasonal name
        String istanbul = "استانبول";
        String TORKIYE = "ترکیه";
        String TORKIYE2 = "تركيه";
        String eourpandturkey = "برندهای اروپا و ترکیه";
        String directsellingturkey = "خريد مستقيم از تركيه";
        String FOROSH = "فروش تنها به صورت آنلاین";
        String BRANDROK = "برندهای ترک";
        String mostaghimazturkey = "خرید مستقیم از ترکیه";
        String TORK = "ترک";
        String sefareshonline = "سفارش انلاین";
        String TORKd = "shop from turkey";
        String kharid = "خرید به صورت تک وعمده";
        String Orderonline = "Order online";
        String online = "فروشگاه هاى معتبر دبى";
        String gallery = "gallery";
        String boutique = "boutique";
        String Onlineshopping = "Online shopping";
        String Onlineshop = "Online shop";
        String shipping = "shipping";
        String eveningdresses = "evening dresses";
        String wqwq = "لباسمجلسی_شیک";
        String HANDMADE = "HAND MADE";
        String mahsoolat = "وارد کننده محصولات";
        String hosoori = "خريد حضوري";
        String shikforoshgah = "فروشگاه آنلاین پارچه های شیک مجلسی";
        String sabtsefaresh = "ثبت سفارشات";
        String nini = "نـي نـي";
        String nini2 = "نی نـی";

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
            else if (bio.contains(designlleba2s)) solution += designlleba2s + "";
            else if (bio.contains(designllebas)) solution += designllebas + "";
            else if (bio.contains(WomenWear)) solution += WomenWear + "";
            else if (bio.contains(shal)) solution += shal + "";
            else if (bio.contains(kif3)) solution += kif3 + "";
            else if (bio.contains(lebashamaj)) solution += lebashamaj + "";
            else if (bio.contains(lebashamaj2)) solution += lebashamaj2 + "";
            else if (bio.contains(ww)) solution += ww + "";
            else if (bio.contains(roosari1)) solution += roosari1 + "";
            else if (bio.contains(dookht)) solution += dookht + "";
            else if (bio.contains(aroslebas)) solution += aroslebas + "";
            else if (bio.contains(shablebas)) solution += shablebas + "";
            else if (bio.contains(shabtork)) solution += shabtork + "";
            else if (bio.toLowerCase().contains(designAndDookht23.toLowerCase())) solution += designAndDookht23 + "";
            else if (bio.toLowerCase().contains(designAndDookht23e.toLowerCase())) solution += designAndDookht23e + "";
            else if (bio.contains(design2)) solution += design2 + "";
            else if (bio.contains(lebastork)) solution += lebastork + "";
            else if (bio.contains(designAndDookht33)) solution += designAndDookht33 + "";
            else if (bio.contains(design52)) solution += design52 + "";
            else if (bio.contains(tanpoosh)) solution += tanpoosh + "";
            else if (bio.contains(tanpooshha)) solution += tanpooshha + "";
            else if (bio.contains(srockmardane)) solution += srockmardane + "";
            else if (bio.contains(tshirt)) solution += tshirt + "";
            else if (bio.contains(einak)) solution += einak + "";
            else if (bio.contains(einak1)) solution += einak + "";
            else if (bio.contains(einak2)) solution += einak + "";
            else if (bio.contains(einak32)) solution += einak + "";
            else if (bio.toLowerCase().contains(FashionDesigner.toLowerCase())) solution += FashionDesigner + "";
            else if (bio.toLowerCase().contains(fashionstyle.toLowerCase())) solution += fashionstyle + "";
            else if (bio.toLowerCase().contains(fashion.toLowerCase())) solution += fashion + "";
            else if (bio.toLowerCase().contains(Beauty.toLowerCase())) solution += Beauty + "";
            else if (bio.toLowerCase().contains(Lifestyle.toLowerCase())) solution += Lifestyle + "";
            else if (bio.toLowerCase().contains(shawlandscarf.toLowerCase())) solution += shawlandscarf + "";
            else if (bio.toLowerCase().contains(scarf.toLowerCase())) solution += scarf + "";
            else if (bio.toLowerCase().contains(shawl.toLowerCase())) solution += shawl + "";
            else if (bio.toLowerCase().contains(manteau.toLowerCase())) solution += manteau + "";
            else if (bio.toLowerCase().contains(mexon.toLowerCase())) solution += mexon + "";

            else if (bio.contains(POOSHAKROOZDONYA)) solution += POOSHAKROOZDONYA + "";
            else if (bio.contains(POOSHAKROOZDONYA2)) solution += POOSHAKROOZDONYA2 + "";
            else if (bio.contains(POOSHAKROOZDONYA22)) solution += POOSHAKROOZDONYA22 + "";
            else if (bio.contains(mezonaroos)) solution += mezonaroos + "";
            else if (bio.contains(KHABLEBAS)) solution += KHABLEBAS + "";
            else if (bio.contains(KHABLEBAS2)) solution += KHABLEBAS2 + "";
            else if (bio.contains(KHABLEBAS22)) solution += KHABLEBAS22 + "";
            else if (bio.contains(KHABLEBAS322)) solution += KHABLEBAS322 + "";
            else if (bio.contains(KHABLEBAS222)) solution += KHABLEBAS222 + "";
            else if (bio.contains(KHABLEBASkk)) solution += KHABLEBASkk + "";
            else if (bio.contains(BOOOTIK)) solution += BOOOTIK + "";
            else if (bio.contains(BOOOTI2K)) solution += BOOOTI2K + "";
            else if (bio.contains(PAZSOOKHT)) solution += PAZSOOKHT + "";
            else if (bio.contains(shiktarinmodels)) solution += shiktarinmodels + "";
            else if (bio.contains(jinpooshak)) solution += jinpooshak + "";
            else if (bio.contains(lebasshab)) solution += lebasshab + "";
            else if (bio.contains(ajnas)) solution += ajnas + "";
            else if (bio.contains(zananelebas)) solution += zananelebas + "";
            else if (bio.contains(zananelebas2)) solution += zananelebas2 + "";
            else if (bio.contains(tarahlebas)) solution += tarahlebas + "";
            else if (bio.contains(tarahlebas2)) solution += tarahlebas2 + "";
            else if (bio.contains(Butic)) solution += Butic + "";
            else if (bio.contains(lebasshab)) solution += lebasshab + "";
            else if (bio.contains(mantodookhtshoomiz)) solution += mantodookhtshoomiz + "";
            else if (bio.contains(pooshaak)) solution += pooshaak + "";
            else if (bio.contains(style)) solution += style + "";
            else if (bio.contains(pooshaak12)) solution += pooshaak12 + "";


            else if (bio.contains(lebas)) solution += lebas + "";
            else if (bio.contains(tanpoosh)) solution += tanpoosh + "";
            else if (bio.contains(pooshakzanane)) solution += pooshakzanane + "";
            else if (bio.contains(lebastork)) solution += lebastork + "";
            else if (bio.contains(tanpooshha)) solution += tanpooshha + "";
            else if (bio.contains(srockmardane)) solution += srockmardane + "";
            else if (bio.contains(womanwear)) solution += womanwear + "";
            else if (bio.contains(dookht)) solution += dookht + "";
            else if (bio.contains(mezon)) solution += mezon + "";

                //country and misc
            else if (bio.contains(istanbul)) solution += istanbul + "";
            else if (bio.contains(TORKIYE)) solution += TORKIYE + "";
            else if (bio.contains(TORKIYE2)) solution += TORKIYE2 + "";
            else if (bio.contains(eourpandturkey)) solution += eourpandturkey + "";
            else if (bio.contains(directsellingturkey)) solution += directsellingturkey + "";
            else if (bio.contains(FOROSH)) solution += FOROSH + "";
            else if (bio.contains(BRANDROK)) solution += BRANDROK + "";
            else if (bio.contains(mostaghimazturkey)) solution += mostaghimazturkey + "";
            else if (bio.contains(TORK)) solution += TORK + "";
            else if (bio.contains(TORKd)) solution += TORKd + "";
            else if (bio.contains(sefareshonline)) solution += sefareshonline + "";
            else if (bio.contains(kharid)) solution += kharid + "";
            else if (bio.contains(Orderonline)) solution += Orderonline + "";
            else if (bio.contains(online)) solution += online + "";
            else if (bio.contains(gallery)) solution += gallery + "";
            else if (bio.contains(boutique)) solution += boutique + "";
            else if (bio.contains(Onlineshopping)) solution += Onlineshopping + "";
            else if (bio.contains(Onlineshop)) solution += Onlineshop + "";
            else if (bio.contains(shipping)) solution += shipping + "";
            else if (bio.contains(eveningdresses)) solution += eveningdresses + "";
            else if (bio.contains(wqwq)) solution += wqwq + "";
            else if (bio.contains(HANDMADE)) solution += HANDMADE + "";
            else if (bio.contains(mahsoolat)) solution += mahsoolat + "";
            else if (bio.contains(hosoori)) solution += hosoori + "";
            else if (bio.contains(shikforoshgah)) solution += shikforoshgah + "";
            else if (bio.contains(sabtsefaresh)) solution += shikforoshgah + "";
            else if (bio.contains(nini)) solution += nini + "";
            else if (bio.contains(nini2)) solution += nini + "";


        }

        if (solution.equals("")) {
            if (fullname.contains(designAndDookht)) solution += designAndDookht + "";
            else if (fullname.contains(designAndDookht2)) solution += designAndDookht + "";
            else if (fullname.contains(designAndDookht3)) solution += designAndDookht + "";
            else if (fullname.contains(designlleba2s)) solution += designlleba2s + "";
            else if (fullname.contains(designllebas)) solution += designllebas + "";
            else if (fullname.contains(WomenWear)) solution += WomenWear + "";
            else if (fullname.contains(shal)) solution += shal + "";
            else if (fullname.contains(kif3)) solution += kif3 + "";
            else if (fullname.contains(lebashamaj)) solution += lebashamaj + "";
            else if (fullname.contains(lebashamaj2)) solution += lebashamaj2 + "";
            else if (fullname.contains(ww)) solution += ww + "";
            else if (fullname.contains(roosari1)) solution += roosari1 + "";
            else if (fullname.contains(dookht)) solution += dookht + "";
            else if (fullname.contains(aroslebas)) solution += aroslebas + "";
            else if (fullname.contains(shablebas)) solution += shablebas + "";
            else if (fullname.contains(shabtork)) solution += shabtork + "";

            else if (fullname.toLowerCase().contains(designAndDookht23.toLowerCase()))
                solution += designAndDookht23 + "";
            else if (fullname.toLowerCase().contains(designAndDookht23e.toLowerCase()))
                solution += designAndDookht23e + "";
            else if (fullname.toLowerCase().contains(mexon.toLowerCase())) solution += mexon + "";

            else if (fullname.contains(design2)) solution += design2 + "";
            else if (fullname.contains(lebastork)) solution += lebastork + "";
            else if (fullname.contains(designAndDookht33)) solution += designAndDookht33 + "";
            else if (fullname.contains(design52)) solution += design52 + "";
            else if (fullname.contains(tanpoosh)) solution += tanpoosh + "";
            else if (fullname.contains(tanpooshha)) solution += tanpooshha + "";
            else if (fullname.contains(srockmardane)) solution += srockmardane + "";
            else if (fullname.contains(tshirt)) solution += tshirt + "";
            else if (fullname.contains(einak)) solution += einak + "";
            else if (fullname.contains(einak1)) solution += einak + "";
            else if (fullname.contains(einak2)) solution += einak + "";
            else if (fullname.contains(einak32)) solution += einak + "";
            else if (fullname.toLowerCase().contains(FashionDesigner.toLowerCase())) solution += FashionDesigner + "";
            else if (fullname.toLowerCase().contains(fashionstyle.toLowerCase())) solution += fashionstyle + "";
            else if (fullname.toLowerCase().contains(fashion.toLowerCase())) solution += fashion + "";
            else if (fullname.toLowerCase().contains(Beauty.toLowerCase())) solution += Beauty + "";
            else if (fullname.toLowerCase().contains(Lifestyle.toLowerCase())) solution += Lifestyle + "";
            else if (fullname.toLowerCase().contains(shawlandscarf.toLowerCase())) solution += shawlandscarf + "";
            else if (fullname.toLowerCase().contains(scarf.toLowerCase())) solution += scarf + "";
            else if (fullname.toLowerCase().contains(shawl.toLowerCase())) solution += shawl + "";
            else if (fullname.toLowerCase().contains(manteau.toLowerCase())) solution += manteau + "";

            else if (fullname.contains(POOSHAKROOZDONYA)) solution += POOSHAKROOZDONYA + "";
            else if (fullname.contains(POOSHAKROOZDONYA2)) solution += POOSHAKROOZDONYA2 + "";
            else if (fullname.contains(POOSHAKROOZDONYA22)) solution += POOSHAKROOZDONYA22 + "";
            else if (fullname.contains(mezonaroos)) solution += mezonaroos + "";
            else if (fullname.contains(KHABLEBAS)) solution += KHABLEBAS + "";
            else if (fullname.contains(KHABLEBAS2)) solution += KHABLEBAS2 + "";
            else if (fullname.contains(KHABLEBAS22)) solution += KHABLEBAS22 + "";
            else if (fullname.contains(KHABLEBAS322)) solution += KHABLEBAS322 + "";
            else if (fullname.contains(KHABLEBAS222)) solution += KHABLEBAS222 + "";
            else if (fullname.contains(KHABLEBASkk)) solution += KHABLEBASkk + "";
            else if (fullname.contains(BOOOTIK)) solution += BOOOTIK + "";
            else if (fullname.contains(BOOOTI2K)) solution += BOOOTI2K + "";
            else if (fullname.contains(PAZSOOKHT)) solution += PAZSOOKHT + "";
            else if (fullname.contains(shiktarinmodels)) solution += shiktarinmodels + "";
            else if (fullname.contains(jinpooshak)) solution += jinpooshak + "";
            else if (fullname.contains(lebasshab)) solution += lebasshab + "";
            else if (fullname.contains(ajnas)) solution += ajnas + "";
            else if (fullname.contains(zananelebas)) solution += zananelebas + "";
            else if (fullname.contains(zananelebas2)) solution += zananelebas2 + "";
            else if (fullname.contains(tarahlebas)) solution += tarahlebas + "";
            else if (fullname.contains(tarahlebas2)) solution += tarahlebas2 + "";
            else if (fullname.contains(Butic)) solution += Butic + "";
            else if (fullname.contains(lebasshab)) solution += lebasshab + "";
            else if (fullname.contains(mantodookhtshoomiz)) solution += mantodookhtshoomiz + "";
            else if (fullname.contains(pooshaak)) solution += pooshaak + "";
            else if (fullname.contains(style)) solution += style + "";
            else if (fullname.contains(pooshaak12)) solution += pooshaak12 + "";


            else if (fullname.contains(lebas)) solution += lebas + "";
            else if (fullname.contains(tanpoosh)) solution += tanpoosh + "";
            else if (fullname.contains(pooshakzanane)) solution += pooshakzanane + "";
            else if (fullname.contains(lebastork)) solution += lebastork + "";
            else if (fullname.contains(tanpooshha)) solution += tanpooshha + "";
            else if (fullname.contains(srockmardane)) solution += srockmardane + "";
            else if (fullname.contains(womanwear)) solution += womanwear + "";
            else if (fullname.contains(dookht)) solution += dookht + "";
            else if (fullname.contains(mezon)) solution += mezon + "";

                //country and misc
            else if (fullname.contains(istanbul)) solution += istanbul + "";
            else if (fullname.contains(TORKIYE)) solution += TORKIYE + "";
            else if (fullname.contains(TORKIYE2)) solution += TORKIYE2 + "";
            else if (fullname.contains(eourpandturkey)) solution += eourpandturkey + "";
            else if (fullname.contains(directsellingturkey)) solution += directsellingturkey + "";
            else if (fullname.contains(FOROSH)) solution += FOROSH + "";
            else if (fullname.contains(BRANDROK)) solution += BRANDROK + "";
            else if (fullname.contains(mostaghimazturkey)) solution += mostaghimazturkey + "";
            else if (fullname.contains(TORK)) solution += TORK + "";
            else if (fullname.contains(TORKd)) solution += TORKd + "";
            else if (fullname.contains(sefareshonline)) solution += sefareshonline + "";
            else if (fullname.contains(kharid)) solution += kharid + "";
            else if (fullname.contains(Orderonline)) solution += Orderonline + "";
            else if (fullname.contains(online)) solution += online + "";
            else if (fullname.contains(gallery)) solution += gallery + "";
            else if (fullname.contains(boutique)) solution += boutique + "";
            else if (fullname.contains(Onlineshopping)) solution += Onlineshopping + "";
            else if (fullname.contains(Onlineshop)) solution += Onlineshop + "";
            else if (fullname.contains(shipping)) solution += shipping + "";
            else if (fullname.contains(eveningdresses)) solution += eveningdresses + "";
            else if (fullname.contains(wqwq)) solution += wqwq + "";
            else if (fullname.contains(HANDMADE)) solution += HANDMADE + "";
            else if (fullname.contains(mahsoolat)) solution += mahsoolat + "";
            else if (fullname.contains(hosoori)) solution += hosoori + "";
            else if (fullname.contains(shikforoshgah)) solution += shikforoshgah + "";
            else if (fullname.contains(nini)) solution += nini + "";
            else if (fullname.contains(nini2)) solution += nini + "";
        }


        if (solution.equals(""))
            System.out.println("nullmull");

        System.out.println("solution: " + solution);
        return solution;//لباس_ترک , عینکwooooof1551 رید کالا از ترکیه پوشاک زنانه


    }

    private ArrayList<String> phoneNumbers(String bio, String externalUrl) {
        ArrayList<String> phones = new ArrayList<>();
        String telid = "";
        System.out.println("externalUrl: " + externalUrl);

        Pattern pattern = Pattern.compile("۹[۰-۹]+");
        Pattern pattern4 = Pattern.compile("۹[۰-۹]{9}");
        Pattern pattern5 = Pattern.compile("[٠-٩]+");
        Pattern pattern2 = Pattern.compile("۹[۰-۹]{2}\\s+[۰-۹]{3}\\s+[۰-۹]{2}\\s+[۰-۹]{2}");
        Matcher matcher = pattern.matcher(bio);
        Matcher matcher2 = pattern2.matcher(bio);
        Matcher matcher4 = pattern4.matcher(bio);
        Matcher matcher5 = pattern5.matcher(bio);

        //("uD83D\uDC48۲۸۳۰۲۲۸-٠٩٣٣");۰۹۳۶۵۳۰۷۵۸۱
        Pattern pattern3 = Pattern.compile("[۰-۹]{7}-٠٩[۰-۹]{2}");
        Matcher matcher3 = pattern3.matcher(bio);

        while (matcher.find()) {
            String number = matcher.group(0);
            phones.add(number);
            System.out.println(number);
        }
        while (matcher2.find()) {
            String mach = matcher2.group(0);
            phones.add(mach);
            System.out.println(mach);
        }

        while (matcher3.find()) {
            String mach = matcher3.group(0);
            phones.add(mach);
            System.out.println(mach);
        }

        if (phones.size() < 1) {
            if (externalUrl.contains("wa.me")) {
                String number = externalUrl.split("http[s]?://wa.me/")[1];
                phones.add(number);
            }
        }
        if (phones.size() < 1) {

            while (matcher4.find()) {
                String mach = matcher4.group(0);
                if (mach.length() > 6)
                    phones.add(mach);
                System.out.println(mach);
            }
            while (matcher5.find()) {
                String mach = matcher5.group(0);
                if (mach.length() > 6)
                    phones.add(mach);
                System.out.println(mach);
            }
        }


        pattern = Pattern.compile("[^0][0]?9[0-9]{9}");
        pattern3 = Pattern.compile("9[0-9]{2}-[0-9]{3}-[0-9]{4}");
        pattern2 = Pattern.compile("09[0-9]{2}\\s+[0-9]{3}\\s+[0-9]{2}\\s+[0-9]{2}");
        matcher = pattern.matcher(bio);
        matcher2 = pattern2.matcher(bio);
        matcher2 = pattern3.matcher(bio);

        while (matcher.find()) {
            String number = matcher.group(0);
            phones.add(number);
            System.out.println(number);
        }
        while (matcher2.find()) {
            String mach = matcher2.group(0);

            phones.add(mach);
            System.out.println(mach);
        }


        if (phones.size() < 1) {
            if (bio.contains("\uD835\uDFD8\uD835\uDFE1\uD835\uDFDB\uD835\uDFE0\uD835\uDFE1\uD835\uDFDD\uD835\uDFD8\uD835\uDFE1\uD835\uDFDC\uD835\uDFE0\uD835\uDFDE"))
                phones.add("09389509486");
            if (bio.contains("۲۸۳۰۲۲۸-٠٩٣٣"))
                phones.add("۲۸۳۰۲۲۸-٠٩٣٣");
        }


        //turkey code
        pattern = Pattern.compile("0090[0-9]{10}");
        matcher = pattern.matcher(bio);

        while (matcher.find()) {
            String number = matcher.group(0);
            phones.add(number);
            System.out.println(number);
        }
        //iran tehran code
        pattern = Pattern.compile("021\\s[0-9]{2}\\s[0-9]{2}\\s[0-9]{4}");
        matcher = pattern.matcher(bio);

        while (matcher.find()) {
            String number = matcher.group(0);
            phones.add(number);
            System.out.println(number);
        }

        if (phones.size() < 1)
            System.out.println(bio);
        return phones;


    }


    private String telID(String bio, String externalUrl) {
        String telid = "";
        if (externalUrl.contains("t.me")) {
            String[] split = externalUrl.split("t.me/");
            telid = "https://t.me/" + split[split.length - 1];

        }

        return telid;
    }
}
