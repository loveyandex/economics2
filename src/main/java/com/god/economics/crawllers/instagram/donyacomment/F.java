package com.god.economics.crawllers.instagram.donyacomment;

import org.json.JSONObject;

import java.io.IOException;

/**
 * created By gOD on 7/20/2020 12:30 PM
 */

public class F {

    public static void main(String[] args) {
        String isin = "IRO3TPEZ0001";
        String orderCount = "15252";
        String orderPrice = "20368";
        String req = "{\"IsSymbolCautionAgreement\":false,\"CautionAgreementSelected\":false,\"IsSymbolSepahAgreement\":false,\"SepahAgreementSelected\":false,\"orderCount\":" + orderCount + ",\"orderPrice\":" + orderPrice + ",\"FinancialProviderId\":1,\"minimumQuantity\":\"\",\"maxShow\":0,\"orderId\":0,\"isin\":\"IRO1BRKT0001\",\"orderSide\":65,\"orderValidity\":74,\"orderValiditydate\":null,\"shortSellIsEnabled\":false,\"shortSellIncentivePercent\":0}";
        String cocike = "GuidedTourVersion=1; SiteVersion=3.7.4; _ga=GA1.2.2099735577.1580370223; crisp-client%2Fsession%2Fe95056ad-2681-452d-976d-0c2a304165c9=session_413b3d4a-1eaa-47f4-b140-039b337e9ac7; _gid=GA1.2.1493581837.1595054765; silverse=r4wkurkomxvloiu4omd4pgxk; text0_-1511277686=true; text0_655037329=true; Token=d5995c5f-dba7-4394-b4fc-81ea277094d9; .ASPXAUTH=2D30808EF2D1233F4F9A8167CF5CB50DEFF2345068DBC43F9B7FAA07836CC23CABDEB8F9C61B6708464F57BE69A066FC9938A57942616DE728FA27DD5857EA8E6CB034B92553E0B4C178A66E18BFB815E2CD04535B4353EAB0E27F8391F86F559BEE878EB97A6F41FD346DC352A71C17CC6A6F63D1286C0218FB14412266326F";
        cocike = "GuidedTourVersion=1; SiteVersion=3.7.4; _ga=GA1.2.2099735577.1580370223; crisp-client%2Fsession%2Fe95056ad-2681-452d-976d-0c2a304165c9=session_413b3d4a-1eaa-47f4-b140-039b337e9ac7; _gid=GA1.2.136695747.1595577779; silverse=vzbpdhssj4ktc25z4xlc0nsx; text0_-842327187=true; text0_1940924546=true; text0_853512067=true; .ASPXAUTH=BF4502799D4ED0202BD7FE2926B26220360FCFB1E7FC6D8AA6C991EF10B3156C0E53714DECCF8F257E44981698607C4B63F51AE7A27AD8151D19700DB254DE69C10193B0225DF24FDDFA72FFC8FD615C64DAD5E2A0840E448D0B4696C3E64DCF9BC40CC4EBEEB07FB167F0E7FB4E3386B41B8E0AA5A3B859A80BBA505FB9D210; Token=bc35fb87-a0d7-4b98-9c70-a442123d8a28; _gat_gtag_UA_77742685_1=1";

        String path = "https://onlineplus.mofidonline.com/Customer/SendOrder";
        int k = 0;
        long millis = System.currentTimeMillis();


        while (true)
            try {

                String s = WithUrlLoggedCommnets.buystock(path, cocike, req);
                System.out.println(s);
                long l = System.currentTimeMillis();
                long x = l - millis;
                System.out.println(x);
                millis = l;

                if (((boolean) new JSONObject(s).get("IsSuccessfull"))) {
                    break;
                }

                Thread.sleep(200);

                k++;
                if (k > 5)
                    break;

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
    }
}
