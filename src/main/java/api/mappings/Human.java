package api.mappings;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Human {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty ("lastName")
    private String lastName;
    @JsonProperty("address")
    private String address;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonProperty("city")
    private String city;
    @JsonProperty("country")
    private String country;
    @JsonProperty("phoneNumber")
    private Integer phoneNumber;
    @JsonProperty("nif")
    private Integer nif;
    @JsonProperty("birthDate")
    private String birthDate;
    @JsonProperty("clientDate")
    private String clientDate;
    @JsonProperty("vehicles")
    private List<Car> cars;
}

