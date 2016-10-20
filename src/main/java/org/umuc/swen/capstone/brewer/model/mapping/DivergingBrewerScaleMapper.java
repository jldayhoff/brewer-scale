package org.umuc.swen.capstone.brewer.model.mapping;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.cytoscape.model.CyRow;
import org.jcolorbrewer.ColorBrewer;

/**
 * Created by cwancowicz on 10/17/16.
 */
public class DivergingBrewerScaleMapper extends AbstractBrewerScaleMapper {

  List<ColorBrewer> colors = new ArrayList<>();

  public DivergingBrewerScaleMapper(String columnName) {
    super(columnName, OrderType.ASCENDING);
  }

  @Override
  public MapType getMapType() {
    return MapType.DIVERGING;
  }

  @Override
  protected Optional<Color> getColor(CyRow row) {

    return null;
  }
}
