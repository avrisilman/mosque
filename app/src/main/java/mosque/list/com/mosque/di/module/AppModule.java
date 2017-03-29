package mosque.list.com.mosque.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mosque.list.com.mosque.logins.LoginActivityPresenter;
import mosque.list.com.mosque.network.Builders;
import mosque.list.com.mosque.network.MainService;
import mosque.list.com.mosque.network.NetService;
import mosque.list.com.mosque.register.RegisterActivityPresenter;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by domikado on 3/25/17.
 */
@Module
public class AppModule {
    Context context;

    public AppModule(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return context;
    }

    @Provides
    @Singleton
    public Builders provideRetrofitBuilder(){
        return new Builders();
    }

    @Provides
    @Singleton
    public AppData provideAppData(){
        return new AppData(context);
    }


    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .create();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {

        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://find-mosque.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor headerInterceptor = chain -> {
            Request.Builder requestBuilder = chain.request().newBuilder()
                    .header("Accept", "application/json")
                    .header("APP-Version","1.2")
                    .header("OS","Android")
                    .header("OS-Version","4.1")
                    .header("Device-Id","adasd");

            Request request = requestBuilder.build();
            return chain.proceed(request);
        };

        return new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(headerInterceptor)
                .build();
    }

    @Singleton
    @Provides
    NetService provideApiService(Retrofit retrofit) {
        return retrofit.create(NetService.class);
    }

    @Singleton
    @Provides
    MainService provideMainService(NetService service) {
        return new MainService(service);
    }

    @Singleton
    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }

    @Singleton
    @Provides
    LoginActivityPresenter provideLoginActivityPresenter(Context context) {
        return new LoginActivityPresenter(context);
    }

    @Singleton
    @Provides
    RegisterActivityPresenter provideRegisterActivityPresenter(Context context){
        return new RegisterActivityPresenter(context);
    }

}
