package org.umuc.swen.capstone.brewer.model.range;

import java.awt.Color;
import org.cytoscape.view.model.ContinuousRange;

/**
 * Created by cwancowicz on 9/29/16.
 */
public class BrewerColorRange implements NetworkRange<Integer> {

  private final ContinuousRange<Integer> continuousRange;
  private final Color brewerColor;

  public BrewerColorRange(ContinuousRange<Integer> continuousRange, Color brewerColor) {
    this.continuousRange = continuousRange;
    this.brewerColor = brewerColor;
  }

  @Override
  public Color getBrewerColorInRange(Integer value) {
    if (continuousRange.inRange(value)) {
      return brewerColor;
    }
    return null;
  }
}
