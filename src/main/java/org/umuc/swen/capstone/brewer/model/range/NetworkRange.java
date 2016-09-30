package org.umuc.swen.capstone.brewer.model.range;

import java.util.Optional;
import org.umuc.swen.capstone.brewer.model.domain.brewercolor.BrewerColor;

/**
 * Created by cwancowicz on 9/29/16.
 */
public interface NetworkRange<T> {

  /**
   * Retrieves the {@link BrewerColor} in range of value.
   * @param value
   * @return {@link BrewerColor} or Null if not found
   */
  BrewerColor getBrewerColorInRange(T value);
}
