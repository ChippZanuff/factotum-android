package dah.budgetapp.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import dah.budgetapp.Categories.Category;
import dah.budgetapp.R;
import dah.budgetapp.StatisticsAdapter;

public class StatisticsActivity extends AppCompatActivity
{
    private ListView categoriesHolder;
    private Toolbar toolbar;
    private ActionBar bar;
    private StatisticsAdapter adapter;
    private List<Category> categories = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_activity);

        this.toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(this.toolbar);

        this.bar = getSupportActionBar();

        if(this.bar != null)
        {
            this.bar.setDisplayHomeAsUpEnabled(true);
            this.bar.setTitle(R.string.statistics_title);
        }

        this.categories.add(new Category("first", 400));
        this.categories.add(new Category("second", 300));
        this.categories.add(new Category("third", 100));
        this.categories.add(new Category("fourth", 200));
        this.categories.add(new Category("fifth", 50));

        this.adapter = new StatisticsAdapter(this, R.layout.statistics_holder_view, this.categories);

        this.categoriesHolder = (ListView) findViewById(R.id.listview_statistics);

        this.categoriesHolder.setAdapter(this.adapter);
    }
}