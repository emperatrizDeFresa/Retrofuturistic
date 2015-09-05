package emperatriz.sveti;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ConfigColor extends Activity implements View.OnClickListener {

    public static String BACKGROUND="background", HOURS="hours", MINUTES="minutes", SECONDS="seconds", DATE="date";
    private LinearLayout background, hours, minutes, seconds, date;
    private ImageView backgroundImg, hoursImg, minutesImg, secondsImg, dateImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configcolor);

        background = (LinearLayout) findViewById(R.id.background);
        background.setOnClickListener(this);

        hours = (LinearLayout) findViewById(R.id.hours);
        hours.setOnClickListener(this);

        minutes = (LinearLayout) findViewById(R.id.minutes);
        minutes.setOnClickListener(this);

        seconds = (LinearLayout) findViewById(R.id.seconds);
        seconds.setOnClickListener(this);

        date = (LinearLayout) findViewById(R.id.date);
        date.setOnClickListener(this);






    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences preferences = getSharedPreferences("sveti", MODE_PRIVATE);
        backgroundImg = (ImageView) findViewById(R.id.backgroundImg);
        backgroundImg.setColorFilter(preferences.getInt(BACKGROUND, 0xff000000));
        hoursImg = (ImageView) findViewById(R.id.hoursImg);
        hoursImg.setColorFilter(preferences.getInt(HOURS, 0xffffffff));
        minutesImg = (ImageView) findViewById(R.id.minutesImg);
        minutesImg.setColorFilter(preferences.getInt(MINUTES, 0xffffffff));
        secondsImg = (ImageView) findViewById(R.id.secondsImg);
        secondsImg.setColorFilter(preferences.getInt(SECONDS, 0xff444444));
        dateImg = (ImageView) findViewById(R.id.dateImg);
        dateImg.setColorFilter(preferences.getInt(DATE, 0xffffffff));
    }

    @Override
    public void onClick(View v) {
        if (v==background){
            SharedPreferences preferences = getSharedPreferences("sveti", MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putString("selected", ConfigColor.BACKGROUND);
            edit.commit();
            startActivity(new Intent(ConfigColor.this, BackgroundColor.class));
        } else if (v==hours){
            SharedPreferences preferences = getSharedPreferences("sveti", MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putString("selected", ConfigColor.HOURS);
            edit.commit();
            startActivity(new Intent(ConfigColor.this, BackgroundColor.class));
        }else if (v==minutes){
            SharedPreferences preferences = getSharedPreferences("sveti", MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putString("selected", ConfigColor.MINUTES);
            edit.commit();
            startActivity(new Intent(ConfigColor.this, BackgroundColor.class));
        }else if (v==seconds){
            SharedPreferences preferences = getSharedPreferences("sveti", MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putString("selected", ConfigColor.SECONDS);
            edit.commit();
            startActivity(new Intent(ConfigColor.this, BackgroundColor.class));
        }else if (v==date){
            SharedPreferences preferences = getSharedPreferences("sveti", MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putString("selected", ConfigColor.DATE);
            edit.commit();
            startActivity(new Intent(ConfigColor.this, BackgroundColor.class));
        }
    }
}
