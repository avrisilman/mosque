package mosque.list.com.mosque.network;

import mosque.list.com.mosque.entity.DataResponse;
import mosque.list.com.mosque.entity.Login;
import mosque.list.com.mosque.entity.Resgister;

/**
 * Created by domikado on 3/25/17.
 */

public class CallBack {

    public interface CallData{
        void onError(String error);
        void onSuccess(DataResponse dataResponse);
    }

    public interface CallLogin{
        void onError(String error);
        void onSuccess(Login login);
    }

    public interface CallRegister{
        void onError(String error);
        void onSuccess(Resgister resgister);
    }

}
