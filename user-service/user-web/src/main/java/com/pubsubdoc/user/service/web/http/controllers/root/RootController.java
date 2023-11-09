package com.pubsubdoc.user.service.web.http.controllers.root;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {
    @RequestMapping("/")
    public String index() {
        return "redirect:/swagger-ui/index.html";
    }
}
