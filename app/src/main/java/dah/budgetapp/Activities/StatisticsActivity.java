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
        setContentView(R.layout.activity_statistics);

        this.toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(this.toolbar);

        this.bar = getSupportActionBar();

        if(this.bar != null)
        {
            this.bar.setDisplayHomeAsUpEnabled(true);
            this.bar.setTitle(R.string.title_statistics);
        }

        this.categories.add(new Category("first"));
        this.categories.add(new Category("second"));
        this.categories.add(new Category("third"));
        this.categories.add(new Category("fourth"));
        this.categories.add(new Category("fifth"));

        this.adapter = new StatisticsAdapter(this, R.layout.holder_view_statistics, this.categories);

        this.categoriesHolder = (ListView) findViewById(R.id.list_statistics);

        this.categoriesHolder.setAdapter(this.adapter);
    }
}
