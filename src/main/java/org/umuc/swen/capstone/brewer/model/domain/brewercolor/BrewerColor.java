package org.umuc.swen.capstone.brewer.model.domain.brewercolor;

import java.awt.Color;

/**
 * Created by cwancowicz on 9/24/16.
 */
public class BrewerColor {
  private Integer red;
  private Integer blue;
  private Integer green;

  public Color getColor() {
    return new Color(red, green, blue);
  }

  public Integer getRed() {
    return red;
  }

  public void setRed(Integer red) {
    this.red = red;
  }

  public Integer getBlue() {
    return blue;
  }

  public void setBlue(Integer blue) {
    this.blue = blue;
  }

  public Integer getGreen() {
    return green;
  }

  public void setGreen(Integer green) {
    this.green = green;
  }
}
