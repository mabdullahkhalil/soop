package com.something.mabdullahk.soop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginPage extends AppCompatActivity {

    EditText rollNumber;
    Button login;
    SimpleArcDialog mDialog ;
    List<student> childrenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        rollNumber = (EditText)findViewById(R.id.rollNumber);
        login      = (Button)findViewById(R.id.loginButton);
        childrenList= new ArrayList<>();


        ArcConfiguration configuration = new ArcConfiguration(this);
        configuration.setLoaderStyle(SimpleArcLoader.STYLE.SIMPLE_ARC);
        configuration.setText("Please wait..");
        int[] colors = new int[]{R.color.colorlogo1,R.color.colorlogo2,R.color.colorlogo3,R.color.colorlogo4};
        configuration.setColors(colors);
        configuration.setAnimationSpeed(SimpleArcLoader.SPEED_MEDIUM);


        mDialog = new SimpleArcDialog(this);
        mDialog.setConfiguration(configuration);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginClicked();
            }
        });


        rollNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    loginClicked();
                }
                return false;
            }
        });
    }

    private void loginClicked(){
        if (!rollNumber.getText().toString().isEmpty()){


            System.out.println(rollNumber.getText().toString());
            mDialog.show();
            sendrequest(rollNumber.getText().toString());
        } else {
            rollNumber.setError("Enter roll number to continue.");
            System.out.println("enter roll numebr");
        }
    }


    private void sendrequest(final String rollnumber){
        Map<String, String> params = new HashMap<>();
        Map<String, String> headers = new HashMap<>();

//        params.put("id","46285948");

        HTTPrequest.placeRequest("https://soop-staging.herokuapp.com/api/v1/parents/sessions?id="+rollnumber, "Get", params, headers, new HTTPrequest.VolleyCallback() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject students = new JSONObject(result);
                    Boolean success = students.getBoolean("success");


//                    System.out.println(studentsData);

                    if (success){
                        JSONObject studentsData = students.getJSONObject("data");

                        JSONArray childrenData= studentsData.getJSONArray("children");


                        for(int i=0; i < childrenData.length() ; i++){
                            JSONObject children = childrenData.getJSONObject(i);
                            childrenList.add(new student(children.getString("name"), children.getString("roll_number"), children.getString("id"), children.getString("class_name"),children.getString("attendance"), children.getString("quizzes"), children.getString("notes"), children.getString("announcements")));
                        }

                        System.out.println("student list ..."+childrenList);
                        mDialog.dismiss();
                        Intent intent = new Intent(LoginPage.this,
                                studentCards.class);
                        intent.putExtra("allChildrenData", (Serializable) childrenList);
                        startActivity(intent);
                    }

                    if (!success) {
                        mDialog.dismiss();
                        rollNumber.setText("");
                        rollNumber.setError("Enter correct roll number to continue.");

                    }

                }catch(JSONException e){
                    System.out.println("you had"+e);
                }


                System.out.println(result);

            }

            @Override
            public void onFaliure(String faliure) {
                System.out.println("failed bro");
            }
        },this);
    }
}

