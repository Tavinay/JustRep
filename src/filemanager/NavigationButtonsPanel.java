package filemanager;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class NavigationButtonsPanel extends JPanel {
    
    private int maxCountType = 8;
    
    private String fullPath55;
    
    private boolean hiddenDirectory = false;

    private boolean smartReduction = false;
    
    private JButton backButton = new ButtonManager(null);
    private JButton nextButton = new ButtonManager(null);
    private JButton upTreeButton = new ButtonManager(null);
    private JButton updateButton = new ButtonManager(null);
    private JButton moreButton = new ButtonManager(null);
    private JButton smartReductionButton = new ButtonManager(null);
    private JButton smartReductionButton2 = new ButtonManager(null);
    private JButton hiddenObject = new ButtonManager(null);
    private JButton hiddenObject2 = new ButtonManager(null);
    private JButton addTabButton = new ButtonManager(null);
    
    public NavigationButtonsPanel(UI ui) {
        
        setLayout(new FlowLayout(FlowLayout.CENTER));
        
        setBackground(new Color(52, 112, 255));
        
        backButton.setBorderPainted(false);
        nextButton.setBorderPainted(false);
        upTreeButton.setBorderPainted(false);
        updateButton.setBorderPainted(false);
        moreButton.setBorderPainted(false);
        smartReductionButton.setBorderPainted(false);
        smartReductionButton2.setBorderPainted(false);
        hiddenObject.setBorderPainted(false);
        hiddenObject2.setBorderPainted(false);
        addTabButton.setBorderPainted(false);
        
        backButton.setFocusable(false);
        nextButton.setFocusable(false);
        upTreeButton.setFocusable(false);
        updateButton.setFocusable(false);
        moreButton.setFocusable(false);
        smartReductionButton.setFocusable(false);
        smartReductionButton2.setFocusable(false);
        hiddenObject.setFocusable(false);
        hiddenObject2.setFocusable(false);
        addTabButton.setFocusable(false);
        
        int dimension = (int)(33.6);
        
        Image backImage = new ImageIcon(new File("src\\Resources\\Button resources\\BackButton.png").getAbsolutePath()).getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        Image nextImage = new ImageIcon(new File("src\\Resources\\Button resources\\NextButton.png").getAbsolutePath()).getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        Image upTreeImage = new ImageIcon(new File("src\\Resources\\Button resources\\UpTreeButton.png").getAbsolutePath()).getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        Image updateImage = new ImageIcon(new File("src\\Resources\\Button resources\\UpdateButton.png").getAbsolutePath()).getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        Image moreImage = new ImageIcon(new File("src\\Resources\\Button resources\\MoreButton.png").getAbsolutePath()).getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        Image smartReductionImage = new ImageIcon(new File("src\\Resources\\Button resources\\SmartReductionButton.png").getAbsolutePath()).getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        Image smartReduction2Image = new ImageIcon(new File("src\\Resources\\Button resources\\SmartReduction2Button.png").getAbsolutePath()).getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        Image hiddenObjectImage = new ImageIcon(new File("src\\Resources\\Button resources\\HiddenObjectButton.png").getAbsolutePath()).getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        Image hiddenObject2Image = new ImageIcon(new File("src\\Resources\\Button resources\\HiddenObject2Button.png").getAbsolutePath()).getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        Image addTabImage = new ImageIcon(new File("src\\Resources\\Button resources\\AddTabButton.png").getAbsolutePath()).getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        
        backButton.setIcon(new ImageIcon(backImage));
        nextButton.setIcon(new ImageIcon(nextImage));
        upTreeButton.setIcon(new ImageIcon(upTreeImage));
        updateButton.setIcon(new ImageIcon(updateImage));
        moreButton.setIcon(new ImageIcon(moreImage));
        smartReductionButton.setIcon(new ImageIcon(smartReductionImage));
        smartReductionButton2.setIcon(new ImageIcon(smartReduction2Image));
        hiddenObject.setIcon(new ImageIcon(hiddenObjectImage));
        hiddenObject2.setIcon(new ImageIcon(hiddenObject2Image));
        addTabButton.setIcon(new ImageIcon(addTabImage));
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() > 0){
                    boolean qwerty = true;
                    int chotoda = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1;
                    String chotoNet = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(chotoda);
                    String chotoNetType = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(chotoda);
                    ui.getPathMaster().getAntiDirsCash().get(ui.getPathMaster().getLastTab()).get(0).add(chotoNet);
                    ui.getPathMaster().getAntiDirsCash().get(ui.getPathMaster().getLastTab()).get(1).add(chotoNetType);
                    ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).remove(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1);
                    ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).remove(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1);
                    if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() > 0){
                    ArrayList <String> realCash = new ArrayList<>();
                    if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("Cash")){
                        int who = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).lastIndexOf("StartCash");
                        realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(who));
                        for(int i = who + 1; i < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); i++){
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("StartCash")){
                                break;
                            }
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("Cash")){
                                realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(i));
                            }
                    }
                    } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("PathCash")){
                        int who = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).lastIndexOf("StartPath");
                        realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(who));
                        for(int i = who + 1; i < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); i++){
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("StartPath")){
                                break;
                            }
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("PathCash")){
                                realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(i));
                            }
                    }
                    } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("SearchCash")){
                        int who = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).lastIndexOf("StartSearch");
                        realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(who));
                        for(int i = who + 1; i < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); i++){
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("StartSearch")){
                                break;
                            }
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("SearchCash")){
                                realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(i));
                            }
                        }
                    } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartSearch")) {
                        
                        ui.getSearchTextField().makeSearch(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1));
                        
                        qwerty = false;
                        
                    } else {
                        
                        realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1));
                        
                    }
                    
                    if(qwerty) {
                    
                        String backDir = ui.getPathMaster().pathBuilder(realCash);
                        String[] objects = new File(backDir).list();
                        ArrayList<String> backRootModel = new ArrayList<>();
                        
                        ui.getPath().setText(backDir);

                        for(String str : objects){
                            File checkFile = new File(backDir, str);

                            if(!hiddenDirectory){
                            if(!checkFile.isHidden()){
                                if(checkFile.isDirectory()){
                                    backRootModel.add(str);
                                } else {
                                    backRootModel.add(/*"файл-" + */str);
                                }
                            }
                            } else if(hiddenDirectory){
                                if(checkFile.isDirectory()){
                                    backRootModel.add(str);
                                } else {
                                    backRootModel.add(/*"файл-" + */str);
                                }
                            }
                        }
                        
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0));
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1));
                        
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).addAll(backRootModel);
                        
                        for(int i = 0; i < ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size(); i++) {
                            
                            ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).add("ForPath");
                            
                        }
                        
                    }
                    
                    } else {
                        
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
                        
                        ui.getPath().setText("Компьютер");
                    }
                } else {
                    
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
                   
                    ui.getPath().setText("Компьютер");
                    
                }
