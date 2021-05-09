package com.alie.modulepracticecommon.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.alie.modulepracticecommon.R;

public class BitmapUtils {
    //节省每次创建时产生的开销，但要注意多线程操作synchronized
    private static final Canvas sCanvas = new Canvas();
    private static final float scale=1;

    public static Bitmap createBitmapFromView(View view) {
        if (view instanceof ImageView) {
            Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable != null && drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }
        }
        view.measure(0, 0);
        //调用layout方法布局后，可以得到view的尺寸大小
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap bitmap = createBitmapSafely((int) (view.getWidth() * scale),
                (int) (view.getHeight() * scale), Bitmap.Config.ARGB_8888, 1);
        if (bitmap != null) {
            synchronized (sCanvas) {
                Canvas canvas = sCanvas;
                canvas.setBitmap(bitmap);
                canvas.save();
                // 防止 View 上面有些区域空白导致最终 Bitmap 上有些区域变黑
                canvas.drawColor(ResUtil.getColor(R.color.design_default_color_error));
                canvas.scale(scale, scale);
                view.draw(canvas);
                canvas.restore();
                canvas.setBitmap(null);
            }
        }
        return bitmap;
    }
    //安全的创建bitmap;如果新建 Bitmap 时产生了 OOM，可以主动进行一次 GC - System.gc()，然后再次尝试创建
    private static Bitmap createBitmapSafely(int width, int height, Bitmap.Config config, int retryCount) {
        try {
            return Bitmap.createBitmap(width, height, config);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            if (retryCount > 0) {
                System.gc();
                return createBitmapSafely(width, height, config, retryCount - 1);
            }
            return null;
        }
    }
}
