package org.umuc.swen.capstone.brewer.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jcolorbrewer.ui.ColorPaletteChooserDialog;
import org.umuc.swen.capstone.brewer.model.mapping.MapType;
import org.umuc.swen.capstone.brewer.model.util.ColorBrewerMapperUtil;

/**
 * Created by cwancowicz on 9/24/16.
 */
public class ApplyBrewerScaleToNetworkListener implements ActionListener {

  private final ColorBrewerMapperUtil colorBrewerMapperUtil;

  public ApplyBrewerScaleToNetworkListener(ColorBrewerMapperUtil colorBrewerMapperUtil) {
    this.colorBrewerMapperUtil = colorBrewerMapperUtil;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    ColorPaletteChooserDialog dialog = new ColorPaletteChooserDialog();
    dialog.showDialog();
    if (dialog.wasOKPressed()) {
      colorBrewerMapperUtil.applyFilterToNetworks("degree.layout", dialog.getColorPalette(), MapType.CONTINUOUS);
    }
  }
}
