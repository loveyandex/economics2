package com.god.economics.crawllers.digikala;

import com.god.economics.ItemRepository;
import com.god.economics.crawllers.digikala.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * created By gOD on 11/22/2019 8:46 PM
 */

@RestController
public class AnalysController {

    @Autowired
    private ItemRepository itemRepository;


    @GetMapping("/google")
    public ArrayList<Item> index() {

        ArrayList<Item> items = new ArrayList<>();
        for (Item item : itemRepository.findAll()) {
            if (item.getPrice()>0)
                items.add(item);
        }
        return items;

    }


}
