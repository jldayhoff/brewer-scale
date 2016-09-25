package org.umuc.swen.capstone.brewer.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import org.umuc.swen.capstone.brewer.model.util.NetworkManagerUtil;
import org.umuc.swen.capstone.brewer.view.listener.ApplyBrewerScaleToNetworkListener;

/**
 * Created by cwancowicz on 9/22/16.
 */
public class BrewerScaleMainPanel extends JPanel {

  private static final String APPLY_BREWER_SCALE_BUTTON_TEXT = "Apply Brewer Scale to Network";

  private JButton applyBrewerScaleButton;
  final private NetworkManagerUtil networkManagerUtil;

  public BrewerScaleMainPanel(NetworkManagerUtil networkManagerUtil) {
    this.networkManagerUtil = networkManagerUtil;
    createViewElements();
    addViewElements();
  }

  private void createViewElements() {
    applyBrewerScaleButton = new JButton(APPLY_BREWER_SCALE_BUTTON_TEXT);
    applyBrewerScaleButton.addActionListener(new ApplyBrewerScaleToNetworkListener(networkManagerUtil));
  }

  private void addViewElements() {
    this.add(applyBrewerScaleButton);
    this.setVisible(true);
  }
}
