package org.umuc.swen.capstone.brewer.model.domain.brewercolor;

import java.util.List;

/**
 * Created by cwancowicz on 9/24/16.
 */
public class BrewerColorScale {
  private String name;
  private String type;
  private List<List<BrewerColor>> scales;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<List<BrewerColor>> getScales() {
    return scales;
  }

  public void setScales(List<List<BrewerColor>> scales) {
    this.scales = scales;
  }
}
