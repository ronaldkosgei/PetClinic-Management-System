package com.PetClinic.Management.System.cotroller;

import com.PetClinic.Management.System.dto.PetDto;
import com.PetClinic.Management.System.entity.Pet;
import com.PetClinic.Management.System.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("pet")
public class PetAuthController {

    @Autowired
    private final PetService petService;

    public PetAuthController(PetService petService){
        this.petService = petService;
    }


    @GetMapping("index")
    public String home() {
        return "index";
    }

    @GetMapping("/register/pet")
    public String showRegistrationForm(Model model) {
        PetDto pet = new PetDto();
        return "register";
    }

    @PostMapping("/register/pet/save")
    public void registration(@Valid @ModelAttribute("pet") PetDto petDto,
                             BindingResult result,
                             Model model) {
        Pet existingPet = petService.findPetsByPetName(petDto.getPetName());

        if (existingPet != null && existingPet.getPetName() != null &&
                !existingPet.getPetName().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }
    }
}
