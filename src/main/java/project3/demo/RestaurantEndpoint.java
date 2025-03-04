package project3.demo;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.vocabulary.RDF;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import demo.project3.schema.GetRestaurantRequest;
import demo.project3.schema.GetRestaurantResponse;
import demo.project3.schema.GetRestaurantResponse.Restaurants;

// <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sch="http://project3.demo/schema">
//    <soapenv:Header/>
//    <soapenv:Body>
//       <sch:getRestaurantRequest>
//       </sch:getRestaurantRequest>
//    </soapenv:Body>
// </soapenv:Envelope>

@Endpoint
public class RestaurantEndpoint {

	private static final String NAMESPACE_URI = "http://project3.demo/schema";
	private static final String ONTOLOGY_FILE = "RestaurantOntology_03_12_24.rdf";
	private static final String NS = "http://www.semanticweb.org/acer/ontologies/2567/8/restaurantontologyfinal#";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRestaurantRequest")
	@ResponsePayload
	public GetRestaurantResponse getRestaurant(@RequestPayload GetRestaurantRequest request) {
		GetRestaurantResponse response = new GetRestaurantResponse();

		Model model = RDFDataMgr.loadModel(ONTOLOGY_FILE);
		StmtIterator restauranIterator = model.listStatements(null, RDF.type, model.createResource(NS + "Restaurant"));
		while (restauranIterator.hasNext()) {
			Statement restaurantStmt = restauranIterator.nextStatement();
			Resource restaurantResource = restaurantStmt.getSubject();

			Restaurants restaurant = new Restaurants();

			// Restaurant Name
			Statement restaurantNameStmt = restaurantResource.getProperty(model.createProperty(NS + "RestaurantName"));
			if (restaurantNameStmt != null) {
				restaurant.setRestaurantName(restaurantNameStmt.getString());
			}

			// hasRestaurantNationality
			Property hasRestaurantNationalityProperty = model.createProperty(NS + "hasRestaurantNationality");
			Statement hasRestaurantNationalityStmt = restaurantResource.getProperty(hasRestaurantNationalityProperty);
			if (hasRestaurantNationalityStmt != null) {
				Resource hasRestaurantNationalityResource = hasRestaurantNationalityStmt.getObject().asResource();
				restaurant.setRestaurantNationality(hasRestaurantNationalityResource.getLocalName());
			}

			// hasRestaurantType
			Property hasRestaurantTypeProperty = model.createProperty(NS + "hasRestaurantType");
			Statement hasRestaurantTypeStmt = restaurantResource.getProperty(hasRestaurantTypeProperty);
			if (hasRestaurantTypeStmt != null) {
				Resource hasRestaurantTypeResource = hasRestaurantTypeStmt.getObject().asResource();
				restaurant.setRestaurantType(hasRestaurantTypeResource.getLocalName());
			}

			// hasRestaurantPlace (with district lookup)
			Property hasRestaurantPlaceProperty = model.createProperty(NS + "hasRestaurantPlace");
			Statement hasRestaurantPlaceStmt = restaurantResource.getProperty(hasRestaurantPlaceProperty);
			if (hasRestaurantPlaceStmt != null) {
				Resource hasRestaurantPlaceResource = hasRestaurantPlaceStmt.getObject().asResource();
				Statement districtStmt = hasRestaurantPlaceResource.getProperty(model.createProperty(NS + "District"));
				if (districtStmt != null) {
					restaurant.setDistrict(districtStmt.getString());
				}
			}

			// hasFoodType (with fat, protein, carb lookup)
			Property hasFoodTypeProperty = model.createProperty(NS + "hasFoodType");
			Statement hasFoodTypeStmt = restaurantResource.getProperty(hasFoodTypeProperty);
			if (hasFoodTypeStmt != null) {
				Resource hasFoodTypeResource = hasFoodTypeStmt.getObject().asResource();
				restaurant.setFoodType(hasFoodTypeResource.getLocalName());

				Statement fatStmt = hasFoodTypeResource.getProperty(model.createProperty(NS + "Fat"));
				if (fatStmt != null) {
					restaurant.setFat(fatStmt.getString());
				}

				Statement proteinStmt = hasFoodTypeResource.getProperty(model.createProperty(NS + "Protein"));
				if (proteinStmt != null) {
					restaurant.setProtein(proteinStmt.getString());
				}

				Statement carbStmt = hasFoodTypeResource.getProperty(model.createProperty(NS + "Carbohydrates"));
				if (carbStmt != null) {
					restaurant.setCarbohydrates(carbStmt.getString());
				}
			}

			// Budget handling (min/max logic)
			Property budgetProperty = model.createProperty(NS + "Budget");
			StmtIterator budgetIterator = restaurantResource.listProperties(budgetProperty);

			Double minBudget = null;
			Double maxBudget = null;

			while (budgetIterator.hasNext()) {
				Statement budgetStmt = budgetIterator.nextStatement();
				double budgetValue = Double.parseDouble(budgetStmt.getObject().asLiteral().getString());

				if (minBudget == null || budgetValue < minBudget) {
					minBudget = budgetValue;
				}
				if (maxBudget == null || budgetValue > maxBudget) {
					maxBudget = budgetValue;
				}
			}

			if (minBudget != null) {
				restaurant.setCleanMinBudget(Double.toString(minBudget));
			}
			if (maxBudget != null) {
				restaurant.setCleanMaxBudget(Double.toString(maxBudget));
			}

			response.getRestaurants().add(restaurant);
		}
		return response;
	}
}
