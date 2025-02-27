package project3.demo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import demo.project3.schema.GetRestaurantRequest;
import demo.project3.schema.GetRestaurantResponse;
import demo.project3.schema.GetRestaurantResponse.Restaurants;

@Endpoint
public class RestaurantEndpoint {

	private static final String NAMESPACE_URI = "http://project3.demo/schema";
	private static final String ONTOLOGY_FILE = "RestaurantOntology_03_12_24.rdf";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRestaurantRequest")
	@ResponsePayload
	public GetRestaurantResponse getRestaurant(@RequestPayload GetRestaurantRequest request) {
		GetRestaurantResponse response = new GetRestaurantResponse();
		Model model = ModelFactory.createDefaultModel();
		InputStream in = FileManager.getInternal().open(ONTOLOGY_FILE);
		if (in == null) {
			throw new IllegalArgumentException("File: " + ONTOLOGY_FILE + " not found");
		}
		model.read(in, null);

		String queryString = """
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX re: <http://www.semanticweb.org/acer/ontologies/2567/8/restaurantontologyfinal#>

SELECT ?resName
WHERE {  
    ?subject rdf:type re:Restaurant .
    ?subject re:RestaurantName ?resName .
}
""";
		System.out.println("Query: " + queryString);
		Query query = QueryFactory.create(queryString);
		try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
			ResultSet results = qexec.execSelect();
			List<Restaurants> restaurantList = new ArrayList<>();
			while (results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				Literal nameLiteral = soln.getLiteral("resName");
				String name = nameLiteral != null ? nameLiteral.getString() : "Unknown";
				Restaurants restaurant = new Restaurants();
				restaurant.setRestaurantName(name);
				restaurantList.add(restaurant);
			}
			response.getRestaurants().addAll(restaurantList);
		}
		return response;
	}
}
