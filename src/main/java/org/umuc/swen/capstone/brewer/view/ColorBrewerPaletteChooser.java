package org.umuc.swen.capstone.brewer.view;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import org.jcolorbrewer.ui.ColorPanelSelectionModel;
import org.jcolorbrewer.ui.DivergingColorPalettePanel;
import org.jcolorbrewer.ui.QualitativeColorPalettePanel;
import org.jcolorbrewer.ui.SequentialColorPalettePanel;
import org.umuc.swen.capstone.brewer.view.listener.RadioButtonListener;

/**
 * Created by cwancowicz on 11/1/16.
 */
public class ColorBrewerPaletteChooser extends JDialog {

  private final JPanel mainPanel = new JPanel();
  private final JColorChooser colorPanel;

  public ColorBrewerPaletteChooser() {
    colorPanel = new JColorChooser(new ColorPanelSelectionModel());
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    initializeDialog();
  }

  public void showDialog() {
    setVisible(true);
  }

  public void setColorPanel(AbstractColorChooserPanel abstractColorChooserPanel) {
    colorPanel.setChooserPanels(new AbstractColorChooserPanel[]{abstractColorChooserPanel});
    revalidate();
    repaint();
  }

  private void initializeDialog() {
    JPanel radioPanel = new JPanel();

    JRadioButton sequential = new JRadioButton("Sequential");
    JRadioButton diverging = new JRadioButton("Diverging");
    JRadioButton qualitative = new JRadioButton("Qualitative");

    sequential.addActionListener(new RadioButtonListener(this, new SequentialColorPalettePanel()));
    diverging.addActionListener(new RadioButtonListener(this, new DivergingColorPalettePanel()));
    qualitative.addActionListener(new RadioButtonListener(this, new QualitativeColorPalettePanel()));

    ButtonGroup mappersButtonGroup = new ButtonGroup();
    mappersButtonGroup.add(sequential);
    mappersButtonGroup.add(diverging);
    mappersButtonGroup.add(qualitative);

    radioPanel.add(sequential);
    radioPanel.add(qualitative);
    radioPanel.add(diverging);

    // set selection to sequential
    sequential.setSelected(true);
    setColorPanel(new SequentialColorPalettePanel());


    // add everything
    mainPanel.add(radioPanel);
    mainPanel.add(colorPanel);
    add(mainPanel);
  }
}
