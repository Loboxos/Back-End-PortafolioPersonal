
package com.ejemplo.SpringBoot.Dto;

import javax.validation.constraints.NotBlank;


public class dtoSkills {
       @NotBlank
    private String nombreS;
    @NotBlank
    private int porcentaje;
    
    //Constructores

    public dtoSkills() {
    }

    public dtoSkills(String nombreS, int porcentaje) {
        this.nombreS = nombreS;
        this.porcentaje = porcentaje;
    }
    
    //Getters & Setters

    public String getNombreSkill() {
        return nombreS;
    }

    public void setNombreSkill(String nombreS) {
        this.nombreS = nombreS;
    }


    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
}
