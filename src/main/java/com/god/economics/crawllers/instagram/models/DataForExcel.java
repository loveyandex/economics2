package com.god.economics.crawllers.instagram.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;

/**
 * created By gOD on 1/31/2020 1:55 PM
 */

@Getter
@Setter
@Accessors(chain = true)
public class DataForExcel {

    private String fullname;
    private String username;
    private String type;
    private String telID;

    public DataForExcel(String fullname, String username, String type, String telID, ArrayList<String> phones) {
        this.fullname = fullname;
        this.username = username;
        this.type = type;
        this.telID = telID;
        this.phones = phones;
    }

    private ArrayList<String> phones;


}
