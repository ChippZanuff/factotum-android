package Factotum.Type;

import java.io.IOException;
import java.util.ArrayList;

import Factotum.Data;
import Factotum.JsonResponse;
import retrofit2.Call;

public class TypeRepository
{
    private final TypeClient client;

    public TypeRepository(TypeClient client)
    {
        this.client = client;
    }

    public Boolean create(String categoryId, Type type)
    {
        Call<Data<Type>> call = client.create(categoryId, type);

        try
        {
            return call.execute().isSuccessful();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public Type find(String categoryId, String typeId)
    {
        Call<Data<Type>> call = client.find(categoryId, typeId);

        try
        {
            return call.execute().body().getAttributes();

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Type> findAll(String categoryId)
    {
        Call<JsonResponse<Data<Type>>> call = client.findAll(categoryId);
        final ArrayList<Type> types = new ArrayList<>();

        try
        {
            ArrayList<Data<Type>> datas = call.execute().body().getData();

            for (Data data : datas)
            {
                types.add(new Type(
                        ((Type) data.getAttributes()).getName()
                    )
                );
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return types;
    }

    public boolean delete(String categoryId, String typeId)
    {
        Call<JsonResponse<Data<Type>>> call = client.delete(categoryId, typeId);

        try
        {
            return call.execute().isSuccessful();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public Type update(String categoryId, String typeId, Type type)
    {
        Call<Data<Type>> call = client.update(categoryId, typeId, type);

        try
        {
            return call.execute().body().getAttributes();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return type;
    }
}
