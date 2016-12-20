package Factotum.Category;

import java.io.IOException;
import java.util.ArrayList;

import Factotum.Data;
import Factotum.JsonResponse;
import retrofit2.Call;

public class CategoryRepository
{
    private final CategoryClient client;

    public CategoryRepository(CategoryClient client)
    {
        this.client = client;
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

        try
        {
            ArrayList<Data<Category>> datas = call.execute().body().getData();

            for (Data data : datas)
            {
                categories.add((Category) data.getAttributes());
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }

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
