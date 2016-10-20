package org.umuc.swen.capstone.brewer.model.mapping;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Stack;
import org.cytoscape.model.CyRow;
import org.jcolorbrewer.ColorBrewer;

/**
 * Created by cwancowicz on 9/29/16.
 */
public class ContinuousBrewerScaleMapper extends AbstractBrewerScaleMapper {

  private final List<Object> values;
  private final ColorBrewer colorBrewer;
  private Stack<Color> colors;

  public ContinuousBrewerScaleMapper(String columnName, ColorBrewer colorBrewer, List<Object> values, OrderType orderType) {
    super(columnName, orderType);
    this.values = values;
    this.colorBrewer = colorBrewer;
    this.colors = initializeStack();
  }

  public ContinuousBrewerScaleMapper(String columnName, ColorBrewer colorBrewer, List<Object> values) {
    this(columnName, colorBrewer, values, OrderType.ASCENDING);
  }

  @Override
  protected Optional<Color> getColor(CyRow row) {
    if (colors.empty()) {
      colors = initializeStack();
    }
    return Optional.of(colors.pop());
  }

  @Override
  public MapType getMapType() {
    return MapType.CONTINUOUS;
  }

  private Stack<Color> initializeStack() {
    Stack stack = new Stack();
    stack.addAll(Arrays.asList(colorBrewer.getColorPalette(values.size())));
    return stack;
  }
}
