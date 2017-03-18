/*
 * Classe de manipulacao de imagem
 * Redimenciona a imagem do produto
 */
package fw;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.Base64;
import javax.imageio.ImageIO;

/**
 *
 * @author daniel
 */
public class redimencionarImage {

    public redimencionarImage() {
    }

    public Blob redimensionaImg(String data) throws Exception {
        try {
           
            Blob blob = null;
            
            //retira as virgulas da imageBAse64
            String partSeparator = ",";
            if (data.contains(partSeparator)) {
              String encodedImg = data.split(partSeparator)[1];
              byte[] img = Base64.getDecoder().decode(encodedImg);
              
                //Cria uma nova imagem do tamanho 200X200 px
                BufferedImage imagem = ImageIO.read(new ByteArrayInputStream(img));
                BufferedImage new_img = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = new_img.createGraphics();
                g.drawImage(imagem, 0, 0, 200, 200, null);
                g.dispose();
                
                //Sobrescreve a imagem em cima da nova
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write( new_img, "png", baos );
                blob = new javax.sql.rowset.serial.SerialBlob(baos.toByteArray());
                baos.flush();
                baos.close();
            }
           
           return blob;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
