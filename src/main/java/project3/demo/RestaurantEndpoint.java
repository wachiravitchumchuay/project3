package project3.demo;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import demo.project3.schema.GetRestaurantRequest;
import demo.project3.schema.GetRestaurantResponse;

@Endpoint
public class RestaurantEndpoint {

	private static final String NAMESPACE_URI = "http://project3.demo/schema";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRestaurantRequest")
	@ResponsePayload
	public GetRestaurantResponse getRestaurant(@RequestPayload GetRestaurantRequest request) {
		GetRestaurantResponse response = new GetRestaurantResponse();
		response.setName(request.getName() + " Restaurant");
		response.setLocation("123 Main St");
		return response;
	}
}
