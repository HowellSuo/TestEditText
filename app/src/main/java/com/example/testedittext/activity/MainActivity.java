package com.example.testedittext.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.testedittext.R;
import com.example.testedittext.adapter.SearchAdapter;
import com.example.testedittext.model.SearchCityResults;
import com.example.testedittext.utils.HttpCallBackListener;
import com.example.testedittext.utils.HttpUtils;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    private static final int LEVEL_ALLCITY = 0;
    private static final int LEVEL_FOUNDCITY = 1;
    private String key_city;
    private ImageView img_delete;
    private EditText et_CityKey;
    private ListView lv_cityFound;
    private int currentLevel;
    private SearchAdapter adapter;
    private List<String> cityFoundLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        cityFoundLists = new ArrayList<>();
        lv_cityFound = (ListView) findViewById(R.id.show_Cityfound);
        et_CityKey = (EditText) findViewById(R.id.et_cityKey);
        img_delete = (ImageView) findViewById(R.id.img_empty);


        et_CityKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                String foundInfo = s.toString();
                if (foundInfo == null) {
                    currentLevel = LEVEL_ALLCITY;
                    showView();
                } else {
                    currentLevel = LEVEL_FOUNDCITY;
                    showView();
                    SearchCityRequest();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void SearchCityRequest() {
        String et_keyTxt = et_CityKey.getText().toString();
        try {
            key_city = URLEncoder.encode(et_keyTxt, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url_search = "https://api.thinkpage.cn/v3/location/search.json?key=uuculwcinhp0hpxq&q=" + key_city;
        parseCity(url_search);
        adapter=new SearchAdapter(this);
        lv_cityFound.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void showView() {
        if (currentLevel == LEVEL_ALLCITY) {
            img_delete.setVisibility(View.INVISIBLE);
        } else if (currentLevel == LEVEL_FOUNDCITY) {
            img_delete.setVisibility(View.VISIBLE);
        }
    }

    private void parseCity(String url_search) {
        HttpUtils.sendHttpRequest(url_search, new HttpCallBackListener() {
            @Override
            public void onFinish(String response) {
                Gson gson = new Gson();
                SearchCityResults searchResults = gson.fromJson(response, SearchCityResults.class);
                List<SearchCityResults.SearchCityBean> results = searchResults.getResults();

                final List<String> lists = new ArrayList<String>();
                for (int i = 0; i < results.size(); i++) {
                    SearchCityResults.SearchCityBean searchCityBean = results.get(i);
                    String cityID = searchCityBean.getId();
                    String S_cityName = searchCityBean.getName();
                    String S_CityPath = searchCityBean.getPath();

                    lists.add(S_cityName);
                    Log.e(TAG, "onFinish: "+lists.toString() );

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.setData(lists);
                        }
                    });
                }
            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG, "onError: " + e);
            }
        });
    }

}
