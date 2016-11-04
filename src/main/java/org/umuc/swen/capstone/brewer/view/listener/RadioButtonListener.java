package org.umuc.swen.capstone.brewer.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import org.umuc.swen.capstone.brewer.view.ColorBrewerPaletteChooser;

/**
 * Created by cwancowicz on 11/1/16.
 */
public class RadioButtonListener implements ActionListener {

  private final AbstractColorChooserPanel abstractColorChooserPanel;
  private final ColorBrewerPaletteChooser jDialog;

  public RadioButtonListener(ColorBrewerPaletteChooser jDialog, AbstractColorChooserPanel abstractColorChooserPanel) {
    this.abstractColorChooserPanel = abstractColorChooserPanel;
    this.jDialog = jDialog;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    jDialog.setColorPanel(abstractColorChooserPanel);
  }
}
