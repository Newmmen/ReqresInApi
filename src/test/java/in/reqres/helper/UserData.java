package in.reqres.helper;

import in.reqres.config.DataConfig;
import lombok.Data;
import org.aeonbits.owner.ConfigFactory;

@Data

public class UserData {
    static DataConfig data = ConfigFactory.create(DataConfig.class, System.getProperties());

    private final String userUndefinedEmail = data.getUserUndefinedEmail();
    private final String userEmail = data.getUserEmail();
    private final String userFirstName = data.getUserFirstName();
    private final String userLastName = data.getUserLastName();
    private final String userJob = data.getUserJob();
    private final String userUndefinedPassword = data.userUndefinedPassword();
    private final String userPassword = data.getUserPassword();
    private final String token = data.getToken();

}

