package com.java;
//import com.asprise.ocr.Ocr;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by iss on 17/12/25.
 */
public class CaptchaTest {

        public static void main(String[] args) throws Exception {
            CaptchaTest.test();
//            HttpClient httpClient = new HttpClient();
//            GetMethod getMethod = new GetMethod("http://dz.bjjtgl.gov.cn/service/checkCode.do");
////      GetMethod getMethod = new GetMethod("https://dynamic.12306.cn/otsweb/passCodeAction.do?rand=sjrand");
//            int statusCode = httpClient.executeMethod(getMethod);
//            if (statusCode != HttpStatus.SC_OK) {
//                System.err.println("Method failed: " + getMethod.getStatusLine());
//                return ;
//            }
//            String picName = "/Users/iss/Desktop";
//            File filepic=new File(picName);
//            if(!filepic.exists())
//                filepic.mkdir();
//            File filepicF=new File(picName+new Date().getTime() + ".jpg");
//            InputStream inputStream = getMethod.getResponseBodyAsStream();
//            OutputStream outStream = new FileOutputStream(filepicF);
//            IOUtils.copy(inputStream, outStream);
//            outStream.close();

//            File file = new File("/Users/iss/Desktop/1.png");
//
//            Ocr.setUp(); // one time setup
//            Ocr ocr = new Ocr(); // create a new OCR engine
//            ocr.startEngine("eng", Ocr.SPEED_FASTEST); // English
//            String s = ocr.recognize(new File[] {file},Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);
//            System.out.println("Result: " + s);
//            System.out.println("图片文字为:" + s.replace(",", "").replace("i", "1").replace(" ", "").replace("'", "").replace("o", "0").replace("O", "0").replace("g", "6").replace("B", "8").replace("s", "5").replace("z", "2"));
//            // ocr more images here ...
//            ocr.stopEngine();
        }

        public static void  test() throws Exception {
            BufferedImage img = ImageIO.read(new File("/Users/iss/Desktop/1.png"));

            int width = img.getWidth();
            int height = img.getHeight();

            for(int i = 1;i < width;i++){
                Color colorFirst = new Color(img.getRGB(i, 1));
                int numFirstGet = colorFirst.getRed()+colorFirst.getGreen()+colorFirst.getBlue();
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        Color color = new Color(img.getRGB(x, y));
                        System.out.println("red:"+color.getRed()+" | green:"+color.getGreen()+" | blue:"+color.getBlue());
                        int num = color.getRed()+color.getGreen()+color.getBlue();
                        if(num >= numFirstGet){
                            img.setRGB(x, y, Color.WHITE.getRGB());
                        }
                    }
                }
            }

            File f2 = new File("/Users/iss/Desktop/2.png");
            ImageIO.write(img, "png", f2);

        }
    }
