package zh.learn.spring5.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import zh.learn.spring5.petclinic.model.Owner;
import zh.learn.spring5.petclinic.services.OwnerService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {
    private static final String CREATE_OR_UPDATE_OWNER_VIEW = "owners/createOrUpdateOwnerForm";
    private static final String FIND_OWNERS_VIEW = "owners/findOwners";
    private static final String OWNERS_LIST_VIEW = "owners/ownersList";
    private static final String OWNER_DETAILS_VIEW = "owners/ownerDetails";

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return FIND_OWNERS_VIEW;
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        if (owner.getLastName() == null)
            owner.setLastName("");

        String searchTerm = "%" + owner.getLastName() + "%";
        List<Owner> results = ownerService.findAllByLastNameLike(searchTerm);
        if (results.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return FIND_OWNERS_VIEW;
        } else if (results.size() == 1) {
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            model.addAttribute("selections", results);
            return OWNERS_LIST_VIEW;
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView(OWNER_DETAILS_VIEW);
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", new Owner());
        return CREATE_OR_UPDATE_OWNER_VIEW;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_OWNER_VIEW;
        } else {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable("ownerId") Long id, Model model) {
        Owner owner = ownerService.findById(id);
        model.addAttribute("owner", owner);
        return CREATE_OR_UPDATE_OWNER_VIEW;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable("ownerId") Long ownerId) {
        if (result.hasErrors()) {
            return CREATE_OR_UPDATE_OWNER_VIEW;
        } else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }
}
