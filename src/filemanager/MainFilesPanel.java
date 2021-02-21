package filemanager;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import static java.util.Objects.isNull;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainFilesPanel extends JPanel {
    
    /**
     *
     */
    private static final long serialVersionUID = -589656521778988103L;
    
    int ii = 1;
    int ii2 = 1;
    int tlf = 0;
    int countSimbol;
    
    boolean textMove = false;
    boolean prokrutkaFiles = false;
    
    private ArrayList<Integer> fileSheetItem = new ArrayList<>();
    
    private JButton privateButton;
    
    private JButton[] topFiles = new JButton[11];
    
    private JLabel[] topLabelFiles = new JLabel[11];
    private JLabel[] topLabelFilesSecond = new JLabel[11];
    
    private TextMove textMoveThread;
    private ProkrutkaFilesThread prokrutkaFilesThread;
    
    private UI ui;
    
    public MainFilesPanel(UI ui) {
        
        this.ui = ui;
        
        setLayout(null);
        
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1 && ui.getBasicPanel().getComponentCount() == 5) {
                    
                    int startingFileNumber = fileSheetItem.get(ui.getPathMaster().getLastTab());
                    
                    if(getWidth() / 2 >= e.getX()) {
                        
                        if(fileSheetItem.get(ui.getPathMaster().getLastTab()) > 0) {
                            System.out.println(">0");
                            fileSheetItem.set(ui.getPathMaster().getLastTab(), fileSheetItem.get(ui.getPathMaster().getLastTab()) - 1);
                            
                            startingFileNumber = fileSheetItem.get(ui.getPathMaster().getLastTab());
                            
                            for(int i = 0; i < topFiles.length && i < ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size(); i++) {
                                
                                topFiles[i].remove(topFiles[i]);
                                topFiles[i] = new JButton(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).get(startingFileNumber));
                                startingFileNumber++;
                                
                            }
                            
                            ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                            iniclizButton(almostEmptyList);
                            
                        }
                        
                        System.out.println("Left");
                        
                    } else {
                        
                        if(fileSheetItem.get(ui.getPathMaster().getLastTab()) < ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size() - topFiles.length) {
                            
                            fileSheetItem.set(ui.getPathMaster().getLastTab(), fileSheetItem.get(ui.getPathMaster().getLastTab()) + 1);
                            
                            startingFileNumber = fileSheetItem.get(ui.getPathMaster().getLastTab()); 
                            
                            for(int i = 0; i < (topFiles.length - 1); i++){
                                
                                topFiles[i].remove(topFiles[i]);
                                topFiles[i] = new JButton(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).get(startingFileNumber));
                                startingFileNumber++;
                                
                            }
                            
                            ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                            iniclizButton(almostEmptyList);
                            
                        }
                        
                        System.out.println("Right");
                        
                    }
                } else {
                    
                    System.out.println("cc: " + ui.getBasicPanel().getComponentCount());
                    
                    System.out.println("1qaz");

                    if(ui.getSearchTextField().getText().equals("") || ui.getSearchTextField().getText() == null) {

                        ui.getSearchTextField().setText("Поиск");
                        ui.getSearchTextField().setForeground(new Color(37, 83, 185));

                    }

                    if(privateButton != null) {
                        privateButton.remove(privateButton);
                    }

                    for(int i = 0; i < (topFiles.length - 1); i++){

                        if(topFiles[i] != null) {

                            topFiles[i].setBorderPainted(false);

                        }

                    }

                    int cpWidth = ui.getContentPane().getWidth();
                    int cpHeight = ui.getContentPane().getHeight();

                    ui.getBasicPanel().removeAll();

                    ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
                    ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
                    ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                    ui.getSort().setBounds(10, 50, 316, 45);
                    setBounds(0, 45, cpWidth, (cpHeight - 45));
                    ui.getButtonsPanel().setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                    ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));

                    ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
                    ui.getBasicPanel().add(ui.getTabbedPanel());
                    ui.getBasicPanel().add(ui.getSearchTextField());
                    ui.getBasicPanel().add(ui.getSort());
                    ui.getBasicPanel().add(MainFilesPanel.this);

                    ui.getBasicPanel().moveToFront(ui.getSort());

                    revalidate();
                    repaint();
                    
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1 && ui.getBasicPanel().getComponentCount() == 5){
                    if(prokrutkaFilesThread != null) {
                        if(prokrutkaFilesThread.isInterrupted()) {
                            
                            prokrutkaFiles = true;
                            prokrutkaFilesThread = null;
                            prokrutkaFilesThread = new ProkrutkaFilesThread(MainFilesPanel.this);
                            prokrutkaFilesThread.start();
                            
                        } else {
                            
                            prokrutkaFiles = true;
                            prokrutkaFilesThread.interrupt();
                            prokrutkaFilesThread = null;
                            prokrutkaFilesThread = new ProkrutkaFilesThread(MainFilesPanel.this);
                            prokrutkaFilesThread.start();
                            
                        }
                    } else {
                        
                        prokrutkaFiles = true;
                        prokrutkaFilesThread = new ProkrutkaFilesThread(MainFilesPanel.this);
                        prokrutkaFilesThread.start();
                        
                        
                    }
                } else {
                    
                    System.out.println("1qaz");

                    if(ui.getSearchTextField().getText().equals("") || ui.getSearchTextField().getText() == null) {

                        ui.getSearchTextField().setText("Поиск");
                        ui.getSearchTextField().setForeground(new Color(37, 83, 185));

                    }

                    if(privateButton != null) {
                        privateButton.remove(privateButton);
                    }

                    for(int i = 0; i < (topFiles.length - 1); i++){

                        if(topFiles[i] != null) {

                            topFiles[i].setBorderPainted(false);

                        }

                    }

                    int cpWidth = ui.getContentPane().getWidth();
                    int cpHeight = ui.getContentPane().getHeight();

                    ui.getBasicPanel().removeAll();

                    ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
                    ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
                    ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                    ui.getSort().setBounds(10, 50, 316, 45);
                    setBounds(0, 45, cpWidth, (cpHeight - 45));
                    ui.getButtonsPanel().setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                    ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));

                    ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
                    ui.getBasicPanel().add(ui.getTabbedPanel());
                    ui.getBasicPanel().add(ui.getSearchTextField());
                    ui.getBasicPanel().add(ui.getSort());
                    ui.getBasicPanel().add(MainFilesPanel.this);

                    ui.getBasicPanel().moveToFront(ui.getSort());

                    revalidate();
                    repaint();
                    
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                prokrutkaFiles = false;
                if(prokrutkaFilesThread != null) {
                    
                    prokrutkaFilesThread.interrupt();
                    
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
        
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                System.out.println("111111111111111111111111: " + ui.getBasicPanel().getComponentCount());
                int ipop2 = fileSheetItem.get(ui.getPathMaster().getLastTab());
                if(e.getWheelRotation() == 1 && ui.getBasicPanel().getComponentCount() == 5) {
                  int ry = ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size();
                        
                        if(ry > (fileSheetItem.get(ui.getPathMaster().getLastTab()) + topFiles.length)){
                            
                            fileSheetItem.set(ui.getPathMaster().getLastTab(), fileSheetItem.get(ui.getPathMaster().getLastTab()) + 1);
                            System.out.println("+: " + fileSheetItem.get(ui.getPathMaster().getLastTab()));
                            ipop2 = fileSheetItem.get(ui.getPathMaster().getLastTab());    
                            for(int i = /*directoryOnOff*/0; i < (topFiles.length - 1) && i < ry; i++){
                                topFiles[i].remove(topFiles[i]);
                            }
                            for(int i = /*directoryOnOff*/0; i < (topFiles.length - 1) && i < ry; i++){
                                topFiles[i] = new JButton(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).get(ipop2));
                                ipop2++;
                            }

                            ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                            iniclizButton(almostEmptyList);
                            
                        }
                  System.out.println("UP");
                } else if(ui.getBasicPanel().getComponentCount() == 5) {
                  int ry = ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size() + 1;
                    System.out.println("-Check: " + fileSheetItem.get(ui.getPathMaster().getLastTab()));
                  if(fileSheetItem.get(ui.getPathMaster().getLastTab()) > 0) {
                            fileSheetItem.set(ui.getPathMaster().getLastTab(), fileSheetItem.get(ui.getPathMaster().getLastTab()) - 1);
                        System.out.println("-: " + fileSheetItem.get(ui.getPathMaster().getLastTab()));
                        ipop2 = fileSheetItem.get(ui.getPathMaster().getLastTab());
                          for(int i = /*directoryOnOff*/0; i < (topFiles.length - 1) && i < ry; i++){
                            topFiles[i].remove(topFiles[i]);
                          }
                        for(int i = /*directoryOnOff*/0; i < (topFiles.length - 1) && i < ry; i++){
                            topFiles[i] = new JButton(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).get(ipop2));
                            ipop2++;
                        }
                        
                        ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                            iniclizButton(almostEmptyList);
                            
                  }
                  System.out.println("DOWN");
                }
            }
        
        });
        
