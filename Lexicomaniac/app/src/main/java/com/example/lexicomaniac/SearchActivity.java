package com.example.lexicomaniac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {
    private String OXFORD_DICTIONARIES_API_KEY = "613376811a84d0858179f8e57c5957ae";
    private EditText editTextSearch;
    private Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editTextSearch = (EditText) findViewById(R.id.search);
        searchBtn = (Button) findViewById(R.id.search_button);
    }
    private String getEditTextString(EditText editText) {
        return editText.getText().toString();
    }
    private String createUrl() {
        // Finish Implementation
        final String language = "en";
        final String word = getEditTextString(editTextSearch);
        final String wordId = word.toLowerCase();
        //return "https://od-api.oxforddictionaries.com:443/api/v1/search/" + language + "?q=" + wordId;
        return "s";
    }
}
