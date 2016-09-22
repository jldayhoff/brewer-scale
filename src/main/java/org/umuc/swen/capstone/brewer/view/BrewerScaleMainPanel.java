package org.umuc.swen.capstone.brewer.view;


import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Created by cwancowicz on 9/22/16.
 */
public class BrewerScaleMainPanel extends JPanel {

  public BrewerScaleMainPanel() {
    JLabel lbXYZ = new JLabel("This is my Control Panel");

    this.add(lbXYZ);
    this.setVisible(true);
  }
}
