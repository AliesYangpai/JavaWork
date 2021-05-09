package com.alie.modulepracticecommon.util;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

/**
 * // todo fix error red for follow-up tasks
 * 卡片宽度根据屏幕大小动态调整
 */

public class CardSizeHelper {
    static final private String TAG = CardSizeHelper.class.getSimpleName();

//    /**
//     * 卡片类型
//     */
//    @IntDef({ CardType.DRIVE_CROSS})
//    @Retention(RetentionPolicy.SOURCE)
//    public @interface CardType {
//        //路口放大图
//        int DRIVE_CROSS = 1009;
//    }
//
//    private static class RadioItem {
//        final float landscapeRadio;
//        final float portraitRadio;
//        final float landscapeWideRadio;
//
//        RadioItem(float landscapeRadio, float portraitRadio, float landscapeWideRadio){
//            this.landscapeRadio = landscapeRadio;
//            this.portraitRadio = portraitRadio;
//            this.landscapeWideRadio = landscapeWideRadio;
//        }
//
//        float getRadio(@ScreenMode int screenMode){
//            switch (screenMode) {
//                case ScreenMode.LANDSCAPE:
//                    return landscapeRadio;
//                case ScreenMode.LANDSCAPE_WIDE:
//                    return landscapeWideRadio;
//                case ScreenMode.PORTRAIT:
//                    return portraitRadio;
//                default:
//                    break;
//            }
//
//            return 0;
//        }
//    }
//
//    /**
//     * 各种卡片比例值定义
//     */
//    static class CardWidthRadio {
//        /**
//         * 路口放大图
//         */
//        final static private float DRIVE_CROSS_RADIO_LANDSCAPE = 0.45f;
//        final static private float DRIVE_CROSS_RADIO_PORTRAIT = 0.45f;
//        final static private float DRIVE_CROSS_RADIO_LANDSCAPEWIDE = 0.375f;
//
//        final static RadioItem DRIVE_CROSS = new RadioItem(
//            DRIVE_CROSS_RADIO_LANDSCAPE, DRIVE_CROSS_RADIO_PORTRAIT, DRIVE_CROSS_RADIO_LANDSCAPEWIDE);
//    }
//
//    static private int calcCardWidth(RadioItem cardRadio, DisplayInfo info){
//        float radio = cardRadio.getRadio(info.screenMode);
//        float width = info.appWidth * radio;
//
//        /**
//         * 宽屏状态的卡片宽度计算(W:屏幕宽，H:屏幕高)
//         * W/H>3，卡片宽度=3H*比例值
//         * W/H<=3，卡片宽度=W*比例值
//         */
//        if (info.screenMode == ScreenMode.LANDSCAPE_WIDE){
//            if ((float)info.appWidth/(float)info.appHeight > 3f){
//                width = 3f * info.appHeight * radio;
//            }
//        }
//
//        /**
//         * 横屏状态的卡片宽度计算(W:屏幕宽，H:屏幕高)
//         * W/H>2, 卡片宽度 = 2H*比例值
//         * W/H<=2, 卡片宽度 = W*比例值
//         */
//        if (info.screenMode == ScreenMode.LANDSCAPE){
//            if ((float)info.appWidth/(float)info.appHeight > 2f){
//                width = 2f * info.appHeight * radio;
//            }
//        }
//
//        return (int)width;
//    }
//
//    /**
//     * 获取卡片宽度
//     */
//    public static int getCardWidth(@CardType int type, DisplayInfo info){
//        RadioItem cardRadio;
//        switch (type) {
//            case CardType.DRIVE_CROSS:
//                cardRadio = CardWidthRadio.DRIVE_CROSS;
//                break;
//            default:
//                Logger.d(TAG, "CardType[{?}] is not found", type);
//                return -1;
//        }
//
//        int width = calcCardWidth(cardRadio, info);
//        Logger.d(TAG, "screenMode={?}, cardType={?}, radio={?}, cardWidth={?}",info.screenMode, type, cardRadio, width);
//        return width;
//    }
}
