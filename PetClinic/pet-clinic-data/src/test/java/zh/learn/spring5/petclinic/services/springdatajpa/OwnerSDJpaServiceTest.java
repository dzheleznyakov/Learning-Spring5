package zh.learn.spring5.petclinic.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zh.learn.spring5.petclinic.model.Owner;
import zh.learn.spring5.petclinic.repositories.OwnerRepository;
import zh.learn.spring5.petclinic.repositories.PetRepository;
import zh.learn.spring5.petclinic.repositories.PetTypeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
    static final String LAST_NAME = "Smith";

    @InjectMocks
    OwnerSDJpaService service;

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    private Owner owner;
    private Long ownerId;

    @BeforeEach
    void setUp() {
        ownerId = 1L;
        owner = Owner.builder()
                .id(ownerId)
                .lastName(LAST_NAME)
                .build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(LAST_NAME))
                .thenReturn(owner);

        Owner owner = service.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, owner.getLastName());

        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = new HashSet<>();
        ownerSet.add(owner);
        ownerSet.add(Owner.builder().id(2L).lastName("Brown").build());

        when(ownerRepository.findAll())
                .thenReturn(ownerSet);

        Set<Owner> owners = service.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(ownerId))
                .thenReturn(Optional.of(owner));

        Owner foundOwner = service.findById(ownerId);

        assertNotNull(foundOwner);
        assertEquals(ownerId, foundOwner.getId());
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(ownerId))
                .thenReturn(Optional.empty());

        Owner foundOwner = service.findById(ownerId);

        assertNull(foundOwner);
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(2L).build();

        when(ownerRepository.save(any()))
                .thenReturn(owner);

        Owner savedOwner = service.save(ownerToSave);

        assertNotNull(savedOwner);
        verify(ownerRepository).save(ownerToSave);
    }

    @Test
    void delete() {
        service.delete(owner);

        verify(ownerRepository, times(1)).delete(owner);
    }

    @Test
    void deleteById() {
        service.deleteById(ownerId);

        verify(ownerRepository).deleteById(ownerId);
    }
}