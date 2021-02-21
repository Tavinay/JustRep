package filemanager;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JLabel;

public class BGPanel extends JLabel{
    
    /**
     *
     */
    private static final long serialVersionUID = 7092871071120631639L;

    public void paint(Graphics g) {
        
        super.paint(g);
        Image img;
        img = Toolkit.getDefaultToolkit().getImage("src/Resource/BackGround.jpg"); // путь к картинке 
        
        g.drawImage(img, 0, 0, null);
        repaint();
        validate();
        
    }
//    Image img = new ImageIcon("src/Resource/BackGround.jpg").getImage();    
//    
//    public void paint(Graphics g) {
//        g = (Graphics2D) g;
//        g.drawImage(img, 0, 0, null);
//        //System.exit(0);
//    }

}
    
//    Timer mainTimer = new Timer(33, this);
//    
//    Image img = new ImageIcon("src/Resource/Jellyfish.jpg").getImage();
//    
//    public BGPanel(){
//        mainTimer.start();
//        setFocusable(true);
//    }
//    
//    
//    public void paint(Graphics g) {
//        g = (Graphics2D) g;
//        g.drawImage(img, 0, 0, null);
//        }
//    
//    public void actionPerformed(ActionEvent e){
//        repaint();
//        revalidate();
//    }
//}
