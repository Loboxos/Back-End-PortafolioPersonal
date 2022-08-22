
package com.ejemplo.SpringBoot.Dto;

import javax.validation.constraints.NotBlank;

public class dtoExperiencia {
    @NotBlank
    private String nombreE;
@NotBlank 
private String fechaE;
    @NotBlank
    private String descripcionE; 

//

    public dtoExperiencia() {
    }

    public dtoExperiencia(String nombreE,String fechaE ,String descripcionE) {
        this.nombreE = nombreE;
        this.fechaE=fechaE;
        this.descripcionE = descripcionE;
    }
//

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }

    public String getDescripcionE() {
        return descripcionE;
    }

    public void setDescripcionE(String descripcionE) {
        this.descripcionE = descripcionE;
    }

    public String getFechaE() {
        return fechaE;
    }

    public void setFechaE(String fechaE) {
        this.fechaE = fechaE;
    }
    

}
