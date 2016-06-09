package com.appdev.adtask2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by SasiDKM on 08-06-2016.
 */
public class JavaCan extends View {

    Canvas Cs;
    Bitmap Bm;
    int x,y,x1,y1;

    public JavaCan(Context c)
    {
        super(c);
        Bm= BitmapFactory.decodeResource(getResources(),R.mipmap.bsquare);
        x=0;
        y=0;
    }

    public void  putPos(int w,int h){
        x1=w;
        y1=h;
    }

    @Override
    protected void onDraw(Canvas C){
        super.onDraw(C);

        Cs=C;

        //for Up direction
        if(y1<0)
        {
          if(y1<0){
             y1++;
              y--;
          }
          else
          {   y1=0; }

         if(y<0){
             y=C.getHeight()-Bm.getHeight();
         }
        }

        //for Down Direction
        else if(y1>0){
            if(y1>0){
                y1--;
                y++;
            }
            else{
                y1=0;
            }
            if(C.getHeight()-Bm.getHeight()<y)
            {
                y=0;
            }
        }

        //for Right Direction
        if(x1>0){
            if(x1>0){
                x++;
                x1--;
            }
            else{
                x1=0;
            }
            if(C.getWidth()-Bm.getWidth()>x){
                x=0;
            }
        }

        else if(x1<0){
            if(x1<0){
                x--;
                x1--;
            }
            else{
                x1=0;
            }
            if (x<0){
                x=C.getWidth()-Bm.getWidth();
            }
        }
        Paint pn= new Paint();
        C.drawBitmap(Bm,x,y,pn);
        invalidate();

    }


    public void putSize(String sh,String sz){
        if(sh.equals("square")) {
            Bm = BitmapFactory.decodeResource(getResources(), R.mipmap.bsquare);
            if (sz.equals("large")) {
                Matrix mt = new Matrix();
                int hg = Bm.getHeight();
                int wd = Bm.getWidth();
                float hscale = ((float) Cs.getWidth()) / hg;
                float wscale = ((float) Cs.getWidth()) / wd;

                mt.postScale(wscale, hscale);

                Bitmap bmNew = Bitmap.createBitmap(Bm, 0, 0, wd, hg, mt, false);
                Bm.recycle();
                Bm = bmNew;

            } else if (sz.equals("medium")) {
                Matrix mt = new Matrix();
                int hg = Bm.getHeight();
                int wd = Bm.getWidth();
                float hscale = ((float) (Cs.getWidth() / 2)) / hg;
                float wscale = ((float) (Cs.getWidth() / 2)) / wd;

                mt.postScale(wscale, hscale);

                Bitmap bmNew = Bitmap.createBitmap(Bm, 0, 0, wd, hg, mt, false);
                Bm.recycle();
                Bm = bmNew;

            } else if (sz.equals("small")) {
                Matrix mt = new Matrix();
                int hg = Bm.getHeight();
                int wd = Bm.getWidth();
                float hscale = ((float) 60) / hg;
                float wscale = ((float) 60) / wd;

                mt.postScale(wscale, hscale);

                Bitmap bmNew = Bitmap.createBitmap(Bm, 0, 0, wd, hg, mt, false);
                Bm.recycle();
                Bm = bmNew;

            }
        }

        else if(sh.equals("rectangle")) {
            Bm = BitmapFactory.decodeResource(getResources(), R.mipmap.bsquare);
            if (sz.equals("large")) {
                Matrix mt = new Matrix();
                int hg = Bm.getHeight();
                int wd = Bm.getWidth();
                float hscale = ((float) (Cs.getWidth()/2)) / hg;
                float wscale = ((float) Cs.getWidth()) / wd;

                mt.postScale(wscale, hscale);

                Bitmap bmNew = Bitmap.createBitmap(Bm, 0, 0, wd, hg, mt, false);
                Bm.recycle();
                Bm = bmNew;

            } else if (sz.equals("medium")) {
                Matrix mt = new Matrix();
                int hg = Bm.getHeight();
                int wd = Bm.getWidth();
                float hscale = ((float) (Cs.getWidth() /4)) / hg;
                float wscale = ((float) (Cs.getWidth() /2)) / wd;

                mt.postScale(wscale, hscale);

                Bitmap bmNew = Bitmap.createBitmap(Bm, 0, 0, wd, hg, mt, false);
                Bm.recycle();
                Bm = bmNew;

            } else if (sz.equals("small")) {
                int hg = Bm.getHeight();
                int wd = Bm.getWidth();
                Matrix mt = new Matrix();
                float hscale = ((float) 60) / hg;
                float wscale = ((float) 120) / wd;

                mt.postScale(wscale, hscale);

                Bitmap bmNew = Bitmap.createBitmap(Bm, 0, 0, wd, hg, mt, false);
                Bm.recycle();
                Bm = bmNew;

            }
        }

        else if(sh.equals("circle")) {
            Bm = BitmapFactory.decodeResource(getResources(), R.mipmap.bcircle);
            if (sz.equals("large")) {
                Matrix mt = new Matrix();
                int hg = Bm.getHeight();
                int wd = Bm.getWidth();
                float hscale = ((float) Cs.getWidth()) / hg;
                float wscale = ((float) Cs.getWidth()) / wd;

                mt.postScale(wscale, hscale);

                Bitmap bmNew = Bitmap.createBitmap(Bm, 0, 0, wd, hg, mt, false);
                Bm.recycle();
                Bm = bmNew;

            } else if (sz.equals("medium")) {
                Matrix mt = new Matrix();
                int hg = Bm.getHeight();
                int wd = Bm.getWidth();
                float hscale = ((float) (Cs.getWidth()/2))/ hg;
                float wscale = ((float) (Cs.getWidth()/2))/ wd;

                mt.postScale(wscale, hscale);

                Bitmap bmNew = Bitmap.createBitmap(Bm, 0, 0, wd, hg, mt, false);
                Bm.recycle();
                Bm = bmNew;

            } else if (sz.equals("small")) {
                int hg = Bm.getHeight();
                int wd = Bm.getWidth();
                Matrix mt = new Matrix();
                float hscale = ((float) 60) / hg;
                float wscale = ((float) 60) / wd;

                mt.postScale(wscale, hscale);

                Bitmap bmNew = Bitmap.createBitmap(Bm, 0, 0, wd, hg, mt, false);
                Bm.recycle();
                Bm = bmNew;

            }
        }
        if(x>Cs.getWidth()-Bm.getWidth()){
            x=0;
        }
        if(y>Cs.getHeight()-Bm.getHeight()){
           y=0;
        }

    }
}




