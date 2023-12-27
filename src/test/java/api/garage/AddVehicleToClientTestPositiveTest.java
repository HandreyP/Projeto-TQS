package api.garage;

import api.calls.VehicleCalls;
import api.mappings.Car;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertNotNull;

public class AddVehicleToClientTestPositiveTest {

    @Test
    public void testAddVehicleToClient() {
        // Cria um objeto Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        VehicleCalls vehicleCalls = retrofit.create(VehicleCalls.class);

        Car car = new Car();
        car.setId(1);
        car.setBrand("Brand");
        car.setModel("Model");
        // ... preencha os outros campos conforme necessário

        Integer clientId = 1;

        Call<Void> call = vehicleCalls.addVehicleToClient(car.getId(), clientId);

        assertNotNull(call);
    }
}
