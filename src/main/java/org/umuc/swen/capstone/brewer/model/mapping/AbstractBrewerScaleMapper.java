package org.umuc.swen.capstone.brewer.model.mapping;

import java.awt.Color;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyRow;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.jcolorbrewer.ColorBrewer;

/**
 * Created by cwancowicz on 10/15/16.
 */
public abstract class AbstractBrewerScaleMapper implements FilterMapper {

  protected final String columnName;
  protected final OrderType orderType;
  protected final ColorBrewer colorBrewer;

  public AbstractBrewerScaleMapper(ColorBrewer colorBrewer, String columnName, OrderType orderType) {
    this.columnName = columnName;
    this.orderType = orderType;
    this.colorBrewer = colorBrewer;
    validateColorBrewer(colorBrewer);
  }

  @Override
  public void applyFilterMapping(Collection<CyNetworkView> networkViews, CyNode node, CyRow row) {
    getColor(row).ifPresent(brewerColor -> {
      networkViews.stream()
              .forEach(networkView -> networkView.getNodeView(node).setLockedValue(BasicVisualLexicon.NODE_FILL_COLOR,
                      brewerColor));
    });
  }

  @Override
  public List<CyRow> sortRows(List<CyRow> list, Class type) {
    if (Objects.nonNull(list) && !list.isEmpty()) {
      if (list.get(0).getTable().getColumn(columnName).getType() == type) {
        switch (orderType) {
          case ASCENDING:
            list.sort((CyRow row1, CyRow row2) -> compareAscendingOrder(row1, row2, columnName, type));
            break;
          case DESCENDING:
            list.sort((CyRow row1, CyRow row2) -> compareDescendingOrder(row1, row2, columnName, type));
            break;
          case NONE:
            break;
        }
      }
    }
    return list;
  }

  @Override
  public OrderType getOrderType() {
    return orderType;
  }

  private int compareAscendingOrder(CyRow row1, CyRow row2, String columnName, Class type) {

    if (Objects.isNull(row1.get(columnName, type)) || Objects.isNull(row2.get(columnName, type))) {
      return -1;
    }
    return ((Comparable<Object>) (row1.get(columnName, type))).compareTo(row2.get(columnName, type));
  }

  private int compareDescendingOrder(CyRow row1, CyRow row2, String columnName, Class type) {
    if (Objects.isNull(row1.get(columnName, type)) || Objects.isNull(row2.get(columnName, type))) {
      return 1;
    }
    return ((Comparable<Object>) (row2.get(columnName, type))).compareTo(row1.get(columnName, type));
  }

  protected abstract Optional<Color> getColor(CyRow row);

  protected abstract void validateColorBrewer(ColorBrewer colorBrewer);
}
