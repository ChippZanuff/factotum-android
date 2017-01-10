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

import Factotum.Type.Type;
import dah.budgetapp.R;

public class TypesAdapter extends BaseAdapter
{
    private ArrayList<Type> types;
    private LayoutInflater inflater;
    private Context context;

    public TypesAdapter(Context context, ArrayList<Type> types)
    {
        this.types = types;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return this.types.size();
    }

    @Override
    public Object getItem(int i)
    {
        return this.types.get(i);
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
            view  = inflater.inflate(R.layout.holder_view_types, viewGroup, false);
        }

        Type type = (Type) this.getItem(i);

        ((TextView) view.findViewById(R.id.type_name)).setText(type.getName());

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
