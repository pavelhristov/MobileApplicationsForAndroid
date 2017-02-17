package com.data;

import com.data.base.BaseData;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpData<T> implements BaseData{

    private final String apiUrl;
    private final OkHttpClient client;
    private final Gson gson;
    private final Class<T> klass;
    private final Class<T[]> klassArray;

    public HttpData(String apiUrl, Class<T> klass, Class<T[]> klassArray) {
        this.apiUrl = apiUrl;
        this.client = new OkHttpClient();
        this.gson = new Gson();
        this.klass = klass;
        this.klassArray = klassArray;
    }

    @Override
    public Observable getAll() {
        return Observable
                .create(new ObservableOnSubscribe<T[]>() {
                    @Override
                    public void subscribe(ObservableEmitter<T[]> e) throws Exception {
                        Request req = new Request.Builder()
                                .url(apiUrl)
                                .build();

                        Response res = client.newCall(req).execute();

                        String json = res.body().string();

                        T[] result = gson.fromJson(json, klassArray);

                        e.onNext(result);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable search(String pattern) {
        final String searchPattern = pattern;
        return Observable
                .create(new ObservableOnSubscribe<T[]>() {
                    @Override
                    public void subscribe(ObservableEmitter<T[]> e) throws Exception {
                        Request req = new Request.Builder()
                                .url(apiUrl + "/search/" + searchPattern)
                                .build();

                        Response res = client.newCall(req).execute();

                        String json = res.body().string();

                        T[] result = gson.fromJson(json, klassArray);

                        e.onNext(result);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
