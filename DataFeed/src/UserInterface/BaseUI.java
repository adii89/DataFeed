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
import Config.Constants;
import DataAccess.Database;
import DataProcessing.ProcessingProvider;
import DataProcessing.Scheduler;
import Exceptions.ApplicationException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
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
        JTextArea txtSuccess;
        @Override
        public void actionPerformed(ActionEvent e) {
            if (SelectedFile != null) {
                ProcessingProvider provider = new ProcessingProvider(SelectedFile);
                provider.Process();
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
       //aboutItem.addActionListener(new AboutDialog(this));
       aboutItem.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
               JOptionPane.showMessageDialog(null, "<html><h1><i>PFU Enrollment Application<br>Capstone Project<br>Northwestern</i></h1><hr>Adrian Krzeszkiewicz<br>Valerie Torres<br> Mina Aitelhadj<br> Erik Miller<br> Horace Flournoy</html>");
           }
       });
       fMenu.add(openItem);
       fMenu.add(closeItem);
       aMenu.add(aboutItem);
       mBar.add(fMenu);
       mBar.add(aMenu);
       return mBar;
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
        btnProcessFile.addActionListener(new ProcessFile());
        processFilePanel.add(btnProcessFile);
        return processFilePanel;
    }
    
    private JPanel GetProcessSchedulePanel() {
        JPanel processSchedulePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        processSchedulePanel.setPreferredSize(new Dimension(550, 90));
        processSchedulePanel.setBorder(BorderFactory.createLineBorder(Color.black));
        JButton btnProcessSchedule = new JButton("Process Scheduling");
        btnProcessSchedule.setPreferredSize(new Dimension(500, 75));
        btnProcessSchedule.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (ValidateData()) {
                    try {
                        Scheduler.Start();
                    } catch (Exception ex) {
                        Logger.ErrorLog.LogError(ex);
                        JOptionPane.showMessageDialog(null, "Oooooops .... Something Went Wrong ... \n No Worries, though, Our Guys Were Notified.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Some Pieces Of Data Are Missing \n Did You Forget To Upload a File?");
                }
            }
        });
        processSchedulePanel.add(btnProcessSchedule);
        return processSchedulePanel;
    }
    
    private boolean ValidateData(){
        boolean bError = false;
        String SQL = "";
        Database DB = new Database();
        ResultSet rs;
        int DataCount = 0;
        try {
            //Check CampusBuilding
            SQL = "SELECT COUNT(BuildingId) FROM CampusBuilding";
            rs = DB.SelectSQL(SQL);
            if (rs.next()) {
                DataCount = rs.getInt(1);
                if (DataCount == 0)
                    //No Data
                    bError = true;
            } else {
                //No Data
                bError = true;
            }
            //Check BuildingRoom
            SQL = "SELECT COUNT(RoomId) FROM BuildingRoom";
            rs = DB.SelectSQL(SQL);
            if (rs.next()) {
                DataCount = rs.getInt(1);
                if (DataCount == 0)
                    //No Data
                    bError = true;
            } else {
                //No Data
                bError = true;
            }
            //Check Department
            SQL = "SELECT COUNT(DepartmentId) FROM Department";
            rs = DB.SelectSQL(SQL);
            if (rs.next()) {
                DataCount = rs.getInt(1);
                if (DataCount == 0)
                    //No Data
                    bError = true;
            } else {
                //No Data
                bError = true;
            }
            //Check PfuUser
            SQL = "SELECT COUNT(UserId) FROM PfuUser";
            rs = DB.SelectSQL(SQL);
            if (rs.next()) {
                DataCount = rs.getInt(1);
                if (DataCount == 0)
                    //No Data
                    bError = true;
            } else {
                //No Data
                bError = true;
            }
            //Check PfuUserPreference
            SQL = "SELECT COUNT(PreferenceId) FROM PfuUserPreference";
            rs = DB.SelectSQL(SQL);
            if (rs.next()) {
                DataCount = rs.getInt(1);
                if (DataCount == 0)
                    //No Data
                    bError = true;
            } else {
                //No Data
                bError = true;
            }
            //Check PfuUserCampusPreference
            SQL = "SELECT COUNT(CampusId) FROM PfuUserCampusPreference";
            rs = DB.SelectSQL(SQL);
            if (rs.next()) {
                DataCount = rs.getInt(1);
                if (DataCount == 0)
                    //No Data
                    bError = true;
            } else {
                //No Data
                bError = true;
            }
            //Check PreviousEnrollment
            SQL = "SELECT COUNT(*) FROM PreviousEnrollment";
            rs = DB.SelectSQL(SQL);
            if (rs.next()) {
                DataCount = rs.getInt(1);
                if (DataCount == 0)
                    //No Data
                    bError = true;
            } else {
                //No Data
                bError = true;
            }
            //Check Section
            SQL = "SELECT COUNT(SectionId) FROM Section";
            rs = DB.SelectSQL(SQL);
            if (rs.next()) {
                DataCount = rs.getInt(1);
                if (DataCount == 0)
                    //No Data
                    bError = true;
            } else {
                //No Data
                bError = true;
            }
        } catch (ApplicationException ex) {
            Logger.ErrorLog.LogError(ex);
            bError = true;
        } catch (SQLException ex) {
            Logger.ErrorLog.LogError(ex);
            bError = true;
        }
        
        return !bError;
    }
}


