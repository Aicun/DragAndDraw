package lac.com.draganddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aicun on 9/20/2017.
 */

public class BoxDrawingView extends View {

    private static final String TAG = "BoxDrawingView";

    private Box mCurrentBox;
    private List<Box> mBoxList = new ArrayList<>();
    private Paint mBoxPaint;
    private Paint mBackgroundPaint;

    private int mActivePointerId;

    public BoxDrawingView(Context context) {
        super(context);
    }

    public BoxDrawingView(Context context, AttributeSet attrs) {
        super(context,attrs);

        //red
        mBoxPaint = new Paint();
        mBoxPaint.setColor(0x22ff0000);

        //background white
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        mActivePointerId = event.getPointerId(0);
        // Use the pointer ID to find the index of the active pointer
        // and fetch its position
        int pointerIndex = event.findPointerIndex(mActivePointerId);

        PointF current = new PointF(event.getX(),event.getY());
        String action = "";

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                action = "ACTION_DOWN";
                mCurrentBox = new Box(current);
                mBoxList.add(mCurrentBox);
                break;
            case MotionEvent.ACTION_UP:
                action = "ACTION_UP";
                mCurrentBox = null;
                break;
            case MotionEvent.ACTION_MOVE:
                action = "ACTION_MOVE";
                if (mCurrentBox != null) {
                    mCurrentBox.setmCurrent(current);
                    //方法会强制BoxDrawingView重新绘制自己
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                action = "ACTION_CANCEL";
                mCurrentBox = null;
                break;
        }
        Log.i(TAG, action + " at x=" + current.x + " ,y=" + current.y);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //fill background
        canvas.drawPaint(mBackgroundPaint);

        for(Box box : mBoxList) {
            float left = Math.min(box.getmOrigin().x,box.getmCurrent().x);
            float right = Math.max(box.getmOrigin().x, box.getmCurrent().x);
            float top = Math.min(box.getmOrigin().y, box.getmCurrent().y);
            float bottom = Math.max(box.getmOrigin().y, box.getmCurrent().y);
            canvas.drawRect(left,top,right,bottom,mBoxPaint);
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }
}
