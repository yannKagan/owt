package com.owt.interview.contactSkill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactSkillRepository extends JpaRepository<ContactSkill, Long> {

    @Query("SELECT cs FROM ContactSkill cs WHERE cs.contact.id = ?1 AND cs.skill.id = ?2")
    Optional<ContactSkill> findByIds(Long contactId, Long skillId);
}
