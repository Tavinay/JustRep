package filemanager;

import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicLong;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InformationAboutObjectsPanel extends JPanel {
    
    /**
     *
     */
    private static final long serialVersionUID = 1215467901345250688L;
    private File dir1 = new File("");
    private File dir2 = new File("");
    private File dir3 = new File("");
    
    private JLabel infoName = new JLabel("Название : ");
    private JLabel infoType = new JLabel("Тип : ");
    private JLabel infoPath = new JLabel("Путь :");
    private JLabel infoSize = new JLabel("Размер : ");
    private JLabel infoDateCreation = new JLabel("Дата создания : ");
    private JLabel infoDateChange = new JLabel("Дата последнего изменения : ");
    private JLabel infoNumberFolders = new JLabel("Количество папок:");
    private JLabel infoNumberFiles = new JLabel("Количество файлов : ");
    
    private Thread th1 = new dirSize(this, "infoSize");
    private Thread th2 = new countFiles(this);
    private Thread th3 = new countDirs(this);
    
    UI ui;
    
    public InformationAboutObjectsPanel(UI ui) {
        
        this.ui = ui;
        
        setLayout(new GridLayout(8, 1, ui.getFrameHeight() / 100, ui.getFrameHeight() / 100));
        
        infoName.setName("infoName");
        infoType.setName("infoType");
        infoPath.setName("infoPath");
        infoSize.setName("infoSize");
        infoDateCreation.setName("infoDateCreation");
        infoDateChange.setName("infoDateChange");
        infoNumberFolders.setName("infoNumberFolders");
        
        add(infoName);
        add(infoType);
        add(infoPath);
        add(infoSize);
        add(infoDateCreation);
        add(infoDateChange);
        add(infoNumberFolders);
        add(infoNumberFiles);
        
    }
    
    public void addThread() {
        
        th1.interrupt();
        th2.interrupt();
        th3.interrupt();
        
        th1 = null;
        th2 = null;
        th3 = null;
        
        th1 = new dirSize(this, "infoSize");
        th2 = new countFiles(this);
        th3 = new countDirs(this);
        
    }
    
    public File getDir1() {
        
        return(dir1);
        
    }
    
    public void setDir1(File dir1) {
        
        this.dir1 = dir1;
        
    }
    
    public File getDir2() {
        
        return(dir2);
        
    }
    
    public void setDir2(File dir2) {
        
        this.dir2 = dir2;
        
    }
    
    public File getDir3() {
        
        return(dir1);
        
    }
    
    public void setDir3(File dir3) {
        
        this.dir3 = dir3;
        
    }
    
    public void setTextInfoName(String text) {
        
        infoName.setText(text);
        
    }
    
    public void setTextInfoType(String text) {
        
        infoType.setText(text);
        
    }
    
    public void setTextInfoPath(String text) {
        
        infoPath.setText(text);
        
    }
    
    public void setTextInfoSize(String text) {
        
        infoSize.setText(text);
        
    }
    
    public void setTextInfoDateCreation(String text) {
        
        infoDateCreation.setText(text);
        
    }
    
    public void setTextInfoDateChange(String text) {
        
        infoDateChange.setText(text);
        
    }
    
    public void setTextInfoNumberFolders(String text) {
        
        infoNumberFolders.setText(text);
        
    }

    public void setTextInfoNumberFiles(String text) {
        
        infoNumberFiles.setText(text);
        
    }
    
    public Thread getTH1() {
        
        return(th1);
        
    }
    
    public void setTH1(Thread th1) {
        
        this.th1 = th1;
        
    }
    
    public void startThread1(String purposeOfValue) {
        
        if(th1 != null) {
            
            if(th1.isInterrupted()) {
                            
                th1 = null;
                th1 = new dirSize(this, purposeOfValue);
                th1.start();
                            
            } else {
                            
                th1.interrupt();
                th1 = null;
                th1 = new dirSize(this, purposeOfValue);
                th1.start();
                            
            }
            
        } else {
                        
            th1 = new dirSize(this, purposeOfValue);
            th1.start();
                        
        }
        
    }
    
    public void startThread2() {
        
        if(th2 != null) {
            
            if(th2.isInterrupted()) {
                            
                th2 = null;
                th2 = new dirSize(this, "infoSize");
                th2.start();
                            
            } else {
                            
                th2.interrupt();
                th2 = null;
                th2 = new dirSize(this, "infoSize");
                th2.start();
                            
            }
            
        } else {
                        
            th2 = new dirSize(this, "infoSize");
            th2.start();    
                        
        }
        
    }
    
    public void startThread3() {
        
        if(th3 != null) {
            
            if(th3.isInterrupted()) {
                            
                th3 = null;
                th3 = new dirSize(this, "infoSize");
                th3.start();
                            
            } else {
                            
                th3.interrupt();
                th3 = null;
                th3 = new dirSize(this, "infoSize");
                th3.start();
                            
            }
            
        } else {
                        
            th3 = new dirSize(this, "infoSize");
            th3.start();
                        
        }
        
    }
    
    public class dirSize extends Thread {
        
        InformationAboutObjectsPanel iaop;
        
        String purposeOfValue;
        
        int realCountSimbol;
        
        dirSize(InformationAboutObjectsPanel iaop, String purposeOfValue) {
            
            super("Thread for UI4");
            
            this.iaop = iaop;
            
            this.purposeOfValue = purposeOfValue;
            
        }
        
        public void run() {
            
            File dir = iaop.dir1;
            
            int msDelay = 1;
            
                final AtomicLong size = new AtomicLong(0);
        
            try {

                Files.walkFileTree(dir.toPath(), new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {

                        size.addAndGet(attrs.size());
                        //fileCount.addAndGet(1);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) {

                        System.out.println("skipped: " + file + " (" + exc + ")");
                        // Skip folders that can't be traversed
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {

                        if (exc != null)
                            System.out.println("had trouble traversing: " + dir + " (" + exc + ")");
                        // Ignore errors traversing a folder
                        //dirCount.addAndGet(1);
                        return FileVisitResult.CONTINUE;
                    }
                });

            } catch (IOException e) {
                throw new AssertionError("walkFileTree will not throw IOException if the FileVisitor does not");
            }
            
            if(purposeOfValue.equals("infoSize")) {

                if(!isInterrupted()) {

                    infoSize.setText("Размер : " + Long.toString(size.get()));

                }
            
            } else if(purposeOfValue.equals("length56")) {
                
                ui.getPathMaster().setLength56(size.get());
                
            }     
            
            iaop.revalidate();
            iaop.repaint();
                
        }
        
    }
    
    public class countFiles extends Thread {
        
        InformationAboutObjectsPanel iaop;
        
        String text;
        
        int realCountSimbol;
        
        countFiles(InformationAboutObjectsPanel iaop) {
            
            super("Thread for UI2");
            
            this.iaop = iaop;
        
        }

        public void run() {
            
            File dir = iaop.dir2;
            
            int msDelay = 1;
            
                final AtomicLong fileCount2 = new AtomicLong(0);
        
        try {
            
            Files.walkFileTree(dir.toPath(), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    fileCount2.getAndIncrement();
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {

                    System.out.println("skipped: " + file + " (" + exc + ")");
                    // Skip folders that can't be traversed
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                    
                    if (exc != null)
                        System.out.println("had trouble traversing: " + dir + " (" + exc + ")");
                    // Ignore errors traversing a folder
                    return FileVisitResult.CONTINUE;
                    
                }
                
            });
            
        } catch (IOException e) {
            throw new AssertionError("walkFileTree will not throw IOException if the FileVisitor does not");
        }
        
        if(!isInterrupted()) {

            infoNumberFiles.setText("Количество файлов : " + Long.toString(fileCount2.get()));
        
        }
                    
                iaop.revalidate();
                iaop.repaint();
            
        }
        
    }
    
    public class countDirs extends Thread {
        
        InformationAboutObjectsPanel iaop;
        
        String text;
        
        int realCountSimbol;
        
        countDirs(InformationAboutObjectsPanel iaop) {
            
            super("Thread for UI3");
            
            this.iaop = iaop;
        
        }

        public void run() {
            
            File dir = iaop.dir3;
            
            int msDelay = 1;
                
            final AtomicLong dirCount2 = new AtomicLong(0);
                
            try {

                    Files.walkFileTree(dir.toPath(), new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult visitFileFailed(Path file, IOException exc) {

                            System.out.println("skipped: " + file + " (" + exc + ")");
                            // Skip folders that can't be traversed
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult postVisitDirectory(Path dir, IOException exc) {

                            if (exc != null)
                                System.out.println("had trouble traversing: " + dir + " (" + exc + ")");
                            // Ignore errors traversing a folder
                            dirCount2.getAndIncrement();
                            return FileVisitResult.CONTINUE;
                        }
                    });

            } catch (IOException e) {
                throw new AssertionError("walkFileTree will not throw IOException if the FileVisitor does not");
            }
            
            if(!isInterrupted()) {
                
                infoNumberFolders.setText("Количество папок : " + Long.toString(dirCount2.get() - 1));

            }
                    
                iaop.revalidate();
                iaop.repaint();
            
        }
        
    }
    
}
