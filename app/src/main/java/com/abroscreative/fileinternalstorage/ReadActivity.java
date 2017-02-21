package com.abroscreative.fileinternalstorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author David
 * This the activity that loads data from the internal storage directory
 */
public class ReadActivity extends AppCompatActivity {

        EditText etFileTitle;
        TextView tvContentText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        etFileTitle = (EditText) findViewById(R.id.etFileTitle);
        tvContentText = (TextView) findViewById(R.id.tvFileContent);
        Button btnLoad = (Button) findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFile();
            }
        });
    }

    public void reset(View view) {
        etFileTitle.setText("");
        tvContentText.setText("");
    }

    public void readFile() {
        String fileName = etFileTitle.getText().toString()+".txt";
        Toast.makeText(ReadActivity.this, "Reading "+fileName, Toast.LENGTH_SHORT).show();
            FileInputStream fis = null;
        try {
            fis = openFileInput(fileName);
            int buf=-1;
            StringBuilder sb = new StringBuilder();
            while((buf = fis.read())!=-1){
                    char letter=  ((char)buf);
                    sb.append(letter);

            }
            tvContentText.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
