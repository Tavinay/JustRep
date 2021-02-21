package filemanager;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;

public class UI extends JFrame {
    
    /**
     *
     */
    private static final long serialVersionUID = 5231223440154776469L;
    
    private int frameWidth = 1024;
    private int frameHeight = 600;
    
    private int frameWidthMin = 614;
    private int frameHeightMin = 360;
    
    int msDelay = 350;
    
    private String sortStr = "По умолчанию";
    
    String[] sortNames = {"По умолчанию", "Имя по возрастанию", "Имя по убыванию", "Тип по возрастанию", "Тип по убыванию", "Размер по возрастанию", "Размер по убыванию", "Дата изменения по возрастанию", "Дата изменения по убыванию",  "Дата создания по возрастанию", "Дата создания по убыванию"};
    String[] filterNames = {"По умолчанию", "Имя по возрастанию", "Имя по убыванию", "Тип по возрастанию", "Тип по убыванию", "Размер по возрастанию", "Размер по убыванию", "Дата изменения по возрастанию", "Дата изменения по убыванию",  "Дата создания по возрастанию", "Дата создания по убыванию"};
    String[] groupNames = {"По умолчанию", "Имя по возрастанию", "Имя по убыванию", "Тип по возрастанию", "Тип по убыванию", "Размер по возрастанию", "Размер по убыванию", "Дата изменения по возрастанию", "Дата изменения по убыванию",  "Дата создания по возрастанию", "Дата создания по убыванию"};
    private JComboBox<String> sort = new JComboBox<>(sortNames);
    private JComboBox<String> filter = new JComboBox<>(filterNames);
    private JComboBox<String> group = new JComboBox<>(groupNames);
    
    private JLayeredPane basicPanel = new JLayeredPane();
    private JPanel settingsPanel = new JPanel();
    private JPanel informationPanel = new JPanel();
    private JPanel morePanel = new JPanel();
    
    private JTextField path = new JTextField("Компьютер");
    
    private PathMaster pathMaster;
    private MainFilesPanel mainFilesPanel;
    private TabbedPanel tabbedPanel;
    private ButtonsPanel buttonsPanel;
    private SearchTextField searchTextField;
    private NavigationButtonsPanel navigationButtonsPanel;
    private InformationAboutObjectsPanel informationAboutObjectsPanel;
    
    private JButton settingsButton = new ButtonManager("Настройки");
    private JButton informationButton = new ButtonManager("Информация");
    
