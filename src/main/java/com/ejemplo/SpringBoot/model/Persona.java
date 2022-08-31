
package com.ejemplo.SpringBoot.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull
    private String img;

    public Persona(){
        
    }
    public Persona(Long id,String nombre,String apellido,String img){
        this.id=id;
        this.nombre=nombre;
        this.apellido=apellido;
        this.img=img;
    }   
}
