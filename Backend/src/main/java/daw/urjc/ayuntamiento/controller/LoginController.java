package daw.urjc.ayuntamiento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping ("/userlogin")
    public String login(){
        return "login";
    }


    @RequestMapping ("/errorLogin")
    public String errorlogin(){
        return "errorLogin";
    }
}