//        addMouseListener(new MouseListener(){
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                
//                System.out.println("1qaz");
//                
//                if(ui.getSearchTextField().getText().equals("") || ui.getSearchTextField().getText() == null) {
//                    
//                    ui.getSearchTextField().setText("Поиск");
//                    ui.getSearchTextField().setForeground(new Color(37, 83, 185));
//                    
//                }
//                
//                if(privateButton != null) {
//                    privateButton.remove(privateButton);
//                }
//                
//                for(int i = 0; i < (topFiles.length - 1); i++){
//                    
//                    if(topFiles[i] != null) {
//                        
//                        topFiles[i].setBorderPainted(false);
//                        
//                    }
//                    
//                }
//                
//                int cpWidth = ui.getContentPane().getWidth();
//                    int cpHeight = ui.getContentPane().getHeight();
//                    
//                    ui.getBasicPanel().removeAll();
//                    
//                    ui.getButtonsPanel().setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
//                    
//                    ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
//                    ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
//                    ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
//                    ui.getSort().setBounds(10, 50, 316, 45);
//                    setBounds(0, 45, cpWidth, (cpHeight - 45));
//                    ui.getButtonsPanel().setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
//                    ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
//                    
//                    ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
//                    ui.getBasicPanel().add(ui.getTabbedPanel());
//                    ui.getBasicPanel().add(ui.getSearchTextField());
//                    ui.getBasicPanel().add(ui.getSort());
//                    ui.getBasicPanel().add(MainFilesPanel.this);
//                    
//                    ui.getBasicPanel().moveToFront(ui.getSort());
//                    
//                    revalidate();
//                    repaint();
//                    
//            }
//            
//            @Override
//            public void mousePressed(MouseEvent e) {
//                
//                System.out.println("1qaz");
//                
//                if(ui.getSearchTextField().getText().equals("") || ui.getSearchTextField().getText() == null) {
//                    
//                    ui.getSearchTextField().setText("Поиск");
//                    ui.getSearchTextField().setForeground(new Color(37, 83, 185));
//                    
//                }
//                
//                if(privateButton != null) {
//                    privateButton.remove(privateButton);
//                }
//                
//                for(int i = 0; i < (topFiles.length - 1); i++){
//                    
//                    if(topFiles[i] != null) {
//                        
//                        topFiles[i].setBorderPainted(false);
//                        
//                    }
//                    
//                }
//                
//                int cpWidth = ui.getContentPane().getWidth();
//                int cpHeight = ui.getContentPane().getHeight();
//                
//                ui.getBasicPanel().removeAll();
//                
//                ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
//                ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
//                ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
//                ui.getSort().setBounds(10, 50, 316, 45);
//                setBounds(0, 45, cpWidth, (cpHeight - 45));
//                ui.getButtonsPanel().setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
//                ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
//                
//                ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
//                ui.getBasicPanel().add(ui.getTabbedPanel());
//                ui.getBasicPanel().add(ui.getSearchTextField());
//                ui.getBasicPanel().add(ui.getSort());
//                ui.getBasicPanel().add(MainFilesPanel.this);
//                
//                ui.getBasicPanel().moveToFront(ui.getSort());
//                
//                revalidate();
//                repaint();
//                
//            }
//            
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                
//                System.out.println("1qaz");
//                
//                if(ui.getSearchTextField().getText().equals("") || ui.getSearchTextField().getText() == null) {
//                    
//                    ui.getSearchTextField().setText("Поиск");
//                    ui.getSearchTextField().setForeground(new Color(37, 83, 185));
//                    
//                }
//                
//                if(privateButton != null) {
//                    privateButton.remove(privateButton);
//                }
//                
//                for(int i = 0; i < (topFiles.length - 1); i++){
//                    
//                    if(topFiles[i] != null) {
//                        
//                        topFiles[i].setBorderPainted(false);
//                        
//                    }
//                    
//                }
//                
//                int cpWidth = ui.getContentPane().getWidth();
//                int cpHeight = ui.getContentPane().getHeight();
//                
//                ui.getBasicPanel().removeAll();
//                
//                ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
//                ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
//                ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
//                ui.getSort().setBounds(10, 50, 316, 45);
//                setBounds(0, 45, cpWidth, (cpHeight - 45));
//                ui.getButtonsPanel().setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
//                ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
//                
//                ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
//                ui.getBasicPanel().add(ui.getTabbedPanel());
//                ui.getBasicPanel().add(ui.getSearchTextField());
//                ui.getBasicPanel().add(ui.getSort());
//                ui.getBasicPanel().add(MainFilesPanel.this);
//                
//                ui.getBasicPanel().moveToFront(ui.getSort());
//                
//                revalidate();
//                repaint();
//                
//            }
//            
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                
//            }
//            
//            @Override
//            public void mouseExited(MouseEvent e) {
//                
//            }
//            
//        });
        
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        //g.drawRect(getSize().width / 2 - getSize().height / 2, 0, getSize().height - 1, getSize().height - 1);// Возможно оставлю
        //g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
        g.drawLine(0, 0, getWidth(), 0);