    public UI() {
        
        /*
        
        Какие-то памятки
        
        orange 255, 104, 0
        blue 0, 83, 138
        
        //java.library.path
        //System.out.println(System.getProperty("os.name"));
        //System.out.println(System.getProperty("java.library.path"));
        //String separator = File.separator;
        //File.pathSeparator
        //Files.find(new File("C:\\"), 3, "path", basicFileAttributes);
        
        //Path p = Paths.get("C:\\");
        //final int maxDepth = 10;
        //Stream<Path> matches;
        //try {
        //    matches = Files.find(p,10,(path, basicFileAttributes) -> String.valueOf(path).indexOf("1") > 0);
        //    matches.map(path -> path.getFileName()).forEach(System.out::println);
        //} catch (IOException ex) {
        //    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        //}
        
        //System.out.println(File.pathSeparator);
        //System.load("C:\\1q");
        
        //    long size = 0;
        //    
        //      File[] subFiles = dir.listFiles();
        //      for (File file : subFiles) {
        //        if(Files.exists(file.toPath()) && Files.isRegularFile(file.toPath()) && Files.isReadable(file.toPath()) && Files.isExecutable(file.toPath())){
        //          if (file.isFile()) {
        //            size += file.length();
        //          } else {
        //              size += getDirSize(file);
        //          }
        //        }
        //      }
        
        //        String str = "";
        //        
        //        int i = 0;
        //        Enumeration en = System.getProperties().elements();
        //        while(en.hasMoreElements()) {
        //            
        //            //System.out.println("?: " + i++ + " " + en.nextElement());
        //            
        //            str = en.nextElement().toString();
        //            
        //            if(i >= 13) {
        //                
        //                break;
        //                
        //            }
        //            i++;
        //        }
        //        
        //        topLabelFiles[0].setText(str);
        
        Сделать копирование с помощью walkFileTree
        
        */
        
        /*
        Описываю окно
        */
        //Название
        super("Файловый проводник");
        //Возможность закрытия крестиком
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Возможность растягивания
        setResizable(true);
        
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        double hor  = sSize.width;
        double vert = sSize.height;
        
        int percentWidth = 80;
        int percentHeight = 45;
        
        int percentWidth2 = 80;
        int percentHeight2 = 45;
        
        if(frameWidth != hor / 100 * percentWidth) {
            
            frameWidth = (int)(hor / 100 * percentWidth);
            
        }
        if(frameHeight != hor / 100 * percentHeight) {
            
            if((int)(hor / 100 * percentHeight) < vert) {
                
                frameHeight = (int)(hor / 100 * percentHeight);
                
            } else {
                
                while((int)(hor / 100 * percentHeight) < vert) {
                    
                    percentWidth -= 16;
                    percentHeight -= 9;
                    
                }
                
                frameWidth = (int)(hor / 100 * percentWidth);
                frameHeight = (int)(hor / 100 * percentHeight);
                
                if(percentHeight < 1) {
                    
                    frameWidth = (int)(vert / 100 * percentWidth2);
                    frameHeight = (int)(vert / 100 * percentHeight2);
                    
                }
                
            }
        }
        
        //setSize(frameWidth, frameHeight);
        basicPanel.setPreferredSize(new Dimension(frameWidth, frameHeight));
        
        int vert2 = sSize.height / 100 * 30;
        int hor2  = sSize.width / 100 * 40;
        
//        if((frameWidth - 400 - 200 - 40) >= 120) {
//        
//            tabWidth = 140;
//        
//        } else {
//            
//            tabWidth = (frameWidth - 400 - 200 - 40);
//            
//        }
//        
//        tabHeight = 28;
//        
//        fileSheetItem.add(0);
//        dirsCash.add(new ArrayList<ArrayList<String>>());
//        antiDirsCash.add(new ArrayList<ArrayList<String>>());
//        filesList.add(new ArrayList<ArrayList<String>>());
//        filesList.get(0).add(new ArrayList<String>());
//        filesList.get(0).add(new ArrayList<String>());
//        
//        dirsCash.get(0).add(new ArrayList<String>());//  Info
//        dirsCash.get(0).add(new ArrayList<String>());//  Type
//        
//        antiDirsCash.get(0).add(new ArrayList<String>());//  Info
//        antiDirsCash.get(0).add(new ArrayList<String>());//  Type
//        
//        TabButton button = new TabButton("Компьютер", tabWidth, tabHeight);
//        
//        button.setName(Integer.toString(0));
//        
//        button.setFont(new Font("Calibri", Font.BOLD, 14));
//        
//        button.setFocusable(false);
//        
//        button.setBounds(20, 12, tabWidth, tabHeight);
//        
//        button.setBackground(new Color(230, 230, 230));
//                
//        button.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if(e.getButton() == MouseEvent.BUTTON1) {
//                    
//                    lastTab = Integer.parseInt(button.getName());
//                    ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
//                            almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
//                            iniclizButton(almostEmptyList);
//                     for(int i = 0; i < tabbedButtons.size(); i++) {
//
//                        tabbedButtons.get(i).setBackground(Color.WHITE);
//
//                    }
//                    button.setBackground(new Color(230, 230, 230));
//                    System.out.println("tb: " + lastTab);
//                    
//                }
//                
//            }
//            @Override
//            public void mousePressed(MouseEvent e) {
//                
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
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
//        
//        tabbedButtons.add(button);
//        tabbedPanel.add(tabbedButtons.get(0));
        
//        mainTimer.start();
        
        setFocusable(true);
        
        Image forDirectoryButton = new ImageIcon("C:\\Users\\Хозяин\\Documents\\NetBeansProjects\\FileManager\\src\\Other resources\\NavigatorButton obrazec.png").getImage().getScaledInstance(frameHeight / 100 * 16, frameHeight / 100 * 16, Image.SCALE_SMOOTH);
        
        /*
        Делаю Менеджеры компоновки
        */
        basicPanel.setLayout(null);
        morePanel.setLayout(new GridLayout(3, 1, 0, 0));
        
        /*
        Украшаю интерфейс программы
        */
        
        /*
        Не понмю что делает
        Пробовал убирать, в итоге ничего не изменялось, не нашёл где используется
        Не знаю для чего делал, вроде бы как для окошек Создания папки и переименовывания
        */
        JDialog createNewDirDialog = new JDialog(UI.this, "Проводник", true);
        JPanel createnewDirPanel = new JPanel();
        createNewDirDialog.add(createnewDirPanel);
        
        /*
        Устанавливаю иконку для программы
        */
        
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(new File("src\\Resources\\Special resources\\Icon.png").getAbsolutePath()));
        
