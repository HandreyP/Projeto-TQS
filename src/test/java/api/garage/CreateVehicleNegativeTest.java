package api.garage;

import api.mappings.Car;
import api.mappings.ErrorResponse;
import api.retrofit.vehicle.Errors;

import api.retrofit.vehicle.Vehicles;
import org.testng.annotations.AfterClass;
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


public class CreateVehicleNegativeTest {

    private Integer vehicleId;

    private List<Integer> vehicleIds = new ArrayList<>();

    @Test(description = "create vehicle with id")
    public void createVehicleWithIdTest() throws IOException {
        Car carRequest = Car.builder()
                .id(15)
                .brand("Renault")
                .model("Megane")
                .plateYear(2019)
                .type("Van")
                .plate("AB-22-WW")
                .active(false)
                .build();
        Response<Integer> response = createVehicles(carRequest);
        assertBadRequest(response);

        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(400));
        assertThat("Error is not the expected", errorResponse.getError(), is("Bad Request"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Id should be null on creation"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle"));
    }

    @Test(description = "create vehicle with id 0")
    public void createVehicleWithIdZeroTest() throws IOException {
        Car carRequest = Car.builder()
                .id(0)
                .plate("AB-22-WW")
                .build();
        Response<Integer> response = createVehicles(carRequest);
        assertBadRequest(response);

        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(400));
        assertThat("Error is not the expected", errorResponse.getError(), is("Bad Request"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Id should be null on creation"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle"));
    }
    @Test(description = "create vehicle with invalid client")
    public void createVehicleWithInvalidClientTest() {
        Car carRequest = Car.builder()
                .client(0)
                .brand("Renault")
                .model("Megane")
                .plateYear(2019)
                .type("Van")
                .plate("AB-22-WW")
                .active(false)
                .build();
        Response<Integer> response = createVehicles(carRequest);
        assertCreated(response);

        vehicleId = response.body();
        vehicleIds.add(vehicleId);
        Car carResponse = getVehicleById(vehicleId).body();

        assertThat("id should not be null", carResponse.getId(), notNullValue());
        assertThat("Client should be null", carResponse.getClient(), is(carResponse.getClient()));
        assertThat("Brand should not be expected", carResponse.getBrand(), is(carResponse.getBrand()));
        assertThat("Model should not be expected", carResponse.getModel(), is(carResponse.getModel()));
        assertThat("PlateYear should not be expected", carResponse.getPlateYear(), is(carResponse.getPlateYear()));
        assertThat("Type should not be expected", carResponse.getType(), is(carResponse.getType()));
        assertThat("PLate should not be expected", carResponse.getPlate(), is(carResponse.getPlate()));
        assertThat("Active should not be expected", carResponse.isActive(), is(carResponse.isActive()));
    }

    @Test(description = "create vehicle with negative client")
    public void createVehicleWithNegativeClientTest() {
        Car carRequest = Car.builder()
                .client(-1)
                .brand("Renault")
                .model("Megane")
                .plateYear(2019)
                .type("Van")
                .plate("AB-22-WW")
                .active(false)
                .build();
        Response<Integer> response = createVehicles(carRequest);
        assertCreated(response);

        vehicleId = response.body();
        vehicleIds.add(vehicleId);
        Car carResponse = getVehicleById(vehicleId).body();

        assertThat("id should not be null", carResponse.getId(), notNullValue());
        assertThat("Client should be null", carResponse.getClient(), is(carResponse.getClient()));
        assertThat("Brand should not be expected", carResponse.getBrand(), is(carResponse.getBrand()));
        assertThat("Model should not be expected", carResponse.getModel(), is(carResponse.getModel()));
        assertThat("PlateYear should not be expected", carResponse.getPlateYear(), is(carResponse.getPlateYear()));
        assertThat("Type should not be expected", carResponse.getType(), is(carResponse.getType()));
        assertThat("PLate should not be expected", carResponse.getPlate(), is(carResponse.getPlate()));
        assertThat("Active should not be expected", carResponse.isActive(), is(carResponse.isActive()));
    }


    @Test(description = "create vehicle with only plate")
    public void createVehicleWithOnlyPlateTest() {
        Car carRequest = Car.builder()
                .plate("AB-22-WW")
                .build();
        Response<Integer> response = createVehicles(carRequest);
        assertCreated(response);

        vehicleId = response.body();
        vehicleIds.add(vehicleId);
        Car carResponse = getVehicleById(vehicleId).body();

        assertThat("id should not be null", carResponse.getId(), notNullValue());
        assertThat("Brand should not be expected", carResponse.getBrand(), is(carResponse.getBrand()));
        assertThat("Model should not be expected", carResponse.getModel(), is(carResponse.getModel()));
        assertThat("PlateYear should not be expected", carResponse.getPlateYear(), is(carResponse.getPlateYear()));
        assertThat("Type should not be expected", carResponse.getType(), is(carResponse.getType()));
        assertThat("PLate should not be expected", carResponse.getPlate(), is(carResponse.getPlate()));
        assertThat("Active should not be expected", carResponse.isActive(), is(carResponse.isActive()));
    }

    @Test(description = "create vehicle with invalid plate")
    public void createVehicleWithInvalidPlateTest() throws IOException {
        Car carRequest = Car.builder()
                .plate("AB22WW")
                .build();
        Response<Integer> response = createVehicles(carRequest);
        assertBadRequest(response);

        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(400));
        assertThat("Error is not the expected", errorResponse.getError(), is("Bad Request"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Invalid Plate"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle"));
    }

    @Test(description = "create vehicle with plate year is 0")
    public void createVehicleWithOnlyPlateYearTest() {
        Car carRequest = Car.builder()
                .plateYear(0)
                .plate("AB-22-WW")
                .build();
        Response<Integer> response = createVehicles(carRequest);
        assertCreated(response);

        vehicleId = response.body();
        vehicleIds.add(vehicleId);
        Car carResponse = getVehicleById(vehicleId).body();

        assertThat("id should not be null", carResponse.getId(), notNullValue());
        assertThat("Brand should not be expected", carResponse.getBrand(), is(carResponse.getBrand()));
        assertThat("Model should not be expected", carResponse.getModel(), is(carResponse.getModel()));
        assertThat("PlateYear should not be expected", carResponse.getPlateYear(), is(carResponse.getPlateYear()));
        assertThat("Type should not be expected", carResponse.getType(), is(carResponse.getType()));
        assertThat("PLate should not be expected", carResponse.getPlate(), is(carResponse.getPlate()));
        assertThat("Active should not be expected", carResponse.isActive(), is(carResponse.isActive()));
    }

    @Test(description = "create vehicle with negative plate year")
    public void createVehicleWithNegativePlateYearTest()  {
        Car carRequest = Car.builder()
                .plateYear(-1)
                .plate("AB-22-WW")
                .build();
        Response<Integer> response = createVehicles(carRequest);
        assertCreated(response);

        vehicleId = response.body();
        vehicleIds.add(vehicleId);
        Car carResponse = getVehicleById(vehicleId).body();

        assertThat("id should not be null", carResponse.getId(), notNullValue());
        assertThat("Brand should not be expected", carResponse.getBrand(), is(carResponse.getBrand()));
        assertThat("Model should not be expected", carResponse.getModel(), is(carResponse.getModel()));
        assertThat("PlateYear should not be expected", carResponse.getPlateYear(), is(carResponse.getPlateYear()));
        assertThat("Type should not be expected", carResponse.getType(), is(carResponse.getType()));
        assertThat("PLate should not be expected", carResponse.getPlate(), is(carResponse.getPlate()));
        assertThat("Active should not be expected", carResponse.isActive(), is(carResponse.isActive()));
    }

    @Test(description = "create vehicle with a random number plate year")
    public void createVehicleWithRandomNumberPlateYearTest()  {
        Car carRequest = Car.builder()
                .plateYear(21564654)
                .plate("AB-22-WW")
                .build();
        Response<Integer> response = createVehicles(carRequest);
        assertCreated(response);

        vehicleId = response.body();
        vehicleIds.add(vehicleId);
        Car carResponse = getVehicleById(vehicleId).body();

        assertThat("id should not be null", carResponse.getId(), notNullValue());
        assertThat("Brand should not be expected", carResponse.getBrand(), is(carResponse.getBrand()));
        assertThat("Model should not be expected", carResponse.getModel(), is(carResponse.getModel()));
        assertThat("PlateYear should not be expected", carResponse.getPlateYear(), is(carResponse.getPlateYear()));
        assertThat("Type should not be expected", carResponse.getType(), is(carResponse.getType()));
        assertThat("PLate should not be expected", carResponse.getPlate(), is(carResponse.getPlate()));
        assertThat("Active should not be expected", carResponse.isActive(), is(carResponse.isActive()));
    }

    @Test(description = "create vehicle with invalid plate year")
    public void createVehicleWithInvalidPlateYearTest() {
        Car carRequest = Car.builder()
                .plateYear(2045)
                .plate("AB-22-WW")
                .build();
        Response<Integer> response = createVehicles(carRequest);
        assertCreated(response);

        vehicleId = response.body();
        vehicleIds.add(vehicleId);
        Car carResponse = getVehicleById(vehicleId).body();

        assertThat("id should not be null", carResponse.getId(), notNullValue());
        assertThat("Brand should not be expected", carResponse.getBrand(), is(carResponse.getBrand()));
        assertThat("Model should not be expected", carResponse.getModel(), is(carResponse.getModel()));
        assertThat("PlateYear should not be expected", carResponse.getPlateYear(), is(carResponse.getPlateYear()));
        assertThat("Type should not be expected", carResponse.getType(), is(carResponse.getType()));
        assertThat("PLate should not be expected", carResponse.getPlate(), is(carResponse.getPlate()));
        assertThat("Active should not be expected", carResponse.isActive(), is(carResponse.isActive()));
    }

    @Test(description = "create vehicle without attributes")
    public void createVehicleWithoutAttributesTest() throws IOException {
        Car carRequest = Car.builder()
                .build();
        Response<Integer> response = createVehicles(carRequest);
        assertInternalServerError(response);

        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(500));
        assertThat("Error is not the expected", errorResponse.getError(), is("Internal Server Error"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Cannot invoke \"String.matches(String)\" because \"plate\" is null"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle"));
    }

    @Test(description = "create vehicle with plate Null")
    public void createVehicleWithPlatesNullTest() throws IOException {
        Car carRequest = Car.builder()
                .plate(null)
                .build();
        Response<Integer> response = createVehicles(carRequest);
        assertInternalServerError(response);

        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(500));
        assertThat("Error is not the expected", errorResponse.getError(), is("Internal Server Error"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Cannot invoke \"String.matches(String)\" because \"plate\" is null"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle"));
    }

    @Test(description = "create vehicle without plate")
    public void createVehicleWithoutPlateTest() throws IOException {
        Car carRequest = Car.builder()
                .brand("Renault")
                .model("Megane")
                .plateYear(2019)
                .type("Van")
                .active(false)
                .build();
        Response<Integer> response = createVehicles(carRequest);
        assertInternalServerError(response);

        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(500));
        assertThat("Error is not the expected", errorResponse.getError(), is("Internal Server Error"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Cannot invoke \"String.matches(String)\" because \"plate\" is null"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle"));
    }

    @Test(description = "create vehicle with a plate only letters")
    public void createVehicleWithPlateOnlyLettersTest() throws IOException {
        Car carRequest = Car.builder()
                .plateYear(2045)
                .plate("ABFAWW")
                .build();
        Response<Integer> response = createVehicles(carRequest);
        assertBadRequest(response);

        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(400));
        assertThat("Error is not the expected", errorResponse.getError(), is("Bad Request"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Invalid Plate"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle"));
    }

    @Test(description = "create vehicle with a plate only letters but with space")
    public void createVehicleWithPlateOnlyLettersButWithSpaceTest() throws IOException {
        Car carRequest = Car.builder()
                .plateYear(2045)
                .plate("AB-FA-WW")
                .build();
        Response<Integer> response = createVehicles(carRequest);
        assertBadRequest(response);

        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(400));
        assertThat("Error is not the expected", errorResponse.getError(), is("Bad Request"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Invalid Plate"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle"));
    }

    @Test(description = "create vehicle with a plate only numbers but with space")
    public void createVehicleWithPlateOnlyNumbersButWithSpaceTest() throws IOException {
        Car carRequest = Car.builder()
                .plateYear(2045)
                .plate("01-23-45")
                .build();
        Response<Integer> response = createVehicles(carRequest);
        assertBadRequest(response);

        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(400));
        assertThat("Error is not the expected", errorResponse.getError(), is("Bad Request"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Invalid Plate"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle"));
    }

    @Test(description = "create vehicle with a plate only numbers")
    public void createVehicleWithPlateOnlyNumbersTest() throws IOException {
        Car carRequest = Car.builder()
                .plateYear(2045)
                .plate("012345")
                .build();
        Response<Integer> response = createVehicles(carRequest);
        assertBadRequest(response);

        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(400));
        assertThat("Error is not the expected", errorResponse.getError(), is("Bad Request"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Invalid Plate"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle"));
    }

    @Test(description = "create vehicle with a plate with character special")
    public void createVehicleWithPlateWithCharacterSpecialTest() throws IOException {
        Car carRequest = Car.builder()
                .plateYear(2045)
                .plate("MM-23-!@")
                .build();
        Response<Integer> response = createVehicles(carRequest);
        assertBadRequest(response);

        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(400));
        assertThat("Error is not the expected", errorResponse.getError(), is("Bad Request"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Invalid Plate"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle"));
    }
    @AfterClass
    public void cleanUp(){
        vehicleIds.forEach(Vehicles::deleteVehicleById);
        vehicleIds.clear();
    }
}
