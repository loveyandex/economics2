package com.god.economics.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;

/**
 * created By gOD on 11/2/2019 2:26 AM
 */
@Getter
@Setter
@Accessors(chain = true)
public class Book {
    @Id
    private String id;
    private String genID;
    private String title;
    private String pageLink;
    private String coverLink;
    private String srcLink;
    private ArrayList<GenAuthor> authors;
    private ArrayList<String> subjects;
    private String publisher;
    private String pages;
    private String lang;
    private String extension;
    private String year;
    private String size;

    public Book() {
    }

    public Book(String genID, String title, ArrayList<GenAuthor> authors, String publisher, String pages, String lang, String extension, String year, String size, String pageLink) {
        this.pageLink = pageLink;
        this.size = size;
        this.genID = genID;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.pages = pages;
        this.lang = lang;
        this.extension = extension;
        this.year = year;
    }
}
