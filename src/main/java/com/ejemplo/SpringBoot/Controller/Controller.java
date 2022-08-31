
package com.ejemplo.SpringBoot.Controller;

import com.ejemplo.SpringBoot.Dto.dtoPersona;
import com.ejemplo.SpringBoot.model.Persona;
import com.ejemplo.SpringBoot.security.Controller.Mensaje;
import com.ejemplo.SpringBoot.service.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://frontendprueba0912.web.app")
public class Controller {
  
    @Autowired
    private IPersonaService persoServ;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new/persona")
    public void agregarPersona(@RequestBody Persona pers){
      persoServ.crearPersona(pers);       
    }
    @GetMapping("/ver/personas")
    @ResponseBody
    public List<Persona>verPersonas(){
      return persoServ.verPersonas();
    }
   
    @DeleteMapping("/delete/{id}")
    public void borrarPersona(@PathVariable Long id){
        persoServ.borrarPersona(id);
    }
        @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody dtoPersona dtopersona){
        
        Persona persona = persoServ.buscarPersona(id);        

        persona.setNombre(dtopersona.getNombre());
        persona.setApellido(dtopersona.getApellido());
        persona.setImg(dtopersona.getImg());
        
        persoServ.crearPersona(persona);
        
        return new ResponseEntity(new Mensaje("Foto actualizada"), HttpStatus.OK);
    }
      @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id")Long id){
        
        Persona persona = persoServ.buscarPersona(id);
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    
    @GetMapping("/personas/traer/perfil")
    public Persona findPersona(){
        return persoServ.buscarPersona((long)1);
    }
}
