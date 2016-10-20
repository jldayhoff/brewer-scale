package org.umuc.swen.capstone.brewer.model.mapping;

import java.awt.Color;
import java.util.Map;
import java.util.Optional;
import org.cytoscape.model.CyRow;

/**
 * Created by cwancowicz on 10/14/16.
 */
public class DiscreteBrewerScaleMapper extends AbstractBrewerScaleMapper {

  private Map<Object, Color> valueColorMap = null;

  public DiscreteBrewerScaleMapper(final Map<Object, Color> valueColorMap, String columnName, OrderType orderType) {
    super(columnName, orderType);
    this.valueColorMap = valueColorMap;
  }

  public DiscreteBrewerScaleMapper(final Map<Object, Color> valueColorMap, String columnName) {
    this(valueColorMap, columnName, OrderType.NONE);
  }

  @Override
  protected Optional<Color> getColor(CyRow row) {
    return Optional.ofNullable(valueColorMap.get(row.get(this.columnName, Object.class)));
  }

  @Override
  public MapType getMapType() {
    return MapType.DISCRETE;
  }
}
