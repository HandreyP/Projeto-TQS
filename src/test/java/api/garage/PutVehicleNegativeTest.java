package api.garage;

import api.mappings.Car;
import api.mappings.ErrorResponse;
import api.retrofit.vehicle.Errors;
import api.retrofit.vehicle.Vehicles;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static api.retrofit.vehicle.Vehicles.*;
import static api.validators.ResponseValidator.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class PutVehicleNegativeTest {
    private Integer vehicleId;

    private List<Integer> vehicleIds = new ArrayList<>();
    @BeforeTest
    public void setup(){
        Car carRequest = Car.builder()
                .brand("Renault")
                .model("Megane")
                .plateYear(2019)
                .type("Van")
                .plate("AB-22-WW")
                .active(true)
                .build();
        Response<Integer> responseCreate = createVehicles(carRequest);

        assertThat("Body is not null", responseCreate.body(), notNullValue());
        vehicleId = responseCreate.body();

    }

    @Test(description = "put vehicle with id")
    public void updateVehicleWithIdTest() throws IOException {
        Car carUpdate = Car.builder()
                .id(vehicleId)
                .brand("Ferrari")
                .model("F-50")
                .plateYear(1995 )
                .type("sport")
                .plate("AC-44-ZZ")
                .active(true)
                .build();
        Response<Integer> response = updateVehicleById(vehicleId, carUpdate);
        assertBadRequest(response);

        vehicleIds.add(vehicleId);

        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(400));
        assertThat("Error is not the expected", errorResponse.getError(), is("Bad Request"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Id on body should be null on Update"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle/"+vehicleId));
    }

    @Test(description = "put vehicle with plate null")
    public void updateVehicleWithNullPlateTest() throws IOException {
        Car carUpdate = Car.builder()
                .brand("Ferrari")
                .model("F-50")
                .plateYear(1995 )
                .type("sport")
                .plate(null)
                .active(true)
                .build();
        Response<Integer> response = updateVehicleById(vehicleId, carUpdate);
        assertInternalServerError(response);

        vehicleIds.add(vehicleId);

        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(500));
        assertThat("Error is not the expected", errorResponse.getError(), is("Internal Server Error"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Cannot invoke \"String.matches(String)\" because \"plate\" is null"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle/"+vehicleId));
    }

    @AfterClass
    public void cleanUp(){
        vehicleIds.forEach(Vehicles::deleteVehicleById);
        vehicleIds.clear();
    }
}
