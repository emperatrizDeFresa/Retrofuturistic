package emperatriz.sveti;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ConfigRound extends Activity implements View.OnClickListener {

    public static String HOURS="hoursR", MINUTES="minutesR", SECONDS="secondsR", DATE="dateR";
    private LinearLayout  hours, minutes, seconds, date;
    private ImageView  hoursImg, minutesImg, secondsImg, dateImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configround);


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

//        SharedPreferences preferences = getSharedPreferences("sveti", MODE_PRIVATE);
        hoursImg = (ImageView) findViewById(R.id.hoursImg);
        hoursImg.setColorFilter(0xffff0099);
        minutesImg = (ImageView) findViewById(R.id.minutesImg);
        minutesImg.setColorFilter(0xffff0099);
        secondsImg = (ImageView) findViewById(R.id.secondsImg);
        secondsImg.setColorFilter(0xffff0099);
        dateImg = (ImageView) findViewById(R.id.dateImg);
        dateImg.setColorFilter(0xffff0099);
    }

    @Override
    public void onClick(View v) {
        if (v==hours){
            SharedPreferences preferences = getSharedPreferences("sveti", MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putString("selectedR", ConfigRound.HOURS);
            edit.commit();
            startActivity(new Intent(ConfigRound.this, Round.class));
        }else if (v==minutes){
            SharedPreferences preferences = getSharedPreferences("sveti", MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putString("selectedR", ConfigRound.MINUTES);
            edit.commit();
            startActivity(new Intent(ConfigRound.this, Round.class));
        }else if (v==seconds){
            SharedPreferences preferences = getSharedPreferences("sveti", MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putString("selectedR", ConfigRound.SECONDS);
            edit.commit();
            startActivity(new Intent(ConfigRound.this, Round.class));
        }else if (v==date){
            SharedPreferences preferences = getSharedPreferences("sveti", MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putString("selectedR", ConfigRound.DATE);
            edit.commit();
            startActivity(new Intent(ConfigRound.this, Round.class));
        }
    }
}
