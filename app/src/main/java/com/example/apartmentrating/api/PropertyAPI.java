package com.example.apartmentrating.api;

import com.example.apartmentrating.api.bean.PropertyResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PropertyAPI {

    private Map<String, String> HEADERMAP = new HashMap<String, String>() {{

    }};
    private String BASEURL = "https://realty-mole-property-api.p.rapidapi.com/";

    /**
     * @param longitude OPTIONAL. Specify a latitude, longitude and radius to search for properties in a particular area.
     * @param latitude  OPTIONAL. Specify a latitude, longitude and radius to search for properties in a particular area.
     * @param radius    OPTIONAL. filter listings within this specified number of kilometers.
     *                  You must provide either an address or latitude/longitude parameters when using a radius search.
     * @param state     OPTIONAL. filter results for a specific state. Use the two character state abbreviation
     * @param city      OPTIONAL. filter results for a specific city
     * @param address   OPTIONAL. pass an optional address for looking up a specific property.
     *                  The address should be formatted as street address, city, state zip (ie 123 Main St, Conway, SC 29526)
     * @param bedrooms  OPTIONAL. filter results for rental listings with a specific number of bedrooms
     * @param bathrooms OPTIONAL. filter results for rental listings with a specific number of bathrooms
     * @param limit     OPTIONAL. maximum number of records to return. must be less than or equal to 50
     * @param offset    OPTIONAL. skip the specified number of properties. use this to page through large sets of properties.
     * @return
     */
    public List<PropertyResponse> request(double longitude, double latitude, double radius, String state, String city, String address, double bedrooms, double bathrooms, int limit,
                                          int offset) {
        // construct query map
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("longitude", longitude);
        queryMap.put("latitude", latitude);
        queryMap.put("radius", radius);
        queryMap.put("state", state);
        queryMap.put("city", city);
        queryMap.put("address", address);
        queryMap.put("bedrooms", bedrooms);
        queryMap.put("bathrooms", bathrooms);
        queryMap.put("limit", limit);
        queryMap.put("offset", offset);

        // construct API instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PropertyService service = retrofit.create(PropertyService.class);

        Call<List<PropertyResponse>> call = service.getPropertyList(queryMap);
        // synchronous
//        call.execute();
        // Asynchronous
        call.enqueue(new Callback<List<PropertyResponse>>() {
            @Override
            public void onResponse(Call<List<PropertyResponse>> call, Response<List<PropertyResponse>> response) {
                List<PropertyResponse> data = response.body();
            }

            @Override
            public void onFailure(Call<List<PropertyResponse>> call, Throwable t) {

            }
        });
        return null;
    }
}
