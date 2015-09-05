package emperatriz.sveti;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Thick extends Activity implements View.OnClickListener {

    private TextView mTextView;
    String selected;
    NumberView number;
    Button more, less;
    int thick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number_modify);

        SharedPreferences preferences = getSharedPreferences("sveti", MODE_PRIVATE);
        selected = preferences.getString("selectedT", "");
        if(selected.length()==0) finish();

        thick = preferences.getInt(selected,4);

        number = (NumberView) findViewById(R.id.number);

        number.thick=thick;


        if (selected.equals(ConfigThick.HOURS)){
            number.width = DrawUtils.hourWidth;
            number.height= DrawUtils.hourHeight;
            number.maxT=20;
            number.round = preferences.getInt(ConfigRound.HOURS, 6);

        } else if (selected.equals(ConfigThick.MINUTES)){
            number.width = DrawUtils.minWidth;
            number.height= DrawUtils.minHeight;
            number.maxT=20;
            number.round = preferences.getInt(ConfigRound.MINUTES, 6);

        } else if (selected.equals(ConfigThick.SECONDS)){
            number.width = DrawUtils.secondSizeW;
            number.height= DrawUtils.secondSizeH;
            number.maxT=50;
            number.round = preferences.getInt(ConfigRound.SECONDS, 6);

        } else if (selected.equals(ConfigThick.DATE)){
            number.width = DrawUtils.dateWidth;
            number.height= DrawUtils.dateheight;
            number.maxT=6;
            number.round = preferences.getInt(ConfigRound.DATE, 6);
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
        edit.putInt(selected, thick);
        edit.commit();
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v==less){
            if (thick>1){
                thick--;
                if (thick%2==1) thick--;
                number.thick=thick;
                number.invalidate();
            }
        }
        else if (v==more){
            if (thick<number.maxT-1){
                thick++;
                if (thick%2==1) thick++;
                number.thick=thick;
                number.invalidate();
            }
        }
    }
}
