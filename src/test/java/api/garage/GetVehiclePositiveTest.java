package api.garage;

import api.mappings.Car;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.util.List;

import static api.retrofit.vehicle.Vehicles.getAllVehicles;
import static api.retrofit.vehicle.Vehicles.getVehicleById;
import static api.validators.ResponseValidator.assertOk;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetVehiclePositiveTest {

    @Test(description = "get all vehicles with sucess")
    public void getAllVehiclesTest() {
        Response<List<Car>> response = getAllVehicles();
        assertOk(response);

        assertThat("Body is not null", response.body(), notNullValue());

    }

    @Test(description = "get vehicle by id with sucess")
    public void getVehicleByIdTest(){
        Response<Car> response = getVehicleById(2);
        assertOk(response);

        assertThat("Body is not null", response.body(), notNullValue());

        Car carResponse = response.body();
        assertThat("client is not the expected", carResponse.getClient(), is(1));

        assertThat("brand is not the expected", carResponse.getBrand(), is("Opel"));

        assertThat("model is not the expected", carResponse.getModel(), is("Corsa"));

        assertThat("plateyear is not the expected", carResponse.getPlateYear(), is(2002));

        assertThat("type is not the expected", carResponse.getType(), is("Hatchback"));

        assertThat("plate is not the expected", carResponse.getPlate(), is("34-CB-45"));

        assertThat("active is not the expected", carResponse.isActive(), is(Boolean.TRUE));
    }
}