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
import static api.retrofit.vehicle.Vehicles.deleteVehicleById;
import static api.validators.ResponseValidator.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class RemoveVehicleFromClientNegativeTest {

    private Integer vehicleId, clientId;
    private List<Integer> vehicleIds = new ArrayList<>();
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

        Response<Integer> responseAdd = addVehicleToClient(vehicleId, clientId);
        assertNoContent(responseAdd);

    }

    @Test(description = "Remove vehicle from client with non existent id")
    public void RemoveVehicleFromClientWithNonExistentIdTest() throws IOException {
        Response<Integer> response = removeVehicleFromClient(0);
        assertNotFound(response);



        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(404));
        assertThat("Error is not the expected", errorResponse.getError(), is("Not Found"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Not found"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle/0/client/"));
    }

    @Test(description = "Remove vehicle from client with negative id")
    public void RemoveVehicleFromClientWithNegativeIdTest() throws IOException {
        Response<Integer> response = removeVehicleFromClient(-1);
        assertNotFound(response);

        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(404));
        assertThat("Error is not the expected", errorResponse.getError(), is("Not Found"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Not found"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle/-1/client/"));
    }

    @Test(description = "Remove vehicle from client twice")
    public void RemoveVehicleFromClientTwiceTest()  {
        Response<Integer> response = removeVehicleFromClient(vehicleId);
        assertNoContent(response);

        Response<Integer> responseSecond = removeVehicleFromClient(vehicleId);
        assertNoContent(responseSecond);

        vehicleIds.add(vehicleId);
        Human humanResponse = getClientById(clientId).body();

        Car carResponse = getVehicleById(vehicleId).body();

        assertThat("id should not be null", carResponse.getId(), notNullValue());
        assertThat("Client should  be null", carResponse.getClient(), is(carResponse.getClient()));
        assertThat("Brand should not be expected", carResponse.getBrand(), is(carResponse.getBrand()));
        assertThat("Model should not be expected", carResponse.getModel(), is(carResponse.getModel()));
        assertThat("PlateYear should not be expected", carResponse.getPlateYear(), is(carResponse.getPlateYear()));
        assertThat("Type should not be expected", carResponse.getType(), is(carResponse.getType()));
        assertThat("PLate should not be expected", carResponse.getPlate(), is(carResponse.getPlate()));
        assertThat("Active should not be expected", carResponse.isActive(), is(carResponse.isActive()));
    }

    @AfterMethod
    public void cleanUp(){
        vehicleIds.forEach(Vehicles::deleteVehicleById);
        vehicleIds.clear();

        deleteClientById(clientId);
    }
}
