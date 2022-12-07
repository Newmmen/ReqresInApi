package in.reqres.models.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)


public class UserDataResponseDto {
    private Data data;

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        int id;
        @JsonProperty("first_name")
        String firstName;
        @JsonProperty("last_name")
        String lastName;

    }

}
