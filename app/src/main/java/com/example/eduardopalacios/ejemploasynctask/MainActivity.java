package com.example.eduardopalacios.ejemploasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

     Button button,button2;
     ProgressBar progressBar;
     miAsyntask miAsyntask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar=findViewById(R.id.progressBar);


        miAsyntask=new miAsyntask();

        button=findViewById(R.id.btnDescarga);
        button2=findViewById(R.id.btnCancelar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                miAsyntask.execute("");


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                miAsyntask.cancel(true);
            }
        });


    }




    class miAsyntask extends AsyncTask<String,Integer,String>
    {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            progressBar.setMax(100);

        }


        @Override
        protected String doInBackground(String... strings) {

            for (int i=0;i<=100;i+=10)
            {
                tarea();
                publishProgress(i);

                if (isCancelled())
                {
                    break;
                }
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(),"Tarea Finalaizada",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onCancelled(String s) {

            Toast.makeText(getApplicationContext(),"Tarea Cancelada",Toast.LENGTH_SHORT).show();
            super.onCancelled(s);

        }
    }

    public void tarea()
    {

        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {}

    }
}
