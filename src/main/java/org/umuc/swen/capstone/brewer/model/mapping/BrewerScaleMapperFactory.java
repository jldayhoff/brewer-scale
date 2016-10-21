package org.umuc.swen.capstone.brewer.model.mapping;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyRow;
import org.jcolorbrewer.ColorBrewer;

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
    List<Color> colors = Arrays.asList(colorBrewer.getColorPalette(values.size()));

    Iterator<Object> iterator = values.iterator();
    Iterator<Color> colorIterator = colors.iterator();
    Map<Object, Color> colorMap = new HashMap();
    while (iterator.hasNext()) {
      colorMap.put(iterator.next(), colorIterator.next());
    }
    return new DiscreteBrewerScaleMapper(colorMap, columnName);
  }

  private static FilterMapper createSequentialFilterMapper(String columnName, ColorBrewer colorBrewer, CyNetwork cyNetwork) {
    return new ContinuousBrewerScaleMapper(columnName, colorBrewer,
            cyNetwork.getDefaultNodeTable().getAllRows().stream()
                    .map(row -> row.get(columnName, Object.class))
                    .collect(Collectors.toList())
    );
  }

  private static FilterMapper createDivergentFilterMapper(String columnName, ColorBrewer colorBrewer, CyNetwork cyNetwork) {
    Integer sizeOfNetwork = cyNetwork.getDefaultNodeTable().getRowCount();
    List<CyRow> rows = DivergingBrewerScaleMapper.sortRows(
            cyNetwork.getDefaultNodeTable().getAllRows(), columnName, OrderType.ASCENDING,
            cyNetwork.getDefaultNodeTable().getColumn(columnName).getType()
    );
    Class type = cyNetwork.getDefaultNodeTable().getColumn(columnName).getType();
    Optional<CyRow> optional = rows.stream().filter(row -> Objects.nonNull(row.get(columnName, type)) && (Double) row.get(columnName, type) >= 0).findFirst();

    Stack<Color> positiveStack = new Stack();
    Stack<Color> negativeStack = new Stack();

    Integer indexOfZero;
    if (optional.isPresent()) {
      indexOfZero = rows.indexOf(optional.get());
    }
    else {
      indexOfZero = 0;
    }

    int colorBrewerSize;
    if (optional.isPresent()) {
      if (indexOfZero <= rows.size()/2) {
        colorBrewerSize = 2 * (rows.size() + 1 - indexOfZero);
      }
      else {
        colorBrewerSize = 2 * indexOfZero;
      }
    }
    else {
      colorBrewerSize = rows.size();
    }

    List<Color> colors = Arrays.asList(colorBrewer.getColorPalette(colorBrewerSize));
    List<Color> positiveColors = colors.subList(colors.size()/2, colors.size());
    List<Color> negativeColors = colors.subList(colors.size()/2 - indexOfZero, colors.size()/2);
    Collections.reverse(negativeColors);

    positiveStack.addAll(positiveColors);
    negativeStack.addAll(negativeColors);

    return new DivergingBrewerScaleMapper(columnName, positiveStack, negativeStack);
  }
}
