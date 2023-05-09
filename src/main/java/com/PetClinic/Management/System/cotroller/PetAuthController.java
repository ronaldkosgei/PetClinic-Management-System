package com.PetClinic.Management.System.cotroller;

import com.PetClinic.Management.System.dto.PetDto;
import com.PetClinic.Management.System.entity.Pet;
import com.PetClinic.Management.System.service.PetService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("pet")
public class PetAuthController {

    private final PetService petService;

    public PetAuthController(PetService petService){
        this.petService = petService;
    }


    @GetMapping("index")
    public String home() {
        return "index";
    }

    @GetMapping("/register/pet")
    public String showRegistrationForms(Model model) {
        PetDto pet = new PetDto();
        model.addAttribute("pet",pet);
        return "register_pet";
    }

    @PostMapping("/register/pet/save")
    public String registration(@Valid @ModelAttribute("pet") PetDto petDto,
                               BindingResult result,
                               Model model) {
        Pet existingPetByIdTag = petService.findByIdTag(petDto.getIdTag());

        if(existingPetByIdTag != null ){
            result.rejectValue("idTag", null,
                    "There is a Pet registered with the same idTag");
        }

        petService.savePet(petDto);
        return "redirect:/pet/register/pet";
    }
}
