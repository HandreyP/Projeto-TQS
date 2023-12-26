package api.garage;

import api.mappings.Car;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import retrofit2.Response;

import static api.retrofit.vehicle.Vehicle.createVehicle;
import static api.retrofit.vehicle.Vehicle.deleteVehicleById;
import static api.validators.ResponseValidator.assertCreated;
import static org.hamcrest.MatcherAssert.assertThat;
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
                .active(false)
                .build();
        Response<Car> response = createVehicle(carRequest);
        assertCreated(response);

        assertThat("Body is not null", response.body(), notNullValue());
        vehicleId = response.body().getId();

    }

    @AfterClass
    public void cleanUp(){
        deleteVehicleById(vehicleId);
    }
}
