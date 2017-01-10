package Factotum.Type;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TypeEventBus
{
    private TypeRepository repository;

    public TypeEventBus(TypeRepository repository)
    {
        this.repository = repository;
    }

    public Subscription observableList(final String categoryId, SingleSubscriber<ArrayList<Type>> subscriber)
    {
        Single<ArrayList<Type>> single = Single.fromCallable(new Callable<ArrayList<Type>>()
        {
            @Override
            public ArrayList<Type> call() throws Exception
            {
                return repository.findAll(categoryId);
            }
        });

        return single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public Subscription observableFindById(final String categoryId, final String typeId, SingleSubscriber<Type> subscriber)
    {
        Single<Type> single = Single.fromCallable(new Callable<Type>()
        {
            @Override
            public Type call() throws Exception
            {
                return repository.find(categoryId, typeId);
            }
        });

        return single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public Subscription observableCreate(final String categoryId, final Type type, SingleSubscriber<Boolean> subscriber)
    {
        Single<Boolean> single = Single.fromCallable(new Callable<Boolean>()
        {
            @Override
            public Boolean call() throws Exception
            {
                return repository.create(categoryId, type);
            }
        });

        return single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public Subscription observableDeleteById(final String categoryId, final String typeId, SingleSubscriber<Boolean> subscriber)
    {
        Single<Boolean> single = Single.fromCallable(new Callable<Boolean>()
        {
            @Override
            public Boolean call() throws Exception
            {
                return repository.delete(categoryId, typeId);
            }
        });

        return single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
