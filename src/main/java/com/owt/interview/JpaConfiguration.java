package com.owt.interview;

import com.owt.interview.contact.Contact;
import com.owt.interview.contact.ContactRepository;
import com.owt.interview.contactSkill.ContactSkill;
import com.owt.interview.skill.Skill;
import com.owt.interview.skill.SkillRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.Set;

@Configuration
@Profile("dev")
public class JpaConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(ContactRepository contactRepository,
                                        SkillRepository skillRepository) {
        return args -> {
            Contact yann = new Contact(
                    null,
                    "Yann",
                    "Kagan",
                    "Reignier Esery",
                    "yann.kagan@gmail.com",
                    "+33 6 01 23 45 67"
            );
            Contact laura = new Contact(
                    null,
                    "Laura",
                    "Kagan",
                    "Reignier Esery",
                    "laura.kagan@gmail.com",
                    "+33 6 01 23 45 68"
            );
            Skill pastry = new Skill(
                    null,
                    "pastry"
            );
            Skill coding = new Skill(
                    null,
                    "coding"
            );
            Skill climbing = new Skill(
                    null,
                    "climbing"
            );

            skillRepository.saveAll(List.of(pastry, coding, climbing));

            Set<ContactSkill> contactSkills = Set.of(
                    new ContactSkill(null, yann, climbing, 5),
                    new ContactSkill(null, yann, coding, 1),
                    new ContactSkill(null, yann, pastry, 8)
            );
            yann.setContactSkills(contactSkills);

            contactRepository.saveAll(List.of(yann, laura));
        };
    }
}
