package zh.learn.spring5.petclinic.services.map;

import org.springframework.stereotype.Service;
import zh.learn.spring5.petclinic.model.Specialty;
import zh.learn.spring5.petclinic.services.SpecialtiesService;

import java.util.Set;

@Service
public class SpecialtiesMapService extends AbstractMapService<Specialty, Long> implements SpecialtiesService {
    @Override
    public Set<Specialty> findAll() {
        return super.findAll();
    }

    @Override
    public Specialty findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Specialty save(Specialty specialty) {
        return super.save(specialty);
    }

    @Override
    public void delete(Specialty specialty) {
        super.delete(specialty);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
