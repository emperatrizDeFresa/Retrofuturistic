package emperatriz.sveti;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.format.Time;

import com.google.android.gms.internal.ra;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by ramon on 1/05/15.
 */
public class DrawUtils {

    public static int height,width, p20;
    public static Canvas canvas;
    public static long now;
    public static Time mTime;
    public static boolean isInAmbientMode;
    public static Context ctx;

    private static int day=0xffaaaaaa, night= 0xffaaaaaa;


    private static Paint paint = new Paint();






    private static float p20(float factor){
        return p20*factor;
    }





    public static void drawBackground(int color, Paint paint){
        if (!isInAmbientMode){
            paint.setColor(color);
            canvas.drawRect(0, 0, width, height, paint);
        } else {
            paint.setColor(0xff000000);
            canvas.drawRect(0, 0, width, height,paint);
        }
    }




    public static void drawDate(int color, Paint paint2){


        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM");

        SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");

        Paint paint = paint2;
        paint.setAntiAlias(true);

        paint.setTextSize(p20(1.81f));



        boolean dayTime = (mTime.hour<20) && (mTime.hour>8);

        String dateText = sdf.format(Calendar.getInstance().getTime()).toUpperCase();
        String dateText2 = sdf2.format(Calendar.getInstance().getTime()).toUpperCase();

        float textWidth = paint.measureText(dateText);
        float textWidth2 = paint.measureText(dateText2);

        Rect r = new Rect();
        paint.getTextBounds(mTime.monthDay + "", 0, (mTime.monthDay + "").length(), r);

        if (!isInAmbientMode) {
//            paint.setShadowLayer(shadowRadius,0,0,color2);
            paint.setColor(color);

        }
        else{
//            paint.setShadowLayer(shadowRadius,0,0,0xff000000);
            paint.setColor(dayTime?day:night);
        }
        canvas.drawText(dateText,width/2-textWidth/2,p20(6.43f),paint);
        canvas.drawText(dateText2,width/2-textWidth2/2,height-p20(6.75f)+(p20(1.81f)),paint);


    }

    public static void drawSeconds(int color){
        if (!isInAmbientMode) {




            RectF r1 = new RectF();

            paint.setStrokeWidth(p20(2.125f));
            paint.setAntiAlias(true);
            paint.setStrokeCap(Paint.Cap.BUTT);
            paint.setStyle(Paint.Style.STROKE);
            paint.setFilterBitmap(true);
            r1.set(p20(1.125f), p20(1.125f), width-p20(1.125f), width-p20(1.125f));

            paint.setShadowLayer(0, 0, 0, 0x00000000);






            float[] hsv = new float[3];
            Color.colorToHSV(color, hsv);
            hsv[2] *= 0.40f; // value component
            int colorDark = Color.HSVToColor(hsv);

            for (int i=0;i<=59;i++){

                int add = i%3==0?0:0;

                float millis = System.currentTimeMillis() % 2000;
                int md = (int)Math.floor((millis / 2000l)*(60+add));
                int md2 = md+90%60;






                if (mTime.second==i){
                    paint.setColor(color);
                } else if (mTime.second==(i+1)%60){
                    paint.setColor(color);
                } else if ((md+mTime.second+(59+add))%(59+add)==i){//((md+mTime.second/3)%(20+add)==i/3)
                    paint.setColor(colorDark);
                }else if ((md+mTime.second+(59+add))%(59+add)==(i-1+(59+add))%(59+add)){//((md+mTime.second/3)%(20+add)==i/3)
                    paint.setColor(colorDark);
                }else if ((md+mTime.second+(59+add))%(59+add)==(i+1+(59+add))%(59+add)){//((md+mTime.second/3)%(20+add)==i/3)
                    paint.setColor(colorDark);
                }else if ((md2+mTime.second+(59+add))%(59+add)==i){//((md+mTime.second/3)%(20+add)==i/3)
                    paint.setColor(colorDark);
                }else if ((md2+mTime.second+(59+add))%(59+add)==(i-1+(59+add))%(59+add)){//((md+mTime.second/3)%(20+add)==i/3)
                    paint.setColor(colorDark);
                }else if ((md2+mTime.second+(59+add))%(59+add)==(i+1+(59+add))%(59+add)){//((md+mTime.second/3)%(20+add)==i/3)
                    paint.setColor(colorDark);
                }

                else {
                    paint.setColor(0xff333333);
                }
                canvas.drawArc(r1, i*6-90+1, 4, false, paint);

            }


            paint.setStrokeWidth(3);
            paint.setColor(color);
            canvas.drawOval(p20(2.5f), p20(2.5f), width - p20(2.5f), width - p20(2.5f), paint);



        }

    }



    public static void drawCenteredText(String text, Paint paint2){
        float size=p20(6.125f);
//        text = text.replace("1","l");
        Paint paint = paint2;
        paint.setAntiAlias(true);
        paint.setTextSize(size);


        float textWidth = paint.measureText(text);

        if (isInAmbientMode){
            paint.setColor(0xff000000);
            paint.setShadowLayer(2, 0, 0, 0xffffffff);
            canvas.drawText(text, width / 2 - textWidth / 2, (height - size) / 2 + size - p20(0.875f), paint);
            canvas.drawText(text, width / 2 - textWidth / 2, (height - size) / 2 + size - p20(0.875f), paint);
            canvas.drawText(text, width / 2 - textWidth / 2, (height - size) / 2 + size - p20(0.875f), paint);
            canvas.drawText(text, width / 2 - textWidth / 2, (height - size) / 2 + size - p20(0.875f), paint);
            canvas.drawText(text, width / 2 - textWidth / 2, (height - size) / 2 + size - p20(0.875f), paint);
            canvas.drawText(text, width / 2 - textWidth / 2, (height - size) / 2 + size - p20(0.875f), paint);


        }else{
            paint.setColor(0xffffffff);
            paint.setShadowLayer(0, 0, 0, 0xffffffff);
        }


        canvas.drawText(text,width/2-textWidth/2,(height-size)/2+size-14,paint);

    }

}
