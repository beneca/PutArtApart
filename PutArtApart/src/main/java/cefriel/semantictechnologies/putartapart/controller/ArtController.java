package cefriel.semantictechnologies.putartapart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cefriel.semantictechnologies.putartapart.business.ArtBusinessLogic;
import cefriel.semantictechnologies.putartapart.dto.Museum;

@RestController
@RequestMapping("/art")
public class ArtController extends AbstractRestController {

	protected final static String CITY = "city";
	protected final static String ARTWORK = "artwork";

	@Autowired
	protected ArtBusinessLogic artBusinessLogic;
	
	@GetMapping("/city/{city}")
	public List<String> searchArtworksByCity(@PathVariable(CITY) String city) {
		LOG.debug("chiamata searchArtworksByCity con città: " + city);
		return artBusinessLogic.searchArtworksByCity(city);
	}
	
	@GetMapping("/artwork/{artwork}")
	public Museum findMuseumByArtwork(@PathVariable(ARTWORK) String artwork) {
	    LOG.debug("chiamata findMuseumByArtwork con opera d'arte: " + artwork);
	    return artBusinessLogic.findMuseumByArtwork(artwork);
	}
}