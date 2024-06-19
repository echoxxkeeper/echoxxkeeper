import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


class SuperMain implements ActionListener{

    static Data data = new Data();
    private Frame frame;
    private Panel sidebarPanel;
        private Panel subPanel;
        private Button libraryButton;
    private Panel contentPanel;
        private JScrollPane scroll;
        private Panel subTopPanel;
    private Button[] object;
    private Panel[] listHolder;
    

    final int arc = 20;
    // format as follows: posX, posY, width, height

    private Integer[] header_panel = {0, -5, 825, 70};
        //format as follows: posX, posY, width, height
        // Integer[] searchbar_value = {155, 20, 400, 40};
        //format as follows: posX, posY, width, height
        private Integer[] minimize_value = {650, 5, 90, 65};
        //format as follows: posX, posY, width, height
        private Integer[] close_value = {735, 5, 90, 65};

    // format as follows: posX, posY, width, height, JScrollPane posX, JScrollPane posY JScrollPane height, JScrollPane width, arcX, arcY.
    private Integer[] side_bar_panel = {10, 80, 80, 650, 5, 5, 70, 630, 20, 20};
        // format as follows: height, width
        private Integer[] sub_panel_sizes= {882, 50};
        // format as follows: posX, posY, width, height
        private Integer[] library_button = {9, 5, 50, 50};
        // format as follows: posX, width, height
        private Integer[] side_bar_button = {9, 50, 50};
        // format as follows: posX, posY, width, height, JScrollPane posX, JScrollPane posY, JScrollPane width, JScrollPane height, arcX, arcY 
        
    private Integer[] content_panel = {100, 80, 715, 650, 10, 10, 695, 630, 20, 20};
    //format as follows: width, and height
        private Integer[] main_content_holder = {0, 1480};
        // array list as follows: posX, posY, width, height, gridLayout_row, gridLayout_column, grid_hgap, grid_vgap
        private Integer[] sub_top_panel = {1, 1, 694, 130, 2, 4, 5, 5};

        // outer panel that holds the entire for loop, as follows: posX, width, height
        private Integer[] panel_object = {0, 694, 280};
            // inner title text, as follows: posX, posY, width, height, font size
            private Integer[] main_title = {1, 10, 500, 30, 24};
            // panel that holds the album/singer, as follows: posX, posY, width, height, rows, column, hgap, vgap.
            private Integer[] list_holder = {1, 40, 694, 240, 0, 4, 10, 0};
            // the album/singer itself, as follows: width, height, arcWidth, arcHeight
            private Integer[] pic_holder = {100, 200, 20, 20};

    SuperMain(){
    
    frame = new Frame(null);

    Panel headerPanel = new Panel(Color.BLUE, header_panel[0], header_panel[1], header_panel[2], header_panel[3], null);
    MouseListener mouse = new MouseListener(); 
    headerPanel.setOpaque(false);
    headerPanel.addMouseListener(mouse);
    headerPanel.addMouseMotionListener(mouse);
    frame.add(headerPanel);

        Button minimizeButton = new Button(minimize_value[0], minimize_value[1], minimize_value[2], minimize_value[3]);
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

        Button closeButton = new Button(close_value[0], close_value[1], close_value[2], close_value[3]);
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

    sidebarPanel = new Panel(new Color(18, 18, 18), side_bar_panel[0], side_bar_panel[1], side_bar_panel[2], side_bar_panel[3], null){
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            Dimension arcs = new Dimension(side_bar_panel[8], side_bar_panel[9]);
            int width = getWidth();
            int height = getHeight();
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //Draws the rounded opaque panel with borders
            g2d.setColor(getBackground());
            g2d.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
            // g2d.setColor(getForeground());
            // g2d.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
        }

    };
    frame.add(sidebarPanel);

            //Panel that holds the scrollable area in the sidebar.
            subPanel = new Panel(new Color(18, 18, 18), sub_panel_sizes[0], sub_panel_sizes[1], null);
            subPanel.setOpaque(true);

