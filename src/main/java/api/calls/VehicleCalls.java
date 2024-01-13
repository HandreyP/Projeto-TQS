package api.calls;

import api.mappings.Car;
import api.mappings.Human;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface VehicleCalls {

    String VEHICLE = "vehicle";
    String VEHICLE_ID = "vehicle/{id}";
    String ID = "id";
    String VEHICLE_TO_CLIENT = "vehicle/{id}/client/{clientId}";
    String VEHICLE_CLIENT="vehicle/{id}/client/";
    String CLIENT= "client";
    String CLIENT_ID = "clientId";

    String CLIENT_ID_PATH = "client/{clientId}";



    @GET(VEHICLE)
    Call<List<Car>> getAllVehicles();

    @GET(VEHICLE_ID)
    Call<Car> getVehiclesById(@Path(ID) Integer vehicleId);

    @GET(CLIENT_ID_PATH)
    Call<Human> getClientById(@Path(CLIENT_ID) Integer clientId);

    @POST(VEHICLE)
    Call<Integer> createVehicles(@Body Car car);

    @POST(CLIENT)
    Call<Integer> createClient(@Body Human human);

    @DELETE(VEHICLE_ID)
    Call<ResponseBody> deleteVehicleById(@Path(ID) Integer vehicleId);

    @DELETE(CLIENT_ID_PATH)
    Call<ResponseBody> deleteClientById(@Path(CLIENT_ID) Integer clientId);

    @PUT(VEHICLE_ID)
    Call<Integer> updateVehicleById(@Path(ID) Integer vehicleId, @Body Car car);

    @PUT(VEHICLE_TO_CLIENT)
    Call<Integer> addVehicleToClient(@Path(ID) Integer vehicleId, @Path(CLIENT_ID) Integer clientId);

    @DELETE(VEHICLE_CLIENT)
    Call<Integer> removeVehicleFromClient(@Path(ID) Integer vehicleId);
}
