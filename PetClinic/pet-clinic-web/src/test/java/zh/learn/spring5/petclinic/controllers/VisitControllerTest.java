package zh.learn.spring5.petclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;
import zh.learn.spring5.petclinic.model.Pet;
import zh.learn.spring5.petclinic.model.Visit;
import zh.learn.spring5.petclinic.services.PetService;
import zh.learn.spring5.petclinic.services.VisitService;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {
    private static final String PETS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";
    private static final String REDIRECT_OWNERS_1 = "redirect:/owners/1";

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    @InjectMocks
    VisitController visitController;

    MockMvc mockMvc;

    private long ownerId = 1L;
    private long petId = 2L;
    private Pet pet;
    private Visit visit;

    private final UriTemplate visitUriTemplate = new UriTemplate("/owners/{ownerId}/pets/{petId}/visits/new");
    private final Map<String, Long> visitUriVariables = new HashMap<>();
    private URI visitURI;

    @BeforeEach
    void setUpMockMvc() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(visitController)
                .build();
    }

    @BeforeEach
    void setUpDomainElementsAndMocks() {
        pet = Pet.builder().id(ownerId).build();
        visit = Visit.builder().id(1L).build();

        when(petService.findById(anyLong()))
                .thenReturn(pet);
    }

    @BeforeEach
    void setUpNewVisitUri() {
        visitUriVariables.clear();
        visitUriVariables.put("ownerId", ownerId);
        visitUriVariables.put("petId", petId);
        visitURI = visitUriTemplate.expand(visitUriVariables);
    }

    @Test
    void initNewVisitForm() throws Exception {
        mockMvc.perform(get(visitURI))
                .andExpect(status().isOk())
                .andExpect(view().name(PETS_CREATE_OR_UPDATE_VISIT_FORM))
                .andExpect(model().attributeExists("visit"));
    }

    @Test
    void processNewVisitForm() throws Exception {
        when(visitService.save(any()))
                .thenReturn(visit);

        mockMvc.perform(post(visitURI))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_OWNERS_1))
                .andExpect(model().attributeExists("visit"));

        verify(visitService, times(1)).save(any(Visit.class));
    }
}