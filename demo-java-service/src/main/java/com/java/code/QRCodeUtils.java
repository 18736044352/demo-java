package com.java.code;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

/**
 * 二维码的生成工具类
 * @author: LiKangning
 * @createTime: 16/12/20 下午5:00
 */
public class QRCodeUtils {

    /**
     * 生成二维码的工具类
     *
     * @param data      二维码中携带的数据
     * @param width     二维码的宽度
     * @param height    二维码的高度
     * @return
     */
    private static BufferedImage createQRCode(String data, int width, int height) {
        ByteArrayOutputStream bos = null;
        MultiFormatWriter formatWriter = new MultiFormatWriter();
        Hashtable<EncodeHintType, Object> param = new Hashtable<EncodeHintType, Object>();
        param.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
        param.put(EncodeHintType.CHARACTER_SET, "utf-8");
        param.put(EncodeHintType.MARGIN, 0);
        try{
            BitMatrix bitMatrix = formatWriter.encode(data, BarcodeFormat.QR_CODE, width, height, param);
            // 1.1去白边
            int[] rec = bitMatrix.getEnclosingRectangle();
            int resWidth = rec[2] + 1;
            int resHeight = rec[3] + 1;
            BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
            resMatrix.clear();
            for (int i = 0; i < resWidth; i++){
                for (int j = 0; j < resHeight; j++){
                    if (bitMatrix.get(i + rec[0], j + rec[1])){
                        resMatrix.set(i, j);
                    }
                }
            }
            int width1 = resMatrix.getWidth();
            int height1 = resMatrix.getHeight();
            BufferedImage qrcode = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width1; x++){
                for (int y = 0; y < height1; y++){
                    qrcode.setRGB(x, y, resMatrix.get(x, y) == true ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
                }
            }
            return qrcode;
        } catch (Exception e){
            throw new RuntimeException(e);
        } finally{
            if (bos != null){
                try{
                    bos.close();
                } catch (IOException e){
                }
            }
        }

    }


    /**
     * @param data  二维码携带的数据
     * @param size  二维码的宽、高
     * @return
     */
    public static BufferedImage createQRCode(String data, int size) {
        return createQRCode(data, size, size);
    }

    // 测试
    public static void main(String[] args) throws Exception {
        // 生成二维码
        String url = "http://www.sina.com.cn123";
        createQRCode(url, 480);
    }
}
