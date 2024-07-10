
package com.example.selectacount;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RegisteredCourseDetail extends AppCompatActivity {

    private RegisteredCoursesDBHelper dbHelper;
    private int courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_course_detail);

        dbHelper = new RegisteredCoursesDBHelper(this);

        TextView courseNameTextView = findViewById(R.id.course_name);
        TextView courseCostTextView = findViewById(R.id.course_cost);
        TextView courseBranchTextView = findViewById(R.id.course_branch);
        TextView courseStartingDateTextView = findViewById(R.id.course_starting_date);
        Button deleteButton = (Button) findViewById(R.id.delete_button);

        courseId = getIntent().getIntExtra("COURSE_ID", -1);

        Cursor cursor = dbHelper.getRegisteredCourseById(courseId);
        if (cursor != null && cursor.moveToFirst()) {
            String courseName = cursor.getString(cursor.getColumnIndex(RegisteredCoursesDBHelper.COLUMN_COURSE_NAME));
            int courseCost = cursor.getInt(cursor.getColumnIndex(RegisteredCoursesDBHelper.COLUMN_COURSE_COST));
            String branch = cursor.getString(cursor.getColumnIndex(RegisteredCoursesDBHelper.COLUMN_BRANCH));
            String startingDate = cursor.getString(cursor.getColumnIndex(RegisteredCoursesDBHelper.COLUMN_STARTING_DATE));

            courseNameTextView.setText(courseName);
            courseCostTextView.setText(String.valueOf(courseCost));
            courseBranchTextView.setText(branch);
            courseStartingDateTextView.setText(startingDate);

            cursor.close();
        }

        deleteButton.setOnClickListener(v -> {
            dbHelper.deleteCourseById(courseId);
            finish();
        });
    }
}

/*
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RegisteredCourseDetail extends AppCompatActivity {

    private UserSOLiteDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_course_detail);

        dbHelper = new UserSOLiteDBHelper(this);

        TextView courseNameTextView = findViewById(R.id.course_name);
        TextView courseCostTextView = findViewById(R.id.course_cost);
        TextView courseBranchTextView = findViewById(R.id.course_branch);
        TextView courseStartingDateTextView = findViewById(R.id.course_starting_date);

        int courseId = getIntent().getIntExtra("COURSE_ID", -1);

        Cursor cursor = dbHelper.getCourseById(courseId);
        if (cursor != null && cursor.moveToFirst()) {
            String courseName = cursor.getString(cursor.getColumnIndex(UserSOLiteDBHelper.column_course));
            int courseCost = cursor.getInt(cursor.getColumnIndex(UserSOLiteDBHelper.column_cost));
            String courseBranch = cursor.getString(cursor.getColumnIndex(UserSOLiteDBHelper.column_branch));
            String courseStartingDate = cursor.getString(cursor.getColumnIndex(UserSOLiteDBHelper.column_starting_date));

            courseNameTextView.setText(courseName);
            courseCostTextView.setText(String.valueOf(courseCost));
            courseBranchTextView.setText(courseBranch);
            courseStartingDateTextView.setText(courseStartingDate);

            cursor.close();
        }
    }
}
*/