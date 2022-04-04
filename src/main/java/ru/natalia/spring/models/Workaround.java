package ru.natalia.spring.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "workarounds")
public class Workaround {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Необходимо написать название решения")
    @Size(min = 2, max = 30, message = "Название должно быть в диапазоне 2 и 30 букв")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)// что это значит
    @JoinColumn(name = "workaround_id")
    @JsonManagedReference
    private Set<ProjectWorkaround> projectWorkarounds;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)// что это значит
    @JoinColumn(name = "workaround_id")
    private Set<Vulnerability> vulnerabilitySet;

    public Workaround() {

    }

    public Workaround(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Set<Vulnerability> getVulnerabilitySet() {
        return vulnerabilitySet;
    }

    public void setVulnerabilitySet(Set<Vulnerability> vulnerabilitySet) {
        this.vulnerabilitySet = vulnerabilitySet;
    }

    public Set<ProjectWorkaround> getProjectWorkarounds() {
        return projectWorkarounds;
    }

    public void setProjectWorkarounds(Set<ProjectWorkaround> projectWorkarounds) {
        this.projectWorkarounds = projectWorkarounds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
