package com.example.eduardopalacios.ejemploasynctask.Threads;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.eduardopalacios.ejemploasynctask.Interfaces.Comunicar;

/**
 * Created by eduardopalacios on 08/05/18.
 */

public class Descargar extends Thread {

    Comunicar comunicar;
    Context context;


  public Descargar(Context context)
  {
      this.context=context;

      comunicar=(Comunicar)context;
  }
    @Override
    public void run() {
        super.run();

        int i;

        for (i=0;i<=100;i+=10)
        {
            tarea();

            comunicar.comunicarHiloPrincipal(i);

            if (isInterrupted())
            {
                comunicar.comunicarHiloPrincipal(0);
                Message msg = handler.obtainMessage();
                msg.arg1 = 2;
                handler.sendMessage(msg);
                break;
            }


            Log.d("tag",String.valueOf(i));


        }



        if (i==110) {
            Message msg = handler.obtainMessage();
            msg.arg1 = 1;
            handler.sendMessage(msg);

        }

    }

    public final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.arg1 == 1)
                Toast.makeText(context,"Tarea Finalizada", Toast.LENGTH_LONG).show();

            if(msg.arg1 == 2)
                Toast.makeText(context,"Tarea Interrumpida", Toast.LENGTH_LONG).show();
        }
    };



    public void tarea()
    {

        try {
            Thread.sleep(1000);



        } catch(InterruptedException e) {

            Thread.currentThread().interrupt();


        }

    }
}
