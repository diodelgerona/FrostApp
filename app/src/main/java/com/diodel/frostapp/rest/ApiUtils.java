package com.diodel.frostapp.rest;

public class ApiUtils {
    public static final String BASE_URL = "https://frost.met.no/";

    public static APIServices getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIServices.class);
    }
}

