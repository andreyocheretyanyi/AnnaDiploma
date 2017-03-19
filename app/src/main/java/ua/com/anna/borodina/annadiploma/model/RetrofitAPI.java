package ua.com.anna.borodina.annadiploma.model;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import ua.com.anna.borodina.annadiploma.model.dao.SendMessageRequest;

/**
 * Created by Андрей on 11.03.2017.
 */

public interface RetrofitAPI {
    @Headers({"Content-type: application/json",
            "Authorization: key=AAAAAr2fwO8:APA91bGmd20OzYV9-dwzVUME6jQSQ1NEVdckh_NHtH8VNvQ80sf56GW7FlppOUK13Ilzl7v3QNA2YnCWqi7bAGY7VaIqofZAX_ORTaP8z9R8dorwl0bcAbtr55gk0VqxBFsYVVS5oUCv"})
    @POST("fcm/send")
    Call<ResponseBody> authRequest(@Body SendMessageRequest sendMessageRequest);
}
