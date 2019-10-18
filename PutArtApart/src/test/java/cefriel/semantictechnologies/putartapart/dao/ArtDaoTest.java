package cefriel.semantictechnologies.putartapart.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cefriel.semantictechnologies.putartapart.configurer.AbstractTest;
import cefriel.semantictechnologies.putartapart.dto.Museum;

public class ArtDaoTest extends AbstractTest {

	private final static String PARIS = "paris";
	private final static String LOUVRE = "Louvre";
	private final static String MONALISA = "Mona Lisa";
	
	@Autowired
	private ArtDao artDao;
	
	@Test
	public void testSearchArtworksByCity() {
		List<String> artworks = artDao.searchArtworksByCity(PARIS);
		assertTrue(artworks.contains(MONALISA));
		// no ordine alfabetico
		assertTrue(!"A".equals(artworks.get(0).substring(0,1)));
	}
	
	@Test
	public void testFindMuseumByArtwork() {
		Museum museo = artDao.findMuseumByArtwork(MONALISA);
		assertEquals(LOUVRE, museo.getName());
		assertEquals(48, museo.getLatitude().intValue());
		assertEquals(2, museo.getLongitude().intValue());
	}
}