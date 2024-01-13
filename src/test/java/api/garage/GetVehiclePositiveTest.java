package api.garage;

import api.mappings.Car;
import org.hamcrest.core.IsNull;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.util.List;

import static api.retrofit.vehicle.Vehicles.*;
import static api.validators.ResponseValidator.assertOk;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetVehiclePositiveTest {

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

    @Test(description = "get all vehicles with sucess")
    public void getAllVehiclesTest() {
        Response<List<Car>> response = getAllVehicles();
        assertOk(response);

        assertThat("Body is not null", response.body(), notNullValue());

    }

    @Test(description = "get vehicle by id with sucess")
    public void getVehicleByIdTest(){
        Response<Car> response = getVehicleById(vehicleId);
        assertOk(response);

        assertThat("Body is not null", response.body(), notNullValue());

        Car carResponse = response.body();

        assertThat("client is not the expected", carResponse.getClient(), is(carResponse.getClient()));
        assertThat("brand is not the expected", carResponse.getBrand(), is("Renault"));
        assertThat("model is not the expected", carResponse.getModel(), is("Megane"));
        assertThat("plateyear is not the expected", carResponse.getPlateYear(), is(2019));
        assertThat("type is not the expected", carResponse.getType(), is("Van"));
        assertThat("plate is not the expected", carResponse.getPlate(), is("AB-22-WW"));
        assertThat("active is not the expected", carResponse.isActive(), is(Boolean.TRUE));
    }

    @AfterTest
    public void cleanUp(){
        deleteVehicleById(vehicleId);
    }
}