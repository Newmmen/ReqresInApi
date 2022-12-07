package in.reqres.models.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseDto {
    String name,
            job,
            token,
            id;
}
