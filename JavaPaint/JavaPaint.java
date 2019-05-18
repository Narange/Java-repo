import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JavaPaint {
  
  JButton clearButton, colorButton, fillButton;
  PaintCanvas paintCanvas;
  
  ActionListener actionListener  = new ActionListener() {
    
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == clearButton) {
        paintCanvas.clear();
      } else if (e.getSource() == colorButton) {
        paintCanvas.chooseColor();
      } else if (e.getSource() == fillButton){
        paintCanvas.fill();
      }
    }
  };
  
  public static void main(String[] args) {
    new JavaPaint().show();
  }
  
  public void show() {
    
    //frame and layout
    JFrame frame = new JFrame("JavaPaint");
    Container content = frame.getContentPane();
    content.setLayout(new BorderLayout());
    
    //the canvas itself
    paintCanvas = new PaintCanvas();
    content.add(paintCanvas, BorderLayout.CENTER);
    
    //button panel, add listeners and add buttons
    JPanel buttonPanel = new JPanel();
    clearButton = new JButton("Clear");
    clearButton.addActionListener(actionListener);
    colorButton = new JButton("Choose Color");
    colorButton.addActionListener(actionListener);
    fillButton = new JButton("Fill Canvas");
    fillButton.addActionListener(actionListener);
    
    buttonPanel.add(clearButton);
    buttonPanel.add(colorButton);
    buttonPanel.add(fillButton);
    
    //add buttonPanel
    content.add(buttonPanel, BorderLayout.NORTH);
    
    //other JFrame stuff
    frame.setSize(700, 700);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

}