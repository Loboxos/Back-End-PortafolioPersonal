
package com.ejemplo.SpringBoot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Proyectos {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
    private String nombreP;
    private String descripcionP;
   private String urlP;

    public Proyectos() {
    }

    public Proyectos( String nombreP, String descripcionP, String urlP) {
        this.nombreP = nombreP;
        this.descripcionP = descripcionP;
        this.urlP = urlP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getDescripcionp() {
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