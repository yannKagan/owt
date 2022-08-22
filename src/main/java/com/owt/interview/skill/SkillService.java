package com.owt.interview.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    @Autowired
    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<Skill> getSkills(Long id) {
        if (id != null) {
            Skill skill = skillRepository.findById(id).orElseThrow(
                    () -> new IllegalStateException(String.format("Skill with id `%d` not found", id))
            );

            return List.of(skill);
        }

        return skillRepository.findAll();
    }

    public void createSkill(Skill skill) {
        Optional<Skill> optionalSkill = skillRepository.findSkillByName(skill.getName());

        if (optionalSkill.isPresent()) {
            throw new IllegalStateException("Skill already exist");
        }

        skillRepository.save(skill);
    }

    public void deleteSkill(Long id) {
        Skill skill = skillRepository.findById(id).orElseThrow(
                () -> new IllegalStateException(String.format("Skill with id `%d` not found", id))
        );

        skillRepository.delete(skill);
    }

    public void updateSkill(Long id, String name) {
        Skill skill = skillRepository.findById(id).orElseThrow(
                () -> new IllegalStateException(String.format("Skill with id `%d` not found", id))
        );

        if (name != null) {
            skill.setName(name);
        }
    }
}
