package com.ambe.customprogressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by AMBE on 10/6/2018 at 8:31 AM.
 */
public class MyCustomView extends View {

    private Paint mCircleYellow;
    private Paint mCircleBlue;
    private Paint mAcrRoundBlack;
    private Paint mAcrRoundBlue;

    private float mCenterX;
    private float mCenterY;
    private float mRadius;
    private RectF mArcBounds = new RectF();
    private int mProgress;


    public MyCustomView(Context context) {
        super(context, null);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        initPaint();
    }

    private void initPaint() {
        mCircleYellow = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleYellow.setStyle(Paint.Style.FILL);
        mCircleYellow.setColor(Color.YELLOW);

        mCircleBlue = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleBlue.setStyle(Paint.Style.FILL);
        mCircleBlue.setColor(Color.BLUE);

        mAcrRoundBlack = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAcrRoundBlack.setStyle(Paint.Style.STROKE);
        mAcrRoundBlack.setStrokeWidth(8 * getResources().getDisplayMetrics().density);
        mAcrRoundBlack.setStrokeCap(Paint.Cap.ROUND);
        mAcrRoundBlack.setColor(Color.BLACK);

        mAcrRoundBlue = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAcrRoundBlue.setStyle(Paint.Style.STROKE);
        mAcrRoundBlue.setStrokeWidth(8 * getResources().getDisplayMetrics().density);
        mAcrRoundBlue.setStrokeCap(Paint.Cap.ROUND);
        mAcrRoundBlue.setColor(Color.BLUE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);

        int size = Math.min(w, h);
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCenterX = w / 2f;
        mCenterY = h / 2f;
        mRadius = Math.min(w, h) / 2f;

    }

    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float eyeRadius = mRadius / 20;
        float width = getWidth() / 2.0f;
        float height = getWidth() / 2.0f;
        float left = (getWidth() - width) / 2.0f;
        float top = (getHeight() - height) / 2.0f;
        mArcBounds.set(left, top, left + width, top + height);
        canvas.drawCircle(mCenterX, mCenterY - width / 2.0f, eyeRadius, mCircleYellow);
        canvas.drawCircle((float) (mCenterX + (width / 4.0f) * Math.sqrt(3)), (mCenterY + width / 4.0f), eyeRadius, mCircleYellow);
        canvas.drawCircle((float) (mCenterX - (width / 4.0f) * Math.sqrt(3)), (mCenterY + width / 4.0f), eyeRadius, mCircleYellow);


        canvas.drawArc(mArcBounds, 40f, 100f, false, mAcrRoundBlack);
        canvas.drawArc(mArcBounds, 160f, 100f, false, mAcrRoundBlack);
        canvas.drawArc(mArcBounds, 280f, 100f, false, mAcrRoundBlack);
        if (0 <= mProgress && mProgress <= 33) {
            canvas.drawArc(mArcBounds, 280f, (mProgress * 3), false, mAcrRoundBlue);

        } else if (34 <= mProgress && mProgress <= 66) {
            canvas.drawArc(mArcBounds, 40f, (mProgress - 33) * 3, false, mAcrRoundBlue);
            canvas.drawArc(mArcBounds, 280f, 100f, false, mAcrRoundBlue);
            canvas.drawCircle((float) (mCenterX + (width / 4.0f) * Math.sqrt(3)), (mCenterY + width / 4.0f), eyeRadius, mCircleBlue);


        } else {
            canvas.drawArc(mArcBounds, 160f, (mProgress - 67) * 3, false, mAcrRoundBlue);
            canvas.drawArc(mArcBounds, 40f, 100f, false, mAcrRoundBlue);
            canvas.drawArc(mArcBounds, 280f, 100f, false, mAcrRoundBlue);
            canvas.drawCircle((float) (mCenterX - (width / 4.0f) * Math.sqrt(3)), (mCenterY + width / 4.0f), eyeRadius, mCircleBlue);
            canvas.drawCircle((float) (mCenterX + (width / 4.0f) * Math.sqrt(3)), (mCenterY + width / 4.0f), eyeRadius, mCircleBlue);

        }
        if (mProgress == 100) {
            canvas.drawCircle(mCenterX, mCenterY - width / 2.0f, eyeRadius, mCircleBlue);

        }


    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
