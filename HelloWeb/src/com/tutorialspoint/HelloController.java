package com.tutorialspoint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/hello")
public class HelloController{
	
   private static String imageLocation = "C:/Program Files/Apache Software Foundation/Tomcat 8.0/webapps/";
   private static String imageName = "imageTest.png";
 
   @RequestMapping(method = RequestMethod.GET)
   public String printHello(ModelMap model) throws IOException {
	   
	   drawImage();
	  
	   String imageUrl = "http://lurkmore.so/images/3/3f/Old_Noise.jpg";	   
	   
      model.addAttribute("message", getTime());
      
   //   model.addAttribute("image", "src=\"" + imageLocation + imageName + "\"");
      
      model.addAttribute("image", "src=\"" + imageUrl + "\"");
                    
      return "hello";

   }
   
   private static String getTime() {
       Calendar calendar = Calendar.getInstance();
       SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
       String time = sdf.format(calendar.getTime());
       return time;  
   }
   
   public static void drawImage() throws IOException {
       int circleRadius = 40;
       int dawnTime = 5;
       int duskTime = 21;

       int currentHour = Integer.parseInt(getTime().substring(0,2));

       BufferedImage bufferedImage = new BufferedImage(200, 400, BufferedImage.TYPE_INT_RGB);
       Graphics g = bufferedImage.createGraphics();

       g.setColor(Color.cyan);
       g.fillRect(0,0,bufferedImage.getWidth(),bufferedImage.getHeight());

       if (currentHour>dawnTime && currentHour<duskTime){
           int sunInSky = (bufferedImage.getHeight()/(duskTime-dawnTime)) * (duskTime - currentHour);                      //

           g.setColor(Color.yellow);
           g.fillOval(((bufferedImage.getWidth()/2)-circleRadius/2),sunInSky-circleRadius,circleRadius,circleRadius);
       }

       ImageIO.write(bufferedImage,"PNG", new File(imageLocation + imageName));
   }

}
