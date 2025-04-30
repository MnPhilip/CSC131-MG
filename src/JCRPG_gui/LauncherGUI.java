package JCRPG_gui;
import JCPRG_code.GM;

import com.sun.jdi.connect.LaunchingConnector;
import java.awt.*;
import java.io.IOException;
import javax.naming.InterruptedNamingException;
import javax.swing.*;

@SuppressWarnings("serial")

public class LauncherGUI extends JFrame
{
    JCRPG_code.GM gameMaster = new JCRPG_code.GM();
    JCRPG_code.Player GUIPObj[] = new JCRPG_code.Player[3];
    
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

        private JTextField P1HP;
        private JTextField P2HP;
        private JTextField P3HP;

        private JTextField P1MP;
        private JTextField P2MP;
        private JTextField P3MP;

        private JTextField P1Output;
        private JTextField P2Output;
        private JTextField P3Output;

        private JScrollPane P1Scroll;
        private JScrollPane P2Scroll;
        private JScrollPane P3Scroll;

        private JTextArea GMOutput;
        private JTextField GMName;
        private JScrollPane GMScroll;

        JFrame mainFrame = new JFrame();
        JPanel mainPanel = new JPanel();

        public LauncherGUI()
        {
            try {UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
            {
                System.out.println(e);
            }

            initGUI();
            setSize(400, 400);
            setTitle("JCRPG - Adventure Emulator");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationByPlatform(true);
            add(mainPanel);
            pack();
            setVisible(true);
        }

        private void initGUI()
        {
            //PANEL SETUP
            //JPanel P1Panel = new JPanel();

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
            // P1Role = new JTextField();
            // P1Rolde.setEditable(true);

            P1Label = new JLabel();
            P1Label.setVisible(false);
            P2Label = new JLabel();
            P2Label.setVisible(false);
            P3Label = new JLabel();
            P3Label.setVisible(false);

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
            GMScroll = new JScrollPane(GMOutput, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            GMScroll.setVisible(true);

            Dimension dim = new Dimension(750, 200);
            setSize(dim);

            P1Name.setPreferredSize(new Dimension(100, 20));
            P2Name.setPreferredSize(new Dimension(100, 20));
            P3Name.setPreferredSize(new Dimension(100, 20));

            P1HP.setPreferredSize(new Dimension(50, 30));
            P2HP.setPreferredSize(new Dimension(50, 30));
            P3HP.setPreferredSize(new Dimension(50, 30));

            P1MP.setPreferredSize(new Dimension(50, 30));
            P2MP.setPreferredSize(new Dimension(50, 30));
            P3MP.setPreferredSize(new Dimension(50, 30));

            P1Output.setPreferredSize(new Dimension(50, 30));
            P2Output.setPreferredSize(new Dimension(50, 30));
            P3Output.setPreferredSize(new Dimension(50, 30));

            GMOutput.setPreferredSize(new Dimension(300, 100));
            GMScroll.setPreferredSize(new Dimension(100, 100));

            // //MAIN PANEL SETUP
            // JPanel mainPanel = new JPanel();
            // mainPanel.setLayout(new GridBagLayout());

            mainPanel.setLayout(new GridBagLayout());

            // mainPanel.add(new JButton("Next"), GridBagLayout.SOUTH);
            // mainPanel.add(new JButton("Reset"), GridBagLayout.SOUTH);
            // mainPanel.add(new JButton("Quit"), GridBagLayout.SOUTH);
            mainPanel.add(new JLabel("GM:"), getConstraints(1, 8, GridBagConstraints.LINE_START));
            mainPanel.add(GMOutput, getConstraints(2, 8, GridBagConstraints.LINE_END));
            //mainPanel.add(GMScroll, getConstraints(3, 8, GridBagConstraints.LINE_END));
            
            mainPanel.add(P1Label, getConstraints(0, 0, GridBagConstraints.LINE_START));
            mainPanel.add(P1Name, getConstraints(0, 0, GridBagConstraints.LINE_START));
            mainPanel.add(P1Output, getConstraints(0, 4, GridBagConstraints.LINE_START));
            mainPanel.add(P1HP, getConstraints(0, 2, GridBagConstraints.LINE_START));
            mainPanel.add(P1MP, getConstraints(0, 3, GridBagConstraints.LINE_START));
            mainPanel.add(P1Role, getConstraints(0, 5, GridBagConstraints.LINE_START));
            P1Name.setText("Player 1:");
            //mainPanel.add(new JLabel("Player One:"), getConstraints(0, 1, GridBagConstraints.LINE_START));

            mainPanel.add(P2Label, getConstraints(2, 0, GridBagConstraints.LINE_START));
            mainPanel.add(P2Name,getConstraints(2, 0, GridBagConstraints.CENTER));
            mainPanel.add(P2Output, getConstraints(2, 4, GridBagConstraints.LINE_START));
            mainPanel.add(P2HP, getConstraints(2, 2, GridBagConstraints.LINE_START));
            mainPanel.add(P2MP, getConstraints(2, 3, GridBagConstraints.LINE_START));
            mainPanel.add(P2Role, getConstraints(2, 5, GridBagConstraints.LINE_START));
            P2Name.setText("Player 2:");
           // mainPanel.add(new JLabel("Player Two:"), getConstraints(0, 1, GridBagConstraints.CENTER));

            mainPanel.add(P3Label, getConstraints(4, 0, GridBagConstraints.LINE_START));
            mainPanel.add(P3Name,getConstraints(4, 0, GridBagConstraints.LINE_START));
            mainPanel.add(P3Output, getConstraints(4, 4, GridBagConstraints.LINE_START));
            mainPanel.add(P3HP, getConstraints(4, 2, GridBagConstraints.LINE_START));
            mainPanel.add(P3MP, getConstraints(4, 3, GridBagConstraints.LINE_START));
            mainPanel.add(P3Role, getConstraints(4, 5, GridBagConstraints.LINE_START));
            P3Name.setText("Player 3:");
          // mainPanel.add(new JLabel("Player Three:"), getConstraints(0, 1, GridBagConstraints.LINE_START));

          GMOutput.setText("JCRPG - DND Story Emulator\nSelect \"Next\" to continue.\nCODE CREATED BY:\nSHIVANI BILIMORIA\nNAM NGUYEN\nSARAVJOT SINGH\nJD DAVID\nCATALINA ESCANO\nMATT PHILIP\n"); //FINISH THIS
        

            //BUTTON PANEL SETUP
            JPanel buttonPanel = new JPanel();

            buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.add(nextButton);
            buttonPanel.add(resetButton);
            buttonPanel.add(quitButton);

            add(buttonPanel, BorderLayout.SOUTH);
        }

        private GridBagConstraints getConstraints(int x, int y, int anchor)
            {
                GridBagConstraints c = new GridBagConstraints();
                c.insets = new Insets(8, 8, 0, 8);
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
                        break;
                case 2: 
                    try {
                        gameMaster.Initialize();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                        break;
                case 3: //Shut everything down
                        System.exit(0);
                        break;
                case 4:
                        //PLAYER 1 IS INDEX 0
                        P1Name.setEditable(false);
                        P1Name.setVisible(false);
                        P1Output.setEditable(false);
                        pRole = Integer.parseInt(P1Output.getText());
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
                        pRole = Integer.parseInt(P2Output.getText());
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
                        pRole = Integer.parseInt(P3Output.getText());
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
