package com.java;

import com.java.code.QRCodeUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * Created by iss on 17/6/28.
 */
public class QRCodeTest {

    public void test(){
        BufferedImage bufferedImage = QRCodeUtils.createQRCode("www.baidu.com", 480);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String content = null;
        try {
            ImageIO.write(bufferedImage, "jpg", out);
            byte[] bytes = out.toByteArray();
//            String qrCode = Base64.encode(bytes);
//            content = "data:image/png;base64," + qrCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
