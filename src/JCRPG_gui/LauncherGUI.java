package JCRPG_gui;

//import JCPRG_code.GM;
import JCRPG_code.GM.*;



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
import javax.print.attribute.standard.OutputBin;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;

@SuppressWarnings("serial")

public class LauncherGUI extends JFrame
{
    JCRPG_code.GM gameMaster = new JCRPG_code.GM();
    JCRPG_code.GPTRUN GPTrun = new JCRPG_code.GPTRUN();
    JCRPG_code.Player GUIPObj[] = new JCRPG_code.Player[3];
    JCRPG_code.Monster GUIMObj[] = new JCRPG_code.Monster[6];

    int player;
    int monster;
    int[] PlayerChap = {0, 0, 0};
    String combatProceeding = "";
    
    int chapIndex = 0;
    String[] GMTEXTBANK = {"JCRPG - DND Story Emulator\nSelect \"Next\" to continue.\n\nCODE CREATED BY:\nSHIVANI BILIMORIA\nNAM NGUYEN\nSARAVJOT SINGH\nJD DAVID\nCATALINA ESCANO\nMATT PHILIP\n",
                            "AVAILABLE CLASSES:\n{1} - Wizard: low health, low defense, very high damage potential.\nThe ultimate glass Cannon\n\n{2} - Barbarian: High health, high defense, medium damage.\n Hearty and hefty, packing a staggering punch\n\n" + 
                        "{3} - Bard: All-arounder build, medium-level damage possibilites with middle-of-the-road health and defense. Perfect for a neutral adventurer\n"};
    
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

        private JTextArea P1Output;
        private JTextArea P2Output;
        private JTextArea P3Output;

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

        Dimension panelDim = new Dimension(150, 200);
        Dimension labelDim = new Dimension (75, 20);
        Dimension statDim = new Dimension(50, 30);
        Dimension outputDim = new Dimension(50, 30); 

        
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
            //mainFrame.setResizable(false);
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
            P1Output = new JTextArea();
            P2Output = new JTextArea();
            P3Output = new JTextArea();
            P1HP.setEditable(false);
            P2HP.setEditable(false);
            P3HP.setEditable(false);
            P1MP.setEditable(false);
            P2MP.setEditable(false);
            P3MP.setEditable(false);

            P1Output.setEditable(true);
            P1Output.setLineWrap(true);
            P1Output.setWrapStyleWord(true);
            P2Output.setEditable(true);
            P2Output.setLineWrap(true);
            P2Output.setWrapStyleWord(true);
            P3Output.setEditable(true);
            P3Output.setLineWrap(true);
            P3Output.setWrapStyleWord(true);

            GMOutput = new JTextArea();
            GMOutput.setEditable(false);
            GMOutput.setLineWrap(true);
            GMOutput.setWrapStyleWord(true);
            GMName = new JTextField();
            GMName.setEditable(false);
            GMName.setOpaque(true);
            GMName.setText("GM:");
            GMName.setBackground(Color.gray);
            GMScroll = new JScrollPane(GMOutput);
            GMScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            GMScroll.setVisible(true);

            // GMScroll.setBorder(
            // BorderFactory.createCompoundBorder(
            //     BorderFactory.createCompoundBorder(
            //                     BorderFactory.createTitledBorder("Plain Text"),
            //                     BorderFactory.createEmptyBorder(5,5,5,5)),
            //     GMScroll.getBorder()));

            P1Panel.setPreferredSize(panelDim);
            P2Panel.setPreferredSize(panelDim);
            P3Panel.setPreferredSize(panelDim);
            gmPanel.setPreferredSize(new Dimension(500, 660));
            //setSize(dim);

            P1Label.setPreferredSize(labelDim);
            P2Label.setPreferredSize(labelDim);
            P3Label.setPreferredSize(labelDim);

            P1HP.setPreferredSize(statDim);
            P2HP.setPreferredSize(statDim);
            P3HP.setPreferredSize(statDim);

            P1MP.setPreferredSize(statDim);
            P2MP.setPreferredSize(statDim);
            P3MP.setPreferredSize(statDim);

