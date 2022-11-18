package in.reqres.config;
import org.aeonbits.owner.Config;
@Config.Sources({
        "classpath:data.properties"

})

public interface DataConfig extends Config{
    @Key("user.email")
    String getUserEmail();

    @Key("user.undefined.email")
    String getUserUndefinedEmail();

    @Key("user.undefined.password")
    String userUndefinedPassword();

    @Key("user.first.name")
    String getUserFirstName();

    @Key("user.job")
    String getUserJob();

    @Key("user.lastname")
    String getUserLastName();

    @Key("user.password")
    String getUserPassword();

    @Key("token")
    String getToken();




}
