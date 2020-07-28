import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
public class Gray{
    public static void main(String args[]) throws IOException{
    BufferedImage img=null;
    File f= null;
    try{
      f=new File("C:/Users/KASHISH/Desktop/pic");
      File[] files=f.listFiles();
      for (File file:files){
      //read-image
      ImageInputStream is = ImageIO.createImageInputStream(file);
      img= ImageIO.read(is);
      System.out.println("Reading Complete.");
      Gray g=new Gray();
      g.ImageGscale(img);
      //write-image
      ImageOutputStream os = ImageIO.createImageOutputStream(file);
      ImageIO.write(img,"jpeg",os); 
      os.close();
      System.out.println("Writing Complete."); 
      }
    }
    catch(IOException e){
        System.out.println("Error"+e);
    }
}
    public BufferedImage ImageGscale(BufferedImage img){
    //get image width and height
      int width= img.getWidth();
      int height= img.getHeight();

      for(int y=0; y<height; y++){
        for(int x=0; x<width; x++){
          int p=img.getRGB(x,y);
          int a = (p>>24)&0xff;
          int r = (p>>16)&0xff;
          int g = (p>>8)&0xff;
          int b = p&0xff;

        //calculate average
          int avg = (r+g+b)/3;

        //replace RGB value with avg
           p = (a<<24) | (avg<<16) | (avg<<8) | avg;
           img.setRGB(x, y, p);
        }
      }
      return img;
    }
  }