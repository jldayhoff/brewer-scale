package org.umuc.swen.capstone.brewer.model.domain.brewercolor;

import java.util.List;

/**
 * Created by cwancowicz on 9/24/16.
 */
public class BrewerColorScale {
  private List<BrewerScale> scales;

  public List<BrewerScale> getScales() {
    return scales;
  }

  public void setScales(List<BrewerScale> scales) {
    this.scales = scales;
  }
}
