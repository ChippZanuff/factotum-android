package dah.budgetapp;

import android.app.Application;

import Factotum.Token;
import Factotum.UserCredentials;

public class BudgetApp extends Application
{
    private Token token;
    private UserCredentials credentials;

    public Token getToken()
    {
        return token;
    }

    public void setToken(Token token)
    {
        this.token = token;
    }

    public UserCredentials getCredentials()
    {
        return credentials;
    }

    public void setCredentials(UserCredentials credentials)
    {
        this.credentials = credentials;
    }
}
