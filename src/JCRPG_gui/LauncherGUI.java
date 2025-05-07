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
    boolean intro = true;
 
    String combatProceeding = "";
    boolean contValue = false;
    
    int z = 0; //USED IN NEXT BUTTON LISTENER, IT'S FINE HERE
    int[] chapIndex = {0, 0, 0};
    String[] GMTEXTBANK = {"JCRPG - DND Story Emulator\nSelect \"Next\" to continue.\n\nCODE CREATED BY:\nSHIVANI BILIMORIA\nNAM NGUYEN\nSARAVJOT SINGH\nJD DAVID\nCATALINA ESCANO\nMATT PHILIP\n",
                            "AVAILABLE CLASSES:\n"+ 
                            "{1} - Wizard: low health, low defense, very high damage potential.\nThe ultimate glass Cannon\n\n" + 
                            "{2} - Barbarian: High health, high defense, medium damage.\n Hearty and hefty, packing a staggering punch\n\n" + 
                            "{3} - Bard: All-arounder build, medium-level damage possibilites with middle-of-the-road health and defense. Perfect for a neutral adventurer\n"}; //+ 
                            //"{4} - Rogue: Decent health, high defense, low damage but incredibly nimble and hard to hit. A comfortable fit for those who don't like to get hurt"};
    
        private JButton continueButton;
        private JButton nextButton;
        private JButton quitButton;
        private JButton resetButton;

        private JButton P1Role;
        private JButton P2Role;
        private JButton P3Role;

        private JTextField P1Label;
        private JTextField P2Label;
        private JTextField P3Label;

        private JLabel P1Name;
        private JLabel P2Name;
        private JLabel P3Name;

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
        Dimension nameDim = new Dimension (125, 20);
        Dimension statDim = new Dimension(50, 30);
        Dimension outputDim = new Dimension(50, 30); 
        Dimension activeOutput = new Dimension(125, 60);

        
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

            continueButton = new JButton("Continue");
            continueButton.addActionListener((ActionEven) -> {buttonListener(7);});

            P1Role = new JButton("Submit");
            P1Role.addActionListener((ActionEven) -> {buttonListener(4);});

            P2Role = new JButton("Submit");
            P2Role.addActionListener((ActionEven) -> {buttonListener(5);});

            P3Role = new JButton("Submit");
            P3Role.addActionListener((ActionEven) -> {buttonListener(6);});


            P1Label = new JTextField();
            P1Label.setEditable(true);
            P2Label = new JTextField();
            P2Label.setEditable(true);
            P3Label = new JTextField();
            P3Label.setEditable(true);

            P1Name = new JLabel("", SwingConstants.CENTER);
            P1Name.setVisible(false);
            P1Name.setOpaque(true);
            //P1Name.setBackground(Color.getHSBColor(290f, 0.5f, 1f));

            P2Name = new JLabel("", SwingConstants.CENTER);
            P2Name.setVisible(false);
            P2Name.setOpaque(true);
            //P2Name.setBackground(Color.getHSBColor(20f, 0.5f, 1f));


            P3Name = new JLabel("", SwingConstants.CENTER);
            P3Name.setVisible(false);
            P3Name.setOpaque(true);
            //P3Name.setBackground(Color.getHSBColor(0, 0.4f, 1));



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

            P1Name.setPreferredSize(nameDim);
            P2Name.setPreferredSize(nameDim);
            P3Name.setPreferredSize(nameDim);

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
            
            P1Panel.add(P1Name, getConstraints(0, 0, GridBagConstraints.CENTER));
            P1Panel.add(P1Label, getConstraints(0, 0, GridBagConstraints.CENTER));
            P1Panel.add(P1Output, getConstraints(0, 4, GridBagConstraints.CENTER));
            P1Panel.add(P1HP, getConstraints(0, 2, GridBagConstraints.CENTER));
            P1Panel.add(P1MP, getConstraints(0, 3, GridBagConstraints.CENTER));
            P1Panel.add(P1Role, getConstraints(0, 5, GridBagConstraints.CENTER));
            P1Label.setText("Player 1");
            //gmPanel.add(new JLabel("Player One:"), getConstraints(0, 1, GridBagConstraints.LINE_START));

            P2Panel.add(P2Name, getConstraints(2, 0, GridBagConstraints.CENTER));
            P2Panel.add(P2Label,getConstraints(2, 0, GridBagConstraints.CENTER));
            P2Panel.add(P2Output, getConstraints(2, 4, GridBagConstraints.CENTER));
            P2Panel.add(P2HP, getConstraints(2, 2, GridBagConstraints.CENTER));
            P2Panel.add(P2MP, getConstraints(2, 3, GridBagConstraints.CENTER));
            P2Panel.add(P2Role, getConstraints(2, 5, GridBagConstraints.CENTER));
            P2Label.setText("Player 2");
           // gmPanel.add(new JLabel("Player Two:"), getConstraints(0, 1, GridBagConstraints.CENTER));

            P3Panel.add(P3Name, getConstraints(4, 0, GridBagConstraints.CENTER));
            P3Panel.add(P3Label,getConstraints(4, 0, GridBagConstraints.CENTER));
            P3Panel.add(P3Output, getConstraints(4, 4, GridBagConstraints.CENTER));
            P3Panel.add(P3HP, getConstraints(4, 2, GridBagConstraints.CENTER));
            P3Panel.add(P3MP, getConstraints(4, 3, GridBagConstraints.CENTER));
            P3Panel.add(P3Role, getConstraints(4, 5, GridBagConstraints.CENTER));
            P3Label.setText("Player 3");
          // gmPanel.add(new JLabel("Player Three:"), getConstraints(0, 1, GridBagConstraints.LINE_START));

            GMOutput.setText(GMTEXTBANK[chapIndex[0]]); //FINISH THIS
        
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
            //buttonPanel.add(continueButton);
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
            JCRPG_code.RNG generate = new JCRPG_code.RNG();
            JCRPG_code.Entity entity = new JCRPG_code.Entity();
            switch (Btype)
            {
                case 1: //Initiate next step through code in GM class
                        if (intro)
                        {
                            GMOutput.setText(GMTEXTBANK[1]);

                            GUIMObj = gameMaster.monsterInit();
                            intro = false;
                            z = -1;
                        }        
                        else if (GUIPObj[z].hp > 0)
                        {
                            GMOutput.setText(GUIPObj[z].name + ": \n" + grabGMOutput(chapIndex[z]));
                            chapIndex[z] = gameMaster.GUIChapUpdate(chapIndex[z], 1);
                            //if (!GUIPObj[z].victory && !GUIPObj[z].defeat)
                            {
                                switch (chapIndex[z]) 
                                {
                                    case 3:
                                            playerOutput(z, "Lost within the endless labyrinth ");
                                            //GUIPObj[z].hp = 0;
                                            GUIPObj[z].defeat = true;
                                            gameMaster.GUIPlayerUpdate(GUIPObj);
                                            break;
                                    case 4:
                                            combatOutsource(z, generate.diceRoll(4, 1));
                                            GUIPObj = gameMaster.playerUpdate();
                                            break;
                                    case 9:
                                            playerOutput(z, "Refreshed by the fountains...oddly colored contents, " + GUIPObj[z].name + "'s health is replenished!");
                                            GUIPObj[z].hp = GUIPObj[z].maxHP;
                                            gameMaster.GUIPlayerUpdate(GUIPObj);
                                            break;
                                    case 11:
                                            combatOutsource(z, 0);
                                            GUIPObj = gameMaster.playerUpdate();
                                            break;
                                    case 12:
                                            combatOutsource(z, 5);
                                            GUIPObj = gameMaster.playerUpdate();
                                            break;
                                    case 13:
                                            GUIPObj[z].defeat = true;
                                            gameMaster.GUIPlayerUpdate(GUIPObj);
                                            break;
                                    case 16: 
                                            GUIPObj[z].victory = true;
                                            playerOutput(z, "Escaped through the garden with the contents of the hidden cache!");
                                            gameMaster.GUIPlayerUpdate(GUIPObj);
                                            break;
                                    case 19:
                                            combatOutsource(z, 0);
                                            GUIPObj = gameMaster.playerUpdate();
                                            break;
                                    case 20:
                                            GUIPObj[z].defeat = true;
                                            gameMaster.GUIPlayerUpdate(GUIPObj);
                                            break;
                                    case 21:
                                            GUIPObj[z].defeat = true;
                                            gameMaster.GUIPlayerUpdate(GUIPObj);
                                            break;
                                    case 24:
                                            playerOutput(z, "Refreshed by the fountains...oddly colored contents, " + GUIPObj[z].name + "'s health is replenished!");
                                            GUIPObj[z].hp = GUIPObj[z].maxHP;
                                            gameMaster.GUIPlayerUpdate(GUIPObj);
                                            break;
                                    case 99:
                                            break;
                                    default:
                                        break;
                                }
                            }
                        }
                        else 
                        {
                            GUIPObj[z].defeat = true;
                            gameMaster.GUIPlayerUpdate(GUIPObj);
                        }

                        z++;
                        if (z > 2)
                        {
                            z = 0;
                        }

                        GUIPObj = gameMaster.playerUpdate();
                        endGameCheck();
                        break;
                case 2: 
                        z = 0;
                        intro = true;
                        nextButton.setEnabled(true);
                        P1Role.setEnabled(true);
                        P1Role.setVisible(true);
                        P1HP.setText("");
                        P1MP.setText("");
                        P1Name.setVisible(false);
                        P1Name.setText("");
                        P1Output.setText("");
                        P1Output.setEditable(true);
                        P1Label.setText("Player 1");
                        P1Label.setVisible(true);
                        P1Label.setEditable(true);

                        P2Role.setEnabled(true);
                        P2Role.setVisible(true);
                        P2HP.setText("");
                        P2MP.setText("");
                        P2Name.setVisible(false);
                        P2Name.setText("");
                        P2Output.setText("");
                        P2Output.setEditable(true);
                        P2Label.setText("Player 2");
                        P2Label.setVisible(true);
                        P2Label.setEditable(true);

                        P3Role.setEnabled(true);
                        P3Role.setVisible(true);
                        P3HP.setText("");
                        P3MP.setText("");
                        P3Name.setVisible(false);
                        P3Name.setText("");
                        P3Output.setText("");
                        P3Output.setEditable(true);
                        P3Label.setText("Player 3");
                        P3Label.setVisible(true);
                        P3Label.setEditable(true);

                        P1Output.setPreferredSize(outputDim);
                        P2Output.setPreferredSize(outputDim);
                        P3Output.setPreferredSize(outputDim);

                        chapIndex[0] = 0;
                        chapIndex[1] = 0;
                        chapIndex[2] = 0;
                        GMOutput.setText(GMTEXTBANK[0]);
                        // try {gameMaster.Initialize();} catch (IOException ex) {} no, instead used chapIndex as "chap" for chapter
                        break; 
                case 3: //Shut everything down
                        System.exit(0);
                        break;
                case 4:
                        //PLAYER 1 IS INDEX 0
                        P1Label.setEditable(false);
                        P1Label.setVisible(false);
                        P1Output.setEditable(false);
                        P1Output.setPreferredSize(activeOutput);
                        P1Role.setVisible(false);
                        P1Role.setEnabled(false);

                        P1Role.setVisible(false);
                        P1Role.setEnabled(false);

                        
                        pRole = Integer.parseInt(P1Output.getText()) - 1;
                        GUIPObj = gameMaster.playerInit(pRole, 0, P1Label.getText());

                        P1Name.setText(GUIPObj[0].name);
                        P1Name.setVisible(true);
                        P1HP.setText(Integer.toString(GUIPObj[0].hp) + "/" + Integer.toString(GUIPObj[0].maxHP));
                        P1MP.setText(Integer.toString(GUIPObj[0].mp));

                        P1Output.setText("");

                        if (pRole == 3)
                        {
                            P1Name.setText("J. Chidella, DMV Champ");
                        }
                        // P1Label.setEditable(false);
                        // P1Label.setVisible(false);
                        // P1Output.setEditable(false);
                        // P1Output.setText("");
                        // pRole = Integer.parseInt(P1Output.getText()) - 1;
                        // GUIPObj = gameMaster.playerInit(pRole, 0, P1Label.getText());
                        // P1Name.setText(GUIPObj[0].name);
                        // P1Name.setVisible(true);
                        // P1HP.setText(Integer.toString(GUIPObj[0].hp) + "/" + Integer.toString(GUIPObj[0].maxHP));
                        // P1MP.setText(Integer.toString(GUIPObj[0].mp));
                        // P1Role.setVisible(false);
                        // P1Role.setEnabled(false);
                        break;
                case 5:
                        //PLAYER 2 IS INDEX 1
                        P2Label.setEditable(false);
                        P2Label.setVisible(false);
                        P2Output.setEditable(false);
                        P2Output.setPreferredSize(activeOutput);
                        P2Role.setVisible(false);
                        P2Role.setEnabled(false);

                        P2Role.setVisible(false);
                        P2Role.setEnabled(false);

                        
                        pRole = Integer.parseInt(P2Output.getText()) - 1;
                        GUIPObj = gameMaster.playerInit(pRole, 1, P2Label.getText());

                        P2Name.setText(GUIPObj[1].name);
                        P2Name.setVisible(true);
                        P2HP.setText(Integer.toString(GUIPObj[1].hp) + "/" + Integer.toString(GUIPObj[1].maxHP));
                        P2MP.setText(Integer.toString(GUIPObj[1].mp));

                        P2Output.setText("");
                        if (pRole == 3)
                        {
                            P2Name.setText("J. Chidella, DMV Champ");
                        }
                        break;
                case 6:
                        //PLAYER 3 IS INDEX 2
                        P3Label.setEditable(false);
                        P3Label.setVisible(false);
                        P3Output.setEditable(false);
                        P3Output.setPreferredSize(activeOutput);
                        P3Role.setVisible(false);
                        P3Role.setEnabled(false);

                        P3Role.setVisible(false);
                        P3Role.setEnabled(false);

                        
                        pRole = Integer.parseInt(P3Output.getText()) - 1;
                        GUIPObj = gameMaster.playerInit(pRole, 2, P3Label.getText());

                        P3Name.setText(GUIPObj[2].name);
                        P3Name.setVisible(true);
                        P3HP.setText(Integer.toString(GUIPObj[2].hp) + "/" + Integer.toString(GUIPObj[2].maxHP));
                        P3MP.setText(Integer.toString(GUIPObj[2].mp));

                        if (pRole == 3)
                        {
                            P3Name.setText("J. Chidella, DMV Champ");
                        }

                        P3Output.setText("");
                        // P3Label.setEditable(false);
                        // P3Label.setVisible(false);
                        // P3Output.setEditable(false);
                        // P3Output.setText("");
                        // pRole = Integer.parseInt(P3Output.getText()) - 1;
                        // GUIPObj = gameMaster.playerInit(pRole, 2, P3Label.getText());
                        // P3Name.setText(GUIPObj[2].name);
                        // P3Name.setVisible(true);
                        // P3HP.setText(Integer.toString(GUIPObj[2].hp) + "/" + Integer.toString(GUIPObj[2].maxHP));
                        // P3MP.setText(Integer.toString(GUIPObj[2].mp));
                        // P3Role.setVisible(false);
                        // P3Role.setEnabled(false);
                        break;
                case 7:
                        contValue = !contValue;
                       if (contValue) //True
                       {
                            continueButton.setEnabled(false);
                       } 
                       else if (!contValue) //False
                       {
                            continueButton.setEnabled(true);
                       }
            }
        }

        void contPrimer()
        {
            contValue = false;
            continueButton.setEnabled(true);
        }

        void playerOutput(int index, String newOutput)
        {
            //int currHP;
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
                        break;
                case 1:
                        P2Output.setFont(new Font("Arial", Font.PLAIN, 10));
                        P2Output.setText(newOutput);
                        // currHP = Integer.parseInt(P1HP.getText());
                        // GUIPObj[index].hp = (entity.HPset(gameMaster.playerUpdate(), -(currHP - GUIPObj[index].maxHP), index));
                        // GUIPObj[index].hp = GUIPObj[index].maxHP;
                        // P2HP.setText(Integer.toString(GUIPObj[1].maxHP) + "/" + Integer.toString(GUIPObj[1].maxHP));
                        break;
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

        public String grabGMOutput(int currChapIndex)
        {
            return gameMaster.GUIGMUpdate(currChapIndex);
        }
        
        public void combatOutsource(int player, int monster)
        {
            if (GUIPObj[z].hp > 0 && !GUIPObj[z].victory && !GUIPObj[z].defeat)
            {
                //GUIPObj = gameMaster.playerUpdate();
                combatProceeding = "";
                //String inProgressString = "";
                switch(player)
                {
                case 0:
                        combatProceeding = gameMaster.combatInit(player, monster, chapIndex[z]);

                        GMOutput.setText(combatProceeding);
                        GUIPObj = gameMaster.playerUpdate();

                        //GUIPObj = gameMaster.playerUpdate();
                        P1HP.setText(Integer.toString(GUIPObj[player].hp) + "/" + Integer.toString(GUIPObj[player].maxHP));
                        P1MP.setText(Integer.toString(GUIPObj[player].mp));
                        if (GUIPObj[player].hp <= 0)
                        {
                            P1Output.setText("Fallen in battle against " + GUIMObj[monster].name);
                            //GUIPObj[0].defeat = true;
                            //gameMaster.GUIPlayerUpdate(GUIPObj);
                        }
                        else if (GUIPObj[player].hp > 0)
                        {
                            P1Output.setText("Defeated " + GUIMObj[monster].name + " in combat!");
                            //gameMaster.GUIPlayerUpdate(GUIPObj);
                        }
                        GMOutput.setPreferredSize(new Dimension(300, 350));
                        GMScroll.setPreferredSize(new Dimension(30, 350));
                        break;
                case 1:
                        combatProceeding = gameMaster.combatInit(player, monster, chapIndex[z]);

                        GMOutput.setText(combatProceeding);
                        GUIPObj = gameMaster.playerUpdate();

                        //GUIPObj = gameMaster.playerUpdate();
                        P2HP.setText(Integer.toString(GUIPObj[player].hp) + "/" + Integer.toString(GUIPObj[player].maxHP));
                        P2MP.setText(Integer.toString(GUIPObj[player].mp));
                        if (GUIPObj[player].hp <= 0)
                        {
                            P2Output.setText("Fallen in battle against " + GUIMObj[monster].name);
                            //GUIPObj[1].defeat = true;
                            //gameMaster.GUIPlayerUpdate(GUIPObj);
                        }
                        else if (GUIPObj[player].hp > 0)
                        {
                            P2Output.setText("Defeated " + GUIMObj[monster].name + " in combat!");
                            //gameMaster.GUIPlayerUpdate(GUIPObj);
                        }
                        GMOutput.setPreferredSize(new Dimension(300, 350));
                        GMScroll.setPreferredSize(new Dimension(30, 350));
                        break;
                case 2:
                        combatProceeding = gameMaster.combatInit(player, monster, chapIndex[z]);

                        GMOutput.setText(combatProceeding);
                        GUIPObj = gameMaster.playerUpdate();

                        //GUIPObj = gameMaster.playerUpdate();
                        P3HP.setText(Integer.toString(GUIPObj[player].hp) + "/" + Integer.toString(GUIPObj[player].maxHP));
                        P3MP.setText(Integer.toString(GUIPObj[player].mp));
                        if (GUIPObj[player].hp <= 0)
                        {
                            P3Output.setText("Fallen in battle against " + GUIMObj[monster].name);
                            //GUIPObj[2].defeat = true;
                            //gameMaster.GUIPlayerUpdate(GUIPObj);
                        }
                        else if (GUIPObj[player].hp > 0)
                        {
                            P3Output.setText("Defeated " + GUIMObj[monster].name + " in combat!");
                            //gameMaster.GUIPlayerUpdate(GUIPObj);
                        }
                        GMOutput.setPreferredSize(new Dimension(300, 350));
                        GMScroll.setPreferredSize(new Dimension(30, 350));
                        break;
                }
            }
            // else if (GUIPObj[z].defeat)
            // {
            //     switch (player)
            //     {
            //         case 0: 
            //                 GMOutput.setText
            //     }
            // } needed?
            //GUIPObj = gameMaster.playerUpdate();

        }

        private void endGameCheck()
        {
            if ((GUIPObj[0].hp <= 0) && (GUIPObj[1].hp <= 0) && (GUIPObj[2].hp <= 0))
            {
                GMOutput.setText("At the hands of those opposed to them, the party lay battered and defeated. Their goal just out of reach, their fates sealed, and the spoils promised to them left undisturbed," +
                                " awaiting whomever shall try next, in their stead....");
                nextButton.setEnabled(false);
            }
            else if (GUIPObj[0].victory && GUIPObj[1].victory && GUIPObj[2].victory)
            {
                GMOutput.setText("With spoils claimed and pockets full, and hopefully not full of holes or battle scars, our adventurers scamper off triumphantly into the beyond. The world may not be " + 
                                    "truly prepared for their next escapade, be it saccarine, melancholic, exhilarating, or even soulrending...");
                nextButton.setEnabled(false);
            }
            else if ((GUIPObj[0].victory || GUIPObj[0].defeat) && (GUIPObj[1].victory || GUIPObj[1].defeat) && (GUIPObj[2].victory || GUIPObj[2].defeat))
            {
                GMOutput.setText("Our stories at their ends, only time shall tell what becomes of those who made off into the night with their newfound riches. As " + 
                                    " for those whose physical being remains on those accursed grounds of the Alchemist...\n\nOnly time shall tell whether their ends today will remain the same forever...");
                nextButton.setEnabled(false);
            }
        }
}