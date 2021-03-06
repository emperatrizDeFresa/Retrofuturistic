package emperatriz.sveti;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.SVBar;
import com.larswerkman.holocolorpicker.SaturationBar;
import com.larswerkman.holocolorpicker.ValueBar;

public class BackgroundColor extends Activity {

    com.larswerkman.holocolorpicker.ColorPicker picker;
    String selected;
    SaturationBar saturationBar;
    ValueBar valueBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_color);

        SharedPreferences preferences = getSharedPreferences("sveti", MODE_PRIVATE);

        selected = ConfigColor.SECONDS;

        int color = preferences.getInt(selected,0xffffffff);


        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);

        picker = (com.larswerkman.holocolorpicker.ColorPicker) findViewById(R.id.picker);



        picker.setShowOldCenterColor(false);

     //   SVBar svBar = (SVBar) findViewById(R.id.svbar);
//        saturationBar = (SaturationBar) findViewById(R.id.saturationbar);
//        valueBar = (ValueBar) findViewById(R.id.valuebar);




       // picker.addSVBar(svBar);
//        picker.addSaturationBar(saturationBar);
        picker.setTouchAnywhereOnColorWheelEnabled(false);
//        picker.addValueBar(valueBar);

//        valueBar.setValue(hsv[2]);
//        saturationBar.setSaturation(hsv[1]);
        picker.setColor(color);


    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("sveti", MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(selected, picker.getColor());
        edit.commit();
        finish();
    }
}
