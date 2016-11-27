package dah.budgetapp.Categories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import dah.budgetapp.R;

public class CategoriesAdapter extends BaseAdapter
{
    private ArrayList<Category> categories;
    private LayoutInflater inflater;

    public CategoriesAdapter(Context context, ArrayList<Category> categories)
    {
        this.categories = categories;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return categories.size();
    }

    @Override
    public Object getItem(int i)
    {
        return categories.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup)
    {
        View view = convertView;

        if(view == null)
        {
            view  = inflater.inflate(R.layout.holder_view_categories, viewGroup, false);
        }

        Category category = (Category) this.getItem(i);

        ((TextView) view.findViewById(R.id.view_category_name)).setText(category.getName());
        ((TextView) view.findViewById(R.id.view_category_money_sum)).setText(Integer.toString(category.getSum()));

        return view;
    }
}
