package mosque.list.com.mosque.network;

import mosque.list.com.mosque.entity.DataResponse;
import mosque.list.com.mosque.entity.Login;
import mosque.list.com.mosque.entity.Resgister;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by domikado on 3/24/17.
 */

public interface NetService {

    @POST("login")
    @FormUrlEncoded
    Observable<Login> logins(@Field("username") String username,
                                @Field("password") String password);


    @POST("register")
    @FormUrlEncoded
    Observable<Resgister> registers(@Field("username") String username,
                                              @Field("password") String password,
                                              @Field("email") String email,
                                              @Field("full_name") String full_name,
                                              @Field("last_name") String last_name);

    @GET("")
    Observable<DataResponse> getData();

}