            P1Output.setPreferredSize(outputDim);
            P2Output.setPreferredSize(outputDim);
            P3Output.setPreferredSize(outputDim);

            GMName.setPreferredSize(new Dimension(30, 20));
            GMOutput.setPreferredSize(new Dimension(300, 350));
            GMScroll.setPreferredSize(new Dimension(30, 350));

            // //MAIN PANEL SETUP
            // JPanel gmPanel = new JPanel();
            // gmPanel.setLayout(new GridBagLayout());

            gmPanel.setLayout(new FlowLayout());
            //gmPanel.setLayout(new GridBagLayout());
            P1Panel.setLayout(new GridBagLayout());
            P2Panel.setLayout(new GridBagLayout());
            P3Panel.setLayout(new GridBagLayout());


            // gmPanel.add(new JLabel("GM:"), getConstraints(1, 8, GridBagConstraints.LINE_START));
            // gmPanel.add(GMOutput, getConstraints(2, 8, GridBagConstraints.LINE_START));
            // gmPanel.add(GMScroll, getConstraints(3, 8, GridBagConstraints.LINE_START));
            gmPanel.add(GMName, new FlowLayout(FlowLayout.CENTER));
            gmPanel.add(GMOutput, new FlowLayout(FlowLayout.CENTER));
            gmPanel.add(GMScroll, new FlowLayout(FlowLayout.LEFT));
            // gmPanel.add(GMOutput, BorderLayout.CENTER);
            // gmPanel.add(GMName, BorderLayout.BEFORE_FIRST_LINE);
            // gmPanel.add(GMScroll, BorderLayout.EAST);
            
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

            GMOutput.setText(GMTEXTBANK[chapIndex]); //FINISH THIS
        
