
package com.ejemplo.SpringBoot.Controller;

import com.ejemplo.SpringBoot.Dto.dtoPersona;
import com.ejemplo.SpringBoot.model.Persona;
import com.ejemplo.SpringBoot.security.Controller.Mensaje;
import com.ejemplo.SpringBoot.service.PersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/personas")
@CrossOrigin(origins = "https://frontendprueba0912.web.app")
public class Controller {
  
    @Autowired
   PersonaService persoServ;
    
    @GetMapping("/saludar")
    public String saludar(){
        return "bienvenidos a arg programa";
    }
    
     @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list (){
        List<Persona> list = persoServ.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new/persona")
    public void agregarPersona(@RequestBody Persona pers){
      persoServ.crearPersona(pers);       
    }
    @GetMapping("/ver/personas")
    @ResponseBody
    public List<Persona>verPersonas(){
      return persoServ.list();
    }
  
   @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void borrarPersona(@PathVariable int id){
        persoServ.delete(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
        @PutMapping("/personas/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtopersona){
          if(!persoServ.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if(persoServ.existsByNombreE(dtopersona.getNombre()) && persoServ.getByNmbre(dtopersona.getNombre()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtopersona.getNombre())){
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        Persona persona = persoServ.buscarPersona(id);        

        persona.setNombre(dtopersona.getNombre());
        persona.setApellido(dtopersona.getApellido());
        persona.setImg(dtopersona.getImg());
        
        persoServ.crearPersona(persona);
        
        return new ResponseEntity(new Mensaje("Foto actualizada"), HttpStatus.OK);
    }
      @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id")int id){
       if(!persoServ.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Persona persona = persoServ.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    
    @GetMapping("/personas/traer/perfil")
    public Persona findPersona(){
        return persoServ.buscarPersona((int)1);
    }
}
