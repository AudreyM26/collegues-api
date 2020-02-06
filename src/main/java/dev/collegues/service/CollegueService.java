package dev.collegues.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
		return this.collegueRepository.findAll().stream().map(col -> col.getMatricule()).collect(Collectors.toList());
	}

	public List<String> rechercherColleguesParNom(String nomRequete) {

		return this.collegueRepository.findByNom(nomRequete).stream().map(col -> col.getMatricule())
				.collect(Collectors.toList());
	}

	public Collegue rechercherCollegueParMatricule(String matriculeRequest) {
		return this.collegueRepository.findByMatricule(matriculeRequest)
				.orElseThrow(() -> new CollegueNonTrouveException("Collègue non trouvé"));
	}

	public Collegue createCollegue(Collegue collegueRecu) {
		Long numeroCol = (this.collegueRepository.count() + 1);
		String matricule = "M";
		if (numeroCol < 10) {
			matricule += "0";
		}
		matricule += numeroCol;
		collegueRecu.setNom(collegueRecu.getNom().toUpperCase());
		collegueRecu.setMatricule(matricule);

		// return collegueRecu;
		return this.collegueRepository.save(collegueRecu);
	}

	public Collegue updateCollegueParMatricule(String matricule, Collegue collegueModif) {

		Collegue col = this.collegueRepository.findByMatricule(matricule)
				.orElseThrow(() -> new CollegueNonTrouveException("Collègue non trouvé"));
		if (collegueModif.getPhotoUrl() != null) {
			col.setPhotoUrl(collegueModif.getPhotoUrl().trim());
		}
		if (collegueModif.getEmail() != null) {
			col.setEmail(collegueModif.getEmail().toLowerCase());
		}

		this.collegueRepository.save(col);
		return col;
	}
}
