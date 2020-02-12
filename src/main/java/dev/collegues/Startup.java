package dev.collegues;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.collegues.entites.Collegue;
import dev.collegues.repository.CollegueRepository;

@Component
public class Startup {

	private static final Logger LOG = LoggerFactory.getLogger(Startup.class);

	private CollegueRepository collegueRepository;

	public Startup(CollegueRepository collegueRepository) {
		super();
		this.collegueRepository = collegueRepository;
	}

	@EventListener(ContextRefreshedEvent.class)
	public void init() {
		LOG.info("Démarrage de l'application");

		if (this.collegueRepository.count() == 0) {
			Collegue col1 = new Collegue("M01","LOPEZ", "Antonio","antonio.lopez@societe.com",LocalDate.parse("1974-02-20"),"https://previews.123rf.com/images/kritchanut/kritchanut1405/kritchanut140500053/28162597-homme-ic%C3%B4ne-silhouette-portrait.jpg");
			this.collegueRepository.save(col1);
			
			Collegue col2 = new Collegue("M02","ROLAND", "Fabien","fabien.roland@societe.com",LocalDate.parse("1983-11-24"),"https://previews.123rf.com/images/kritchanut/kritchanut1405/kritchanut140500053/28162597-homme-ic%C3%B4ne-silhouette-portrait.jpg");
			this.collegueRepository.save(col2);
			
			Collegue col3 = new Collegue("M03","LOPEZ", "Julia","julia.lopez@societe.com",LocalDate.parse("1984-03-12"),"https://previews.123rf.com/images/jemastock/jemastock1608/jemastock160812180/61830899-design-plat-femme-sans-visage-portrait-ic%C3%B4ne-illustration-vectorielle.jpg");
			this.collegueRepository.save(col3);
			
		}
	}

}
