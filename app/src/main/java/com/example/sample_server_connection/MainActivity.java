package com.example.sample_server_connection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    TextView txt_one;
    private RequestQueue mQueue;
    String main_url = "https://192.168.1.103/Projects/Databases/productdetails_API.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.button1);
        txt_one = findViewById(R.id.Text_one_id);
        mQueue = Volley.newRequestQueue(this);




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    string();
                }catch (Exception e) {txt_one.setText("" +e);};

            }
        });
    }

    private void string() {


        RequestQueue requestQueue;

// Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);

// Start the queue
        requestQueue.start();

// Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, main_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Do something with the response
                        txt_one.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        txt_one.setText("Something is wrong \n\n,,"+error);
                    }
                });

// Add the request to the RequestQueue.
        requestQueue.add(stringRequest);

// ...
    }

}