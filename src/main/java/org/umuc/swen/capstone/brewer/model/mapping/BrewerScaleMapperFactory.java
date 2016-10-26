package org.umuc.swen.capstone.brewer.model.mapping;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyRow;
import org.jcolorbrewer.ColorBrewer;
import org.umuc.swen.capstone.brewer.model.exception.InvalidBrewerColorMapper;
import org.umuc.swen.capstone.brewer.model.exception.InvalidDataException;
import org.umuc.swen.capstone.brewer.model.exception.InvalidElement;

/**
 * Created by cwancowicz on 10/20/16.
 */
public class BrewerScaleMapperFactory {

  public static FilterMapper createFilterMapper(CyNetwork cyNetwork, String columnName, ColorBrewer colorBrewer, MapType mapType) {

    switch (mapType) {
      case DISCRETE:
        return createDiscreteFilterMapper(columnName, colorBrewer, cyNetwork);
      case CONTINUOUS:
        return createSequentialFilterMapper(columnName, colorBrewer, cyNetwork);
      case DIVERGING:
        return createDivergentFilterMapper(columnName, colorBrewer, cyNetwork);
      default:
        throw new InternalError(String.format("Unsupported Map Type [%s]", mapType));
    }
  }

  private static FilterMapper createDiscreteFilterMapper(String columnName, ColorBrewer colorBrewer, CyNetwork cyNetwork) {
    Set<Object> values = cyNetwork.getDefaultNodeTable().getAllRows()
            .stream()
            .map(row -> row.get(columnName, Object.class))
            .collect(Collectors.toSet());
    return new DiscreteBrewerScaleMapper(values, colorBrewer, columnName);
  }

  private static FilterMapper createSequentialFilterMapper(String columnName, ColorBrewer colorBrewer, CyNetwork cyNetwork) {
    return new ContinuousBrewerScaleMapper(columnName, colorBrewer,
            cyNetwork.getDefaultNodeTable().getAllRows().stream()
                    .map(row -> row.get(columnName, Object.class))
                    .collect(Collectors.toList())
    );
  }

  private static FilterMapper createDivergentFilterMapper(String columnName, ColorBrewer colorBrewer, CyNetwork cyNetwork) {
    Class type = cyNetwork.getDefaultNodeTable().getColumn(columnName).getType();
    Integer maxValue;
    if (type == Integer.class) {
      CyRow cyRow = cyNetwork.getDefaultNodeTable().getAllRows()
              .stream()
              .filter(row -> Objects.nonNull(row.get(columnName, Integer.class)))
              .max((row1, row2) -> Integer.valueOf(Math.abs(row1.get(columnName, Integer.class)))
                      .compareTo(Integer.valueOf(Math.abs(row2.get(columnName, Integer.class)))))
              .orElseThrow(() -> new InvalidDataException(columnName));
      maxValue = Math.abs(cyRow.get(columnName, Integer.class));
    }
    else if (type == Double.class) {
      CyRow cyRow = cyNetwork.getDefaultNodeTable().getAllRows()
              .stream()
              .filter(row -> Objects.nonNull(row.get(columnName, Double.class)))
              .max((row1, row2) -> Double.valueOf(Math.abs(row1.get(columnName, Double.class)))
                      .compareTo(Double.valueOf(Math.abs(row2.get(columnName, Double.class)))))
              .orElseThrow(() -> new InvalidDataException(columnName));
      maxValue = Double.valueOf(Math.ceil(Math.abs(cyRow.get(columnName, Double.class)))).intValue();
    }
    else {
      throw new InvalidBrewerColorMapper(MapType.DIVERGING, InvalidElement.INVALID_DATA_TYPE);
    }

    return new DivergingBrewerScaleMapper(columnName, colorBrewer, maxValue.doubleValue(), type);
  }
}
