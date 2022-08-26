
package com.ejemplo.SpringBoot.Controller;

import com.ejemplo.SpringBoot.Dto.dtoExperiencia;
import com.ejemplo.SpringBoot.model.Experiencia;
import com.ejemplo.SpringBoot.security.Controller.Mensaje;
import com.ejemplo.SpringBoot.service.ExperienciaService;
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
@RequestMapping("/explab")
@CrossOrigin(origins = "**")
public class ControllerExp {
    @Autowired
    ExperienciaService sExperiencia;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list (){
        List<Experiencia>list =sExperiencia.list();
        return new ResponseEntity(list,HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoExperiencia dtoexp){
        if(StringUtils.isBlank(dtoexp.getNombreE()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"),HttpStatus.BAD_REQUEST);
    if (sExperiencia.existsByNombreE(dtoexp.getNombreE()))
        return new ResponseEntity(new Mensaje("Esa Experiencia existe"), HttpStatus.BAD_REQUEST);
     Experiencia experiencia = new Experiencia(dtoexp.getNombreE(),dtoexp.getFechaE(),dtoexp.getDescripcionE());
     sExperiencia.save(experiencia);
     return new ResponseEntity(new Mensaje("Experiencia agregada"),HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = sExperiencia.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id,@RequestBody dtoExperiencia dtoexp){
        //validamos si existe el id
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("el ID no existe"), HttpStatus.BAD_REQUEST);
       //compara nombre de experiencia
        if(sExperiencia.existsByNombreE(dtoexp.getNombreE())&& sExperiencia.getByNombreE(dtoexp.getNombreE()).get().getId() != id)
   return new ResponseEntity(new Mensaje("Esa experiencia ya existe"),HttpStatus.BAD_REQUEST);
         //no puede esar vacio
       if(StringUtils.isBlank(dtoexp.getNombreE()))
           return new ResponseEntity(new Mensaje("el nombre es obligatorio"),HttpStatus.BAD_REQUEST);
    Experiencia experiencia = sExperiencia.getOne(id).get();
    experiencia.setNombreE(dtoexp.getNombreE());
    experiencia.setFechaE(dtoexp.getFechaE());
    experiencia.setDescripcionE(dtoexp.getDescripcionE());
    
    sExperiencia.save(experiencia);
    return new ResponseEntity(new Mensaje("experiencia actualizada"),HttpStatus.OK);
    
       
    }
 @DeleteMapping("/delete/{id}")    
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        //validamos  id  
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("el ID no existe"), HttpStatus.BAD_REQUEST);
         sExperiencia.delete(id);
         return new ResponseEntity(new Mensaje("Experiencia eliminada"),HttpStatus.OK);
    }
}
