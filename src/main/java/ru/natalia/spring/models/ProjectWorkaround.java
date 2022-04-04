package ru.natalia.spring.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="project_workarounds")
public class ProjectWorkaround {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="description")
    @NotEmpty(message = "Необходимо добавить описания решения в проекте")
    @Size(min=5,message = "Описание должно состоять из как минимум 5 букв")
    private String description;

    @ManyToOne
    @JoinColumn(name = "workaround_id")
    @JsonBackReference
    private Workaround workaround;

    public ProjectWorkaround() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Workaround getWorkaround() {
        return workaround;
    }

    public void setWorkaround(Workaround workaround) {
        this.workaround = workaround;
    }
}
