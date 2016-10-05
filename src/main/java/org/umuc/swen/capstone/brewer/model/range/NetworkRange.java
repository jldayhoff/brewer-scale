package org.umuc.swen.capstone.brewer.model.range;

import java.awt.Color;

/**
 * Created by cwancowicz on 9/29/16.
 */
public interface NetworkRange<T> {

  /**
   * Retrieves the {@link Color} in range of value.
   * @param value
   * @return {@link Color} or Null if not found
   */
  Color getBrewerColorInRange(T value);
}
