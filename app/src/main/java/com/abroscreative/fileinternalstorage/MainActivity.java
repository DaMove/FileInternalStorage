package com.abroscreative.fileinternalstorage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**@author David
 * This is the starter activity that writes content to the internal storage directory
 */
public class MainActivity extends AppCompatActivity {

        EditText etFileName;
        EditText etContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFileName = (EditText) findViewById(R.id.etFileName);
        etContent = (EditText) findViewById(R.id.etContent);
    }

    public void saveFile(View view) {
        String fileName = etFileName.getText().toString();
        String content = etContent.getText().toString();

        writeToFile(fileName, content);

        Toast.makeText(MainActivity.this, fileName+".txt successfully written to internal storage", Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "This file is located in "+getFilesDir().toString(), Toast.LENGTH_SHORT).show();
    }

    private void writeToFile(String fileName, String content) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(fileName+".txt", MODE_PRIVATE);
            fos.write(content.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if(fos!= null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void goToReadActivity(View view) {
        startActivity(new Intent(this, ReadActivity.class));
    }

    public void clearText(View view) {
        etFileName.setText("");
        etContent.setText("");
    }

    public void cacheFile(View view) {
        String fileName = etFileName.getText().toString();
        String content = etContent.getText().toString();
        File internalCacheDir = getCacheDir();//returns The path of the directory holding application cache files.
        File file = new File(internalCacheDir, fileName+".txt");

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(fileName+".txt", MODE_PRIVATE);
            fos.write(content.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if(fos!= null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //writeToFile(fileName, content);

        Toast.makeText(MainActivity.this, fileName+".txt successfully written to internal cache storage", Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "This file is located in "+getCacheDir().toString(), Toast.LENGTH_SHORT).show();
    }
}
