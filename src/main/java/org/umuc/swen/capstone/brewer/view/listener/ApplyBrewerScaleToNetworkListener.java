package org.umuc.swen.capstone.brewer.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import org.cytoscape.view.model.ContinuousRange;
import org.umuc.swen.capstone.brewer.model.domain.brewercolor.BrewerColor;
import org.umuc.swen.capstone.brewer.model.mapping.BrewerScaleMapping;
import org.umuc.swen.capstone.brewer.model.mapping.RandomBrewerScaleMapper;
import org.umuc.swen.capstone.brewer.model.mapping.SequentialBrewerScaleMapper;
import org.umuc.swen.capstone.brewer.model.range.BrewerColorRange;
import org.umuc.swen.capstone.brewer.model.range.NetworkRange;
import org.umuc.swen.capstone.brewer.model.range.NetworkRangeMapper;
import org.umuc.swen.capstone.brewer.model.util.BrewerScaleColorChooserUtil;
import org.umuc.swen.capstone.brewer.model.util.NetworkManagerUtil;

/**
 * Created by cwancowicz on 9/24/16.
 */
public class ApplyBrewerScaleToNetworkListener implements ActionListener {

  private final NetworkManagerUtil networkManagerUtil;

  public ApplyBrewerScaleToNetworkListener(NetworkManagerUtil networkManagerUtil) {
    this.networkManagerUtil = networkManagerUtil;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    applyContinuousRangeImplementation();
  }

  private void applyProofOfConcept() {
    this.networkManagerUtil.applyFilterToNetworks(new BrewerScaleMapping());
  }

  private void applyRandomFilterImplementation() {
    this.networkManagerUtil.applyFilterToNetworks(new RandomBrewerScaleMapper(
            new BrewerScaleColorChooserUtil().getBrewerColorScale().get(15).getScales().get(2)
    ));
  }

  private void applyContinuousRangeImplementation() {
    List<BrewerColor> colors = new BrewerScaleColorChooserUtil().getBrewerColorScale().get(0).getScales().get(5);
    List<NetworkRange> ranges = Arrays.asList(
            new BrewerColorRange(createContinuousRange(Integer.class, 0, 2), colors.get(0)),
            new BrewerColorRange(createContinuousRange(Integer.class, 3, 5), colors.get(1)),
            new BrewerColorRange(createContinuousRange(Integer.class, 6, 8), colors.get(2)),
            new BrewerColorRange(createContinuousRange(Integer.class, 9, 11), colors.get(3)),
            new BrewerColorRange(createContinuousRange(Integer.class, 12, 14), colors.get(4)),
            new BrewerColorRange(createContinuousRange(Integer.class, 15, 17), colors.get(5)),
            new BrewerColorRange(createContinuousRange(Integer.class, 18, 20), colors.get(6)),
            new BrewerColorRange(createContinuousRange(Integer.class, 21, 23), colors.get(7))
    );
    NetworkRangeMapper rangeMapper = new NetworkRangeMapper(ranges);
    this.networkManagerUtil.applyFilterToNetworks(new SequentialBrewerScaleMapper(rangeMapper, "degree.layout"));
  }

  private ContinuousRange createContinuousRange(Class type, Object min, Object max) {
    return new ContinuousRange(type, min, max, true, true);
  }
}
