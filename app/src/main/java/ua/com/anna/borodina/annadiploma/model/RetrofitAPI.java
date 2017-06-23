package ua.com.anna.borodina.annadiploma.model;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import ua.com.anna.borodina.annadiploma.model.dao.GetDataResultResponse;
import ua.com.anna.borodina.annadiploma.model.dao.ResponseStatusUpdate;
import ua.com.anna.borodina.annadiploma.model.dao.SendMessageRequest;
import ua.com.anna.borodina.annadiploma.model.dao.UpdateRequestBody;


public interface RetrofitAPI {
    @Headers({"Content-type: application/json",
            "Authorization: key=AAAAAr2fwO8:APA91bGmd20OzYV9-dwzVUME6jQSQ1NEVdckh_NHtH8VNvQ80sf56GW7FlppOUK13Ilzl7v3QNA2YnCWqi7bAGY7VaIqofZAX_ORTaP8z9R8dorwl0bcAbtr55gk0VqxBFsYVVS5oUCv"})
    @POST("fcm/send")
    Call<ResponseBody> authRequest(@Body SendMessageRequest sendMessageRequest);


    @GET("/getAll")
    Call<GetDataResultResponse> getDataFromServer(@HeaderMap Map<String,String> headers);

    @POST("/add")
    Call<ResponseStatusUpdate> updateDataOnServer(@HeaderMap Map<String,String> headers, @Body UpdateRequestBody body);
}
