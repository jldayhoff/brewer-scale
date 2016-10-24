package org.umuc.swen.capstone.brewer.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jcolorbrewer.ui.ColorPaletteChooserDialog;
import org.umuc.swen.capstone.brewer.model.mapping.MapType;
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
    ColorPaletteChooserDialog dialog = new ColorPaletteChooserDialog();
    dialog.showDialog();
    if (dialog.wasOKPressed()) {
      networkManagerUtil.applyFilterToNetworks("degree.layout", dialog.getColorPalette(), MapType.DISCRETE);
    }
  }
}
