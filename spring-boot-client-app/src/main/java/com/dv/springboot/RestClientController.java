package com.dv.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vitaliy on 5/12/17.
 */
@CrossOrigin
@Controller
public class RestClientController {


    @Value("${app.server.rest.url}")
    private String restUrl;

    private Map<String, Integer> map = new HashMap<>();

    @Autowired
    private OAuth2RestTemplate restTemplate;

    @GetMapping("/")
    public String getCounterFromRestServer(Model model) {

        if(!map.isEmpty()) {
            int count = map.get("counter");
            model.addAttribute("count", count);
        }

        int count2 = restTemplate.getForObject(restUrl, Integer.class);
        model.addAttribute("count2", count2);

        return "index";

    }

    @GetMapping("/{counter}")
    @ResponseBody
    public void getCounterFromPhpClient(@PathVariable("counter") int counter) {
        map.put("counter", counter);
    }

}
