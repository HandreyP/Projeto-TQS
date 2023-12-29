package api.garage;

import api.mappings.Car;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import retrofit2.Response;

import static api.retrofit.vehicle.Vehicles.createVehicles;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class DeleteServiceFromVehiclePositive {
    private Integer vehicleId;

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
}
