package com.example.selectacount;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.selectacount.R;
import com.example.selectacount.RegisteredCourseDetail;
import com.example.selectacount.RegisteredCoursesDBHelper;

public class RegisteredCourses extends AppCompatActivity {

    private RegisteredCoursesDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_courses);

        dbHelper = new RegisteredCoursesDBHelper(this);

        LinearLayout layout = findViewById(R.id.layout_buttons);

        Cursor cursor = dbHelper.getAllRegisteredCourses();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String courseName = cursor.getString(cursor.getColumnIndex(RegisteredCoursesDBHelper.COLUMN_COURSE_NAME));
                int courseId = cursor.getInt(cursor.getColumnIndex(RegisteredCoursesDBHelper.COLUMN_ID));

                Button button = new Button(this);
                button.setText(courseName);
                button.setTextSize(22);
                button.setOnClickListener(v -> {
                    Intent intent = new Intent(RegisteredCourses.this, RegisteredCourseDetail.class);
                    intent.putExtra("COURSE_ID", courseId);
                    startActivity(intent);
                });

                layout.addView(button);
            } while (cursor.moveToNext());
            cursor.close();
        }
    }
}


/*
package com.example.selectacount;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class RegisteredCourses extends AppCompatActivity {

    private UserSOLiteDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_courses);

        dbHelper = new UserSOLiteDBHelper(this);

        LinearLayout layout = findViewById(R.id.layout_buttons);

        Cursor cursor = dbHelper.getAllCourses();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String courseName = cursor.getString(cursor.getColumnIndex(UserSOLiteDBHelper.column_course));
                int courseId = cursor.getInt(cursor.getColumnIndex(UserSOLiteDBHelper.column_id));

                Button button = new Button(this);
                button.setText(courseName);
                button.setOnClickListener(v -> {
                    Intent intent = new Intent(RegisteredCourses.this, RegisteredCourseDetail.class);
                    intent.putExtra("COURSE_ID", courseId);
                    startActivity(intent);
                });

                layout.addView(button);
            } while (cursor.moveToNext());
            cursor.close();
        }
    }
}
*/
