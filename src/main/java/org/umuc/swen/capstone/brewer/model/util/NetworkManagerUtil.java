package org.umuc.swen.capstone.brewer.model.util;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyRow;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.jcolorbrewer.ColorBrewer;
import org.umuc.swen.capstone.brewer.CyActivator;
import org.umuc.swen.capstone.brewer.model.mapping.ContinuousBrewerScaleMapper;
import org.umuc.swen.capstone.brewer.model.mapping.DiscreteBrewerScaleMapper;
import org.umuc.swen.capstone.brewer.model.mapping.DivergingBrewerScaleMapper;
import org.umuc.swen.capstone.brewer.model.mapping.FilterMapper;
import org.umuc.swen.capstone.brewer.model.mapping.MapType;
import org.umuc.swen.capstone.brewer.model.mapping.OrderType;

/**
 * Created by cwancowicz on 9/24/16.
 */
public class NetworkManagerUtil {

  private final CyActivator cyActivator;

  public NetworkManagerUtil(CyActivator cyActivator) {
    this.cyActivator = cyActivator;
  }

  /**
   * Applies a {@link FilterMapper} to each network available
   * in the {@link CyNetworkManager}
   *
   */
  public void applyFilterToNetworks(String columnName, ColorBrewer colorBrewer, MapType mapType) {
    this.cyActivator.getNetworkManager().getNetworkSet()
            .stream()
            .forEach(cyNetwork -> applyFilterToNetwork(cyNetwork, columnName, colorBrewer, mapType));
  }

  /**
   * Applies a {@link FilterMapper} to specified {@link CyNetwork} and its
   * {@link Collection} of {@link CyNetworkView}
   *
   * @param network {@link CyNetwork}
   */
  public void applyFilterToNetwork(CyNetwork network, String columnName, ColorBrewer colorBrewer, MapType mapType) {
    CyNetworkViewManager viewManager = cyActivator.getNetworkViewManager();
    Collection<CyNetworkView> networkViews = viewManager.getNetworkViews(network);
    FilterMapper mapper = createFilterMapper(network, columnName, colorBrewer, mapType);
    List<CyRow> cyRows = mapper.sortRows(network.getDefaultNodeTable().getAllRows());
    cyRows.stream()
            .forEach(row -> mapper.applyFilterMapping(networkViews, network.getNode(row.get(CyNetwork.SUID, Long.class)), row));
    networkViews.stream().forEach(CyNetworkView::updateView);
  }

  private FilterMapper createFilterMapper(CyNetwork cyNetwork, String columnName, ColorBrewer colorBrewer, MapType mapType) {

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

  private FilterMapper createDiscreteFilterMapper(String columnName, ColorBrewer colorBrewer, CyNetwork cyNetwork) {
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

  private FilterMapper createSequentialFilterMapper(String columnName, ColorBrewer colorBrewer, CyNetwork cyNetwork) {
    return new ContinuousBrewerScaleMapper(columnName, colorBrewer,
            cyNetwork.getDefaultNodeTable().getAllRows().stream()
            .map(row -> row.get(columnName, Object.class))
            .collect(Collectors.toList())
    );
  }

  private FilterMapper createDivergentFilterMapper(String columnName, ColorBrewer colorBrewer, CyNetwork cyNetwork) {
    Integer sizeOfNetwork = cyNetwork.getDefaultNodeTable().getRowCount();
    List<CyRow> rows = DivergingBrewerScaleMapper.sortRows(
            cyNetwork.getDefaultNetworkTable().getAllRows(), columnName, OrderType.ASCENDING
    );
    Optional<CyRow> optional = rows.stream().filter(row -> row.get(columnName, Double.class) >= 0).findFirst();

    Stack<Color> positiveStack = new Stack();
    Stack<Color> negativeStack = new Stack();

    int colorBrewerSize;
    if (optional.isPresent()) {
      int indexOfZero = rows.indexOf(optional.get());
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
    List<Color> negativeColors = colors.subList(0, colors.size()/2);
    Collections.reverse(negativeColors);

    positiveStack.addAll(positiveColors);
    negativeStack.addAll(negativeColors);

    return null;
  }
}
