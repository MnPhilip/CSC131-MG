package JCRPG_gui;
import JCPRG_code.GM;



import com.sun.jdi.connect.LaunchingConnector;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

import javax.naming.InterruptedNamingException;
import javax.print.DocFlavor.URL;

import static javax.print.attribute.standard.MediaSize.Engineering.C;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;

@SuppressWarnings("serial")

public class LauncherGUI extends JFrame
{
    JCRPG_code.GM gameMaster = new JCRPG_code.GM();
    JCRPG_code.Player GUIPObj[] = new JCRPG_code.Player[3];
    int textTurner = 0;
    String[] initText = {"JCRPG - DND Story Emulator\nSelect \"Next\" to continue.\n\nCODE CREATED BY:\nSHIVANI BILIMORIA\nNAM NGUYEN\nSARAVJOT SINGH\nJD DAVID\nCATALINA ESCANO\nMATT PHILIP\n",
                            "AVAILABLE CLASSES:\n{1} - Wizard: low health, low defense, very high damage potential.\nThe ultimate glass Cannon\n\n{2} - Barbarian: High health, high defense, medium damage.\n Hearty and hefty, packing a staggering punch\n\n" + 
                        "{3} - Bard: All-arounder build, medium-level damage possibilites with middle-of-the-road health and defense. Perfect for a neutral adventurer"};
    
        private JButton nextButton;
        private JButton quitButton;
        private JButton resetButton;

        private JButton P1Role;
        private JButton P2Role;
        private JButton P3Role;

        private JTextField P1Name;
        private JTextField P2Name;
        private JTextField P3Name;

        private JLabel P1Label;
        private JLabel P2Label;
        private JLabel P3Label;

        // private JLabel P1bckgnd;
        // private JLabel P2bckgnd;
        // private JLabel P3bckgnd;
        // private JLabel GMbckgnd;

        private JTextField P1HP;
        private JTextField P2HP;
        private JTextField P3HP;

        private JTextField P1MP;
        private JTextField P2MP;
        private JTextField P3MP;

        private JTextField P1Output;
        private JTextField P2Output;
        private JTextField P3Output;

        // private JScrollPane P1Scroll;
        // private JScrollPane P2Scroll;
        // private JScrollPane P3Scroll;

        private JTextArea GMOutput;
        private JTextField GMName;
        private JScrollPane GMScroll;

