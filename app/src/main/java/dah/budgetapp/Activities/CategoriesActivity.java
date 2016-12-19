package dah.budgetapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Factotum.Category.CategoriesEventBus;
import Factotum.Category.Category;
import Factotum.Category.CategoryClient;
import Factotum.Category.CategoryRepository;
import Factotum.ServiceGenerator;
import dah.budgetapp.Categories.CategoriesAdapter;
import dah.budgetapp.Dialogs.WaitDialog;
import dah.budgetapp.R;
import rx.SingleSubscriber;
import rx.Subscription;

public class CategoriesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    private ListView list;
    private CategoriesAdapter adapter;
    private WaitDialog waitDialog;
    private ArrayList<Category> categories;
    private CategoriesEventBus eventBus;
    private Subscription subscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        this.list = (ListView) findViewById(R.id.list_categories);

        this.waitDialog = new WaitDialog(this);
        this.categories = new ArrayList<>();

        this.adapter = new CategoriesAdapter(this, categories);

        list.setAdapter(this.adapter);

        this.eventBus = new CategoriesEventBus(new CategoryRepository(ServiceGenerator.createService(CategoryClient.class)));

        this.subscription = this.eventBus.findAll(this.listObserver());

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        ActionBar bar = getSupportActionBar();
        
        if (bar != null)
        {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle(R.string.title_categories);
        }

        list.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Intent intent = new Intent(this, TypesActivity.class);
        startActivity(intent);
    }

    public void viewsFinder()
    {

    }

    private SingleSubscriber<ArrayList<Category>> listObserver()
    {
        return new SingleSubscriber<ArrayList<Category>>()
        {
            @Override
            public void onSuccess(ArrayList<Category> value)
            {
                categories = value;
                adapter.refresh(categories);
                waitDialog.dismissDialog();
            }

            @Override
            public void onError(Throwable error)
            {

            }
        };
    }

    private SingleSubscriber<Category> getByIdObserver()
    {
        return new SingleSubscriber<Category>()
        {
            @Override
            public void onSuccess(Category value)
            {

            }

            @Override
            public void onError(Throwable error)
            {

            }
        };
    }

    private SingleSubscriber<Category> createObserver()
    {
        return new SingleSubscriber<Category>()
        {
            @Override
            public void onSuccess(Category value)
            {
                categories.add(value);
                adapter.refresh(categories);
                waitDialog.dismissDialog();
            }

            @Override
            public void onError(Throwable error)
            {

            }
        };
    }

    private SingleSubscriber<Boolean> deleteObserver()
    {
        return new SingleSubscriber<Boolean>()
        {
            @Override
            public void onSuccess(Boolean value)
            {
                if(value)
                {
                    subscription = eventBus.findAll(listObserver());
                }
                else
                {
                    Toast.makeText(CategoriesActivity.this, "Category is not deleted", Toast.LENGTH_SHORT).show();
                }

                waitDialog.dismissDialog();
            }

            @Override
            public void onError(Throwable error)
            {

            }
        };
    }
}
