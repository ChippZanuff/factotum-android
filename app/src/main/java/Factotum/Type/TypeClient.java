package Factotum.Type;

import Factotum.Data;
import Factotum.JsonResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TypeClient
{
    @GET("api/v1/categories/{id}/types")
    Call<JsonResponse<Data<Type>>> findAll(@Path("categoryId") String categoryId);

    @GET("api/v1/categories/{categoryId}/types/{id}")
    Call<Data<Type>> find(@Path("categoryId") String categoryId, @Path("typeId") String typeId);

    @DELETE("api/v1/categories/{categoryId}/types/{id}")
    Call<JsonResponse<Data<Type>>> delete(@Path("categoryId") String categoryId, @Path("typeId") String typeId);

    @POST("api/v1/categories/{categoryId}/types")
    Call<Data<Type>> create(@Path("categoryId") String categoryId, @Body Type type);

    @PUT("api/v1/categories/{categoryId}/types/{typeId}")
    Call<Data<Type>> update(@Path("categoryId") String categoryId, @Path("typeId") String typeId, @Body Type type);
}
