package com.example.currencyconverterapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;



public class MainActivity extends AppCompatActivity {
    private TextView txtSelectCurrency, txtTargetAmount, txtCurrentRate;
    private Spinner spnSourceCurrency,spnTargetCurrency;
    private Button btnConvert, btnCheckRate, btnHistory;
    private EditText edtSourceAmount;
    private ImageView imgSwapCurrency;
    private RequestQueue queue;
    private Double exchangeRate;
    private String selectedCurrencyCode, targetCurrencyCode;
    private ArrayList<Conversion> conversions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();
        queue= initRequestQueue(queue);
        fetchCurrencyList();
        setOnClickListeners();

    }

    private void initViews(){

        txtSelectCurrency= findViewById(R.id.txtSelectCurrency);
        txtTargetAmount = findViewById(R.id.txtTargetAmount);
        spnSourceCurrency= findViewById(R.id.spnSourceCurrency);
        spnTargetCurrency = findViewById(R.id.spnTargetCurrency);
        btnConvert= findViewById(R.id.btnConvert);
        edtSourceAmount = findViewById(R.id.edtSourceAmount);
        imgSwapCurrency = findViewById(R.id.imgSwapCurrency);
        btnCheckRate = findViewById(R.id.btnCheckRate);
        txtCurrentRate= findViewById(R.id.txtCurrentRate);
        btnHistory= findViewById(R.id.btnHistory);

    }
    private RequestQueue initRequestQueue(RequestQueue queue){
        if(queue == null){
            queue = Volley.newRequestQueue(MainActivity.this);
            return queue;
        }else{
            return queue;
        }
    }
    private void setOnClickListeners(){

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedCurrencyCode = spnSourceCurrency.getSelectedItem().toString().substring(0,3);
                targetCurrencyCode = spnTargetCurrency.getSelectedItem().toString().substring(0,3);
                String url = "https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_Qp76T6gQRWh1Rwq6gWiR9EmyHDjT6sGnDK5eRFrM&currencies=EUR%2CUSD%2CJPY%2CBGN%2CCZK%2CDKK%2CGBP%2CHUF%2CPLN%2CRON%2CSEK%2CCHF%2CISK%2CNOK%2CHRK%2CRUB%2CTRY%2CAUD%2CBRL%2CCAD%2CCNY%2CHKD%2CIDR%2CILS%2CINR%2CKRW%2CMXN%2CMYR%2CNZD%2CPHP%2CSGD%2CTHB%2CZAR&base_currency="+selectedCurrencyCode ;

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject data = response.getJSONObject("data");
                                    exchangeRate= data.getDouble(targetCurrencyCode);
                                    Double enteredAmount = Double.parseDouble(edtSourceAmount.getText().toString());
                                    Double targetAmount = enteredAmount*exchangeRate;
                                    txtTargetAmount.setText("Converted Amount: "+ enteredAmount.toString()+" "+selectedCurrencyCode+"= "+String.format("%.4f", targetAmount)+" "+targetCurrencyCode);
                                    txtCurrentRate.setText("Current Rate: 1.0 "+ selectedCurrencyCode+" = " +String.format("%.4f", exchangeRate)+" "+targetCurrencyCode);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    throw new RuntimeException(e);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Something is Wrong!", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(request);
            }
        });
        imgSwapCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int tempPosition = spnSourceCurrency.getSelectedItemPosition();
                spnSourceCurrency.setSelection(spnTargetCurrency.getSelectedItemPosition());
                spnTargetCurrency.setSelection(tempPosition);
            }
        });
        btnCheckRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedCurrencyCode = spnSourceCurrency.getSelectedItem().toString().substring(0,3);
                targetCurrencyCode = spnTargetCurrency.getSelectedItem().toString().substring(0,3);
                String url = "https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_Qp76T6gQRWh1Rwq6gWiR9EmyHDjT6sGnDK5eRFrM&currencies=EUR%2CUSD%2CJPY%2CBGN%2CCZK%2CDKK%2CGBP%2CHUF%2CPLN%2CRON%2CSEK%2CCHF%2CISK%2CNOK%2CHRK%2CRUB%2CTRY%2CAUD%2CBRL%2CCAD%2CCNY%2CHKD%2CIDR%2CILS%2CINR%2CKRW%2CMXN%2CMYR%2CNZD%2CPHP%2CSGD%2CTHB%2CZAR&base_currency="+selectedCurrencyCode ;

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject data = response.getJSONObject("data");
                                    exchangeRate= data.getDouble(targetCurrencyCode);
                                    txtCurrentRate.setText("Current Rate: 1.0 "+ selectedCurrencyCode+" = " +String.format("%.4f",exchangeRate)+" "+targetCurrencyCode);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    throw new RuntimeException(e);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Something is Wrong!", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(request);
            }
        });
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
    }
    private void fetchCurrencyList(){
        String url = "https://api.freecurrencyapi.com/v1/currencies?apikey=fca_live_Qp76T6gQRWh1Rwq6gWiR9EmyHDjT6sGnDK5eRFrM&currencies=EUR%2CUSD%2CJPY%2CBGN%2CCZK%2CDKK%2CGBP%2CHUF%2CPLN%2CRON%2CSEK%2CCHF%2CISK%2CNOK%2CHRK%2CRUB%2CTRY%2CAUD%2CBRL%2CCAD%2CCNY%2CHKD%2CIDR%2CILS%2CINR%2CKRW%2CMXN%2CMYR%2CNZD%2CPHP%2CSGD%2CTHB%2CZAR";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            ArrayList<String> currencyList = new ArrayList<>();
                            Iterator<String> keys = data.keys();
                            while (keys.hasNext()) {
                                String key = keys.next();
                                String name = data.getJSONObject(key).getString("name");
                                String fullData = key + "-"+ name;
                                currencyList.add(fullData.replace(" ",""));
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                                    android.R.layout.simple_spinner_item, currencyList);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spnSourceCurrency.setAdapter(adapter);
                            spnTargetCurrency.setAdapter(adapter);

                            selectedCurrencyCode = spnSourceCurrency.getSelectedItem().toString().substring(0,3);
                            targetCurrencyCode = spnTargetCurrency.getSelectedItem().toString().substring(0,3);

                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Error parsing currency list", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Failed to fetch currency list", Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(request);
    }}

