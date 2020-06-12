package zh.learn.spring5.petclinic.services;

import zh.learn.spring5.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
}
