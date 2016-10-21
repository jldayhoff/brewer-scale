package org.umuc.swen.capstone.brewer.model.mapping;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Stack;
import org.cytoscape.model.CyRow;
import org.jcolorbrewer.ColorBrewer;

/**
 * Created by cwancowicz on 10/17/16.
 */
public class DivergingBrewerScaleMapper extends AbstractBrewerScaleMapper {

  private final Stack<Color> positiveColorStack;
  private final Stack<Color> negativeColorStack;

  public DivergingBrewerScaleMapper(String columnName, Stack<Color> positiveColorStack, Stack<Color> negativeColorStack) {
    super(columnName, OrderType.ASCENDING);
    this.positiveColorStack = positiveColorStack;
    this.negativeColorStack = negativeColorStack;
  }

  @Override
  public MapType getMapType() {
    return MapType.DIVERGING;
  }

  @Override
  protected Optional<Color> getColor(CyRow row) {
    if (Objects.isNull(row.get(columnName, Double.class))) {
      return Optional.empty();
    }
    else if (row.get(columnName, Double.class) > 0) {
      return Optional.of(positiveColorStack.pop());
    }
    else {
      return Optional.of(negativeColorStack.pop());
    }
  }
}
