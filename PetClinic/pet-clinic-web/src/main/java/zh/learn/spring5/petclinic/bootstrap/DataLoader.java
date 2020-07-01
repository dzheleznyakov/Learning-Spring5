package zh.learn.spring5.petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import zh.learn.spring5.petclinic.model.Owner;
import zh.learn.spring5.petclinic.model.Pet;
import zh.learn.spring5.petclinic.model.PetType;
import zh.learn.spring5.petclinic.model.Specialty;
import zh.learn.spring5.petclinic.model.Vet;
import zh.learn.spring5.petclinic.model.Visit;
import zh.learn.spring5.petclinic.services.OwnerService;
import zh.learn.spring5.petclinic.services.PetTypeService;
import zh.learn.spring5.petclinic.services.SpecialtyService;
import zh.learn.spring5.petclinic.services.VetService;
import zh.learn.spring5.petclinic.services.VisitService;

import java.time.LocalDate;
import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService,
                      VetService vetService,
                      PetTypeService petTypeService,
                      SpecialtyService specialtyService,
                      VisitService visitService
    ) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) {
        int count = petTypeService.findAll().size();

        if (count == 0)
            loadData();
    }

    private void loadData() {
        PetType dog = PetType.builder()
                .name("Dog")
                .build();
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = PetType.builder()
                .name("Cat")
                .build();
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = Specialty.builder()
                .description("Radiology")
                .build();
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = Specialty.builder()
                .description("Surgery")
                .build();
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = Specialty.builder()
                .description("Dentistry")
                .build();
        Specialty savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = Owner.builder()
                .address("123 Fake Street")
                .city("Blenavon")
                .telephone("123-4567-8901")
                .pets(new HashSet<>())
                .build();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");

        Pet mikesPet = Pet.builder()
                .petType(savedDogPetType)
                .owner(owner1)
                .birthDate(LocalDate.now())
                .name("Rosco")
                .build();
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = Owner.builder()
                .address("123 Fake Street")
                .city("Blenavon")
                .telephone("123-4567-8901")
                .pets(new HashSet<>())
                .build();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");

        Pet fionasCat = Pet.builder()
                .name("Alma")
                .owner(owner2)
                .birthDate(LocalDate.now())
                .petType(savedCatPetType)
                .build();
        owner2.getPets().add(fionasCat);

        ownerService.save(owner2);

        Visit catVisit = Visit.builder()
                .pet(fionasCat)
                .date(LocalDate.now())
                .description("Sneezy kitty")
                .build();

        visitService.save(catVisit);

        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialties().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
