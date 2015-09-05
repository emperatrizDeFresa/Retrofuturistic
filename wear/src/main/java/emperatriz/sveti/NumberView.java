package emperatriz.sveti;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class NumberView  extends View {


    public NumberView(Context context) {
        super(context);
        setWillNotDraw(false);
    }

    public NumberView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setWillNotDraw(false);

    }

    public float thick=4f, round=4f;
    public float maxT=20f, maxR=20f;
    public int numberColor=0xff000000;
    public float height, width;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        DrawUtils.canvas=canvas;
        DrawUtils.drawNumber(8, (int)width, (int)height, canvas.getWidth() / 2, canvas.getHeight() / 2, thick, round, numberColor, 0xffffffff);

    }
}
