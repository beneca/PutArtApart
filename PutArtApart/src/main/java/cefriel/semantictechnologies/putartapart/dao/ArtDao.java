package cefriel.semantictechnologies.putartapart.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.stereotype.Repository;

import cefriel.semantictechnologies.putartapart.dto.Museum;

@Repository
public class ArtDao extends AbstractDao {

	private final static String ENDPOINT_DB_PEDIA = "http://dbpedia.org/sparql";
	
	private final static String QUERY_ARTWORK_BY_CITY = "PREFIX dbo: <http://dbpedia.org/ontology/> "
			+ "PREFIX dbr: <http://dbpedia.org/resource/> "
			+ "PREFIX dbp: <http://dbpedia.org/property/> "
			+ "SELECT ?art WHERE {"
			+ "?art a dbo:Artwork; dbp:city dbr:";
	private final static String CHIUSURA_QUERY_1 = " }";
	
	private final static String QUERY_COORDINATE_BY_ARTWORK = "PREFIX dbo: <http://dbpedia.org/ontology/> "
			+ "PREFIX dbp: <http://dbpedia.org/property/> "
			+ "PREFIX res: <http://dbpedia.org/resource/> "
			+ "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#> "
			+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
			+ "SELECT ?mus ?lg ?lt WHERE { "
			+ "?mus geo:long ?lg. ?mus geo:lat ?lt { "
			+ "SELECT ?mus WHERE {	"
			+ "<http://dbpedia.org/resource/";
	private final static String CHIUSURA_QUERY_2 = "> dbo:museum ?mus . }}}";

	
	public List<String> searchArtworksByCity(String city) {
		LOG.debug("chiamata ArtDao.searchArtworksByCity con città: " + city);		
		List<String> artworks = new ArrayList<>();
		String cityFinal = "";
		
		String[] tokenC = city.split(" ");
		if (tokenC.length == 1) {
			cityFinal = city.substring(0,1).toUpperCase() + city.substring(1,city.length()).toLowerCase();
		} else {
			cityFinal = tokenC[0].substring(0,1).toUpperCase() + tokenC[0].substring(1,tokenC[0].length()).toLowerCase();
			for(int i = 1; i < tokenC.length; i++) {
				cityFinal = cityFinal.concat("_").concat(tokenC[i].substring(0,1).toUpperCase() + tokenC[i].substring(1,tokenC[i].length()).toLowerCase());
			}
		}

		Query query = QueryFactory.create(QUERY_ARTWORK_BY_CITY.concat(cityFinal).concat(CHIUSURA_QUERY_1));
		QueryExecution qexec = QueryExecutionFactory.sparqlService(ENDPOINT_DB_PEDIA, query);
	    ResultSet results = qexec.execSelect();

	    while (results.hasNext()) {
			QuerySolution s = results.nextSolution();
			String artwork = s.get("?art").toString();
			String[] tokenA = artwork.split("/");
			String nomeArt = tokenA[tokenA.length - 1];
			String nomeFinal = nomeArt.replaceAll("_", " ");
			artworks.add(nomeFinal);
		}
		return artworks;
	}
	
	
	public Museum findMuseumByArtwork(String artwork) {
	    LOG.debug("chiamata ArtDao.findMuseumByArtwork con opera d'arte: " + artwork);
	    Museum museum = new Museum();

	    String artworkForQuery = artwork.replaceAll(" ", "_");
	    Query query = QueryFactory.create(QUERY_COORDINATE_BY_ARTWORK.concat(artworkForQuery).concat(CHIUSURA_QUERY_2));
		QueryExecution qexec = QueryExecutionFactory.sparqlService(ENDPOINT_DB_PEDIA, query);
	    ResultSet results = qexec.execSelect();

		QuerySolution s = results.nextSolution();
		
		String museo = s.get("?mus").toString();
		String[] tokenM = museo.split("/");
		String name = tokenM[tokenM.length - 1].replaceAll("_", " ");
		museum.setName(name);
		
		String longitudine = s.get("?lg").toString();
		String[] tokenLong = longitudine.split("\\^");
		museum.setLongitude(Float.valueOf(tokenLong[0]));
		
		String latitudine = s.get("?lt").toString();
		String[] tokenLat = latitudine.split("\\^");
		museum.setLatitude(Float.valueOf(tokenLat[0]));
	   
		return museum;
	}
}