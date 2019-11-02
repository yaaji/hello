package javastar.hello;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	
	@Autowired
	StarsRepo repo;
	
	@GetMapping("/")
	public String index(ModelMap model) {
		model.addAttribute("stars", repo.findAll() );
		return "index";
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
	public String post(@ModelAttribute Star star) {
		repo.save(star);
		return "redirect:/";
	}
	
	
}