        pathMaster = new PathMaster(this);
        //mainFilesPanel = new MainFilesPanel(this);
        //tabbedPanel = new TabbedPanel(this);
        buttonsPanel = new ButtonsPanel(this);
        searchTextField = new SearchTextField(this, "Поиск");
        navigationButtonsPanel = new NavigationButtonsPanel(this);
        informationAboutObjectsPanel = new InformationAboutObjectsPanel(this);

        addComponentListener(new ComponentAdapter() {  
            @Override
            public void componentResized(ComponentEvent evt) {
                
                int cpWidth = getContentPane().getWidth();
                int cpHeight = getContentPane().getHeight();
                
                navigationButtonsPanel.setBounds(0, 0, 336, 45);
                tabbedPanel.setBounds(336, 0, (cpWidth - 336 - 168), 45);
                searchTextField.setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                sort.setBounds(10, 50, 316, 45);
                mainFilesPanel.setBounds(0, 45, cpWidth, (cpHeight - 45));
                buttonsPanel.setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                morePanel.setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
                revalidate();
                repaint();
                
            }
        });
        
        //basicPanel.setBounds(0, 0, frameWidth, frameHeight);
        //startPanel.setBounds(0, 0, frameWidth, frameHeight);
        
        path.addKeyListener(new KeyAdapter() {
          @Override
          public void keyPressed(KeyEvent e){
              char symvol = e.getKeyChar();
              if(symvol == KeyEvent.VK_ENTER){
                  String pathFromText = path.getText();
                  //path.setFocusable(false);
                  //search.setFocusable(false);
                  if(new File(pathFromText).exists()){
                  
                  pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(0).add(pathFromText);
                  pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(1).add("StartPath");    
                  
                  if(new File(pathFromText).isDirectory()){
                  String backDir = pathFromText;
                  String[] objects = new File(backDir).list();
                  ArrayList<String> backRootModel = new ArrayList<>();
                    
                    for(String str : objects){
                        File checkFile = new File(backDir, str);
                        
                        if(!navigationButtonsPanel.getHiddenDirectory()) {
                        if(!checkFile.isHidden()){
                            if(checkFile.isDirectory()){
                                backRootModel.add(str);
                            } else {
                                backRootModel.add(/*"файл-" + */str);
                            }
                        }
                        } else if(navigationButtonsPanel.getHiddenDirectory()) {
                            if(checkFile.isDirectory()){
                                backRootModel.add(str);
                            } else {
                                backRootModel.add(/*"файл-" + */str);
                            }
                        }
                    }
                    
                    pathMaster.getFilesList().get(pathMaster.getLastTab()).get(0).removeAll(pathMaster.getFilesList().get(pathMaster.getLastTab()).get(0));
                    pathMaster.getFilesList().get(pathMaster.getLastTab()).get(1).removeAll(pathMaster.getFilesList().get(pathMaster.getLastTab()).get(1));
                    
                    pathMaster.getFilesList().get(pathMaster.getLastTab()).get(0).addAll(backRootModel);
                        
                    for(int i = 0; i < pathMaster.getFilesList().get(pathMaster.getLastTab()).get(0).size(); i++) {
                            
                        pathMaster.getFilesList().get(pathMaster.getLastTab()).get(1).add("ForPath");
                            
                    }
                    ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                    almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                    mainFilesPanel.iniclizButton(almostEmptyList);
                  } else {
                  Desktop desktop = null;
                  if (Desktop.isDesktopSupported()) {
                  desktop = Desktop.getDesktop();
                }

                try {
                  desktop.open(new File(pathFromText));
                  } catch (IOException ioe) {
                  ioe.printStackTrace();
                  }
                }
                  //path.setFocusable(true);
                  //search.setFocusable(true);
              }
              }
          }
        });
        
