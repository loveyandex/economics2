package com.god.economics.crawllers.stocks.tsetmc;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.XML;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * created By gOD on 8/2/2020 12:24 PM
 */

public class ApacheRequest {

    public static String getMethod(String url) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();

//        String uri = "https://www.instagram.com/p/"+shortcode+"/";

        HttpGet httpPost = new HttpGet(url);
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static InputStream getMethodInputsterem(String url) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpPost = new HttpGet(url);
        CloseableHttpResponse response = client.execute(httpPost);
        return response.getEntity().getContent();
    }


    public static BufferedReader getMethodBufferedReader(String url) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpPost = new HttpGet(url);
        CloseableHttpResponse response = client.execute(httpPost);
        return new BufferedReader(new InputStreamReader(response.getEntity().getContent()), 65728);
    }

    public static void main(String[] args) {
        try {
            String xmlresponse = getMethod("http://www.tsetmc.com/tsev2/data/TradeDetail.aspx?i=58964593134314938");
            System.out.println(xmlresponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