                libraryButton = new Button(library_button[0], library_button[1], library_button[2], library_button[3], data.getImageIcon(1).getImage(), 10, 10, library_button[2] -20, library_button[3] -20, 0);
                libraryButton.setContentAreaFilled(false);
                libraryButton.setBackground(Color.DARK_GRAY);
                libraryButton.setBorder(BorderFactory.createEmptyBorder());
                libraryButton.setLayout(null);
                libraryButton.addActionListener(this);
                subPanel.add(libraryButton);

                Label libraryText = new Label(data.getTitle(4), "Myanmar Text", new Color(255, 230, 230), Font.BOLD, 50, 15, 150, 30, 22);
                libraryText.setBackground(Color.RED);
                libraryButton.add(libraryText);

                object = new Button[15];
                for(int i = 0; i < data.song_array.length; i++){
                    object[i] = new Button(side_bar_button[0], 60 + (i * 55) ,side_bar_button[1], side_bar_button[2], data.song_array[i].getImageIcon().getImage(), data.song_array[i].getImageX(), data.song_array[i].getImageY(), data.song_array[i].getImageWidth(), data.song_array[i].getImageHeight(), 0);
                    object[i].setLayout(null);
                    object[i].setBorderPainted(false);
                    object[i].setContentAreaFilled(false);
                    object[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    subPanel.add(object[i]);
                }

            scroll = new JScrollPane(subPanel);
            scroll.getVerticalScrollBar().setUnitIncrement(5);
            scroll.setBounds(side_bar_panel[4], side_bar_panel[5], side_bar_panel[6], side_bar_panel[7]);
            scroll.setBorder(BorderFactory.createEmptyBorder());
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            sidebarPanel.add(scroll);
        

        //Color, posX, posY, width, height
    contentPanel = new Panel(new Color(18, 18, 18), content_panel[0], content_panel[1], content_panel[2], content_panel[3], null){
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            Dimension arcs = new Dimension(content_panel[8], content_panel[9]);
            int width = getWidth();
            int height = getHeight();
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //Draws the rounded opaque panel with borders
            g2d.setColor(getBackground());
            g2d.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
            // g2d.setColor(getForeground());
            // g2d.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
        }
    
    };
    contentPanel.setOpaque(false);
    frame.add(contentPanel);

    //holds the scrollable area for the whole content panel
    JPanel mainContentHolder = new JPanel();
    mainContentHolder.setLayout(null);
    mainContentHolder.setBackground(new Color(18, 18, 18));
    mainContentHolder.setPreferredSize(new Dimension(main_content_holder[0], main_content_holder[1]));

            subTopPanel = new Panel(null, sub_top_panel[0], sub_top_panel[1], sub_top_panel[2], sub_top_panel[3], new GridLayout(sub_top_panel[4],sub_top_panel[5],sub_top_panel[6],sub_top_panel[7]));
            // grid layout set on a 2x4 rowXcolumn with a set size
                Color[] color_array = {Color.BLACK, Color.RED, Color.GREEN, Color.WHITE, Color.CYAN, Color.MAGENTA, Color.PINK, Color.YELLOW};
                Panel[] playlist = new Panel[8];

