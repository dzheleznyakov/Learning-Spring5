package zh.learn.spring5.petclinic.model;

public class Specialty extends BaseEntity {
    private String description;

    public Specialty() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
