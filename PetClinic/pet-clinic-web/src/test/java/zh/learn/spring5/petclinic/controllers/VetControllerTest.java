package zh.learn.spring5.petclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import zh.learn.spring5.petclinic.model.Vet;
import zh.learn.spring5.petclinic.services.VetService;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {
    @Mock
    VetService vetService;

    @InjectMocks
    VetController vetController;

    MockMvc mockMvc;

    private Set<Vet> vets;

    @BeforeEach
    void setUp() {
        vets = new HashSet<>();
        vets.add(Vet.builder().id(1L).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(vetController)
                .build();
    }

    @Test
    void testAccessToVetList() throws Exception {
        when(vetService.findAll())
                .thenReturn(vets);

        mockMvc.perform(get("/vets"))
                .andExpect(status().isOk())
                .andExpect(view().name("vets/index"))
                .andExpect(model().attributeExists("vets"));
    }

    @Test
    void name() throws Exception {
        when(vetService.findAll())
                .thenReturn(vets);

        mockMvc.perform(get("/api/vets"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\": 1}]"));
    }
}