                //no need to put height and width to child component of a gridlayout because child component adapts its container size on a gridlayoutformat parent container: supTopPanel.

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
                            // g2d.setColor(getForeground());
                            // g2d.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);
                        }
                    };
                    playlist[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    subTopPanel.add(playlist[i]);
                }
                mainContentHolder.add(subTopPanel);

    JScrollPane mainContentScroll = new JScrollPane(mainContentHolder);
    mainContentScroll.getVerticalScrollBar().setUnitIncrement(8);
    mainContentScroll.setBorder(BorderFactory.createEmptyBorder());
    mainContentScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    mainContentScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    mainContentScroll.setBounds(content_panel[4], content_panel[5], content_panel[6], content_panel[7]);
    contentPanel.add(mainContentScroll);
    
    

    Panel[] panelObject = new Panel[4]; //4 outer panel that holds the mainTitle, listHolder, and picsHolder.
    Label[] mainTitle = new Label[4]; //4 inner specific Title on each panelObject.
    listHolder = new Panel[4]; //inner panel that holds the album together.
    Panel[] picHolder = new Panel[4]; //album itself.

            for (int i = 0; i < panelObject.length; i++){
                panelObject[i] = new Panel(Color.RED, panel_object[0], (60 + 130) + (i * 330), panel_object[1], panel_object[2], null);
                //posY 30 is the spacing between (4) panels, 130 is the height of upper outer panel of the content, 210 is the total height of the first upper panel combined with existing (or not) spacing before and after it... im so proud of myself XD      
                // panelObject[i].setOpaque(true);
                mainContentHolder.add(panelObject[i]);

                mainTitle[i] = new Label(data.getTitle(i), "Myanmar Text", Color.WHITE, Font.BOLD, main_title[0], main_title[1], main_title[2], main_title[3], main_title[4]);
                panelObject[i].add(mainTitle[i]);

                listHolder[i] = new Panel(Color.BLUE, list_holder[0], list_holder[1], list_holder[2], list_holder[3], new GridLayout(list_holder[4], list_holder[5], list_holder[6] , list_holder[7]));
                // listHolder[i].setOpaque(true);
                panelObject[i].add(listHolder[i]);

                for (int j = 0; j < picHolder.length; j++){
                    picHolder[i] = new Panel(Color.MAGENTA, pic_holder[0], pic_holder[1], null){
                        @Override
                        protected void paintComponent(Graphics g){
                            super.paintComponent(g);
                            Dimension arcs = new Dimension(pic_holder[2], pic_holder[3]);
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
                    picHolder[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    listHolder[i].add(picHolder[i]);
                }
            };
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

    private Boolean is_toggled = false;
    Label[] songTitle = new Label[data.song_array.length];
    Label[] songAuthor = new Label[data.song_array.length];

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == libraryButton){
            is_toggled = !is_toggled;
            if (is_toggled){
                sidebarPanel.setBounds(side_bar_panel[0], side_bar_panel[1], 255, side_bar_panel[3]);
                sidebarPanel.revalidate();
                sidebarPanel.repaint();
                scroll.setBounds(side_bar_panel[4], side_bar_panel[5], 240, side_bar_panel[7]);
                scroll.revalidate();
                scroll.repaint();
                for (int i = 0; i < data.song_array.length; i++){
                    object[i].setBounds(side_bar_button[0], 60 + (i * 55), 220, side_bar_button[2]);
                    object[i].setImage(data.song_array[i].getImageCover().getImage());
                    object[i].setImageX(data.song_array[i].getImageXClicked());
                    object[i].setImageY(data.song_array[i].getImageYClicked());
                    object[i].setImageWidth(data.song_array[i].getImageWidthClicked());
                    object[i].setImageHeight(data.song_array[i].getImageHeightClicked());
                    object[i].setAlpha(data.song_array[i].getAlpha());

                    songTitle[i] = new Label(data.song_array[i].getName(), "Myanmar Text", Color.WHITE, Font.BOLD, 10, 2, 220, 30, 13);
                    object[i].add(songTitle[i]);
                    songAuthor[i] = new Label(data.song_array[i].getAuthor(), "Myanmar Text", Color.WHITE, Font.BOLD, 10, 20, 220, 30, 10);
                    object[i].add(songAuthor[i]);
                    object[i].revalidate();
                    object[i].repaint();
                }
                libraryButton.setBounds(library_button[0], library_button[1], 220, library_button[3]);
                contentPanel.setBounds(275, content_panel[1], 540, content_panel[3]);
                contentPanel.revalidate();
                contentPanel.repaint();
                subTopPanel.setBounds(sub_top_panel[0], sub_top_panel[1], 524, sub_top_panel[3]);
                subTopPanel.revalidate();
                subTopPanel.repaint();
                for (int i = 0; i < listHolder.length; i++){
                    listHolder[i].setBounds(list_holder[0], list_holder[1], 524, list_holder[3]);
                    listHolder[i].setLayout(new GridLayout(list_holder[4], list_holder[5], 5, list_holder[7]));
                    listHolder[i].revalidate();
                    listHolder[i].repaint();
                }
            }
            else if (!is_toggled) {
                
                sidebarPanel.setBounds(side_bar_panel[0], side_bar_panel[1], side_bar_panel[2], side_bar_panel[3]);
                sidebarPanel.revalidate();
                sidebarPanel.repaint();
                scroll.setBounds(side_bar_panel[4], side_bar_panel[5], side_bar_panel[6], side_bar_panel[7]);
                scroll.revalidate();
                scroll.repaint();
                for (int i = 0; i < data.song_array.length; i++){
                    object[i].setBounds(side_bar_button[0], 60 + (i * 55), side_bar_button[1], side_bar_button[2]);
                    object[i].setImage(data.song_array[i].getImageIcon().getImage());
                    object[i].setImageX(data.song_array[i].getImageX());
                    object[i].setImageY(data.song_array[i].getImageY());
                    object[i].setImageWidth(data.song_array[i].getImageWidth());
                    object[i].setImageHeight(data.song_array[i].getImageHeight());
                    object[i].remove(songTitle[i]);
                    object[i].remove(songAuthor[i]);
                    object[i].setAlpha(0);
                    object[i].revalidate();
                    object[i].repaint();
                }
                libraryButton.setBounds(library_button[0], library_button[1], library_button[2], library_button[3]);

                contentPanel.setBounds(content_panel[0], content_panel[1], content_panel[2], content_panel[3]);
                contentPanel.revalidate();
                contentPanel.repaint();
                subTopPanel.setBounds(sub_top_panel[0], sub_top_panel[1], sub_top_panel[2], sub_top_panel[3]);
                subTopPanel.revalidate();
                subTopPanel.repaint();
                for (int i = 0; i < listHolder.length; i++){
                    listHolder[i].setBounds(list_holder[0], list_holder[1], list_holder[2], list_holder[3]);
                    listHolder[i].setLayout(new GridLayout(list_holder[4], list_holder[5], list_holder[6], list_holder[7]));
                    listHolder[i].revalidate();
                    listHolder[i].repaint();
                }
            }
        }
    }

}

