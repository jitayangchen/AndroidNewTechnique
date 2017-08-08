package com.pepoc.androidnewtechnique.file;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pepoc.androidnewtechnique.R;
import com.pepoc.androidnewtechnique.log.LogManager;
import com.stericson.RootShell.RootShell;
import com.stericson.RootTools.RootTools;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileActivity extends AppCompatActivity {

    private String path = "/data/anr/traces.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        init();

    }

    private void init() {
        View btnDelete = findViewById(R.id.btn_delete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean rootAvailable = RootShell.isRootAvailable();
                boolean accessGiven = RootTools.isAccessGiven();
                LogManager.i("rootAvailable = " + rootAvailable);
                LogManager.i("accessGiven = " + accessGiven);


//                try {
//                    Runtime.getRuntime().exec("su");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

//                execute();


//                path = "/sdcard/zzz.txt";
//                try {
//                    Process process = Runtime.getRuntime().exec(new String[]{"rm", "-f", path});
//
//                    process.getOutputStream().write("exit\n".getBytes());
//                    process.getOutputStream().flush();
//                    int exitVal = process.waitFor();
//                    LogManager.i("Process exitValue: " + exitVal);
//                    Toast.makeText(FileActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Toast.makeText(FileActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
//                }



//                File file = new File(path);
//                LogManager.i("file.canRead() " + file.canRead());
//                LogManager.i("file.canWrite() " + file.canWrite());
//                if (file.exists() && file.canWrite() && file.canRead()) {
//                    boolean delete = file.delete();
//                    Toast.makeText(FileActivity.this, "delete = " + delete, Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(FileActivity.this, "Permission denied", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        View btnGetRoot = findViewById(R.id.btn_get_root);
        btnGetRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getRoot("chmod 777 " + path);
//                getRoot("rm -f " + path);
//                getRoot("id");

//                for (int i = 0; i < 50; i++) {
////                    getRoot("cp /data/anr/zzz/traces.txt /data/anr/traces.txt" + i);
//                    getRoot("rm -f " + path + i);
//                }

                execute();
            }
        });


        findViewById(R.id.btn_create_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/ZZZCCChahaha.txt");
//                LogManager.i(file.getAbsolutePath());
//                try {
//                    boolean success = file.createNewFile();
//                    LogManager.i("create file = " + success);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    private boolean getRoot(String command) {
        Process process = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec("su");
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();



            //取得命令结果的输出流
            InputStream fis = process.getInputStream();
            //用一个读输出流类去读
            InputStreamReader isr=new InputStreamReader(fis);
            //用缓冲器读行
            BufferedReader br=new BufferedReader(isr);
            String line=null;
            //直到读完为止
            while((line=br.readLine())!=null)
            {
                LogManager.i(line);
            }
            fis.close();



            int waitFor = process.exitValue();
//            int waitFor = process.waitFor();
            LogManager.i("Process waitFor: " + waitFor);
        } catch (Exception e) {
            LogManager.i("ROOT REE" + e.getMessage());
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
//                if (process != null) {
//                    process.destroy();
//                }
            } catch (Exception e) {

            }
        }
        return true;
    }

    private void execute() {
        try {
            Process process = Runtime.getRuntime().exec("id");
            //取得命令结果的输出流
            InputStream fis = process.getInputStream();
            //用一个读输出流类去读
            InputStreamReader isr=new InputStreamReader(fis);
            //用缓冲器读行
            BufferedReader br=new BufferedReader(isr);
            String line=null;
            //直到读完为止
            while((line=br.readLine())!=null)
            {
                LogManager.i(line);
            }
            fis.close();
//            Toast.makeText(FileActivity.this, "Success", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
