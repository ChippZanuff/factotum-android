package Factotum.GuestSession;

import Factotum.Token;
import Factotum.UserCredentials;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GuestClient
{
    @POST("/api/v1/auth/token")
    Call<Token> authenticate(@Body UserCredentials credentials);
}
