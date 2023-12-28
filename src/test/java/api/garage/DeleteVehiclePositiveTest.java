package api.garage;

import api.mappings.Car;
import okhttp3.ResponseBody;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import retrofit2.Response;

import static api.retrofit.vehicle.Vehicles.*;
import static api.validators.ResponseValidator.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class DeleteVehiclePositiveTest {

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


    @Test(description = "delete vehicle with sucess")
    public void deleteVehicleTest(){

        Response<ResponseBody> response = deleteVehicleById(vehicleId);
        assertNoContent(response);

        Response<Car> responseGet = getVehicleById(vehicleId);
        assertNotFound(responseGet);
    }
}
