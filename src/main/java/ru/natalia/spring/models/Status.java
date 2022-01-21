package ru.natalia.spring.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Status {
    private int id;
    @NotEmpty(message = "Необходимо написать имя статуса")
    @Size(min=2,max=30,message = "Название должно быть в диапазоне 2 и 30 букв")
    private String name;

    public Status(){
    }
    public Status(int id, String name) {
        this.id = id;
        this.name = name;
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