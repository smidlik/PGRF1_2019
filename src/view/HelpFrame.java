package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

 class HelpFrame extends JFrame {


     private JTextArea description;


        HelpFrame(){
            init();
        }

        private void init() {
            setSize(300,300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            setUndecorated(true);
            setVisible(true);

            Container pane = this.getContentPane();
            pane.setLayout(new BorderLayout());


            JTextArea title = new JTextArea("Nápověda:");
            description=new JTextArea();

            title.setLineWrap(true);
            title.setEditable(false);
            title.setBackground(Color.GRAY);
            title.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            title.setFont(new Font("Courier", Font.BOLD,15));
            description.setLineWrap(true);
            description.setEditable(false);
            description.setBackground(Color.LIGHT_GRAY);

            description.setText("Ovládání:\n" +
                    "\n" +
                    "   C:       Smazání rasteru\n" +
                    "\n" +
                    "N_OBJECT\n" +
                    "\n" +
                    "   W:       Přidat hranu N uhelniku\n" +
                    "   S:       Odebrat hranu N uhelniku\n" +
                    "\n" +
                    "\n" +
                    "   Kliknutím zavři nápovědu....");

            pane.add(description, BorderLayout.CENTER);
            pane.add(title, BorderLayout.PAGE_START);

            description.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    dispose();

                }
            });
            pane.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                        dispose();
                }
            });



        }
    }


