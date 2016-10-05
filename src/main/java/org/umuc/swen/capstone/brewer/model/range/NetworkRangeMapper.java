package org.umuc.swen.capstone.brewer.model.range;

import java.awt.Color;
import java.util.List;
import java.util.Objects;
import org.umuc.swen.capstone.brewer.model.domain.brewercolor.BrewerColor;
import org.umuc.swen.capstone.brewer.model.exception.NoBrewerColorFoundException;

/**
 * Created by cwancowicz on 9/29/16.
 */
public class NetworkRangeMapper<T> {

  private final List<NetworkRange> ranges;

  public NetworkRangeMapper(List<NetworkRange> ranges) {
    this.ranges = ranges;
  }

  /**
   * Retrieves the {@link BrewerColor} from the {@link List} of {@link NetworkRange}
   * that contains value in range.
   *
   * @param value
   * @return {@link BrewerColor}. Throws {@link NoBrewerColorFoundException} if value not in
   * any range.
   */
  public Color getBrewerColorInRange(T value) {
    return ranges.stream()
            .map(networkRange -> networkRange.getBrewerColorInRange(value))
            .filter(Objects::nonNull)
            .findFirst()
            .orElseThrow(() -> new NoBrewerColorFoundException());
  }
}
