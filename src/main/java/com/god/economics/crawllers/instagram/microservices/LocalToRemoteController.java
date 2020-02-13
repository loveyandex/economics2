package com.god.economics.crawllers.instagram.microservices;

import com.god.economics.PinstaUserRepo;
import com.god.economics.crawllers.Reqs;
import com.god.economics.crawllers.instagram.models.PinstaUser;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class LocalToRemoteController {

    @Autowired
    private PinstaUserRepo pinstaUserRepo;

//    @GetMapping("/pinsta")
    public List<PinstaUser> exchange() throws IOException {
        String req = Reqs.getReq("http://localhost:8585/pinsta");
        PinstaUser[] pinstaUser = new Gson().fromJson(req, PinstaUser[].class);
        List<PinstaUser> pinstaUsers = pinstaUserRepo.saveAll(Arrays.asList(pinstaUser));

        return pinstaUsers;
    }
}
