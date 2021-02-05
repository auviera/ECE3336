package com.example.persistent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.OpenOption;

public class MainActivity extends AppCompatActivity {
    TextView output;
    EditText input;
    Button savePref,loadPref,saveFile,loadFile;
    SharedPreferences myPref;
    SharedPreferences.Editor myEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output=findViewById(R.id.outputText);
        input=findViewById(R.id.inputText);
        savePref=findViewById(R.id.savePref);
        saveFile=findViewById(R.id.saveFile);
        loadPref=findViewById(R.id.loadPref);
        loadFile=findViewById(R.id.loadFile);

        myPref= PreferenceManager.getDefaultSharedPreferences(this);
        myEditor = myPref.edit();

        savePref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputString = input.getText().toString();
                myEditor.putString(getString(R.string.com_example_persistent_preferenceString),inputString);
                myEditor.commit();
                Toast.makeText(MainActivity.this, inputString+" saved", Toast.LENGTH_SHORT).show();
            }
        });

        loadPref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String display = myPref.getString(getString(R.string.com_example_persistent_preferenceString),"NUL");
                output.setText(display);
            }
        });

        saveFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputString = input.getText().toString();
                /* FOR EXTERNAL FILE
                 */
                String path = getExternalFilesDir("notes")+"/notes.txt";
                FileWriter fw= null;
                try {
                    fw = new FileWriter(path);
                    fw.write(inputString);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

//                FOR INTERNAL FILE
//                OutputStreamWriter out = null;
//                try {
//                    out = new OutputStreamWriter(openFileOutput("notes.txt", Context.MODE_APPEND));
//                    out.write(inputString);
//                    Toast.makeText(MainActivity.this, "output saved to file", Toast.LENGTH_SHORT).show();
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    try {
//                        out.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        });

        loadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FOR EXTERNAL FILE
                String path = getExternalFilesDir("notes")+"/notes.txt";
                FileReader fr = null;
                try {
                    fr = new FileReader(path);
                    BufferedReader reader = new BufferedReader(fr);
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while((line=reader.readLine())!=null){
                        sb.append(line+"\n");
                    }
                    output.setText(toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        fr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
//                FOR INTERNAL FILE
//                InputStream is = null;
//                try {
//                    is = openFileInput("notes.txt");
//                    InputStreamReader isr = new InputStreamReader(is);
//                    BufferedReader reader = new BufferedReader(isr);
//                    String line;
//                    StringBuilder sb = new StringBuilder();
//                    while((line=reader.readLine())!=null){
//                        sb.append(line+"\n");
//                    }
//                    output.setText(sb.toString());
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }finally {
//                    try {
//                        is.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        });

    }
}