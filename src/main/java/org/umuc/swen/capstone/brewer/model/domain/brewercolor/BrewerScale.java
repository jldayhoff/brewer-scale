package org.umuc.swen.capstone.brewer.model.domain.brewercolor;

import java.util.List;

/**
 * Created by cwancowicz on 9/24/16.
 */
public class BrewerScale {

  private String scaleName;
  private String type;
  private List<BrewerColor> colors;

  public String getScaleName() {
    return scaleName;
  }

  public void setScaleName(String scaleName) {
    this.scaleName = scaleName;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<BrewerColor> getColors() {
    return colors;
  }

  public void setColors(List<BrewerColor> colors) {
    this.colors = colors;
  }
}
