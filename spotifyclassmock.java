import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

class SuperMain{

    Frame frame = new Frame(null);

    SuperMain(){

    final int arc = 20;
    // format2 are follows: posX, posY, width, height, JScrollPane width, JScrollPane height.
    Integer[] side_bar_panel = {10, 60, 80, 650, 90, 50};
    // format are follows: height, width, button wh, button ht.
    Integer[] sub_panel_sizes= {882, 50, 50, 50};
    // array list are at follow: posX, posY, width, height, gridLayout_row, gridLayout_column, grid_hgap, grid_vgap
    Integer[] sub_top_panel = {1, 1, 694, 130, 2, 4, 5, 5};
    // as follows: posX, posY, width, height
    Integer[] first_panel = {0, 160, 694, 180};
    // as follows: posX, posY, width, height
    Integer[] second_panel = {0, 370, 694, 180};
    // as follows: posX, posY, width, height
    Integer[] third_panel = {0, 540, 694, 180};
    

    Panel headerPanel = new Panel(Color.GREEN, 0, -5, 825, 50, null);
    headerPanel.setOpaque(true);
    MouseListener mouse = new MouseListener(); 
    headerPanel.addMouseListener(mouse);
    headerPanel.addMouseMotionListener(mouse);
    frame.add(headerPanel);

        Button minimizeButton = new Button(650, 0, 90, 50);
        minimizeButton.setOpaque(false);
        minimizeButton.setContentAreaFilled(false);
        minimizeButton.setBorderPainted(false);
        minimizeButton.addActionListener(e -> frame.setState(java.awt.Frame.ICONIFIED));
        minimizeButton.setText("Minimize");
        minimizeButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e){
                minimizeButton.setContentAreaFilled(true);
                minimizeButton.setBackground(Color.RED);
                minimizeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent e){
                minimizeButton.setContentAreaFilled(false);
            }
        });
        headerPanel.add(minimizeButton);

        Button closeButton = new Button(735, 0, 90, 50);
        closeButton.setText("Close");
        closeButton.setOpaque(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.addActionListener(e -> frame.dispose());
        closeButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e){
                closeButton.setContentAreaFilled(true);
                closeButton.setBackground(Color.RED);
                closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent e){
                closeButton.setContentAreaFilled(false);
            }
        });
        headerPanel.add(closeButton);

    Panel sidebarPanel = new Panel(Color.BLUE, side_bar_panel[0], side_bar_panel[1], side_bar_panel[2], side_bar_panel[3], null){
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            Dimension arcs = new Dimension(arc, arc);
            int width = getWidth();
            int height = getHeight();
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //Draws the rounded opaque panel with borders
            g2d.setColor(getBackground());
            g2d.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
            g2d.setColor(getForeground());
            g2d.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
        }

    };
    frame.add(sidebarPanel);

            //Panel that holds the scrollable area in the sidebar.
            Panel subPanel = new Panel(null, sub_panel_sizes[0], sub_panel_sizes[1], new FlowLayout(FlowLayout.CENTER));
            subPanel.setBackground(Color.BLUE);
            subPanel.setOpaque(true);

                Button libraryButton = new Button(sub_panel_sizes[2], sub_panel_sizes[3]);
                libraryButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                subPanel.add(libraryButton);

                Button[] object = new Button[15];
                for(int i = 0; i < 15; i++){
                    object[i] = new Button(sub_panel_sizes[2], sub_panel_sizes[3]);
                    object[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    subPanel.add(object[i]);
                }

            JScrollPane scroll = new JScrollPane(subPanel);
            scroll.getVerticalScrollBar().setUnitIncrement(5);
            scroll.setBounds(5, 5, 70, 630);
            scroll.setBorder(BorderFactory.createEmptyBorder());
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            sidebarPanel.add(scroll);
        

        //Color, posX, posY, width, height
    Panel contentPanel = new Panel(Color.DARK_GRAY, 100, 60, 715, 650, null){
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            Dimension arcs = new Dimension(arc, arc);
            int width = getWidth();
            int height = getHeight();
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //Draws the rounded opaque panel with borders
            g2d.setColor(getBackground());
            g2d.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
            g2d.setColor(getForeground());
            g2d.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
        }
    
    };
    contentPanel.setOpaque(false);
    frame.add(contentPanel);

    //holds the scrollable area for the whole content panel
    JPanel mainContentHolder = new JPanel();
    mainContentHolder.setLayout(null);
    mainContentHolder.setBackground(Color.DARK_GRAY);
    mainContentHolder.setPreferredSize(new Dimension(695, 1300));

            Panel subTopPanel = new Panel(null, sub_top_panel[0], sub_top_panel[1], sub_top_panel[2], sub_top_panel[3], new GridLayout(sub_top_panel[4],sub_top_panel[5],sub_top_panel[6],sub_top_panel[7]));

                //as follows: height, width
                Color[] color_array = {Color.BLACK, Color.RED, Color.GREEN, Color.WHITE, Color.CYAN, Color.MAGENTA, Color.PINK, Color.YELLOW};
                Panel[] playlist = new Panel[8];

                //no need to put height and width to child component of a gridlayout because it child component adapts its container size.

                for (int i = 0; i < 8; i++){
                    playlist[i] = new Panel(color_array[i], 0, 0, null){
                        @Override
                        protected void paintComponent(Graphics g){
                            super.paintComponent(g);
                            Dimension arcs = new Dimension(arc, arc);
                            int width = getWidth();
                            int height = getHeight();
                            Graphics2D g2d = (Graphics2D)g;
                            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
                            //Draws the rounded opaque panel with borders
                            g2d.setColor(getBackground());
                            g2d.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
                            g2d.setColor(getForeground());
                            g2d.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
                        }
                    };
                    playlist[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    subTopPanel.add(playlist[i]);
                }
                mainContentHolder.add(subTopPanel);

            Panel firstPanel = new Panel(null, first_panel[0], first_panel[1], first_panel[2], first_panel[3], null);
            firstPanel.setOpaque(false);
            mainContentHolder.add(firstPanel);

                Label panelTitles = new Label("Recently Played", "Myanmar Text", Color.WHITE, Font.BOLD, 0, 2, 694, 30, 24);
                firstPanel.add(panelTitles);

                Panel playlistPanel = new Panel(null, 0, 33, 694, 147, new FlowLayout(FlowLayout.CENTER));
                firstPanel.add(playlistPanel);

                    Panel[] albumList = new Panel[5];

                    for (int i = 0; i < 5; i++){
                        albumList[i] = new Panel(Color.RED, 132, 132, null){
                            @Override
                            protected void paintComponent(Graphics g){
                                super.paintComponent(g);
                                Dimension arcs = new Dimension(arc, arc);
                                int width = getWidth();
                                int height = getHeight();
                                Graphics2D g2d = (Graphics2D)g;
                                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                
                                //Draws the rounded opaque panel with borders
                                g2d.setColor(getBackground());
                                g2d.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
                                g2d.setColor(getForeground());
                                g2d.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
                            }
                        };
                        albumList[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        playlistPanel.add(albumList[i]);
                    }

                JScrollPane mainContentScroll = new JScrollPane(mainContentHolder);
                mainContentScroll.getVerticalScrollBar().setUnitIncrement(8);
                mainContentScroll.setBorder(BorderFactory.createEmptyBorder());
                mainContentScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
                mainContentScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                mainContentScroll.setBounds(10, 10, 695, 630);
                contentPanel.add(mainContentScroll);

            Panel secondPanel = new Panel(null, second_panel[0], second_panel[1], second_panel[2], second_panel[3], null);
            mainContentHolder.add(secondPanel);

                Label secondPanelTitle = new Label("Made for you", "Myanmar Text", Color.WHITE, Font.BOLD, 0, 5, 684, 30, 24);
                secondPanel.add(secondPanelTitle);

                Panel secondPlaylistPanel = new Panel(null, 0, 35, 694, 145, new FlowLayout());
                secondPanel.add(secondPlaylistPanel);

                Panel[] madeList = new Panel[5];
                
                for (int i = 0; i < 5; i++){
                    madeList[i] = new Panel(Color.RED, 132, 132, null){
                        @Override
                        protected void paintComponent(Graphics g){
                            super.paintComponent(g);
                            Dimension arcs = new Dimension(arc, arc);
                            int width = getWidth();
                            int height = getHeight();
                            Graphics2D g2d = (Graphics2D)g;
                            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                            
                            //Draws the rounded opaque panel with borders
                            g2d.setColor(getBackground());
                            g2d.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
                            g2d.setColor(getForeground());
                            g2d.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
                        }
                    };
                    madeList[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    secondPlaylistPanel.add(madeList[i]);
                }

            Panel thirdPanel = new Panel(Color.ORANGE, third_panel[0], third_panel[1], third_panel[2], third_panel[3], null);





    frame.setVisible(true);
    }

    public class MouseListener extends MouseAdapter{
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

// custom jswing

class Button extends JButton{
    Button(int posX, int posY, int width, int height){
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setFocusable(false);
        this.setBounds(posX, posY, width, height);
        this.setForeground(Color.WHITE);
    }

    Button(int width, int height){
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setPreferredSize(new Dimension(width, height));
        this.setFocusable(false);
    }
}

class Frame extends JFrame{
    Frame(LayoutManager layout_style){
        this.setResizable(false);
        this.setSize(825,825);
        this.setUndecorated(true);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(layout_style);
    }
}

class Label extends JLabel{
    Label(String text, String text_name, Color color,  int text_style, int posX, int posY, int width, int height, int font_size){
        this.setText(text);
        this.setBounds(posX, posY, width, height);
        this.setForeground(color);
        this.setFont(new Font(text_name, text_style, font_size));
    }
}

class Panel extends JPanel{
    Panel(Color color, int posX, int posY, int width, int height, LayoutManager layout_style){
        this.setBounds(posX, posY, width, height);
        this.setOpaque(false);
        this.setLayout(layout_style);;
        this.setBackground(color);
    }

    Panel(Color color, int height, int width, LayoutManager layout_style){
        this.setOpaque(false);
        this.setLayout(layout_style);
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(color);
    }
}