        JFrame mainFrame = new JFrame();
        JPanel gmPanel = new JPanel();
        JPanel P1Panel = new JPanel();
        JPanel P2Panel = new JPanel();
        JPanel P3Panel = new JPanel();

        
        public LauncherGUI()
        {
            try {UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
            {
                System.out.println(e);
            }


            initGUI();
            mainFrame.setSize(500, 660); //DO NOT CHANGE PLEASE - MP (PS. OR ATLEAST COPY AND COMMENT ONE OUT AND CHANGE THE OTHER)
            mainFrame.setLayout(new FlowLayout());
            mainFrame.setTitle("JCRPG - Adventure Emulator");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setLocationByPlatform(true);
            mainFrame.getContentPane().setBackground(Color.BLACK);

            pack();
            mainFrame.setResizable(false);
            mainFrame.setVisible(true);
        }

        private void initGUI()
        {
            // P1bckgnd = new JLabel();
            // P2bckgnd = new JLabel();
            // P3bckgnd = new JLabel();
            // GMbckgnd = new JLabel();

            // try {
            //     BufferedImage imgRead = ImageIO.read(new File("src\\JCRPG_gui\\backgroundImages\\redBCKGND.jpg"));
            //     ImageIcon P1Icon = new ImageIcon(imgRead);
            //     P1bckgnd.setIcon(P1Icon);

            //     P1Panel.super.paintComponents(imgRead);
            //     P1Panel.add(P1bckgnd);

 
            // } catch (IOException | IllegalArgumentException e) {
            //     // TODO Auto-generated catch block
            //     e.printStackTrace();
            //     P1Panel.setBackground(Color.RED);
            // }

            // try {
            //     BufferedImage imgRead = ImageIO.read(new File("src\\JCRPG_gui\\backgroundImages\\bluBCKGND.jpg"));
            //     ImageIcon P2Icon = new ImageIcon(imgRead);
            //     P2bckgnd.setIcon(P2Icon);
            //     P2Panel.add(P2bckgnd);

 
            // } catch (IOException | IllegalArgumentException e) {
            //     // TODO Auto-generated catch block
            //     e.printStackTrace();
            //     P2Panel.setBackground(Color.BLUE);
            // }

            // try {
            //     BufferedImage imgRead = ImageIO.read(new File("src\\JCRPG_gui\\backgroundImages\\grnBCKGND.jpg"));
            //     ImageIcon P3Icon = new ImageIcon(imgRead);
            //     P3bckgnd.setIcon(P3Icon);
            //     P3Panel.add(P3bckgnd);

 
            // } catch (IOException | IllegalArgumentException e) {
            //     // TODO Auto-generated catch block
            //     e.printStackTrace();
            //     P3Panel.setBackground(Color.GREEN);
            // }

            // try {
            //     BufferedImage imgRead = ImageIO.read(new File("src\\JCRPG_gui\\backgroundImages\\gmBCKGND.jpg"));
            //     ImageIcon gmIcon = new ImageIcon(imgRead);
            //     GMbckgnd.setIcon(gmIcon);
            //     gmPanel.add(GMbckgnd);

 
            // } catch (IOException | IllegalArgumentException e) {
            //     // TODO Auto-generated catch block
            //     e.printStackTrace();
            //     gmPanel.setBackground(Color.DARK_GRAY);
            // }


            nextButton = new JButton("Next");
            nextButton.addActionListener((ActionEven) -> {buttonListener(1); });

            resetButton = new JButton("Reset");
            resetButton.addActionListener((ActionEven) -> {buttonListener(2);});

            quitButton = new JButton("Quit");
            quitButton.addActionListener((ActionEven) -> {buttonListener(3);});

            P1Role = new JButton("Submit");
            P1Role.addActionListener((ActionEven) -> {buttonListener(4);});

            P2Role = new JButton("Submit");
            P2Role.addActionListener((ActionEven) -> {buttonListener(5);});

            P3Role = new JButton("Submit");
            P3Role.addActionListener((ActionEven) -> {buttonListener(6);});


            P1Name = new JTextField();
            P1Name.setEditable(true);
            P2Name = new JTextField();
            P2Name.setEditable(true);
            P3Name = new JTextField();
            P3Name.setEditable(true);

            P1Label = new JLabel("", SwingConstants.CENTER);
            P1Label.setVisible(false);
            P1Label.setOpaque(true);
            //P1Label.setBackground(Color.getHSBColor(290f, 0.5f, 1f));

            P2Label = new JLabel("", SwingConstants.CENTER);
            P2Label.setVisible(false);
            P2Label.setOpaque(true);
            //P2Label.setBackground(Color.getHSBColor(20f, 0.5f, 1f));


            P3Label = new JLabel("", SwingConstants.CENTER);
            P3Label.setVisible(false);
            P3Label.setOpaque(true);
            //P3Label.setBackground(Color.getHSBColor(0, 0.4f, 1));



            P1HP = new JTextField();
            P2HP = new JTextField();
            P3HP = new JTextField();
            P1MP = new JTextField();
            P2MP = new JTextField();
            P3MP = new JTextField();
            P1Output = new JTextField();
            P2Output = new JTextField();
            P3Output = new JTextField();
            P1HP.setEditable(false);
            P2HP.setEditable(false);
            P3HP.setEditable(false);
            P1MP.setEditable(false);
            P2MP.setEditable(false);
            P3MP.setEditable(false);
            P1Output.setEditable(true);
            P2Output.setEditable(true);
            P3Output.setEditable(true);

            GMOutput = new JTextArea();
            GMOutput.setEditable(false);
            GMOutput.setLineWrap(true);
            GMOutput.setWrapStyleWord(true);
            GMName = new JTextField();
            GMName.setEditable(false);
            GMName.setOpaque(true);
            GMName.setText("GM:");
            GMName.setBackground(Color.gray);
            GMScroll = new JScrollPane(GMOutput, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            GMScroll.setVisible(true);

            Dimension dim = new Dimension(150, 200);

            P1Panel.setPreferredSize(dim);
            P2Panel.setPreferredSize(dim);
            P3Panel.setPreferredSize(dim);
            gmPanel.setPreferredSize(new Dimension(455, 400));
            //setSize(dim);

            P1Label.setPreferredSize(new Dimension(75, 20));
            P2Label.setPreferredSize(new Dimension(75, 20));
            P3Label.setPreferredSize(new Dimension(75, 20));

            P1HP.setPreferredSize(new Dimension(50, 30));
            P2HP.setPreferredSize(new Dimension(50, 30));
            P3HP.setPreferredSize(new Dimension(50, 30));

            P1MP.setPreferredSize(new Dimension(50, 30));
            P2MP.setPreferredSize(new Dimension(50, 30));
            P3MP.setPreferredSize(new Dimension(50, 30));

            P1Output.setPreferredSize(new Dimension(50, 30));
            P2Output.setPreferredSize(new Dimension(50, 30));
            P3Output.setPreferredSize(new Dimension(50, 30));

            GMName.setPreferredSize(new Dimension(30, 20));
            GMOutput.setPreferredSize(new Dimension(300, 350));
            GMScroll.setPreferredSize(new Dimension(30, 350));

            // //MAIN PANEL SETUP
            // JPanel gmPanel = new JPanel();
            // gmPanel.setLayout(new GridBagLayout());

            gmPanel.setLayout(new FlowLayout());
            P1Panel.setLayout(new GridBagLayout());
            P2Panel.setLayout(new GridBagLayout());
            P3Panel.setLayout(new GridBagLayout());


            // gmPanel.add(new JLabel("GM:"), getConstraints(1, 8, GridBagConstraints.LINE_START));
            // gmPanel.add(GMOutput, getConstraints(2, 8, GridBagConstraints.LINE_START));
            // gmPanel.add(GMScroll, getConstraints(3, 8, GridBagConstraints.LINE_START));
            gmPanel.add(GMName, new FlowLayout(FlowLayout.CENTER));
            gmPanel.add(GMOutput, new FlowLayout(FlowLayout.CENTER));
            gmPanel.add(GMScroll, new FlowLayout(FlowLayout.CENTER));
            
            P1Panel.add(P1Label, getConstraints(0, 0, GridBagConstraints.CENTER));
            P1Panel.add(P1Name, getConstraints(0, 0, GridBagConstraints.CENTER));
            P1Panel.add(P1Output, getConstraints(0, 4, GridBagConstraints.CENTER));
            P1Panel.add(P1HP, getConstraints(0, 2, GridBagConstraints.CENTER));
            P1Panel.add(P1MP, getConstraints(0, 3, GridBagConstraints.CENTER));
            P1Panel.add(P1Role, getConstraints(0, 5, GridBagConstraints.CENTER));
            P1Name.setText("Player 1:");
            //gmPanel.add(new JLabel("Player One:"), getConstraints(0, 1, GridBagConstraints.LINE_START));

            P2Panel.add(P2Label, getConstraints(2, 0, GridBagConstraints.CENTER));
            P2Panel.add(P2Name,getConstraints(2, 0, GridBagConstraints.CENTER));
            P2Panel.add(P2Output, getConstraints(2, 4, GridBagConstraints.CENTER));
            P2Panel.add(P2HP, getConstraints(2, 2, GridBagConstraints.CENTER));
            P2Panel.add(P2MP, getConstraints(2, 3, GridBagConstraints.CENTER));
            P2Panel.add(P2Role, getConstraints(2, 5, GridBagConstraints.CENTER));
            P2Name.setText("Player 2:");
           // gmPanel.add(new JLabel("Player Two:"), getConstraints(0, 1, GridBagConstraints.CENTER));

            P3Panel.add(P3Label, getConstraints(4, 0, GridBagConstraints.CENTER));
            P3Panel.add(P3Name,getConstraints(4, 0, GridBagConstraints.CENTER));
            P3Panel.add(P3Output, getConstraints(4, 4, GridBagConstraints.CENTER));
            P3Panel.add(P3HP, getConstraints(4, 2, GridBagConstraints.CENTER));
            P3Panel.add(P3MP, getConstraints(4, 3, GridBagConstraints.CENTER));
            P3Panel.add(P3Role, getConstraints(4, 5, GridBagConstraints.CENTER));
            P3Name.setText("Player 3:");
          // gmPanel.add(new JLabel("Player Three:"), getConstraints(0, 1, GridBagConstraints.LINE_START));

            GMOutput.setText(initText[textTurner]); //FINISH THIS
        
            P1Panel.setBackground(new Color(251, 148, 196));
            P2Panel.setBackground(new Color(218, 148, 251));
            P3Panel.setBackground(new Color(117, 210, 241));
            gmPanel.setBackground(new Color(42, 225, 70));

            mainFrame.add(P1Panel);
            mainFrame.add(P2Panel);
            mainFrame.add(P3Panel);
            mainFrame.add(gmPanel);


            //BUTTON PANEL SETUP
            JPanel buttonPanel = new JPanel();

            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.add(nextButton);
            buttonPanel.add(resetButton);
            buttonPanel.add(quitButton);
            gmPanel.add(buttonPanel, BorderLayout.SOUTH);
            buttonPanel.setBackground(Color.DARK_GRAY);
            pack();
        }

        private GridBagConstraints getConstraints(int x, int y, int anchor)
            {
                GridBagConstraints c = new GridBagConstraints();
                c.insets = new Insets(10, 10, 0, 10);
                c.gridx = x;
                c.gridy = y;
                c.anchor = anchor;
                return c;
            }

        private void buttonListener(int Btype)
        {
            int pRole;
            switch (Btype)
            {
                case 1: //Initiate next step through code in GM class
                        if (textTurner < 1)
                        {
                            textTurner++;
                            GMOutput.setText(initText[textTurner]);
                        }
                        break;
                case 2: 
                        P1Role.setEnabled(true);
                        P1Role.setVisible(true);
                        P1HP.setText("");
                        P1MP.setText("");
                        P1Label.setVisible(false);
                        P1Label.setText("");
                        P1Output.setText("");
                        P1Output.setEditable(true);
                        P1Name.setText("Player 1:");
                        P1Name.setVisible(true);
                        P1Name.setEditable(true);

                        P2Role.setEnabled(true);
                        P2Role.setVisible(true);
                        P2HP.setText("");
                        P2MP.setText("");
                        P2Label.setVisible(false);
                        P2Label.setText("");
                        P2Output.setText("");
                        P2Output.setEditable(true);
                        P2Name.setText("Player 2:");
                        P2Name.setVisible(true);
                        P2Name.setEditable(true);

                        P3Role.setEnabled(true);
                        P3Role.setVisible(true);
                        P3HP.setText("");
                        P3MP.setText("");
                        P3Label.setVisible(false);
                        P3Label.setText("");
                        P3Output.setText("");
                        P3Output.setEditable(true);
                        P3Name.setText("Player 3:");
                        P3Name.setVisible(true);
                        P3Name.setEditable(true);

                        textTurner = 0;
                        GMOutput.setText(initText[0]);
                        try {gameMaster.Initialize();} catch (IOException ex) {}
                        break;
                case 3: //Shut everything down
                        System.exit(0);
                        break;
                case 4:
                        //PLAYER 1 IS INDEX 0
                        P1Name.setEditable(false);
                        P1Name.setVisible(false);
                        P1Output.setEditable(false);
                        pRole = Integer.parseInt(P1Output.getText()) - 1;
                        GUIPObj = gameMaster.playerInit(pRole, 0, P1Name.getText());
                        P1Label.setText(GUIPObj[0].name);
                        P1Label.setVisible(true);
                        P1HP.setText(Integer.toString(GUIPObj[0].hp) + "/" + Integer.toString(GUIPObj[0].maxHP));
                        P1MP.setText(Integer.toString(GUIPObj[0].mp));
                        P1Role.setVisible(false);
                        P1Role.setEnabled(false);
                        break;
                case 5:
                        //PLAYER 1 IS INDEX 0
                        P2Name.setEditable(false);
                        P2Name.setVisible(false);
                        P2Output.setEditable(false);
                        pRole = Integer.parseInt(P2Output.getText()) - 1;
                        GUIPObj = gameMaster.playerInit(pRole, 1, P2Name.getText());
                        P2Label.setText(GUIPObj[1].name);
                        P2Label.setVisible(true);
                        P2HP.setText(Integer.toString(GUIPObj[1].hp) + "/" + Integer.toString(GUIPObj[1].maxHP));
                        P2MP.setText(Integer.toString(GUIPObj[1].mp));
                        P2Role.setVisible(false);
                        P2Role.setEnabled(false);
                        break;
                case 6:
                        //PLAYER 1 IS INDEX 0
                        P3Name.setEditable(false);
                        P3Name.setVisible(false);
                        P3Output.setEditable(false);
                        pRole = Integer.parseInt(P3Output.getText()) - 1;
                        GUIPObj = gameMaster.playerInit(pRole, 2, P3Name.getText());
                        P3Label.setText(GUIPObj[2].name);
                        P3Label.setVisible(true);
                        P3HP.setText(Integer.toString(GUIPObj[2].hp) + "/" + Integer.toString(GUIPObj[2].maxHP));
                        P3MP.setText(Integer.toString(GUIPObj[2].mp));
                        P3Role.setVisible(false);
                        P3Role.setEnabled(false);
                        break;
            }
        }
}
