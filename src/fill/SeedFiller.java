package fill;


import view.Raster;

import java.awt.*;

public class SeedFiller implements Filler {

    private Raster raster;
    private int background, fillColor;

    public SeedFiller(Raster raster, int fillColor) {
        this.raster = raster;
        this.fillColor = fillColor;
    }

    @Override
    public void setRaster(Raster raster) {
        this.raster = raster;
    }

    @Override
    public void setColor(Color color) {
        fillColor = color.getRGB();
    }

    @Override
    public void fill(int x, int y) {
        background = raster.getPixel(x, y);
        seed(x, y);
    }

    // pozor na rekurzivní volání
    // nutné upravit parametr pro VM "-Xss100m"
    // https://stackoverflow.com/questions/4967885/jvm-option-xss-what-does-it-do-exactly
    private void seed(int ax, int ay) {
        if (ax >= 0 && ay >= 0 && ax < Raster.WIDTH && ay < Raster.HEIGHT) {
            if (background == raster.getPixel(ax, ay)) {
                raster.drawPixel(ax, ay,fillColor);
                seed(ax + 1, ay);
                seed(ax - 1, ay);
                seed(ax, ay + 1);
                seed(ax, ay - 1);
            }
        }
    }
}
