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

import java.util.ArrayList;
import java.util.List;

import Factotum.Category.Category;
import Factotum.Category.CategoryClient;
import Factotum.Category.CategoryRepository;
import Factotum.Data;
import Factotum.ServiceGenerator;
import dah.budgetapp.Categories.CategoriesAdapter;
import dah.budgetapp.Dialogs.WaitDialog;
import dah.budgetapp.R;
import dah.budgetapp.UiRefresh;

public class CategoriesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    private ListView list;
    private CategoriesAdapter adapter;
    private WaitDialog waitDialog;
    private ArrayList<Category> categories;
    private UiRefresh refresher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        this.list = (ListView) findViewById(R.id.list_categories);

        this.waitDialog = new WaitDialog(this);
        this.categories = new ArrayList<>();

        this.adapter = new CategoriesAdapter(this, categories);
        this.refresher = new UiRefresh(adapter, this.waitDialog);

        list.setAdapter(this.adapter);

        CategoryRepository repository = new CategoryRepository(ServiceGenerator.createService(CategoryClient.class));

        this.categories = repository.findAll();

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

    public ArrayList<Category> getCategories(List<Data> categories1)
    {
        ArrayList<Category> categories = new ArrayList<>();
        for (Data data : categories1)
        {
            categories.add((Category) data.getAttributes());
        }

        return categories;
    }
}
