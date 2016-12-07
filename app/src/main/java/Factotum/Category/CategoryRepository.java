package Factotum.Category;

import java.io.IOException;
import java.util.ArrayList;

import Factotum.Data;
import Factotum.JsonResponse;
import dah.budgetapp.UiRefresh;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository
{
    private final CategoryClient client;
    private UiRefresh refresher;
    private final int REFRESH_LIST = 0;

    public CategoryRepository(CategoryClient client, UiRefresh refresher)
    {
        this.client = client;
        this.refresher = refresher;
    }

    public Category create(Category category)
    {
        Call<Data> call = client.create(category);

        try
        {
            return (Category) call.execute().body().getAttributes();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public Category find(int id)
    {
        Call<Data> call = client.find(id);

        try
        {
            return (Category) call.execute().body().getAttributes();

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Category> findAll()
    {
        Call<JsonResponse<Data<Category>>> call = client.findAll();
        final ArrayList<Category> categories = new ArrayList<>();

        call.enqueue(new Callback<JsonResponse<Data<Category>>>()
        {
            @Override
            public void onResponse(Call<JsonResponse<Data<Category>>> call, Response<JsonResponse<Data<Category>>> response)
            {
                if (response.isSuccessful())
                {
                    for (Data data : response.body().getData())
                    {
                        categories.add((Category) data.getAttributes());
                    }
                    refresher.setList(categories);
                    refresher.sendHandlerMessage(REFRESH_LIST);
                }
            }

            @Override
            public void onFailure(Call<JsonResponse<Data<Category>>> call, Throwable t)
            {
                t.printStackTrace();
            }

        });

        return categories;
    }

    public boolean delete(int id)
    {
        Call<JsonResponse<Data>> call = client.delete(id);

        try
        {
            return call.execute().isSuccessful();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public Category update(int id, Category category)
    {
        Call<Data> call = client.update(id, category);

        try
        {
            return (Category) call.execute().body().getAttributes();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return category;
    }
}
