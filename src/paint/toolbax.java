package paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class toolbax extends JPanel {
    private infopanel Info;

    private drawpanel draw;     
    JButton[] btn = new JButton[100];
    
    public toolbax(drawpanel draw) {
        super();
        
        this.draw = draw;
        
        setOpaque(true);
        setBackground( new Color(186, 186, 186));
        setPreferredSize(new Dimension(100, main.gHeight - 20));
        
        for (int i = 0; i < 19; i++) {
            btn[i] = new JButton();
            btn[i].setOpaque(true);
            btn[i].setFont(new Font("tahoma", 1, 10));    
            btn[i].setPreferredSize(new Dimension(95, 30));
            btn[i].setBackground( new Color(255, 229, 233));
        }
        ////////////////////////////////////////////////////////////////////////////  clear
        btn[0].setText("Clear");
        btn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw.Clear();         
            }
        }); 
        this.add(btn[0]);
        ////////////////////////////////////////////////////////////////////////////  Clearback
        
        btn[1].setText("undoClear");
        btn[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw.undoClear();     
            }
        });
        this.add(btn[1]);

        ////////////////////////////////////////////////////////////////////////////  undoClear

        btn[2].setText("Clearback");
        btn[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw.Clearback();     
            }
        });
        this.add(btn[2]);
       
        ////////////////////////////////////////////////////////////////////////////  undoLast

        btn[3].setText("undoLast");
        btn[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw.undoLast();     
            }
        });
        this.add(btn[3]);
        ////////////////////////////////////////////////////////////////////////////  line

        btn[4].setText("Line");
        btn[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw.setshapeTool(shapetool.Line); 
            }
        });
        this.add(btn[4]);
        ////////////////////////////////////////////////////////////////////////////  rect

        btn[5].setText("Rect");
        btn[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw.setshapeTool(shapetool.Rect); 
            }
        });
        this.add(btn[5]);
        ////////////////////////////////////////////////////////////////////////////  Oval

        btn[6].setText("Oval");
        btn[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw.setshapeTool(shapetool.Oval ); 
            }
        });
        this.add(btn[6]);
        ////////////////////////////////////////////////////////////////////////////  pencil
        btn[7].setText("pencil");
        btn[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw.setshapeTool(shapetool.pencil); 
                draw.setStrokeSize(1);

            }
        });
        this.add(btn[7]);
        
        //////////////////////////////////////////////////////////////////////////////////////// save

        btn[8].setText("save");
        btn[8].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // گرفتن مسیر دسکتاپ
                    String desktopPath = System.getProperty("user.home") + "/Desktop/drawing.png";
                    File file = new File(desktopPath);

                    // ساخت تصویر از پنل نقاشی
                    BufferedImage image = new BufferedImage(draw.getWidth(), draw.getHeight(), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2 = image.createGraphics();
                    draw.paint(g2);
                    g2.dispose();

                    // ذخیره در فایل
                    ImageIO.write(image, "png", file);

                    System.out.println("تصویر در دسکتاپ ذخیره شد: " + desktopPath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    
        this.add(btn[8]);
        
       //////////////////////////////////////////////////////////////////////////////////////// eraser
        btn[9].setText("eraser");  // نام دکمه
        btn[9].setBackground(Color.LIGHT_GRAY);  // تغییر رنگ پس‌زمینه دکمه
        btn[9].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw.setshapeTool(shapetool.pencil);  // اینجا مداد رو انتخاب می‌کنیم (برای پاک‌کن باید به جای eraser این رو بذاری)
                draw.setPenColor(Color.BLACK);  // تنظیم رنگ قلم به رنگ پس‌زمینه (مشکی)
                draw.setStrokeSize(10);  // افزایش ضخامت قلم برای دیده شدن پاک‌کن

            }
        });
        this.add(btn[9]);  // افزودن دکمه به پنل
        /////////////////////////////////////////////////////////////////////////////////////////////  load
        btn[14].setText("load");
        btn[14].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // مسیر دسکتاپ
                    String desktopPath = System.getProperty("user.home") + "/Desktop/drawing.png";
                    File file = new File(desktopPath);

                    if (!file.exists()) {
                        System.out.println("فایل یافت نشد: " + desktopPath);
                        return;
                    }

                    // بارگذاری تصویر
                    BufferedImage image = ImageIO.read(file);
                    Graphics g=draw.getGraphics();
                   g.drawImage(image, 0, 0, draw.getWidth(), draw.getHeight(), null);
                   g.dispose();

                    System.out.println("تصویر بارگذاری شد!");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        	this.add(btn[14]);
        	/////////////////////////////////////////////////////////////////////////////new
        	 btn[15].setText("new");  // نام دکمه
             btn[15].addActionListener(new ActionListener() {
             	    @Override
             	    public void actionPerformed(ActionEvent e) {
             	        // پاک کردن محتوای رسم‌شده (مثلاً اگر لیستی از اشکال دارید)
             	    	 draw.Clear();          	        
             	        
             	        // بازآرایی و بازنمایی دوباره
             	        draw.repaint();
             	        draw.revalidate();
             	    }
             	});

             this.add(btn[15]); 
             //////////////////////////////////////////////////////////////////////////////////////// pink

        btn[10].setBackground(Color.pink);  // تغییر رنگ دکمه به زرد
        btn[10].setFont(new Font("Arial", Font.PLAIN, 16));  // تغییر اندازه فونت دکمه به 16

        // تغییر اندازه دکمه به عرض 150 پیکسل و ارتفاع 50 پیکسل
        btn[10].setPreferredSize(new Dimension(43, 43));

        btn[10].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw.setPenColor(Color.pink); 

         
            }
        });

        this.add(btn[10]);
        //////////////////////////////////////////////////////////////////////////////////////// white

        btn[11].setBackground(Color.white);  // تغییر رنگ دکمه به زرد

        // تغییر اندازه دکمه به عرض 150 پیکسل و ارتفاع 50 پیکسل
        btn[11].setPreferredSize(new Dimension(43, 43));

        btn[11].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                draw.setPenColor(Color.white); 
            }
        });

        this.add(btn[11]);
        //////////////////////////////////////////////////////////////////////////////////////// Grin

        btn[13].setBackground(new Color(129, 233, 245));  // تغییر رنگ دکمه به زرد

        // تغییر اندازه دکمه به عرض 150 پیکسل و ارتفاع 50 پیکسل
        btn[13].setPreferredSize(new Dimension(43, 43));

        btn[13].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 draw.setPenColor(new Color(129, 233, 245)); 
            }
        });

        this.add(btn[13]);
        //////////////////////////////////////////////////////////////////////////////////////// blue

        btn[12].setBackground(new Color(81, 192, 75));  

        // تغییر اندازه دکمه به عرض 150 پیکسل و ارتفاع 50 پیکسل
        btn[12].setPreferredSize(new Dimension(43, 43));

        btn[12].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           	 draw.setPenColor(new Color(81, 192, 75)); 
            }
        });

        this.add(btn[12]);
        //////////////////////////////////////////////////////////////////////////////////////// yellow

        btn[16].setBackground(new Color(252, 252, 118));  

        // تغییر اندازه دکمه به عرض 150 پیکسل و ارتفاع 50 پیکسل
        btn[16].setPreferredSize(new Dimension(43, 43));

        btn[16].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           	 draw.setPenColor(new Color(252, 252, 118)); 
            }
        });

        this.add(btn[16]);
        
        
        btn[17].setBackground(new Color(255, 156, 63));  

        // تغییر اندازه دکمه به عرض 150 پیکسل و ارتفاع 50 پیکسل
        btn[17].setPreferredSize(new Dimension(43, 43));

        btn[17].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           	 draw.setPenColor(new Color(255, 156, 63)); 
            }
        });

        this.add(btn[17]);
        


        
        	//با انتخاب فایل 
        	//btn[14] = new JButton("Load"); // اگه از قبل مقداردهی نکردی
        	//btn[14].addActionListener(new ActionListener() {
        	  //  @Override
        	    //public void actionPerformed(ActionEvent e) {
        	      //  JFileChooser fileChooser = new JFileChooser();
        	        //fileChooser.setDialogTitle("انتخاب تصویر");
        	        
        	        // فیلتر کردن برای نمایش فقط فایل‌های تصویری
        	        //fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "png", "jpg", "jpeg", "gif"));

        	       // int result = fileChooser.showOpenDialog(null);
        	        //if (result == JFileChooser.APPROVE_OPTION) {
        	          //  File selectedFile = fileChooser.getSelectedFile();
        	            //try {
        	              //  BufferedImage image = ImageIO.read(selectedFile);

        	                // کشیدن تصویر روی `draw`
        	                //Graphics g = draw.getGraphics();
        	                //g.drawImage(image, 0, 0, draw.getWidth(), draw.getHeight(), null);
        	                //g.dispose();

        	                //System.out.println("تصویر بارگذاری شد: " + selectedFile.getAbsolutePath());
        	            //} catch (IOException ex) {
        	              //  ex.printStackTrace();
        	            //}
        	        //}
        	    //}
        	//});

        	// اضافه کردن دکمه به پنل
        	//this.add(btn[14]);  


        
    }
}
