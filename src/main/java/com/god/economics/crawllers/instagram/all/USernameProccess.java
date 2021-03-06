package com.god.economics.crawllers.instagram.all;

import com.god.economics.PinstaUserRepo;
import com.god.economics.crawllers.Reqs;
import com.god.economics.crawllers.instagram.models.DataForExcel;
import com.god.economics.crawllers.instagram.models.HashTags;
import com.god.economics.crawllers.instagram.models.PinstaUser;
import com.google.gson.Gson;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
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
        ArrayList<String> provinceAndtowns = new ArrayList<>();
        Scanner provinceAndtownsScanner = new Scanner(new File("provinceAndtowns.txt"));
        while (provinceAndtownsScanner.hasNextLine()) {
            provinceAndtowns.add(provinceAndtownsScanner.nextLine());
        }

        Scanner scanner = new Scanner(new File("usernames1_5000.txt"));
        int ap = 0;
        while (scanner.hasNext()) {

            String resp = null;
            try {
                String username = scanner.nextLine();
                if (pinstaUserRepo.existsById(username)) {
                    System.err.println("it exists " + username);
                    continue;
                }

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
                String userId = (String) ((JSONObject) (((JSONObject) graphql).get("user")))
                        .get("id");

                int edge_followed_by = ((int) ((JSONObject) ((JSONObject) (((JSONObject) graphql).get("user")))
                        .get("edge_followed_by")).get("count"));

                int edge_follow = ((int) ((JSONObject) ((JSONObject) (((JSONObject) graphql).get("user")))
                        .get("edge_follow")).get("count"));

                String city = city(bio, provinceAndtowns);
                System.out.println("city " + city);
                PinstaUser pinstaUser = new PinstaUser(username, username, bio, externalurl, full_name, city, HashTags.MANTO,edge_followed_by, edge_follow);
                pinstaUser.setUserId(userId);

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
        ArrayList<DataForExcel> dataForExcels = new ArrayList<>();
        ArrayList<String> provinceAndtowns = new ArrayList<>();
        Scanner provinceAndtownsScanner = new Scanner("provinceAndtowns");
        while (provinceAndtownsScanner.hasNextLine()) {
            provinceAndtowns.add(provinceAndtownsScanner.nextLine());
        }

        for (PinstaUser pinstaUser : pinstaUserAll) {
            String bio = pinstaUser.getBio();
            String externalUrl = pinstaUser.getExternalUrl();
//            System.out.println(bio);
//            System.out.println(pinstaUser.getUsername());
            String type = type(pinstaUser.getFullName(), bio);
            String city = city(bio, provinceAndtowns);
//            if (!city.isEmpty())


            ArrayList<String> phoneNumbers = phoneNumbers(bio, externalUrl);
            String telID = telID(bio, externalUrl);
//            System.out.println("phons: " + new Gson().toJson(phoneNumbers));
//            System.out.println("telid" + telID);
            String einak = "عینک";

            if (!type.equals("") && !type.equals(einak)) {
                DataForExcel dataForExcel = new DataForExcel(pinstaUser.getFullName(), pinstaUser.getUsername(), type, telID, phoneNumbers);
                dataForExcels.add(dataForExcel);
            }
//            System.out.println("------------------------------------------------------------------------");


        }
        addtoSheet(dataForExcels);


    }

    private void addtoSheet(ArrayList<DataForExcel> dataForExcels) throws IOException {

        FileInputStream myxls = null;
        // Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
        CreationHelper createHelper = workbook.getCreationHelper();

        Sheet sheet = workbook.createSheet("data");


        // Create Other rows and cells with employees data
        int rowNum = 1;
        for (DataForExcel dataForExcel : dataForExcels) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0)
                    .setCellValue(dataForExcel.getFullname());
            row.createCell(1)
                    .setCellValue(dataForExcel.getType());

            Row row1 = sheet.createRow(rowNum++);
            row1.createCell(0)
                    .setCellValue(dataForExcel.getUsername());

            Row row2 = sheet.createRow(rowNum++);
            row2.createCell(0)
                    .setCellValue(dataForExcel.getTelID());

            String phns = "";
            for (String phone : dataForExcel.getPhones()) {
                phns += phone + ",";
            }
            Row row3 = sheet.createRow(rowNum++);
            row3.createCell(0)
                    .setCellValue(phns);

        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("poi2.xlsx");
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();

    }

    private void addtoSheet(String fullName, String username, String type, ArrayList<String> phoneNumbers, String telID) {


    }

    private String type(String fullname, String bio) {

        bio = bio.replaceAll("ى", "ی");
        bio = bio.replaceAll("ي", "ی");
        bio = bio.replaceAll("ك", "ک");

        fullname = fullname.replaceAll("ى", "ی");
        fullname = fullname.replaceAll("ي", "ی");
        fullname = fullname.replaceAll("ك", "ک");

        String leabasMaj = "لباس مجلسي،لباس مجلسى،لباس مجلسی";
        String manto = "manto،Manto";

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
        String designAndDookhtmanto = "طراحی و دوخت مانتو";
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
        String shawlandscarfpersian = "شال و روسری";
        String shawlandscarfpersian2 = "شال و روسری";
        String dastdooz = "دستدوز";
        String dastdooz2 = "دست دوز";


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
        String manto2 = "مانتو";

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
            if (bio.contains(designAndDookht))
                solution += designAndDookht + "";
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
            else if (bio.contains(manto2)) solution += manto2 + "";
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


    private String city(String bio, ArrayList<String> provinceAndtowns) {
        bio = bio.replaceAll("ى", "ی");
        bio = bio.replaceAll("ي", "ی");
        bio = bio.replaceAll("ك", "ک");

        for (String provinceAndtown : provinceAndtowns) {
            String[] split = provinceAndtown.split(">>>");
            String province = split[0]
                    .replaceAll("ي", "ی")
                    .replaceAll("ى", "ی")
                    .replaceAll("ك", "ک");
            String city = split[1]
                    .replaceAll("ي", "ی")
                    .replaceAll("ى", "ی")
                    .replaceAll("ك", "ک");

            if (bio.contains(city))
                return city;
            if (bio.contains(province))
                return province;
        }
        return "";
    }

    private ArrayList<String> phoneNumbers(String bio, String externalUrl) {
        ArrayList<String> phones = new ArrayList<>();
        String telid = "";
        System.out.println("externalUrl: " + externalUrl);

        Pattern pattern = Pattern.compile("۹[۰-۹]+");
        Pattern pattern4 = Pattern.compile("۹[۰-۹]{9}");
        Pattern pattern5 = Pattern.compile("[٠-٩]+");
        Pattern pattern6 = Pattern.compile("۹[۰-۹]{2}-[۰-۹]{7}");
        Pattern pattern7 = Pattern.compile("[٠-٩]+-[٠-٩]+");

        Pattern pattern2 = Pattern.compile("۹[۰-۹]{2}\\s+[۰-۹]{3}\\s+[۰-۹]{2}\\s+[۰-۹]{2}");

        Matcher matcher = pattern.matcher(bio);
        Matcher matcher2 = pattern2.matcher(bio);
        Matcher matcher4 = pattern4.matcher(bio);
        Matcher matcher5 = pattern5.matcher(bio);
        Matcher matcher6 = pattern6.matcher(bio);
        Matcher matcher7 = pattern7.matcher(bio);

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


        while (matcher6.find()) {
            String mach = matcher6.group(0);
            phones.add(mach);
            System.out.println(mach);
        }
        while (matcher7.find()) {
            String mach = matcher7.group(0);
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

        }
        while (matcher5.find()) {
            String mach = matcher5.group(0);
            if (mach.length() > 6)
                phones.add(mach);
            System.out.println(mach);
        }

        pattern = Pattern.compile("[^0][0]?9[0-9]{9}");
        pattern2 = Pattern.compile("9[0-9]{2}\\s+[0-9]{3}\\s+[0-9]{2}\\s+[0-9]{2}");
        pattern3 = Pattern.compile("9[0-9]{2}-[0-9]{3}-[0-9]{4}");
        pattern4 = Pattern.compile("9[0-9]{2}\\s[0-9]{7}");
        pattern5 = Pattern.compile("021[0-9]{8}");
        pattern6 = Pattern.compile("9[0-9]{2}-[0-9]{7}");
        pattern7 = Pattern.compile("9[0-9]{2}_[0-9]{7}");
        Pattern pattern8 = Pattern.compile("9[0-9]{2}_[0-9]{3}_[0-9]{4}");
        Pattern pattern9 = Pattern.compile("[0-9]{7}_0*9[0-9]{2}");

        matcher = pattern.matcher(bio);
        matcher2 = pattern2.matcher(bio);
        matcher3 = pattern3.matcher(bio);
        matcher4 = pattern4.matcher(bio);
        matcher5 = pattern5.matcher(bio);
        matcher6 = pattern6.matcher(bio);
        matcher7 = pattern7.matcher(bio);
        Matcher matcher8 = pattern8.matcher(bio);
        Matcher matcher9 = pattern9.matcher(bio);

        while (matcher.find()) {
            String number = matcher.group(0);
            phones.add(number);
            System.out.println(number);
        }
        while (matcher3.find()) {
            String mach = matcher3.group(0);

            phones.add(mach);
            System.out.println(mach);
        }

        while (matcher2.find()) {
            String mach = matcher2.group(0);

            phones.add(mach);
            System.out.println(mach);
        }


        while (matcher4.find()) {
            String mach = matcher4.group(0);

            phones.add(mach);
            System.out.println(mach);
        }


        while (matcher5.find()) {
            String mach = matcher5.group(0);

            phones.add(mach);
            System.out.println(mach);
        }


        while (matcher6.find()) {
            String mach = matcher6.group(0);

            phones.add(mach);
            System.out.println(mach);
        }


        while (matcher7.find()) {
            String mach = matcher7.group(0);

            phones.add(mach);
            System.out.println(mach);
        }

        while (matcher8.find()) {
            String mach = matcher8.group(0);

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

        pattern2 = Pattern.compile("0090\\s+([0-9]+)\\s+[0-9]{3}\\s+[0-9]{2}\\s+[0-9]{2}");
        matcher2 = pattern2.matcher(bio);

        while (matcher.find()) {
            String number = matcher.group(0);
            phones.add(number);
            System.out.println(number);
        }


        while (matcher2.find()) {
            String number = matcher2.group(0);
            phones.add(number);
            System.out.println(number);
        }
        //iran tehran code
        pattern = Pattern.compile("021\\s[0-9]{2}\\s[0-9]{2}\\s[0-9]{4}");
        matcher = pattern.matcher(bio);

        pattern2 = Pattern.compile("021_[0-9]*\\s*[0-9]*\\s*[0-9]*");
        matcher2 = pattern2.matcher(bio);


        while (matcher.find()) {
            String number = matcher.group(0);
            phones.add(number);
            System.out.println(number);
        }

        while (matcher2.find()) {
            String number = matcher2.group(0);
            phones.add(number);
            System.out.println(number);
        }

        if (phones.size() < 1) {

            while (matcher9.find()) {
                String mach = matcher9.group(0);

                phones.add(mach);
                System.out.println(mach);
            }
        }
        if (phones.size() < 1) {


            pattern2 = Pattern.compile("0[0-9]{2}_[0-9]*\\s*[0-9]*\\s*[0-9]*");
            matcher2 = pattern2.matcher(bio);
            while (matcher2.find()) {
                String number = matcher2.group(0);
                phones.add(number);
                System.out.println(number);
            }

//            pattern = Pattern.compile("[0-9]+[-_\\s]*[0-9]+-*_*\\s*[0-9]*");
//            matcher = pattern.matcher(bio);
//
//            while (matcher.find()) {
//                String number = matcher.group(0);
//                phones.add(number);
//                System.out.println(number);
//            }


            System.out.println(bio);

        }
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
