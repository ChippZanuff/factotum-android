package dah.budgetapp.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import dah.budgetapp.Categories.CategoriesAdapter;
import dah.budgetapp.Categories.Category;
import dah.budgetapp.R;

public class CategoriesActivity extends AppCompatActivity
{
    private Button createNewCategory;
    private ListView categoriesHolder;
    private CategoriesAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        ActionBar bar = getSupportActionBar();


        this.categoriesHolder = (ListView) findViewById(R.id.list_categories_holder);
        this.createNewCategory = (Button) findViewById(R.id.create_new_category);

        this.adapter = new CategoriesAdapter(this, this.getCategories());

        this.categoriesHolder.setAdapter(this.adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_add_category_button:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void viewsFinder()
    {

    }

    public ArrayList<Category> getCategories()
    {
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(new Category("first", 200));
        categories.add(new Category("second", 300));
        categories.add(new Category("third", 150));
        categories.add(new Category("fourth", 400));
        categories.add(new Category("fifth", 230));

        return categories;
    }
}