//        int gray = 130;
//        for(int i = 0; i < 10; i++) {
//            g.setColor(new Color(gray + i * 10, gray + i * 10, gray + i * 10));
//            g.drawLine(0, i, getWidth(), i);
//            
//        }
    }
    
    public void iniclizButton(ArrayList<ArrayList<String>> files) {
        
        for(int i = 0; i < topFiles.length; i++){
            
            if(topFiles[i] != null) {
              
                topFiles[i].remove(topFiles[i]);
            
            }
          
        }
        
        for(int i = 0; i < topLabelFiles.length; i++){
            
            if(topLabelFiles[i] != null) {
              
                topLabelFiles[i].remove(topLabelFiles[i]);
            
            }
          
        }
        
        for(int i = 0; i < topLabelFilesSecond.length; i++){
            
            if(topLabelFilesSecond[i] != null) {
              
                topLabelFilesSecond[i].remove(topLabelFilesSecond[i]);
            
            }
          
        }
        
        if(ui.getSearchTextField().getText().equals("") || ui.getSearchTextField().getText() == null) {
                    
                    ui.getSearchTextField().setText("Поиск");
                    ui.getSearchTextField().setForeground(new Color(37, 83, 185));
                    
                }
                
                if(privateButton != null) {
                    privateButton.remove(privateButton);
                }
                
                for(int i = 0; i < (topFiles.length - 1); i++){
                    
                    if(topFiles[i] != null) {
                        
                        topFiles[i].setBorderPainted(false);
                        
                    }
                    
                }
                
                int cpWidth2 = ui.getContentPane().getWidth();
                int cpHeight2 = ui.getContentPane().getHeight();
                
                ui.getBasicPanel().removeAll();
                
                ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
                ui.getTabbedPanel().setBounds(336, 0, (cpWidth2 - 336 - 168), 45);
                ui.getSearchTextField().setBounds((336 + (cpWidth2 - 336 - 168)), 0, 168, 45);
                ui.getSort().setBounds(10, 50, 316, 45);
                setBounds(0, 45, cpWidth2, (cpHeight2 - 45));
                ui.getButtonsPanel().setBounds((int)(cpWidth2 - cpWidth2 / 5 * 1), 0, (int)(cpWidth2 / 5 * 1 + (cpWidth2 - cpWidth2 / 5 * 1 - (int)(cpWidth2 - cpWidth2 / 5 * 1))), (int)(cpHeight2));
                ui.getMorePanel().setBounds(0, 0, (int)(cpWidth2 / 5 * 1 + (cpWidth2 - cpWidth2 / 5 * 5 - (int)(cpWidth2 - cpWidth2 / 5 * 5))), (int)(cpHeight2));
                
                ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
        //ui.getBasicPanel().add(path);
        ui.getBasicPanel().add(ui.getTabbedPanel());
        ui.getBasicPanel().add(ui.getSearchTextField());
        ui.getBasicPanel().add(ui.getSort());
        ui.getBasicPanel().add(MainFilesPanel.this);
        
        ui.getBasicPanel().moveToFront(ui.getSort());
                
                revalidate();
                repaint();
        
//        if(directoryOnOff == 1){
//            
//            topFiles[0] = directoryButton;
//            
//        }
        
        removeAll();
        
        ArrayList<String> fileLinesList = new ArrayList<>();
        
        String path = new File("src\\data\\MarkedFiles.txt").getAbsolutePath();
        
        StringBuffer p = new StringBuffer();
        File file = new File(path);
	BufferedReader bReader = null;
	InputStreamReader iReader = null;
	FileInputStream fStream = null;
 
	try {
            
	    fStream = new FileInputStream(file);
	    iReader = new InputStreamReader(fStream, "UTF-8");
	    bReader = new BufferedReader(iReader);
            
	    String fileLine = bReader.readLine();
        
            int countData = 0;
	    while (fileLine != null) {
                
                fileLinesList.add(fileLine);
                
	        fileLine = bReader.readLine();
                
	    }
            
            fStream.close();
            iReader.close();
            bReader.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        ArrayList <String> realCashC = new ArrayList<>();
        String fullPathC = "";
        
        if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() > 0) {
                        
                        if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartCash") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("Cash")){
                            for(int j = 0; j < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); j++){
                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("StartCash") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("Cash")){
                                    realCashC.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(j));
                                }
                            }
                            fullPathC = ui.getPathMaster().pathBuilder(realCashC);
                        } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartPath") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("PathCash")){
                            for(int j = 0; j < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); j++){
                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("StartPath") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("PathCash")){
                                    realCashC.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(j));
                                }
                            }
                            fullPathC = ui.getPathMaster().pathBuilder(realCashC);
                        } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartSearch") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("SearchCash")){
                            for(int j = 0; j < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); j++){
                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("StartSearch") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("SearchCash")){
                                    realCashC.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(j));
                                }
                            }

                            fullPathC = ui.getPathMaster().pathBuilder(realCashC);

                        }
                        
        }
                
                    
        File selectedFileC;
        selectedFileC = new File(fullPathC);
        
        if(selectedFileC.list() != null) {
            
            ui.getPath().setText(selectedFileC.toString());
            
            ui.getPathMaster().setUi7(0);
            ui.getPathMaster().setDiscii2(0);

            File discs2[] = File.listRoots();
            
                        for (int discii = 0; discii < discs2.length; discii++) {  
                            
                          long normalDisk = discs2[discii].getTotalSpace();
                          if(normalDisk > 0 || discs2[discii].exists()){
                            ui.getPathMaster().setDiscii2(ui.getPathMaster().getDiscii2() + 1);
                          }
                        }
                        
                        ui.getPathMaster().setDiscs(new File[ui.getPathMaster().getDiscii2()]);
                        
                        for (int discii = 0; discii < discs2.length; discii++) {  
                            
                          long normalDisk = discs2[discii].getTotalSpace();
                          if(normalDisk > 0 || discs2[discii].exists()){
                            ui.getPathMaster().getDiscs()[ui.getPathMaster().getUi7()] = discs2[discii];
                            ui.getPathMaster().setUi7(ui.getPathMaster().getUi7() + 1);
                          }
                        }
                        
            String fileName = selectedFileC.toString();
            
            List<String> discHave = new ArrayList<>(Arrays.asList(ui.getPathMaster().getDiscs())).stream().filter(disk -> disk.toString().equals(fileName)).map(d -> d.toString()).collect(Collectors.toList());
            
            if(!discHave.isEmpty()) {
            
                ui.getTabbedPanel().getTabbedButtons().get(ui.getPathMaster().getLastTab()).setText(selectedFileC.toString());
            
            } else {
                
                ui.getTabbedPanel().getTabbedButtons().get(ui.getPathMaster().getLastTab()).setText(selectedFileC.getName());
                
            }
            
            ArrayList<String> idkModel = new ArrayList<>();
                         
            for(String str : selectedFileC.list()){
                            File checkFile = new File(selectedFileC + str);

                            if(!ui.getNavigationButtonsPanel().getHiddenDirectory()){
                            if(!checkFile.isHidden()){
                                if(checkFile.isDirectory()){
                                    idkModel.add(str);
                                } else {
                                    idkModel.add(str);
                                }
                            }
                            } else if(ui.getNavigationButtonsPanel().getHiddenDirectory()){
                                if(checkFile.isDirectory()){
                                    idkModel.add(str);
                                } else {
                                    idkModel.add(str);
                                }
                            }
                        }
                        
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0));
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1));
                        
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).addAll(idkModel);
                        
                        for(int i = 0; i < ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size(); i++) {
                            
                            ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).add("ForPath");
                            
                        }
            
        } else {
            
            ui.getTabbedPanel().getTabbedButtons().get(ui.getPathMaster().getLastTab()).setText("Компьютер");
            
            ui.getPath().setText("Компьютер");
                        
            ui.getPathMaster().setUi7(0);
            ui.getPathMaster().setDiscii2(0);

            File discs2[] = File.listRoots();

                        for (int discii = 0; discii < discs2.length; discii++) {  

                          long normalDisk = discs2[discii].getTotalSpace();
                          if(normalDisk > 0 || discs2[discii].exists()){
                            ui.getPathMaster().setDiscii2(ui.getPathMaster().getDiscii2() + 1);
                          }
                        }

                        ui.getPathMaster().setDiscs(new File[ui.getPathMaster().getDiscii2()]);

                        for (int discii = 0; discii < discs2.length; discii++) {  

                          long normalDisk = discs2[discii].getTotalSpace();
                          if(normalDisk > 0 || discs2[discii].exists()){
                            ui.getPathMaster().getDiscs()[ui.getPathMaster().getUi7()] = discs2[discii];
                            ui.getPathMaster().setUi7(ui.getPathMaster().getUi7() + 1);
                          }
                        }
                        
                        List<String> discsList = new ArrayList<>(Arrays.asList(ui.getPathMaster().getDiscs())).stream().map(d -> d.toString()).collect(Collectors.toList());
                        
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0));
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1));
                        
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).addAll(new ArrayList<>(discsList));

                        for(int i = 0; i < ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size(); i++) {

                            ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).add("ForPath");

                        }
            
        }
        
        ArrayList<String> marked = new ArrayList<>();
        
        ArrayList<String> filesL = new ArrayList<>();
        
        if(files.get(0).size() == 1 && files.get(0).get(0).equals("\\/:*?\"<>|")) {
            
            for(int i = 0; i < ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size(); i++) {
            
                for(int j = 0; j < fileLinesList.size(); j++) {

                    selectedFileC = new File(fullPathC + ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).get(i));

                    if(fileLinesList.get(j).equals(selectedFileC.toString())) {

                        marked.add(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).get(i));

                    }

                }
            
            }

            ArrayList<String> fl = new ArrayList<>(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0));
            
            System.out.println("ui.getSortStr(): " + ui.getSortStr());
            if(ui.getSortStr().equals("По умолчанию")) {

                        ArrayList <String> realCashC2 = new ArrayList<>();
                        String fullPathC2 = "";

                        if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() > 0) {

                                        if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartCash") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("Cash")){
                                            for(int j = 0; j < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); j++){
                                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("StartCash") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("Cash")){
                                                    realCashC.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(j));
                                                }
                                            }
                                            fullPathC = ui.getPathMaster().pathBuilder(realCashC);
                                        } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartPath") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("PathCash")){
                                            for(int j = 0; j < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); j++){
                                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("StartPath") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("PathCash")){
                                                    realCashC.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(j));
                                                }
                                            }
                                            fullPathC = ui.getPathMaster().pathBuilder(realCashC);
                                        } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartSearch") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("SearchCash")){
                                            for(int j = 0; j < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); j++){
                                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("StartSearch") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("SearchCash")){
                                                    realCashC.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(j));
                                                }
                                            }

                                            fullPathC2 = ui.getPathMaster().pathBuilder(realCashC);

                                        }


                        File selectedFileC2;
                        selectedFileC2 = new File(fullPathC2);

                            String backDir = selectedFileC2.toString();
                            String[] objects = new File(backDir).list();
                            if(objects != null) {
                                
                                ArrayList<String> backRootModel = new ArrayList<>();
                                
                                if(files.get(0).size() == 1 && files.get(0).get(0).equals("\\/:*?\"<>|")) {

                                    for(int i = 0; i < objects.length; i++) {

                                        for(int j = 0; j < fileLinesList.size(); j++) {

                                            selectedFileC = new File(fullPathC + objects[i]);

                                            if(fileLinesList.get(j).equals(selectedFileC.toString())) {

                                                marked.add(objects[i]);

                                            }

                                        }

                                    }

                                }

                                marked.removeAll(marked);

                                marked.addAll(backRootModel);

                            }

                        }

                        } else if(ui.getSortStr().equals("Имя по возрастанию")) {

                System.out.println("11: " + marked);
                Collections.sort(marked, Comparator.comparing(String::toString));
                System.out.println("22: " + marked);

            } else if(ui.getSortStr().equals("Имя по убыванию")) {

                            System.out.println("133: " + marked);
                            Collections.sort(marked, Comparator.comparing(String::toString));
                            System.out.println("233: " + marked);
                            List<String> tempFL = new ArrayList<>();
                            tempFL.addAll(marked);
                            marked.removeAll(marked);
                            for(int i = tempFL.size() - 1; i > 0; i--) {

                                marked.add(tempFL.get(i));

                            }
                        
            }
            
            System.out.println("ui.getSortStr(): " + ui.getSortStr());
            if(ui.getSortStr().equals("По умолчанию")) {

                        ArrayList <String> realCashC2 = new ArrayList<>();
                        String fullPathC2 = "";

                        if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() > 0) {

                                        if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartCash") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("Cash")){
                                            for(int j = 0; j < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); j++){
                                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("StartCash") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("Cash")){
                                                    realCashC.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(j));
                                                }
                                            }
                                            fullPathC = ui.getPathMaster().pathBuilder(realCashC);
                                        } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartPath") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("PathCash")){
                                            for(int j = 0; j < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); j++){
                                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("StartPath") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("PathCash")){
                                                    realCashC.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(j));
                                                }
                                            }
                                            fullPathC = ui.getPathMaster().pathBuilder(realCashC);
                                        } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartSearch") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("SearchCash")){
                                            for(int j = 0; j < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); j++){
                                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("StartSearch") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("SearchCash")){
                                                    realCashC.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(j));
                                                }
                                            }

                                            fullPathC2 = ui.getPathMaster().pathBuilder(realCashC);

                                        }


                        File selectedFileC2;
                        selectedFileC2 = new File(fullPathC2);

                            String backDir = selectedFileC2.toString();
                            String[] objects = new File(backDir).list();
                            if(objects != null) {
                                
                                ArrayList<String> backRootModel = new ArrayList<>();
                                
                                if(files.get(0).size() == 1 && files.get(0).get(0).equals("\\/:*?\"<>|")) {

                                    for(int i = 0; i < objects.length; i++) {

                                        for(int j = 0; j < fileLinesList.size(); j++) {

                                            selectedFileC = new File(fullPathC + objects[i]);

                                            if(fileLinesList.get(j).equals(selectedFileC.toString())) {

                                                fl.add(objects[i]);

                                            }

                                        }

                                    }

                                }

                                fl.removeAll(fl);

                                fl.addAll(backRootModel);

                            }

                        }

                        } else if(ui.getSortStr().equals("Имя по возрастанию")) {

                System.out.println("11: " + fl);
                Collections.sort(fl, Comparator.comparing(String::toString));
                System.out.println("22: " + fl);

            } else if(ui.getSortStr().equals("Имя по убыванию")) {

                            System.out.println("133: " + fl);
                            Collections.sort(fl, Comparator.comparing(String::toString));
                            System.out.println("233: " + fl);
                            List<String> tempFL = new ArrayList<>();
                            tempFL.addAll(fl);
                            fl.removeAll(fl);
                            for(int i = tempFL.size() - 1; i > 0; i--) {

                                fl.add(tempFL.get(i));

                            }
                        
            }

            ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0));

            for(String str : marked) {

                for(int i = 0; i < fl.size(); i++) {

                    if(fl.get(i).equals(str)) {

                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).add(str);

                    }

                }

            }

            for(String str : fl) {

                if(marked.size() > 0) {

                    boolean onePath = false;

                    for(int i = 0; i < marked.size(); i++) {

                        if(marked.get(i).equals(str)) {

                            onePath = true;

                        }

                    }

                    if(!onePath) {

                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).add(str);

                    }

                } else {

                    ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).add(str);

                }

            }

            int countD = ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).size();
            int countM = marked.size();

            ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1));

            for(int i = 0; i < countM; i++) {

                ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).add("ForPathSuper");

            }

            for(int i = 0; i < countD; i++) {

                ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).add("ForPath");

            }
            
        } else {
            
            for(int i = 0; i < files.get(0).size(); i++) {
                
                for(int j = 0; j < fileLinesList.size(); j++) {
                    
                    selectedFileC = new File(fullPathC + files.get(0).get(i));
                    
                    if(fileLinesList.get(j).equals(selectedFileC.toString())) {
                        
                        marked.add(files.get(0).get(i));
                        
                    }
                    
                }
                
            }

            ArrayList<String> fl = new ArrayList<>(files.get(0));

            files.get(0).removeAll(files.get(0));

            for(String str : marked) {

                for(int i = 0; i < fl.size(); i++) {

                    if(fl.get(i).equals(str)) {

                        files.get(0).add(str);

                    }

                }

            }

            for(String str : fl) {

                if(marked.size() > 0) {

                    boolean onePath = false;

                    for(int i = 0; i < marked.size(); i++) {

                        if(marked.get(i).equals(str)) {

                            onePath = true;

                        }

                    }

                    if(!onePath) {

                        files.get(0).add(str);

                    }

                } else {

                    files.get(0).add(str);

                }

            }

            int countD = files.get(1).size();
            int countM = marked.size();

            files.get(1).removeAll(files.get(1));

            for(int i = 0; i < countM; i++) {

                files.get(1).add("ForSearchSuper");

            }

            for(int i = 0; i < countD; i++) {

                files.get(1).add("ForSearch");

            }
            
        }
        
        System.out.println("fl123: " + ui.getPathMaster().getFilesList());
        int re = 0;
        
        if(files.get(0).size() == 1 && files.get(0).get(0).equals("\\/:*?\"<>|")) {
            
            re = ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size();
            
        } else {
            
            re = files.get(0).size();
            
        }
        
