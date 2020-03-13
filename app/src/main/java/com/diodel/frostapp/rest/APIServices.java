package com.diodel.frostapp.rest;

import com.diodel.frostapp.model.Reference;
import com.diodel.frostapp.model.Station;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServices {
    @GET("sources/v0.jsonld?types=SensorSystem&municipality=MELØY&country=NO")
    Observable<Station> sourcesAPI();

    @GET("observations/v0.jsonld?elements=air_temperature%2Cwind_speed%2Cboolean_fair_weather(cloud_area_fraction%20P1D)")
    Observable<Reference> observationsAPI(@Query("sources") String source, @Query("referencetime") String referencetime);
}
