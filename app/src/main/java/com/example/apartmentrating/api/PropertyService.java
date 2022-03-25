package com.example.apartmentrating.api;

import com.example.apartmentrating.api.bean.PropertyResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

public interface PropertyService {

    @Headers({
            "X-RapidAPI-Host: realty-mole-property-api.p.rapidapi.com",
            "X-RapidAPI-Key: 68b2ed8da6msh9d36a9d70a7baafp177b66jsn6d4d0cdbdb11"
    })
    @GET("rentalListings")
    Call<List<PropertyResponse>> getPropertyList(@QueryMap Map<String, Object> queryMap);
}
