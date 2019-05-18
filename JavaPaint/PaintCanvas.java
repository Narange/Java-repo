import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaintCanvas extends JComponent {

  private static final long serialVersionUID = -8683549318255104009L;
  
  private Image image;
  private Graphics2D g2;
  private int currentX, currentY, previousX, previousY;
  private Color color = Color.BLACK; //THE color for pen and canvas-filler. Initially black.
  
  public PaintCanvas() {
    setDoubleBuffered(false);
    addMouseListener(new MouseAdapter() {
      
      public void mousePressed(MouseEvent e) {
        //when mouse is pressed, save coordinates
        previousX = e.getX();
        previousY = e.getY();
      }
    });
    
    addMouseMotionListener(new MouseMotionAdapter() {
      
      public void mouseDragged(MouseEvent e) {
        currentX = e.getX();
        currentY = e.getY();
        
        if (g2 != null) {
          //draw the line from previous coords to current
          g2.drawLine(previousX, previousY, currentX, currentY);
          repaint();
          
          //update coords
          previousX = currentX;
          previousY = currentY;
          
        }
      }
    });
  }
  
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    //if image is null then create it
    if (image == null) {
      image = createImage(getWidth(), getHeight());
      g2 = (Graphics2D) image.getGraphics();
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      clear();
    }
    
    g.drawImage(image, 0, 0, null);
  }

  public void clear() {
    g2.setColor(Color.WHITE);
    g2.fillRect(0, 0, getWidth(), getHeight());
    g2.setColor(color); //affects the "color" global var
    repaint();
  }
  
  //bring up a JColorChooser to let user pick custom color
  public void chooseColor() {
    g2.setColor(JColorChooser.showDialog(null, "Choose a color", color));
  }
  
  //fill the canvas with the global var color
  public void fill(){
    g2.fillRect(0, 0, getWidth(), getHeight());
    repaint();
  }
  
}