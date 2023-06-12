
package com.ejemplo.SpringBoot.service;

import com.ejemplo.SpringBoot.model.Skills;
import com.ejemplo.SpringBoot.repository.SkillsRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SkillsService {
     @Autowired
    SkillsRepository skillsRepository;

    public List<Skills> list() {
        return skillsRepository.findAll();
    }

    public Optional<Skills> getOne(int id) {
        return skillsRepository.findById(id);
    }

    public Optional<Skills> getByNombreSkill(String nombreS) {
        return skillsRepository.findByNombreS(nombreS);
    }

    public void save(Skills ski) {
        skillsRepository.save(ski);
    }

    public void delete(int id) {
        skillsRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return skillsRepository.existsById(id);
    }

    public boolean existsByNombreSkill(String nombreS) {
        return skillsRepository.existsByNombreS(nombreS);
    }
}