//                } else if(ui.getPathMaster().getDirsCash().get(0).size() == 0){
//                   int chotoda = ui.getPathMaster().getDirsCash().get(0).size() - 1;
//                   String chotoNet = ui.getPathMaster().getDirsCash().get(0).get(chotoda);
//                   ui.getPathMaster().getAntiDirsCash().get(0).add(chotoNet);
//                   ui.getPathMaster().getAntiDirsCash().get(1).add("Cash");
//                   //ui.getPathMaster().getDirsCash().get(0).remove(ui.getPathMaster().getDirsCash().get(0).size() - 1);
//                   ui.getPathMaster().getDirsCash().get(0).clear();
//                   ui.getPathMaster().getDirsCash().get(1).clear();
//                   ui.getPathMaster().getFilesList().setListData(ui.getPathMaster().getDiscs());
//                   puter = "Начальная страница";
//                   fullPuter = "Путь : Ненайден";
//        
//                } else {
//                   ui.getPathMaster().getDirsCash().get(0).clear();
//                   ui.getPathMaster().getDirsCash().get(1).clear();
//                   ui.getPathMaster().getFilesList().setListData(ui.getPathMaster().getDiscs());
//                   puter = "Начальная страница";
//                   fullPuter = "Путь : Ненайден";
//                }
                ui.getMainFilesPanel().getFileSheetItem().set(ui.getPathMaster().getLastTab(), 0);
                if(ui.getMainFilesPanel().getPrivateButton() != null){
                  ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                }
                //}
                ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                            ui.getMainFilesPanel().iniclizButton(almostEmptyList);
                            
                            System.out.println("1qaz");
                
                if(ui.getSearchTextField().getText().equals("") || ui.getSearchTextField().getText() == null) {
                    
                    ui.getSearchTextField().setText("Поиск");
                    ui.getSearchTextField().setForeground(new Color(37, 83, 185));
                    
                }
                
                if(ui.getMainFilesPanel().getPrivateButton() != null) {
                    ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                }
                
                for(int i = 0; i < (ui.getMainFilesPanel().getTopFiles().length - 1); i++){
                    
                    if(ui.getMainFilesPanel().getTopFiles()[i] != null) {
                        
                        ui.getMainFilesPanel().getTopFiles()[i].setBorderPainted(false);
                        
                    }
                    
                }
                
                int cpWidth = ui.getContentPane().getWidth();
                int cpHeight = ui.getContentPane().getHeight();
                
                ui.getBasicPanel().removeAll();
                
                ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
                ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
                ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                ui.getSort().setBounds(10, 50, 316, 45);
                ui.getMainFilesPanel().setBounds(0, 45, cpWidth, (cpHeight - 45));
                ui.getButtonsPanel().setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
                ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
                ui.getBasicPanel().add(ui.getTabbedPanel());
                ui.getBasicPanel().add(ui.getSearchTextField());
                ui.getBasicPanel().add(ui.getSort());
                ui.getBasicPanel().add(ui.getMainFilesPanel());
                
                ui.getBasicPanel().moveToFront(ui.getSort());
                
                ui.revalidate();
                repaint();
                            
            //}
            }
        });
        
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ui.getPathMaster().getAntiDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() > 0) {
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
                        
                   ui.getPath().setText("Компьютер");
                    
                    if(ui.getMainFilesPanel().getPrivateButton() != null){
                      ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                    }
                    int chotoda2 = ui.getPathMaster().getAntiDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1;
                    String notNextDir = ui.getPathMaster().getAntiDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(chotoda2);
                    ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).add(notNextDir);
                    ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).add(ui.getPathMaster().getAntiDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(chotoda2));
                    
