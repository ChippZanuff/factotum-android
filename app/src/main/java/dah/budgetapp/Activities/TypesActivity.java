package dah.budgetapp.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import Factotum.ServiceGenerator;
import Factotum.Token;
import Factotum.Type.Type;
import Factotum.Type.TypeClient;
import Factotum.Type.TypeEventBus;
import Factotum.Type.TypeRepository;
import dah.budgetapp.Adapters.TypesAdapter;
import dah.budgetapp.BudgetApp;
import dah.budgetapp.Dialogs.WaitDialog;
import dah.budgetapp.R;
import rx.SingleSubscriber;
import rx.Subscription;

public class TypesActivity extends AppCompatActivity
{
    private ArrayList<Type> types = new ArrayList<>();
    private ListView list;
    private TypesAdapter adapter;
    private TypeEventBus eventBus;
    private WaitDialog waitDialog;
    private Subscription subscription;
    private Token token;
    private String categoryId;
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_types);

        this.viewsFinder();
        this.waitDialog = new WaitDialog(this);

        this.adapter = new TypesAdapter(this, this.types);
        this.list.setAdapter(this.adapter);

        BudgetApp application = (BudgetApp) getApplication();
        this.token = application.getToken();

        this.categoryId = getIntent().getStringExtra("Id");

        this.eventBus = new TypeEventBus(new TypeRepository(ServiceGenerator.authorizedService(TypeClient.class, this.getTokenString())));
        this.subscription = this.eventBus.observableList(this.categoryId, this.typesListObserver());
    }

    @Override
    protected void onDestroy()
    {
        if(this.subscription != null && !this.subscription.isUnsubscribed())
        {
            this.subscription.unsubscribe();
        }
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.action_bar_menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_add_category_button:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void viewsFinder()
    {
        this.list = (ListView) findViewById(R.id.list_types);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        ActionBar bar = getSupportActionBar();

        if (bar != null)
        {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle(R.string.title_types);
        }
    }

    private String getTokenString()
    {
        return this.token.getToken_type() + " " + this.token.getAccess_token();
    }

    private SingleSubscriber<ArrayList<Type>> typesListObserver()
    {
        return new SingleSubscriber<ArrayList<Type>>()
        {
            @Override
            public void onSuccess(ArrayList<Type> value)
            {
                types = value;
                adapter.refresh(types);
                waitDialog.dismissDialog();
            }

            @Override
            public void onError(Throwable error)
            {
                error.printStackTrace();
            }
        };
    }

    private SingleSubscriber<Type> deletionTypeObserver()
    {
        return new SingleSubscriber<Type>()
        {
            @Override
            public void onSuccess(Type value)
            {
                int index = types.indexOf(value);
                if(index >= 0)
                {
                    types.remove(index);
                    adapter.refresh(types);
                    waitDialog.dismissDialog();
                }
                else
                {
                    Log.d(TAG, "Updating element doesnt exist");
                }
            }

            @Override
            public void onError(Throwable error)
            {
                error.printStackTrace();
            }
        };
    }

    private SingleSubscriber<Type> updationTypeObserver()
    {
        return new SingleSubscriber<Type>()
        {
            @Override
            public void onSuccess(Type value)
            {
                int index = types.indexOf(value);
                if(index >= 0)
                {
                    types.set(index, value);
                    adapter.refresh(types);
                    waitDialog.dismissDialog();
                }
                else
                {
                    Log.d(TAG, "Updating element doesnt exist");
                }
            }

            @Override
            public void onError(Throwable error)
            {
                error.printStackTrace();
            }
        };
    }

    private SingleSubscriber<Type> creationTypeObserver()
    {
        return new SingleSubscriber<Type>()
        {
            @Override
            public void onSuccess(Type value)
            {
                types.add(0, value);
                adapter.refresh(types);
                waitDialog.dismissDialog();
            }

            @Override
            public void onError(Throwable error)
            {
                error.printStackTrace();
            }
        };
    }
}
