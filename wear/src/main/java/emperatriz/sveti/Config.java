package emperatriz.sveti;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by ramon on 21/05/15.
 */
public class Config extends Activity implements View.OnClickListener  {

    private LinearLayout color, thick, round;
    private ImageView colorImg, thickImg, roundImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        color = (LinearLayout) findViewById(R.id.color);
        color.setOnClickListener(this);

        thick = (LinearLayout) findViewById(R.id.thick);
        thick.setOnClickListener(this);

        round = (LinearLayout) findViewById(R.id.round);
        round.setOnClickListener(this);


        colorImg = (ImageView) findViewById(R.id.colorImg);
        colorImg.setColorFilter(0xff00ffbb);
        thickImg = (ImageView) findViewById(R.id.thickImg);
        thickImg.setColorFilter(0xff00ffbb);
        roundImg = (ImageView) findViewById(R.id.roundImg);
        roundImg.setColorFilter(0xff00ffbb);





    }


    @Override
    public void onClick(View v) {
        if (v==color){
            startActivity(new Intent(Config.this, ConfigColor.class));
        } else if (v==thick){
            startActivity(new Intent(Config.this, ConfigThick.class));
        }else if (v==round){
            startActivity(new Intent(Config.this, ConfigRound.class));
        }
    }
}
