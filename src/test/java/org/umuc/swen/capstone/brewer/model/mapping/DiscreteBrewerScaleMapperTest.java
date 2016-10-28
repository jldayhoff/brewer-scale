package org.umuc.swen.capstone.brewer.model.mapping;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.cytoscape.model.CyNode;
import org.cytoscape.model.CyRow;
import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.model.View;
import org.jcolorbrewer.ColorBrewer;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.umuc.swen.capstone.brewer.model.exception.InvalidBrewerColorMapper;

/**
 * Created by cwancowicz on 10/15/16.
 */
public class DiscreteBrewerScaleMapperTest {

  private static String columnName = "testing";

  private CyNetworkView cyNetworkView;
  private CyNode node;
  private CyRow row;
  private View<CyNode> view;

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Before
  public void setUp() {
    cyNetworkView = mock(CyNetworkView.class);
    node = mock(CyNode.class);
    row = mock(CyRow.class);
    view = mock(View.class);
  }

  @Test
  public void shouldGetColorWhenMatchingValueFromSet() {
    Set<String> values = new HashSet(Arrays.asList("v1", "v2", "v3"));
    ColorBrewer colorBrewer = ColorBrewer.Accent;

    when(row.get(columnName, Object.class)).thenReturn("v1");
    when(cyNetworkView.getNodeView(node)).thenReturn(view);
    DiscreteBrewerScaleMapper discreteBrewerScaleMapper = new DiscreteBrewerScaleMapper(values, colorBrewer, columnName);

    discreteBrewerScaleMapper.applyFilterMapping(Arrays.asList(cyNetworkView), node, row);

    // verify we are getting a color
    verify(view).setLockedValue(any(), any(Color.class));
  }

  @Test
  public void shouldThrowExceptionWhenColorBrewerIsNotQualitativeType() {
    exception.expect(InvalidBrewerColorMapper.class);
    new DiscreteBrewerScaleMapper(Collections.emptySet(), ColorBrewer.Blues, columnName);
  }

  @Test
  public void shouldReturnDiscreteMapType() {
    assertEquals(MapType.DISCRETE,
            new DiscreteBrewerScaleMapper(Collections.emptySet(), ColorBrewer.Accent, columnName).getMapType());
  }
}
