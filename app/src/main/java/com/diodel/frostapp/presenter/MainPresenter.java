package com.diodel.frostapp.presenter;

import android.util.Log;

import com.diodel.frostapp.interfaces.MainView;
import com.diodel.frostapp.model.ErrorResponse;
import com.diodel.frostapp.model.Reference;
import com.diodel.frostapp.model.Station;
import com.diodel.frostapp.rest.APIServices;
import com.diodel.frostapp.rest.ApiUtils;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import java.io.IOException;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MainPresenter {
    private MainView view;
    private APIServices mAPIService;
    private CompositeDisposable mCompositeDisposable;

    public MainPresenter(MainView view) {
        this.view = view;
        mCompositeDisposable = new CompositeDisposable();
        mAPIService = ApiUtils.getAPIService();
    }

    public void getSourceAPI() {
        view.showLoading();
        mCompositeDisposable.add(mAPIService.sourcesAPI()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Station>() {
                                   @Override
                                   public void onNext(Station value) {
                                       view.getSourceAPIResponse(value);
                                       view.hideLoading();
                                   }

                                   @Override
                                   public void onError(Throwable e) {
                                       if (e instanceof HttpException) {
                                           ResponseBody body = ((HttpException) e).response().errorBody();
                                           Gson gson = new Gson();
                                           TypeAdapter<ErrorResponse> adapter = gson.getAdapter
                                                   (ErrorResponse
                                                           .class);
                                           try {
                                               ErrorResponse errorParser =
                                                       adapter.fromJson(body.string());
                                               view.onError(errorParser.errors.getReason());

                                               Log.e("errorParser", errorParser.errors.getReason());

                                           } catch (IOException ee) {
                                               Log.e("IOException", ee.getMessage());
                                               ee.printStackTrace();
                                           }
                                       }

                                       view.hideLoading();
                                   }

                                   @Override
                                   public void onComplete() {
                                   }

                               }
                ));
    }
    public void getObservationAPI(String id,String date) {
        view.showLoading();
        mCompositeDisposable.add(mAPIService.observationsAPI(id,date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Reference>() {
                                   @Override
                                   public void onNext(Reference value) {
                                       view.getObservationAPIResponse(value);
                                       view.hideLoading();
                                   }

                                   @Override
                                   public void onError(Throwable e) {
                                       if (e instanceof HttpException) {
                                           ResponseBody body = ((HttpException) e).response().errorBody();
                                           Gson gson = new Gson();
                                           TypeAdapter<ErrorResponse> adapter = gson.getAdapter
                                                   (ErrorResponse
                                                           .class);
                                           try {
                                               ErrorResponse errorParser =
                                                       adapter.fromJson(body.string());
                                               view.onError(errorParser.errors.getReason());

                                               Log.e("errorParser", errorParser.errors.getReason());

                                           } catch (IOException ee) {
                                               Log.e("IOException", ee.getMessage());
                                               ee.printStackTrace();
                                           }
                                       }

                                       view.hideLoading();
                                   }

                                   @Override
                                   public void onComplete() {
                                   }

                               }
                ));
    }

    public void onDestroy()
    {
        if(mCompositeDisposable != null)
            mCompositeDisposable.dispose();
    }


}
