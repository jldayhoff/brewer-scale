package org.umuc.swen.capstone.brewer.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import org.jcolorbrewer.ui.ColorPanelSelectionModel;
import org.jcolorbrewer.ui.DivergingColorPalettePanel;
import org.jcolorbrewer.ui.QualitativeColorPalettePanel;
import org.jcolorbrewer.ui.SequentialColorPalettePanel;
import org.umuc.swen.capstone.brewer.view.listener.RadioButtonListener;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.model.CyColumn;
import org.cytoscape.model.CyTable;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyTable;

/**
 * Created by cwancowicz on 11/1/16.
 */
public class ColorBrewerPaletteChooser extends JDialog {

  private final JPanel mainPanel = new JPanel();
  private final JColorChooser colorPanel = new JColorChooser(new ColorPanelSelectionModel());
  private final CyTable node = null;
 // private final CyColumn tblcolumn;
  List<CyColumn> columns = new ArrayList<>();
  private CyNetwork network;
  
  JComboBox columns1; 

  public ColorBrewerPaletteChooser() {
      
      
        //this.node = new CyTable();
           
     //colorPanel = 
        //for (nodeTable.getC)
        
     //this.columns = new ArrayList<CyColumn>();
    //columns.addAll(node.getColumns());
    
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    colorPanel.setPreviewPanel(new JPanel());
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    
    initializeDialog();
    //checkNetwork();
    
    
  }
  
  public void checkNetwork()
  {
   
      
  }
  
  

  public void showDialog() {
    setVisible(true);
  }
  
  public static void main(String [] args)
  {
      ColorBrewerPaletteChooser panel = new ColorBrewerPaletteChooser(); 
      panel.showDialog();
      //panel.show();
  }

  public void setColorPanel(AbstractColorChooserPanel abstractColorChooserPanel) {
    colorPanel.setChooserPanels(new AbstractColorChooserPanel[]{abstractColorChooserPanel});
    revalidate();
    repaint();
  }
  
  
              

       


  private void initializeDialog() {
    JPanel radioPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    JRadioButton sequential = new JRadioButton("Sequential");
    JRadioButton diverging = new JRadioButton("Diverging");
    JRadioButton qualitative = new JRadioButton("Qualitative");
    
    
    
    
    columns1 = new JComboBox ();
    
    JButton reset = new JButton("Reset");
    JButton apply = new JButton("Apply Palette");
    JButton cancel = new JButton("Cancel");
    JButton test = new JButton("Test");
    test.addActionListener(new ActionListener()
    {@Override
    @SuppressWarnings("empty-statement")
    public void actionPerformed(ActionEvent e){
        JOptionPane.showConfirmDialog(rootPane, "Clicked");
         
         CyTable nodeTable = network.getDefaultNetworkTable();
         columns.addAll(nodeTable.getColumns());
        
         for (Iterator<CyColumn> it = columns.iterator(); it.hasNext();) {
            columns1.addItem(it.toString());
        }
;
         
    
         
         
    
    }});
     
    buttonPanel.add(reset);
    buttonPanel.add(apply);
    buttonPanel.add(cancel);
    buttonPanel.add(test);

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
    
    radioPanel.add(columns1);

    // set selection to sequential
    sequential.setSelected(true);
    setColorPanel(new SequentialColorPalettePanel());


    // add everything
    mainPanel.add(radioPanel);
    mainPanel.add(colorPanel);
    mainPanel.add(buttonPanel);
    add(mainPanel);
    pack();
  }
}
