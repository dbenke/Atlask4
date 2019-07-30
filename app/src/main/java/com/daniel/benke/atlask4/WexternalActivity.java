package com.daniel.benke.atlask4;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WexternalActivity extends Activity {

    File myTelasFile;





    String filename = "Telas.txt";

    String filepath = "MyFileStorage";
    String acesso;
    public WexternalActivity(){
    }

    public void Write(Context context, String textolog) {
        /*if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
        }
        else {
           File myExternalFile = new File(context.getExternalFilesDir(filepath), filename);
        }
        */
       this.acesso = textolog;

        try {
            // Context context = getContext();
            myTelasFile = new File(context.getExternalFilesDir(filepath), filename);
            BufferedWriter writer = new BufferedWriter(new FileWriter(myTelasFile,true));

            if (!myTelasFile.exists()){
                myTelasFile.createNewFile();
                writer.write("Daniel Benke");
                Log.i("uploadFile", "HTTP Response is : ggggggggggggg");
            }


            writer.write(acesso);
            SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM HH:mm:ss");
            //SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM HH:mm");

            Date currentTime = Calendar.getInstance().getTime();

            dateformat.format(currentTime);
            writer.write(String.valueOf(dateformat.format(currentTime))+"\n");



            writer.close();




        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
}
