package pl.edu.pjwstk.jaz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.pjwstk.jaz.repository.SectionRepository;
import pl.edu.pjwstk.jaz.zad4.entity.Section;

@RestController
public class SectionController {

     private SectionRepository sectionRepository;

    @PostMapping ("/addSection")
    public ResponseEntity<String> addSection(@RequestBody Section section){
    if(sectionRepository.findByName(section.getName()).isEmpty()){
        sectionRepository.save(section);
        return new ResponseEntity<>("Section created.", HttpStatus.CREATED);
    }
    else{
        return new ResponseEntity<>("A section with the given name already exists.", HttpStatus.NOT_ACCEPTABLE);
    }

    }
}
