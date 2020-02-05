package dev.collegues.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
