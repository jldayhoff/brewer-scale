package org.umuc.swen.capstone.brewer.view;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import org.jcolorbrewer.ColorBrewer;
import org.jcolorbrewer.ui.ColorPanelSelectionModel;
import org.jcolorbrewer.ui.DivergingColorPalettePanel;
import org.jcolorbrewer.ui.QualitativeColorPalettePanel;
import org.jcolorbrewer.ui.SequentialColorPalettePanel;
import org.umuc.swen.capstone.brewer.view.listener.RadioButtonListener;

/**
 * Created by cwancowicz on 11/1/16.
 */
public class ColorBrewerPaletteChooser extends JDialog {

  private final JPanel mainPanel;
  private final JColorChooser colorPanel;
  private final SequentialColorPalettePanel sequentialColorPalettePanel;
  private final DivergingColorPalettePanel divergingColorPalettePanel;
  private final QualitativeColorPalettePanel qualitativeColorPalettePanel;

  public ColorBrewerPaletteChooser() {
    mainPanel = new JPanel();
    colorPanel = new JColorChooser(new ColorPanelSelectionModel());
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    // create color palettes
    sequentialColorPalettePanel = new SequentialColorPalettePanel();
    divergingColorPalettePanel = new DivergingColorPalettePanel();
    qualitativeColorPalettePanel = new QualitativeColorPalettePanel();

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

  public ColorBrewer getSelectedPalette() {
    return ((ColorPanelSelectionModel) colorPanel.getSelectionModel()).getColorBrewer();
  }

  private void initializeDialog() {
    JPanel radioPanel = new JPanel();

    JRadioButton sequential = new JRadioButton(sequentialColorPalettePanel.getDisplayName());
    JRadioButton diverging = new JRadioButton(divergingColorPalettePanel.getDisplayName());
    JRadioButton qualitative = new JRadioButton(qualitativeColorPalettePanel.getDisplayName());

    sequential.addActionListener(new RadioButtonListener(this, sequentialColorPalettePanel));
    diverging.addActionListener(new RadioButtonListener(this, divergingColorPalettePanel));
    qualitative.addActionListener(new RadioButtonListener(this, qualitativeColorPalettePanel));

    ButtonGroup mappersButtonGroup = new ButtonGroup();
    mappersButtonGroup.add(sequential);
    mappersButtonGroup.add(diverging);
    mappersButtonGroup.add(qualitative);

    radioPanel.add(sequential);
    radioPanel.add(qualitative);
    radioPanel.add(diverging);

    // set initial selection to sequential
    sequential.setSelected(true);
    setColorPanel(sequentialColorPalettePanel);


    // add everything
    mainPanel.add(radioPanel);
    mainPanel.add(colorPanel);
    add(mainPanel);
    pack();
  }
}
