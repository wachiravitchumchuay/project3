package project3.demo;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import demo.project3.schema.CreateUserProfileRequest;
import demo.project3.schema.CreateUserProfileResponse;

public class CreateUserProfileEndpoint {

    private static final String NAMESPACE_URI = "http://project3.demo/schema";

    private static final String RESTAURANT_ONTOLOGY_FILE = "data/RestaurantOntology.rdf";
    private static final String RESTAURANT_NS = "http://www.semanticweb.org/acer/ontologies/2567/8/restaurantontologyfinal#";

    private static final String RUNNING_ONTOLOGY_FILE = "data/RunningEventOntology.rdf";
    private static final String RUNNING_NS = "http://www.semanticweb.org/guind/ontologies/runningeventontology#";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createUserProfileRequest")
    @ResponsePayload
    public CreateUserProfileResponse createUserProfileEndpoint(@RequestPayload CreateUserProfileRequest request) {
        CreateUserProfileResponse response = new CreateUserProfileResponse();

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
        String userProfileName = request.getUsername();
        String userURI = RUNNING_NS + userProfileName;

        Resource existingUser = model.getResource(userURI);
        if (!model.contains(existingUser, RDF.type, userClassRes)
                && !model.contains(existingUser, RDF.type, userClassRun)) {
            Resource userInstance = model.createResource(userURI);
            userInstance.addProperty(RDF.type, userClassRes);
            userInstance.addProperty(RDF.type, userClassRun);

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
            userInstance.addProperty(hasOrganization, organizationInstance);

            Individual raceTypeInstance = model.getIndividual(RUNNING_NS + request.getRaceType());
            userInstance.addProperty(hasRacetype, raceTypeInstance);

            try (FileOutputStream out = new FileOutputStream("src/main/resources/RunningEventOntologyFinal2.rdf")) {
                model.write(out, "RDF/XML");
                System.out.println("Saved user instance to ontology.");
            } catch (IOException e) {
                System.err.println("Error saving ontology: " + e.getMessage());
            }

            response.setStatus("0");
            response.setMessage("Success");
            return response;
        } else {
            response.setStatus("1");
            response.setMessage("User already exist");
            return response;
        }

    }
}
