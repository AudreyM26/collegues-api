package dev.collegues.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.collegues.entites.Collegue;
import dev.collegues.entites.ColleguePhoto;
import dev.collegues.exception.CollegueNonTrouveException;
import dev.collegues.service.CollegueService;

@CrossOrigin
@RestController
@RequestMapping(value = "/collegues")
public class CollegueController {

	private CollegueService collegueService;

	public CollegueController(CollegueService collegueService) {
		super();
		this.collegueService = collegueService;
	}

	@GetMapping
	public List<String> listerMatricules() {
		return this.collegueService.listerMatricules();
	}

	// requete GET clients?nom=XXX , methode executee avec url clients avec
	// parametre
	@GetMapping(params = "nom")
	public List<String> rechercherColleguesParNom(@RequestParam("nom") String nomRequeteHttp) {
		return this.collegueService.rechercherColleguesParNom(nomRequeteHttp);
	}

	@GetMapping("{matriculeReqHttp}")
	// @RequestMapping(path = "/{matriculeReqHttp}")
	public Collegue rechercherCollegueParMatricule(@PathVariable @Valid String matriculeReqHttp) {
		return this.collegueService.rechercherCollegueParMatricule(matriculeReqHttp);
	}
	
	@GetMapping("/photos")
	// @RequestMapping(path = "/{matriculeReqHttp}")
	public List<ColleguePhoto> listerPhotosCollegue() {
		return this.collegueService.listerPhotosCollegue();
	}

	@ExceptionHandler(value = { CollegueNonTrouveException.class })
	public ResponseEntity<String> colleguePresent(CollegueNonTrouveException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}

	// POST /collegue
	@PostMapping
	public ResponseEntity<Collegue> createCollegue(@RequestBody @Valid Collegue colleguePost) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.collegueService.createCollegue(colleguePost));
	}

	@PatchMapping("{matriculePost}")
	public ResponseEntity<Collegue> updateCollegueParMatricule(@PathVariable String matriculePost,
			@RequestBody Collegue colleguePatch) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(this.collegueService.updateCollegueParMatricule(matriculePost, colleguePatch));
	}

	// requete GET clients?email=XXX , methode executee avec url clients avec
	// parametre
	@GetMapping(params = "email")
	public boolean existCollegue(@RequestParam("email") String emailRequeteHttp) {
		return this.collegueService.existCollegue(emailRequeteHttp);
	}
}
