package javastar.hello;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StarsRepo extends JpaRepository<Star, String>{

}
