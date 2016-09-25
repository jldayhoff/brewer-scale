package org.umuc.swen.capstone.brewer.model.util;

import java.util.Collection;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.CyNetworkViewManager;
import org.umuc.swen.capstone.brewer.CyActivator;
import org.umuc.swen.capstone.brewer.model.mapping.FilterMapper;

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
   * @param mapper {@link FilterMapper}
   */
  public void applyFilterToNetworks(FilterMapper mapper) {
    this.cyActivator.getNetworkManager().getNetworkSet()
            .stream()
            .forEach(cyNetwork -> applyFilterToNetwork(cyNetwork, mapper));
  }

  /**
   * Applies a {@link FilterMapper} to specified {@link CyNetwork} and its
   * {@link Collection} of {@link CyNetworkView}
   *
   * @param network {@link CyNetwork}
   * @param mapper {@link FilterMapper}
   */
  public void applyFilterToNetwork(CyNetwork network, FilterMapper mapper) {
    CyNetworkViewManager viewManager = cyActivator.getNetworkViewManager();
    Collection<CyNetworkView> networkViews = viewManager.getNetworkViews(network);

    network.getNodeList()
            .stream()
            .forEach(node -> mapper.applyFilterMapping(networkViews, node, network.getRow(node)));
    networkViews.stream().forEach(CyNetworkView::updateView);
  }
}
