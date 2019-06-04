package com.Daddit.app.controllers;

import com.Daddit.app.models.Dad;
import com.Daddit.app.services.DadService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/dad")
public class DadController {
    
    private DadService dadService;
    
    @Autowired
    public DadController(DadService dadService) {
        this.dadService = dadService;
    }
    
    @GetMapping("/getAll")
    public List<Dad> getAllDads() {
        return dadService.findAllDads();
    }
    
    @PostMapping("/addDad")
    public Dad newDad(@RequestBody Map<String, String> body) {
        
        String username = body.get("username");
        String password = body.get("password");
        Dad newDad = new Dad(username, password);
        return dadService.addDad(newDad);
    }
    
   @PostMapping("/login")
    public Dad getLoginResult(@RequestBody Map<String, String> login) {
        Dad logindad = dadService.logInDad(login.get("login_username"), login.get("login_password"));
        if (logindad != null) {
            return logindad;
        }
        else{
            return null;
        }
    }
}
