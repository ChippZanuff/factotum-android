package Factotum.Category;

import Factotum.Data;
import Factotum.JsonResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CategoryClient
{
    @GET("api/v1/categories")
    Call<JsonResponse<Data<Category>>> findAll();

    @GET("api/v1/categories/{id}")
    Call<Data> find(@Path("id") int id);

    @DELETE("api/v1/categories/{id}")
    Call<JsonResponse<Data>> delete(@Path("id") int id);

    @POST("api/v1/categories")
    Call<Data> create(@Body Category category);

    @PUT("api/v1/categories/{id}")
    Call<Data> update(@Path("id") int id, @Body Category category);
}
