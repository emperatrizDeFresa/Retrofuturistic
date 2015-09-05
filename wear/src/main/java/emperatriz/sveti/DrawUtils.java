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

    public static int height,width,chunk;
    public static Canvas canvas;
    public static long now;
    public static Time mTime;
    public static boolean isInAmbientMode;
    public static Context ctx;

    private static int day=0xffaaaaaa, night= 0xffaaaaaa;


    static Paint paint = new Paint();
    static Paint accentPaint = new Paint();

    static float textSize=120;
    static float padding=40;

    static float dateheight=29;
    static float dateWidth=6;
    static float dateThick=2;
    static float paddingDate=2;
    static float dateOffset=60*0;

    static float secondSizeH=276;
    static float secondSizeW=90;
    public static float thick=20;
    static float paddingSec=24;

    public static float hourThick=1;
    static float hourHeight=96;
    static float hourWidth=32;

    public static float minThick=3;
    static float minHeight=96;
    static float minWidth=32;

    static float paddingTime=24;

    static int shadowRadius=4;





    public static void drawBackground(int color, Paint paint){




        if (!isInAmbientMode){
            paint.setColor(color);
            canvas.drawRect(0, 0, width, height, paint);
        } else {
            paint.setColor(0xff000000);
            canvas.drawRect(0,0,width,height,paint);
        }
    }

    public static void drawHours(int color, float radius, int background){


        float hX = width/2 - paddingTime/4 - hourWidth/2;
        float hY = height/2 ;

        int h1 = mTime.hour/10;
        int h2 = mTime.hour%10;

        boolean dayTime = (mTime.hour<20) && (mTime.hour>8);


        if (!isInAmbientMode) {
            paint.setColor(color);
//            if (h1==0){
//                drawNumber(h2,(int) hourWidth,(int)  hourHeight,(int)hX,(int)hY, hourThick, radius, color, background);
//            }
//            else{
                drawNumber(h1, (int) hourWidth, (int) hourHeight, (int) Math.round(hX - hourWidth - paddingTime / 2), (int) hY, hourThick, radius, color, background);
                drawNumber(h2, (int) hourWidth, (int) hourHeight, (int) hX, (int) hY, hourThick, radius, color, background);
//            }
        } else {
//            if (h1==0){
//                drawNumber(h2,(int) hourWidth,(int)  hourHeight,(int)hX,(int)hY, hourThick, radius, dayTime?day:night, 0xff000000,true);
//            }
//            else{
                drawNumber(h1, (int) hourWidth, (int) hourHeight, (int) Math.round(hX - hourWidth - paddingTime / 2), (int) hY, hourThick, radius, dayTime ? day : night, 0xff000000, true);
                drawNumber(h2, (int) hourWidth, (int) hourHeight, (int) hX, (int) hY, hourThick, radius, dayTime ? day : night, 0xff000000, true);
//            }
        }
    }

    public static void drawMinutes(int color, float radius, int background){

        boolean dayTime = (mTime.hour<20) && (mTime.hour>8);

        float hX = width/2 + paddingTime/4 + minWidth/2;
        float hY = height/2 ;

        int h1 = mTime.minute/10;
        int h2 = mTime.minute%10;


        if (!isInAmbientMode) {
            paint.setColor(color);

                drawNumber(h2,(int) minWidth,(int)  minHeight, (int) Math.round(hX+minWidth+paddingTime/2),(int) hY, minThick ,radius, color, background);
                drawNumber(h1,(int) minWidth,(int)  minHeight,(int)  hX,(int) hY, minThick ,radius, color, background);

        } else {


                drawNumber(h2,(int) minWidth,(int)  minHeight, (int) Math.round(hX+minWidth+paddingTime/2),(int) hY, minThick ,radius, dayTime?day:night, 0xff000000, true);
                drawNumber(h1,(int) minWidth,(int)  minHeight,(int)  hX,(int) hY, minThick ,radius, dayTime?day:night, 0xff000000,true);



        }
    }

    public static void drawDate(float radius, int color, int color2,Paint paint2){


        SimpleDateFormat sdf = new SimpleDateFormat("d MMMM");

        SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");

        Paint paint = paint2;
        paint.setAntiAlias(true);

        paint.setTextSize(dateheight);



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
        canvas.drawText(dateText,width/2-textWidth/2,103,paint);
        canvas.drawText(dateText2,width/2-textWidth2/2,320-108+dateheight,paint);

//        float mX = width/2;
//        float mY = height/2 ;
//
//        int d1 = mTime.monthDay/10;
//        int d2 = mTime.monthDay%10;
//
//        if (!isInAmbientMode) {
//            paint.setColor(color2);
//            canvas.drawRoundRect(mX - dateWidth - 4, mY - dateheight/2 - 4+dateOffset, mX + dateWidth + 4, mY + dateheight/2 + 4+dateOffset, 4, 4, paint);
//            if (d1==0){
//                drawNumber(d2,(int) dateWidth,(int)  dateheight,(int)  (width/2),(int) Math.round(height/2+dateOffset), dateThick, radius, color);
//            }
//            else{
//                drawNumber(d1,(int) dateWidth,(int)  dateheight,(int)  (width/2 - dateWidth/2 - paddingDate/2),(int) Math.round(height/2+dateOffset), dateThick ,radius, color);
//                drawNumber(d2,(int) dateWidth,(int)  dateheight,(int)  (width/2 + dateWidth/2 + paddingDate/2),(int) Math.round(height/2+dateOffset), dateThick ,radius, color);
//            }
//        } else {
//            if (d1==0){
//                drawNumber(d2,(int) dateWidth,(int)  dateheight,(int)  (width/2),(int) Math.round(height/2+dateOffset), dateThick ,radius, dayTime?day:night);
//            }
//            else{
//                drawNumber(d1,(int) dateWidth,(int)  dateheight,(int)  (width/2 - dateWidth/2 - paddingDate/2),(int) Math.round(height/2+dateOffset), dateThick ,radius, dayTime?day:night);
//                drawNumber(d2,(int) dateWidth,(int)  dateheight,(int)  (width/2 + dateWidth/2 + paddingDate/2),(int) Math.round(height/2+dateOffset), dateThick ,radius, dayTime?day:night);
//            }
//        }
    }

    public static void drawSeconds(float radius, int color){
        if (!isInAmbientMode) {
//            int s1 = mTime.second / 10;
//            int s2 = mTime.second % 10;
//
//            drawNumber(s1, (int) secondSizeW, (int) secondSizeH, (int) (width / 2 - secondSizeW/2 - paddingSec / 2), (int) height / 2, thick, radius,  color, 0x00000000);
//            drawNumber(s2, (int) secondSizeW, (int) secondSizeH, (int) (width / 2 + secondSizeW/2 + paddingSec / 2), (int) height / 2, thick, radius,  color, 0x00000000);

            RectF r1 = new RectF();
            paint.setStrokeWidth(50);
            paint.setAntiAlias(true);
            paint.setStrokeCap(Paint.Cap.BUTT);
            paint.setStyle(Paint.Style.STROKE);
            paint.setFilterBitmap(true);
            r1.set(8, 8, 312, 312);

            paint.setShadowLayer(0, 0, 0, 0x00000000);

            float[] hsv = new float[3];
            Color.colorToHSV(color, hsv);
            hsv[2] *= 0.45f; // value component
            int colorDark = Color.HSVToColor(hsv);

            for (int i=0;i<=59;i++){

                int add = i%3==0?0:0;

                float millis = System.currentTimeMillis() % 2000;
                int md = (int)Math.floor((millis / 2000l)*(60+add));
                int md2 = md+90%60;



                if (mTime.second==i){
                    paint.setColor(color);
//                    paint.setAlpha(255);
                } else if (mTime.second==(i+1)%60){
                    paint.setColor(color);
//                    paint.setAlpha(255);
                } else if ((md+mTime.second+(59+add))%(59+add)==i){//((md+mTime.second/3)%(20+add)==i/3)
                    paint.setColor(colorDark);
//                    paint.setAlpha(120);
                }else if ((md+mTime.second+(59+add))%(59+add)==(i-1+(59+add))%(59+add)){//((md+mTime.second/3)%(20+add)==i/3)
                    paint.setColor(colorDark);
//                    paint.setAlpha(120);
                }else if ((md+mTime.second+(59+add))%(59+add)==(i+1+(59+add))%(59+add)){//((md+mTime.second/3)%(20+add)==i/3)
                    paint.setColor(colorDark);
//                    paint.setAlpha(120);
                }else if ((md2+mTime.second+(59+add))%(59+add)==i){//((md+mTime.second/3)%(20+add)==i/3)
                    paint.setColor(colorDark);
//                    paint.setAlpha(120);
                }else if ((md2+mTime.second+(59+add))%(59+add)==(i-1+(59+add))%(59+add)){//((md+mTime.second/3)%(20+add)==i/3)
                    paint.setColor(colorDark);
//                    paint.setAlpha(120);
                }else if ((md2+mTime.second+(59+add))%(59+add)==(i+1+(59+add))%(59+add)){//((md+mTime.second/3)%(20+add)==i/3)
                    paint.setColor(colorDark);
//                    paint.setAlpha(120);
                }
//                else if ((md+mTime.second/3)%(20+add)==i/3){
//                paint.setColor(color);
//                paint.setAlpha(120);
//                }
                else {
                    paint.setColor(0xff333333);
//                    paint.setAlpha(255);
                }
                canvas.drawArc(r1, i*6-90+1, 4, false, paint);

            }
            paint.setColor(color);
//            canvas.drawArc(r1, mTime.second*6-90+1, 4, false, paint);
//
//            float millis = System.currentTimeMillis() % 1000;
//            float md = millis / 1000l;
//
//            canvas.drawArc(r1, md*366f+mTime.second*6-90+1, 4, false, paint);


            paint.setStrokeWidth(3);
            canvas.drawOval(40, 40, 280, 280, paint);

//            paint.setShadowLayer(60, 0, 0, color);
//            paint.setColor(0xff000000);
//            paint.setStyle(Paint.Style.FILL);
//            canvas.drawOval(40, 40, 280, 280, paint);


        }
        else{
//            paint.setColor(0xff888888);
//            paint.setStrokeWidth(2);
//            canvas.drawOval(40, 40, 280, 280, paint);
        }
    }



    public static void drawCenteredText(String text, Paint paint2){
        int size=98;
//        text = text.replace("1","l");
        Paint paint = paint2;
        paint.setAntiAlias(true);
        paint.setTextSize(size);


        float textWidth = paint.measureText(text);

        if (isInAmbientMode){
            paint.setColor(0xff000000);
            paint.setShadowLayer(2, 0, 0, 0xffffffff);
            canvas.drawText(text, width / 2 - textWidth / 2, (320 - size) / 2 + size - 14, paint);
            canvas.drawText(text, width / 2 - textWidth / 2, (320 - size) / 2 + size - 14, paint);
            canvas.drawText(text, width / 2 - textWidth / 2, (320 - size) / 2 + size - 14, paint);
            canvas.drawText(text, width / 2 - textWidth / 2, (320 - size) / 2 + size - 14, paint);
            canvas.drawText(text, width / 2 - textWidth / 2, (320 - size) / 2 + size - 14, paint);
            canvas.drawText(text, width / 2 - textWidth / 2, (320 - size) / 2 + size - 14, paint);


        }else{
            paint.setColor(0xffffffff);
            paint.setShadowLayer(0, 0, 0, 0xffffffff);
        }


        canvas.drawText(text,width/2-textWidth/2,(320-size)/2+size-14,paint);

    }

    public static void drawNumber(int number, int width, int height, int x, int y, float thick, float radius, int color, int background){
        drawNumber2(number, width, height, x, y, thick, radius, color, background, false, false);
        drawNumber2(number, width, height, x, y, thick, radius, color, background, false,false);
    }


    public static void drawNumber(int number, int width, int height, int x, int y, float thick, float radius, int color, int background, boolean hollow){
        drawNumber2(number, width, height, x, y, thick, radius, color, background, hollow, false);
        drawNumber2(number, width, height, x, y, thick, radius, color, background, hollow, false);
    }

    public static void drawNumber2(int number, int width, int height, int x, int y, float thick, float radius, int color, int background, boolean hollow, boolean shadow){

        final RectF r1 = new RectF();
        final RectF r2 = new RectF();
        final RectF r3 = new RectF();
        final RectF r4up = new RectF();
        final RectF r6up = new RectF();
        final RectF r4down = new RectF();
        final RectF r6up_2 = new RectF();
        final RectF r4down_2 = new RectF();
        final RectF r6down = new RectF();
        final RectF r7 = new RectF();
        final RectF r6down_5 = new RectF();
        final RectF r9 = new RectF();


        radius = Math.min(radius,width/2-thick/2);

        boolean hStripes = width>=radius*2;

        hollow = hollow && thick>=8;

        int hollowThick=2;


        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(thick);
        paint.setColor(color);
        if (shadow) paint.setShadowLayer(shadowRadius, 0, 0,background);

        int wPart = (int) width/2;
        int hPart = (int) height/2;
        int thickOffset = (int) thick/2;

        Point p1 = new Point(x-wPart+thickOffset, y-hPart+thickOffset);
        Point p2 = new Point(x, y-hPart+thickOffset);
        Point p3 = new Point(x+wPart-thickOffset, y-hPart+thickOffset);
        Point p4 = new Point(x-wPart+thickOffset, y);
        Point p5 = new Point(x, y);
        Point p6 = new Point(x+wPart-thickOffset, y);
        Point p7 = new Point(x-wPart+thickOffset, y+hPart-thickOffset);
        Point p8 = new Point(x, y+hPart-thickOffset);
        Point p9 = new Point(x+wPart-thickOffset, y+hPart-thickOffset);


        r1.set(p1.x, p1.y, p1.x + radius * 2, p1.y + radius * 2);
        r2.set(p2.x-radius*2, p2.y, p2.x, p2.y + radius * 2);
        r3.set(p3.x - radius * 2, p3.y,p3.x, p3.y + radius * 2);
        r4up.set(p4.x, p4.y-radius*2, p4.x + radius * 2, p4.y);
        r6up.set(p6.x - radius * 2, p6.y -radius*2,p6.x, p6.y);
        r4down.set(p4.x, p4.y, p4.x + radius * 2, p4.y+radius*2);
        r6up_2.set(p6.x - radius * 2, p6.y -radius*2+ radius,p6.x, p6.y+ radius);
        r4down_2.set(p4.x, p4.y+ radius, p4.x + radius * 2, p4.y+radius*2+ radius);
        r6down.set(p6.x - radius * 2, p6.y,p6.x, p6.y+radius*2);
        r7.set(p7.x, p7.y - radius * 2,p7.x+ radius*2, p7.y);
        r6down_5.set(p6.x - radius * 2, p6.y- radius,p6.x, p6.y+radius*2- radius);
        r9.set(p9.x - radius * 2, p9.y - radius * 2, p9.x, p9.y);


//        radius += thick;

        switch (number){
            case 0:{
                if (hStripes)
                canvas.drawLines(new float[]{p1.x+radius, p1.y, p3.x-radius, p3.y,
                                             p1.x, p1.y+radius, p7.x, p7.y-radius,
                                             p7.x+radius, p7.y, p9.x-radius, p9.y,
                                             p9.x, p9.y-radius, p3.x, p3.y+radius}, paint);
                else
                    canvas.drawLines(new float[]{
                            p1.x, p1.y + radius, p7.x, p7.y - radius,
                            p9.x, p9.y - radius, p3.x, p3.y + radius}, paint);

                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeCap(Paint.Cap.ROUND);
                canvas.drawArc(r1, 180, 90, false, paint);
                canvas.drawArc(r3,270,90,false,paint);
                canvas.drawArc(r7,90,90,false,paint);
                canvas.drawArc(r9,0,90,false,paint);

                if (hollow){
                    paint.setStrokeWidth(thick-hollowThick*2);
                    paint.setColor(0xff000000);
                    if (hStripes)
                        canvas.drawLines(new float[]{p1.x+radius, p1.y, p3.x-radius, p3.y,
                                p1.x, p1.y+radius, p7.x, p7.y-radius,
                                p7.x+radius, p7.y, p9.x-radius, p9.y,
                                p9.x, p9.y-radius, p3.x, p3.y+radius}, paint);
                    else
                        canvas.drawLines(new float[]{
                                p1.x, p1.y + radius, p7.x, p7.y - radius,
                                p9.x, p9.y - radius, p3.x, p3.y + radius}, paint);

                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeCap(Paint.Cap.ROUND);
                    canvas.drawArc(r1, 180, 90, false, paint);
                    canvas.drawArc(r3,270,90,false,paint);
                    canvas.drawArc(r7,90,90,false,paint);
                    canvas.drawArc(r9,0,90,false,paint);
                }

                break;
            }
            case 1:{
                canvas.drawLines(new float[]{p7.x, p7.y, p9.x, p9.y,
                                             p1.x, p1.y, p2.x-radius, p2.y,
                                             p2.x,p2.y+radius,p8.x,p8.y}, paint);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeCap(Paint.Cap.ROUND);
                canvas.drawArc(r2, 270, 90, false, paint);

                if (hollow) {
                    paint.setStrokeWidth(thick - hollowThick*2);
                    paint.setColor(0xff000000);

                    canvas.drawLines(new float[]{p7.x, p7.y, p9.x, p9.y,
                            p1.x, p1.y, p2.x-radius, p2.y,
                            p2.x,p2.y+radius,p8.x,p8.y}, paint);
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeCap(Paint.Cap.ROUND);
                    canvas.drawArc(r2, 270, 90, false, paint);
                }
                break;
            }
            case 2:{
                if (hStripes)
                    canvas.drawLines(new float[]{p1.x+radius,p1.y,p3.x-radius,p3.y,
                                             p3.x,p3.y+radius,p6.x,p6.y-radius+radius,
                                             p6.x-radius,p6.y+radius,p4.x+radius,p4.y+radius,
                                             p4.x,p4.y+radius+radius,p7.x,p7.y,
                                             p9.x,p9.y,p7.x,p7.y}, paint);
                else
                    canvas.drawLines(new float[]{
                                                 p3.x,p3.y+radius,p6.x,p6.y-radius,
                                                 p4.x, p4.y + radius, p7.x, p7.y,
                                                 p9.x,p9.y,p7.x,p7.y}, paint);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeCap(Paint.Cap.ROUND);
                canvas.drawArc(r1, 180, 90, false, paint);
                canvas.drawArc(r3, 270, 90, false, paint);
                canvas.drawArc(r6up_2, 0, 90, false, paint);
                canvas.drawArc(r4down_2, 180, 90, false, paint);

                if (hollow) {
                    paint.setStrokeWidth(thick - hollowThick*2);
                    paint.setColor(0xff000000);

                    if (hStripes)
                        canvas.drawLines(new float[]{p1.x+radius,p1.y,p3.x-radius,p3.y,
                                p3.x,p3.y+radius,p6.x,p6.y-radius+radius,
                                p6.x-radius,p6.y+radius,p4.x+radius,p4.y+radius,
                                p4.x,p4.y+radius+radius,p7.x,p7.y,
                                p9.x,p9.y,p7.x,p7.y}, paint);
                    else
                        canvas.drawLines(new float[]{
                                p3.x,p3.y+radius,p6.x,p6.y-radius,
                                p4.x, p4.y + radius, p7.x, p7.y,
                                p9.x,p9.y,p7.x,p7.y}, paint);
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeCap(Paint.Cap.ROUND);
                    canvas.drawArc(r1, 180, 90, false, paint);
                    canvas.drawArc(r3, 270, 90, false, paint);
                    canvas.drawArc(r6up_2, 0, 90, false, paint);
                    canvas.drawArc(r4down_2, 180, 90, false, paint);
                }

                break;
            }
            case 3:{
                if (hStripes)
                    canvas.drawLines(new float[]{p1.x+radius,p1.y,p3.x-radius,p3.y,
                                             p3.x,p3.y+radius,p6.x,p6.y-radius,
                                             p6.x,p6.y+radius,p9.x,p9.y-radius,
                                             p6.x-radius,p6.y,p5.x,p5.y,
                                             p9.x-radius,p9.y,p7.x+radius,p7.y}, paint);
                else
                    canvas.drawLines(new float[]{
                            p3.x, p3.y + radius, p6.x, p6.y - radius,
                            p6.x, p6.y + radius, p9.x, p9.y - radius,
                            p6.x - radius, p6.y, p5.x, p5.y}, paint);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeCap(Paint.Cap.ROUND);
                canvas.drawArc(r1, 180, 90, false, paint);
                canvas.drawArc(r3, 270, 90, false, paint);
                canvas.drawArc(r6up, 0, 90, false, paint);
                canvas.drawArc(r6down, 270, 90, false, paint);
                canvas.drawArc(r7, 90, 90, false, paint);
                canvas.drawArc(r9, 0, 90, false, paint);

                if (hollow) {
                    paint.setStrokeWidth(thick - hollowThick*2);
                    paint.setColor(0xff000000);

                    if (hStripes)
                        canvas.drawLines(new float[]{p1.x+radius,p1.y,p3.x-radius,p3.y,
                                p3.x,p3.y+radius,p6.x,p6.y-radius,
                                p6.x,p6.y+radius,p9.x,p9.y-radius,
                                p6.x-radius,p6.y,p5.x,p5.y,
                                p9.x-radius,p9.y,p7.x+radius,p7.y}, paint);
                    else
                        canvas.drawLines(new float[]{
                                p3.x, p3.y + radius, p6.x, p6.y - radius,
                                p6.x, p6.y + radius, p9.x, p9.y - radius,
                                p6.x - radius, p6.y, p5.x, p5.y}, paint);
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeCap(Paint.Cap.ROUND);
                    canvas.drawArc(r1, 180, 90, false, paint);
                    canvas.drawArc(r3, 270, 90, false, paint);
                    canvas.drawArc(r6up, 0, 90, false, paint);
                    canvas.drawArc(r6down, 270, 90, false, paint);
                    canvas.drawArc(r7, 90, 90, false, paint);
                    canvas.drawArc(r9, 0, 90, false, paint);
                }

                break;
            }
            case 4:{
                canvas.drawLines(new float[]{p1.x,p1.y,p4.x,p4.y-radius,
                                             p4.x+radius,p4.y,p6.x,p6.y,
                                             p3.x,p3.y,p9.x,p9.y}, paint);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeCap(Paint.Cap.ROUND);
                canvas.drawArc(r4up, 90, 90, false, paint);

                if (hollow) {
                    paint.setStrokeWidth(thick - hollowThick*2);
                    paint.setColor(0xff000000);

                    canvas.drawLines(new float[]{p1.x,p1.y,p4.x,p4.y-radius,
                            p4.x+radius,p4.y,p6.x,p6.y,
                            p3.x,p3.y,p9.x,p9.y}, paint);
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeCap(Paint.Cap.ROUND);
                    canvas.drawArc(r4up, 90, 90, false, paint);
                }

                break;
            }
            case 5:{
                if (hStripes)
                    canvas.drawLines(new float[]{p1.x,p1.y,p3.x,p3.y,
                                             p1.x,p1.y,p4.x,p4.y-radius,
                                             p6.x-radius,p6.y-radius,p4.x,p4.y-radius,
                                             p6.x,p6.y+radius-radius,p9.x,p9.y-radius,
                                             p9.x-radius,p9.y,p7.x+radius,p7.y}, paint);
                else
                    canvas.drawLines(new float[]{p1.x,p1.y,p3.x,p3.y,
                            p1.x,p1.y,p4.x,p4.y-radius,
                            p6.x-radius,p6.y-radius,p4.x,p4.y-radius,
                            p6.x,p6.y+radius-radius,p9.x,p9.y-radius}, paint);

                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeCap(Paint.Cap.ROUND);
                canvas.drawArc(r6down_5, 270, 90, false, paint);
                canvas.drawArc(r7, 90, 90, false, paint);
                canvas.drawArc(r9, 0, 90, false, paint);


                if (hollow) {
                    paint.setStrokeWidth(thick - hollowThick*2);
                    paint.setColor(0xff000000);

                    if (hStripes)
                        canvas.drawLines(new float[]{p1.x,p1.y,p3.x,p3.y,
                                p1.x,p1.y,p4.x,p4.y-radius,
                                p6.x-radius,p6.y-radius,p4.x,p4.y-radius,
                                p6.x,p6.y+radius-radius,p9.x,p9.y-radius,
                                p9.x-radius,p9.y,p7.x+radius,p7.y}, paint);
                    else
                        canvas.drawLines(new float[]{p1.x,p1.y,p3.x,p3.y,
                                p1.x,p1.y,p4.x,p4.y-radius,
                                p6.x-radius,p6.y-radius,p4.x,p4.y-radius,
                                p6.x,p6.y+radius-radius,p9.x,p9.y-radius}, paint);

                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeCap(Paint.Cap.ROUND);
                    canvas.drawArc(r6down_5, 270, 90, false, paint);
                    canvas.drawArc(r7, 90, 90, false, paint);
                    canvas.drawArc(r9, 0, 90, false, paint);
                }

                break;
            }
            case 6:{
                if (hStripes)
                    canvas.drawLines(new float[]{p1.x+radius,p1.y,p3.x-radius,p3.y,
                                             p1.x,p1.y+radius,p7.x,p7.y-radius,
                                             p7.x+radius,p7.y,p9.x-radius,p9.y,
                                             p9.x,p9.y-radius,p6.x,p6.y+radius,
                                             p4.x+radius,p4.y,p6.x-radius,p6.y}, paint);
                else
                    canvas.drawLines(new float[]{
                            p1.x,p1.y+radius,p7.x,p7.y-radius,
                            p9.x,p9.y-radius,p6.x,p6.y+radius}, paint);

                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeCap(Paint.Cap.ROUND);
                canvas.drawArc(r1, 180, 90, false, paint);
                canvas.drawArc(r3, 270, 90, false, paint);
                canvas.drawArc(r6down, 270, 90, false, paint);
                canvas.drawArc(r4down, 180, 90, false, paint);
                canvas.drawArc(r7, 90, 90, false, paint);
                canvas.drawArc(r9, 0, 90, false, paint);

                if (hollow) {
                    paint.setStrokeWidth(thick - hollowThick*2);
                    paint.setColor(0xff000000);

                    if (hStripes)
                        canvas.drawLines(new float[]{p1.x+radius,p1.y,p3.x-radius,p3.y,
                                p1.x,p1.y+radius,p7.x,p7.y-radius,
                                p7.x+radius,p7.y,p9.x-radius,p9.y,
                                p9.x,p9.y-radius,p6.x,p6.y+radius,
                                p4.x+radius,p4.y,p6.x-radius,p6.y}, paint);
                    else
                        canvas.drawLines(new float[]{
                                p1.x,p1.y+radius,p7.x,p7.y-radius,
                                p9.x,p9.y-radius,p6.x,p6.y+radius}, paint);

                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeCap(Paint.Cap.ROUND);
                    canvas.drawArc(r1, 180, 90, false, paint);
                    canvas.drawArc(r3, 270, 90, false, paint);
                    canvas.drawArc(r6down, 270, 90, false, paint);
                    canvas.drawArc(r4down, 180, 90, false, paint);
                    canvas.drawArc(r7, 90, 90, false, paint);
                    canvas.drawArc(r9, 0, 90, false, paint);
                }

                break;
            }
            case 7:{
                canvas.drawLines(new float[]{p1.x,p1.y,p3.x-radius,p3.y,
                                             //p5.x,p5.y,p6.x,p6.y,
                                             p9.x,p9.y,p3.x,p3.y+radius}, paint);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeCap(Paint.Cap.ROUND);
                canvas.drawArc(r3, 270, 90, false, paint);

                if (hollow) {
                    paint.setStrokeWidth(thick - hollowThick*2);
                    paint.setColor(0xff000000);

                    canvas.drawLines(new float[]{p1.x,p1.y,p3.x-radius,p3.y,
                            //p5.x,p5.y,p6.x,p6.y,
                            p9.x,p9.y,p3.x,p3.y+radius}, paint);
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeCap(Paint.Cap.ROUND);
                    canvas.drawArc(r3, 270, 90, false, paint);
                }

                break;
            }
            case 8:{
                if (hStripes)
                    canvas.drawLines(new float[]{p1.x+radius,p1.y,p3.x-radius,p3.y,
                                             p1.x,p1.y+radius,p4.x,p4.y-radius,
                                             p7.x+radius,p7.y,p9.x-radius,p9.y,
                                             p4.x,p4.y+radius,p7.x,p7.y-radius,
                                             p3.x,p3.y+radius,p6.x,p6.y-radius,
                                             p6.x,p6.y+radius,p9.x,p9.y-radius,
                                             p4.x+radius,p4.y,p6.x-radius,p6.y}, paint);
                else
                    canvas.drawLines(new float[]{
                            p1.x, p1.y + radius, p4.x, p4.y - radius,
                            p4.x, p4.y + radius, p7.x, p7.y - radius,
                            p3.x, p3.y + radius, p6.x, p6.y - radius,
                            p6.x, p6.y + radius, p9.x, p9.y - radius}, paint);

                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeCap(Paint.Cap.ROUND);
                canvas.drawArc(r1, 180, 90, false, paint);
                canvas.drawArc(r3, 270, 90, false, paint);
                canvas.drawArc(r6down, 270, 90, false, paint);
                canvas.drawArc(r4down, 180, 90, false, paint);
                canvas.drawArc(r6up, 0, 90, false, paint);
                canvas.drawArc(r4up, 90, 90, false, paint);
                canvas.drawArc(r7, 90, 90, false, paint);
                canvas.drawArc(r9, 0, 90, false, paint);

                if (hollow) {
                    paint.setStrokeWidth(thick - hollowThick*2);
                    paint.setColor(0xff000000);

                    if (hStripes)
                        canvas.drawLines(new float[]{p1.x+radius,p1.y,p3.x-radius,p3.y,
                                p1.x,p1.y+radius,p4.x,p4.y-radius,
                                p7.x+radius,p7.y,p9.x-radius,p9.y,
                                p4.x,p4.y+radius,p7.x,p7.y-radius,
                                p3.x,p3.y+radius,p6.x,p6.y-radius,
                                p6.x,p6.y+radius,p9.x,p9.y-radius,
                                p4.x+radius,p4.y,p6.x-radius,p6.y}, paint);
                    else
                        canvas.drawLines(new float[]{
                                p1.x, p1.y + radius, p4.x, p4.y - radius,
                                p4.x, p4.y + radius, p7.x, p7.y - radius,
                                p3.x, p3.y + radius, p6.x, p6.y - radius,
                                p6.x, p6.y + radius, p9.x, p9.y - radius}, paint);

                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeCap(Paint.Cap.ROUND);
                    canvas.drawArc(r1, 180, 90, false, paint);
                    canvas.drawArc(r3, 270, 90, false, paint);
                    canvas.drawArc(r6down, 270, 90, false, paint);
                    canvas.drawArc(r4down, 180, 90, false, paint);
                    canvas.drawArc(r6up, 0, 90, false, paint);
                    canvas.drawArc(r4up, 90, 90, false, paint);
                    canvas.drawArc(r7, 90, 90, false, paint);
                    canvas.drawArc(r9, 0, 90, false, paint);
                }

                break;
            }
            case 9:{
                if (hStripes)
                    canvas.drawLines(new float[]{p1.x + radius, p1.y, p3.x - radius, p3.y,
                        p1.x, p1.y + radius, p4.x, p4.y - radius,
                        p7.x + radius, p7.y, p9.x - radius, p9.y,
                        p9.x, p9.y - radius, p3.x, p3.y + radius,
                        p4.x + radius, p4.y,p6.x-radius,p6.y}, paint);
                else
                    canvas.drawLines(new float[]{
                            p1.x, p1.y + radius, p4.x, p4.y - radius,

                            p9.x, p9.y - radius, p3.x, p3.y + radius}, paint);

                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeCap(Paint.Cap.ROUND);
                canvas.drawArc(r1, 180, 90, false, paint);
                canvas.drawArc(r3, 270, 90, false, paint);
                canvas.drawArc(r6up, 0, 90, false, paint);
                canvas.drawArc(r4up, 90, 90, false, paint);
                canvas.drawArc(r7, 90, 90, false, paint);
                canvas.drawArc(r9, 0, 90, false, paint);

                if (hollow) {
                    paint.setStrokeWidth(thick - hollowThick*2);
                    paint.setColor(0xff000000);

                    if (hStripes)
                        canvas.drawLines(new float[]{p1.x + radius, p1.y, p3.x - radius, p3.y,
                                p1.x, p1.y + radius, p4.x, p4.y - radius,
                                p7.x + radius, p7.y, p9.x - radius, p9.y,
                                p9.x, p9.y - radius, p3.x, p3.y + radius,
                                p4.x + radius, p4.y,p6.x-radius,p6.y}, paint);
                    else
                        canvas.drawLines(new float[]{
                                p1.x, p1.y + radius, p4.x, p4.y - radius,

                                p9.x, p9.y - radius, p3.x, p3.y + radius}, paint);

                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeCap(Paint.Cap.ROUND);
                    canvas.drawArc(r1, 180, 90, false, paint);
                    canvas.drawArc(r3, 270, 90, false, paint);
                    canvas.drawArc(r6up, 0, 90, false, paint);
                    canvas.drawArc(r4up, 90, 90, false, paint);
                    canvas.drawArc(r7, 90, 90, false, paint);
                    canvas.drawArc(r9, 0, 90, false, paint);
                }

                break;
            }
        }

    }

}
