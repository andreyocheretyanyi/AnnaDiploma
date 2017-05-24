package ua.com.anna.borodina.annadiploma;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ua.com.anna.borodina.annadiploma.model.RetrofitAPI;


public class App extends Application {
    private static RetrofitAPI retrofitAPI;
    private Retrofit retrofit;
    @Override
    public void onCreate() {
      super.onCreate();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://fcm.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
    }


    public static RetrofitAPI getRetrofitAPI(){
        return retrofitAPI;
    }
}
