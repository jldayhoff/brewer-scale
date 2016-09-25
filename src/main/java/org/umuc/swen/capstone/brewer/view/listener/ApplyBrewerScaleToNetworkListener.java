package org.umuc.swen.capstone.brewer.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.umuc.swen.capstone.brewer.model.mapping.BrewerScaleMapping;
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
    this.networkManagerUtil.applyFilterToNetworks(new BrewerScaleMapping());
  }
}
