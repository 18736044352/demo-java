package com.java.img;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by iss on 17/6/1.
 */
public class PdfToJpg {


    public static void main(String[] args)  {
//        pdfToJpg("/Users/iss/Desktop/1.pdf","/Users/iss/Desktop/1.jpg",1);
        toJpg();
    }


    private static void toJpg(){
        File file = new File("/Users/iss/Desktop/1.pdf");
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for(int i=0;i<pageCount;i++){
                BufferedImage image = renderer.renderImageWithDPI(i, 1200);
//          BufferedImage image = renderer.renderImage(i, 2.5f);
                ImageIO.write(image, "PNG", new File("/Users/iss/Desktop/4.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param source  源PDF文件路径
     * @param target  保存PDF文件路径
     * @param pageNum  提取PDF中第pageNum页
     * @throws Exception
     */
    private static void pdfExtraction(String source,String target,int pageNum) throws Exception{
        //1：创建PDF读取对象
        PdfReader pr = new PdfReader(source);
        System.out.println("this document "+pr.getNumberOfPages()+" page");

        //2：将第page页转为提取，创建document对象
        Document doc = new Document(pr.getPageSize(pageNum));

        //3：通过PdfCopy转其单独存储
        PdfCopy copy = new PdfCopy(doc, new FileOutputStream(new File(target)));
        doc.open();
        doc.newPage();

        //4：获取第1页，装载到document中。
        PdfImportedPage page = copy.getImportedPage(pr,pageNum);
        copy.addPage(page);

        //5：释放资源
        copy.close();
        doc.close();
        pr.close();
    }



    /**
     *
     * @param source 源文件
     * @param target 目标文件
     * @param x	读取源文件中的第几页
     */
    private static void pdfToJpg(String source,String target,int x) throws Exception {
        //创建从中读取和向其中写入（可选）的随机访问文件流，R表示对其只是访问模式
        RandomAccessFile rea = new RandomAccessFile(new File(source), "r");

        //将流读取到内存中，然后还映射一个PDF对象
        FileChannel channel = rea.getChannel();
        ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY,0, channel.size());
        PDFFile pdfFile = new PDFFile(buf);
        PDFPage page = pdfFile.getPage(x);

        // get the width and height for the doc at the default zoom
        java.awt.Rectangle rect = new java.awt.Rectangle(0, 0, (int) page.getBBox()
                .getWidth(), (int) page.getBBox().getHeight());

        // generate the image
        java.awt.Image img = page.getImage(rect.width, rect.height, // width &
                rect, // clip rect
                null, // null for the ImageObserver
                true, // fill background with white
                true // block until drawing is done
        );

        BufferedImage tag = new BufferedImage(rect.width, rect.height,
                BufferedImage.TYPE_INT_RGB);

        tag.getGraphics().drawImage(img, 0, 0, rect.width, rect.height,
                null);
        FileOutputStream out = new FileOutputStream(target); // 输出到文件流
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(tag); // JPEG编码
        out.close();
    }
}
