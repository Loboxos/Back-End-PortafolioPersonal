
package com.ejemplo.SpringBoot.service;

import com.ejemplo.SpringBoot.model.Persona;
import com.ejemplo.SpringBoot.repository.PersonaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PersonaService {

    @Autowired PersonaRepository persoRepo;
    
  
    public List<Persona> list() {
       return persoRepo.findAll();
    }
 public Optional<Persona> getOne(int id){
        return persoRepo.findById(id);
    }
     public Optional<Persona> getByNmbre(String nombre){
        return persoRepo.findByNombre(nombre);
    }
    
    public void crearPersona(Persona per) {
       persoRepo.save(per);
    }
    public void delete(int id){
        persoRepo.deleteById(id);
    }
    
  public boolean existsById(int id){
        return persoRepo.existsById(id);
    }
    
    public boolean existsByNombreE(String nombre){
        return persoRepo.existsByNombre(nombre);
    }

    public Persona buscarPersona(int id) {
     return persoRepo.findById(id).orElse(null);
    }
    
}
