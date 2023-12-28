package api.garage;

import api.mappings.Car;
import okhttp3.ResponseBody;
import org.testng.annotations.Test;
import retrofit2.Response;

import static api.retrofit.vehicle.Vehicles.deleteVehicleById;
import static api.retrofit.vehicle.Vehicles.getVehicleById;
import static api.validators.ResponseValidator.assertNoContent;
import static api.validators.ResponseValidator.assertNotFound;

public class DeleteVehicleNegativeTest {
    @Test(description = "delete non existent vehicleId")
    public void deleteVehicleWithNonExistentIdTest(){

        Response<ResponseBody> response = deleteVehicleById(0);
        assertNoContent(response);

        Response<Car> responseGet = getVehicleById(0);
        assertNotFound(responseGet);
    }

    @Test(description = "delete negative vehicleId")
    public void deleteVehicleWithNegativeIdTest(){

        Response<ResponseBody> response = deleteVehicleById(-1);
        assertNoContent(response);

        Response<Car> responseGet = getVehicleById(-1);
        assertNotFound(responseGet);
    }
}
