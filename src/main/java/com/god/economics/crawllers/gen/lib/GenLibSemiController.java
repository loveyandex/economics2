package com.god.economics.crawllers.gen.lib;

import com.god.economics.BookRepo;
import com.god.economics.models.Book;
import com.god.economics.models.GenAuthor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * created By gOD on 11/2/2019 12:21 AM
 */

@RestController
public class GenLibSemiController {

    @Autowired
    private BookRepo bookRepo;

    private String host = "http://gen.lib.rus.ec/";

    @GetMapping("/addbookfromgen")
    private ArrayList<Book> main() throws IOException {
        ArrayList<Book> books = new ArrayList<>();

        Document document = Jsoup.connect("http://gen.lib.rus.ec/search.php?req=seo&lg_topic=libgen&open=0&view=simple&res=100&phrase=1&column=def").get();

        Element body = document.body();
        Element table = body.getElementsByTag("table").get(2);


        Elements rows = table.getElementsByTag("tr");
        for (int i = 1; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements td = row.getElementsByTag("td");
            Element authors = td.get(1);
            String genid = td.get(0).text();
            Elements authorsnameAndLinks = authors.getElementsByTag("a");
            ArrayList<GenAuthor> authorslist = new ArrayList<>();

            for (Element authorsandlink : authorsnameAndLinks) {
                String href = authorsandlink.attr("href");
                String name = authorsandlink.text();
                authorslist.add(new GenAuthor().setName(name));
                System.out.printf("%s >> %s/%s \n", name, host, href);

            }
            String title;
            String pagelink;
            try {
                Element Title = td.get(2);
                Elements a = Title.getElementsByTag("a");
                if (a.size() == 2) {
                    title = a.get(1).textNodes().get(0).text();
                     pagelink = a.get(1).attr("href");
                } else if (a.size() == 1) {
                    title = a.get(0).textNodes().get(0).text();
                     pagelink = a.get(0).attr("href");

                }else {
                    title = "rrrrrrrrrrr";
                    pagelink = "rrrrrrrrrrr";

                }


            } catch (java.lang.IndexOutOfBoundsException e) {
                title = "";
                pagelink = "";
            }


            Element Publisher = td.get(3);
            String publisher = Publisher.text();


            Element Year = td.get(4);
            String year = Year.text();


            Element Pages = td.get(5);
            String pages = Pages.text();


            Element Language = td.get(6);
            String lang = Language.text();

            Element Size = td.get(7);
            String size = Size.text();


            Element Extension = td.get(8);
            String extension = Extension.text();


            Book book = new Book(genid, title, authorslist, publisher, pages, lang, extension, year, size, pagelink);

            bookRepo.save(book);


            books.add(book);

        }
        return books;
    }




    @GetMapping("/gotopages")
    private void page() throws IOException{
        List<Book> books = bookRepo.findAll();

        for (Book book : books) {
            String pageLink = host+book.getPageLink();

            Document document = Jsoup.connect(pageLink).get();

            Element body = document.body();
            String coverPath = body.getElementsByTag("img")
                    .get(0).attr("src");
            String genLibRusEcUrlforThisbook =  body.getElementsByTag("table")
                    .get(4).getElementsByTag("tr")
                    .get(0).getElementsByTag("td")
                    .get(0).getElementsByTag("a").get(0).attr("href");

            System.out.println(genLibRusEcUrlforThisbook);



        }




    }
}
