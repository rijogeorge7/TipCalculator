package net.logicshades.tipcalculator.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

import net.logicshades.tipcalculator.R;

/**
 * Created by rijogeorge on 4/13/16 for custom labeled seekbar.
 */
public class LabeledThumbSeekBar extends SeekBar {
    int viewWidth, barHeight,labelOffset,thumbX;
    float progressPosX;
    private Bitmap labelBackground;
    private Rect barBounds,labelTextRect;
    private Point labelPos;
    private Drawable progressDrawable,thumbDrawable;
    private Paint labelTextPaint,labelBackgroundPaint;
    public LabeledThumbSeekBar(Context context) {
        super(context);
    }

    public LabeledThumbSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        labelBackground = BitmapFactory.decodeResource(getResources(), R.mipmap.progressshape);
        barBounds = new Rect();
        labelTextRect = new Rect();
        labelPos = new Point();
        labelTextPaint = new Paint();
        labelTextPaint.setColor(Color.WHITE);
        labelTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        labelTextPaint.setAntiAlias(true);
        labelTextPaint.setDither(true);
        labelTextPaint.setTextSize(50f);

        labelBackgroundPaint = new Paint();
    }

    public LabeledThumbSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LabeledThumbSeekBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (labelBackground != null)
        {

            viewWidth = getMeasuredWidth();
            barHeight = getMeasuredHeight();// returns only the bar height (without the label);
            setMeasuredDimension(viewWidth, barHeight + labelBackground.getHeight());
        }

    }

    @Override
    protected synchronized void onDraw(Canvas canvas)
    {
        if (labelBackground != null)
        {
            barBounds.left = getPaddingLeft();
            barBounds.top = labelBackground.getHeight() + getPaddingTop();
            barBounds.right = barBounds.left + viewWidth - getPaddingRight() - getPaddingLeft();
            barBounds.bottom = barBounds.top + barHeight - getPaddingBottom() - getPaddingTop();

            progressPosX = barBounds.left + ((float) this.getProgress() / (float) this.getMax()) * barBounds.width();

            labelOffset=(labelBackground.getWidth()/2);
            labelPos.x = (int) progressPosX - labelOffset;
            labelPos.y = getPaddingTop();

            progressDrawable = getProgressDrawable();
            progressDrawable.setBounds(barBounds.left, barBounds.top, barBounds.right, barBounds.bottom);
            progressDrawable.draw(canvas);

            String labelText = (Integer.toString(getProgress()))+"%";

            labelTextPaint.getTextBounds(labelText, 0, labelText.length(), labelTextRect);

            canvas.drawBitmap(labelBackground, labelPos.x, labelPos.y, labelBackgroundPaint);
            canvas.drawText(labelText, labelPos.x + labelBackground.getWidth() / 2 - labelTextRect.width() / 2, labelPos.y + labelBackground.getHeight() / 2 + labelTextRect.height() / 2, labelTextPaint);

            thumbX = (int) progressPosX - getThumbOffset();
            thumbDrawable=getThumb();
            thumbDrawable.setBounds(thumbX, barBounds.top, thumbX + thumbDrawable.getIntrinsicWidth(), barBounds.top + thumbDrawable.getIntrinsicHeight());
            thumbDrawable.draw(canvas);
        } else
        {
            super.onDraw(canvas);
        }
    }


}
