package org.umuc.swen.capstone.brewer.model.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.umuc.swen.capstone.brewer.model.domain.brewercolor.BrewerColorScale;

/**
 * Created by cwancowicz on 9/24/16.
 */
public class BrewerScaleColorChooserUtilTest {

  private BrewerScaleColorChooserUtil brewerScaleColorChooserUtil;

  @Before
  public void setUp() {
    brewerScaleColorChooserUtil = new BrewerScaleColorChooserUtil();
  }

  @Test
  public void shouldTestBrewerScaleCanLoad() {
    BrewerColorScale brewerColorScale = brewerScaleColorChooserUtil.getBrewerColorScale();
    assertNotNull(brewerColorScale);
    assertNotNull(brewerColorScale.getScales());
    assertTrue(brewerColorScale.getScales().size() >= 1);
  }
}
