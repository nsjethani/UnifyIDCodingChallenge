import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageGenerator {

    //Use following method to generate Random list of given size
    static List generateRandomList(int size, int min, int max, int cols, int base, String format, String rnd){

        List randomList = null;

        try{
            String urlString = "https://www.random.org/integers/?" + "num="+size+ "&min="+min+ "&max="+max+ "&col="+cols+ "&base="+base+ "&format="+format+ "&rnd="+rnd;

            System.out.println("URL string :"+urlString);
            URL url = new URL(urlString);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            System.out.println("Respone code "+con.getResponseCode());

            //Generate ArrayList from random numbers if response code from random.org is 200
            if(con.getResponseCode()==200){
                randomList = new ArrayList();
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String num=br.readLine();
                while(num!=null)
                {
                    randomList.add(Integer.parseInt(num));
                    num=br.readLine();
                }
            }
            else {
                System.out.println(" Following Error occurred while fetching random numbers" );
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String s=br.readLine();
                System.out.println(s);
            }
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return randomList;
    }

    //Use following method to generate random image from given List
    static void createRandomImage(List random){
        try{

            System.out.println(random);
            System.out.println(random.size());

            //image dimension
            int width = 128;
            int height = 128;

            //int total = 128*128*3;
            /*
            *  Here, we can generate 128*128*3 random numbers for each pixel (Red, Green, Blue)
            *  using generateRGBList method mentioned but I got error message
            *  "Error: You have used your quota of random bits for today.  See the quota page for details."
            *  so I am using only 128*3 bits list and repeating it.
            */

            //create buffered image object img
            BufferedImage randomImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            //create random image pixel by pixel
            int count=0;
            for(int ht=0;ht<height;ht++)
            {
                for(int wd=0;wd<width;wd++)
                {
                    int red = Integer.parseInt(random.get(count).toString());
                    int green = Integer.parseInt(random.get(count+1).toString());
                    int blue = Integer.parseInt(random.get(count+2).toString());
                    int pixel = (red << 16) | (green << 8) | blue;

                    randomImg.setRGB(ht, wd, pixel);

                    if(count<381) //As index values count,count+1 and count+2 are used
                        count++;
                    else
                        count=0;
                }
            }

            /*
             *  We can use following code to generate 128*128*3 random numbers using generateRGBList method and use it for all 3 colors of pixels
             */

            //code:

            /*
            int count=0;
            for(int ht=0;ht<height;ht++)
            {
                for(int wd=0;wd<width;wd++)
                {
                    int red = Integer.parseInt(random.get(count++).toString());
                    int green = Integer.parseInt(random.get(count++).toString());
                    int blue = Integer.parseInt(random.get(count++).toString());
                    int pixel = (red << 16) | (green << 8) | blue;

                    randomImg.setRGB(ht, wd, pixel);

                }
            }
            */

            //write image in Image Directory
            File file = new File("Picture/random.bmp");
            ImageIO.write(randomImg, "bmp", file);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    static List generateRGBList()
    {
        List RGBList = new ArrayList();
        int total = 128*128*3; //49152

        while(total>0) {
            if (total > 10000) {
                System.out.println("Total " + total);
                RGBList.addAll(generateRandomList(10000, 0, 255, 1, 10, "plain", "new"));
            } else
                RGBList.addAll(generateRandomList(total, 0, 255, 1, 10, "plain", "new"));
            total -= 10000;
        }
        return RGBList;

    }

    public static void main(String[] args) {
        try{
            List randomNumbers = generateRandomList(384, 0, 255, 1, 10, "plain", "new"); // 128*3
            // generate 128*128*3 random numbers
            // List randomNumbers = generateRGBList(); // 128*128*3

            if(randomNumbers!=null)
                createRandomImage(randomNumbers);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
