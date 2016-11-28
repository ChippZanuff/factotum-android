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

import dah.budgetapp.Categories.CategoriesAdapter;
import dah.budgetapp.Categories.Category;
import dah.budgetapp.R;

public class CategoriesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    private ListView list;
    private CategoriesAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        ActionBar bar = getSupportActionBar();
        
        if (bar != null)
        {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle(R.string.title_categories);
        }

        this.list = (ListView) findViewById(R.id.list_categories);

        this.adapter = new CategoriesAdapter(this, this.getCategories());

        this.list.setAdapter(this.adapter);
        list.setOnItemClickListener(this);
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

    public ArrayList<Category> getCategories()
    {
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("first"));
        categories.add(new Category("second"));
        categories.add(new Category("third"));
        categories.add(new Category("fourth"));
        categories.add(new Category("fifth"));

        return categories;
    }
}
