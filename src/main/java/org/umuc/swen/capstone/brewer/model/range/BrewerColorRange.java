package org.umuc.swen.capstone.brewer.model.range;

import org.cytoscape.view.model.ContinuousRange;
import org.umuc.swen.capstone.brewer.model.domain.brewercolor.BrewerColor;

/**
 * Created by cwancowicz on 9/29/16.
 */
public class BrewerColorRange implements NetworkRange<Integer> {

  private final ContinuousRange<Integer> continuousRange;
  private final BrewerColor brewerColor;

  public BrewerColorRange(ContinuousRange<Integer> continuousRange, BrewerColor brewerColor) {
    this.continuousRange = continuousRange;
    this.brewerColor = brewerColor;
  }

  @Override
  public BrewerColor getBrewerColorInRange(Integer value) {
    if (continuousRange.inRange(value)) {
      return brewerColor;
    }
    return null;
  }
}
