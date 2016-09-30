package org.umuc.swen.capstone.brewer.model.util;

import java.util.List;
import java.util.Map;
import java.util.Random;
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
  private final static Random RANDOM = new Random();

  @Before
  public void setUp() {
    brewerScaleColorChooserUtil = new BrewerScaleColorChooserUtil();
  }

  @Test
  public void shouldCreateListOfBrewerColorScales() {
    int index = RANDOM.nextInt(33);
    List<BrewerColorScale> brewerColorScale = brewerScaleColorChooserUtil.getBrewerColorScale();
    assertNotNull(brewerColorScale);
    assertTrue(brewerColorScale.size() >= 1);
    assertNotNull("ScaleType is null", brewerColorScale.get(index).getType());
    assertNotNull("Name is null", brewerColorScale.get(index).getName());
    assertNotNull("Scales is null", brewerColorScale.get(index).getScales());
    assertTrue(brewerColorScale.get(index).getScales().size() >= 1);
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
