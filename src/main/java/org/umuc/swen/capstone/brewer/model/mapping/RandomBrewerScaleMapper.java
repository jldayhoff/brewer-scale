package org.umuc.swen.capstone.brewer.model.mapping;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyRow;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.umuc.swen.capstone.brewer.model.domain.brewercolor.BrewerColor;

/**
 * Created by cwancowicz on 9/29/16.
 */
public class RandomBrewerScaleMapper implements FilterMapper {

  private static final Random RANDOM = new Random();
  private final List<BrewerColor> colorScale;
  private final int max;

  public RandomBrewerScaleMapper(List<BrewerColor> colorScale) {
    this.colorScale = colorScale;
    this.max = colorScale.size();
  }

  @Override
  public void applyFilterMapping(Collection<CyNetworkView> networkViews, CyNode node, CyRow row) {
    networkViews.stream()
            .forEach(networkView -> networkView.getNodeView(node).setLockedValue(BasicVisualLexicon.NODE_FILL_COLOR,
                    getNextRandomColor().getColor()));
  }

  @Override
  public MapType getMapType() {
    return null;
  }

  @Override
  public OrderType getOrderType() {
    return null;
  }

  @Override
  public List<CyRow> sortRows(List<CyRow> list) {
    return null;
  }

  private BrewerColor getNextRandomColor() {
    return colorScale.get(RANDOM.nextInt(this.max));
  }
}
