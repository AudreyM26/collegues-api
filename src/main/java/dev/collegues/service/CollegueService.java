package dev.collegues.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import dev.collegues.entites.Collegue;
import dev.collegues.exception.CollegueNonTrouveException;
import dev.collegues.repository.CollegueRepository;


@Service
public class CollegueService {

	private CollegueRepository collegueRepository;

	public CollegueService(CollegueRepository collegueRepository) {
		super();
		this.collegueRepository = collegueRepository;
	}

	public List<String> listerMatricules() {
		return this.collegueRepository.findAll().stream().map(col -> col.getMatricule())
				.collect(Collectors.toList());
	}

	public List<String> rechercherColleguesParNom(String nomRequete) {

		return this.collegueRepository.findByNom(nomRequete).stream().map(col -> col.getMatricule())
				.collect(Collectors.toList());
	}

	public Collegue rechercherCollegueParMatricule(String matriculeRequest){
		return this.collegueRepository.findByMatricule(matriculeRequest).orElseThrow(() -> new CollegueNonTrouveException("Collègue non trouvé"));
	}
	
	public Collegue creerCollegue(Collegue colRecu) {
		colRecu.setNom(colRecu.getNom().toUpperCase());
		/*
		 * if (this.collegueRepository.existsByNomAndPrenoms(colRecu.getNom(),
		 * colRecu.getPrenoms())) { throw new EntityExistsException(); }
		 */

		return this.collegueRepository.save(colRecu);
	}

}
