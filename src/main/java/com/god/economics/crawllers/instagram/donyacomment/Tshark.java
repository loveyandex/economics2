package com.god.economics.crawllers.instagram.donyacomment;

import com.datastax.oss.driver.api.core.CqlSession;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

/**
 * @author Abolfazl
 */
public class Tshark {

    public static void main(String[] args) throws IOException {

        CqlSession session = CqlSession.builder()
                .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
                .addContactPoint(new InetSocketAddress("127.0.0.1", 9043))
                .addContactPoint(new InetSocketAddress("127.0.0.1", 9044))
                .withLocalDatacenter("datacenter1")
                .withKeyspace("store")
                .build();

        Process netsh = Runtime.getRuntime().exec("tshark  -i 6");

        InputStream inputStream = netsh.getInputStream();
        BufferedReader inStreamReader = new BufferedReader(
                new InputStreamReader(inputStream));

        while (inStreamReader.readLine() != null) {
            String line = inStreamReader.readLine();
            System.out.println(line);
            session.execute(String.format("INSERT INTO store.wifi(line, timestamp) VALUES('"+line+"', toTimeStamp(now()));" ));

        }

    }

}

