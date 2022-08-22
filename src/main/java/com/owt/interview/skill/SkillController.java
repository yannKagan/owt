package com.owt.interview.skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/skill")
public class SkillController {

    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public List<Skill> getSkills(@RequestParam(required = false) Long id) {
        return skillService.getSkills(id);
    }

    @PostMapping
    public void createSkill(@RequestBody Skill skill) {
        skillService.createSkill(skill);
    }

    @DeleteMapping(path = "{skillId}")
    public void deleteSkill(@PathVariable("skillId") Long id) {
        skillService.deleteSkill(id);
    }

    @PutMapping(path = "{skillId}")
    public void updateSkill(@PathVariable("skillId") Long id,
                            @RequestParam(required = false) String name)
    {
        skillService.updateSkill(id, name);
    }
}
