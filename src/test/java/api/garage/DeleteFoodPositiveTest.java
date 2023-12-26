package api.garage;

import api.mappings.Car;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.ResponseBody;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.IOException;

import static api.retrofit.vehicle.Vehicle.*;
import static api.validators.ResponseValidator.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class DeleteFoodPositiveTest {

    private Integer vehicleId;

    @BeforeMethod
    public void setup(){
        Car carRequest = Car.builder()
                .brand("Renault")
                .model("Megane")
                .plateYear(2019)
                .type("Van")
                .plate("AB-22-WW")
                .active(true)
                .build();
        Response<Car> responseCreate = createVehicle(carRequest);

        assertThat("Body is not null", responseCreate.body(), notNullValue());
        vehicleId = responseCreate.body().getId();

    }


    @Test(description = "delete vehicle with sucess")
    public void deleteVehicleTest(){

        Response<ResponseBody> response = deleteVehicleById(vehicleId);
        assertNoContent(response);

        Response<Car> responseGet = getVehicleById(vehicleId);
        assertNotFound(responseGet);
    }
}
