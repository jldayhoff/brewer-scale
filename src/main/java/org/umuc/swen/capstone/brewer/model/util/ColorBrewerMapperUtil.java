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
import org.umuc.swen.capstone.brewer.model.mapping.BrewerScaleMapperFactory;
import org.umuc.swen.capstone.brewer.model.mapping.FilterMapper;
import org.umuc.swen.capstone.brewer.model.mapping.MapType;

/**
 * Created by cwancowicz on 9/24/16.
 */
public class ColorBrewerMapperUtil {

  private final CyActivator cyActivator;

  public ColorBrewerMapperUtil(CyActivator cyActivator) {
    this.cyActivator = cyActivator;
  }

  /**
   * Applies a {@link FilterMapper} to each network available
   * in the {@link CyNetworkManager}
   *
   * @param columnName  {@link String}
   * @param colorBrewer {@link ColorBrewer}
   * @param mapType     {@link MapType}
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
   * @param network     {@link CyNetwork}
   * @param columnName  {@link String}
   * @param colorBrewer {@link ColorBrewer}
   * @param mapType     {@link MapType}
   */
  public void applyFilterToNetwork(CyNetwork network, String columnName, ColorBrewer colorBrewer, MapType mapType) {
    CyNetworkViewManager viewManager = cyActivator.getNetworkViewManager();
    Collection<CyNetworkView> networkViews = viewManager.getNetworkViews(network);
    FilterMapper mapper = BrewerScaleMapperFactory.createFilterMapper(network, columnName, colorBrewer, mapType);
    network.getDefaultNodeTable().getAllRows()
            .stream()
            .forEach(row -> mapper.applyFilterMapping(
                    networkViews, network.getNode(row.get(CyNetwork.SUID, Long.class)), row));
    networkViews.stream().forEach(CyNetworkView::updateView);
  }


}
