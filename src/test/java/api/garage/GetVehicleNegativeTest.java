package api.garage;

import api.mappings.Car;
import api.mappings.ErrorResponse;
import api.retrofit.vehicle.Errors;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.IOException;

import static api.retrofit.vehicle.Vehicles.getVehicleById;
import static api.validators.ResponseValidator.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GetVehicleNegativeTest {
    @Test(description = "get vehicle by id non existent")
    public void getVehicleByIdNonExistentTest() throws IOException {
        Response<Car> response = getVehicleById(0);
        assertNotFound(response);

        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(404));
        assertThat("Error is not the expected", errorResponse.getError(), is("Not Found"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Vehicle not found"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle/0"));
    }

    @Test(description = "get vehicle by negative id")
    public void getVehicleByNegativeIdTest() throws IOException {
        Response<Car> response = getVehicleById(-1);
        assertNotFound(response);

        ErrorResponse errorResponse = Errors.getErrorsResponse(response);

        assertThat("status is not the expected", errorResponse.getStatus(), is(404));
        assertThat("Error is not the expected", errorResponse.getError(), is("Not Found"));
        assertThat("Message is not the expected", errorResponse.getMessage(), is("Vehicle not found"));
        assertThat("Path is not the expected", errorResponse.getPath(), is("/vehicle/-1"));
    }

}
