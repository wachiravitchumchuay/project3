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
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.rulesys.GenericRuleReasonerFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.ReasonerVocabulary;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import demo.project3.schema.GetAllRecommendationRequest;
import demo.project3.schema.GetAllRecommendationResponse;

@Endpoint
public class AllRecommendation {

    private static final String NAMESPACE_URI = "http://project3.demo/schema";

    private static final String RESTAURANT_ONTOLOGY_FILE = "RestaurantOntology_03_12_24.rdf";
    private static final String RESTAURANT_RULES_FILE = "rule.rules";
    private static final String RESTAURANT_NS = "http://www.semanticweb.org/acer/ontologies/2567/8/restaurantontologyfinal#";

    private static final String RUNNING_ONTOLOGY_FILE = "RunningEventOntologyFinal2.rdf"; // include travel place
    private static final String RUNNING_RULES_FILE = "testrules1.rules";
    private static final String RUNNING_NS = "http://www.semanticweb.org/guind/ontologies/runningeventontology#";

    private static final String TRAVELPLACE_RULES_FILE = "travelPlaceRule.rules";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllRecommendationRequest")
    @ResponsePayload
    public GetAllRecommendationResponse test(@RequestPayload GetAllRecommendationRequest request) {
        GetAllRecommendationResponse response = new GetAllRecommendationResponse();
        System.out.println("Begin");

        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        model.read(RESTAURANT_ONTOLOGY_FILE);
        model.read(RUNNING_ONTOLOGY_FILE);

        OntProperty PostRunCarbConsumtion = model.getDatatypeProperty(RESTAURANT_NS + "PostRunCarbConsumtion");
        OntProperty PostRunFatConsumtion = model.getDatatypeProperty(RESTAURANT_NS + "PostRunFatConsumtion");
        OntProperty PostRunProteinConsumtion = model.getDatatypeProperty(RESTAURANT_NS + "PostRunProteinConsumtion");

        OntProperty PreRunCarbConsumtion = model.getDatatypeProperty(RESTAURANT_NS + "PreRunCarbConsumtion");
        OntProperty PreRunFatConsumtion = model.getDatatypeProperty(RESTAURANT_NS + "PreRunFatConsumtion");
        OntProperty PreRunProteinConsumtion = model.getDatatypeProperty(RESTAURANT_NS + "PreRunProteinConsumtion");

        OntProperty RunnerType = model.getDatatypeProperty(RESTAURANT_NS + "RunnerType");

        OntProperty BudgetIntereset = model.getDatatypeProperty(RESTAURANT_NS + "BudgetInterest");

        OntProperty hasRestaurantTypeInterest = model.getObjectProperty(RESTAURANT_NS + "hasRestaurantTypeInterest");
        OntProperty hasFoodTypeInterest = model.getObjectProperty(RESTAURANT_NS + "hasFoodTypeInterest");

        OntProperty ActivityArea = model.getDatatypeProperty(RUNNING_NS + "ActivityAreaInterest");
        OntProperty StartPeriod = model.getDatatypeProperty(RUNNING_NS + "StartPeriodInterest");
        OntProperty Reward = model.getDatatypeProperty(RUNNING_NS + "RewardInterest");
        OntProperty Location = model.getDatatypeProperty(RUNNING_NS + "LocationInterest");
        OntProperty TypeOfEvent = model.getDatatypeProperty(RUNNING_NS + "TypeOfEventInterest");
        OntProperty EventPrice = model.getDatatypeProperty(RUNNING_NS + "EventPriceInterest");
        OntProperty LevelEvent = model.getDatatypeProperty(RUNNING_NS + "LevelEventInterest");
        OntProperty StandardEvent = model.getDatatypeProperty(RUNNING_NS + "StandardEventInterest");
        OntProperty PlaceType = model.getDatatypeProperty(RUNNING_NS + "TravelPlaceTypeInterest");
        OntProperty hasRacetype = model.getObjectProperty(RUNNING_NS + "hasRaceTypeInterest");
        OntProperty hasOrganization = model.getObjectProperty(RUNNING_NS + "hasOrganizationInterest");

        OntClass userClassRes = model.getOntClass(RESTAURANT_NS + "User");
        OntClass userClassRun = model.getOntClass(RUNNING_NS + "User");
        String userProfileName = "userTmpInf";
        String userURI = RUNNING_NS + userProfileName;
        Resource userInstance = model.createResource(userURI);
        userInstance.addProperty(RDF.type, userClassRes);
        userInstance.addProperty(RDF.type, userClassRun);

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
        Individual restaurantInterest = model.getIndividual(RESTAURANT_NS + request.getHasRestaurantTypeInterest());
        userInstance.addProperty(hasRestaurantTypeInterest, restaurantInterest);

        for (String foodType : request.getHasFoodTypeInterests().getHasFoodTypeInterest()) {
            Individual foodTypeInterest = model.getIndividual(RESTAURANT_NS + foodType);
            userInstance.addProperty(hasFoodTypeInterest, foodTypeInterest);
        }

        userInstance.addProperty(PlaceType, request.getTravelPlaceType());
        userInstance.addProperty(Location, request.getDistrict());
        userInstance.addProperty(TypeOfEvent, request.getTypeofEvent());
        userInstance.addProperty(EventPrice, request.getPrice());
        userInstance.addProperty(ActivityArea, request.getActivityArea());
        userInstance.addProperty(StandardEvent, request.getStandard());
        userInstance.addProperty(LevelEvent, request.getLevel());
        userInstance.addProperty(StartPeriod, request.getStartPeriod());
        userInstance.addProperty(Reward, request.getReward());

        Individual organizationInstance = model.getIndividual(RUNNING_NS + request.getOrganization());
        userInstance.addProperty(hasOrganization, organizationInstance); // 

        System.out.println("1.1");
        Individual raceTypeInstance = model.getIndividual(RUNNING_NS + request.getRaceType());
        System.out.println("1.2");
        userInstance.addProperty(hasRacetype, raceTypeInstance);
        
        System.out.println("2");
        Model rm = ModelFactory.createDefaultModel();
        Resource configuration = rm.createResource();
        configuration.addProperty(ReasonerVocabulary.PROPruleMode, "hybrid");
        configuration.addProperty(ReasonerVocabulary.PROPruleSet, RUNNING_RULES_FILE);
        configuration.addProperty(ReasonerVocabulary.PROPruleSet, TRAVELPLACE_RULES_FILE);
        configuration.addProperty(ReasonerVocabulary.PROPruleSet, RESTAURANT_RULES_FILE);
        Reasoner reasoner = GenericRuleReasonerFactory.theInstance().create(configuration);
        InfModel inf = ModelFactory.createInfModel(reasoner, model);
        System.out.println("3");
        try (FileOutputStream out = new FileOutputStream("testInf.rdf")) {
            inf.write(out, "RDF/XML");
            System.out.println("Save");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("End");
        return response;
    }
}
