import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        BufferedImage image = null;
        File dir = new File("src/inputImages");

        for (File file: dir.listFiles()){
            try {
                image = ImageIO.read(file);
            } catch (IOException e) {
                System.out.println(e);
            }

            int width = image.getWidth();
            int height = image.getHeight();

            for (int x = 0; x < (width); x++){
                for (int y = 0; y < height; y++){

                    int pixel = image.getRGB(x,y);
                    int alpha = (pixel>>24)&0xff;
                    int red = (pixel>>16)&0xff;
                    int green = (pixel>>8)&0xff;
                    int blue = pixel&0xff;

                    int average = (red + green + blue )/3;

                    if (x<width/2){
                        if (average < 127){
                            average = 0;
                        }
                        else{
                            average = 255;
                        }
                    }


                    pixel = (alpha<<24) | (average<<16) | (average<<8) | average;
                    image.setRGB(x,y,pixel);
                }
            }

            try {
                file = new File("src/outputImages/"+file.getName()+"(scaled).jpg");
                ImageIO.write(image,"jpg",file);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}