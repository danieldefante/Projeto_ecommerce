/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fw;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author daniel
 */
public class redimencionarImage {

    public redimencionarImage() {
    }
    

 public static BufferedImage base64StringToImg(final String base64String) {
    try {
        return ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(base64String)));
    } catch (final IOException ioe) {
        throw new UncheckedIOException(ioe);
    }
}
    
    public Blob redimensionaImg(String data) throws Exception {
        
        
        try {
           
            Blob blob = null;
            
            String partSeparator = ",";
            if (data.contains(partSeparator)) {
              String encodedImg = data.split(partSeparator)[1];
              byte[] img = Base64.getDecoder().decode(encodedImg);

    
              
              BufferedImage imagem = ImageIO.read(new ByteArrayInputStream(img));
              BufferedImage new_img = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
              Graphics2D g = new_img.createGraphics();
              g.drawImage(imagem, 0, 0, 50, 50, null);
              g.dispose();
              
//              ImageIO.write(new_img, "png", new File("/home/daniel/Pictures/asdfasdf.png"));
              
              
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            
            ImageIO.write( new_img, "png", baos );
            ImageIO.write( new_img, "png", new File("/home/daniel/Pictures/nhas.png"));
            blob = new javax.sql.rowset.serial.SerialBlob(baos.toByteArray());
            baos.flush();
            
            baos.close();
            }
           
           return blob;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public Blob redimensionaImg3(String data) throws Exception {
        
        
        try {
           
            Blob blob = null;
            
            String partSeparator = ",";
            if (data.contains(partSeparator)) {
              String encodedImg = data.split(partSeparator)[1];
              byte[] decodedImg = Base64.getDecoder().decode(encodedImg.getBytes(StandardCharsets.UTF_8));
//              Path destinationFile = Paths.get("/home/daniel/Pictures/", "testando.png");
//              Files.write(destinationFile, decodedImg);
              
              
              
              
              String na = Base64.getEncoder().encodeToString(decodedImg);
              
              BufferedImage imagem = base64StringToImg(na);
              BufferedImage new_img = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
              Graphics2D g = new_img.createGraphics();
              g.drawImage(imagem, 0, 0, 50, 50, null);
              g.dispose();
              
//              ImageIO.write(new_img, "png", new File("/home/daniel/Pictures/asdfasdf.png"));
              
              
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            
            ImageIO.write( new_img, "png", baos );
            ImageIO.write( new_img, "png", new File("/home/daniel/Pictures/nhas.png"));
            blob = new javax.sql.rowset.serial.SerialBlob(baos.toByteArray());
            baos.flush();
            
            baos.close();
             
             }
             
//            BufferedImage imagem = base64StringToImg(data);
//            BufferedImage new_img = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
//            Graphics2D g = new_img.createGraphics();
//            g.drawImage(imagem, 0, 0, 50, 50, null);
//            g.dispose();
//            ImageIO.write(new_img, "png", new File("/home/daniel/Pictures/testando.png"));
           return blob;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public ImageIcon redimensionaImg2(int new_w, int new_h) throws Exception {
        try {
            File image = new File("/home/daniel/Pictures/teste.png");
            
            BufferedImage imagem = ImageIO.read(image);
            BufferedImage new_img = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = new_img.createGraphics();
            g.drawImage(imagem, 0, 0, new_w, new_h, null);
            g.dispose();
            ImageIO.write(new_img, "png", new File("/home/daniel/Pictures/testando.png"));
            return new ImageIcon(new_img);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    
}














//
//package fw;
//
//import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.sql.Blob;
//import javax.imageio.ImageIO;
//    
///**
// *
// * @author daniel
// */
//public class redimencionarImage {
//
//
//    public Blob redimensionarImg(String imagemBase64) throws Exception {
//        try {
//            
//            byte[] decodedByte = imagemBase64.getBytes();
//            
//            BufferedImage imagem = ImageIO.read(new ByteArrayInputStream(decodedByte));
//            
//            
//            BufferedImage new_img = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
//            Graphics2D g = new_img.createGraphics();
//            g.drawImage(imagem, 0, 0, 50, 50, null);
//            g.dispose();
//            
//            
//            String s = new String(decodedByte);
//            System.out.println(s);
//            
////            ImageIO.write( imagem, "png", new File("/home/daniel/Pictures/nha.png"));
//
//            Blob blob = new javax.sql.rowset.serial.SerialBlob(decodedByte);
//
//        
//            return blob;
//            
//        } catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//    
//    public Blob redimensionarImg2(String imagemBase64) throws Exception {
//        try {
//            
//            byte[] decodedByte = imagemBase64.getBytes();
//            
//            BufferedImage imagem = ImageIO.read(new ByteArrayInputStream(decodedByte));
//            
//            BufferedImage new_img = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
//            Graphics2D g = new_img.createGraphics();
//            g.drawImage(imagem, 0, 0, 50, 50, null);
//            g.dispose();
//            
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ImageIO.write( new_img, "png", baos );
//            ImageIO.write( new_img, "png", new File("/home/daniel/Pictures/nhas.png"));
//            baos.flush();
//            Blob blob = new javax.sql.rowset.serial.SerialBlob(baos.toByteArray());
//            baos.close();
//            
//            
//            
//            return blob;
//            
//        } catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//     
//    
//}
