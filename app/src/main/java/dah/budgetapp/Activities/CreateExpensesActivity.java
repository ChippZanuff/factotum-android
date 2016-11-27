package dah.budgetapp.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import dah.budgetapp.R;

public class CreateExpensesActivity extends AppCompatActivity
{
    private TextView typedNumbers;
    private GridView gridNumbers;
    private String[] values = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "C", "0", "X" };
    private Toolbar toolbar;
    private ActionBar bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_expenses);

        this.viewFinder();

        setSupportActionBar(this.toolbar);

        this.bar = getSupportActionBar();

        if(this.bar != null)
        {
            this.bar.setDisplayHomeAsUpEnabled(true);
            this.bar.setTitle(R.string.title_statistics);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.calc_item, R.id.calc_item, values);
        gridNumbers.setAdapter(adapter);
    }

    private void viewFinder()
    {
        this.typedNumbers = (TextView) findViewById(R.id.expenses_edit_text);
        this.gridNumbers = (GridView) findViewById(R.id.expenses_calc_button_field);
        this.toolbar = (Toolbar) findViewById(R.id.my_toolbar);
    }
}
