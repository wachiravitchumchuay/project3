package project3.demo;

import java.io.FileOutputStream;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.InfModel;
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

        OntClass userClassRes = model.getOntClass(RESTAURANT_NS + "User");
        OntClass userClassRun = model.getOntClass(RUNNING_NS + "User");
        String userProfileName = "userTmpInf";
        String userURI = RUNNING_NS + userProfileName;
        Resource userInstance = model.createResource(userURI);
        userInstance.addProperty(RDF.type, userClassRes);
        userInstance.addProperty(RDF.type, userClassRun);

        if (true) {
            
        }

        Model rm = ModelFactory.createDefaultModel();
        Resource configuration = rm.createResource();
        configuration.addProperty(ReasonerVocabulary.PROPruleMode, "hybrid");
        configuration.addProperty(ReasonerVocabulary.PROPruleSet, RUNNING_RULES_FILE);
        configuration.addProperty(ReasonerVocabulary.PROPruleSet, TRAVELPLACE_RULES_FILE);
        configuration.addProperty(ReasonerVocabulary.PROPruleSet, RESTAURANT_RULES_FILE);
        Reasoner reasoner = GenericRuleReasonerFactory.theInstance().create(configuration);
        InfModel inf = ModelFactory.createInfModel(reasoner, model);



        
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
