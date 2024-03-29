
package com.ejemplo.SpringBoot.Controller;

import com.ejemplo.SpringBoot.Dto.dtoSkills;
import com.ejemplo.SpringBoot.model.Skills;
import com.ejemplo.SpringBoot.security.Controller.Mensaje;
import com.ejemplo.SpringBoot.service.SkillsService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skills")
@CrossOrigin(origins = "https://frontendprueba0912.web.app")
public class ControllerSkills {
     @Autowired
    SkillsService skillsService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Skills>> list(){
        List<Skills> list = skillsService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
      @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoSkills dtoski){
        if(StringUtils.isBlank(dtoski.getNombreS())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
            if(skillsService.existsByNombreSkill(dtoski.getNombreS())){
            return new ResponseEntity(new Mensaje("Habilidad existente"), HttpStatus.BAD_REQUEST);
    }
        Skills skills = new Skills(dtoski.getNombreS(),dtoski.getPorcentaje());
        skillsService.save(skills);
        
        return new ResponseEntity(new Mensaje("Habilidad agregada"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Skills> getById(@PathVariable("id") int id){
        if(!skillsService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        Skills skills = skillsService.getOne(id).get();
        return new ResponseEntity(skills, HttpStatus.OK);
    }
      @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody dtoSkills dtoski){
        //Validar ID
        if(!skillsService.existsById(id)){
            return new ResponseEntity(new Mensaje("ID inexistente"), HttpStatus.BAD_REQUEST);
        //Comparar nombre Habilidad
        }
        if(skillsService.existsByNombreSkill(dtoski.getNombreS()) && skillsService.getByNombreSkill(dtoski.getNombreS()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Habilidad existente"), HttpStatus.BAD_REQUEST);
        //No puede estar vacio 
        }
        if(StringUtils.isBlank(dtoski.getNombreS())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        Skills skills = skillsService.getOne(id).get();
        skills.setNombreS(dtoski.getNombreS());
        skills.setPorcentaje(dtoski.getPorcentaje());
        
        skillsService.save(skills);
        return new ResponseEntity(new Mensaje("Habilidad actualizada"), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!skillsService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        skillsService.delete(id);
        return new ResponseEntity(new Mensaje("Eliminado"), HttpStatus.OK);
    }

}