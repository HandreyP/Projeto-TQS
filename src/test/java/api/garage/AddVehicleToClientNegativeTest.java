package api.garage;

import api.mappings.Car;

import api.mappings.ErrorResponse;
import api.mappings.Human;
import api.retrofit.vehicle.Errors;
import api.retrofit.vehicle.Vehicles;
import org.testng.annotations.AfterMethod;
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

public class AddVehicleToClientNegativeTest {

    private Integer vehicleId, clientId;
    private List<Integer> vehicleIds = new ArrayList<>();
    private List<Integer> clientIds = new ArrayList<>();
    @BeforeTest
    public void setup(){
        Human ClientResponse = Human.builder()
                .firstName("James")
                .lastName("Bond")
                .phoneNumber(915035235)
                .address("MI - 6 Londres")
                .country("Inglaterra")
                .city("Londres")
                .postalCode("4350-334")
                .nif(233211225)
                .birthDate("2024-01-18")
                .clientDate("2024-01-13")
                .build();
        Response<Integer> responseHuman = createClient(ClientResponse);
        assertThat("Body is not null", responseHuman.body(), notNullValue());
        clientId = responseHuman.body();



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

    @Test(description = "add a client by non existent id to a vehicle")
    public void addVehicleToClientByNonExistentIdTest() throws IOException {
        Response<Integer> response = addVehicleToClient(vehicleId, 0);
        assertNotFound(response);

        vehicleIds.add(vehicleId);

        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(404));
        assertThat("Error is not the expected", errorResponse.getError(), is("Not Found"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Not found"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle/"+vehicleId+"/client/0"));
    }

    @Test(description = "add a client by negative id to a vehicle")
    public void addVehicleToClientByNegativeIdTest() throws IOException {
        Response<Integer> response = addVehicleToClient(vehicleId, -1);
        assertNotFound(response);

        vehicleIds.add(vehicleId);

        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(404));
        assertThat("Error is not the expected", errorResponse.getError(), is("Not Found"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Not found"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle/"+vehicleId+"/client/-1"));
    }

    @Test(description = "add a client to a vehicle with non existent Id")
    public void addVehicleWithNonExistentIdToClientTest() throws IOException {
        Response<Integer> response = addVehicleToClient(0, clientId);
        assertNotFound(response);

        clientIds.add(clientId);

        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(404));
        assertThat("Error is not the expected", errorResponse.getError(), is("Not Found"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Not found"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle/0/client/"+clientId));
    }

    @Test(description = "add a client to a vehicle with negative Id")
    public void addVehicleWithNegativeIdToClientTest() throws IOException {
        Response<Integer> response = addVehicleToClient(-1, clientId);
        assertNotFound(response);

        clientIds.add(clientId);

        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(404));
        assertThat("Error is not the expected", errorResponse.getError(), is("Not Found"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Not found"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle/-1/client/"+clientId));
    }

    @Test(description = "add a client by negative Id to a vehicle with negative Id")
    public void addVehicleWithNegativeIdToClientWithNegativeIdTest() throws IOException {
        Response<Integer> response = addVehicleToClient(-1, -1);
        assertNotFound(response);


        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(404));
        assertThat("Error is not the expected", errorResponse.getError(), is("Not Found"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Not found"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle/-1/client/-1"));
    }

    @Test(description = "add a client with non existent to a vehicle with non existent Id")
    public void addVehicleWithNonExistentIdToClientWithNonExistentIdTest() throws IOException {
        Response<Integer> response = addVehicleToClient(0, 0);
        assertNotFound(response);


        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(404));
        assertThat("Error is not the expected", errorResponse.getError(), is("Not Found"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Not found"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle/0/client/0"));
    }


    @AfterMethod
    public void cleanUp(){
        vehicleIds.forEach(Vehicles::deleteVehicleById);
        vehicleIds.clear();

        clientIds.forEach(Vehicles :: deleteClientById);
        clientIds.clear();
    }
}
