package view;


import controller.Controler;
import controller.LineControler;
import controller.NObjectControler;
import controller.PolygonControler;
import misc.AppModes;
import misc.Lines;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PGRFWindow extends JFrame {

    private Raster raster;
    private Controler controler;
    private JComboBox<AppModes> controlerBox;
    private JComboBox<Lines> lineBox;
    private Color borderColor;
    private Color fillColor;
    private JPanel controlPanel;
    private JLabel controlerLbl;
    private JLabel lineLbl;

    private JButton btnHelp;
    private JButton btnBorderColor;
    private JButton btnFillColor;

    public PGRFWindow() {

        borderColor = Color.YELLOW;
        fillColor = Color.RED;

        // bez tohoto nastavení se okno zavře, ale aplikace stále běží na pozadí
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("PGRF1 2019 - Šmída Jakub "); // titulek okna


        lineLbl = new JLabel();
        lineLbl.setText("Typ vykreslování: ");
        controlerLbl = new JLabel();
        controlerLbl.setText("Objekt: ");
        lineBox = new JComboBox<>(Lines.values());
        lineBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseLine();
            }
        });

        controlerBox = new JComboBox<>(AppModes.values());
        controlerBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseControler();
            }
        });
        btnHelp = new JButton("Help");
        btnHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HelpFrame();
            }
        });
        btnHelp.setFocusable(false);

        btnBorderColor = new JButton("BorderColor");
        btnBorderColor.setBackground(borderColor);
        btnBorderColor.setOpaque(true);
        btnBorderColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borderColor = JColorChooser.showDialog(PGRFWindow.this, "Border Color", borderColor);
                chooseColor();
                btnBorderColor.setBackground(borderColor);
            }
        });
        btnBorderColor.setFocusable(false);

        btnFillColor = new JButton("FillColor");
        btnFillColor.setBackground(fillColor);
        btnFillColor.setOpaque(true);
        btnFillColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillColor = JColorChooser.showDialog(PGRFWindow.this, "Fill Color", fillColor);
                chooseColor();
                btnFillColor.setBackground(fillColor);

            }
        });
        btnFillColor.setFocusable(false);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        controlerBox.setFocusable(false);
        lineBox.setFocusable(false);
        setFocusable(false);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setFocusable(false);
        controlPanel.add(btnBorderColor);
        controlPanel.add(btnFillColor);
        controlPanel.add(controlerLbl);
        controlPanel.add(controlerBox);
        controlPanel.add(lineLbl);
        controlPanel.add(lineBox);
        controlPanel.add(btnHelp);

        add(controlPanel, BorderLayout.PAGE_START);
        raster = new Raster();
        raster.setFocusable(true);
        raster.grabFocus(); // důležité pro pozdější ovládání z klávesnicer

        add(raster, BorderLayout.CENTER); // vložit plátno do okna
        pack();

        controler = new LineControler(raster, (Lines) lineBox.getSelectedItem(), borderColor, fillColor);

        setLocationRelativeTo(null); // vycentrovat okno
    }

    private void chooseLine() {
        controler.setLines((Lines) lineBox.getSelectedItem());
    }

    private void chooseColor() {
        controler.setBorderColor(borderColor);
        controler.setFillColor(fillColor);
    }

    private void chooseControler() {

        raster.clear();
        switch ((AppModes) controlerBox.getSelectedItem()) {
            case POLYGON:
                controler = new PolygonControler(raster, (Lines) lineBox.getSelectedItem(), borderColor, fillColor);
                break;
            case LINE:
                controler = new LineControler(raster, (Lines) lineBox.getSelectedItem(), borderColor, fillColor);
                break;
            case N_OBJECT:
                controler = new NObjectControler(raster, (Lines) lineBox.getSelectedItem(), borderColor, fillColor);
                break;
        }
    }


}
