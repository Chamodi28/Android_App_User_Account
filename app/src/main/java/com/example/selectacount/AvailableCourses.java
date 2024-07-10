package com.example.selectacount;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class AvailableCourses extends AppCompatActivity {

    public AvailableCoursesDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_courses);

        dbHelper = new AvailableCoursesDBHelper(this);

        LinearLayout layout = findViewById(R.id.layout_buttons);

        Cursor cursor = dbHelper.getAllCourses();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String courseName = cursor.getString(cursor.getColumnIndex(AvailableCoursesDBHelper.COLUMN_COURSE_NAME));
                int courseId = cursor.getInt(cursor.getColumnIndex(AvailableCoursesDBHelper.COLUMN_ID));

                Button button = new Button(this);
                button.setText(courseName);
                button.setTextSize(22);
                button.setOnClickListener(v -> {
                    Intent intent = new Intent(AvailableCourses.this, CourseDetails.class);
                    intent.putExtra("COURSE_ID", courseId);
                    startActivity(intent);
                });

                layout.addView(button);
            } while (cursor.moveToNext());
            cursor.close();
        }
    }
}
