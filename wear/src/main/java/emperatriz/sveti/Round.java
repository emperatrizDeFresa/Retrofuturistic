package emperatriz.sveti;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Round extends Activity implements View.OnClickListener {

    private TextView mTextView;
    String selected;
    NumberView number;
    Button more, less;
    int round;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number_modify);

        SharedPreferences preferences = getSharedPreferences("sveti", MODE_PRIVATE);
        selected = preferences.getString("selectedR", "");
        if(selected.length()==0) finish();

        round = preferences.getInt(selected,4);

        number = (NumberView) findViewById(R.id.number);

        number.round=round;

        if (selected.equals(ConfigRound.HOURS)){
            number.width = DrawUtils.hourWidth;
            number.height= DrawUtils.hourHeight;
            number.maxR=DrawUtils.hourWidth/2;
            number.thick = preferences.getInt(ConfigThick.HOURS, 4);

        } else if (selected.equals(ConfigRound.MINUTES)){
            number.width = DrawUtils.minWidth;
            number.height= DrawUtils.minHeight;
            number.maxR=DrawUtils.minWidth/2;
            number.thick = preferences.getInt(ConfigThick.MINUTES, 12);

        } else if (selected.equals(ConfigRound.SECONDS)){
            number.width = DrawUtils.secondSizeW;
            number.height= DrawUtils.secondSizeH;
            number.maxR=DrawUtils.secondSizeW/2;
            number.thick = preferences.getInt(ConfigThick.SECONDS, 20);

        } else if (selected.equals(ConfigRound.DATE)){
            number.width = DrawUtils.dateWidth;
            number.height= DrawUtils.dateheight;
            number.maxR=DrawUtils.dateWidth/2;
            number.thick = preferences.getInt(ConfigThick.DATE, 2);

        }

        less = (Button) findViewById(R.id.buttonLess);
        less.setOnClickListener(this);

        more = (Button) findViewById(R.id.buttonMore);
        more.setOnClickListener(this);



    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("sveti", MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(selected, round);
        edit.commit();
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v==less){
            if (round>0){
                round--;
                number.round=round;
                number.invalidate();
            }
        }
        else if (v==more){
            if (round<number.maxR){
                round++;
                number.round=round;
                number.invalidate();
            }
        }
    }
}
