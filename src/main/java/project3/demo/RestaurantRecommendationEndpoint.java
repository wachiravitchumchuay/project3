package project3.demo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
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

        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        InputStream in = FileManager.getInternal().open(ONTOLOGY_FILE);
        if (in == null) {
            throw new IllegalArgumentException("File: " + ONTOLOGY_FILE + " not found");
        }
        model.read(in, null);
        OntProperty PostRunProteinConsumtion = model.getDatatypeProperty(NS + "PostRunProteinConsumtion");
        OntProperty PostRunFatConsumtion = model.getDatatypeProperty(NS + "PostRunFatConsumtion");
        OntProperty PostRunCarbConsumtion = model.getDatatypeProperty(NS + "PostRunCarbConsumtion");

        OntProperty RunnerType = model.getDatatypeProperty(NS + "RunnerType");

        OntProperty hasRestaurantTypeInterest = model.getObjectProperty(NS + "hasRestaurantTypeInterest");
        OntClass RestaurantTypeClass = model.getOntClass(NS + "RestaurantType");
        OntClass Casual_DiningClass = model.getOntClass(NS + "Casual_Dining");
        OntClass Fast_DiningClass = model.getOntClass(NS + "Fast_Dining");
        OntClass Fine_DiningClass = model.getOntClass(NS + "Fine_Dining");
        OntClass KioskClass = model.getOntClass(NS + "Kiosk");

        OntClass FoodTypeClass = model.getOntClass(NS + "FoodType");

        OntClass ALaCarteClass = model.getOntClass(NS + "ALaCarte");
        OntClass Bakery_CakeClass = model.getOntClass(NS + "Bakery_Cake");
        OntClass BreakfastClass = model.getOntClass(NS + "Breakfast");
        OntClass BubbleMilkTeaClass = model.getOntClass(NS + "BubbleMilkTea");
        OntClass BuffetClass = model.getOntClass(NS + "Buffet");
        OntClass BurgerClass = model.getOntClass(NS + "Burger");
        OntClass CleanFood_SaladClass = model.getOntClass(NS + "CleanFood_Salad");
        OntClass DessertClass = model.getOntClass(NS + "Dessert");
        OntClass DimsumClass = model.getOntClass(NS + "Dimsum");
        OntClass DrinksJuiceClass = model.getOntClass(NS + "DrinksJuice");
        OntClass FastFoodClass = model.getOntClass(NS + "FastFood");
        OntClass GrillClass = model.getOntClass(NS + "Grill");
        OntClass GrilledPorkClass = model.getOntClass(NS + "GrilledPork");
        OntClass IceCreamClass = model.getOntClass(NS + "IceCream");
        OntClass NoodlesClass = model.getOntClass(NS + "Noodles");
        OntClass OmakaseClass = model.getOntClass(NS + "Omakase");
        OntClass OneDishMealClass = model.getOntClass(NS + "OneDishMeal");
        OntClass PizzaClass = model.getOntClass(NS + "Pizza");
        OntClass RamenClass = model.getOntClass(NS + "Ramen");
        OntClass SeafoodClass = model.getOntClass(NS + "Seafood");
        OntClass Shabu_SukiyakiClass = model.getOntClass(NS + "Shabu_Sukiyaki");
        OntClass SteakClass = model.getOntClass(NS + "Steak");
        OntClass SushiClass = model.getOntClass(NS + "Sushi");
        OntClass Vegetarian_JayClass = model.getOntClass(NS + "Vegetarian_Jay");
        OntClass VegetarianFoodClass = model.getOntClass(NS + "VegetarianFood");

        OntProperty Protein = model.getDatatypeProperty(NS + "Protein");
        OntProperty Fat = model.getDatatypeProperty(NS + "Fat");
        OntProperty Carbohydrates = model.getDatatypeProperty(NS + "Carbohydrates");

        OntProperty hasRestaurantType = model.getObjectProperty(NS + "hasRestaurantType");

        OntProperty confidence = model.getDatatypeProperty(NS + "confidence");
        OntProperty hasRecommend = model.getObjectProperty(NS + "hasRecommend");

        OntClass userClass = model.getOntClass(NS + "User");

        String userProfileName = "tempUserInf";
        String userURI = NS + userProfileName;
        Resource user = model.createResource(userURI);
        user.addProperty(RDF.type, userClass);
        user.addProperty(null, userProfileName);

        Model ruleModel = ModelFactory.createDefaultModel();
        Resource configuration = ruleModel.createResource();
        configuration.addProperty(ReasonerVocabulary.PROPruleMode, "hybrid");
        configuration.addProperty(ReasonerVocabulary.PROPruleSet, RULES_FILE);
        Reasoner reasoner = GenericRuleReasonerFactory.theInstance().create(configuration);
        Model infModel = ModelFactory.createInfModel(reasoner, model);


        StmtIterator iterator = infModel.listStatements(user, hasRecommend, (RDFNode) null);
        while (iterator.hasNext()) {
            Statement stmt = iterator.nextStatement();
            Resource recommendedRestaurant = stmt.getObject().asResource();
            String restaurantName = recommendedRestaurant
                    .getProperty(model.createProperty(NAMESPACE_URI, "RestaurantName")).getString();
            System.out.println("User has recommendation: " + restaurantName);
        }

        try {
            infModel.write(new PrintWriter(new FileOutputStream("InferenceModel.rdf")), "RDF/XML");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        return response;
    }
}

