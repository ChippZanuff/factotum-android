package dah.budgetapp.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import dah.budgetapp.R;
import dah.budgetapp.Types.Type;
import dah.budgetapp.Types.TypesAdapter;

public class TypesActivity extends AppCompatActivity
{
    private ArrayList<Type> types;
    private ListView list;
    private TypesAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_types);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        ActionBar bar = getSupportActionBar();

        if (bar != null)
        {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setTitle(R.string.title_types);
        }

        this.types = new ArrayList<>();
        this.types.add(new Type("first"));
        this.types.add(new Type("second"));
        this.types.add(new Type("third"));
        this.types.add(new Type("fourth"));
        this.types.add(new Type("fifth"));

        this.adapter = new TypesAdapter(this, this.types);

        this.list = (ListView) findViewById(R.id.list_types);
        this.list.setAdapter(this.adapter);
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
}
