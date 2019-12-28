package javastar.hello;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	
	@Autowired
	StarsRepo repo;
	
	@Autowired
	SkillsRepo skillsRepo;
	
	@GetMapping("/")
	public String index(@RequestParam(defaultValue = "0", required = false) int page, ModelMap model) {
		PageRequest pagable = PageRequest.of(page, 5, Sort.by("name").ascending());
		model.addAttribute("stars", repo.findAll(pagable) );
		return "index";
	}
	
	@GetMapping("/error")
	public String error() {
		return "error";
	}
	
	@GetMapping("/form")
	public String form(ModelMap model, @RequestParam(required = false) String name) {
		
		Star star = new Star();
		
		if( name != null) {
			Optional<Star> optionStar = repo.findById(name);
			if( optionStar.isPresent() ) {
				star= optionStar.get();
			}
		}
		
		model.addAttribute("star", star);
		model.addAttribute("skills", skillsRepo.findAll() );
		return "form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam String name) {
		
		
		if( name != null) {
			Optional<Star> optionStar = repo.findById(name);
			if( optionStar.isPresent() ) {
				repo.delete(optionStar.get());
			}
		}
		
		return "redirect:/";
	}
	
	@PostMapping("/")
	public String post(@Valid Star star, BindingResult bindingResult) {
		if( bindingResult.hasErrors()) {
			return "form";
		}
		
		repo.save(star);
		return "redirect:/";
	}
	
	
}
