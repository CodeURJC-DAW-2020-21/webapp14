package daw.urjc.ayuntamiento.controller;

import daw.urjc.ayuntamiento.modules.User;
import daw.urjc.ayuntamiento.service.UserService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registeredUser")
    public String userRegister(User user, MultipartFile imageField, Model model) throws IOException {

        if (!imageField.isEmpty()) {
            user.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));

        }
        String passwordaux;
        passwordaux=passwordEncoder.encode(user.getPassword());

        user.setPassword(passwordaux);
        List<String> roles = new LinkedList<>();
        roles.add("USER");
        user.setRoles(roles);
        service.save(user);

        model.addAttribute("userName",user);

        return "index";
    }

    @GetMapping("/user/{userName}/image")
    public ResponseEntity<Object> giveUserImage(@PathVariable String userName) throws SQLException {
        Optional<User> user = service.findByName(userName);
        if(user.isPresent() && user.get().getImageFile() != null){
            Resource file = new InputStreamResource(user.get().getImageFile().getBinaryStream());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg").contentLength(user.get().getImageFile().length()).body(file);
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/editProfileP")
    public String editProfile(Model model, User user, MultipartFile imageField,HttpServletRequest request) throws IOException {

        Principal principal = request.getUserPrincipal();
        Optional<User> principaluser = service.findByName(principal.getName());

        if (user.getPassword().isEmpty()){
            user.setPassword(principaluser.get().getPassword());
        }

        if (imageField.isEmpty()){

            user.setImageFile(principaluser.get().getImageFile());
        }
        else {
            user.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
        }

        service.save(user);


        return "profile";
    }



    @GetMapping("/editProfile")
    public String editProfile(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        Optional<User> user = service.findByName(principal.getName());

        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "editpProfile";
        } else {
            return "login";
        }
    }





}

