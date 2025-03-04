//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2025.03.04 at 07:26:04 PM ICT 
//


package demo.project3.schema;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the demo.project3.schema package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: demo.project3.schema
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetRestaurantResponse }
     * 
     */
    public GetRestaurantResponse createGetRestaurantResponse() {
        return new GetRestaurantResponse();
    }

    /**
     * Create an instance of {@link GetRestaurantRecommendationRequest }
     * 
     */
    public GetRestaurantRecommendationRequest createGetRestaurantRecommendationRequest() {
        return new GetRestaurantRecommendationRequest();
    }

    /**
     * Create an instance of {@link GetRestaurantRecommendationResponse }
     * 
     */
    public GetRestaurantRecommendationResponse createGetRestaurantRecommendationResponse() {
        return new GetRestaurantRecommendationResponse();
    }

    /**
     * Create an instance of {@link GetRestaurantRequest }
     * 
     */
    public GetRestaurantRequest createGetRestaurantRequest() {
        return new GetRestaurantRequest();
    }

    /**
     * Create an instance of {@link GetRestaurantResponse.Restaurants }
     * 
     */
    public GetRestaurantResponse.Restaurants createGetRestaurantResponseRestaurants() {
        return new GetRestaurantResponse.Restaurants();
    }

    /**
     * Create an instance of {@link GetRestaurantRecommendationRequest.FoodTypes }
     * 
     */
    public GetRestaurantRecommendationRequest.FoodTypes createGetRestaurantRecommendationRequestFoodTypes() {
        return new GetRestaurantRecommendationRequest.FoodTypes();
    }

    /**
     * Create an instance of {@link GetRestaurantRecommendationResponse.Restaurants }
     * 
     */
    public GetRestaurantRecommendationResponse.Restaurants createGetRestaurantRecommendationResponseRestaurants() {
        return new GetRestaurantRecommendationResponse.Restaurants();
    }

}
