package controller;

import fill.Filler;
import misc.Lines;
import renderer.Renderer;
import view.Raster;

import java.awt.*;


public abstract class Controler {



    protected boolean fillState = false;
    protected Filler fillMethod;
    protected Renderer renderer;

//    public Controler(Raster raster, Lines lines, Color borderColor, Color fillColor) {
//        renderer = new Renderer(raster, lines, borderColor);
//
//        initObjects(raster);
//
//        initListeners(raster);
//    }

    public abstract void initObjects(Raster raster);

    public abstract void initListeners(Raster raster);

    public void setLines(Lines lines) {
        renderer.setLines(lines);
    }

    public void setBorderColor(Color color) {
        renderer.setColor(color);
    }

    public void setFillColor(Color color) {
        fillMethod.setColor(color);
    }

    public String getFillStateString() {
        if (fillState)
            return "ON";
        else
            return "OFF";
    }
    public void setFillState(boolean fillState) {
        this.fillState = fillState;
    }
    public boolean isFillState() {
        return fillState;
    }



}
