package api.retrofit.vehicle;

import api.calls.VehicleCalls;
import api.mappings.Car;
import api.mappings.Human;
import api.retrofit.RetrofitBuilder;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Response;

import java.util.List;

public class Vehicles {
        public static final VehicleCalls vehicleCalls = new RetrofitBuilder().getRetrofit().create(VehicleCalls.class);

        @SneakyThrows
        public static Response<List<Car>> getAllVehicles(){
                return vehicleCalls.getAllVehicles().execute();
        }

        @SneakyThrows
        public static Response<Car> getVehicleById(Integer vehicleId){
                return vehicleCalls.getVehiclesById(vehicleId).execute();
        }

        @SneakyThrows
        public static Response<Human> getClientById(Integer clientId){
                return vehicleCalls.getClientById(clientId).execute();
        }

        @SneakyThrows
        public static Response<Integer> createVehicles(Car car){
                return vehicleCalls.createVehicles(car).execute();
        }

        @SneakyThrows
        public static Response<Integer> createClient(Human human){
                return vehicleCalls.createClient(human).execute();
        }

        @SneakyThrows
        public static Response<ResponseBody> deleteVehicleById(Integer vehicleId){
                return vehicleCalls.deleteVehicleById(vehicleId).execute();
        }

        @SneakyThrows
        public static Response<Integer> updateVehicleById(Integer vehicleId, Car car){
                return vehicleCalls.updateVehicleById(vehicleId, car).execute();
        }

        @SneakyThrows
        public static Response<Integer> addVehicleToClient(Integer vehicleId, Integer clientId){
                return vehicleCalls.addVehicleToClient(vehicleId, clientId).execute();
        }


        @SneakyThrows
        public static Response<Integer> removeVehicleFromClient(Integer vehicleId){
                return vehicleCalls.removeVehicleFromClient(vehicleId).execute();
        }

        @SneakyThrows
        public static Response<ResponseBody> deleteClientById(Integer clientId){
                return vehicleCalls.deleteClientById(clientId).execute();
        }

}
