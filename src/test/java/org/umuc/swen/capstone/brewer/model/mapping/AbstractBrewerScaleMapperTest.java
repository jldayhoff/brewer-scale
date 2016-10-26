package org.umuc.swen.capstone.brewer.model.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.cytoscape.model.CyRow;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by cwancowicz on 10/25/16.
 */
public class AbstractBrewerScaleMapperTest {

  private static final Random RANDOM = new Random();
  private List<CyRow> cyRows;

  private AbstractBrewerScaleMapper abstractBrewerScaleMapper = mock(AbstractBrewerScaleMapper.class,  Mockito.CALLS_REAL_METHODS);

  @Before
  public void setUp() {
    cyRows = new ArrayList();

  }

//  @Test
//  public void shouldTestSortInAscendingOrderInteger() {
//    int size = 72;
//    String columnName = "test";
//    Class type = Integer.class;
//    List<CyRow> rows = createRows(RANDOM.nextInt(), columnName, type);
//
//    abstractBrewerScaleMapper.sortRows(rows, )
//  }

  private List<CyRow> createRows(int size, String columnName, Class type) {
    List<CyRow> rows = new ArrayList();
    for (int i = 0; i < size; i++) {
      CyRow row = mock(CyRow.class);
      if (type == Integer.class) {
        when(row.get(columnName, type)).thenReturn(RANDOM.nextInt());
      }
      else if (type == Double.class){
        when(row.get(columnName, type)).thenReturn(RANDOM.nextDouble());
      }
      else {
        when(row.get(columnName, type)).thenReturn(RANDOM.nextLong());
      }
      rows.add(row);
    }
    return rows;
  }
}
