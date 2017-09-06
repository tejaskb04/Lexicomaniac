package com.example.lexicomaniac;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class SearchActivity extends AppCompatActivity {

    private String OXFORD_DICTIONARIES_API_KEY = "613376811a84d0858179f8e57c5957ae";
    private EditText editTextSearch;
    private Button searchBtn;
    private TextView wordId;
    private boolean wordExists = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editTextSearch = (EditText) findViewById(R.id.search);
        searchBtn = (Button) findViewById(R.id.search_button);
        wordId = (TextView) findViewById(R.id.word_id);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wordExistsUrl = createWordExistsUrl();
                new WordExistsCallbackTask().execute(wordExistsUrl);
                if (wordExists) {
                    String wordInfoUrl = createWordInfoUrl();
                    new WordInfoCallbackTask().execute(wordInfoUrl);
                }
            }
        });
    }

    private String getEditTextString(EditText editText) {
        return editText.getText().toString();
    }

    private String createWordExistsUrl() {
        final String language = "en";
        final String word = getEditTextString(editTextSearch);
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v1/inflections/" + language + "/" + word_id;
    }

    private String createWordInfoUrl() {
        final String language = "en";
        final String word = getEditTextString(editTextSearch);
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
    }

    private class WordExistsCallbackTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            final String app_id = "7e60d145";
            final String app_key = OXFORD_DICTIONARIES_API_KEY;
            try {
                URL url = new URL(params[0]);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                httpsURLConnection.setRequestProperty("Accept", "application/json");
                httpsURLConnection.setRequestProperty("app_id", app_id);
                httpsURLConnection.setRequestProperty("app_key", app_key);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection
                .getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                return stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            String word = null;
            String lemma = null;
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject data = jsonArray.getJSONObject(i);
                    word = data.getString("id");
                    lemma = data.getJSONObject("lexicalEntries").getJSONObject("inflectionOf").getString("text");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (word != null) {
                wordExists = true;
                wordId.setText(word + " " + lemma);
            }
        }
    }

    private class WordInfoCallbackTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            final String app_id = "7e60d145";
            final String app_key = OXFORD_DICTIONARIES_API_KEY;
            try {
                URL url = new URL(params[0]);
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                httpsURLConnection.setRequestProperty("Accept", "application/json");
                httpsURLConnection.setRequestProperty("app_id", app_id);
                httpsURLConnection.setRequestProperty("app_key", app_key);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection
                        .getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                return stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // Implement
        }
    }
}
