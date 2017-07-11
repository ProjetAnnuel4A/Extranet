package com.esgi.extranet;

import com.esgi.extranet.login.MailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author timotheearnauld
 */
@CrossOrigin
@Controller
public class AdminController {
    @GetMapping(value={"admin/home", ""})
    public String home(){
        return "admin/index";
    }
}
