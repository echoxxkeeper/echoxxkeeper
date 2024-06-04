import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

class Main{

    Frame frame;

    Main(){
        MouseListener mouse = new MouseListener();

        Button closeButton = new Button(735, 5, 80, 40);
        closeButton.setText("Close");
        closeButton.addActionListener(e -> frame.dispose());

        Button minimizeButton  = new Button(680, 5, 90, 40);
        minimizeButton.setText("Minimize");
        minimizeButton.addActionListener(e -> frame.setState(java.awt.Frame.ICONIFIED));
    

        Panel headerPanel = new Panel(0, 0, 825, 50, null);
        headerPanel.setBackground(Color.GREEN);
        headerPanel.addMouseListener(mouse);
        headerPanel.addMouseMotionListener(mouse);
        headerPanel.add(minimizeButton);
        headerPanel.add(closeButton);

        frame = new Frame();
        frame.add(headerPanel);
    }

    public class MouseListener extends MouseAdapter implements MouseMotionListener{
        private Point mouse_rel_comp_coords = null;
        public void mouseReleased(MouseEvent e){
            mouse_rel_comp_coords = null;
        }
        public void mousePressed(MouseEvent e){
            mouse_rel_comp_coords = e.getPoint();
        }
        public void mouseDragged(MouseEvent e){
            Point currentPt = e.getLocationOnScreen();
            frame.setLocation(currentPt.x - mouse_rel_comp_coords.x, currentPt.y - mouse_rel_comp_coords.y);
        }
    }
}

class Frame extends JFrame{
    Frame(){
        this.setResizable(false);
        this.setSize(825,825);
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.BLACK);
    }
}

class Panel extends JPanel{
    Panel(int posX, int posY, int width, int height, LayoutManager layout_style){
        this.setBounds(posX, posY, width, height);
        this.setOpaque(true);
        this.setLayout(layout_style);;
    }
}

class Button extends JButton{
    Button(int posX, int posY, int width, int height){
        this.setFocusable(false);
        this.setBounds(posX, posY, width, height);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setForeground(Color.BLACK);
    }
}


