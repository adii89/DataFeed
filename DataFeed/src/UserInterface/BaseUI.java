/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import Config.Constants;
import DataProcessing.ProcessingProvider;
import java.util.Arrays;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

/**
 *
 * @author Adi
 */
public class BaseUI extends JFrame implements Runnable {
    JFileChooser File = new JFileChooser();
    JLabel lblFileName = new JLabel();
    JLabel lblFileNameError = new JLabel();
    File SelectedFile;
    JButton btnProcessFile = new JButton("Process Selected File");
    @Override
    public void run() {
       this.setTitle("PFU Enrollment Scheduler");
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setResizable(false);
       
       Container mainContainer = this.getContentPane();
       mainContainer.setLayout(new BorderLayout());
       
       mainContainer.add(GetMenuBar(), BorderLayout.NORTH); 
       
       mainContainer.add(GetCenterPanel(), BorderLayout.CENTER);
       this.setPreferredSize(new Dimension(600, 350));
       this.pack();   
       this.setLocationRelativeTo(null);
       this.setVisible(true);
    }
    
    public class OpenFile implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        int optionSelected = File.showOpenDialog(BaseUI.this);
        if (optionSelected == JFileChooser.APPROVE_OPTION){
            SelectedFile = File.getSelectedFile();
            lblFileName.setText(SelectedFile.getName());
            if (IsValidFile(SelectedFile)){
                Color c = new Color(0x0B8C38);
                lblFileNameError.setForeground(c);
                lblFileNameError.setText("The File Is Valid");
                btnProcessFile.setEnabled(true);
            } else {
                lblFileNameError.setForeground(Color.RED);
                lblFileNameError.setText("The File Is Not Valid - Please Select an Appropriate File");
                btnProcessFile.setEnabled(false);
            }
            
        }
    }
    
    
    
    private boolean IsValidFile(File selectedFile){
        return Arrays.asList(Constants.Filenames).contains(selectedFile.getName());
    }
}
    
    public class CloseApplication implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    
    public class ProcessFile extends JDialog implements ActionListener {
        public ProcessFile(JFrame parentFrame) {
            super(parentFrame, "Process File", true);
            this.setLocationRelativeTo(parentFrame);
            JLabel lblSuccess = new JLabel("Success");
            JTextArea txtSuccess = new JTextArea();
            txtSuccess.setEditable(false);
            txtSuccess.setPreferredSize(new Dimension(225, 200));
            txtSuccess.setLineWrap(true);
            txtSuccess.setWrapStyleWord(true);
            JScrollPane successScroll = new JScrollPane(txtSuccess);
            successScroll.setPreferredSize(new Dimension(225, 200));
            successScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            JLabel lblFail = new JLabel("Failure");
            JTextArea txtFail = new JTextArea();
            txtFail.setPreferredSize(new Dimension(225, 200));
            txtFail.setLineWrap(true);
            txtFail.setWrapStyleWord(true);
            JScrollPane failScroll = new JScrollPane(txtFail);
            failScroll.setPreferredSize(new Dimension(225, 200));
            failScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            //JLabel lblText = new JLabel("<html><h1><i>PFU Enrollment Application<br>Capstone Project<br>Northwestern</i></h1><hr>Adrian Krzeszkiewicz<br>Valerie Torres<br> Mina Aitelhadj<br> Erik Miller<br> Horace Flournoy</html>");
            JButton btnFinish = new JButton("Finish");
            btnFinish.setPreferredSize(new Dimension(475, 35));
            btnFinish.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    setVisible(false);
                }
            });
            JPanel leftPanel = new JPanel();
            leftPanel.setPreferredSize(new Dimension(225, 225));
            leftPanel.add(lblSuccess);
            leftPanel.add(successScroll);
            JPanel rightPanel = new JPanel();
            rightPanel.setPreferredSize(new Dimension(225, 225));
            rightPanel.add(lblFail);
            rightPanel.add(failScroll);
            JPanel p = new JPanel();
            p.add(leftPanel);
            p.add(rightPanel);
            p.add(btnFinish);
            add(p);
            this.setResizable(false);
            setSize(500, 300);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (SelectedFile != null) {
                setVisible(true);
                ProcessingProvider provider = new ProcessingProvider(SelectedFile);
                provider.Process();
            }
        }
    }
    
    private void SetLookAndFeel(){
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                Logger.ErrorLog.LogError(ex);
            }
        }
    }
    
    private Font GetFont(){
        Font font = new Font("Verdana", Font.BOLD, 12);
        return font;
    }
    
    private JMenuBar GetMenuBar() {
       JMenuBar mBar = new JMenuBar();
       //this.setJMenuBar(mBar);
       JMenu fMenu = new JMenu("File");
       JMenu aMenu = new JMenu("About");
       JMenuItem openItem = new JMenuItem("Open File");
       JMenuItem closeItem = new JMenuItem("Close Application");
       JMenuItem aboutItem = new JMenuItem("About");
       openItem.addActionListener(new OpenFile());
       closeItem.addActionListener(new CloseApplication());
       aboutItem.addActionListener(new AboutDialog(this));
       fMenu.add(openItem);
       fMenu.add(closeItem);
       aMenu.add(aboutItem);
       mBar.add(fMenu);
       mBar.add(aMenu);
       return mBar;
    }
    
    private class AboutDialog extends JDialog implements ActionListener {
        public AboutDialog(JFrame parentFrame) {
            super(parentFrame, "About", true);
            this.setLocationRelativeTo(parentFrame);
            JLabel lblText = new JLabel("<html><h1><i>PFU Enrollment Application<br>Capstone Project<br>Northwestern</i></h1><hr>Adrian Krzeszkiewicz<br>Valerie Torres<br> Mina Aitelhadj<br> Erik Miller<br> Horace Flournoy</html>");
            JButton OK = new JButton("OK");
            OK.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    setVisible(false);
                }
            });
            JPanel p = new JPanel();
            p.add(lblText);
            p.add(OK);
            add(p);
            this.setResizable(false);
            setSize(350, 300);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(true);
        }
    }
    
    private JPanel GetCenterPanel(){
        JPanel centerPanel = new JPanel();
       centerPanel.setLayout(new FlowLayout());
       centerPanel.add(GetSelectFilePanel());
       centerPanel.add(GetProcessFilePanel());
       centerPanel.add(GetProcessSchedulePanel());
       return centerPanel;
    }
    
    private JPanel GetSelectFilePanel() {
        JPanel selectFilePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        selectFilePanel.setPreferredSize(new Dimension(550, 100));
        selectFilePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        //JLabel lblOpenFile = new JLabel("SELECT FILE:");
        //lblOpenFile.setPreferredSize(new Dimension(125, 30));
        //lblOpenFile.setFont(GetFont());
        JButton btnOpenFile = new JButton("CLICK HERE TO SELECT A FILE");
        btnOpenFile.setBorder(null);
        btnOpenFile.setPreferredSize(new Dimension(500, 30));
        btnOpenFile.addActionListener(new OpenFile());
        //JLabel lblSelectedFile = new JLabel();
        lblFileName.setPreferredSize(new Dimension(300, 25));
        lblFileName.setHorizontalAlignment(SwingConstants.CENTER);
        lblFileNameError.setPreferredSize(new Dimension(400, 25));
        lblFileNameError.setHorizontalAlignment(SwingConstants.CENTER);
        //selectFilePanel.add(lblOpenFile);
        selectFilePanel.add(btnOpenFile);
        selectFilePanel.add(lblFileName);
        selectFilePanel.add(lblFileNameError);
        return selectFilePanel;
    }
    
    private JPanel GetProcessFilePanel() {
        JPanel processFilePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        processFilePanel.setPreferredSize(new Dimension(550, 90));
        processFilePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        btnProcessFile.setEnabled(false);
        btnProcessFile.setPreferredSize(new Dimension(500, 75));
        btnProcessFile.addActionListener(new ProcessFile(this));
        processFilePanel.add(btnProcessFile);
        return processFilePanel;
    }
    
    private JPanel GetProcessSchedulePanel() {
        JPanel processSchedulePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        processSchedulePanel.setPreferredSize(new Dimension(550, 90));
        processSchedulePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        JButton btnProcessSchedule = new JButton("Process Scheduling");
        btnProcessSchedule.setPreferredSize(new Dimension(500, 75));
        processSchedulePanel.add(btnProcessSchedule);
        return processSchedulePanel;
    }
}


