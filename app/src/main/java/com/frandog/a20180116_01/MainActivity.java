package com.frandog.a20180116_01;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.GetChars;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click1(View v){
        String str = getFilesDir().getAbsolutePath();
        Log.d("File",str);
        String str2 = getCacheDir().getAbsolutePath();
        Log.d("Cache",str2);
    }
    public void click2(View v){
        File f = new File(getFilesDir(),"myfile.txt");      //創一個f的File

        try {
            FileWriter fw = new FileWriter(f);
            fw.write("Hello world");        //可在data/data/專案名/files/找到myfile，裡面寫了Hello world
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void click3(View v){
        ArrayList<String> mylist = new ArrayList<>();
        mylist.add("Bob");
        mylist.add("Marry");
        mylist.add("Peter");
        File f = new File(getFilesDir(),"myfile2.txt");
        Gson gson = new Gson();
        String data = gson.toJson(mylist);      //轉Json格式
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickRead1(View v){
        File f = new File(getFilesDir(),"myfile2.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();     //由於我們寫的myfile2內容沒分行，所以可用此方法一行讀出全部，否則就要用while
            Log.d("File",str);
            Gson gson = new Gson();
            ArrayList<String> mydata = gson.fromJson(str,new TypeToken<ArrayList<String>>(){}.getType());
            for(String s: mydata)
            {
                Log.d("File","dara:"+s);
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void click4(View v){
        ArrayList<Student> mydata = new ArrayList<>();
        mydata.add(new Student(1,"Bob",99));
        mydata.add(new Student(1,"Marry",89));
        mydata.add(new Student(1,"Peter",39));

        Gson gson = new Gson();
        String data = gson.toJson(mydata);
        File f = new File(getFilesDir(),"myfile3.txt");
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickRead2(View v)
    {
        File f = new File(getFilesDir(),"myfile3.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            Log.d("FILE",str);
            Gson gson = new Gson();
            ArrayList<Student> mydata = gson.fromJson(str,new TypeToken<ArrayList<Student>>(){}.getType());
            for(Student s:mydata)
            {
                Log.d("FILE","data:"+ s.ID +","+s.name+","+s.score);
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class Student
    {
        int ID;
        String name;
        int score;
        Student(int ID,String name,int score)
        {
            this.ID=ID;
            this.name=name;
            this.score=score;
        }
    }

    public void click5(View v)
    {
        File f = getExternalFilesDir("data");
//        File f = new File(getExternalFilesDir(null),"data1");
        Log.d("FILE",f.getAbsolutePath());
    }
    public void click6(View v)
    {
        File f = Environment.getExternalStorageDirectory();
        Log.d("FILE",f.getAbsolutePath());
    }
}
