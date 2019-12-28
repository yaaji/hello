package javastar.hello;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SkillsRepo extends JpaRepository<Skill, Integer>{
	
	@Query("from Skill where name like concat('%',:term,'%') order by name")
	public List<Skill> findByTerm(String term);
	
}
