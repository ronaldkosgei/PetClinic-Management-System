package com.PetClinic.Management.System.cotroller;

import com.PetClinic.Management.System.dto.PetOwnerDto;
import com.PetClinic.Management.System.entity.PetOwner;
import com.PetClinic.Management.System.service.PetOwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("petOwner")
public class PetOwnerAuthController {

    @Autowired
    private final PetOwnerService petOwnerService;

    public PetOwnerAuthController(PetOwnerService petOwnerService){
        this.petOwnerService = petOwnerService;
    }


    @GetMapping("index")
    public String home() {
        return "index";
    }

    @PostMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register/petOwner")
    public String showRegistrationForms(Model model) {
        PetOwnerDto petOwner = new PetOwnerDto();
        model.addAttribute("petOwner",petOwner);
        return "register_petOwner";
    }

    @PostMapping("/register/petOwner/save")
    public String registration(@Valid @ModelAttribute("user") PetOwnerDto petOwnerDto,
                               BindingResult result,
                               Model model){

        if(result.hasErrors()){
            model.addAttribute("petOwner", petOwnerDto);
            return "/register_petOwner";
        }

        PetOwner existingPetOwnerByEmail = petOwnerService.findPetOwnersByEmailAddress(petOwnerDto.getEmailAddress());

        PetOwner existingPetOwnerIdNumber = petOwnerService.findPetOwnerIdNumber(petOwnerDto.getIdNumber());

        if(existingPetOwnerByEmail != null ){
            result.rejectValue("emailAddress", null,
                    "There is already an account registered with the same email");
        }

        if(existingPetOwnerIdNumber != null ){
            result.rejectValue("idNumber", null,
                    "There is already an account registered with the same idNumber");
        }

        if(result.hasErrors()){
            model.addAttribute("petOwner", petOwnerDto);
            return "/register_petOwner";
        }

        petOwnerService.savePetOwner(petOwnerDto);
        return "redirect:/petOwner/register/petOwner";
    }
}
