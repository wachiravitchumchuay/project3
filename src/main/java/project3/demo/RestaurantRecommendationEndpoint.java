package project3.demo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasonerFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.ReasonerVocabulary;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import demo.project3.schema.GetRestaurantRecommendationRequest;
import demo.project3.schema.GetRestaurantRecommendationResponse;
import demo.project3.schema.GetRestaurantRecommendationResponse.Restaurants;

@Endpoint
public class RestaurantRecommendationEndpoint {

    private static final String NAMESPACE_URI = "http://project3.demo/schema";
    private static final String ONTOLOGY_FILE = "RestaurantOntology_03_12_24.rdf";
    private static final String RULES_FILE = "rule.rules";
    private static final String NS = "http://www.semanticweb.org/acer/ontologies/2567/8/restaurantontologyfinal#";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRestaurantRecommendationRequest")
    @ResponsePayload
    public GetRestaurantRecommendationResponse getRestaurantRecommendation(
            @RequestPayload GetRestaurantRecommendationRequest request) {
        GetRestaurantRecommendationResponse response = new GetRestaurantRecommendationResponse();

        // Load ontology into memory
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        InputStream in = FileManager.getInternal().open(ONTOLOGY_FILE);
        if (in == null) {
            throw new IllegalArgumentException("File: " + ONTOLOGY_FILE + " not found");
        }
        model.read(in, null);

        // Create user instance with necessary properties
        String userURI = NS + "User_temp";
        Resource user = model.createResource(userURI);
        user.addProperty(RDF.type, model.createResource(userURI));
        user.addProperty(model.createProperty(NS, "Username"), "User_temp");
        user.addProperty(model.createProperty(NS, "PreRunProteinConsumtion"), request.getPreRunProteinConsumtion());
        user.addProperty(model.createProperty(NS, "PostRunProteinConsumtion"), request.getPostRunProteinConsumtion());
        user.addProperty(model.createProperty(NS, "PostRunFatConsumtion"), request.getPostRunFatConsumtion());
        user.addProperty(model.createProperty(NS, "PostRunCarbConsumtion"), request.getPostRunCarbConsumtion());
        user.addProperty(model.createProperty(NS, "RunnerType"), request.getRunnerType());
        user.addProperty(model.createProperty(NS, "hasRestaurantTypeInterest"), model.createResource(NS + request.getRestaurantTypeInterest()));

        // Apply rules to the model
        Model ruleModel = ModelFactory.createDefaultModel();
        Resource configuration = ruleModel.createResource();
        configuration.addProperty(ReasonerVocabulary.PROPruleMode, "hybrid");
        configuration.addProperty(ReasonerVocabulary.PROPruleSet, RULES_FILE);
        Reasoner reasoner = GenericRuleReasonerFactory.theInstance().create(configuration);
        Model infModel = ModelFactory.createInfModel(reasoner, model);

        // Query the inference model for recommendations
        Property hasRecommend = model.getProperty(NS, "hasRecommend");
        Property confidence = model.getProperty(NS, "confidence");
        Property restaurantName = model.getProperty(NS, "RestaurantName");

        if (hasRecommend == null || confidence == null || restaurantName == null) {
            throw new IllegalArgumentException("One or more properties not found in the ontology");
        }

        StmtIterator iterator = infModel.listStatements(user, hasRecommend, (RDFNode) null);
        List<Restaurants> restaurantList = new ArrayList<>();
        while (iterator.hasNext()) {
            Statement stmt = iterator.nextStatement();
            Resource recommendedRestaurant = stmt.getObject().asResource();
            String name = recommendedRestaurant.getProperty(restaurantName).getString();
            String conf = recommendedRestaurant.getProperty(confidence).getString();

            Restaurants restaurant = new Restaurants();
            restaurant.setRestaurantName(name);
            restaurant.setConfidence(conf);
            restaurantList.add(restaurant);
        }
        response.getRestaurants().addAll(restaurantList);

        return response;
    }
}
