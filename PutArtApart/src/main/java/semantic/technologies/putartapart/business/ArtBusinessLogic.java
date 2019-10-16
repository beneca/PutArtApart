package semantic.technologies.putartapart.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import semantic.technologies.putartapart.dao.ArtDao;
import semantic.technologies.putartapart.dto.Museum;

@Service
public class ArtBusinessLogic extends AbstractBusinessLogic {

	@Autowired
	private ArtDao artDao;
	
	public List<String> searchArtworksByCity(String city) {
		LOG.debug("chiamata ArtBusinessLogic.searchArtworksByCity con città: " + city);
		List<String> lista = new ArrayList<>();
		lista = artDao.searchArtworksByCity(city);
		Collections.sort(lista);
		return lista;
	}
	
	public Museum findMuseumByArtwork(String artwork) {
	    LOG.debug("chiamata ArtBusinessLogic.findMuseumByArtwork con opera d'arte: " + artwork);
	    return artDao.findMuseumByArtwork(artwork);
	}
}