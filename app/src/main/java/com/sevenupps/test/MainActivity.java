package com.sevenupps.test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private Button resultButton;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private CircularProgressIndicator spinner;
    private ViewGroup questionContainer;
    private int rightAnswerIndex = 0;

    private View.OnClickListener checkAnswerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String resultText;
            int color;
            if (v == button1 && rightAnswerIndex == 1 || v == button2 && rightAnswerIndex == 2 ||
                    v == button3 && rightAnswerIndex == 3|| v == button4 && rightAnswerIndex == 4) {
                resultText = getResources().getString(R.string.you_got_it);
                color = Color.parseColor("#417634");
            } else {
                resultText = getResources().getString(R.string.try_again);
                color = Color.parseColor("#A00203");
            }

            resultButton.setVisibility(View.VISIBLE);
            resultButton.setTextColor(color);
            resultButton.setText(resultText);

            questionContainer.setVisibility(View.GONE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        questionContainer = findViewById(R.id.question_container);

        questionTextView = findViewById(R.id.tv_question);
        resultButton = findViewById(R.id.btn_result);
        button1 = findViewById(R.id.btn_answer_1);
        button2 = findViewById(R.id.btn_answer_2);
        button3 = findViewById(R.id.btn_answer_3);
        button4 = findViewById(R.id.btn_answer_4);

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionContainer.setVisibility(View.VISIBLE);
                resultButton.setVisibility(View.GONE);
            }
        });

        button1.setOnClickListener(checkAnswerClickListener);
        button2.setOnClickListener(checkAnswerClickListener);
        button3.setOnClickListener(checkAnswerClickListener);
        button4.setOnClickListener(checkAnswerClickListener);

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://questionanswers.herokuapp.com/question/get/question/1";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        spinner.setVisibility(View.GONE);

                        if (response.has("question")) {
                            questionContainer.setVisibility(View.VISIBLE);
                            try {
                                questionTextView.setText(response.getString("question"));
                                button1.setText("1");
                                button2.setText("2");
                                button3.setText("3");
                                button4.setText("4");

                                if (response.has("rightAnswer")) {
                                    rightAnswerIndex = response.getInt("rightAnswer");
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        spinner.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), getString(R.string.general_error), Toast.LENGTH_LONG).show();
                    }
                });

        queue.add(jsonObjectRequest);
    }
}