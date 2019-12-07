package com.yur0k.homework3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TextView textCount;
    private ProgressBar progressBar;
    private Button buttonGo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textCount = findViewById(R.id.textCount);
        buttonGo = findViewById(R.id.buttonGo);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);

        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Count count = new Count();
                count.execute();
            }
        });
    }


    class Count extends AsyncTask<Void, Integer, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textCount.setText("Начинаем");
            buttonGo.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textCount.setText("Прошло " + values[0] + " сек.");
            progressBar.setProgress(values[0]);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                int counter = 0;
                for (int i = 0; i <10 ; i++) {
                    getCount(counter);
                    publishProgress(counter++);
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            textCount.setText("Закончили");
            buttonGo.setVisibility(View.VISIBLE);
            progressBar.setProgress(0);
            progressBar.setVisibility(View.INVISIBLE);
        }

        public void getCount(int count) throws InterruptedException {
            TimeUnit.SECONDS.sleep(1);
        }
    }


}
