package org.umuc.swen.capstone.brewer.model.util;

import java.awt.Color;
import org.jcolorbrewer.ColorBrewer;
import org.junit.Test;

/**
 * Created by cwancowicz on 10/14/16.
 */
public class SomeTest {

  @Test
  public void testingColorBrewerLimitiations() {
    ColorBrewer colorBrewer = ColorBrewer.Accent;
    Color[] colors = colorBrewer.getColorPalette(1000000);
    System.out.println(colors.length);
  }
}
