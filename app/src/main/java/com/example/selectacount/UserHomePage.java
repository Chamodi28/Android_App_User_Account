package com.example.selectacount;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class UserHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);

        Button myCoursesButton = (Button) findViewById(R.id.btn_my_courses);
        Button availableCoursesButton = findViewById(R.id.btn_available_courses);

        myCoursesButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserHomePage.this, RegisteredCourses.class);
            startActivity(intent);
        });

        availableCoursesButton.setOnClickListener(v -> {
            Intent intent = new Intent(UserHomePage.this, AvailableCourses.class);
            startActivity(intent);
        });
    }
}
