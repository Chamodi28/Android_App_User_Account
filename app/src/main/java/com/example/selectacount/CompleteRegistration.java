package com.example.selectacount;


import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class CompleteRegistration extends AppCompatActivity {

    private RegisteredCoursesDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_registration);

        dbHelper = new RegisteredCoursesDBHelper(this);

        LinearLayout layout = findViewById(R.id.layout_checkboxes);
        Button registerButton = findViewById(R.id.register_button);

        Cursor cursor = dbHelper.getAllRegisteredCourses();
        if (cursor != null && cursor.moveToFirst()) {

            do {
                String courseName = cursor.getString(cursor.getColumnIndex(RegisteredCoursesDBHelper.COLUMN_COURSE_NAME));
                int courseId = cursor.getInt(cursor.getColumnIndex(RegisteredCoursesDBHelper.COLUMN_ID));

                CheckBox checkBox = new CheckBox(this);
                checkBox.setText(courseName);
                checkBox.setTag(courseId);
                checkBox.setTextSize(27);
                layout.addView(checkBox);
            } while (cursor.moveToNext());
            cursor.close();
        }

        registerButton.setOnClickListener(v -> {
            List<Integer> selectedCourseIds = new ArrayList<>();

            for (int i = 0; i < layout.getChildCount(); i++) {
                CheckBox checkBox = (CheckBox) layout.getChildAt(i);
                if (checkBox.isChecked()) {
                    int courseId = (int) checkBox.getTag();
                    selectedCourseIds.add(courseId);
                }
                else{
                    int courseId = (int) checkBox.getTag();
                    dbHelper.deleteCourseById(courseId);
                }

            }

            layout.removeAllViews();

            Toast.makeText(this, "Registration complete!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}

