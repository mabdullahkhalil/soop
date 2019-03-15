package com.something.mabdullahk.soop.questions;

import android.graphics.drawable.Drawable;
import android.support.design.chip.Chip;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;
import com.leo.simplearcloader.SimpleArcLoader;
import com.something.mabdullahk.soop.HTTPrequest;
import com.something.mabdullahk.soop.R;
import com.something.mabdullahk.soop.quizzesList.quizzesListActivity;
import com.something.mabdullahk.soop.quizzesList.quizzesListCardAdapter;
import com.something.mabdullahk.soop.quizzesList.quizzesListClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.view.ViewGroup.*;

public class questionsActivity extends AppCompatActivity {


    LinearLayout answerss;
    LinearLayout selectedAnswer;
    List<questionsClass> questions;
    int currentQuestionNumber;
    TextView question;
    SimpleArcDialog mDialog;
    questionsClass fetchedQuestion;
    TextView totalquestions;
    TextView currentquestion;
    Chip questionSbject;
    int totalNumberofquestions;
    Button nextquestion;
    questionsClass questionDetails;
    String studentId;
    String practiceId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        answerss = (LinearLayout) findViewById(R.id.answersLayout);
        questions = new ArrayList<>();
        question = (TextView) findViewById(R.id.question);
        totalquestions = (TextView) findViewById(R.id.totalQuestions);
        currentquestion = (TextView) findViewById(R.id.currentQuestion);
        questionSbject = (Chip) findViewById(R.id.questionSubjectChip);
        nextquestion = (Button) findViewById(R.id.questionNextButton);
        studentId = (String) getIntent().getStringExtra("studentID");
        practiceId = (String) getIntent().getStringExtra("practiceID");
        currentQuestionNumber = 1;

        ArcConfiguration configuration = new ArcConfiguration(this);
        configuration.setLoaderStyle(SimpleArcLoader.STYLE.SIMPLE_ARC);
        configuration.setText("Fetching Data..");
        int[] colors = new int[]{R.color.colorlogo1, R.color.colorlogo2, R.color.colorlogo3, R.color.colorlogo4};
        configuration.setColors(colors);
        configuration.setAnimationSpeed(SimpleArcLoader.SPEED_MEDIUM);


        mDialog = new SimpleArcDialog(this);
        mDialog.setConfiguration(configuration);


        getQuestions();


