package api.garage;

import api.mappings.Car;
import api.retrofit.vehicle.Vehicles;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

import static api.retrofit.vehicle.Vehicles.*;
import static api.validators.ResponseValidator.assertCreated;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CreateVehiclePositiveTest {

    private Integer vehicleId;


    @Test(description = "create car with sucess")
    public void createVehicleTest(){
        Car carRequest = Car.builder()
                .brand("Renault")
                .model("Megane")
                .plateYear(2019)
                .type("Van")
                .plate("AB-22-WW")
                .active(true)
                .build();
        Response<Integer> response = createVehicles(carRequest);
        assertCreated(response);

        assertThat("Body is not null", response.body(), notNullValue());
        vehicleId = response.body();
        Car carResponse = getVehicleById(vehicleId).body();

        assertThat("id should not be null", carResponse.getId(), notNullValue());
        assertThat("Brand should not be null", carResponse.getBrand(), is(carResponse.getBrand()));
        assertThat("Model should not be null", carResponse.getModel(), is(carResponse.getModel()));
        assertThat("PlateYear should not be null", carResponse.getPlateYear(), is(carResponse.getPlateYear()));
        assertThat("Type should not be null", carResponse.getType(), is(carResponse.getType()));
        assertThat("PLate should not be null", carResponse.getPlate(), is(carResponse.getPlate()));
        assertThat("Active should not be null", carResponse.isActive(), is(carResponse.isActive()));
    }

   @AfterMethod
    public void cleanUp(){
       deleteVehicleById(vehicleId);
    }
}
