package project3.demo;

import java.io.FileOutputStream;
import java.util.Arrays;

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

import demo.project3.schema.GetAllRecommendationRequest;
import demo.project3.schema.GetAllRecommendationResponse;
import demo.project3.schema.GetAllRecommendationResponse.Restaurants;
import demo.project3.schema.GetAllRecommendationResponse.RunningEvents;
import demo.project3.schema.GetAllRecommendationResponse.TravelPlaces;



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
        System.out.println("Begin getAllRecommendationRequest");

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


        Individual raceTypeInstance = model.getIndividual(RUNNING_NS + request.getRaceType());

        userInstance.addProperty(hasRacetype, raceTypeInstance);
        
        Model rm = ModelFactory.createDefaultModel();
        Resource configuration = rm.createResource();
        configuration.addProperty(ReasonerVocabulary.PROPruleMode, "hybrid");
        configuration.addProperty(ReasonerVocabulary.PROPruleSet, RUNNING_RULES_FILE);
        configuration.addProperty(ReasonerVocabulary.PROPruleSet, TRAVELPLACE_RULES_FILE);
        configuration.addProperty(ReasonerVocabulary.PROPruleSet, RESTAURANT_RULES_FILE);
        Reasoner reasoner = GenericRuleReasonerFactory.theInstance().create(configuration);
        InfModel inf = ModelFactory.createInfModel(reasoner, model);

        Resource user = inf.getResource(userURI);
        Property p = inf.getProperty(RESTAURANT_NS, "hasRecommend");
        StmtIterator i1 = inf.listStatements(user, p, (RDFNode) null);

        Property p2 = inf.getProperty(RUNNING_NS, "hasRecommend");
        StmtIterator i3 = inf.listStatements(user, p2, (RDFNode) null);

        Property p3 = inf.getProperty(RUNNING_NS, "hasTravelPlaceRecommend");
        StmtIterator i5 = inf.listStatements(user, p3, (RDFNode) null);
        //RESTAURANT
        while (i1.hasNext()) {
            
            Statement statement = i1.nextStatement();
            RDFNode statementObj = statement.getObject();
            Restaurants restaurantRes = new Restaurants();
            if (statementObj.isResource()) {
                Resource restaurant = statementObj.asResource();
        
                Statement restaurantNameStmt = restaurant.getProperty(inf.createProperty(RESTAURANT_NS + "RestaurantName"));
                String restaurantName = "";
                if (restaurantNameStmt != null) {
                    restaurantName = restaurantNameStmt.getString();
                }
                restaurantRes.setRestaurantName(restaurantName);
        
                Property hasRestaurantNationality = inf.createProperty(RESTAURANT_NS + "hasRestaurantNationality");
                Statement nationalityStmt = restaurant.getProperty(hasRestaurantNationality);
                String restaurantNationality = "";
                if (nationalityStmt != null && nationalityStmt.getObject().isResource()) {
                    restaurantNationality = nationalityStmt.getObject().asResource().getLocalName();
                }
                restaurantRes.setRestaurantNationality(restaurantNationality);
        
                Property hasRestaurantType = inf.createProperty(RESTAURANT_NS + "hasRestaurantType");
                Statement typeStmt = restaurant.getProperty(hasRestaurantType);
                String restaurantType = "";
                if (typeStmt != null && typeStmt.getObject().isResource()) {
                    restaurantType = typeStmt.getObject().asResource().getLocalName();
                }
                restaurantRes.setRestaurantType(restaurantType);
        
                Property hasRestaurantPlace = inf.createProperty(RESTAURANT_NS + "hasRestaurantPlace");
                Statement placeStmt = restaurant.getProperty(hasRestaurantPlace);
                String district = "";
                if (placeStmt != null && placeStmt.getObject().isResource()) {
                    Resource placeResource = placeStmt.getObject().asResource();
                    Statement districtStmt = placeResource.getProperty(inf.createProperty(RESTAURANT_NS + "District"));
                    if (districtStmt != null) {
                        district = districtStmt.getString();
                    }
                }
                restaurantRes.setDistrict(district);
        
                Property hasFoodType = inf.createProperty(RESTAURANT_NS + "hasFoodType");
                Statement foodTypeStmt = restaurant.getProperty(hasFoodType);
                String foodType = "";
                String fat = "";
                String protein = "";
                String carbohydrates = "";
        
                if (foodTypeStmt != null && foodTypeStmt.getObject().isResource()) {
                    Resource foodTypeResource = foodTypeStmt.getObject().asResource();
                    foodType = foodTypeResource.getLocalName();
        
                    Statement fatStmt = foodTypeResource.getProperty(inf.createProperty(RESTAURANT_NS + "Fat"));
                    if (fatStmt != null) {
                        fat = fatStmt.getString();
                    }
        
                    Statement proteinStmt = foodTypeResource.getProperty(inf.createProperty(RESTAURANT_NS + "Protein"));
                    if (proteinStmt != null) {
                        protein = proteinStmt.getString();
                    }
        
                    Statement carbStmt = foodTypeResource.getProperty(inf.createProperty(RESTAURANT_NS + "Carbohydrates"));
                    if (carbStmt != null) {
                        carbohydrates = carbStmt.getString();
                    }
                }
                restaurantRes.setFoodType(foodType);
                restaurantRes.setFat(fat);
                restaurantRes.setProtein(protein);
                restaurantRes.setCarbohydrates(carbohydrates);
        
                Property budgetProperty = inf.createProperty(RESTAURANT_NS + "Budget");
                StmtIterator budgetIterator = restaurant.listProperties(budgetProperty);
        
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
        
                String cleanMinBudget = minBudget != null ? minBudget.toString() : "";
                String cleanMaxBudget = maxBudget != null ? maxBudget.toString() : "";
        
                restaurantRes.setCleanMinBudget(cleanMinBudget);
                restaurantRes.setCleanMaxBudget(cleanMaxBudget);
        
                // float highestConfidence = 0;
                // StmtIterator i2 = inf.listStatements(restaurant, inf.createProperty(RESTAURANT_NS + "confidence"), (RDFNode) null);
                // while (i2.hasNext()) {
                //     Statement confidenceStatement = i2.nextStatement();
                //     RDFNode confidence = confidenceStatement.getObject();
                //     float confidenceValue = confidence.asLiteral().getFloat();
                //     if (confidenceValue > highestConfidence) {
                //         highestConfidence = confidenceValue;
                //     }
                // }
                // restaurantRes.setConfidence(String.valueOf(highestConfidence));

                                int[] rulePriorities = new int[24];
                Arrays.fill(rulePriorities, Integer.MAX_VALUE);
                // rule No         = priority level 1 is the highest 
                //rulePriorities[0] start from 1
                rulePriorities[1]  = 4; // FoodType
                rulePriorities[2]  = 4;
                rulePriorities[3]  = 4;
                rulePriorities[4]  = 3; // RunnerType 
                rulePriorities[5]  = 3;
                rulePriorities[6]  = 2; // hasRestaurantTypeInterest
                rulePriorities[7]  = 3; 
                rulePriorities[8]  = 2;
                rulePriorities[9]  = 4;
                rulePriorities[10] = 3;
                rulePriorities[11] = 4;
                rulePriorities[12] = 3;  
                rulePriorities[13] = 2;
                rulePriorities[14] = 4;
                rulePriorities[15] = 3; 
                rulePriorities[16] = 4;
                rulePriorities[17] = 1; // hasRestaurantTypeInterest + RunnerType 
                rulePriorities[18] = 3;
                rulePriorities[19] = 3;
                rulePriorities[20] = 4;
                rulePriorities[21] = 2;
                rulePriorities[22] = 4;
                rulePriorities[23] = 3; 

                int bestConfidence = 0;
                int bestPriority = Integer.MAX_VALUE;

                StmtIterator i2 = inf.listStatements(restaurant, inf.createProperty(RESTAURANT_NS + "confidence"), (RDFNode) null);
                while (i2.hasNext()) {
                    Statement confidenceStatement = i2.nextStatement();
                    RDFNode confidence = confidenceStatement.getObject();
                    String rawConfidence = confidence.asLiteral().getString();

                    if (rawConfidence.contains(".")) {
                        String[] parts = rawConfidence.split("\\.");
                            int confVal = Integer.parseInt(parts[0]);
                            int ruleId = Integer.parseInt(parts[1]);

                            int priority = (ruleId < rulePriorities.length) ? rulePriorities[ruleId] : Integer.MAX_VALUE;

                            if (priority < bestPriority || (priority == bestPriority && confVal > bestConfidence)) {
                                bestPriority = priority;
                                bestConfidence = confVal;
                            }

                    }
                }
                restaurantRes.setConfidence(String.valueOf(bestConfidence));

                response.getRestaurants().add(restaurantRes);
            }
            
        }
        //RUNNING
        while (i3.hasNext()) {
            
            Statement statement = i3.nextStatement();
            RDFNode statementObj = statement.getObject();
            RunningEvents runningEventRes = new RunningEvents();
            if (statementObj.isResource()) {
                Resource runningEvent = statementObj.asResource();
        
                Statement runningEventNameStmt = runningEvent.getProperty(inf.createProperty(RUNNING_NS + "RunningEventName"));
                String runningEventName = "";
                if (runningEventNameStmt != null) {
                    runningEventName = runningEventNameStmt.getString();
                }
                runningEventRes.setRunningEventName(runningEventName);
        
                Statement venueStmt = runningEvent.getProperty(inf.createProperty(RUNNING_NS + "hasEventVenue"));
                String district = "";
                if (venueStmt != null) {
                    Resource venue = venueStmt.getResource();
                    Statement districtStmt = venue.getProperty(inf.createProperty(RUNNING_NS + "District"));
                    if (districtStmt != null) {
                        district = districtStmt.getString();
                    }
                }
                runningEventRes.setDistrict(district);

                Statement organizationStmt = runningEvent.getProperty(inf.createProperty(RUNNING_NS + "isOrganizedBy"));
                String organizationName = "";
                if (organizationStmt != null) {
                    Resource organization = organizationStmt.getResource();
                    Statement nameStmt = organization.getProperty(inf.createProperty(RUNNING_NS + "OrganizationName"));
                    if (nameStmt != null) {
                        organizationName = nameStmt.getString();
                    }
                }
                runningEventRes.setOrganization(organizationName);

                Statement typeofEventStmt = runningEvent.getProperty(inf.createProperty(RUNNING_NS + "TypeOfEvent"));
                String typeofEvent = "";
                if (typeofEventStmt != null) {
                    typeofEvent = typeofEventStmt.getString();
                }
                runningEventRes.setTypeofEvent(typeofEvent);

                Statement levelOfEventStmt = runningEvent.getProperty(inf.createProperty(RUNNING_NS + "LevelOfEvent"));
                String evelOfEvent = "";
                if (levelOfEventStmt != null) {
                    evelOfEvent = levelOfEventStmt.getString();
                }
                runningEventRes.setLevel(evelOfEvent);

                Statement standardOfEventStmt = runningEvent.getProperty(inf.createProperty(RUNNING_NS + "StandardOfEvent"));
                String standardOfEvent = "";
                if (standardOfEventStmt != null) {
                    standardOfEvent = standardOfEventStmt.getString();
                }
                runningEventRes.setStandard(standardOfEvent);


                float highestConfidence = 0;
                StmtIterator i4 = inf.listStatements(runningEvent, inf.createProperty(RUNNING_NS + "confidence"), (RDFNode) null);
                while (i4.hasNext()) {
                    Statement confidenceStatement = i4.nextStatement();
                    RDFNode confidence = confidenceStatement.getObject();
                    float confidenceValue = confidence.asLiteral().getFloat();
                    if (confidenceValue > highestConfidence) {
                        highestConfidence = confidenceValue;
                    }
                }
                runningEventRes.setConfidence(String.valueOf(highestConfidence));
                
                StmtIterator raceTypeStmts = inf.listStatements(runningEvent, inf.createProperty(RUNNING_NS + "hasRaceType"), (RDFNode) null);
                RunningEvents.RaceTypes raceTypes = new RunningEvents.RaceTypes();
                RunningEvents.Prices prices = new RunningEvents.Prices();
                RunningEvents.Rewards rewards = new RunningEvents.Rewards();
                RunningEvents.ActivityAreas activityAreas = new RunningEvents.ActivityAreas();
                RunningEvents.StartPeriods startPeriods = new RunningEvents.StartPeriods();
                
                while (raceTypeStmts.hasNext()) {
                    Statement stmt = raceTypeStmts.nextStatement();
                    Resource raceTypeRes = stmt.getResource();
                
                    Statement nameStmt = raceTypeRes.getProperty(inf.createProperty(RUNNING_NS + "RaceTypeName"));
                    if (nameStmt != null) {
                        String raceTypeName = nameStmt.getString();
                        if (!raceTypes.getRaceType().contains(raceTypeName)) {
                            raceTypes.getRaceType().add(raceTypeName);
                        }
                    }
                
                    Statement priceStmt = raceTypeRes.getProperty(inf.createProperty(RUNNING_NS + "Price"));
                    if (priceStmt != null) {
                        String price = priceStmt.getString();
                        if (!prices.getPrice().contains(price)) {
                            prices.getPrice().add(price);
                        }
                    }
                
                    Statement rewardStmt = raceTypeRes.getProperty(inf.createProperty(RUNNING_NS + "Reward"));
                    if (rewardStmt != null) {
                        String reward = rewardStmt.getString();
                        if (!rewards.getReward().contains(reward)) {
                            rewards.getReward().add(reward);
                        }
                    }

                    Statement startPeriodStmt = raceTypeRes.getProperty(inf.createProperty(RUNNING_NS + "StartPeriod"));
                    if (startPeriodStmt != null) {
                        String startPeriod = startPeriodStmt.getString();
                        if (!startPeriods.getStartPeriod().contains(startPeriod)) {
                            startPeriods.getStartPeriod().add(startPeriod);
                        }
                    }

                    Statement activityAreaStmt = raceTypeRes.getProperty(inf.createProperty(RUNNING_NS + "ActivityArea"));
                    if (activityAreaStmt != null) {
                        String activityArea = activityAreaStmt.getString();
                        if (!activityAreas.getActivityArea().contains(activityArea)) {
                            activityAreas.getActivityArea().add(activityArea);
                        }
                    }
                }
                runningEventRes.setRaceTypes(raceTypes);
                runningEventRes.setPrices(prices);
                runningEventRes.setRewards(rewards);
                runningEventRes.setActivityAreas(activityAreas);
                runningEventRes.setStartPeriods(startPeriods);
                
                StmtIterator travelPlaceStmts = inf.listStatements(runningEvent, inf.createProperty(RUNNING_NS + "hasTravelPlaceRecommend"), (RDFNode) null);
                while (travelPlaceStmts.hasNext()) {
                    Statement statement2 = travelPlaceStmts.nextStatement();
                    RDFNode statementObj2 = statement2.getObject();
                    RunningEvents.TravelPlacesRunning travelPlaceRes = new RunningEvents.TravelPlacesRunning();
                
                    if (statementObj2.isResource()) {

                        Resource travelPlace = statementObj2.asResource();
                        
                        Statement travelPlaceNameStmt2 = travelPlace.getProperty(inf.createProperty(RUNNING_NS + "TravelPlaceName"));
                        String travelPlaceName = "";
                        if (travelPlaceNameStmt2 != null) {
                            travelPlaceName = travelPlaceNameStmt2.getString();
                        }
                        travelPlaceRes.setTravelPlaceName(travelPlaceName);

                        if (!travelPlaceName.isEmpty()) {
                            Statement travelPlaceTypeStmt = travelPlace.getProperty(inf.createProperty(RUNNING_NS + "TravelPlaceType"));
                            String travelPlaceType = "";
                            if (travelPlaceTypeStmt != null) {
                                travelPlaceType = travelPlaceTypeStmt.getString();
                            }
                            travelPlaceRes.setTravelPlaceType(travelPlaceType);
                    
                            Statement districtStmt = travelPlace.getProperty(inf.createProperty(RUNNING_NS + "District"));
                            String district2 = "";
                            if (districtStmt != null) {
                                district2 = districtStmt.getString();
                            }
                            travelPlaceRes.setDistrict(district2);
                    
                            Statement longitudeStmt = travelPlace.getProperty(inf.createProperty(RUNNING_NS + "Longitude"));
                            String longitude = "";
                            if (longitudeStmt != null) {
                                longitude = longitudeStmt.getString();
                            }
                            travelPlaceRes.setLongitude(longitude);
            
                            Statement latitudeStmt = travelPlace.getProperty(inf.createProperty(RUNNING_NS + "Latitude"));
                            String latitude = "";
                            if (latitudeStmt != null) {
                                latitude = latitudeStmt.getString();
                            }
                            travelPlaceRes.setLatitude(latitude);
            
                            Statement hotScoreStmt = travelPlace.getProperty(inf.createProperty(RUNNING_NS + "HotScore"));
                            String hotScore = "";
                            if (hotScoreStmt != null) {
                                hotScore = hotScoreStmt.getString();
                            }
                            travelPlaceRes.setHotScore(hotScore);
                    
                            runningEventRes.getTravelPlacesRunning().add(travelPlaceRes);
                        }



                    }
                }
                response.getRunningEvents().add(runningEventRes);
            }
            
        }
        //TRAVEL
        while (i5.hasNext()) {
            
            Statement statement = i5.nextStatement();
            RDFNode statementObj = statement.getObject();
            TravelPlaces travelPlaceRes = new TravelPlaces();
            if (statementObj.isResource()) {
                Resource travelPlace = statementObj.asResource();
        
                Statement travelPlaceNameStmt = travelPlace.getProperty(inf.createProperty(RUNNING_NS + "TravelPlaceName"));
                String travelPlaceName = "";
                if (travelPlaceNameStmt != null) {
                    travelPlaceName = travelPlaceNameStmt.getString();
                }
                travelPlaceRes.setTravelPlaceName(travelPlaceName);

                if (!travelPlaceName.isEmpty()) {
                    Statement travelPlaceTypeStmt = travelPlace.getProperty(inf.createProperty(RUNNING_NS + "TravelPlaceType"));
                    String travelPlaceType = "";
                    if (travelPlaceTypeStmt != null) {
                        travelPlaceType = travelPlaceTypeStmt.getString();
                    }
                    travelPlaceRes.setTravelPlaceType(travelPlaceType);
    
                    Statement districtStmt = travelPlace.getProperty(inf.createProperty(RUNNING_NS + "District"));
                    String district = "";
                    if (districtStmt != null) {
                        district = districtStmt.getString();
                    }
                    travelPlaceRes.setDistrict(district);
    
                    Statement longitudeStmt = travelPlace.getProperty(inf.createProperty(RUNNING_NS + "Longitude"));
                    String longitude = "";
                    if (longitudeStmt != null) {
                        longitude = longitudeStmt.getString();
                    }
                    travelPlaceRes.setLongitude(longitude);
    
                    Statement latitudeStmt = travelPlace.getProperty(inf.createProperty(RUNNING_NS + "Latitude"));
                    String latitude = "";
                    if (latitudeStmt != null) {
                        latitude = latitudeStmt.getString();
                    }
                    travelPlaceRes.setLatitude(latitude);
    
                    Statement hotScoreStmt = travelPlace.getProperty(inf.createProperty(RUNNING_NS + "HotScore"));
                    String hotScore = "";
                    if (hotScoreStmt != null) {
                        hotScore = hotScoreStmt.getString();
                    }
                    travelPlaceRes.setHotScore(hotScore);
                }

                response.getTravelPlaces().add(travelPlaceRes);
            }
            
        }

        try (FileOutputStream out = new FileOutputStream("allRec.rdf")) {
            inf.write(out, "RDF/XML");
            System.out.println("Save");
        } catch (Exception e) {
            // e.printStackTrace();
        }

        System.out.println("End");
        return response;
    }
}
