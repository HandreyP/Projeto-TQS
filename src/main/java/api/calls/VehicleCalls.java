package api.calls;

import api.mappings.Car;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface VehicleCalls {

    String VEHICLE = "vehicle";

    String VEHICLE_ID = "vehicle/{id}";

    String ID = "id";

    String ADD_VEHICLE_TO_CLIENT = "vehicle/{vehicleId}/client/{clientId}";



    @GET(VEHICLE)
    Call<List<Car>> getAllVehicles();

    @GET(VEHICLE_ID)
    Call<Car> getVehiclesById(@Path(ID) Integer vehicleId);


    @POST(VEHICLE)
    Call<Integer> createVehicles(@Body Car car);

    @DELETE(VEHICLE_ID)
    Call<ResponseBody> deleteVehicleById(@Path(ID) Integer vehicleId);

    @PUT(VEHICLE_ID)
    Call<Integer> updateVehicleById(@Path(ID) Integer vehicleId, @Body Car car);

    @PUT(ADD_VEHICLE_TO_CLIENT)
    Call<Void> addVehicleToClient(@Path("vehicleId") Integer vehicleId, @Path("clientId") Integer clientId);
}