//                    if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartCash")) {
//                        
//                        
//                        
//                    } else {
                    
                        String nextDir = ui.getPathMaster().pathBuilder(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0));
                        String[] objects = new File(nextDir).list();
                        ArrayList<String> nextRootModel = new ArrayList<>();

                        for(String str : objects){
                            File checkFile = new File(nextDir, str);

                            //if(!checkFile.isHidden()){
                                if(checkFile.isDirectory()){
                                    nextRootModel.add(str);
                                } else {
                                    nextRootModel.add(/*"файл-" + */str);
                                }
                            //}
                        }
                        
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0));
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1));
                        
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).addAll(nextRootModel);
                        
                        for(int i = 0; i < ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size(); i++) {
                            
                            ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).add("ForPath");
                            
                        }
                        
                        ui.getPathMaster().getAntiDirsCash().get(ui.getPathMaster().getLastTab()).get(0).remove(chotoda2);
                        ui.getPathMaster().getAntiDirsCash().get(ui.getPathMaster().getLastTab()).get(1).remove(chotoda2);
                        
                }
                
                ui.getMainFilesPanel().getFileSheetItem().set(ui.getPathMaster().getLastTab(), 0);
                ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                            ui.getMainFilesPanel().iniclizButton(almostEmptyList);
                            
                            System.out.println("1qaz");
                
                if(ui.getSearchTextField().getText().equals("") || ui.getSearchTextField().getText() == null) {
                    
                    ui.getSearchTextField().setText("Поиск");
                    ui.getSearchTextField().setForeground(new Color(37, 83, 185));
                    
                }
                
                if(ui.getMainFilesPanel().getPrivateButton() != null) {
                    ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                }
                
                for(int i = 0; i < (ui.getMainFilesPanel().getTopFiles().length - 1); i++){
                    
                    if(ui.getMainFilesPanel().getTopFiles()[i] != null) {
                        
                        ui.getMainFilesPanel().getTopFiles()[i].setBorderPainted(false);
                        
                    }
                    
                }
                
                int cpWidth = ui.getContentPane().getWidth();
                int cpHeight = ui.getContentPane().getHeight();
                
                ui.getBasicPanel().removeAll();
                
                ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
                ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
                ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                ui.getSort().setBounds(10, 50, 316, 45);
                ui.getMainFilesPanel().setBounds(0, 45, cpWidth, (cpHeight - 45));
                ui.getButtonsPanel().setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
                ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
        //ui.getBasicPanel().add(ui.getPath());
        ui.getBasicPanel().add(ui.getTabbedPanel());
        ui.getBasicPanel().add(ui.getSearchTextField());
        ui.getBasicPanel().add(ui.getSort());
        ui.getBasicPanel().add(ui.getMainFilesPanel());
        
        ui.getBasicPanel().moveToFront(ui.getSort());
                
                ui.revalidate();
                repaint();
                            
            }
        });
        
        upTreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() > 0) {
                    
                    boolean haveCash = true;
                    int chotoda = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1;
                    String chotoNet = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(chotoda);
                    String chotoNetType = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(chotoda);
                    
                    ArrayList <String> realCash = new ArrayList<>();
                    if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("Cash")){
                        ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).remove(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1);
                        ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).remove(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1);
                        int who = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).lastIndexOf("StartCash");
                        realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(who));
                        for(int i = who + 1; i < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); i++){
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("StartCash")){
                                break;
                            }
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("Cash")){
                                realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(i));
                            }
                    }
                    } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("PathCash")){
                        ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).remove(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1);
                        ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).remove(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1);
                        int who = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).lastIndexOf("StartPath");
                        realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(who));
                        for(int i = who + 1; i < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); i++){
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("StartPath")){
                                break;
                            }
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("PathCash")){
                                realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(i));
                            }
                    }
                    } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("SearchCash")){
                        ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).remove(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1);
                        ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).remove(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1);
                        int who = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).lastIndexOf("StartSearch");
                        realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(who));
                        for(int i = who + 1; i < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); i++){
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("StartSearch")){
                                break;
                            }
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("SearchCash")){
                                realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(i));
                            }
                        }
                    } else {
                        
                        if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("StartPath")) {
                            
                            int countDir = 0;
                            
                            for(int i = 0; i < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1).length(); i++) {
                                
                                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1).substring(i, i + 1).equals(ui.getPathMaster().getSep())) {
                                    
                                    countDir++;
                                    
                                }
                                
                            }
                            
                            boolean lastSlash = true;
                            
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1).lastIndexOf(ui.getPathMaster().getSep()) + 1 != ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1).length()) {
                                
                                countDir++;
                                
                                lastSlash = false;
                                
                            }
                            
                            if(countDir > 1) {
                                
                                String lastCash = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1);
                                
                                if(lastSlash) {
                                    
                                    lastCash = lastCash.substring(0, ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1).lastIndexOf("\\"));
                                    
                                    if(countDir == 2) {
                                        
                                        lastCash = lastCash.substring(0, ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1).lastIndexOf("\\") + 1);
                                        
                                    } else {
                                        
                                        lastCash = lastCash.substring(0, ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1).lastIndexOf("\\"));
                                        
                                    }
                                    
                                } else {
                                    
                                    if(countDir == 2) {
                                        
                                        lastCash = lastCash.substring(0, ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1).lastIndexOf("\\") + 1);
                                        
                                    } else {
                                        
                                        lastCash = lastCash.substring(0, ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1).lastIndexOf("\\"));
                                        
                                    }
                                    
                                }
                                
                                ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).remove(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1);
                                ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).add(lastCash);
                                
                                realCash.add(lastCash);
                                
                            } else {
                                
                                haveCash = false;
                                
                            }
                            
                        } else {
                            
                            ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).remove(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1);
                            ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).remove(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1);
                            
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).isEmpty() && ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).isEmpty()) {
                                
                                haveCash = false;
                                
                            }
                            
                        }
                        
                    }
                    
                    if(haveCash) {
                    
                        String backDir = ui.getPathMaster().pathBuilder(realCash);
                        String[] objects = new File(backDir).list();
                        ArrayList<String> backRootModel = new ArrayList<>();
                        System.out.println("bd: " + backDir);
                        ui.getPath().setText(backDir);
                        
                        for(String str : objects){
                            File checkFile = new File(backDir, str);

                            if(!hiddenDirectory){
                            if(!checkFile.isHidden()){
                                if(checkFile.isDirectory()){
                                    backRootModel.add(str);
                                } else {
                                    backRootModel.add(/*"файл-" + */str);
                                }
                            }
                            } else if(hiddenDirectory){
                                if(checkFile.isDirectory()){
                                    backRootModel.add(str);
                                } else {
                                    backRootModel.add(/*"файл-" + */str);
                                }
                            }
                        }
                        
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0));
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1));
                        
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).addAll(backRootModel);
                        
                        for(int i = 0; i < ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size(); i++) {
                            
                            ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).add("ForPath");
                            
                        }
                        
                    } else {
                        
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
                    
                        ui.getPath().setText("Компьютер");
                        
                    }
                    
                } else {
                    
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
                        
                   ui.getPath().setText("Компьютер");
                   
                }
