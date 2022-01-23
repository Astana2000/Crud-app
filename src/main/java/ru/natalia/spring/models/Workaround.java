package ru.natalia.spring.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="workarounds")
public class Workaround {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    @NotEmpty(message = "Необходимо написать название решения")
    @Size(min=2,max=30,message = "Название должно быть в диапазоне 2 и 30 букв")
    private String name;

    @ManyToMany(mappedBy = "workarounds")
    private List<VulnerabilityType> vulnerabilityTypes;

    public Workaround(){

    }
    public Workaround(int id,String name){
        this.id = id;
        this.name=name;
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
