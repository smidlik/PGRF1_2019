package fill;

import view.Raster;

import java.awt.*;

public interface Filler {
    void fill(int x,int y);
    void setRaster(Raster raster);
    void setColor(Color color);
}
