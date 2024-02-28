package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    StepDefinitions sd = new StepDefinitions();

    @Before(value = "@DeletePlace")
    public void beforeDeletePlace() throws IOException {
        //this code will be executed only when the place ID is null


        if (StepDefinitions.place_id==null) //we call using class name when the variable is static
        {
            sd.add_place_payload_with("Devarti", "Pahadi", "6889, love street");
            sd.user_calls_with_http_request("addPlaceAPI", "Post");
            sd.verify_that_the_place_id_created_maps_to_using("Devarti", "getPlaceAPI");
        }
    }

    @Before(value = "@UpdatePlace")
    public void beforeUpdatePlace() throws IOException {
        //this code will be executed only when the place ID is null


        if (StepDefinitions.place_id==null) //we call using class name when the variable is static
        {
            sd.add_place_payload_with("Devarti", "Pahadi", "6889, love street");
            sd.user_calls_with_http_request("addPlaceAPI", "Post");
            sd.verify_that_the_place_id_created_maps_to_using("Devarti", "getPlaceAPI");
        }
    }

}