// custom jswing ----------------------------------------------------------------------------------------

class Button extends JButton{

    private Image priv_image;
    private int priv_imagex;
    private int priv_imagey;
    private int priv_image_wh;
    private int priv_image_ht;
    private int priv_alpha;

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

    Button(int posX, int posY, int width, int height, Image image, int imagex, int imagey, int image_wh, int image_ht, int alpha){
        this.priv_image = image;
        this.priv_imagex = imagex;
        this.priv_imagey = imagey;
        this.priv_image_wh = image_wh;
        this.priv_image_ht = image_ht;
        this.priv_alpha = alpha;
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setFocusable(false);
        setBounds(posX, posY, width, height);
        setForeground(Color.WHITE);
    }

    public void setImage(Image image){
        this.priv_image = image;
    }

    public void setImageX(int value){
        this.priv_imagex = value;
    }

    public void setImageY(int value){
        this.priv_imagey = value;
    }

    public void setImageWidth(int value){
        this.priv_image_wh = value;
    }

    public void setImageHeight(int value){
        this.priv_image_ht = value;
    }

    public void setAlpha(int value){
        this.priv_alpha = value;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(priv_image, priv_imagex, priv_imagey, priv_image_wh, priv_image_ht, this);
        g.setColor(new Color(0, 0, 0, priv_alpha));
        g.fillRect(0, 0, getWidth(), getHeight());
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

    public void changeForeground(Color color){
        this.setForeground(color);
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
