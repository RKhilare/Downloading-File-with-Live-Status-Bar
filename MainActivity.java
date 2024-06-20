package com.example.livestatusdownloadfile;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{

    Button button;

    static final String url="https://cdn.britannica.com/57/178157-050-4D76AED9/Ellora-Caves-Maharashtra-India.jpg";
    String name="caves image.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.btn_download);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar(download(url,name));
            }
        });


    }


    public MainActivity download(String url, String name)
    {
        DownloadManager.Request request=new DownloadManager.Request(Uri.parse(url));
        request.setTitle(name);
        request.allowScanningByMediaScanner();
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,name);
        //request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);


        DownloadManager manager= (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
        return null;
    }

    public  void progressBar(MainActivity download)
    {
        final ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Downloading..."+"\n"+name);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIndeterminate(false);
        progressDialog.setMax(100);
        progressDialog.setProgress(0);
        progressDialog.setCancelable(false);
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (i<=100)
                {
                    try{
                        progressDialog.setProgress(i);
                        i++;
                        Thread.sleep(200);
                    }catch (InterruptedException e){
                        Log.d(("Name"),"Something wrong");
                    }
                }
            }
        }).start();
    }
}
