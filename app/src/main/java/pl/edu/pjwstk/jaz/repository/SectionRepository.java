package pl.edu.pjwstk.jaz.repository;
import pl.edu.pjwstk.jaz.zad4.entity.Section;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SectionRepository extends JpaRepository <Section, Long> {

    Optional<Section> findByName(String name);
}