        sort.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent event) {
                if(event.getStateChange() == ItemEvent.SELECTED) {
                    
                    if(event.getItem().toString().equals("По умолчанию")) {
                        
                        sortStr = "По умолчанию";
                        
                        ArrayList <String> realCashC = new ArrayList<>();
                    String fullPathC = "";

                    if(pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(0).size() > 0) {

                                    ArrayList <String> realCashC2 = new ArrayList<>();
                    String fullPathC2 = "";

                    if(pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(0).size() > 0) {

                                    if(pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(1).get(pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(1).size() - 1).equals("StartCash") || pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(1).get(pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(1).size() - 1).equals("Cash")){
                                        for(int j = 0; j < pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(0).size(); j++){
                                            if(pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(1).get(j).equals("StartCash") || pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(1).get(j).equals("Cash")){
                                                realCashC.add(pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(0).get(j));
                                            }
                                        }
                                        fullPathC = pathMaster.pathBuilder(realCashC);
                                    } else if(pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(1).get(pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(1).size() - 1).equals("StartPath") || pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(1).get(pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(1).size() - 1).equals("PathCash")){
                                        for(int j = 0; j < pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(0).size(); j++){
                                            if(pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(1).get(j).equals("StartPath") || pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(1).get(j).equals("PathCash")){
                                                realCashC.add(pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(0).get(j));
                                            }
                                        }
                                        fullPathC = pathMaster.pathBuilder(realCashC);
                                    } else if(pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(1).get(pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(1).size() - 1).equals("StartSearch") || pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(1).get(pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(1).size() - 1).equals("SearchCash")){
                                        for(int j = 0; j < pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(0).size(); j++){
                                            if(pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(1).get(j).equals("StartSearch") || pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(1).get(j).equals("SearchCash")){
                                                realCashC.add(pathMaster.getDirsCash().get(pathMaster.getLastTab()).get(0).get(j));
                                            }
                                        }

                                        fullPathC2 = pathMaster.pathBuilder(realCashC);

                                    }


                    File selectedFileC2;
                    selectedFileC2 = new File(fullPathC2);
                        
                        String backDir = selectedFileC2.toString();
                        String[] objects = new File(backDir).list();
                        if(objects != null) {
                        ArrayList<String> backRootModel = new ArrayList<>();

                          for(String str : objects){
                              File checkFile = new File(backDir, str);

                              if(!navigationButtonsPanel.getHiddenDirectory()){
                              if(!checkFile.isHidden()){
                                  if(checkFile.isDirectory()){
                                      backRootModel.add(str);
                                  } else {
                                      backRootModel.add(/*"файл-" + */str);
                                  }
                              }
                              } else if(navigationButtonsPanel.getHiddenDirectory()){
                                  if(checkFile.isDirectory()){
                                      backRootModel.add(str);
                                  } else {
                                      backRootModel.add(/*"файл-" + */str);
                                  }
                              }
                          }
                          
                          pathMaster.getFilesList().get(pathMaster.getLastTab()).get(0).removeAll(pathMaster.getFilesList().get(pathMaster.getLastTab()).get(0));
                          pathMaster.getFilesList().get(pathMaster.getLastTab()).get(1).removeAll(pathMaster.getFilesList().get(pathMaster.getLastTab()).get(1));

                          pathMaster.getFilesList().get(pathMaster.getLastTab()).get(0).addAll(backRootModel);

                          for(int i = 0; i < pathMaster.getFilesList().get(pathMaster.getLastTab()).get(0).size(); i++) {

                              pathMaster.getFilesList().get(pathMaster.getLastTab()).get(1).add("ForPath");

                          }
                          
                        }
                        
                    }
                    
                    }
                        
                    } else if(event.getItem().toString().equals("Имя по возрастанию")) {
                        
                        sortStr = "Имя по возрастанию";
                        
                        System.out.println("1: " + pathMaster.getFilesList().get(pathMaster.getLastTab()).get(0));
                        Collections.sort(pathMaster.getFilesList().get(pathMaster.getLastTab()).get(0), Comparator.comparing(String::toString));
                        System.out.println("2: " + pathMaster.getFilesList().get(pathMaster.getLastTab()).get(0));
                        
                    } else if(event.getItem().toString().equals("Имя по убыванию")) {
                        
                        sortStr = "Имя по убыванию";
                        
                        System.out.println("1: " + pathMaster.getFilesList().get(pathMaster.getLastTab()).get(0));
                        Collections.sort(pathMaster.getFilesList().get(pathMaster.getLastTab()).get(0), Comparator.comparing(String::toString));
                        System.out.println("2: " + pathMaster.getFilesList().get(pathMaster.getLastTab()).get(0));
                        List<String> tempFL = new ArrayList<>();
                        tempFL.addAll(pathMaster.getFilesList().get(pathMaster.getLastTab()).get(0));
                        System.out.println("tempFL" + tempFL);
                        pathMaster.getFilesList().get(pathMaster.getLastTab()).get(0).removeAll(pathMaster.getFilesList().get(pathMaster.getLastTab()).get(0));
                        System.out.println("tempFL.size(): " + tempFL.size());
                        for(int i = tempFL.size() - 1; i > 0; i--) {
                            
                            pathMaster.getFilesList().get(pathMaster.getLastTab()).get(0).add(tempFL.get(i));
                            System.out.println("teeeeem123: " + tempFL.get(i));
                        }
                        System.out.println("3: " + pathMaster.getFilesList().get(pathMaster.getLastTab()).get(0));
                    }
                    
                    pathMaster.getFilesList().get(pathMaster.getLastTab()).get(1).removeAll(pathMaster.getFilesList().get(pathMaster.getLastTab()).get(1));

                    for(int i = 0; i < pathMaster.getFilesList().get(pathMaster.getLastTab()).get(0).size(); i++) {

                        pathMaster.getFilesList().get(pathMaster.getLastTab()).get(1).add("ForPath");
                        
                        
                    }
                    
                    ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
                    almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
                    mainFilesPanel.iniclizButton(almostEmptyList);
                    
                    System.out.println("1qaz");
                
                if(searchTextField.getText().equals("") || searchTextField.getText() == null) {
                    
                    searchTextField.setText("Поиск");
                    searchTextField.setForeground(new Color(37, 83, 185));
                    
                }
                
                if(mainFilesPanel.getPrivateButton() != null) {
                    mainFilesPanel.getPrivateButton().remove(mainFilesPanel.getPrivateButton());
                }
                
                for(int i = 0; i < (mainFilesPanel.getTopFiles().length - 1); i++){
                    
                    if(mainFilesPanel.getTopFiles()[i] != null) {
                        
                        mainFilesPanel.getTopFiles()[i].setBorderPainted(false);
                        
                    }
                    
                }
                
                int cpWidth = getContentPane().getWidth();
                int cpHeight = getContentPane().getHeight();
                
                basicPanel.removeAll();
                
                navigationButtonsPanel.setBounds(0, 0, 336, 45);
                tabbedPanel.setBounds(336, 0, (cpWidth - 336 - 168), 45);
                searchTextField.setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                sort.setBounds(10, 50, 316, 45);
                mainFilesPanel.setBounds(0, 45, cpWidth, (cpHeight - 45));
                buttonsPanel.setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                morePanel.setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
                basicPanel.add(navigationButtonsPanel);
                basicPanel.add(tabbedPanel);
                basicPanel.add(searchTextField);
                basicPanel.add(sort);
                basicPanel.add(mainFilesPanel);
                
                revalidate();
                repaint();
                    
                }
            }
        });
        
        basicPanel.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                
                System.out.println("1qaz");
                
                if(searchTextField.getText().equals("") || searchTextField.getText() == null) {
                    
                    searchTextField.setText("Поиск");
                    searchTextField.setForeground(new Color(37, 83, 185));
                    
                }
                
                if(mainFilesPanel.getPrivateButton() != null) {
                    mainFilesPanel.getPrivateButton().remove(mainFilesPanel.getPrivateButton());
                }
                
                for(int i = 0; i < (mainFilesPanel.getTopFiles().length - 1); i++){
                    
                    if(mainFilesPanel.getTopFiles()[i] != null) {
                        
                        mainFilesPanel.getTopFiles()[i].setBorderPainted(false);
                        
                    }
                    
                }
                
                int cpWidth = getContentPane().getWidth();
                int cpHeight = getContentPane().getHeight();
                
                basicPanel.removeAll();
                
                navigationButtonsPanel.setBounds(0, 0, 336, 45);
                tabbedPanel.setBounds(336, 0, (cpWidth - 336 - 168), 45);
                searchTextField.setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                sort.setBounds(10, 50, 316, 45);
                mainFilesPanel.setBounds(0, 45, cpWidth, (cpHeight - 45));
                buttonsPanel.setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                morePanel.setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
                basicPanel.add(navigationButtonsPanel);
                basicPanel.add(tabbedPanel);
                basicPanel.add(searchTextField);
                basicPanel.add(sort);
                basicPanel.add(mainFilesPanel);
                
                revalidate();
                repaint();
                
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                
                System.out.println("1qaz");
                
                if(searchTextField.getText().equals("") || searchTextField.getText() == null) {
                    
                    searchTextField.setText("Поиск");
                    searchTextField.setForeground(new Color(37, 83, 185));
                    
                }
                
                if(mainFilesPanel.getPrivateButton() != null) {
                    mainFilesPanel.getPrivateButton().remove(mainFilesPanel.getPrivateButton());
                }
                
                for(int i = 0; i < (mainFilesPanel.getTopFiles().length - 1); i++){
                    
                    if(mainFilesPanel.getTopFiles()[i] != null) {
                        
                        mainFilesPanel.getTopFiles()[i].setBorderPainted(false);
                        
                    }
                    
                }
                
                int cpWidth = getContentPane().getWidth();
                int cpHeight = getContentPane().getHeight();
                
                basicPanel.removeAll();
                
                navigationButtonsPanel.setBounds(0, 0, 336, 45);
                tabbedPanel.setBounds(336, 0, (cpWidth - 336 - 168), 45);
                searchTextField.setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                sort.setBounds(10, 50, 316, 45);
                mainFilesPanel.setBounds(0, 45, cpWidth, (cpHeight - 45));
                buttonsPanel.setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                morePanel.setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
                basicPanel.add(navigationButtonsPanel);
                basicPanel.add(tabbedPanel);
                basicPanel.add(searchTextField);
                basicPanel.add(sort);
                basicPanel.add(mainFilesPanel);
                
                revalidate();
                repaint();
                
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                
                System.out.println("1qaz");
                
                if(searchTextField.getText().equals("") || searchTextField.getText() == null) {
                    
                    searchTextField.setText("Поиск");
                    searchTextField.setForeground(new Color(37, 83, 185));
                    
                }
                
                if(mainFilesPanel.getPrivateButton() != null) {
                    mainFilesPanel.getPrivateButton().remove(mainFilesPanel.getPrivateButton());
                }
                
                for(int i = 0; i < (mainFilesPanel.getTopFiles().length - 1); i++){
                    
                    if(mainFilesPanel.getTopFiles()[i] != null) {
                        
                        mainFilesPanel.getTopFiles()[i].setBorderPainted(false);
                        
                    }
                    
                }
                
                int cpWidth = getContentPane().getWidth();
                int cpHeight = getContentPane().getHeight();
                
                basicPanel.removeAll();
                
                navigationButtonsPanel.setBounds(0, 0, 336, 45);
                tabbedPanel.setBounds(336, 0, (cpWidth - 336 - 168), 45);
                searchTextField.setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                sort.setBounds(10, 50, 316, 45);
                mainFilesPanel.setBounds(0, 45, cpWidth, (cpHeight - 45));
                buttonsPanel.setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                morePanel.setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
                basicPanel.add(navigationButtonsPanel);
                basicPanel.add(tabbedPanel);
                basicPanel.add(searchTextField);
                basicPanel.add(sort);
                basicPanel.add(mainFilesPanel);
                
                revalidate();
                repaint();
                
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                
            }
            
        });
        
        settingsButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    
                    int cpWidth = getContentPane().getWidth();
                    int cpHeight = getContentPane().getHeight();
                    
                    basicPanel.removeAll();
                    
                    settingsPanel.setBounds((int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), 0, (int)(cpWidth - (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5)))), (int)(cpHeight));
                    morePanel.setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                    basicPanel.add(settingsPanel);
                    basicPanel.add(morePanel);
                    
                    navigationButtonsPanel.setBounds(0, 0, 336, 45);
                    tabbedPanel.setBounds(336, 0, (cpWidth - 336 - 168), 45);
                    searchTextField.setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                    sort.setBounds(10, 50, 316, 45);
                    mainFilesPanel.setBounds(0, 45, cpWidth, (cpHeight - 45));
                    buttonsPanel.setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                    morePanel.setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                    
                    basicPanel.add(navigationButtonsPanel);
                    basicPanel.add(tabbedPanel);
                    basicPanel.add(searchTextField);
                    basicPanel.add(sort);
                    basicPanel.add(mainFilesPanel);

                    revalidate();
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
        
        informationButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    
                    int cpWidth = getContentPane().getWidth();
                    int cpHeight = getContentPane().getHeight();

                    basicPanel.removeAll();

                    informationPanel.setBounds((int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), 0, (int)(cpWidth - (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5)))), (int)(cpHeight));
                    morePanel.setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                    basicPanel.add(informationPanel);
                    basicPanel.add(morePanel);

                    navigationButtonsPanel.setBounds(0, 0, 336, 45);
                    tabbedPanel.setBounds(336, 0, (cpWidth - 336 - 168), 45);
                    searchTextField.setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
                    sort.setBounds(10, 50, 316, 45);
                    mainFilesPanel.setBounds(0, 45, cpWidth, (cpHeight - 45));
                    buttonsPanel.setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
                    morePanel.setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));

                    basicPanel.add(navigationButtonsPanel);
                    basicPanel.add(tabbedPanel);
                    basicPanel.add(searchTextField);
                    basicPanel.add(sort);
                    basicPanel.add(mainFilesPanel);

                    revalidate();
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
        
        /*
        Потом надо будет сделать кнопку для разархивирования
        */
        
        int cpWidth = getContentPane().getWidth();
        int cpHeight = getContentPane().getHeight();
        
        navigationButtonsPanel.setBounds(0, 0, 336, 45);
        tabbedPanel.setBounds(336, 0, (cpWidth - 336 - 168), 45);
        searchTextField.setBounds((336 + (cpWidth - 336 - 168)), 0, 168, 45);
        sort.setBounds(10, 50, 316, 45);
        mainFilesPanel.setBounds(0, 45, cpWidth, (cpHeight - 45));
        buttonsPanel.setBounds((int)(cpWidth - cpWidth / 5 * 1), 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 1 - (int)(cpWidth - cpWidth / 5 * 1))), (int)(cpHeight));
        morePanel.setBounds(0, 0, (int)(cpWidth / 5 * 1 + (cpWidth - cpWidth / 5 * 5 - (int)(cpWidth - cpWidth / 5 * 5))), (int)(cpHeight));
                
        path.setBackground(new Color(52, 112, 255));
        tabbedPanel.setBackground(new Color(52, 112, 255));
        searchTextField.setBackground(new Color(52, 112, 255));
        sort.setBackground(Color.WHITE);
        mainFilesPanel.setBackground(Color.WHITE);
        buttonsPanel.setBackground(Color.WHITE);
        morePanel.setBackground(Color.WHITE);
        informationAboutObjectsPanel.setBackground(new Color(35, 146, 232));
        
        searchTextField.setForeground(new Color(37, 83, 185));
        
        mainFilesPanel.setOpaque(true);
        
        searchTextField.setHorizontalAlignment(SwingConstants.CENTER);
        
        sort.setFocusable(false);
        
        path.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        morePanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        
        path.setFont(new Font("Calibri", Font.BOLD, 20));
        searchTextField.setFont(new Font("Calibri", Font.BOLD, 20));
        
        informationAboutObjectsPanel.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 2, new Color(180, 180, 180)));
        
        morePanel.add(path);
        morePanel.add(settingsButton);
        morePanel.add(informationButton);
        
        double cpaneWidth = 1;
        double cpaneHeight = 1;

        if(getContentPane().getWidth() > 0) {

            cpaneWidth = getContentPane().getWidth();
            cpaneHeight = getContentPane().getHeight();

        } else {

            cpaneWidth = frameWidth;
            cpaneHeight = frameHeight;

        }
        
        int dimension = (int)(33.6);
        
        Image settingsImage = new ImageIcon(new File("src\\Resources\\Button resources\\CopyInButton.png").getAbsolutePath()).getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        Image informationImage = new ImageIcon(new File("src\\Resources\\Button resources\\MoveButton.png").getAbsolutePath()).getImage().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
        settingsButton.setIcon(new ImageIcon(settingsImage));
        informationButton.setIcon(new ImageIcon(informationImage));
                
        basicPanel.add(navigationButtonsPanel);
        //basicPanel.add(path);
        basicPanel.add(tabbedPanel);
        basicPanel.add(searchTextField);
        basicPanel.add(sort);
        basicPanel.add(mainFilesPanel);
        //System.out.println(true ? false : 9);
        ArrayList<ArrayList<String>> almostEmptyList = new ArrayList<>();
        almostEmptyList.add(new ArrayList<>(Arrays.asList(new String[]{"\\/:*?\"<>|"})));
        mainFilesPanel.iniclizButton(almostEmptyList);
        
        System.out.println("cpw: " + getContentPane().getWidth() + "cph: " + getContentPane().getHeight());
        basicPanel.revalidate();
        basicPanel.repaint();
        
        getContentPane().add(basicPanel);
        
        //Размер
        
        pack();
        
        //Место размещения на экране
        setLocationRelativeTo(null);
        
        //Делаю окно видимым
        setVisible(true);
        
        Dimension sSize2 = Toolkit.getDefaultToolkit().getScreenSize();
        
        double hor3  = sSize2.width;
        double vert3 = sSize2.height;
        
        int percentWidth3 = 64;
        int percentHeight3 = 36;
        
        int percentWidth4 = 64;
        int percentHeight4 = 36;
        
        if(frameWidthMin != hor3 / 100 * percentWidth3) {
            
            frameWidthMin = (int)(hor3 / 100 * percentWidth3);
            
        }
        if(frameHeightMin != hor3 / 100 * percentHeight3) {
            
            if((int)(hor3 / 100 * percentHeight3) < vert3) {
                
                frameHeightMin = (int)(hor3 / 100 * percentHeight3);
                
            } else {
                
//                int i = 0;
                
                while((int)(hor3 / 100 * percentHeight3) < vert3) {
                    
                    percentWidth3 -= 16;
                    percentHeight3 -= 9;
                    
//                    i++;
//                    
//                    if(i % 3 == 0 && i % 4 == 0) {
//                        
//                        percentWidth -= i / 3;
//                        percentHeight -= i / 4;
//                        
//                    }
                    
                }
                
                frameWidthMin = (int)(hor3 / 100 * percentWidth3);
                frameHeightMin = (int)(hor3 / 100 * percentHeight3);
                
                if(percentHeight3 < 1) {
                    
                    frameWidthMin = (int)(vert3 / 100 * percentWidth4);
                    frameHeightMin = (int)(vert3 / 100 * percentHeight4);
                    
                }
                
            }
        }
        
        setMinimumSize(new Dimension((int)(frameWidthMin + (getWidth() - getContentPane().getWidth())), (int)(frameHeightMin + (getHeight() - getContentPane().getHeight()))));
        
    }
    
    public int getFrameWidth() {
        
        return(frameWidth);
        
    }
    
    public void setFrameWidth(int frameWidth) {
        
        this.frameWidth = frameWidth;
        
    }
    
    public int getFrameHeight() {
        
        return(frameHeight);
        
    }
    
    public void setFrameHeight(int frameHeight) {
        
        this.frameHeight = frameHeight;
        
    }

    public String getSortStr() {
        
        return(sortStr);
        
    }
    
    public void setSortStr(String sortStr) {
        
        this.sortStr = sortStr;
        
    }
    
    public JLayeredPane getBasicPanel() {
        
        return(basicPanel);
        
    }
    
    public void setBasicPanel(JLayeredPane basicPanel) {
        
        this.basicPanel = basicPanel;
        
    }

    public JPanel getMorePanel() {
        
        return(morePanel);
        
    }
    
    public void setMorePanel(JPanel morePanel) {
        
        this.morePanel = morePanel;
        
    }

    public JComboBox getSort() {
        
        return(sort);
        
    }
    
    public void setSort(JComboBox sort) {
        
        this.sort = sort;
        
    }

    public JTextField getPath() {
        
        return(path);
        
    }

    public void setPath(JTextField path) {
        
        this.path = path;
        
    }
    
    public TabbedPanel getTabbedPanel() {
        
        return(tabbedPanel);
        
    }
    
    public void setTabbedPanel(TabbedPanel tabbedPanel) {
        
        this.tabbedPanel = tabbedPanel;
        
    }
    
    public PathMaster getPathMaster() {
        
        return(pathMaster);
        
    }
    
    public void setPathMaster(PathMaster pathMaster) {
        
        this.pathMaster = pathMaster;
        
    }
    
    public ButtonsPanel getButtonsPanel() {
        
        return(buttonsPanel);
        
    }
    
    public void setButtonsPanel(ButtonsPanel buttonsPanel) {
        
        this.buttonsPanel = buttonsPanel;
        
    }
    
    public MainFilesPanel getMainFilesPanel() {
        
        return(mainFilesPanel);
        
    }
    
    public void setMainFilesPanel(MainFilesPanel mainFilesPanel) {
        
        this.mainFilesPanel = mainFilesPanel;
        
    }
    
    public SearchTextField getSearchTextField() {
        
        return(searchTextField);
        
    }
    
    public void setSearchTextField(SearchTextField searchTextField) {
        
        this.searchTextField = searchTextField;
        
    }
    
    public NavigationButtonsPanel getNavigationButtonsPanel() {
        
        return(navigationButtonsPanel);
        
    }
    
    public void setNavigationButtonsPanel(NavigationButtonsPanel navigationButtonsPanel) {
        
        this.navigationButtonsPanel = navigationButtonsPanel;
        
    }
    
    public InformationAboutObjectsPanel getInformationAboutObjectsPanel() {
        
        return(informationAboutObjectsPanel);
        
    }
    
    public void setInformationAboutObjectsPanel(InformationAboutObjectsPanel informationAboutObjectsPanel) {
        
        this.informationAboutObjectsPanel = informationAboutObjectsPanel;
        
    }
    
}
