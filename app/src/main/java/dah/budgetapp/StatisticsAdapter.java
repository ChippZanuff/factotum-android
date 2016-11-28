package dah.budgetapp;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import dah.budgetapp.Categories.Category;

public class StatisticsAdapter extends BaseAdapter
{
    private int resource;
    private Context context;
    private List<Category> categories;

    public StatisticsAdapter(Context context, int resource,  List<Category> categories)
    {
        this.context = context;
        this.resource = resource;
        this.categories = categories;
    }

    @Override
    public int getCount()
    {
        return this.categories.size();
    }

    @Override
    public Object getItem(int i)
    {
        return this.categories.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup)
    {
        View view = convertView;

        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(this.resource, viewGroup, false);
        }

        Category category = this.categories.get(i);

        ((TextView) view.findViewById(R.id.name_statistics)).setText(category.getName());
        ((TextView) view.findViewById(R.id.sum_statistics)).setText(Integer.toString(10));

        ImageButton menu = (ImageButton) view.findViewById(R.id.popup_menu);

        menu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                PopupMenu popupMenu = new PopupMenu(context, view);
                popupMenu.getMenuInflater().inflate(R.menu.item_settings_menu, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {
                        switch (item.getItemId())
                        {
                            case R.id.edit:

                                break;
                            case R.id.delete:

                                break;
                        }

                        return true;
                    }
                });
            }
        });

        return view;
    }
}
