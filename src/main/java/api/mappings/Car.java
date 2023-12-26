package api.mappings;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("client")
    private Integer client;
    @JsonProperty ("brand")
    private String brand;
    @JsonProperty("model")
    private String model;
    @JsonProperty("plateYear")
    private Integer plateYear;
    @JsonProperty("type")
    private String type;
    @JsonProperty("plate")
    private String plate;
    @JsonProperty("active")
    private boolean active;
}
