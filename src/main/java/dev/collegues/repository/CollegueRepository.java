package dev.collegues.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.collegues.entites.Collegue;


public interface CollegueRepository extends JpaRepository<Collegue, Integer>{

	List<Collegue> findByNom(String nom);
	Optional<Collegue> findByMatricule(String matricule);
}