            P1Panel.setBackground(new Color(135, 18, 22));
            P2Panel.setBackground(new Color(28, 32, 118));
            P3Panel.setBackground(new Color(93, 0, 105));
            gmPanel.setBackground(Color.DARK_GRAY);

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
            int z = 0;
            JCRPG_code.RNG generate = new JCRPG_code.RNG();
            switch (Btype)
            {
                case 1: //Initiate next step through code in GM class
                        if (chapIndex < 1)
                        {
                            chapIndex++;
                            GMOutput.setText(GMTEXTBANK[chapIndex]);

                            GUIMObj = gameMaster.monsterInit();
                        }        
                        else 
                        {
                            GMOutput.setText(grabGMOutput(chapIndex));
                            chapIndex = gameMaster.GUIChapUpdate(chapIndex, 1);
                            for (z = 0; z < 3; z++)
                            {
                                if (chapIndex == 3)
                                {
                                    playerOutput(z, "Lost within the endless labyrinth ");
                                }
                                else if (chapIndex == 4)
                                {
                                    combatOutsource(z, generate.diceRoll(4, 1));
                                }
                                else if (chapIndex == 9)
                                {
                                    playerOutput(z, "Refreshed by the fountains...oddly colored contents, " + GUIPObj[z].name + "'s health is replenished!");
                                }
                                else if (chapIndex == 12)
                                {
                                    combatOutsource(z, 5);
                                }
                                else if (chapIndex == 19)
                                {
                                    combatOutsource(z, 0);

                                }
                            }
                            GUIPObj = gameMaster.playerUpdate();
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

                        P1Output.setPreferredSize(outputDim);
                        P2Output.setPreferredSize(outputDim);
                        P3Output.setPreferredSize(outputDim);

                        chapIndex = 0;
                        GMOutput.setText(GMTEXTBANK[0]);
                        // try {gameMaster.Initialize();} catch (IOException ex) {} no, instead used chapIndex as "chap" for chapter
                        break; 
                case 3: //Shut everything down
                        System.exit(0);
                        break;
                case 4:
                        //PLAYER 1 IS INDEX 0
                        P1Name.setEditable(false);
                        P1Name.setVisible(false);
                        P1Output.setEditable(false);
                        P1Output.setPreferredSize(new Dimension(125, 60));
                        P1Role.setVisible(false);
                        P1Role.setEnabled(false);

                        P1Role.setVisible(false);
                        P1Role.setEnabled(false);

                        
                        pRole = Integer.parseInt(P1Output.getText()) - 1;
                        GUIPObj = gameMaster.playerInit(pRole, 0, P1Name.getText());

                        P1Label.setText(GUIPObj[0].name);
                        P1Label.setVisible(true);
                        P1HP.setText(Integer.toString(GUIPObj[0].hp) + "/" + Integer.toString(GUIPObj[0].maxHP));
                        P1MP.setText(Integer.toString(GUIPObj[0].mp));

                        P1Output.setText("");
                        // P1Name.setEditable(false);
                        // P1Name.setVisible(false);
                        // P1Output.setEditable(false);
                        // P1Output.setText("");
                        // pRole = Integer.parseInt(P1Output.getText()) - 1;
                        // GUIPObj = gameMaster.playerInit(pRole, 0, P1Name.getText());
                        // P1Label.setText(GUIPObj[0].name);
                        // P1Label.setVisible(true);
                        // P1HP.setText(Integer.toString(GUIPObj[0].hp) + "/" + Integer.toString(GUIPObj[0].maxHP));
                        // P1MP.setText(Integer.toString(GUIPObj[0].mp));
                        // P1Role.setVisible(false);
                        // P1Role.setEnabled(false);
                        break;
                case 5:
                        //PLAYER 2 IS INDEX 1
                        P2Name.setEditable(false);
                        P2Name.setVisible(false);
                        P2Output.setEditable(false);
                        P2Output.setPreferredSize(new Dimension(125, 60));
                        P2Role.setVisible(false);
                        P2Role.setEnabled(false);

                        P2Role.setVisible(false);
                        P2Role.setEnabled(false);

                        
                        pRole = Integer.parseInt(P2Output.getText()) - 1;
                        GUIPObj = gameMaster.playerInit(pRole, 1, P2Name.getText());

                        P2Label.setText(GUIPObj[1].name);
                        P2Label.setVisible(true);
                        P2HP.setText(Integer.toString(GUIPObj[1].hp) + "/" + Integer.toString(GUIPObj[1].maxHP));
                        P2MP.setText(Integer.toString(GUIPObj[1].mp));

                        P2Output.setText("");
                        break;
                case 6:
                        //PLAYER 3 IS INDEX 2
                        P3Name.setEditable(false);
                        P3Name.setVisible(false);
                        P3Output.setEditable(false);
                        P3Output.setPreferredSize(new Dimension(125, 60));
                        P3Role.setVisible(false);
                        P3Role.setEnabled(false);

                        P3Role.setVisible(false);
                        P3Role.setEnabled(false);

                        
                        pRole = Integer.parseInt(P3Output.getText()) - 1;
                        GUIPObj = gameMaster.playerInit(pRole, 2, P3Name.getText());

                        P3Label.setText(GUIPObj[2].name);
                        P3Label.setVisible(true);
                        P3HP.setText(Integer.toString(GUIPObj[2].hp) + "/" + Integer.toString(GUIPObj[2].maxHP));
                        P3MP.setText(Integer.toString(GUIPObj[2].mp));

                        P3Output.setText("");
                        // P3Name.setEditable(false);
                        // P3Name.setVisible(false);
                        // P3Output.setEditable(false);
                        // P3Output.setText("");
                        // pRole = Integer.parseInt(P3Output.getText()) - 1;
                        // GUIPObj = gameMaster.playerInit(pRole, 2, P3Name.getText());
                        // P3Label.setText(GUIPObj[2].name);
                        // P3Label.setVisible(true);
                        // P3HP.setText(Integer.toString(GUIPObj[2].hp) + "/" + Integer.toString(GUIPObj[2].maxHP));
                        // P3MP.setText(Integer.toString(GUIPObj[2].mp));
                        // P3Role.setVisible(false);
                        // P3Role.setEnabled(false);
                        break;
            }
        }

