package com.esgi.extranet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author timotheearnauld
 */
@CrossOrigin
@Controller
public class AdminController {

    @Autowired
    public AdminController() {

    }

    @GetMapping(value={"home", ""})
    public String home(){
        return "admin/index";
    }
}
