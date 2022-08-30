
package com.ejemplo.SpringBoot.Dto;

import javax.validation.constraints.NotBlank;


public class dtoProyectos {
       @NotBlank
    private String nombreP;
    @NotBlank 
private String descripcionP;
    @NotBlank
    private String urlP;

    public dtoProyectos() {
    }

    public dtoProyectos(String nombreP, String descripcionP, String urlP) {
        this.nombreP = nombreP;
        this.descripcionP = descripcionP;
        this.urlP = urlP;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getDescripcionP() {
        return descripcionP;
    }

    public void setDescripcionP(String descripcionP) {
        this.descripcionP = descripcionP;
    }

    public String getUrlP() {
        return urlP;
    }

    public void setUrlP(String urlP) {
        this.urlP = urlP;
    }
    

}
