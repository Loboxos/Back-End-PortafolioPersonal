
package com.ejemplo.SpringBoot.repository;

import com.ejemplo.SpringBoot.model.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository <Persona, Integer>{
 public Optional<Persona> findByNombre(String nombre);
    public boolean existsByNombre(String apellido);

}
