package org.umuc.swen.capstone.brewer.model.mapping;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.cytoscape.model.CyRow;
import org.jcolorbrewer.ColorBrewer;
import org.umuc.swen.capstone.brewer.model.exception.InvalidBrewerColorMapper;
import org.umuc.swen.capstone.brewer.model.exception.InvalidElement;

/**
 * Created by cwancowicz on 10/17/16.
 */
public class DivergingBrewerScaleMapper<T> extends AbstractBrewerScaleMapper {

  private final Double maxValue;
  private final Integer colorScale;
  private final List<Color> positiveColors;
  private final List<Color> negativeColors;
  private final Class<T> type;

  public DivergingBrewerScaleMapper(String columnName, ColorBrewer colorBrewer, Double maxValue, Class<T> type) {
    super(colorBrewer, columnName, OrderType.ASCENDING);
    this.maxValue = maxValue;
    this.colorScale = 100;
    this.type = type;

    validateType(type);

    Color[] colors = colorBrewer.getColorPalette((colorScale*2) + 1);
    this.negativeColors = Arrays.asList(Arrays.copyOfRange(colors, 0, colorScale));
    this.positiveColors = Arrays.asList(Arrays.copyOfRange(colors, colorScale, colors.length));
    Collections.reverse(negativeColors);
  }

  @Override
  public MapType getMapType() {
    return MapType.DIVERGING;
  }

  @Override
  protected Optional<Color> getColor(CyRow row) {
    if (Objects.isNull(row.get(columnName, type))) {
      return Optional.empty();
    }

    T value = row.get(columnName, type);

    if (isPositive(value)) {
      return Optional.of(positiveColors.get(getBucket(value)));
    }
    else {
      return Optional.of(negativeColors.get(getBucket(value)));
    }
  }

  @Override
  protected void validateColorBrewer(ColorBrewer colorBrewer) {
    if (!Arrays.asList(ColorBrewer.getDivergingColorPalettes(false)).contains(colorBrewer)) {
      throw new InvalidBrewerColorMapper(MapType.DISCRETE, InvalidElement.EXPECTED_DIVERGING_PALETTE);
    }
  }

  private Integer getBucket(T value) {
    if (value instanceof Double) {
      return (int) Math.floor((Math.abs((Double) value) / maxValue) * colorScale);
    }
    else {
      return (int) Math.floor((Math.abs((Integer) value) / maxValue) * colorScale);
    }
  }

  private boolean isPositive(T value) {
    if (value instanceof Integer && (Integer)value >= 0) {
      return true;
    }
    return false;
  }

  private void validateType(Class<T> type) {
    if (type == Integer.class || type == Double.class) {
      return;
    }
    throw new InvalidBrewerColorMapper(getMapType(), InvalidElement.INVALID_DATA_TYPE);
  }
}
