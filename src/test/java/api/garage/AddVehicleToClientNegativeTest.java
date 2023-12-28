package api.garage;

import api.calls.VehicleCalls;
import api.mappings.Car;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertFalse;

public class AddVehicleToClientNegativeTest {

    @Test //(description = "add a vehicle to a client")
    //passa e não deveria passar
    public void testAddVehicleToClientNegative() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")

                .build();

        VehicleCalls vehicleCalls = retrofit.create(VehicleCalls.class);

        // Cria um objeto Car com ID inválido (por exemplo, ID negativo)
        Car car = new Car();
        car.setId(-1);
        car.setBrand("InvalidBrand");
        car.setModel("InvalidModel");

        Integer clientId = 1;

        Call<Void> call = vehicleCalls.addVehicleToClient(car.getId(), clientId);

        try {
            // Execute a chamada
            Response<Void> response = call.execute();

            // Verifica se a chamada não foi bem-sucedida
            assertFalse(response.isSuccessful());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

