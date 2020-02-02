package com.god.economics.crawllers.stocks.tsetmc;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * created By gOD on 12/17/2019 2:46 PM
 */

public class EasyTrader {


    public static void main(String[] args) throws IOException, InterruptedException {



        while(true){

            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://old.easytrader.emofid.com/MarketData/GetMainIndices");

            String json = "{}";
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
//        httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json;charset=UTF-8");
            httpPost.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.79 Safari/537.36");
            httpPost.setHeader("cookie", "__auc=e13daf4f16f1300e95a9ca0d2e3; _ga=GA1.2.48110319.1576571825; _gid=GA1.2.710220218.1576571825; _hjid=2e5b317c-d22b-46e7-97fd-34838ccdd2da; __RequestVerificationToken=m4R4jbppe6Y3oyqS0aMLJBBvQJJZcTF9JIQDS2RLwEugx845F5PP5LzUMdy1Iwa-gccl2RBuX7KBdK9LaPrNo8BF4P2ARm44VaywbI764G41; .AspNet.ExternalCookie=0XgUbQ9mnYDlQ0iI2ujIMragQ3dRJA7k2sMRp-zxkC7eB3Jo12rA0zhUEzgmIWKpmu9d0zUdfXq-CosRWWbMGmSfAXjp2U-OK_zJ83tJgLPDJp9vhcBJ2zqAZPNpQNCHNRks6ExMBTVmZS2epvYgthQUMSi3cZDxUnRrG-JcCsEc8SVlk8nGq76TSVPcIcjXcyHGFJsw9Yc37pVMrEc2QVS92OZISzgwwgGvc79_mvBQ58MJQ7EiTey95FUr2OZdv52bcjwW14Ld3hWIKE_G0kRHh_lTG5kyI-smyjKhZg7GZJQbDEdN94dyS1kHdx7-pkunT9RgCan7zFXIW9oY_bbPWeswprNuVPjtxXKaOSDYaGtq5TjJONxzWHN06cAraEPaZmq7cb0y7vI7v9r-rlOsr2PVdBu-qaJ3o45p-PxhLyl6xOLE_7K58Vt_g97kLJJYanaO2dYW3GBECMkq-W8dFOkbA7L_KbLL9CaUqkf1l9C4CsG35yunEphifhw0baI0FHocIHg_yozz_sRJ5dmpqmoeNAzeRoXGsL2pgbJE_lensjBsx-XYPn2HXQhCW0j_pY2Rio8kA4HLaKn39bsHsI_VoRM5PkAQxjhK20L2MIuEgBvvDHnhes3oQUEFj5sFsDN54MPuWaUTUUkhgSBzK7q70lhbeXAVVlWeOiUxHGFeku2P5k4yQgEVUioyGvVNF83EtcLGjQLUC7MUsZypPYlQczOu-GbxDrmIM14u9cBID99uPnAOaMxjCZx2Xvytrwl9H6VTnqMiS89QWHs2K9NY1OYsqu03A6iqPX6tn2SijnP6I3v12GbhcWxH1aP0jkphwNC8zMVd73PerYvBG8WJQ7k8N85F7cpPEew_5xpDp2MdXYCEtd1vaqru83RtJ3DBtsdGDt5swlaRsRhDO-sV_aRYASkJ4to-A3OqSUUuvIt_EcjGGcpbuu3dJ74gqV8v73IxGzlkVUIkuMMlob564BaNgCoLID4lwP4ZGUGEbDeHjIjmMsG8s_Z6eRv2j230mRqWfGJ5LCrTqWNM-w-9mxTBfHszZa3yAy35CEMZeYJz58_mXauHHVYBIvKs4_85ofFo9G6BpMrDljZWPpeu8_bLH8iA1yVKFoOhX91cM19ae-ELA1h_frs-96ytyw_PqY6jr3xS-qsFCgrc0LlBTWdSXpqP9cgD7E-ILsjtcRF9xot8J5MQcIivpoyZPNg-XaFt8bhD8-0FiMOp4uC15IlU6sLa-ffBx9Bslq4skXhgjuIyFuaiWbFWWMwXRp7fIZMwVSZtN3Y33c70L6klQAcUFSqJXWweAv7YqBq8Qof_WjGi7SnvrnzKgfw0IjfhnbR__B8OOm6qNhVYBvM3OZqkVvS4l9nhIIn-YAeCSVjw7s9amXqAzfg8rpo7iaBTh2lgfGlYJVXlfiwdzj3rfNFRYeBUDDG91haYB6x8IIOKukSx41EgmByHARvg5ZWEv5JIv8uyjAChcm6S-PveUsTjz0PSpx4567x3oLXb5N93KuFI1yRgsXA1OEGLGjvuktPYg4hf_Jnc-FmmlgjZ4yQQ3ueHbKF1huV8vslb00BlbJwYklLm4Ofz-pLfcgJ67JgRJuPNh_5510endnauHVNpMPEToK65d1qNWlOXVsBgaeis3uhq9m53acotsFB6IThMn7r7CUS-X4-kNbrBbXVRSNmp7XKnMiyoPjdqFCG5KA0xWKhnf9zky6Tb1fTq-w6DF636PdliHv02GC6FTgEUMZn3ph7A5zbZTMr31sLq_PA7sAdHV63dA-Xrqh246xU6GjuZCBJYQdlQmInFqKjlbUzoCyBPAtnPS9cCz_YyqUake4AfAAEhgW8ETzy_vYxxAQ3XEc25kie1Mpe27Xx4jnHBFNMIlQZHmuDwEN3beQYoZnDih0fT9rsIdFpIHeK5fuspOZrRWEftCElAvGgFDYTMbRZqclTXhgLUHdyL42mXqB_3dxZGnZacO1S8XlnV0iUEEVOkXKSwbP3s0zej3Xuws9DThhm4M_1HoDyEvXj0i7NfuWqkUiiKkfJG5Oft6ONu91YvYKRa1Qv_DdYZRPj336CmPatFCGrwFPoknrWiuN_jvxuCHTsWpZvK6GzPpAWmqBg98ch6xlHva6dBNjH7rCpvhFW0tD1E7KGEvd1wbiMfJDmY1EzU0ePImOtjvbJTDaQC-T546udoe77fUNigOgeoThVDuTkyu4jI1D_ExU6x5TWIHA_Owk9bJOTIU3N5Np44mty3guzUfzNpazgPjsHQqQjcAphCKYx4puS-dpQ1DpNpbXlkyMEZFH2nyh2BH79zp0crlmWai-HPVA1xp3ENV_LHlPBmwJZS2-NpGPQ-AefmJAh1ncrwGbaYdQC9Q1Nv7YCl2-xUhhFFHoQvZZwi_iBEfs_wiBIU6deYCwkBMoR5iKGpVdOwZ42eVV4_IBCl9AKYbngasz0hXEBaGYBhnR2u_4N-EZsXV3qRjvPa1unjS4oYeMIdM7jxGlQjVK2X8r5gY2XgXEwwstxNiiyRmcv53ozSaQKBtt8u5qD10jhKOPHUvQc9uT4K6Q3ngvtKR4J6muns77DYsG6eN1HxwgZqD5NVJW7HPPkHPoUxGNsgzlef32n1haO4CqwRcSt6mI5M4RQ0iCMsJCpLfxyXBbICOQvVmzuHaWmrNZPqZ8ldgvUPj59UC_MtOWS4s0DOPQ; .AspNet.ApplicationCookie=f_i3Gedoot4g7IhJhs9wdwrloIxqbaX9XqTP63M9SldDPYqIQ0H2x6wl4A-gpkbp9vckjDcHnt992FRl3i-_uaR0Jufho09Li4ub8iL6hYak3SP49KydgaQ20Ay7XBBbCdZTJ3Vp0j2pBrRGWBtxHa9n9G-N_-rKm84hHLORokEYXIIrJkIQTpYJrVZdmqjW18Elz3uhshl4A8V2Tipo-fnNCKBUvARCvhCXpai_FBT4vv5kQYacEid10cu1GpH7dBRGU9c_rIwlMdwSw05YHHb35sEl-8tyBle43sGQpNTvzJ6iS6mLLrO7MYmrzTEkIfqAkZ6EiqVuzftCNcvmHA0mQpueVP-FvM8rF44iPsApndbuP0FX4AziQ3kw6VokS1zopGgSOb-enaA-2LOyRvkY0ZO3glBFRNGjlijq4rH5MlLznb1hLH4urKA22rr7ocLb5V9Hi2RWmCvqi46qEGqEjhbAJVM8QXfwp_YvnKA7f7ru6F9RbtmbBymqiLHvIVjk_6MgnZts8G9NXbudK4n-E5qqae0ttTr7aUmhr70CIhcuPtOpNxcTp_WA_oFv07lENBIRHxTqBL2SfW_zXlKB3YEjIkQ-RSvC0HCU5D2zUQ8IUASu15Hdf21KU-KMK2gGI3fGE-CCRbLHSsjEiD9sxc0lgXEgP2jao7dO7i3ug7a3SyeLQcMYHLFjhP6CBGXGgrTA7HZiHCjWmapTjxpalp_MNkrzx1fKaP61iAiOjfctdlBsr5MZ_CwV8Re1U0EBjJPZ_mtzOHna831hyDNOvG7HS8BEWOON2VvLKmBme6dqbxcMP1V_HjyPt-ZAbzopdZ4QKug55sQXYutZeXMmosuHrWSK3uh1ucKV3s8Mmbu5rM0KItcYmkuQXAEyTk5FiORMA6orllyB1c_8We5NUbNe3hw0M79rcWklnGQEdkllNCvkQDNak8rjHvJ8Rc0f7jPJXRNxT_ayZ-sW9COpvKLHRPRvuM_uGjDdxLRm7DdMlcMqonYeiYsK05jeTh_jmIeSL84YjKUzJs-u_0s6-EQ00sXYD2yNvXhz81prnKn3iPcPNSCziexcr6tVJ2PTZs5kytWQ7m_UpOE7AeEGqsqjbIw21JfoMTSkidGYKpCJlxI_22vygx5dRJ-4GEINuIO_5BizbxA2i1jrKa0jGsqh4QEJ5FK4uvFxaDpzN2N_0ZRzqnX35G9ZnUuzEtdqRRfPeI4wq8tiXOcAm_sStFWyn4f9CC2EW8gs2o3Mqr6orDzf8T64xAg30TExtfey_ov_mH93riT_kc-rElDVzUh6xiKGaQXgVj8pGMgZMFaY-gX1ar2_hIlsiQgYAZtUMvUjbZygTJkKIj9KD6Tx09t0Do_RRZCyPiGo0tC435EkB1QiCR3EbD4g5aXAvneqYrWHT_XiUsPOC2rPaEN4jXFJn7Eh43V4aeXhM0Ph12SP-uN-Mqrif_nIy0XtkvzFEtHknISgUGNHS2B5gI4TXe66qtpDKy65XXAVko2Gy6nfYQUVmtERZQ8U7890WYmILPW0p6qWHP0JOdH4yc4cBz6iTLEMR6nM9CU6JuNcTxFZqG4OWmVvByOJPjw1F2duWJd85m6-BgxdL8tp6WWHePis7Jb9VWv7nZy7GN_WBof-x85p8Bl09aXEGG7xdwAzWH2PIyGxlPkQCOtobxkNYC3L28c-ms0aBtofuTfjww_Pfj8lY6v_UO69D7MBo7iQKMXSMaB284c2kdQ_YGX2avSW1otJ51Q93K51D_zOqcR5j7J2YbGsMuSq-1p7kp4D9DLdhDfGrq9lpV7wLxeJwBihyhTx1sBbxCaBBL4; LS6_https_1603_https%3A%2F%2Fpush.etadbir.com%2F=|307_TadbirConnection|; LS6_https_1603_TadbirConnection=|307|; LS6_https_1603_307_TadbirConnection=1576597022060|C|LS6__old_easytrader_emofid_com_307_TadbirConnection|old.easytrader.emofid.com");

            CloseableHttpResponse response = client.execute(httpPost);

            int statusCode = response.getStatusLine().getStatusCode();
            response.getEntity().getContentLength();  //it should not be 0

            StringBuilder sb = new StringBuilder();
            try {
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(response.getEntity().getContent()), 65728);
                String line = null;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }
            catch (IOException e) { e.printStackTrace(); }
            catch (Exception e) { e.printStackTrace(); }


            System.out.println("finalResult " + sb.toString());

            JSONObject jsObject = new JSONObject(sb.toString());



            client.close();

            Thread.sleep(10000);
        }


//
//        OkHttpClient client = new OkHttpClient();
//        MediaType JSON
//                = MediaType.get("application/json;charset=UTF-8");
//        String url = "https://old.easytrader.emofid.com/MarketData/GetMainIndices";
//        String json = "{}";
//
//        RequestBody body = RequestBody.create(JSON, json);
//
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            String string = response.body().string();
//            System.out.println(string);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }
}