//        if(directoryOnOff == 1){
//            
//            topFiles[0].setBorderPainted(false);
//            topFiles[0].setFocusable(false);
//            
//        }

        int ipop2 = fileSheetItem.get(ui.getPathMaster().getLastTab());
        ii2 = /*directoryOnOff*/0;
        
        for(int i = 0; i < (topFiles.length - /*directoryOnOff*/0) && i < topLabelFiles.length && i < re; i++) {

            if(files.get(0).size() == 1 && files.get(0).get(0).equals("\\/:*?\"<>|")) {
                    System.out.println("ui.getPathMaster().getFilesList(): " + ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).get(ipop2));
                    topFiles[ii2] = new JButton();
                    topFiles[ii2].setName(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).get(ipop2));
                    
                    topLabelFiles[ii2] = new JLabel(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).get(ipop2));
                    
                    topLabelFiles[ii2].setName(Integer.toString(ii2));
                    
                    addLabelListener(topLabelFiles[ii2]);
                    
                    String selectedObject = ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).get(ipop2);
                    
                    if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() > 0) {
                        
                        ArrayList <String> realCash = new ArrayList<>();
                        String fullPath = "";
                        
                        boolean isPath = false;
                        
                        if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartCash") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("Cash")){
                            for(int j = 0; j < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); j++){
                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("StartCash") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("Cash")){
                                    realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(j));
                                }
                            }
                            fullPath = ui.getPathMaster().pathBuilder(realCash);
                        } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartPath") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("PathCash")){
                            for(int j = 0; j < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); j++){
                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("StartPath") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("PathCash")){
                                    realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(j));
                                }
                                isPath = true;
                            }
                            fullPath = ui.getPathMaster().pathBuilder(realCash);
                        } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartSearch") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("SearchCash")){
                            for(int j = 0; j < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); j++){
                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("StartSearch") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("SearchCash")){
                                    realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(j));
                                }
                            }

                            fullPath = ui.getPathMaster().pathBuilder(realCash);

                        }
                        
                        File selectedFile = new File("");
                
                    if(!isPath) {
                        
                        selectedFile = new File(fullPath + selectedObject);
                        
                    } else {
                        
                        selectedFile = new File(fullPath);
                        
                    }
                    
                    double cpWidth = 1;
                    double cpHeight = 1;

                    if(ui.getContentPane().getWidth() > 0) {

                        cpWidth = ui.getContentPane().getWidth();
                        cpHeight = ui.getContentPane().getHeight();

                    } else {

                        cpWidth = ui.getFrameWidth();
                        cpHeight = ui.getFrameHeight();

                    }

                    double dimension = (int)(cpWidth / 100 * 10 - (cpWidth / 100 * 10 / 32 * 2));
                    
                    if(selectedFile.isFile()) {
                        
                        topFiles[ii2].setIcon(new ImageIcon(new ImageIcon(new File("src\\Resources\\Button resources\\FileButton.png").getAbsolutePath()).getImage().getScaledInstance((int)dimension, (int)dimension, Image.SCALE_SMOOTH)));
                        
                    } else if(selectedFile.isDirectory()) {
                        
                        topFiles[ii2].setIcon(new ImageIcon(new ImageIcon(new File("src\\Resources\\Button resources\\DirectoryButton.png").getAbsolutePath()).getImage().getScaledInstance((int)dimension, (int)dimension, Image.SCALE_SMOOTH)));
                        
                    }
                        
                    } else {
                        
                        double cpWidth = 1;
                        double cpHeight = 1;

                        if(ui.getContentPane().getHeight() > 0) {

                            cpWidth = ui.getContentPane().getWidth();
                            cpHeight = ui.getContentPane().getHeight();

                        } else {

                            cpWidth = ui.getFrameWidth();
                            cpHeight = ui.getFrameHeight();

                        }

                        double dimension = (int)(cpWidth / 100 * 10 - (cpWidth / 100 * 10 / 32 * 2));
                        
                        topFiles[ii2].setIcon(new ImageIcon(new ImageIcon(new File("src\\Resources\\Button resources\\DriveButton.png").getAbsolutePath()).getImage().getScaledInstance((int)dimension, (int)dimension, Image.SCALE_SMOOTH)));
                        
                    }
                
                } else {
                    
                    topFiles[ii2] = new JButton();
                    
                    topFiles[ii2].setName(files.get(0).get(ipop2));
                    
                    topLabelFiles[ii2] = new JLabel(new File(files.get(0).get(ipop2)).getName());
                    
                    topLabelFiles[ii2].setName(Integer.toString(ii2));
                    
                    addLabelListener(topLabelFiles[ii2]);
                    
                    String selectedObject = files.get(0).get(ii2);
                    
                    boolean isDrive = false;
                    
                    for(int j = 0; j < ui.getPathMaster().getDiscs().length; j++) {
                        
                        if(files.get(0).get(ii2).equals(ui.getPathMaster().getDiscs()[j].toString())) {
                            
                            isDrive = true;
                            
                        }
                        
                    }
                    
                    if(files.get(0).size() > ii2 && !isDrive) {
                    
                        File selectedFile;
                        selectedFile = new File(selectedObject);

                        double cpWidth = 1;
                        double cpHeight = 1;

                        if(ui.getContentPane().getWidth() > 0) {

                            cpWidth = ui.getContentPane().getWidth();
                            cpHeight = ui.getContentPane().getHeight();

                        } else {

                            cpWidth = ui.getFrameWidth();
                            cpHeight = ui.getFrameHeight();

                        }

                        double dimension = (int)(cpWidth / 100 * 10 - (cpWidth / 100 * 10 / 32 * 2));

                        if(selectedFile.isFile()) {

                            topFiles[ii2].setIcon(new ImageIcon(new ImageIcon(new File("src\\Resources\\Button resources\\FileButton.png").getAbsolutePath()).getImage().getScaledInstance((int)dimension, (int)dimension, Image.SCALE_SMOOTH)));

                        } else if(selectedFile.isDirectory()) {

                            topFiles[ii2].setIcon(new ImageIcon(new ImageIcon(new File("src\\Resources\\Button resources\\DirectoryButton.png").getAbsolutePath()).getImage().getScaledInstance((int)dimension, (int)dimension, Image.SCALE_SMOOTH)));

                        }
                        
                    } else if(files.get(0).size() > ii2) {
                        
                        double cpWidth = 1;
                        double cpHeight = 1;

                        if(ui.getContentPane().getHeight() > 0) {

                            cpWidth = ui.getContentPane().getWidth();
                            cpHeight = ui.getContentPane().getHeight();

                        } else {

                            cpWidth = ui.getFrameWidth();
                            cpHeight = ui.getFrameHeight();

                        }

                        double dimension = (int)(cpWidth / 100 * 10 - (cpWidth / 100 * 10 / 32 * 2));
                        
                        topFiles[ii2].setIcon(new ImageIcon(new ImageIcon(new File("src\\Resources\\Button resources\\DriveButton.png").getAbsolutePath()).getImage().getScaledInstance((int)dimension, (int)dimension, Image.SCALE_SMOOTH)));
                        
                    }
                    
                }
                
                topFiles[ii2].setBorderPainted(false);
                
                ii2++;
                ii++;
                
                ipop2++;
            
            }
        
        if(files.get(0).size() == 1 && files.get(0).get(0).equals("\\/:*?\"<>|")) {
            System.out.println("nnnnnnnnn");
            addListenerButton(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
            
        } else {
            System.out.println("ttttttttt");
            addListenerButton(files.get(0));
            
        }
        
        int ipop3 = fileSheetItem.get(ui.getPathMaster().getLastTab());
        
        int ipop4 = fileSheetItem.get(ui.getPathMaster().getLastTab());
        
        for(int i = 0;  i < topFiles.length && i < topLabelFiles.length && i < re; i++) {
            
            topLabelFiles[i].setHorizontalAlignment(SwingConstants.CENTER);
            
            topLabelFiles[i].setOpaque(true);
            
            topFiles[i].setBackground(Color.WHITE);
            
            if(files.get(0).size() == 1 && files.get(0).get(0).equals("\\/:*?\"<>|")) {
            
                if(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).get(ipop3).equals("ForPathSuper")) {

                    topLabelFiles[i].setBackground(new Color(38, 130, 255));

                } else {

                    topLabelFiles[i].setBackground(new Color(38, 177, 255));

                }
                
            } else {
                
                if(files.get(1).get(ipop3).equals("ForSearchSuper")) {

                    topLabelFiles[i].setBackground(new Color(38, 130, 255));

                } else {

                    topLabelFiles[i].setBackground(new Color(38, 177, 255));

                }
                
            }
            
            double cpWidth = 1;
            double cpHeight = 1;
            
            if(ui.getContentPane().getHeight() > 0) {
            
                cpWidth = ui.getContentPane().getWidth();
                cpHeight = ui.getContentPane().getHeight();
            
            } else {
                
                cpWidth = ui.getFrameWidth();
                cpHeight = ui.getFrameHeight();
                
            }
            
            double dimension = (int)(cpWidth / 100 * 10 - (cpWidth / 100 * 10 / 32 * 2));
            double promedutok = (int)(cpWidth / 100 * 10 / 32);
            //System.out.println("ipop4:::::::::::::::::::::::::::::::::: " + (ipop4 + topFiles.length) + ". re: " + re);
            if(ipop4 > 0 && (ipop4 + topFiles.length) < re) {
            
                if(i == 0 && ii2 >= topFiles.length && ii2 >= topLabelFiles.length) {
                    System.out.println("ii21: " + ii2);
                    topFiles[i].setBounds((int)(-(dimension / 2)), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));

                    topLabelFiles[i].setBounds((int)(-(dimension / 2)), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));

    //            } else if(i == topFiles.length && ii2 >= topFiles.length  && ii2 >= topLabelFiles.length) {
    //                
    //                topFiles[i].setBounds((int)((cpWidth / 100 * 16 * i + 5) + (cpWidth / 100 * 8)), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
    //                
    //                topLabelFiles[i].setBounds((int)((cpHeight / 100 * 16 * i + 5) + (cpHeight / 100 * 8)), (int)((cpHeight / 100 * 16 + 2) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
    //                
                } else {
                    System.out.println("ii22: " + ii2);
                    topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (i - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                    
                    topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (i - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));

                }
            
            } else {
                System.out.println("ii23: " + ii2);
                if(ii2 % 2 == 0.0 && ii2 < topFiles.length && ii2 < topLabelFiles.length) {
                    System.out.println("ii24: " + ii2);
                    switch (i) {
                        case 0:
                            topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (4.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (4.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            break;
                        case 1:
                            topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (5.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (5.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            break;
                        case 2:
                            topFiles[i - 2].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (3.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 2].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (3.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 1].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (4.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 1].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (4.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (5.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (5.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            break;
                        case 3:
                            topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (6.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (6.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            break;
                        case 4:
                            topFiles[i - 4].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (2.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 4].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (2.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 3].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (3.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 3].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (3.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 2].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (4.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 2].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (4.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 1].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (5.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 1].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (5.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (6.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (6.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            break;
                        case 5:
                            topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (7.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (7.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            break;
                        case 6:
                            topFiles[i - 6].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (1.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 6].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (1.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 5].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (2.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 5].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (2.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 4].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (3.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 4].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (3.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 3].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (4.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 3].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (4.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 2].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (5.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 2].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (5.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 1].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (6.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 1].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (6.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (7.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (7.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            break;
                        case 7:
                            topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (8.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (8.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            break;
                        case 8:
                            topFiles[i - 8].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (0.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 8].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (0.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 7].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (1.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 7].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (1.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 6].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (2.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 6].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (2.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 5].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (3.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 5].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (3.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 4].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (4.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 4].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (4.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 3].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (5.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 3].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (5.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 2].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (6.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 2].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (6.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 1].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (7.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 1].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (7.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (8.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (8.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            break;
                        case 9:
                            topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (9.5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (9.5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            break;
                        case 10:
                            topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (8 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (8 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            break;
                        default:
                            break;
                    }
                
                } else if(ii2 % 2 != 0.0) {
                    System.out.println("ii25: " + ii2);
                    switch (i) {
                        case 0:
                            topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            break;
                        case 1:
                            topFiles[i - 1].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (4 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 1].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (4 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            break;
                        case 2:
                            topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (6 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (6 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            break;
                        case 3:
                            topFiles[i - 3].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (3 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 3].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (3 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 2].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (4 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 2].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (4 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 1].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 1].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (6 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (6 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            break;
                        case 4:
                            topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (7 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (7 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            break;
                        case 5:
                            topFiles[i - 5].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (2 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 5].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (2 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 4].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (3 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 4].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (3 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 3].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (4 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 3].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (4 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 2].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 2].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 1].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (6 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 1].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (6 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (7 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (7 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            break;
                        case 6:
                            topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (8 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (8 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            break;
                        case 7:
                            topFiles[i - 7].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (1 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 7].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (1 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 6].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (2 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 6].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (2 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 5].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (3 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 5].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (3 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 4].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (4 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 4].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (4 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 3].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 3].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 2].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (6 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 2].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (6 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 1].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (7 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 1].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (7 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (8 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (8 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            break;
                        case 8:
                            topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (9 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (9 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            break;
                        case 9:
                            topFiles[i - 9].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (1 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 9].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (1 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 8].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (2 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 8].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (2 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 7].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (3 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 7].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (3 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 6].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (4 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 6].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (4 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 5].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (5 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 5].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (5 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 4].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (6 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 4].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (6 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 3].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (7 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 3].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (7 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 2].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (8 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 2].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (8 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i - 1].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (9 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i - 1].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (9 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            
                            topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (10 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (10 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            break;
                        case 10:
                            topFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (11 - 1))), (int)(cpHeight / 36 * 7), (int)(dimension), (int)(dimension));
                            topLabelFiles[i].setBounds((int)((dimension / 2) + promedutok * 2 + ((cpWidth / 100 * 10) * (11 - 1))), (int)((cpHeight / 100 * 16 + (cpHeight / 100 * 5 / 16)) + (cpHeight / 36 * 7) + 5), (int)(dimension), (int)(cpHeight / 100 * 5));
                            break;
                        default:
                            break;
                    }
                    
                }
                
            }
            
            add(topFiles[i]);
            
            add(topLabelFiles[i]);
            
            ipop3++;
            
        }
        
        ii2 = 1;
        
        revalidate();
        repaint();
        
    }
    
    public void addListenerButton(ArrayList<String> files) {
        
       int numberListeners = 0;
       int navigatorCheck = 0;
       int fileSheetSize = ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size() + /*directoryOnOff*/0;
       for (JButton arrayButton : topFiles) {
            
        if(numberListeners < (topFiles.length) && numberListeners < fileSheetSize){
            arrayButton.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1/* || !files.get(0).equals("\\/:*?\"<>|")*/) {
                    System.out.println("57tfr09g");
                    if(privateButton != null){
                        privateButton.remove(privateButton);
                    }
                    ui.getBasicPanel().remove(ui.getButtonsPanel());
                    ui.getBasicPanel().revalidate();
                    ui.getBasicPanel().repaint();
                    System.out.println("repaint");
                    arrayButton.setBorderPainted(false);
                    ArrayList<String> model = new ArrayList<>();
                    String selectedObject = arrayButton.getName();
                    System.out.println("1");
                    System.out.println("files111: " + files);
                    if(files.size() == 1 && files.get(0).equals("\\/:*?\"<>|")) {
                        ui.getPathMaster().setLastSearch(false);
                        System.out.println("1-1");
                        ArrayList <String> realCash = new ArrayList<>();
                        String fullPath = "";
                        boolean success = false;

                        if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).isEmpty()){
                            
                            ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).add(selectedObject);
                            ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).add("StartCash");
                            fullPath = ui.getPathMaster().pathBuilder(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0));
                            success = true;
                            
                        }
                        System.out.println("hg n n: " + ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size());
                    if(!success && ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() > 0){

                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartCash") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("Cash")){
                            ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).add(selectedObject);
                            ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).add("Cash");
                            for(int i = 0; i < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); i++){
                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("StartCash") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("Cash")){
                                    realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(i));
                                }
                            }
                                
                            fullPath = ui.getPathMaster().pathBuilder(realCash);
                        } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartPath") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("PathCash")){
                            ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).add(selectedObject);
                            ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).add("PathCash");
                            for(int i = 0; i < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); i++){
                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("StartPath") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("PathCash")){
                                    realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(i));
                                }
                            }
                            fullPath = ui.getPathMaster().pathBuilder(realCash);
                        } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartSearch") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("SearchCash")){
                            ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).add(selectedObject);
                            ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).add("SearchCash");
                            for(int i = 0; i < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); i++){
                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("StartSearch") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("SearchCash")){
                                    realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(i));
                                }
                            }
                            
                            fullPath = ui.getPathMaster().pathBuilder(realCash);
                            
                        }

                    }

    //                    if(ui.getPathMaster().getDirsCash().get(1).get(ui.getPathMaster().getDirsCash().get(1).size() - 1).equals("Path")){
    //                        fullPath = ui.getPathMaster().getDirsCash().get(0).get(ui.getPathMaster().getDirsCash().get(0).size() - 1);
    //                    } else if(ui.getPathMaster().getDirsCash().get(1).get(ui.getPathMaster().getDirsCash().get(1).size() - 1).equals("Cash")){
    //                        for(int i = 0; i < ui.getPathMaster().getDirsCash().get(0).size(); i++){
    //                            if(ui.getPathMaster().getDirsCash().get(1).get(i).equals("Cash")){
    //                                realCash.add(ui.getPathMaster().getDirsCash().get(0).get(i));
    //                            }
    //                        }

                    //}
                
                    
                    File selectedFile;
                    selectedFile = new File(fullPath/* + selectedObject*/);
                    if(selectedFile.isFile()) {
                        
                        ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).remove(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1);
                        ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).remove(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1);
                        
                        realCash.remove(realCash.size() - 1);
                        
                        fullPath = ui.getPathMaster().pathBuilder(realCash);
                        
                    }
                    ui.getPath().setText(fullPath);
                    if(selectedFile.isDirectory()){
                        fileSheetItem.set(ui.getPathMaster().getLastTab(), 0);
                    }
                    
                    ArrayList<String> fileTypes = new ArrayList<>();

                    if(!ui.getNavigationButtonsPanel().getHiddenDirectory()){
                    if(selectedFile.isDirectory()){
                        String[] rootStr = selectedFile.list();
                        for(String str : rootStr) {
                            
                            int i = 0;
                            //System.out.println("i1: " + i);
                            for(String str2 : rootStr) {
                                //System.out.println("i2: " + i);
                                //System.out.println("true/false: " + str.substring((str.lastIndexOf(".") + 1)).equals(str2));
                                if(str.substring((str.lastIndexOf(".") + 1)).equals(str2.substring((str2.lastIndexOf(".") + 1)))) {
                                    
                                    i++;
                                    
                                }
                                
                            }
                            //System.out.println("i3: " + i);
                            if(i >= 8) {
                                
                                boolean haveOneType = false;
                                
                                for(String str3 : fileTypes) {
                                    
                                    if(str3.equals(str.substring((str.lastIndexOf(".") + 1)))) {
                                        
                                        haveOneType = true;
                                        
                                        break;
                                        
                                    }
                                
                                }
                                
                                if(!haveOneType) {
                                    
                                    fileTypes.add(str.substring((str.lastIndexOf(".") + 1)));
                                    
                                }
                                
                            }
                            
                        }
                        
                        System.out.println("=======================================================");
                        fileTypes.forEach(a -> System.out.println("ft: " + a));
                        
                        for(String str : rootStr){
                            File checkObject = new File(selectedFile.getPath(), str);
                            if(!checkObject.isHidden()){
                                if(checkObject.isDirectory()){
                                    model.add(str);
                                } else {
                                    model.add(str);
                                }
                            }
                        }
                    } else {
                        Desktop desktop = null;
                        if (Desktop.isDesktopSupported()) {
                        desktop = Desktop.getDesktop();
                    }

                    try {
                      desktop.open(selectedFile);
                      } catch (IOException ioe) {
                      ioe.printStackTrace();
                      }
                    }

                    } else if(ui.getNavigationButtonsPanel().getHiddenDirectory()){
                        if(selectedFile.isDirectory()){
                        String[] rootStr = selectedFile.list();
                        for(String str : rootStr){
                            fileTypes.add(str.substring((str.lastIndexOf(".") + 1)));
                            File checkObject = new File(selectedFile.getPath(), str);
                                if(checkObject.isDirectory()){
                                    model.add(str);
                                } else {
                                    model.add(str);
                                }
                            }

                    } else {
                        Desktop desktop = null;
                        if (Desktop.isDesktopSupported()) {
                        desktop = Desktop.getDesktop();
                    }

                    try {
                      desktop.open(selectedFile);
                      } catch (IOException ioe) {
                      ioe.printStackTrace();
                      }
                    }
                    }
                    if(selectedFile.isDirectory()){
                        //puter = (selectedObject);
                        //put.setText("Вы находитесь в : " + puter);

    //                    ui.getPathMaster().getDirsCash().get(0).add(selectedObject);
    //                    ui.getPathMaster().getDirsCash().get(1).add("Cash");
//                        forHidden.clear();
//                        forHidden.addAll(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0));
                        
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0));
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1));
                        
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).addAll(model);
                        
                        for(int i = 0; i < ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size(); i++) {
                            
                            ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).add("ForPath");
                            
                        }
                        
                        ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                            iniclizButton(almostEmptyList);

                }
                
                } else {
                    System.out.println("ui.getSearchTextField()");
                    ui.getPathMaster().setLastSearch(true);
                    
                    if(files.size() > 0) {
                        
                        ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).add(arrayButton.getName());
                        ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).add("SearchCash");
                        
                        File selectedFile = new File(arrayButton.getName());
                        System.out.println("sf: " + selectedFile);
                        System.out.println("dc1: " + ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1));
                        System.out.println("dc2: " + ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1));
                        if(!ui.getNavigationButtonsPanel().getHiddenDirectory()){
                            if(selectedFile.isDirectory()){
                                String[] rootStr = selectedFile.list();
                                for(String str : rootStr){
                                    File checkObject = new File(selectedFile.getPath(), str);
                                    if(!checkObject.isHidden()){
                                        if(checkObject.isDirectory()){
                                            model.add(str);
                                        } else {
                                            model.add(str);
                                        }
                                    }
                                }
                            } else {
                              Desktop desktop = null;
                              if (Desktop.isDesktopSupported()) {
                              desktop = Desktop.getDesktop();
                            }

                            try {
                              desktop.open(selectedFile);
                              } catch (IOException ioe) {
                              ioe.printStackTrace();
                              }
                            }

                            } else if(ui.getNavigationButtonsPanel().getHiddenDirectory()){
                                if(selectedFile.isDirectory()){
                                String[] rootStr = selectedFile.list();
                                for(String str : rootStr){
                                    File checkObject = new File(selectedFile.getPath(), str);
                                        if(checkObject.isDirectory()){
                                            model.add(str);
                                        } else {
                                            model.add(str);
                                        }
                                    }

                            } else {
                              Desktop desktop = null;
                              if (Desktop.isDesktopSupported()) {
                              desktop = Desktop.getDesktop();
                            }

                            try {
                              desktop.open(selectedFile);
                              } catch (IOException ioe) {
                              ioe.printStackTrace();
                              }
                            }
                            }
                        
                    } else {
                        
                        model.add("По запросу ничего не было найдено");
                        
                    }
                    
                    ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0));
                    ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1));
                    
                    ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).addAll(model);
                        
                        for(int i = 0; i < ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size(); i++) {
                            
                            ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).add("ForPath");
                            
                        }
                    
                    ui.getPathMaster().getAntiDirsCash().get(ui.getPathMaster().getLastTab()).get(0).clear();
                    ui.getPathMaster().getAntiDirsCash().get(ui.getPathMaster().getLastTab()).get(1).clear();
                    
                    ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                            iniclizButton(almostEmptyList);
                    
                }
                
            } else if(e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1/* && files.size() == 1 && files.get(0).equals("\\/:*?\"<>|")*/) {
                if(privateButton != null) {
                    privateButton.remove(privateButton);
                }
                //ui.getBasicPanel().remove(ui.getButtonsPanel());
                //ui.getBasicPanel().revalidate();
                //ui.getBasicPanel().repaint();
                for(int i = 0; i < (topFiles.length - 1) && i < fileSheetSize; i++){
                    topFiles[i].setBorderPainted(false);
                }
                
                int cpWidth = ui.getContentPane().getWidth();
                int cpHeight = ui.getContentPane().getHeight();
                
                ui.getBasicPanel().removeAll();
                
                ui.getInformationAboutObjectsPanel().setBounds((int)(cpWidth / 8 * 1), (int)(cpHeight - cpHeight / 36 * 12), (int)(cpWidth / 8 * 6), (int)(cpHeight / 36 * 12));
                ui.getBasicPanel().add(ui.getInformationAboutObjectsPanel());
                
                ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
                ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
                ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                ui.getSort().setBounds(10, 50, 316, 45);
                setBounds(0, 45, cpWidth, (cpHeight - 45));
                ui.getButtonsPanel().setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
                ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
                ui.getBasicPanel().add(ui.getTabbedPanel());
                ui.getBasicPanel().add(ui.getSearchTextField());
                ui.getBasicPanel().add(ui.getSort());
                ui.getBasicPanel().add(MainFilesPanel.this);
                
                ui.getBasicPanel().moveToFront(ui.getSort());
                
                revalidate();
                repaint();
                //ui.getBasicPanel().add(ui.getInformationAboutObjectsPanel()/*, BorderLayout.SOUTH*/);
                //ui.getBasicPanel().revalidate();
                //ui.getBasicPanel().repaint();
                String selectedObject = arrayButton.getName();
                ArrayList <String> realCash = new ArrayList<>();
                String fullPath = "";
                boolean success = false;
                
                    if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).isEmpty()){
                        
                        success = true;
                        
                    }
                    
                if(!success && ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() > 0){
                        if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartCash") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("Cash")){
                        for(int i = 0; i < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); i++){
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("StartCash") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("Cash")){
                                realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(i));
                            }
                        }
                        fullPath = ui.getPathMaster().pathBuilder(realCash);
                    } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartPath") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("PathCash")){
                        for(int i = 0; i < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); i++){
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("StartPath") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("PathCash")){
                                realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(i));
                            }
                        }
                        fullPath = ui.getPathMaster().pathBuilder(realCash);
                    } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartSearch") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("SearchCash")){
                        for(int i = 0; i < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); i++){
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("StartSearch") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("SearchCash")){
                                realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(i));
                            }
                        }
                        fullPath = ui.getPathMaster().pathBuilder(realCash);
                    }
                }
                
                File selectedFile = null;
                
                if(files.size() == 1 && files.get(0).equals("\\/:*?\"<>|")) {
                    
                    selectedFile = new File(fullPath + selectedObject);
                    
                } else {
                    
                    selectedFile = new File(selectedObject);
                    
                }
                    System.out.println("1278megwrg: " + fullPath);
                //=== Папки ===
                if(selectedFile.isDirectory()) {
                    
                    ui.getInformationAboutObjectsPanel().addThread();
                    
                    ///
                    if(files.size() == 1 && files.get(0).equals("\\/:*?\"<>|")) {
                        
                        ui.getInformationAboutObjectsPanel().setTextInfoName("Название : " + selectedObject);

                    } else {

                        ui.getInformationAboutObjectsPanel().setTextInfoName("Название : " + selectedFile.getName());

                    }
                    ///
                    if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() < 1) {
                        ui.getInformationAboutObjectsPanel().setTextInfoType("Тип : " + ui.getPathMaster().getFsv().getSystemTypeDescription(new File(selectedObject)));
                    } else {
                        ui.getInformationAboutObjectsPanel().setTextInfoType("Тип : Папка"); 
                    }
                    ///
                    if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() < 1){
                        ui.getInformationAboutObjectsPanel().setTextInfoPath("Свободно : " + selectedFile.getUsableSpace() + " байт");
                    } else {
                        
                        if(files.size() == 1 && files.get(0).equals("\\/:*?\"<>|")) {

                            ui.getInformationAboutObjectsPanel().setTextInfoPath("Местонахождение : " + selectedFile.getParent());

                        } else {

                            ui.getInformationAboutObjectsPanel().setTextInfoPath("Местонахождение : " + selectedObject);

                        }
                        //infoPath.setText("Местонахождение : " + selectedFile.getParent());
                        
                    }
                    ///
                    long size = 0;
                    if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() < 1){
                      size = selectedFile.getTotalSpace();
                      ui.getInformationAboutObjectsPanel().setTextInfoSize("Ёмкость : " + size + " байт");
                    } else if(selectedFile.isDirectory()) {
                        
                        ui.getInformationAboutObjectsPanel().setDir1(selectedFile);

                        ui.getInformationAboutObjectsPanel().setTextInfoSize("Размер : " + 0 + " байт");
                        
                        ui.getInformationAboutObjectsPanel().startThread1("infoSize");
                      
                    } else {
                        
                        ui.getInformationAboutObjectsPanel().setDir1(selectedFile);

                        ui.getInformationAboutObjectsPanel().setTextInfoSize("Размер : " + 0 + " байт");
                        
                        ui.getInformationAboutObjectsPanel().startThread1("infoSize");
                      
                    }
                    ///
                    BasicFileAttributes attr = null;
                    try {      
                        attr = Files.readAttributes(selectedFile.toPath(), BasicFileAttributes.class);
                    } catch (IOException ex) {
                        Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Date tyh4 = new Date(attr.creationTime().toMillis());
                    String str = String.format("%1$td %1$tB %1$tY г., %1$tT", tyh4);
                    ui.getInformationAboutObjectsPanel().setTextInfoDateCreation("Дата создания : " + str);
                    ///
                    Date tyh5 = new Date(attr.lastModifiedTime().toMillis());
                    String str2 = String.format("%1$td %1$tB %1$tY г., %1$tT", tyh5);
                    ui.getInformationAboutObjectsPanel().setTextInfoDateChange("Дата изменения : " + str2);
                    ///
                    
                    ui.getInformationAboutObjectsPanel().setTextInfoNumberFolders("Количество папок : " + 0);
                    
                    ui.getInformationAboutObjectsPanel().setDir3(selectedFile);
                        
                        ui.getInformationAboutObjectsPanel().startThread3();
                        
                        revalidate();
                        repaint();
                        System.out.println("All 1");
                    
                        ui.getInformationAboutObjectsPanel().setTextInfoNumberFolders("Количество файлов : " + 0);
                    
                    ///
                    
                        ui.getInformationAboutObjectsPanel().setDir2(selectedFile);
                        //th2.stop();
                        ui.getInformationAboutObjectsPanel().startThread2();
                        //long fileC = 0;
                        //fileC = getFileCount(selectedFile2);
                        
                        //infoNumberFiles.setText("Количество файлов : " + fileC);
                        
                        revalidate();
                        repaint();
                        System.out.println("All 2");
                    
                    ///
                //=== Файлы ===
                } else {
                    ///
                    if(files.size() == 1 && files.get(0).equals("\\/:*?\"<>|")) {
                        
                        ui.getInformationAboutObjectsPanel().setTextInfoName("Название : " + selectedObject);

                    } else {

                        ui.getInformationAboutObjectsPanel().setTextInfoName("Название : " + selectedFile.getName());

                    }
                    //infoName.setText("Название : " + selectedFile.getName());
                    ///
                    ui.getInformationAboutObjectsPanel().setTextInfoType("Тип : Файл");
                    ///
                    if(files.size() == 1 && files.get(0).equals("\\/:*?\"<>|")) {
                        
                        ui.getInformationAboutObjectsPanel().setTextInfoType("Местонахождение : " + selectedFile.getParent());
                        
                    } else {
                        
                        ui.getInformationAboutObjectsPanel().setTextInfoType("Местонахождение : " + selectedObject);
                        
                    }
                    //infoPath.setText("Местонахождение : " + selectedFile.getParent());
                    ///
                    long size = selectedFile.length();
                    System.out.println("=== size === " + size);
                    ui.getInformationAboutObjectsPanel().setTextInfoSize("Размер : " + size + " байт");
                    ///
                    BasicFileAttributes attr = null;
                    try {      
                        attr = Files.readAttributes(selectedFile.toPath(), BasicFileAttributes.class);
                    } catch (IOException ex) {
                        Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Date tyh4 = new Date(attr.creationTime().toMillis());
                    String str = String.format("%1$td %1$tB %1$tY г., %1$tT", tyh4);
                    ui.getInformationAboutObjectsPanel().setTextInfoDateCreation("Дата создания : " + str);
                    ///
                    Date tyh5 = new Date(attr.lastModifiedTime().toMillis());
                    String str2 = String.format("%1$td %1$tB %1$tY г., %1$tT", tyh5);
                    ui.getInformationAboutObjectsPanel().setTextInfoDateChange("Дата изменения : " + str2);
                    ///
                    Date tyh6 = new Date(attr.lastAccessTime().toMillis());
                    String str3 = String.format("%1$td %1$tB %1$tY г., %1$tT", tyh6);
                    ui.getInformationAboutObjectsPanel().setTextInfoNumberFolders("Дата открытия : " + str3);
                    ///
                    ui.getInformationAboutObjectsPanel().setTextInfoNumberFiles("Расширение : " + selectedFile.toString().substring(selectedFile.toString().lastIndexOf(".") + 1, selectedFile.toString().length()));
                    ///
                }
                
                arrayButton.setBorderPainted(true);
                privateButton = arrayButton;
                
            } else if(e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON3/* && files.size() == 1 && files.get(0).equals("\\/:*?\"<>|")*/) {
                
                if(privateButton != null) {
                    
                    boolean havePath = false;
                    
                    String selectedObject = privateButton.getName();
                        
                        ArrayList <String> realCashC = new ArrayList<>();
                        String fullPathC = "";

                        if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() > 0) {

                                        if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartCash") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("Cash")){
                                            for(int j = 0; j < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); j++){
                                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("StartCash") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("Cash")){
                                                    realCashC.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(j));
                                                }
                                            }
                                            fullPathC = ui.getPathMaster().pathBuilder(realCashC);
                                        } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartPath") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("PathCash")){
                                            for(int j = 0; j < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); j++){
                                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("StartPath") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("PathCash")){
                                                    realCashC.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(j));
                                                }
                                            }
                                            fullPathC = ui.getPathMaster().pathBuilder(realCashC);
                                        } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartSearch") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("SearchCash")){
                                            for(int j = 0; j < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); j++){
                                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("StartSearch") || ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(j).equals("SearchCash")){
                                                    realCashC.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(j));
                                                }
                                            }

                                            fullPathC = ui.getPathMaster().pathBuilder(realCashC);

                                        }

                        }


                        File selectedFileC;
                        selectedFileC = new File(fullPathC + selectedObject);
                        
                        String path = new File("src\\data\\MarkedFiles.txt").getAbsolutePath();
        
                        StringBuffer p = new StringBuffer();
                        File file = new File(path);
                        BufferedReader bReader = null;
                        InputStreamReader iReader = null;
                        FileInputStream fStream = null;

                        try {

                            fStream = new FileInputStream(file);
                            iReader = new InputStreamReader(fStream, "UTF-8");
                            bReader = new BufferedReader(iReader);

                            String fileLine = bReader.readLine();

                            int countData = 0;
                            
                            while (fileLine != null) {
                                
                                if(fileLine.equals(selectedFileC.toString())) {
                                    
                                    havePath = true;
                                    
                                }
                                
                                fileLine = bReader.readLine();
                                
                            }
                            
                            fStream.close();
                            iReader.close();
                            bReader.close();
                            
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        } catch (UnsupportedEncodingException ex) {
                            Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
                        
                        if(havePath) {
                            
                            ui.getButtonsPanel().remove(ui.getButtonsPanel().getAddMarkedFilesButton());
                            ui.getButtonsPanel().add(ui.getButtonsPanel().getRemoveMarkedFilesButton());

                            revalidate();
                            repaint();
                            
                        } else {
                            
                            ui.getButtonsPanel().remove(ui.getButtonsPanel().getRemoveMarkedFilesButton());
                            ui.getButtonsPanel().add(ui.getButtonsPanel().getAddMarkedFilesButton());

                            revalidate();
                            repaint();
                            
                        }
                    
                    int cpWidth = ui.getContentPane().getWidth();
                    int cpHeight = ui.getContentPane().getHeight();

                    ui.getBasicPanel().removeAll();

                    ui.getButtonsPanel().setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));

                    ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
                    ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
                    ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                    ui.getSort().setBounds(10, 50, 316, 45);
                    setBounds(0, 45, cpWidth, (cpHeight - 45));
                    ui.getButtonsPanel().setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                    ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                    
                    ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
                    ui.getBasicPanel().add(ui.getTabbedPanel());
                    ui.getBasicPanel().add(ui.getSearchTextField());
                    ui.getBasicPanel().add(ui.getSort());
                    ui.getBasicPanel().add(MainFilesPanel.this);
                    ui.getBasicPanel().add(ui.getButtonsPanel());
                    
                    ui.getBasicPanel().moveToFront(ui.getSort());
                    ui.getBasicPanel().moveToFront(ui.getButtonsPanel());

                    revalidate();
                    repaint();
                    
                }
                
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
        
        numberListeners++;
        }
        }
    }
    
    public void addLabelListener(JLabel label) {
        
        label.addMouseListener(new MouseListener(){
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            
                        }
                        
                        @Override
                        public void mousePressed(MouseEvent e) {
                            
                        }
                        
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            
                        }
                        
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            
                            tlf = Integer.parseInt(label.getName());
                            
                            prokrutkaText();
                            
                        }
                        
                        @Override
                        public void mouseExited(MouseEvent e) {
                            
                            System.out.println("Last: " + textMove);
                            
                            if(textMove) {
                                
                                textMoveThread.interrupt();
                                
                                topLabelFiles[tlf].setText(textMoveThread.text);
                            
                                tlf = 0;
                            
                            }
                            
                        }
                    });
        
    }
    
    public void prokrutkaText() {
        
        FontMetrics metrics = topLabelFiles[tlf].getFontMetrics(topLabelFiles[tlf].getFont());
                
                int hgt = metrics.getHeight();
                
                int adv = metrics.stringWidth(topLabelFiles[tlf].getText());
                
                Dimension size = new Dimension(adv+2, hgt+2);
    
                if((adv + 2) > topLabelFiles[tlf].getWidth()) {
                    
                    countSimbol = topLabelFiles[tlf].getText().length();
                    
                    while((metrics.stringWidth(topLabelFiles[tlf].getText().substring(0, countSimbol)) + 10) > topLabelFiles[tlf].getWidth()) {
                        
                        countSimbol--;
                        
                    }
                    
                    textMove = true;
                    
                    if(textMoveThread != null) {
                        
                        textMoveThread.interrupt();
                        
                        textMoveThread = null;
                        
                    }
                    
                    textMoveThread = new TextMove(this);
                    
                    textMoveThread.start();
                    
                } else {
                    
                    textMove = false;
                    
                }
        
    }
    
    public JButton getPrivateButton() {
        
        return(privateButton);
        
    }
    
    public void setPrivateButton(JButton privateButton) {
        
        this.privateButton = privateButton;
        
    }
    
    public JButton[] getTopFiles() {
        
        return(topFiles);
        
    }
    
    public void setTopFiles(JButton[] topFiles) {
        
        this.topFiles = topFiles;
        
    }
    
    public ArrayList<Integer> getFileSheetItem() {
        
        return(fileSheetItem);
        
    }
    
    public void setFileSheetItem(ArrayList<Integer> fileSheetItem) {
        
        this.fileSheetItem = fileSheetItem;
        
    }
    
    private class ProkrutkaFilesThread extends Thread {
        
        MainFilesPanel mfp;
        
        String text;
        
        int realCountSimbol;
        
        ProkrutkaFilesThread(MainFilesPanel mfp) {
            
            super("Thread for UI");
            
            this.mfp = mfp;
        
        }

        public void run() {
            
            //int i = 0;
            
            realCountSimbol = countSimbol;
            
            text = topLabelFiles[tlf].getText();
            System.out.println("11111111111111: " + tlf);
            int msDelay = 350;
            
            while(true) {
                
                if(prokrutkaFiles && !isInterrupted()) {
                    
                    Point locationCursorPanel = getMousePosition();
                    
                    double locationX = locationCursorPanel.getX();
                    double locationY = locationCursorPanel.getY();

                    int ipop2 = fileSheetItem.get(ui.getPathMaster().getLastTab());
                    if(getWidth() / 2 >= locationX) {
                      int ry = ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size() + 1;
                                    //if(topFiles[directoryOnOff].getText().toString() != ui.getPathMaster().getFilesList().getModel().getElementAt(0).toString()){
                                    fileSheetItem.set(ui.getPathMaster().getLastTab(), fileSheetItem.get(ui.getPathMaster().getLastTab()) - 1);
                                    ipop2 = fileSheetItem.get(ui.getPathMaster().getLastTab());

                                    for(int i = /*directoryOnOff*/0; i < (topFiles.length - 1) && i < ry; i++){
                                        topFiles[i].remove(topFiles[i]);
                                    }

                                    for(int i = /*directoryOnOff*/0; i < topFiles.length; i++){
                                        topFiles[i] = new JButton(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).get(ipop2));
                                        ipop2++;
                                    }

                                    ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                                    almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                                    iniclizButton(almostEmptyList);

                      System.out.println("Left");
                    } else if(getWidth() / 2 < locationX) {
                      int ry = ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size();

                                    if(ry >= topFiles.length && !topFiles[(topFiles.length - 1)].getText().toString().equals(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).get(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size() - 1))){
                                    fileSheetItem.set(ui.getPathMaster().getLastTab(), fileSheetItem.get(ui.getPathMaster().getLastTab()) + 1);
                                    ipop2 = fileSheetItem.get(ui.getPathMaster().getLastTab()); 

                                    for(int i = /*directoryOnOff*/0; i < topFiles.length; i++){
                                        topFiles[i].remove(topFiles[i]);
                                    }

                                    for(int i = /*directoryOnOff*/0; i < topFiles.length; i++){
                                        topFiles[i] = new JButton(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).get(ipop2));
                                        ipop2++;
                                    }

                                    ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                                    almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                                    iniclizButton(almostEmptyList);

                                    }
                        System.out.println("Right");
                    }
                } else if(isInterrupted()) {
                    
                    return;
                    
                }
                
                try {
                    Thread.sleep(msDelay);
                } catch (InterruptedException ex) {
                    //Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                }
                
            }
            
        }
        
    }
    
    private class TextMove extends Thread {
        
        MainFilesPanel mfp;
        
        String text;
        
        int realCountSimbol;
        
        TextMove(MainFilesPanel mfp) {
            
            super("Thread for UI");
            
            this.mfp = mfp;
        
        }

        public void run() {
            
            int i = 0;
            
            realCountSimbol = countSimbol;
            
            text = topLabelFiles[tlf].getText();
            System.out.println("11111111111111: " + tlf);
            int msDelay = 500;
            
            while(true) {
                System.out.println("qqqqq: " + realCountSimbol + " " + text.length());
                if((realCountSimbol + i) <= text.length()) {
                    
                    topLabelFiles[tlf].setText(text.substring(i, realCountSimbol + i));
                    
                    textMove = true;
                    
                    msDelay = 450;
                    
                } else {
                    
                    i = 0;
                    
                    topLabelFiles[tlf].setText(text.substring(i, realCountSimbol + i));
                    
                    textMove = true;
                    
                    msDelay = 900;
                    
                }
                
                i++;
                
                System.out.println(topLabelFiles[tlf].getText());
                    
                revalidate();
                repaint();
                
                try {
                    Thread.sleep(msDelay);
                } catch (InterruptedException ex) {
                    //Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                    return;
                }
                
            }
            
        }
        
    }
    
}
