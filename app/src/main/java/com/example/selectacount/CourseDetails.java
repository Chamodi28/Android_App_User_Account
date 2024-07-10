package com.example.selectacount;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CourseDetails extends AppCompatActivity {

    private AvailableCoursesDBHelper dbHelper;
    private RegisteredCoursesDBHelper regDbHelper;
    private int courseId;
    private String courseName;
    private int courseCost;
    private String branch;
    private String startingDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        dbHelper = new AvailableCoursesDBHelper(this);
        regDbHelper = new RegisteredCoursesDBHelper(this);

        TextView courseNameTextView = (TextView)findViewById(R.id.course_name);
        TextView courseCostTextView = findViewById(R.id.course_cost);
        Spinner branchSpinner = findViewById(R.id.course_branch);
        TextView courseStartingDateTextView = findViewById(R.id.course_starting_date);
        TextView promoCodeTextView = findViewById(R.id.promo_code);
        Button addToCartButton = findViewById(R.id.add_to_cart_button);

        courseId = getIntent().getIntExtra("COURSE_ID", -1);

        Cursor cursor = dbHelper.getCourseById(courseId);
        if (cursor != null && cursor.moveToFirst()) {
            courseName = cursor.getString(cursor.getColumnIndex(AvailableCoursesDBHelper.COLUMN_COURSE_NAME));
            courseCost = cursor.getInt(cursor.getColumnIndex(AvailableCoursesDBHelper.COLUMN_COURSE_COST));
            branch = cursor.getString(cursor.getColumnIndex(AvailableCoursesDBHelper.COLUMN_BRANCH));
            startingDate = cursor.getString(cursor.getColumnIndex(AvailableCoursesDBHelper.COLUMN_STARTING_DATE));

            courseNameTextView.setText(courseName);
            courseCostTextView.setText(String.valueOf(courseCost));
            courseStartingDateTextView.setText(startingDate);
            promoCodeTextView.setText("PROMO2024");

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, branch.split(","));
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            branchSpinner.setAdapter(adapter);

            cursor.close();
        }

        addToCartButton.setOnClickListener(v -> {
            regDbHelper.registerCourse(courseName, courseCost, branch, startingDate);
            Intent intent = new Intent(this, CompleteRegistration.class);
            startActivity(intent);
        });
    }
}
