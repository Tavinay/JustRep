package filemanager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JPanel;

public class TabbedPanel extends JPanel {
    
    /**
     *
     */
    private static final long serialVersionUID = -7253801756484307789L;
    
    int tabWidth = 16;
    int tabHeight = 4;
    
    private ArrayList<TabButton> tabbedButtons = new ArrayList<>();
    
    UI ui;
    
    public TabbedPanel(UI ui, PathMaster pathMaster) {
        
        this.ui = ui;
        
        setLayout(null);
        
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        
        if((ui.getFrameWidth() - 400 - 200 - 40) >= 120) {
        
            tabWidth = 140;
        
        } else {
            
            tabWidth = (ui.getFrameWidth() - 400 - 200 - 40);
            
        }
        
        tabHeight = 28;
        
        ui.setMainFilesPanel(new MainFilesPanel(ui));
        
        ui.getMainFilesPanel().getFileSheetItem().add(0);
        pathMaster.getDirsCash().add(new ArrayList<ArrayList<String>>());
        pathMaster.getAntiDirsCash().add(new ArrayList<ArrayList<String>>());
        pathMaster.getFilesList().add(new ArrayList<ArrayList<String>>());
        pathMaster.getFilesList().get(0).add(new ArrayList<String>());
        pathMaster.getFilesList().get(0).add(new ArrayList<String>());
        
        pathMaster.getDirsCash().get(0).add(new ArrayList<String>());//  Info
        pathMaster.getDirsCash().get(0).add(new ArrayList<String>());//  Type
        
        pathMaster.getAntiDirsCash().get(0).add(new ArrayList<String>());//  Info
        pathMaster.getAntiDirsCash().get(0).add(new ArrayList<String>());//  Type
        
        TabButton button = new TabButton("Компьютер", tabWidth, tabHeight);
        
        button.setName(Integer.toString(0));
        
        button.setFont(new Font("Calibri", Font.BOLD, 14));
        
        button.setFocusable(false);
        
        button.setBounds(20, 12, tabWidth, tabHeight);
        
        button.setBackground(new Color(230, 230, 230));
                
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) {
                    
                    pathMaster.setLastTab(Integer.parseInt(button.getName()));
                    ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                    almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                    ui.getMainFilesPanel().iniclizButton(almostEmptyList);
                     for(int i = 0; i < tabbedButtons.size(); i++) {

                        tabbedButtons.get(i).setBackground(Color.WHITE);

                    }
                    button.setBackground(new Color(230, 230, 230));
                    System.out.println("tb: " + pathMaster.getLastTab());
                    
                }
                
            }
            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
            
        });
        
        tabbedButtons.add(button);
        add(tabbedButtons.get(0));
        
    }
    
    public void setTB() {
        
        for(int i = 0; i < tabbedButtons.size(); i++) {
                        
                        int tab = i;
                        
                        ActionListener al = (new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                System.out.println("Tab111: " + tab);
                                if(tabbedButtons.size() > 1) {
                                    
                                    remove(tabbedButtons.get(tab));
                                    
                                    tabbedButtons.remove(tab);
                                    
                                    ui.getMainFilesPanel().getFileSheetItem().remove(tab);
                                    ui.getPathMaster().getDirsCash().remove(tab);
                                    ui.getPathMaster().getAntiDirsCash().remove(tab);
                                    ui.getPathMaster().getFilesList().remove(tab);
                                    
                                    for(int i = 0; i < tabbedButtons.size(); i++) {

                                        tabbedButtons.get(i).setName(Integer.toString(i));

                                    }
                                    
                                    if((20 + 20 + (tabbedButtons.size()) * tabWidth - (tabbedButtons.size()) * (tabWidth / 10)) < getWidth()) {
                                        
                                        if((ui.getFrameWidth() - 400 - 200 - 40) >= 120) {
                                            
                                            tabWidth = 140;
                                            
                                        } else {
                                            
                                            tabWidth = (ui.getFrameWidth() - 400 - 200 - 40);
                                            
                                        }
                                        
                                        for(int i = 0; i < tabbedButtons.size(); i++) {
                                            
                                            tabbedButtons.get(i).setBounds(20 + i * tabWidth - i * (tabWidth / 10), 12, tabWidth, tabHeight);
                                            
                                        }
                                        
                                    } else {
                                        
                                        tabWidth = (getWidth() - (20 + 20)) / (tabbedButtons.size());
                                        
                                        for(int i = 0; i < tabbedButtons.size(); i++) {
                                            
                                            tabbedButtons.get(i).setBounds(20 + i * tabWidth - i * (tabWidth / 10), 12, tabWidth, tabHeight);
                                            
                                        }
                                        
                                    }
                                    
                                    ui.getPathMaster().setLastTab(Integer.parseInt(tabbedButtons.get(tabbedButtons.size() - 1).getName()));
                                    ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                                    almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                                    ui.getMainFilesPanel().iniclizButton(almostEmptyList);
                                    for(int i = 0; i < tabbedButtons.size(); i++) {

                                        tabbedButtons.get(i).setBackground(Color.WHITE);

                                    }
                                    tabbedButtons.get(tabbedButtons.size() - 1).setBackground(new Color(230, 230, 230));
                                    System.out.println("tb!!!: " + ui.getPathMaster().getLastTab());
                                    
                                    setTB();
                                    
                                    revalidate();
                                    repaint();
                                    
                                }
                                
                            }
                        });
                        
                        tabbedButtons.get(i).setCloseButtonAL(al);
                        
                    }
        
    }

    public int getTabWidth() {
        
        return(tabWidth);
        
    }
    
    public void setTabWidth(int tabWidth) {
        
        this.tabWidth = tabWidth;
        
    }

    public int getTabHeight() {
        
        return(tabHeight);
        
    }
    
    public void setTabHeight(int tabHeight) {
        
        this.tabHeight = tabHeight;
        
    }

    public ArrayList<TabButton> getTabbedButtons() {
        
        return(tabbedButtons);
        
    }
    
    public void setTabbedButtons(ArrayList<TabButton> tabbedButtons) {
        
        this.tabbedButtons = tabbedButtons;
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        
        Graphics2D g2d = (Graphics2D)g;
            
        super.paintComponent(g2d);
        
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
            
        Toolkit.getDefaultToolkit().sync();
        
        g2d.drawRoundRect(8, 5, (int)((double)getSize().width - 16), (int)((double)getSize().height - 10), 25, 25);
        
    }
    
    @Override
    protected void paintBorder(Graphics g) {
        
        Graphics2D g2d = (Graphics2D)g;
            
        super.paintComponent(g2d);
        
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
            
        Toolkit.getDefaultToolkit().sync();
        
        g2d.setColor(new Color(180, 180, 180));
        
        //g2d.drawRect(0, 0, getSize().width - 1, getSize().height - 1);
        
        g2d.drawRoundRect(8, 5, (int)((double)getSize().width - 16), (int)((double)getSize().height - 10), 25, 25);
        
    }
    
    Shape shape;
    @Override
    public boolean contains(int x, int y) {
        if (shape == null ||
                !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(8, 5, (int)((double)getSize().width - 16), (int)((double)getSize().height - 10), 25, 25);
        }
        return shape.contains(x, y);
    }
    
}
