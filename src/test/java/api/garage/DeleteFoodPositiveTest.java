package api.garage;

import api.mappings.Car;
import okhttp3.ResponseBody;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import retrofit2.Response;

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
                .active(false)
                .build();
        Response<Car> responseCreate = createVehicle(carRequest);
        assertCreated(responseCreate);

        assertThat("Body is not null", responseCreate.body(), notNullValue());
        vehicleId = responseCreate.body().getId();

        assertOk(responseCreate);
    }

    @Test(description = "delete vehicle with sucess")
    public void deleteVehicleTest(){
        Response<ResponseBody> response = deleteVehicleById(9);
        assertNoContent(response);

        Response<Car> responseGet = getVehicleById(9);
        assertNotFound(responseGet);
    }
}
