package project3.demo;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import demo.project3.schema.GetRestaurantRecommendationRequest;
import demo.project3.schema.GetRestaurantRecommendationResponse;

@Endpoint
public class RestaurantRecommendationEndpoint {

	private static final String NAMESPACE_URI = "http://project3.demo/schema";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRestaurantRecommendationRequest")
	@ResponsePayload
	public GetRestaurantRecommendationResponse getRestaurantRecommendation(@RequestPayload GetRestaurantRecommendationRequest request) {
		GetRestaurantRecommendationResponse response = new GetRestaurantRecommendationResponse();
		response.setName(request.getName() + " Recommended Restaurant");
		response.setLocation("456 Elm St");
		return response;
	}
}
