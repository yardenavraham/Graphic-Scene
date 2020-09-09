import java.awt.Color;
import renderer.ImageWriter;
import org.junit.Test;

//This class tests the functions of image writer
public class ImageWriterTest {

    @Test
    public void writeImageTest() {//an image with diffrent colors by using diffrent functions of write pixel.
        ImageWriter image = new ImageWriter("test1", 500, 500, 10, 10);
        for (int i = 0; i <= 99; i++) {
            for (int j = 0; j <= 499; j++) {
                image.writePixel(i, j, Color.yellow);
            }
        }
        int[] rgbArray={255,0,255};

        for (int i = 100; i <= 199; i++) {
            for (int j = 0; j <= 499; j++) {
                image.writePixel(i, j, rgbArray);
            }
        }
        for (int i = 200; i <= 299; i++) {
            for (int j = 0; j <= 499; j++) {
                image.writePixel(i, j, 255, 0, 0);
            }
        }
        for (int i = 300; i <= 399; i++) {
            for (int j = 0; j <= 499; j++) {
                image.writePixel(i, j, Color.blue);
            }
        }
        for (int i = 400; i <= 499; i++) {
            for (int j = 0; j <= 499; j++) {
                image.writePixel(i, j, Color.green);
            }
        }
        image.writeToimage();
        
        image = new ImageWriter("test2", 500, 500, 10, 10);//an image with one color
        for (int i = 0; i <= 499; i++) {
            for (int j = 0; j <= 499; j++) {
                image.writePixel(i, j, Color.magenta);
            }
        }
        image.writeToimage();
        
        image = new ImageWriter("test3", 500, 500, 10, 10);//an image with one color
        for (int i = 0; i <= 499; i++) {
            for (int j = 0; j <= 499; j++) {
                image.writePixel(i, j, 40,60,200);
            }
        }
        image.writeToimage();

    }

}