        void playerOutput(int index, String newOutput)
        {
            int currHP;
            JCRPG_code.Entity entity = new JCRPG_code.Entity();

            switch (index)
            {
                case 0:
                        P1Output.setFont(new Font("Arial", Font.PLAIN, 10));
                        P1Output.setText(newOutput);
                        // currHP = Integer.parseInt(P1HP.getText());
                        // GUIPObj[index].hp = (entity.HPset(gameMaster.playerUpdate(), -(currHP - GUIPObj[index].maxHP), index));
                        // GUIPObj[index].hp = GUIPObj[index].maxHP;
                        // P1HP.setText(Integer.toString(GUIPObj[0].maxHP) + "/" + Integer.toString(GUIPObj[0].maxHP));
                case 1:
                        P2Output.setFont(new Font("Arial", Font.PLAIN, 10));
                        P2Output.setText(newOutput);
                        // currHP = Integer.parseInt(P1HP.getText());
                        // GUIPObj[index].hp = (entity.HPset(gameMaster.playerUpdate(), -(currHP - GUIPObj[index].maxHP), index));
                        // GUIPObj[index].hp = GUIPObj[index].maxHP;
                        // P2HP.setText(Integer.toString(GUIPObj[1].maxHP) + "/" + Integer.toString(GUIPObj[1].maxHP));
                case 2:
                        P3Output.setFont(new Font("Arial", Font.PLAIN, 10));
                        P3Output.setText(newOutput);
                        // currHP = Integer.parseInt(P1HP.getText());
                        // GUIPObj[index].hp = (entity.HPset(gameMaster.playerUpdate(), -(currHP - GUIPObj[index].maxHP), index));
                        // GUIPObj[index].hp = GUIPObj[index].maxHP;
                        // P3HP.setText(Integer.toString(GUIPObj[2].maxHP) + "/" + Integer.toString(GUIPObj[2].maxHP));
                    
                    break;
            }
        }

        public String grabGMOutput(int chapIndex)
        {
            return gameMaster.GUIGMUpdate(chapIndex);
        }
        
        public void combatOutsource(int player, int monster)
        {
            combatProceeding = "";
            String inProgressString = "";
            switch(player)
            {
            case 0:
                    combatProceeding = gameMaster.combatInit(player, monster);

                    GMOutput.setText(combatProceeding);

                    GUIPObj = gameMaster.playerUpdate();
                    P1HP.setText(Integer.toString(GUIPObj[player].hp) + "/" + Integer.toString(GUIPObj[player].maxHP));
                    P1MP.setText(Integer.toString(GUIPObj[player].mp));
                    if (GUIPObj[player].hp <= 0)
                    {
                        P1Output.setText("Fallen in battle against " + GUIMObj[monster].name);
                    }
                    GMOutput.setPreferredSize(new Dimension(300, 350));
                    GMScroll.setPreferredSize(new Dimension(30, 350));
            case 1:
                    combatProceeding = gameMaster.combatInit(player, monster);

                    GMOutput.setText(combatProceeding);

                    GUIPObj = gameMaster.playerUpdate();
                    P2HP.setText(Integer.toString(GUIPObj[player].hp) + "/" + Integer.toString(GUIPObj[player].maxHP));
                    P2MP.setText(Integer.toString(GUIPObj[player].mp));
                    if (GUIPObj[player].hp <= 0)
                    {
                        P2Output.setText("Fallen in battle against " + GUIMObj[monster].name);
                    }
                    GMOutput.setPreferredSize(new Dimension(300, 350));
                    GMScroll.setPreferredSize(new Dimension(30, 350));
            case 2:
                    combatProceeding = gameMaster.combatInit(player, monster);

                    GMOutput.setText(combatProceeding);

                    GUIPObj = gameMaster.playerUpdate();
                    P3HP.setText(Integer.toString(GUIPObj[player].hp) + "/" + Integer.toString(GUIPObj[player].maxHP));
                    P3MP.setText(Integer.toString(GUIPObj[player].mp));
                    if (GUIPObj[player].hp <= 0)
                    {
                        P3Output.setText("Fallen in battle against " + GUIMObj[monster].name);
                    }
                    GMOutput.setPreferredSize(new Dimension(300, 350));
                    GMScroll.setPreferredSize(new Dimension(30, 350));
            }
            if (GUIPObj[0].hp <= 0 && GUIPObj[1].hp <= 0 && GUIPObj[2].hp <= 0)
            {
                nextButton.setEnabled(false);
            }
        }
}
