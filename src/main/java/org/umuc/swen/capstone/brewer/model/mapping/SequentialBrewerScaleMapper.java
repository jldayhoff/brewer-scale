package org.umuc.swen.capstone.brewer.model.mapping;

import java.util.Collection;
import java.util.Optional;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyRow;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.umuc.swen.capstone.brewer.model.range.NetworkRangeMapper;

/**
 * Created by cwancowicz on 9/29/16.
 */
public class SequentialBrewerScaleMapper implements FilterMapper {

  private final NetworkRangeMapper networkRangeMapper;
  private final String columnName;

  public SequentialBrewerScaleMapper(NetworkRangeMapper networkRangeMapper, String columnName) {
    this.networkRangeMapper = networkRangeMapper;
    this.columnName = columnName;
  }

  @Override
  public void applyFilterMapping(Collection<CyNetworkView> networkViews, CyNode node, CyRow row) {
    Optional.ofNullable(networkRangeMapper.getBrewerColorInRange(row.get(this.columnName, Object.class)))
            .ifPresent(brewerColor -> {
              networkViews.stream()
                      .forEach(networkView -> networkView.getNodeView(node).setLockedValue(BasicVisualLexicon.NODE_FILL_COLOR,
                              brewerColor.getColor()));
            });
  }
}
