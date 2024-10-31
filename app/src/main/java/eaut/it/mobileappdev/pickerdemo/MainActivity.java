package eaut.it.mobileappdev.pickerdemo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView dateText;
    private TextView timeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dateText = findViewById(R.id.textView1);
        timeText = findViewById(R.id.textView2);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button1)
            showDatePickerDialog();
        else
            showTimePickerDialog();
    }

    private String getDateStr(int year, int month, int dayOfMonth) {
        StringBuilder builder = new StringBuilder();
        builder.append(dayOfMonth + "/");
        builder.append(month + 1 + "/");//month is 0 based
        builder.append(year);
        return builder.toString();
    }

    private String getTimeStr(int hourOfDay, int minute){
        return hourOfDay+":"+minute;
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR)+1;
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, month1, dayOfMonth) -> {
                    // Xử lý sự kiện khi người dùng chọn ngày
                    dateText.setText("Selected Date: " + getDateStr(year1, month1, dayOfMonth));
                }, year, month, day);
        datePickerDialog.show();
    }

    private void showTimePickerDialog(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Xử lý sự kiện khi người dùng chọn thời gian
                        timeText.setText("Selected Time: "+ getTimeStr(hourOfDay,minute));
                    }
                }, hour, minute, true);
        timePickerDialog.show();
    }
}