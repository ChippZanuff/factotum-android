package dah.budgetapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Factotum.GuestSession.GuestClient;
import Factotum.GuestSession.GuestEventBus;
import Factotum.ServiceGenerator;
import Factotum.Token;
import Factotum.UserCredentials;
import dah.budgetapp.BudgetApp;
import dah.budgetapp.R;
import rx.SingleSubscriber;
import rx.Subscription;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    private String stringUsername, stringPassword;
    private final String TAG = this.getClass().getSimpleName();
    private EditText etUsername, etPassword;
    private Button register, login;
    private BudgetApp application;
    private Subscription subscription;
    private GuestEventBus eventBus;
    private UserCredentials credentials;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.findViews();
        this.eventBus = new GuestEventBus(ServiceGenerator.guestService(GuestClient.class));
        this.application = (BudgetApp) getApplication();
        this.etUsername.setText("demo@factotum.app");
        this.etPassword.setText("secret");
        this.register.setOnClickListener(this);
        this.login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.register:
                Log.d(this.TAG, "");
                break;
            case R.id.login:
                Log.d(this.TAG, "Starting to validate entered data");

                this.stringUsername = this.etUsername.getText().toString();
                this.stringPassword = this.etPassword.getText().toString();

                if(this.stringPassword.length() < 5)
                {
                    Log.d(this.TAG, "Password length is less then 6 symbols");
                }
                else
                {
                    Log.d(this.TAG, "Validated");
                    this.credentials = new UserCredentials(this.stringUsername, this.stringPassword);
                    this.subscription = eventBus.authenticationObservable(this.authenticationObserver(getApplicationContext()), credentials);
                }
                break;
        }
    }

    private void findViews()
    {
        this.etPassword = (EditText) findViewById(R.id.password);
        this.etUsername = (EditText) findViewById(R.id.user_name);
        this.register = (Button) findViewById(R.id.register);
        this.login = (Button) findViewById(R.id.login);
    }

    private SingleSubscriber<Token> authenticationObserver(final Context context)
    {
        return new SingleSubscriber<Token>()
        {
            @Override
            public void onSuccess(Token value)
            {
                application.setToken(value);
                application.setCredentials(credentials);

                startActivity(new Intent(context, MainActivity.class));
            }

            @Override
            public void onError(Throwable error)
            {

            }
        };
    }
}
