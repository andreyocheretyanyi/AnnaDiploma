package ua.com.anna.borodina.annadiploma;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ua.com.anna.borodina.annadiploma.model.RetrofitAPI;


public class App extends Application {
    private static RetrofitAPI retrofitNtfc;
    private static RetrofitAPI retrofitServer;
    private static final String URL = "https://marcetplaceserver.herokuapp.com/";

    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofitNotifications = new Retrofit.Builder()
                .baseUrl("https://fcm.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitNtfc = retrofitNotifications.create(RetrofitAPI.class);

        Retrofit retrofitServer = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        App.retrofitServer = retrofitServer.create(RetrofitAPI.class);
    }


    public static RetrofitAPI getRetrofitNotifications() {
        return retrofitNtfc;
    }

    public static RetrofitAPI getRetrofitServer() {
        return retrofitServer;
    }
}
