package Factotum;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator
{
    public static final String API_URL = "http://api.factotum.app/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S guestService(Class<S> serviceClass)
    {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor()
        {
            public Response intercept(Interceptor.Chain chain) throws IOException
            {
                final Request request = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "application/json")
                        .build();

                return chain.proceed(request);
            }
        });

        Retrofit retrofit = builder
                .client(httpClient.build())
                .build();
        return retrofit.create(serviceClass);
    }

    public static <S> S authorizedService(Class<S> serviceClass, final String token) {
        OkHttpClient.Builder authenticatedBuilder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor()
                {
                    public Response intercept(Chain chain) throws IOException
                    {
                        final Request request = chain.request().newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .addHeader("Accept", "application/json")
                                .addHeader("Authorization", token)
                                .build();

                        return chain.proceed(request);
                    }
                });


        Retrofit retrofit = builder
                .client(authenticatedBuilder.build())
                .build();
        return retrofit.create(serviceClass);
    }
}
