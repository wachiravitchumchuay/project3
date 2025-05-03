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

import demo.project3.schema.GetRestaurantRecommendationRequest;
import demo.project3.schema.GetRestaurantRecommendationResponse;
import demo.project3.schema.GetRestaurantRecommendationResponse.Restaurants;

// <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:sch="http://project3.demo/schema">
//    <soapenv:Header/>
//    <soapenv:Body>
//       <sch:getRestaurantRecommendationRequest>
//          <PreRunCarbConsumtion>Medium</PreRunCarbConsumtion>
//          <PreRunFatConsumtion>Medium</PreRunFatConsumtion>
//          <PreRunProteinConsumtion>Medium</PreRunProteinConsumtion>
//          <PostRunCarbConsumtion>Medium</PostRunCarbConsumtion>
//          <PostRunFatConsumtion>Medium</PostRunFatConsumtion>
//          <PostRunProteinConsumtion>Medium</PostRunProteinConsumtion>
//          <RunnerType>Fun run</RunnerType>
//          <BudgetInteresets>
//             <BudgetIntereset>301.0</BudgetIntereset>
//             <BudgetIntereset>600.0</BudgetIntereset>
//          </BudgetInteresets>
//          <hasRestaurantTypeInterest>Fast_Dining_Type</hasRestaurantTypeInterest>
//          <hasFoodTypeInterests>
//             <hasFoodTypeInterest>ALaCarte_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>Bakery_Cake_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>Breakfast_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>BubbleMilkTea_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>Buffet_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>Burger_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>CleanFood_Salad_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>Dessert_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>Dimsum_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>DrinksJuice_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>FastFood_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>Grill_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>GrilledPork_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>IceCream_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>Noodles_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>Omakase_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>OneDishMeal_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>Pizza_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>Ramen_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>Seafood_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>Shabu_Sukiyaki_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>Steak_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>Sushi_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>VegatarianFood_Type</hasFoodTypeInterest>
//             <hasFoodTypeInterest>Vegatarian_Jay_Type</hasFoodTypeInterest>
//          </hasFoodTypeInterests>
//       </sch:getRestaurantRecommendationRequest>
//    </soapenv:Body>
// </soapenv:Envelope>
@Endpoint
public class GetRestaurantRecommendationEndpoint {

    private static final String NAMESPACE_URI = "http://project3.demo/schema";
    private static final String ONTOLOGY_FILE = "RestaurantOntology_03_12_24.rdf";
    private static final String RULES_FILE = "restaurant.rules";
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
        Resource user = inf.getResource(userURI);
        Property p = inf.getProperty(NS, "hasRecommend");
        StmtIterator i1 = inf.listStatements(user, p, (RDFNode) null);


        while (i1.hasNext()) {
            
            Statement statement = i1.nextStatement();
            RDFNode statementObj = statement.getObject();
            Restaurants restaurantRes = new Restaurants();
            if (statementObj.isResource()) {
                Resource restaurant = statementObj.asResource();
        
                Statement restaurantNameStmt = restaurant.getProperty(inf.createProperty(NS + "RestaurantName"));
                String restaurantName = "";
                if (restaurantNameStmt != null) {
                    restaurantName = restaurantNameStmt.getString();
                }
                restaurantRes.setRestaurantName(restaurantName);
        
                Property hasRestaurantNationality = inf.createProperty(NS + "hasRestaurantNationality");
                Statement nationalityStmt = restaurant.getProperty(hasRestaurantNationality);
                String restaurantNationality = "";
                if (nationalityStmt != null && nationalityStmt.getObject().isResource()) {
                    restaurantNationality = nationalityStmt.getObject().asResource().getLocalName();
                }
                restaurantRes.setRestaurantNationality(restaurantNationality);
        
                Property hasRestaurantType = inf.createProperty(NS + "hasRestaurantType");
                Statement typeStmt = restaurant.getProperty(hasRestaurantType);
                String restaurantType = "";
                if (typeStmt != null && typeStmt.getObject().isResource()) {
                    restaurantType = typeStmt.getObject().asResource().getLocalName();
                }
                restaurantRes.setRestaurantType(restaurantType);
        
                Property hasRestaurantPlace = inf.createProperty(NS + "hasRestaurantPlace");
                Statement placeStmt = restaurant.getProperty(hasRestaurantPlace);
                String district = "";
                if (placeStmt != null && placeStmt.getObject().isResource()) {
                    Resource placeResource = placeStmt.getObject().asResource();
                    Statement districtStmt = placeResource.getProperty(inf.createProperty(NS + "District"));
                    if (districtStmt != null) {
                        district = districtStmt.getString();
                    }
                }
                restaurantRes.setDistrict(district);
        
                Property hasFoodType = inf.createProperty(NS + "hasFoodType");
                Statement foodTypeStmt = restaurant.getProperty(hasFoodType);
                String foodType = "";
                String fat = "";
                String protein = "";
                String carbohydrates = "";
        
                if (foodTypeStmt != null && foodTypeStmt.getObject().isResource()) {
                    Resource foodTypeResource = foodTypeStmt.getObject().asResource();
                    foodType = foodTypeResource.getLocalName();
        
                    Statement fatStmt = foodTypeResource.getProperty(inf.createProperty(NS + "Fat"));
                    if (fatStmt != null) {
                        fat = fatStmt.getString();
                    }
        
                    Statement proteinStmt = foodTypeResource.getProperty(inf.createProperty(NS + "Protein"));
                    if (proteinStmt != null) {
                        protein = proteinStmt.getString();
                    }
        
                    Statement carbStmt = foodTypeResource.getProperty(inf.createProperty(NS + "Carbohydrates"));
                    if (carbStmt != null) {
                        carbohydrates = carbStmt.getString();
                    }
                }
                restaurantRes.setFoodType(foodType);
                restaurantRes.setFat(fat);
                restaurantRes.setProtein(protein);
                restaurantRes.setCarbohydrates(carbohydrates);
        
                Property budgetProperty = inf.createProperty(NS + "Budget");
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
                // StmtIterator i2 = inf.listStatements(restaurant, inf.createProperty(NS + "confidence"), (RDFNode) null);
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

                StmtIterator i2 = inf.listStatements(restaurant, inf.createProperty(NS + "confidence"), (RDFNode) null);
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


        try (FileOutputStream out = new FileOutputStream("getRec.rdf")) {
            inf.write(out, "RDF/XML");
        } catch (Exception e) {
            // e.printStackTrace();
        }

        return response;
    }
}