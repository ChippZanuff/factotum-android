package Factotum.Category;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CategoriesEventBus
{
    private CategoryRepository repository;

    public CategoriesEventBus(CategoryRepository repository)
    {
        this.repository = repository;
    }

    public Subscription findAll(SingleSubscriber<ArrayList<Category>> subscriber)
    {
        Single<ArrayList<Category>> single = Single.fromCallable(new Callable<ArrayList<Category>>()
        {
            @Override
            public ArrayList<Category> call() throws Exception
            {
                return repository.findAll();
            }
        });

        return single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public Subscription findById(final int id, SingleSubscriber<Category> subscriber)
    {
        Single<Category> single = Single.fromCallable(new Callable<Category>()
        {
            @Override
            public Category call() throws Exception
            {
                return repository.find(id);
            }
        });

        return single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public Subscription create(final Category category, SingleSubscriber<Category> subscriber)
    {
        Single<Category> single = Single.fromCallable(new Callable<Category>()
        {
            @Override
            public Category call() throws Exception
            {
                return repository.create(category);
            }
        });

        return single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public Subscription deleteById(final int id, SingleSubscriber<Boolean> subscriber)
    {
        Single<Boolean> single = Single.fromCallable(new Callable<Boolean>()
        {
            @Override
            public Boolean call() throws Exception
            {
                return repository.delete(id);
            }
        });

        return single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
