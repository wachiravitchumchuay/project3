package project3.demo;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.RDF;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import demo.project3.schema.GetUserProfileRequest;
import demo.project3.schema.GetUserProfileResponse;
import demo.project3.schema.GetUserProfileResponse.BudgetInterests;
import demo.project3.schema.GetUserProfileResponse.HasFoodTypeInterests;

@Endpoint
public class GetUserProfileEndpoint {

    private static final String NAMESPACE_URI = "http://project3.demo/schema";

    private static final String RESTAURANT_ONTOLOGY_FILE = "data/RestaurantOntology.rdf";
    private static final String RESTAURANT_NS = "http://www.semanticweb.org/acer/ontologies/2567/8/restaurantontologyfinal#";

    private static final String RUNNING_ONTOLOGY_FILE = "data/RunningEventOntology.rdf";
    private static final String RUNNING_NS = "http://www.semanticweb.org/guind/ontologies/runningeventontology#";

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserProfileRequest")
    @ResponsePayload
    public GetUserProfileResponse getUserProfileEndpoint(@RequestPayload GetUserProfileRequest request) {
        GetUserProfileResponse response = new GetUserProfileResponse();

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

        OntProperty BudgetInterest = model.getDatatypeProperty(RESTAURANT_NS + "BudgetInterest");

        OntProperty hasRestaurantTypeInterest = model.getObjectProperty(RESTAURANT_NS + "hasRestaurantTypeInterest");
        OntProperty hasFoodTypeInterest = model.getObjectProperty(RESTAURANT_NS + "hasFoodTypeInterest");
        OntProperty hasRacetype = model.getObjectProperty(RUNNING_NS + "hasRaceTypeInterest"); //
        OntProperty hasOrganization = model.getObjectProperty(RUNNING_NS + "hasOrganizationInterest"); //

        OntProperty ActivityArea = model.getDatatypeProperty(RUNNING_NS + "ActivityAreaInterest");
        OntProperty StartPeriod = model.getDatatypeProperty(RUNNING_NS + "StartPeriodInterest");
        OntProperty Reward = model.getDatatypeProperty(RUNNING_NS + "RewardInterest");
        OntProperty Location = model.getDatatypeProperty(RUNNING_NS + "LocationInterest");
        OntProperty TypeOfEvent = model.getDatatypeProperty(RUNNING_NS + "TypeOfEventInterest");
        OntProperty EventPrice = model.getDatatypeProperty(RUNNING_NS + "EventPriceInterest");
        OntProperty LevelEvent = model.getDatatypeProperty(RUNNING_NS + "LevelEventInterest");
        OntProperty StandardEvent = model.getDatatypeProperty(RUNNING_NS + "StandardEventInterest");
        OntProperty PlaceType = model.getDatatypeProperty(RUNNING_NS + "TravelPlaceTypeInterest");

        OntProperty Username = model.getDatatypeProperty(RUNNING_NS + "Username");
        OntProperty Password = model.getDatatypeProperty(RUNNING_NS + "Password");

        OntClass userClassRes = model.getOntClass(RESTAURANT_NS + "User");
        OntClass userClassRun = model.getOntClass(RUNNING_NS + "User");
        String userProfileName = request.getUsername();
        String userURI = RUNNING_NS + userProfileName;

        Resource existingUser = model.getResource(userURI);
        if (model.contains(existingUser, RDF.type, userClassRes)
                && model.contains(existingUser, RDF.type, userClassRun)) {

            Resource user = model.getResource(userURI);

            String username = user.getProperty(Username).getString();
            String district = user.getProperty(Location).getString();

            String typeofEvent = user.getProperty(TypeOfEvent).getString();
            String travelPlaceType = user.getProperty(PlaceType).getString();
            String price = user.getProperty(EventPrice).getString();

            String activityArea = user.getProperty(ActivityArea).getString();
            String standard = user.getProperty(StandardEvent).getString();
            String level = user.getProperty(LevelEvent).getString();
            String startPeriod = user.getProperty(StartPeriod).getString();
            String reward = user.getProperty(Reward).getString();

            String postRunCarbConsumtion = user.getProperty(PostRunCarbConsumtion).getString();
            String postRunFatConsumtion = user.getProperty(PostRunFatConsumtion).getString();
            String postRunProteinConsumtion = user.getProperty(PostRunProteinConsumtion).getString();
            String preRunCarbConsumtion = user.getProperty(PreRunCarbConsumtion).getString();
            String preRunFatConsumtion = user.getProperty(PreRunFatConsumtion).getString();
            String preRunProteinConsumtion = user.getProperty(PreRunProteinConsumtion).getString();
            String runnerType = user.getProperty(RunnerType).getString();

            Resource restaurantTypeResource = user.getPropertyResourceValue(hasRestaurantTypeInterest);
            String restaurantTypeInterest = restaurantTypeResource.getLocalName();
            Resource hasRacetypeResource = user.getPropertyResourceValue(hasRacetype);
            String raceType = hasRacetypeResource.getLocalName();

            Resource organizationResource = user.getPropertyResourceValue(hasOrganization);
            String organization = organizationResource.getLocalName();

            StmtIterator budgets = user.listProperties(BudgetInterest);
            BudgetInterests budgetInterestRes = new BudgetInterests();
            while (budgets.hasNext()) {
                String budget = budgets.nextStatement().getString();
                budgetInterestRes.getBudgetInterest().add(budget);
            }

            StmtIterator foodTypes = user.listProperties(hasFoodTypeInterest);
            HasFoodTypeInterests foodTypeRes = new HasFoodTypeInterests();
            while (foodTypes.hasNext()) {
                Statement foodTypeStmt = foodTypes.nextStatement();
                Resource foodType = foodTypeStmt.getObject().asResource();
                String type = foodType.getLocalName();
                foodTypeRes.getHasFoodTypeInterest().add(type);
            }

            response.setBudgetInterests(budgetInterestRes);
            response.setHasFoodTypeInterests(foodTypeRes);

            response.setUsername(username);
            response.setDistrict(district);
            response.setRaceType(raceType);
            response.setTypeofEvent(typeofEvent);
            response.setTravelPlaceType(travelPlaceType);
            response.setPrice(price);
            response.setOrganization(organization);
            response.setActivityArea(activityArea);
            response.setStandard(standard);
            response.setLevel(level);
            response.setStartPeriod(startPeriod);
            response.setReward(reward);

            response.setPostRunCarbConsumtion(postRunCarbConsumtion);
            response.setPostRunFatConsumtion(postRunFatConsumtion);
            response.setPostRunProteinConsumtion(postRunProteinConsumtion);
            response.setPreRunCarbConsumtion(preRunCarbConsumtion);
            response.setPreRunFatConsumtion(preRunFatConsumtion);
            response.setPreRunProteinConsumtion(preRunProteinConsumtion);
            response.setHasRestaurantTypeInterest(restaurantTypeInterest);
            response.setRunnerType(runnerType);

            String hashedPassword = user.getProperty(Password).getString();
            String plainPassword = request.getPassword();

            if (encoder.matches(plainPassword, hashedPassword)) {
                response.setStatus("0");
                response.setMessage("Success");
                return response;
            } else {
                response.setStatus("1");
                response.setMessage("Wrong password");
                return response;
            }
        } else {
            response.setStatus("1");
            response.setMessage("User not exist");
            return response;
        }

    }

}
