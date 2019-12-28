package javastar.hello;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SkillsController {

	@Autowired
	SkillsRepo repo;
	 
	@GetMapping("/api/skills")
	public List<Skill> getSkills(@RequestParam(name="term") String term){
		return repo.findByTerm(term);
	}
	
}