        nextquestion.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                if (currentQuestionNumber == totalNumberofquestions-1){
                    nextquestion.setText("SUBMIT quiz");
                    nextquestion.setBackgroundDrawable(ContextCompat.getDrawable(questionsActivity.this, R.drawable.border5));
                    answerss.removeAllViewsInLayout();
                    getNextQuestion();

                }else if(currentQuestionNumber < totalNumberofquestions) {
                    answerss.removeAllViewsInLayout();
                    getNextQuestion();
                }

            }
        });

    }


    private void getQuestions() {
        mDialog.show();
        Map<String, String> params = new HashMap<>();
        Map<String, String> headers = new HashMap<>();

        System.out.println("url =====> https://soop-staging.herokuapp.com/api/v1/tests/quizzes/"+practiceId+"/questions");
        HTTPrequest.placeRequest("https://soop-staging.herokuapp.com/api/v1/tests/quizzes/"+practiceId+"/questions", "Get", params, headers, new HTTPrequest.VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject questionObject = new JSONObject(result);
                    Boolean success = questionObject.getBoolean("success");

                    System.out.println(result+" is the resultlttttt.......");
                    if (success){
                        JSONObject exercisesData = questionObject.getJSONObject("data").getJSONObject("questions");
                        List<answersClass> answers = new ArrayList<>();
                        JSONArray answersData = exercisesData.getJSONArray("options");

                        for (int j=0;j< answersData.length();j++){
                                JSONObject anno1 = answersData.getJSONObject(j);
                                answers.add(new answersClass(Integer.toString(anno1.getInt("id")), anno1.getString("text")));
                            }


                            totalNumberofquestions = questionObject.getJSONObject("data").getInt("total_questions");
                        totalquestions.setText(Integer.toString(questionObject.getJSONObject("data").getInt("total_questions")));

                        fetchedQuestion = new questionsClass(
                            Integer.toString(exercisesData.getInt("id")),
                                exercisesData.getString("statement"),
                                exercisesData.getString("subject"),
                                answers,
                                Integer.toString(questionObject.getJSONObject("data").getInt("quiz_id"))
                        );

                        questionDetails = fetchedQuestion;
                        displayData(fetchedQuestion);
                    }
                }catch (JSONException e){
                    mDialog.dismiss();
                    System.out.println("JSON ERROR IN payments.ajva"+e);
                }
            }

            @Override
            public void onFaliure(String faliure) {
                System.out.println("it failed i payments.java");
                mDialog.dismiss();

            }
        },this);

    }



    private void displayData(questionsClass questionGot){

        System.out.println("came ot displayy");
        final float scale = this.getResources().getDisplayMetrics().density;
        int pixels = (int) (8 * scale + 0.5f);

        List<answersClass> answersList = questionGot.getOptions();
        for (int i=0;i<questionGot.getOptions().size();i++){
            final LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setBackground(Drawable.createFromPath(String.valueOf(R.drawable.border1)));
            linearLayout.setId(Integer.valueOf(answersList.get(i).getId()));
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0);
            layoutParams.setMargins(pixels,pixels,pixels,pixels);
            layoutParams.weight = 1;
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.border1) );
            linearLayout.setGravity(Gravity.CENTER);
            linearLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedAnswer.setBackgroundDrawable(ContextCompat.getDrawable(questionsActivity.this, R.drawable.border1));
                    linearLayout.setBackgroundDrawable(ContextCompat.getDrawable(questionsActivity.this, R.drawable.border_answers));
                    selectedAnswer = linearLayout;


                }
            });

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.setMargins(pixels,pixels,pixels,pixels);

            TextView textView = new TextView(this);
            textView.setText(answersList.get(i).getAnswer());
            textView.setTextSize(16);
            textView.setLayoutParams(params);
            linearLayout.addView(textView);
            selectedAnswer = linearLayout;

            answerss.addView(linearLayout);

        }

        currentquestion.setText(Integer.toString(currentQuestionNumber));
        question.setText(questionGot.getStatement());
        questionSbject.setText(questionGot.getSubject());
        mDialog.dismiss();

    }


    private void getNextQuestion(){
        System.out.println(selectedAnswer.getId());
        mDialog.show();
        Map<String, String> params = new HashMap<>();
        Map<String, String> headers = new HashMap<>();

        System.out.println("url ======> https://soop-staging.herokuapp.com/api/v1/tests/quizzes/"+practiceId+"/next_question?qid="+questionDetails.getId());
        HTTPrequest.placeRequest("https://soop-staging.herokuapp.com/api/v1/tests/quizzes/"+practiceId+"/next_question?qid="+questionDetails.getId(), "Get", params, headers, new HTTPrequest.VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject questionObject = new JSONObject(result);
                    Boolean success = questionObject.getBoolean("success");

                    System.out.println(result+" is the resultlttttt.......");
                    if (success){
                        JSONObject exercisesData = questionObject.getJSONObject("data").getJSONObject("questions");
                        List<answersClass> answers = new ArrayList<>();
                        JSONArray answersData = exercisesData.getJSONArray("options");

                        for (int j=0;j< answersData.length();j++){
                            JSONObject anno1 = answersData.getJSONObject(j);
                            answers.add(new answersClass(Integer.toString(anno1.getInt("id")), anno1.getString("text")));
                        }

                        currentQuestionNumber++;
                        fetchedQuestion = new questionsClass(
                                Integer.toString(exercisesData.getInt("id")),
                                exercisesData.getString("statement"),
                                exercisesData.getString("subject"),
                                answers,
                                Integer.toString(questionObject.getJSONObject("data").getInt("quiz_id"))
                        );

                        questionDetails = fetchedQuestion;
                        displayData(fetchedQuestion);
                    }
                }catch (JSONException e){
                    mDialog.dismiss();
                    System.out.println("JSON ERROR IN payments.ajva"+e);
                }
            }

            @Override
            public void onFaliure(String faliure) {
                System.out.println("it failed i payments.java");
                mDialog.dismiss();

            }
        },this);


    }

}
