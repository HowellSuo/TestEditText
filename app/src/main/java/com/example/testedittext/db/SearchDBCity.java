package com.example.testedittext.db;


import android.util.Log;

import com.example.testedittext.model.SearchCityResults;
import com.example.testedittext.utils.HttpCallBackListener;
import com.example.testedittext.utils.HttpUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SearchDBCity {

    private static final String TAG = "TAG";
    private String key_city;
    private List<String> foundList;

    public List<String> parseCity(String url_search) {
        final List<String> foundList = new ArrayList<String>();
        HttpUtils.sendHttpRequest(url_search, new HttpCallBackListener() {

            @Override
            public void onFinish(String response) {
                Gson gson = new Gson();
                SearchCityResults searchResults = gson.fromJson(response, SearchCityResults.class);
                List<SearchCityResults.SearchCityBean> results = searchResults.getResults();
                for (int i = 0; i < results.size(); i++) {
                    SearchCityResults.SearchCityBean searchCityBean = results.get(i);
                    String cityID = searchCityBean.getId();
                    String S_cityName = searchCityBean.getName();
                    String S_CityPath = searchCityBean.getPath();
                    // Log.w(TAG, "onFinish: " + cityID + "--" + S_cityName + "--" + S_CityPath);
                    foundList.add(S_CityPath);
                }

                Log.w(TAG, "onFinish: " + foundList.toString());
            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG, "onError: " + e);
            }
        });

        return foundList;
    }
}
