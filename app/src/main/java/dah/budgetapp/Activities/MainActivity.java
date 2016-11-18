package dah.budgetapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import dah.budgetapp.R;

public class MainActivity extends AppCompatActivity
{
    private Button newExpense;
    private Button statistics;
    private Button categories;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.viewsFinder();
    }

    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.goto_categories:
                Intent intent = new Intent(this, CategoriesActivity.class);
                startActivity(intent);
                break;
            case R.id.goto_statistics:
                System.out.println("b");
                break;
            case R.id.goto_new_expense:
                System.out.println("c");
                break;
        }
    }

    private void viewsFinder()
    {
        this.newExpense = (Button) findViewById(R.id.goto_new_expense);
        this.statistics = (Button) findViewById(R.id.goto_statistics);
        this.categories = (Button) findViewById(R.id.goto_categories);
    }
}
