package dah.budgetapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.intrusoft.scatter.ChartData;
import com.intrusoft.scatter.PieChart;

import java.util.ArrayList;
import java.util.List;

import dah.budgetapp.Categories.Category;
import dah.budgetapp.R;

public class MainActivity extends AppCompatActivity
{
    private ImageButton newExpense;
    private ImageButton statistics;
    private ImageButton categories;
    private PieChart chart;
    private float[] yData = { 5, 10, 15, 30, 40 };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.viewsFinder();

        this.pieChart();
    }

    private void pieChart()
    {
        ArrayList<Category> categories = new ArrayList<>();
        List<ChartData> data = new ArrayList<>();

        for(int i = 0; i < categories.size(); i++)
        {
            data.add(new ChartData(categories.get(i).getName(), yData[i]));
        }

        this.chart.setChartData(data);
    }

    public void onClick(View view)
    {
        Intent intent;
        switch (view.getId())
        {
            case R.id.goto_categories:
                intent = new Intent(this, CategoriesActivity.class);
                startActivity(intent);
                break;
            case R.id.goto_statistics:
                intent = new Intent(this, StatisticsActivity.class);
                startActivity(intent);
                break;
            case R.id.goto_new_expense:
                System.out.println("c");
                break;
        }
    }

    private void viewsFinder()
    {
        this.newExpense = (ImageButton) findViewById(R.id.goto_new_expense);
        this.statistics = (ImageButton) findViewById(R.id.goto_statistics);
        this.categories = (ImageButton) findViewById(R.id.goto_categories);
        this.chart = (PieChart) findViewById(R.id.pie_chart);
    }
}
