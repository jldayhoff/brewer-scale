package org.umuc.swen.capstone.brewer.model.mapping;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.umuc.swen.capstone.brewer.model.exception.InvalidBrewerColorMapper;

/**
 * Created by cwancowicz on 10/28/16.
 */
public class ContinuousBrewerScaleMapperTest {

  private static final Random RANDOM = new Random();
  private String columnName = "testColumn";

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
  public void shouldGetWeightedColor() {
    ColorBrewer colorBrewer = ColorBrewer.Blues;
    Color[] colors = colorBrewer.getColorPalette(100);
    Class type = Integer.class;
    int value = 72;
    List<Integer> values = Arrays.asList(value, RANDOM.nextInt(100), RANDOM.nextInt(100),
            RANDOM.nextInt(100), RANDOM.nextInt(100), 100);

    when(row.get(columnName, type)).thenReturn(value);
    when(cyNetworkView.getNodeView(node)).thenReturn(view);

    ContinuousBrewerScaleMapper continuousMapper = new ContinuousBrewerScaleMapper(columnName, colorBrewer, values, Integer.class);
    continuousMapper.applyFilterMapping(Arrays.asList(cyNetworkView), node, row);

    verify(view).setLockedValue(any(), eq(colors[value - 1]));
  }


  @Test
  public void shouldThrowExceptionWhenColorBrewerIsNotTypeSequential() {
    exception.expect(InvalidBrewerColorMapper.class);
    new ContinuousBrewerScaleMapper(columnName, ColorBrewer.Accent, Collections.emptyList(), Integer.class);
  }

  @Test
  public void shouldReturnDivergingMapType() {
    assertEquals(MapType.CONTINUOUS,
            new ContinuousBrewerScaleMapper(
                    columnName, ColorBrewer.Blues, Arrays.asList(1), Integer.class).getMapType());
  }
}
