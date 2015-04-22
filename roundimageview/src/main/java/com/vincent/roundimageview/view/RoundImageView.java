package com.vincent.roundimageview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.vincent.roundimageview.R;

public class RoundImageView extends View {

    private int type;
    private static final int TYPE_CIRCLE = 0;
    private static final int TYPE_ROUND = 1;
    private Bitmap srcBitmap;
    private int mRadius;
    private int mWidth;
    private int mHeight;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundImageView, defStyleAttr, 0);
        int count = ta.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = ta.getIndex(i);
            switch (attr) {
                case R.styleable.RoundImageView_src:
                    srcBitmap = BitmapFactory.decodeResource(context.getResources(), ta.getResourceId(attr, 0));
                    break;
                case R.styleable.RoundImageView_radius:
                    mRadius = ta.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f,
                            getResources().getDisplayMetrics()));// 默认为10DP
                    break;
                case R.styleable.RoundImageView_type:
                    type = ta.getInt(attr, 0);
                    break;
            }
        }
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置宽度
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) { //match_parent
            mWidth = widthSize;
        } else {//由图片的宽度决定
            int picWidth = getPaddingLeft() + getPaddingRight() + srcBitmap.getWidth();
            if (widthMode == MeasureSpec.AT_MOST) {//wrap_content
                mWidth = Math.min(picWidth, widthSize);
            } else {
                mWidth = picWidth;
            }
        }

        //设置高度
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            int picHeight = getPaddingTop() + getPaddingBottom() + srcBitmap.getHeight();
            if (heightMode == MeasureSpec.AT_MOST) {
                mHeight = Math.min(picHeight, heightSize);
            } else {
                mHeight = heightSize;
            }
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        switch (type) {
            case TYPE_CIRCLE:
                int min = Math.min(mWidth, mHeight);
                srcBitmap = Bitmap.createScaledBitmap(srcBitmap, min, min, false);
                canvas.drawBitmap(createCircleBitmap(srcBitmap, min), 0, 0, null);
                break;
            case TYPE_ROUND:
                canvas.drawBitmap(createRoundBitmap(srcBitmap), 0, 0, null);
                break;
        }
    }

    private Bitmap createCircleBitmap(Bitmap srcBitmap, int min) {
        final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(srcBitmap, 0, 0, paint);
        return target;
    }

    private Bitmap createRoundBitmap(Bitmap srcBitmap) {
        final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Bitmap target = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        RectF rectF = new RectF(0, 0, srcBitmap.getWidth(), srcBitmap.getHeight());
        canvas.drawRoundRect(rectF, mRadius, mRadius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(srcBitmap, 0, 0, paint);
        return target;
    }
}
