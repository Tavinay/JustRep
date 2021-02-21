package filemanager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StartPicture extends JPanel {
    
        /**
    *
    */
    private static final long serialVersionUID = -4735659636475422138L;
    Timer timer;
        Timer timer2;
        
        Thread minusAlpha;
        
        int alpha = 255;
        
        BufferedImage image;
        
        public StartPicture(String imagePath) {
            
            timer = new Timer(0, ae -> {
                try {
                    
                    image = ImageIO.read(new File(imagePath));
                    
                    repaint();
                    revalidate();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            
            timer.setRepeats(true);
            timer.setDelay(1);
            timer.start();
            
            minusAlpha = new MinusAlpha(imagePath);

        }
        
        public void painting(String imagePath) {
            try {
                
                image = ImageIO.read(new File(imagePath));
                
                revalidate();
                repaint();
                
            } catch (IOException ex) {
                Logger.getLogger(BGPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, null);
        }
        
        private class MinusAlpha extends Thread {
            
            String imagePath;
            
            public MinusAlpha(String imagePath) {
                
                super("Thread for alpha");
                
                this.imagePath = imagePath;
                
            }
            
            public void run() {
                
                
                
                while(true) {
                    
                    try {
                        
                        image = ImageIO.read(new File(imagePath));
                        
                    } catch (IOException ex) {
                        Logger.getLogger(StartPicture.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    for (int y = 0; y < image.getHeight(); y++) {
                        for (int x = 0; x < image.getWidth(); x++) {
                            
                            if(alpha >= 0) {
                                
                                int pixel = image.getRGB(x,y);
                                
                                Color color = new Color(pixel);
                                
                                int red = color.getRed();
                                int green = color.getGreen();
                                int blue = color.getBlue();
                                
                                image.setRGB(x, y, new Color(red, green, blue, alpha).getRGB());
                                
                            }
                           
                        }
                    }
                    
                    Date dateNow = new Date();
                    SimpleDateFormat formatForDateNow = new SimpleDateFormat("mm:ss:SS a zzz");
                    
                    System.out.println(alpha + " " + formatForDateNow.format(dateNow));
                    
                    if(alpha >= 44) {
                        
                        alpha -= 44;
                        
                    } else {
                        
                        alpha = 0;
                        
                    }
                    
                    repaint();
                    revalidate();
                
                    try {
                        Thread.sleep(4);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(StartPicture.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                }
                
            }
            
        }
        
    }

