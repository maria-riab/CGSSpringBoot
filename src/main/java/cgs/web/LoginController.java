package cgs.web;

import cgs.Administrator;
import cgs.data.AdministratorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class LoginController {
    private AdministratorRepository adminRepo;

    public LoginController(AdministratorRepository adminRepo) {
        this.adminRepo = adminRepo;
    }
    @ModelAttribute(name = "administrator")
    public Administrator administrator(){
        return new Administrator();
    }

    @GetMapping
    public String login(){
        return "login";
    }
    @PostMapping
    public String processLogin(@Valid Administrator administrator, Errors errors){

        if (errors.hasErrors()|| !(isAdministrator(administrator))){
            return "login";
        }
        return "redirect:/student";
    }

    public boolean isAdministrator(Administrator administrator){
        try{
            Administrator admin = adminRepo.findAdministratorByUsernameAndPassword(administrator.getUsername(), administrator.getPassword()).get(0);
            if (admin != null){
                return true;
            }
            return false;

        } catch (Exception e){
            return false;
        }
    }
}
