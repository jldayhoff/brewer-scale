package org.umuc.swen.capstone.brewer.model.util;

import java.util.List;
import java.util.Map;
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
  public void shouldCreateListOfBrewerColorScales() {
    List<BrewerColorScale> brewerColorScale = brewerScaleColorChooserUtil.getBrewerColorScale();
    assertNotNull(brewerColorScale);
    assertTrue(brewerColorScale.size() >= 1);
    assertNotNull(brewerColorScale.get(0).getScales());
    assertTrue(brewerColorScale.get(0).getScales().size() >= 1);
  }

  @Test
  public void shouldCreateMapOfBrewerColorScales() {
    String keyToFind = "Spectral";
    Map<String, BrewerColorScale> brewerColorScaleMap = brewerScaleColorChooserUtil.getBrewerColorScaleMap();
    assertNotNull(brewerColorScaleMap);
    assertTrue(brewerColorScaleMap.containsKey(keyToFind));
    assertTrue(brewerColorScaleMap.get(keyToFind).getScales().size() >= 1);
  }
}
