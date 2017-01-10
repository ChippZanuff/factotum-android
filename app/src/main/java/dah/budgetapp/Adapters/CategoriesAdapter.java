package dah.budgetapp.Adapters;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import Factotum.Category.Category;
import Factotum.AdapterUpdater;
import dah.budgetapp.R;

public class CategoriesAdapter extends BaseAdapter implements AdapterUpdater
{
    private ArrayList<Category> categories;
    private LayoutInflater inflater;
    private Context context;

    public CategoriesAdapter(Context context, ArrayList<Category> categories)
    {
        this.context = context;
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

    @Override
    public void refresh(ArrayList list)
    {
        this.categories.clear();
        this.categories.addAll(list);
        this.notifyDataSetChanged();
    }
}
