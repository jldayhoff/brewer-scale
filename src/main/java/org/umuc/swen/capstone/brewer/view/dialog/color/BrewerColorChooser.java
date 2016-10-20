package org.umuc.swen.capstone.brewer.view.dialog.color;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.colorchooser.AbstractColorChooserPanel;

/**
 * Created by cwancowicz on 10/14/16.
 */
public class BrewerColorChooser extends JDialog {

  private final AbstractColorChooserPanel colorChooserPanel;
  private final JColorChooser jColorChooser;

  public BrewerColorChooser(final AbstractColorChooserPanel colorChooserPanel) {
    this.colorChooserPanel = colorChooserPanel;
    this.jColorChooser = new JColorChooser();
    initialize();
  }

  private void initialize() {
    this.colorChooserPanel.installChooserPanel(this.jColorChooser);
    add(this.colorChooserPanel);
    setTitle(colorChooserPanel.getDisplayName());
    setVisible(true);
  }
}
