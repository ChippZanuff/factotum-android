package Factotum.GuestSession;

import java.util.concurrent.Callable;

import Factotum.Token;
import Factotum.UserCredentials;
import retrofit2.Call;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GuestEventBus
{
    private GuestClient client;
    private final String TAG = this.getClass().getSimpleName();

    public GuestEventBus(GuestClient client)
    {
        this.client = client;
    }

    public Subscription authenticationObservable(SingleSubscriber<Token> subscriber, final UserCredentials credentials)
    {
        Single<Token> single = Single.fromCallable(new Callable<Token>()
        {
            @Override
            public Token call() throws Exception
            {
                Call<Token> tokenCall = client.authenticate(credentials);
                Token token = tokenCall.execute().body();

                return token;
            }
        });

        return single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
