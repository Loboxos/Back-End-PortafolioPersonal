
package com.ejemplo.SpringBoot.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nombre;
    @NotNull
    private String apellido;
     
    public Persona(){
        
    }
    public Persona(Long id,String nombre,String apellido){
        this.id=id;
        this.nombre=nombre;
        this.apellido=apellido;
    }   
}
