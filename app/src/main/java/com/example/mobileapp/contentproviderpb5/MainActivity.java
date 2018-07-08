package com.example.mobileapp.contentproviderpb5;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final Uri CONTENT_URI = Uri.parse(StudentDataProvider.CONTENT_STRING+"/"+
    StudentDatabaseHelper.TABLE_STUDENT);
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView infoTV;
    private List<Student>students = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoTV = findViewById(R.id.studentInfo);
        getInfo();
    }

    public void saveStudent(View view) {
        String name = ((EditText)findViewById(R.id.nameET)).getText().toString();
        String department = ((EditText)findViewById(R.id.deptET)).getText().toString();
        Student student = new Student(name,department);
        ContentValues values = new ContentValues();
        values.put(StudentDatabaseHelper.COL_NAME,student.getName());
        values.put(StudentDatabaseHelper.COL_DEPARTMENT,student.getDepartment());
        Uri insertedUri = getContentResolver().insert(CONTENT_URI,values);
        Log.e(TAG, "insertedUri"+insertedUri.toString());

    }

    private void getInfo(){
        Cursor cursor = getContentResolver().query(CONTENT_URI,null,null,null,null);
        if(cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                int id = cursor.getInt(cursor.getColumnIndex(StudentDatabaseHelper.COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(StudentDatabaseHelper.COL_NAME));
                String department = cursor.getString(cursor.getColumnIndex(StudentDatabaseHelper.COL_DEPARTMENT));
                Student student = new Student(id,name,department);
                students.add(student);
            }while (cursor.moveToNext());
        }
        String info = "";
        for(Student s : students){
            info += s.getName()+"\n"+s.getDepartment()+"\n\n";
        }
        infoTV.setText(info);
    }
}
