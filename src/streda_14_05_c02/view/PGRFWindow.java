package streda_14_05_c02.view;

import javax.swing.*;

public class PGRFWindow extends JFrame {

    private final Raster raster;

    public PGRFWindow() {
        // bez tohoto nastavení se okno zavře, ale aplikace stále běží na pozadí
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600); // velikost okna
        setLocationRelativeTo(null); // vycentrovat okno
        setTitle("PGRF1 cvičení"); // titulek okna

        raster = new Raster();
        raster.setFocusable(true);
        raster.grabFocus(); // důležité pro pozdější ovládání z klávesnice
        add(raster); // vložit plátno do okna
    }

    public Raster getRaster() {
        return raster;
    }

}
