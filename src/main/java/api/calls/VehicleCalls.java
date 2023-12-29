package api.calls;

import api.mappings.Car;
import api.mappings.Human;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.SimpleTimeZone;

public interface VehicleCalls {

    String VEHICLE = "vehicle";

    String VEHICLE_ID = "vehicle/{id}";

    String ID = "id";

    String VEHICLE_TO_CLIENT = "vehicle/{vehicleId}/client/{clientId}";

    String CLIENT= "client";
    String CLIENT_ID = "client/{id}";



    @GET(VEHICLE)
    Call<List<Car>> getAllVehicles();

    @GET(VEHICLE_ID)
    Call<Car> getVehiclesById(@Path(ID) Integer vehicleId);


    @POST(VEHICLE)
    Call<Integer> createVehicles(@Body Car car);

    @POST(CLIENT)
    Call<Integer> createClient(@Body Human human);

    @DELETE(VEHICLE_ID)
    Call<ResponseBody> deleteVehicleById(@Path(ID) Integer vehicleId);

    @PUT(VEHICLE_ID)
    Call<Integer> updateVehicleById(@Path(ID) Integer vehicleId, @Body Car car);

    @PUT(VEHICLE_TO_CLIENT)
    Call<Integer> addVehicleToClient(@Path(ID) Integer vehicleId, @Path(ID) Integer clientId);
}
