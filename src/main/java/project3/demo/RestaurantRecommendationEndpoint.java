package project3.demo;

import java.io.FileOutputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasonerFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.ReasonerVocabulary;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import demo.project3.schema.GetRestaurantRecommendationRequest;
import demo.project3.schema.GetRestaurantRecommendationResponse;

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

        System.out.println("===== Restaurant Recommendation Request =====");

        System.out.println("Post-Run Carb Consumption: " + request.getPostRunCarbConsumtion());
        System.out.println("Post-Run Fat Consumption: " + request.getPostRunFatConsumtion());
        System.out.println("Post-Run Protein Consumption: " + request.getPostRunProteinConsumtion());

        System.out.println("Pre-Run Carb Consumption: " + request.getPreRunCarbConsumtion());
        System.out.println("Pre-Run Fat Consumption: " + request.getPreRunFatConsumtion());
        System.out.println("Pre-Run Protein Consumption: " + request.getPreRunProteinConsumtion());

        System.out.println("Restaurant Type Interest: " + request.getHasRestaurantTypeInterest());
        System.out.println("Runner Type: " + request.getRunnerType());

        System.out.println("Budget Interests:");
        for (String budget : request.getBudgetInteresets().getBudgetIntereset()) {
            System.out.println("  - " + budget);
        }

        System.out.println("Food Type Interests:");
        for (String foodType : request.getHasFoodTypeInterests().getHasFoodTypeInterest()) {
            System.out.println("  - " + foodType);
        }

        System.out.println("============================================");

        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        model.read(ONTOLOGY_FILE);

        OntProperty PostRunCarbConsumtion = model.getDatatypeProperty(NS + "PostRunCarbConsumtion");
        OntProperty PostRunFatConsumtion = model.getDatatypeProperty(NS + "PostRunFatConsumtion");
        OntProperty PostRunProteinConsumtion = model.getDatatypeProperty(NS + "PostRunProteinConsumtion");

        OntProperty PreRunCarbConsumtion = model.getDatatypeProperty(NS + "PreRunCarbConsumtion");
        OntProperty PreRunFatConsumtion = model.getDatatypeProperty(NS + "PreRunFatConsumtion");
        OntProperty PreRunProteinConsumtion = model.getDatatypeProperty(NS + "PreRunProteinConsumtion");

        OntProperty RunnerType = model.getDatatypeProperty(NS + "RunnerType");

        OntProperty BudgetIntereset = model.getDatatypeProperty(NS + "BudgetInterest");

        OntProperty hasRestaurantTypeInterest = model.getObjectProperty(NS + "hasRestaurantTypeInterest");
        OntProperty hasFoodTypeInterest = model.getObjectProperty(NS + "hasFoodTypeInterest");

        OntClass userClass = model.getOntClass(NS + "User");

        String userProfileName = "userTmpInf";
        String userURI = NS + userProfileName;
        Resource userInstance = model.createResource(userURI);
        userInstance.addProperty(RDF.type, userClass);

        userInstance.addProperty(PostRunCarbConsumtion, request.getPostRunCarbConsumtion());
        userInstance.addProperty(PostRunFatConsumtion, request.getPostRunFatConsumtion());
        userInstance.addProperty(PostRunProteinConsumtion, request.getPostRunProteinConsumtion());

        userInstance.addProperty(PreRunCarbConsumtion, request.getPreRunCarbConsumtion());
        userInstance.addProperty(PreRunFatConsumtion, request.getPreRunFatConsumtion());
        userInstance.addProperty(PreRunProteinConsumtion, request.getPreRunProteinConsumtion());

        userInstance.addProperty(RunnerType, request.getRunnerType());

        for (String budget : request.getBudgetInteresets().getBudgetIntereset()) {
            float budgetFloat = Float.parseFloat(budget);
            Literal budgetLiteral = model.createTypedLiteral(budgetFloat);
            userInstance.addProperty(BudgetIntereset, budgetLiteral);
        }
        Individual restaurantInterest = model.getIndividual(NS + request.getHasRestaurantTypeInterest());
        userInstance.addProperty(hasRestaurantTypeInterest, restaurantInterest);

        for (String foodType : request.getHasFoodTypeInterests().getHasFoodTypeInterest()) {
            Individual foodTypeInterest = model.getIndividual(NS + foodType);
            userInstance.addProperty(hasFoodTypeInterest, foodTypeInterest);
        }
        Model rm = ModelFactory.createDefaultModel();
        Resource configuration = rm.createResource();
        configuration.addProperty(ReasonerVocabulary.PROPruleMode, "hybrid");
        configuration.addProperty(ReasonerVocabulary.PROPruleSet, RULES_FILE);
        Reasoner reasoner = GenericRuleReasonerFactory.theInstance().create(configuration);
        InfModel inf = ModelFactory.createInfModel(reasoner, model);
        // Resource user = inf.getResource(userURI);
        Resource user = inf.getResource(NS + "user3");
        Property p = inf.getProperty(NS, "hasRecommend");
        Property c = inf.getProperty(NS, "confidence");
        StmtIterator i1 = inf.listStatements(user, p, (RDFNode) null);


        while (i1.hasNext()) {
            Statement statement = i1.nextStatement();
            RDFNode statementObj = statement.getObject();
        
            if (statementObj.isResource()) {
                Resource restaurant = statementObj.asResource();
                System.out.println("Recommendation: " + restaurant.getLocalName());
        
                StmtIterator i2 = inf.listStatements(restaurant, c, (RDFNode) null);
                float highestConfidence = 0;
                while (i2.hasNext()) {
                    Statement confidenceStatement = i2.nextStatement();
                    RDFNode confidence = confidenceStatement.getObject();
                    float confidenceValue = confidence.asLiteral().getFloat();
                    if (confidenceValue > highestConfidence) {
                        highestConfidence = confidenceValue;
                    }
                }
                System.out.println("Confidence: " + highestConfidence);

            }
        }


        try (FileOutputStream out = new FileOutputStream("testUser.rdf")) {
            inf.write(out, "RDF/XML");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}