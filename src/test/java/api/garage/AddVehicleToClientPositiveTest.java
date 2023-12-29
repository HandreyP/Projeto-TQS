package api.garage;

import api.calls.VehicleCalls;
import api.mappings.Car;

import api.mappings.Human;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import retrofit2.Response;



import static api.retrofit.vehicle.Vehicles.*;
import static api.retrofit.vehicle.Vehicles.deleteVehicleById;
import static api.validators.ResponseValidator.assertOk;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class AddVehicleToClientPositiveTest {

        private Integer vehicleId, clientId;
        @BeforeTest
        public void setup(){
            Human humanRequest = Human.builder()
                    .firstName("Handrey")
                    .lastName("Pereira")
                    .address("Aveiro")
                    .postalCode("3800-545")
                    .city("Aveiro")
                    .country("Brasil")
                    .phoneNumber(912212212)
                    .nif(212212412)
                    .birthDate("2000-12-29")
                    .clientDate("2023-12-29")
                    .build();
            Response<Integer> responseCreateHuman = createClient(humanRequest);

            assertThat("Body is not null", responseCreateHuman.body(), notNullValue());
            clientId = responseCreateHuman.body();


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

        @Test(description = "put vehicle with sucess")
        public void updateVehicleByIdTest(){
            Response<Integer> response = addVehicleToClient(vehicleId, clientId);
            assertOk(response);

            Car carResponse = getVehicleById(vehicleId).body();

            assertThat("id should not be null", carResponse.getId(), notNullValue());
            assertThat("Brand should not be expected", carResponse.getBrand(), is(carResponse.getBrand()));
            assertThat("Model should not be expected", carResponse.getModel(), is(carResponse.getModel()));
            assertThat("PlateYear should not be expected", carResponse.getPlateYear(), is(carResponse.getPlateYear()));
            assertThat("Type should not be expected", carResponse.getType(), is(carResponse.getType()));
            assertThat("PLate should not be expected", carResponse.getPlate(), is(carResponse.getPlate()));
            assertThat("Active should not be expected", carResponse.isActive(), is(carResponse.isActive()));
        }

        @AfterMethod
        public void cleanUp(){
            deleteVehicleById(vehicleId);
    }
}
