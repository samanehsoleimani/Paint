package paint;

import java.awt.FlowLayout;
import javax.swing.JFrame;

public class main extends JFrame {
    public static final int gWidth = 800;
    public static final int gHeight = 600;

    private toolbax tools;
    private infopanel Info;
    private drawpanel draw;
    
    
    public main() {
        this.setBounds(200, 100, gWidth, gHeight);
        this.setTitle("gPainter");
        this.setLayout(new FlowLayout());
      
        // Create Info panel
        Info = new infopanel();
        // Pass Info panel to drawpanel
        draw = new drawpanel(Info);
        // Create ToolBax
        tools = new toolbax(draw);

        // Add components to the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(tools);
        this.add(Info);
        this.add(draw);
        setVisible(true);
    }

    public static void main(String[] args) {
        main frame = new main();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); // تنظیم اندازه پنجره بر اساس اندازه محتویات داخلی
        frame.setVisible(true);
    }
}
