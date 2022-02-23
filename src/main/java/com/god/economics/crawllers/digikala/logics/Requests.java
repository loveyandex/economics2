package com.god.economics.crawllers.digikala.logics;

import lombok.SneakyThrows;

import java.io.IOException;

/**
 * @author Abolfazl
 */
public interface Requests<Response> {
     String url();

     @SneakyThrows
     public Response apply( ) throws IOException, ClassNotFoundException;

     public Response paging();


}
