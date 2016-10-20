package org.umuc.swen.capstone.brewer.model.mapping;

import java.util.Collection;
import java.util.List;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyRow;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.umuc.swen.capstone.brewer.model.domain.brewercolor.BrewerColorScale;
import org.umuc.swen.capstone.brewer.model.util.BrewerScaleColorChooserUtil;

/**
 * Created by cwancowicz on 9/24/16.
 */
public class BrewerScaleMapping implements FilterMapper {

  private final List<BrewerColorScale> brewerColorScales;

  public BrewerScaleMapping() {
    brewerColorScales = new BrewerScaleColorChooserUtil().getBrewerColorScale();
  }

  @Override
  public void applyFilterMapping(Collection<CyNetworkView> networkViews, CyNode node, CyRow row) {

    //TODO: This is for a Proof of Concept that we can modify the color of nodes based on a certain criteria.
    if (row.get("degree.layout", Integer.class) == 1) {
      networkViews.stream()
              .forEach(networkView -> networkView.getNodeView(node).setLockedValue(BasicVisualLexicon.NODE_FILL_COLOR,
                      brewerColorScales.get(10).getScales().get(0).get(0).getColor()));
    }
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
}