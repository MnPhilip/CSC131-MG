package JCRPG_gui;

import JCRPG_code.*;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")

public class LauncherGUI extends JFrame 
{
    String nameHolder;
    int HPHolder;
    int MPHolder;
    int locHolder; 
    
        private JButton nextButton;
        private JButton quitButton;
        private JButton resetButton;

        private JTextField P1Name;
        private JTextField P2Name;
        private JTextField P3Name;

        private JTextField P1HP;
        private JTextField P2HP;
        private JTextField P3HP;

        private JTextField P1MP;
        private JTextField P2MP;
        private JTextField P3MP;

        private JTextField P1Output;
        private JTextField P2Output;
        private JTextField P3Output;

        private JTextField GMOutput;
        private JTextField GMName;

        public LauncherGUI()
        {
            try {UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
            {
                System.out.println(e);
            }

            initGUI();
            setTitle("JCRPG - Adventure Emulator");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationByPlatform(true);
            pack();
            setVisible(true);
        }

        private void initGUI()
        {
            nextButton = new JButton("Next");
            nextButton.addActionListener((ActionEven) -> {buttonListener(1); });

            resetButton = new JButton("Reset");
            resetButton.addActionListener((ActionEven) -> {buttonListener(2);});

            quitButton = new JButton("Quit");
            quitButton.addActionListener((ActionEven) -> {buttonListener(3);});

            P1Name = new JTextField();
            P1Name.setEditable(true);
            P2Name = new JTextField();
            P2Name.setEditable(true);
            P3Name = new JTextField();
            P3Name.setEditable(true);

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
            P1Output.setEditable(false);
            P2Output.setEditable(false);
            P3Output.setEditable(false);

            GMOutput = new JTextField();
            GMOutput.setEditable(false);
            GMName = new JTextField();
            GMName.setEditable(false);

            Dimension dim = new Dimension(750, 200);
            setSize(dim);
            P1Name.setPreferredSize(new Dimension(100, 20));
            P2Name.setPreferredSize(new Dimension(100, 20));
            P3Name.setPreferredSize(new Dimension(100, 20));

            P1HP.setPreferredSize(new Dimension(30, 30));
            P2HP.setPreferredSize(new Dimension(30, 30));
            P3HP.setPreferredSize(new Dimension(30, 30));

            P1MP.setPreferredSize(new Dimension(30, 30));
            P2MP.setPreferredSize(new Dimension(30, 30));
            P3MP.setPreferredSize(new Dimension(30, 30));

            P1Output.setPreferredSize(new Dimension(30, 30));
            P2Output.setPreferredSize(new Dimension(30, 30));
            P3Output.setPreferredSize(new Dimension(30, 30));

            GMOutput.setPreferredSize(new Dimension(300, 100));

            //MAIN PANEL SETUP
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new GridBagLayout());

            // mainPanel.add(new JButton("Next"), GridBagLayout.SOUTH);
            // mainPanel.add(new JButton("Reset"), GridBagLayout.SOUTH);
            // mainPanel.add(new JButton("Quit"), GridBagLayout.SOUTH);
            mainPanel.add(new JLabel("GM:"), getConstraints(1, 8, GridBagConstraints.LINE_START));
            mainPanel.add(GMOutput, getConstraints(2, 8, GridBagConstraints.LINE_END));



            mainPanel.add(P1Name, getConstraints(0, 0, GridBagConstraints.LINE_START));
            mainPanel.add(P1Output, getConstraints(0, 3, GridBagConstraints.LINE_START));
            mainPanel.add(P1HP, getConstraints(0, 1, GridBagConstraints.LINE_START));
            mainPanel.add(P1MP, getConstraints(0, 2, GridBagConstraints.LINE_START));
            //mainPanel.add(new JLabel("Player One:"), getConstraints(0, 1, GridBagConstraints.LINE_START));

            mainPanel.add(P2Name,getConstraints(2, 0, GridBagConstraints.CENTER));
            mainPanel.add(P2Output, getConstraints(2, 3, GridBagConstraints.LINE_START));
            mainPanel.add(P2HP, getConstraints(2, 1, GridBagConstraints.LINE_START));
            mainPanel.add(P2MP, getConstraints(2, 2, GridBagConstraints.LINE_START));
           // mainPanel.add(new JLabel("Player Two:"), getConstraints(0, 1, GridBagConstraints.CENTER));

            mainPanel.add(P3Name,getConstraints(4, 0, GridBagConstraints.LINE_START));
            mainPanel.add(P3Output, getConstraints(4, 3, GridBagConstraints.LINE_START));
            mainPanel.add(P3HP, getConstraints(4, 1, GridBagConstraints.LINE_START));
            mainPanel.add(P3MP, getConstraints(4, 2, GridBagConstraints.LINE_START));
          // mainPanel.add(new JLabel("Player Three:"), getConstraints(0, 1, GridBagConstraints.LINE_START));
        

            //BUTTON PANEL SETUP
            JPanel buttonPanel = new JPanel();

            buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            buttonPanel.add(nextButton);
            buttonPanel.add(resetButton);
            buttonPanel.add(quitButton);

            add(buttonPanel, BorderLayout.SOUTH);
            add(mainPanel, BorderLayout.CENTER);

            pack();
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
            switch (Btype)
            {
                case 1: //Initiate next step through code in GM class
                        break;
                case 2: //Re-start code, maybe make special re-initialize class in GM?
                        break;
                case 3: //Shut everything down
                        System.exit(0);
            }
        }
}