//                } else if(ui.getPathMaster().getDirsCash().get(0).size() == 0){
//                   int chotoda = ui.getPathMaster().getDirsCash().get(0).size() - 1;
//                   String chotoNet = ui.getPathMaster().getDirsCash().get(0).get(chotoda);
//                   ui.getPathMaster().getAntiDirsCash().get(0).add(chotoNet);
//                   ui.getPathMaster().getAntiDirsCash().get(1).add("Cash");
//                   //ui.getPathMaster().getDirsCash().get(0).remove(ui.getPathMaster().getDirsCash().get(0).size() - 1);
//                   ui.getPathMaster().getDirsCash().get(0).clear();
//                   ui.getPathMaster().getDirsCash().get(1).clear();
//                   ui.getPathMaster().getFilesList().setListData(ui.getPathMaster().getDiscs());
//                   puter = "Начальная страница";
//                   fullPuter = "Путь : Ненайден";
//        
//                } else {
//                   ui.getPathMaster().getDirsCash().get(0).clear();
//                   ui.getPathMaster().getDirsCash().get(1).clear();
//                   ui.getPathMaster().getFilesList().setListData(ui.getPathMaster().getDiscs());
//                   puter = "Начальная страница";
//                   fullPuter = "Путь : Ненайден";
//                }
                ui.getMainFilesPanel().getFileSheetItem().set(ui.getPathMaster().getLastTab(), 0);
                if(ui.getMainFilesPanel().getPrivateButton() != null){
                  ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                }
                //}
                ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                            ui.getMainFilesPanel().iniclizButton(almostEmptyList);
                            
                            System.out.println("1qaz");
                
                if(ui.getSearchTextField().getText().equals("") || ui.getSearchTextField().getText() == null) {
                    
                    ui.getSearchTextField().setText("Поиск");
                    ui.getSearchTextField().setForeground(new Color(37, 83, 185));
                    
                }
                
                if(ui.getMainFilesPanel().getPrivateButton() != null) {
                    ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                }
                
                for(int i = 0; i < (ui.getMainFilesPanel().getTopFiles().length - 1); i++){
                    
                    if(ui.getMainFilesPanel().getTopFiles()[i] != null) {
                        
                        ui.getMainFilesPanel().getTopFiles()[i].setBorderPainted(false);
                        
                    }
                    
                }
                
                int cpWidth = ui.getContentPane().getWidth();
                int cpHeight = ui.getContentPane().getHeight();
                
                ui.getBasicPanel().removeAll();
                
                ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
                ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
                ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                ui.getSort().setBounds(10, 50, 316, 45);
                ui.getMainFilesPanel().setBounds(0, 45, cpWidth, (cpHeight - 45));
                ui.getButtonsPanel().setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
                ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
        //ui.getBasicPanel().add(ui.getPath());
        ui.getBasicPanel().add(ui.getTabbedPanel());
        ui.getBasicPanel().add(ui.getSearchTextField());
        ui.getBasicPanel().add(ui.getSort());
        ui.getBasicPanel().add(ui.getMainFilesPanel());
        
        ui.getBasicPanel().moveToFront(ui.getSort());
                
                ui.revalidate();
                repaint();
                
            }
        });
        
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() > 0){
                    int chotoda = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1;
                    String chotoNet = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(chotoda);
                    String chotoNetType = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(chotoda);
                    if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() > 0){
                    ArrayList <String> realCash = new ArrayList<>();
                    if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("Cash")){
                        int who = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).lastIndexOf("StartCash");
                        realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(who));
                        for(int i = who + 1; i < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); i++){
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("StartCash")){
                                break;
                            }
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("Cash")){
                                realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(i));
                            }
                    }
                    } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("PathCash")){
                        int who = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).lastIndexOf("StartPath");
                        realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(who));
                        for(int i = who + 1; i < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); i++){
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("StartPath")){
                                break;
                            }
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("PathCash")){
                                realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(i));
                            }
                    }
                    } else if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1).equals("SearchCash")){
                        int who = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).lastIndexOf("StartSearch");
                        
                        for(int i = who + 1; i < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); i++){
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("StartSearch")){
                                break;
                            }
                            if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).get(i).equals("SearchCash")){
                                realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(i));
                            }
                        }
                    } else {
                        
                        realCash.add(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(1).size() - 1));
                        
                    }
                    String backDir = ui.getPathMaster().pathBuilder(realCash);
                    String[] objects = new File(backDir).list();
                    ArrayList<String> backRootModel = new ArrayList<>();
                    
                    ui.getPath().setText(backDir);
                    
                    for(String str : objects){
                        File checkFile = new File(backDir, str);
                        
                        if(!hiddenDirectory){
                        if(!checkFile.isHidden()){
                            if(checkFile.isDirectory()){
                                backRootModel.add(str);
                            } else {
                                backRootModel.add(/*"файл-" + */str);
                            }
                        }
                        } else if(hiddenDirectory){
                            if(checkFile.isDirectory()){
                                backRootModel.add(str);
                            } else {
                                backRootModel.add(/*"файл-" + */str);
                            }
                        }
                    }
                    
                    ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0));
                    ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1));
                    
                    ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).addAll(backRootModel);
                        
                        for(int i = 0; i < ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size(); i++) {
                            
                            ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).add("ForPath");
                            
                        }
                    
                    } else {
                        
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
                            System.out.println("gyh: " + ui.getPathMaster().getDiscs()[ui.getPathMaster().getUi7()]);
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
                        
                        ui.getPath().setText("Компьютер");
                        
                    }
                } else {
                    
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
                    
                    ui.getPath().setText("Компьютер");
                    
                }

                ui.getMainFilesPanel().getFileSheetItem().set(ui.getPathMaster().getLastTab(), 0);
                
                if(ui.getMainFilesPanel().getPrivateButton() != null){
                  ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                }
                
                ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                            ui.getMainFilesPanel().iniclizButton(almostEmptyList);
                            
                            System.out.println("1qaz");
                
                if(ui.getSearchTextField().getText().equals("") || ui.getSearchTextField().getText() == null) {
                    
                    ui.getSearchTextField().setText("Поиск");
                    ui.getSearchTextField().setForeground(new Color(37, 83, 185));
                    
                }
                
                if(ui.getMainFilesPanel().getPrivateButton() != null) {
                    ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                }
                
                for(int i = 0; i < (ui.getMainFilesPanel().getTopFiles().length - 1); i++){
                    
                    if(ui.getMainFilesPanel().getTopFiles()[i] != null) {
                        
                        ui.getMainFilesPanel().getTopFiles()[i].setBorderPainted(false);
                        
                    }
                    
                }
                
                int cpWidth = ui.getContentPane().getWidth();
                int cpHeight = ui.getContentPane().getHeight();
                
                ui.getBasicPanel().removeAll();
                
                ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
                ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
                ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                ui.getSort().setBounds(10, 50, 316, 45);
                ui.getMainFilesPanel().setBounds(0, 45, cpWidth, (cpHeight - 45));
                ui.getButtonsPanel().setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
                ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
        //ui.getBasicPanel().add(ui.getPath());
        ui.getBasicPanel().add(ui.getTabbedPanel());
        ui.getBasicPanel().add(ui.getSearchTextField());
        ui.getBasicPanel().add(ui.getSort());
        ui.getBasicPanel().add(ui.getMainFilesPanel());
        
        ui.getBasicPanel().moveToFront(ui.getSort());
                
                ui.revalidate();
                repaint();
                
            }
        });
        
        moreButton.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) {
                    
                    int cpWidth = ui.getContentPane().getWidth();
                    int cpHeight = ui.getContentPane().getHeight();

                    ui.getBasicPanel().removeAll();

                    ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                    ui.getBasicPanel().add(ui.getMorePanel());

                    ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
                ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
                ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                ui.getSort().setBounds(10, 50, 316, 45);
                ui.getMainFilesPanel().setBounds(0, 45, cpWidth, (cpHeight - 45));
                ui.getButtonsPanel().setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
                ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
                ui.getBasicPanel().add(ui.getTabbedPanel());
                ui.getBasicPanel().add(ui.getSearchTextField());
                ui.getBasicPanel().add(ui.getSort());
                ui.getBasicPanel().add(ui.getMainFilesPanel());
                
                ui.getBasicPanel().moveToFront(ui.getSort());
                ui.getBasicPanel().moveToFront(ui.getMorePanel());
                
                ui.revalidate();
                ui.repaint();
                
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
        
        smartReductionButton.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) {
                    
                    smartReduction = true;
                    
                    ui.getNavigationButtonsPanel().remove(smartReductionButton);
                    ui.getNavigationButtonsPanel().remove(hiddenObject);
                    ui.getNavigationButtonsPanel().remove(addTabButton);
                    
                    ui.getNavigationButtonsPanel().add(smartReductionButton2);
                    ui.getNavigationButtonsPanel().add(hiddenObject);
                    ui.getNavigationButtonsPanel().add(addTabButton);
                    
                    ui.revalidate();
                    repaint();
                    
                    ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                            ui.getMainFilesPanel().iniclizButton(almostEmptyList);
                            
                            System.out.println("1qaz");
                
                if(ui.getSearchTextField().getText().equals("") || ui.getSearchTextField().getText() == null) {
                    
                    ui.getSearchTextField().setText("Поиск");
                    ui.getSearchTextField().setForeground(new Color(37, 83, 185));
                    
                }
                
                if(ui.getMainFilesPanel().getPrivateButton() != null) {
                    ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                }
                
                for(int i = 0; i < (ui.getMainFilesPanel().getTopFiles().length - 1); i++){
                    
                    if(ui.getMainFilesPanel().getTopFiles()[i] != null) {
                        
                        ui.getMainFilesPanel().getTopFiles()[i].setBorderPainted(false);
                        
                    }
                    
                }
                
                int cpWidth = ui.getContentPane().getWidth();
                int cpHeight = ui.getContentPane().getHeight();
                
                ui.getBasicPanel().removeAll();
                
                ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
                ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
                ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                ui.getSort().setBounds(10, 50, 316, 45);
                ui.getMainFilesPanel().setBounds(0, 45, cpWidth, (cpHeight - 45));
                ui.getButtonsPanel().setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
                ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
        //ui.getBasicPanel().add(ui.getPath());
        ui.getBasicPanel().add(ui.getTabbedPanel());
        ui.getBasicPanel().add(ui.getSearchTextField());
        ui.getBasicPanel().add(ui.getSort());
        ui.getBasicPanel().add(ui.getMainFilesPanel());
        
        ui.getBasicPanel().moveToFront(ui.getSort());
                
                ui.revalidate();
                repaint();
                    
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
        
        smartReductionButton2.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) {
                    
                    smartReduction = false;
                    
                    ui.getNavigationButtonsPanel().remove(smartReductionButton2);
                    ui.getNavigationButtonsPanel().remove(hiddenObject);
                    ui.getNavigationButtonsPanel().remove(addTabButton);
                    
                    ui.getNavigationButtonsPanel().add(smartReductionButton);
                    ui.getNavigationButtonsPanel().add(hiddenObject);
                    ui.getNavigationButtonsPanel().add(addTabButton);
                    
                    ui.revalidate();
                    repaint();
                    
                    ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                            ui.getMainFilesPanel().iniclizButton(almostEmptyList);
                            
                            System.out.println("1qaz");
                
                if(ui.getSearchTextField().getText().equals("") || ui.getSearchTextField().getText() == null) {
                    
                    ui.getSearchTextField().setText("Поиск");
                    ui.getSearchTextField().setForeground(new Color(37, 83, 185));
                    
                }
                
                if(ui.getMainFilesPanel().getPrivateButton() != null) {
                    ui.getMainFilesPanel().getPrivateButton().remove(ui.getMainFilesPanel().getPrivateButton());
                }
                
                for(int i = 0; i < (ui.getMainFilesPanel().getTopFiles().length - 1); i++){
                    
                    if(ui.getMainFilesPanel().getTopFiles()[i] != null) {
                        
                        ui.getMainFilesPanel().getTopFiles()[i].setBorderPainted(false);
                        
                    }
                    
                }
                
                int cpWidth = ui.getContentPane().getWidth();
                int cpHeight = ui.getContentPane().getHeight();
                
                ui.getBasicPanel().removeAll();
                
                ui.getNavigationButtonsPanel().setBounds(0, 0, 336, 45);
                ui.getTabbedPanel().setBounds(336, 0, (cpWidth - 336 - 168), 45);
                ui.getSearchTextField().setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                ui.getSort().setBounds(10, 50, 316, 45);
                ui.getMainFilesPanel().setBounds(0, 45, cpWidth, (cpHeight - 45));
                ui.getButtonsPanel().setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                ui.getMorePanel().setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
                ui.getBasicPanel().add(ui.getNavigationButtonsPanel());
        //ui.getBasicPanel().add(ui.getPath());
        ui.getBasicPanel().add(ui.getTabbedPanel());
        ui.getBasicPanel().add(ui.getSearchTextField());
        ui.getBasicPanel().add(ui.getSort());
        ui.getBasicPanel().add(ui.getMainFilesPanel());
        
        ui.getBasicPanel().moveToFront(ui.getSort());
                
                ui.revalidate();
                repaint();
                    
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
        
        hiddenObject.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                if(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size() > 0){
                ArrayList<String> model = new ArrayList<>();
                String fullPath = ui.getPathMaster().pathBuilder(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0));
                File selectedFile;
                  selectedFile = new File(fullPath);
                
                if(selectedFile.isDirectory()){
                    String[] rootStr = selectedFile.list();
                    for(String str : rootStr){
                        File checkObject = new File(selectedFile.getPath(), str);
                        //if(!checkObject.isHidden()){
                            if(checkObject.isDirectory()){
                                model.add(str);
                            } else {
                                model.add(/*"файл - " + */str);
                            }
                        //}
                    }
                    if(selectedFile.isDirectory()){
                    int uiop = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1;
                    }
                    
                    ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0));
                    ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1));
                    
                    ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).addAll(model);

                    for(int i = 0; i < ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size(); i++) {

                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).add("ForPath");

                    }
                }
            }
           
            }
                
                hiddenDirectory = true;
                ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                ui.getMainFilesPanel().iniclizButton(almostEmptyList);
                ui.getNavigationButtonsPanel().remove(hiddenObject);
                ui.getNavigationButtonsPanel().remove(addTabButton);
                ui.getNavigationButtonsPanel().add(hiddenObject2);
                ui.getNavigationButtonsPanel().add(addTabButton);
                ui.revalidate();
                repaint();
                
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
        
        hiddenObject2.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                if(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size() > 0){
                ArrayList<String> model = new ArrayList<>();
                String fullPath = ui.getPathMaster().pathBuilder(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0));
                
                fullPath55 = ui.getPathMaster().pathBuilder(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0));
               
                    for(int YaHeXotelNo = 0; YaHeXotelNo < ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size(); YaHeXotelNo++) {
                         File Joke = new File(fullPath55);
                         fullPath55 = Joke.getAbsolutePath();
                         fullPath55 = fullPath55.substring(0, fullPath55.lastIndexOf("\\"));
                         
                        File checkObject = new File(Joke.getPath());
                        if(!checkObject.isHidden()){
                            continue;
                        } else if(checkObject.isHidden()){
                          for(int YaHeXotelNo2 = 0; YaHeXotelNo2 <= YaHeXotelNo && ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() > 1; YaHeXotelNo2++){
                          ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).remove(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1);
                          }
                          fullPath = fullPath55.substring(0, fullPath55.lastIndexOf("\\") + 1);
                        }
                    }
                
                File selectedFile;
                fullPath = ui.getPathMaster().pathBuilder(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0));
                  selectedFile = new File(fullPath);
                
                if(selectedFile.isDirectory()){
                    String[] rootStr = selectedFile.list();
                    for(String str : rootStr){
                        File checkObject = new File(selectedFile.getPath(), str);
                        if(!checkObject.isHidden()){
                            if(checkObject.isDirectory()){
                                model.add(str);
                            } else {
                                model.add(/*"файл - " + */str);
                            }
                        }
                    }
                    if(selectedFile.isDirectory()){
                    int uiop = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() - 1;
                    }
//                    int uiop2 = ui.getPathMaster().getDirsCash().size() - 1;
//                    ui.getPathMaster().getDirsCash().add(ui.getPathMaster().getDirsCash()[uiop2]);

                    ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0));
                    ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).removeAll(ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1));

                    ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).addAll(model);

                    for(int i = 0; i < ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(0).size(); i++) {

                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).add("ForPath");

                    }
                }
            }
           
            }
            
                hiddenDirectory = false;
                ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                ui.getMainFilesPanel().iniclizButton(almostEmptyList);
                ui.getNavigationButtonsPanel().remove(hiddenObject2);
                ui.getNavigationButtonsPanel().remove(addTabButton);
                ui.getNavigationButtonsPanel().add(hiddenObject);
                ui.getNavigationButtonsPanel().add(addTabButton);
                ui.revalidate();
                repaint();
                
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
        
        addTabButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(ui.getTabbedPanel().getTabbedButtons().size() < 100 && 48 <= (ui.getTabbedPanel().getWidth() - (20 + 20)) / (ui.getTabbedPanel().getTabbedButtons().size() + 1)) {
                    
                    String tabName = "Компьютер";
                    
    //                if(ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).size() > 0) {
    //                    
    //                    tabName = ui.getPathMaster().getDirsCash().get(ui.getPathMaster().getLastTab()).get(0).get(ui.getPathMaster().getDirsCash().get(0).size() - 1);
    //                    
    //                }
    
                    //TabButton button = new TabButton("Вкладка " + (ui.getTabbedPanel().getTabbedButtons().size() + 1), ui.getTabbedPanel().getTabWidth(), ui.getTabbedPanel().getTabHeight());
                    TabButton button = new TabButton("Компьютер", ui.getTabbedPanel().getTabWidth(), ui.getTabbedPanel().getTabHeight());
                    
                    button.setName(Integer.toString(ui.getTabbedPanel().getTabbedButtons().size()));
                    button.setFont(new Font("Calibri", Font.BOLD, 14));
        
                    //button.setHorizontalAlignment(SwingConstants.LEFT);
                    
                    button.setFocusable(false);
                    
                    ui.getPathMaster().setLastTab(Integer.parseInt(button.getName()));
                    
                    for(int i = 0; i < ui.getTabbedPanel().getTabbedButtons().size(); i++) {
                                
                        ui.getTabbedPanel().getTabbedButtons().get(i).setBackground(Color.WHITE);
                                
                    }
                    
                    button.setBackground(new Color(230, 230, 230));
                    
                    if((20 + 20 + (ui.getTabbedPanel().getTabbedButtons().size() + 1) * ui.getTabbedPanel().getTabWidth() - (ui.getTabbedPanel().getTabbedButtons().size() + 1) * (ui.getTabbedPanel().getTabWidth() / 10)) < ui.getTabbedPanel().getWidth()) {
                        
                        if((ui.getFrameWidth() - 400 - 200 - 40) >= 120) {
                            
                            ui.getTabbedPanel().setTabWidth(140);
                            
                        } else {
                            
                            ui.getTabbedPanel().setTabWidth(ui.getFrameWidth() - 400 - 200 - 40);
                            
                        }
                        
                        button.setBounds(20 + ui.getTabbedPanel().getTabbedButtons().size() * ui.getTabbedPanel().getTabWidth() - ui.getTabbedPanel().getTabbedButtons().size() * (ui.getTabbedPanel().getTabWidth() / 10), 13, ui.getTabbedPanel().getTabWidth(), ui.getTabbedPanel().getTabHeight());
                        
                    } else {
                        
                        ui.getTabbedPanel().setTabWidth((ui.getTabbedPanel().getWidth() - (20 + 20)) / (ui.getTabbedPanel().getTabbedButtons().size() + 1));
                        
                        for(int i = 0; i < ui.getTabbedPanel().getTabbedButtons().size(); i++) {
                            
                            ui.getTabbedPanel().getTabbedButtons().get(i).setBounds(20 + i * ui.getTabbedPanel().getTabWidth() - i * (ui.getTabbedPanel().getTabWidth() / 10), 13, ui.getTabbedPanel().getTabWidth(), ui.getTabbedPanel().getTabHeight());
                            
                        }
                        
                        button.setBounds(20 + ui.getTabbedPanel().getTabbedButtons().size() * ui.getTabbedPanel().getTabWidth() - ui.getTabbedPanel().getTabbedButtons().size() * (ui.getTabbedPanel().getTabWidth() / 10), 13, ui.getTabbedPanel().getTabWidth(), ui.getTabbedPanel().getTabHeight());
                        
                    }
                    
                    ui.getPath().setText("Компьютер");
                    
                    button.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(e.getButton() == MouseEvent.BUTTON1) {
                            
                            ui.getPathMaster().setLastTab(Integer.parseInt(button.getName()));
                            ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                            ui.getMainFilesPanel().iniclizButton(almostEmptyList);
                            for(int i = 0; i < ui.getTabbedPanel().getTabbedButtons().size(); i++) {
                                
                                ui.getTabbedPanel().getTabbedButtons().get(i).setBackground(Color.WHITE);
                                
                            }
                            button.setBackground(new Color(230, 230, 230));
                            System.out.println("tb: " + ui.getPathMaster().getLastTab());
                            
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
                    
                    ui.getTabbedPanel().getTabbedButtons().add(button);
                    
                    ui.getTabbedPanel().setTB();
                    
                    for(int i = 0; i < ui.getTabbedPanel().getTabbedButtons().size(); i++) {
                        
                        ui.getTabbedPanel().remove(ui.getTabbedPanel().getTabbedButtons().get(i));
                        
                    }
                    
                    for(int i = (ui.getTabbedPanel().getTabbedButtons().size() - 1); i >= 0; i--) {
                        
                        ui.getTabbedPanel().add(ui.getTabbedPanel().getTabbedButtons().get(i));
                        
                    }

                    ui.getMainFilesPanel().getFileSheetItem().add(0);
                    ui.getPathMaster().getDirsCash().add(new ArrayList<ArrayList<String>>());
                    ui.getPathMaster().getAntiDirsCash().add(new ArrayList<ArrayList<String>>());
                    ui.getPathMaster().getFilesList().add(new ArrayList<ArrayList<String>>());
                    ui.getPathMaster().getFilesList().get(ui.getTabbedPanel().getTabbedButtons().size() - 1).add(new ArrayList<String>());
                    ui.getPathMaster().getFilesList().get(ui.getTabbedPanel().getTabbedButtons().size() - 1).add(new ArrayList<String>());

                    ui.getPathMaster().getDirsCash().get(ui.getTabbedPanel().getTabbedButtons().size() - 1).add(new ArrayList<String>());//  Info
                    ui.getPathMaster().getDirsCash().get(ui.getTabbedPanel().getTabbedButtons().size() - 1).add(new ArrayList<String>());//  Type

                    ui.getPathMaster().getAntiDirsCash().get(ui.getTabbedPanel().getTabbedButtons().size() - 1).add(new ArrayList<String>());//  Info
                    ui.getPathMaster().getAntiDirsCash().get(ui.getTabbedPanel().getTabbedButtons().size() - 1).add(new ArrayList<String>());//  Type
                    
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
                    
                    ui.getPathMaster().getFilesList().get(ui.getTabbedPanel().getTabbedButtons().size() - 1).get(0).addAll(new ArrayList<>(discsList));
                    
                    for(int i = 0; i < ui.getPathMaster().getFilesList().get(ui.getTabbedPanel().getTabbedButtons().size() - 1).get(0).size(); i++) {
                        
                        ui.getPathMaster().getFilesList().get(ui.getPathMaster().getLastTab()).get(1).add("ForPath");
                        
                    }
                    
                    ui.getPathMaster().setLastTab(Integer.parseInt(ui.getTabbedPanel().getTabbedButtons().get(ui.getTabbedPanel().getTabbedButtons().size() - 1).getName()));

                    ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                            ui.getMainFilesPanel().iniclizButton(almostEmptyList);
                    
                    ui.revalidate();
                    repaint();
                
                }
                
            }
        });
        
        add(backButton);
        add(nextButton);
        add(upTreeButton);
        add(updateButton);
        add(moreButton);
        add(smartReductionButton);
        add(hiddenObject);
        add(addTabButton);
        
    }
    
    public boolean getHiddenDirectory() {
        
        return(hiddenDirectory);
        
    }
    
    public void setHiddenDirectory(boolean hiddenDirectory) {
        
        this.hiddenDirectory = hiddenDirectory;
        
    }
    
}
