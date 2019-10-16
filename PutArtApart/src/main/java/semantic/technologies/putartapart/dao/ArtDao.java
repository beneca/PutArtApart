package semantic.technologies.putartapart.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.stereotype.Repository;

import semantic.technologies.putartapart.dto.Museum;

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
			+ "SELECT DISTINCT ?mus ?lg ?lt WHERE { "
			+ "?mus geo:long ?lg. ?mus geo:lat ?lt { "
			+ "SELECT DISTINCT ?mus WHERE {	"
			+ "<http://dbpedia.org/resource/";
	private final static String CHIUSURA_QUERY_2 = "> dbo:museum ?mus . }}}";

	
	public List<String> searchArtworksByCity(String city) {
		LOG.debug("chiamata ArtDao.searchArtworksByCity con città: " + city);		
		List<String> artworks = new ArrayList<>();
		
		city = city.substring(0,1).toUpperCase() + city.substring(1,city.length()).toLowerCase();
		

//		ParameterizedSparqlString queryStr = new ParameterizedSparqlString();
//		queryStr.setNsPrefix("dbo", "http://skunkworks.example.com/redacted#");
//		queryStr.setNsPrefix("dbr", "http://skunkworks.example.com/redacted#");
//		queryStr.setNsPrefix("dbp", "http://skunkworks.example.com/redacted#");
//
//		queryStr.append("SELECT ?a ?b ?c ?d");
//		queryStr.append("{");
//		queryStr.append("   ?rawHit sw:key");
//		queryStr.appendNode(someKey);
//		queryStr.append(".");
//		queryStr.append("  ?rawHit sw:a ?a .");
//		queryStr.append("  ?rawHit sw:b ?b .");
//		queryStr.append("  ?rawHit sw:c ?c . ");
//		queryStr.append("  ?rawHit sw:d ?d .");
//		queryStr.append("} ORDER BY DESC(d)");
//
//		Query q = queryStr.asQuery();
		
		Query query = QueryFactory.create(QUERY_ARTWORK_BY_CITY.concat(city).concat(CHIUSURA_QUERY_1));
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
		museum.setName(tokenM[tokenM.length - 1]);
		
		String longitudine = s.get("?lg").toString();
		String[] tokenLong = longitudine.split("\\^");
		museum.setLongitude(Float.valueOf(tokenLong[0]));
		
		String latitudine = s.get("?lt").toString();
		String[] tokenLat = latitudine.split("\\^");
		museum.setLatitude(Float.valueOf(tokenLat[0]));
	   
		return museum;
	}
}