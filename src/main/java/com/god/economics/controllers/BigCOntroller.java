package com.god.economics.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Base64;

/**
 * created By aMIN on 7/24/2019 5:06 PM
 */

@Controller
public class BigCOntroller {

    @RequestMapping("/cx")
    public String f(Model model) {

        model.addAttribute("objs","[9,9,8,2,5,6,12,21,11]" );
        return "chart";
    }


    @GetMapping("/")
    public String index(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

}
