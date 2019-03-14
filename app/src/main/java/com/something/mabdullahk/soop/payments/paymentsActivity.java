package com.something.mabdullahk.soop.payments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;
import com.leo.simplearcloader.SimpleArcLoader;
import com.something.mabdullahk.soop.HTTPrequest;
import com.something.mabdullahk.soop.R;
import com.something.mabdullahk.soop.payments.paymentsActivity;
import com.something.mabdullahk.soop.payments.paymentsCardAdapter;
import com.something.mabdullahk.soop.payments.paymentsClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class paymentsActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    List<paymentsClass> payments;
    SimpleArcDialog mDialog;
    String studentId;
    LinearLayout paymentsMainView;
    ScrollView paymentsScroll;
    LinearLayout paymentsmsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);

        recyclerView = findViewById(R.id.paymentsRecyclerView);
        studentId = (String) getIntent().getStringExtra("studentID");


        GridLayoutManager paymentsGrid = new GridLayoutManager(this, 1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Payments");

        ArcConfiguration configuration = new ArcConfiguration(this);
        configuration.setLoaderStyle(SimpleArcLoader.STYLE.SIMPLE_ARC);
        configuration.setText("Fetching Data..");
        int[] colors = new int[]{R.color.colorlogo1, R.color.colorlogo2, R.color.colorlogo3, R.color.colorlogo4};
        configuration.setColors(colors);
        configuration.setAnimationSpeed(SimpleArcLoader.SPEED_MEDIUM);


        mDialog = new SimpleArcDialog(this);
        mDialog.setConfiguration(configuration);
        paymentsMainView = (LinearLayout) findViewById(R.id.paymentsMainView);
        paymentsmsg = (LinearLayout) findViewById(R.id.paymentsMessage);
        paymentsScroll = (ScrollView) findViewById(R.id.paymentsScroll);


        recyclerView.setLayoutManager(paymentsGrid);
        payments = new ArrayList<>();


        getPayments();

    }

    private void getPayments(){

        mDialog.show();
        Map<String, String> params = new HashMap<>();
        Map<String, String> headers = new HashMap<>();


        HTTPrequest.placeRequest("https://soop-staging.herokuapp.com/api/v1/parents/children/" + studentId + "/payments", "Get", params, headers, new HTTPrequest.VolleyCallback() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject studentspayments = new JSONObject(result);
                    Boolean success = studentspayments.getBoolean("success");

                    if (success){
                        JSONArray paymentsData = studentspayments.getJSONObject("data").getJSONArray("payments");

                        for(int i=0; i < paymentsData.length() ; i++) {
                            JSONObject anno = paymentsData.getJSONObject(i);
                            payments.add(new paymentsClass(
                                    anno.getString("month"),
                                    anno.getString("paid_at"),
                                    anno.getString("amount_paid"),
                                    anno.getString("remaining_amount"),
                                    anno.getString("status")
                            ));
                        }

                        System.out.println(payments+ " is the quiz list.");
                        if (payments.size() > 0){
                            mDialog.dismiss();
                            paymentsMainView.removeView(paymentsmsg);
                            paymentsMainView.setBackgroundColor(getResources().getColor(R.color.colorgray));
                            paymentsCardAdapter myAdapter = new paymentsCardAdapter(paymentsActivity.this,payments);
                            recyclerView.setAdapter(myAdapter);
                        } else {
                            mDialog.dismiss();
                            paymentsMainView.removeView(paymentsScroll);
                            paymentsMainView.setBackgroundColor(getResources().getColor(R.color.white));
                            paymentsmsg.setVisibility(View.VISIBLE);
                        }

                    }
                }catch (JSONException e){
                    System.out.println("JSON ERROR IN payments.ajva"+e);
                }
            }

            @Override
            public void onFaliure(String faliure) {
                System.out.println("it failed i payments.java");
                mDialog.dismiss();
                paymentsMainView.setBackgroundColor(getResources().getColor(R.color.white));
                paymentsMainView.removeView(paymentsScroll);
                paymentsmsg.setVisibility(View.VISIBLE);
            }
        },this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
