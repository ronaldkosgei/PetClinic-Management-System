package com.PetClinic.Management.System.cotroller;


import com.PetClinic.Management.System.dto.VetDto;
import com.PetClinic.Management.System.entity.Vet;
import com.PetClinic.Management.System.service.VetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("vet")
public class VetAuthController {

    @Autowired
    private final VetService vetService;

    public VetAuthController(VetService vetService){
        this.vetService = vetService;
    }


    @GetMapping("index")
    public String home() {
        return "index";
    }

    @PostMapping("/login")
    public String login(){
        return "login_petOwner";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login_vet";
    }

    @GetMapping("/register/vet")
    public String showRegistrationForms(Model model) {
        VetDto petOwner = new VetDto();
        model.addAttribute("vet",petOwner);
        return "register_vet";
    }

    @PostMapping("/register/vet/save")
    public String registration(@Valid @ModelAttribute("vet") VetDto vetDto,
                               BindingResult result,
                               Model model){

        if(result.hasErrors()){
            model.addAttribute("vet", vetDto);
            return "/register_vet";
        }

        Vet existingVetByEmailAddress = vetService.findVetsByEmailAddress(vetDto.getEmailAddress());

        Vet existingVetIdNumber = vetService.findVetIdNumber(vetDto.getIdNumber());

        if(existingVetByEmailAddress != null ){
            result.rejectValue("emailAddress", null,
                    "There is already an account registered with the same email");
        }

        if(existingVetIdNumber != null ){
            result.rejectValue("idNumber", null,
                    "There is already an account registered with the same idNumber");
        }

        if(result.hasErrors()){
            model.addAttribute("vet", vetDto);
            return "/register_vet";
        }

        vetService.saveVet(vetDto);
        return "redirect:/vet/register/vet";
    }
}


