package lac.com.draganddraw;

import android.graphics.PointF;

/**
 * Created by Aicun on 9/20/2017.
 */

public class Box {

    private PointF mOrigin;
    private PointF mCurrent;

    public Box(PointF origin) {
        mOrigin = origin;
        mCurrent = origin;
    }

    public PointF getmCurrent() {
        return mCurrent;
    }

    public void setmCurrent(PointF mCurrent) {
        this.mCurrent = mCurrent;
    }

    public PointF getmOrigin() {
        return mOrigin;
    }
}
