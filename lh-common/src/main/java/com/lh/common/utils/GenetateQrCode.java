package com.lh.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成二维码
 *
 * @author nijuns
 */
public class GenetateQrCode {

        public MultipartFile code(String content,String path) throws WriterException, IOException{
        //core.3.3.jar Zxing
        //创建一个‘画者’
        MultiFormatWriter mfw = new MultiFormatWriter();
        //内容，类型,宽度，高度，其他信息map
       // String content = "写的二维码";
        BarcodeFormat type = BarcodeFormat.QR_CODE;
        int width = 600;
        int height = 600;
        Map map = new HashMap();
        map.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        map.put(EncodeHintType.MARGIN,2);
        //画一个虚拟的二维码对象
        BitMatrix matrix = mfw.encode(content,type,width,height,map);
        //流虚拟二维码对象的信息写入文件
        int black = Color.black.getRGB();
        int white = Color.white.getRGB();

        MultipartFile multipartFile=null;


            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, matrix.get(x, y) ? black : white);
                }
            }
            File file = new File(path);
          //  ImageIO.write(image, "jpg", file);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
           // ImageIO.write(image, "jpg", out);
            //ByteArrayOutputStream 转化为 byte[]
            byte[] imageByte = out.toByteArray();
            //将 byte[] 转为 MultipartFile
             multipartFile = new ConvertToMultipartFile(imageByte, "二维码", "pic", "jpg", imageByte.length);


            return multipartFile;
    }

    public static void main(String[] args) throws WriterException, IOException {

        GenetateQrCode code =new GenetateQrCode();

        code.code("http://baidu.com","D:\\qr_code.jpg");



    }

}
