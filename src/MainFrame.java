import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class MainFrame extends JFrame {
    private int frmW = 400, frmH = 250, screenW, screenH;
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private JMenuBar jmb = new JMenuBar();
    private JMenu jmF = new JMenu("File");
 //Add LOTO
    private JMenuItem jmiLOTO = new JMenuItem("LOTO");
    private JMenuItem jmiCP = new JMenuItem("CP");
    private JMenuItem jmiExit = new JMenuItem("EXIT");
    private JDesktopPane jdp = new JDesktopPane();
    private Container cp;
    private JPanel jpn = new JPanel(new GridLayout(1, 6, 5, 5));
    private JPanel jpn1 = new JPanel(new GridLayout(1, 2, 5, 5));
    private JButton jbnclose = new JButton("Close");
    private JButton jbnRegen = new JButton("Regen");
    private JLabel jlbs[] = new JLabel[6];
    private int data[] = new int[6];
    private Random rnd = new Random(System.currentTimeMillis());
    private JInternalFrame jif = new JInternalFrame();
//Add Font
    private JMenuItem jmiSetFont = new JMenuItem("Font");
    private JPanel jpanel1 = new JPanel(new GridLayout(2,3,5,5));
    private JLabel jlbFontFamily =new JLabel("Family");
    private JLabel jlbFontStyle =new JLabel("Style");
    private JLabel jlbFontSize =new JLabel("Size");
    private JTextField jtfFamily = new JTextField("Times New Romans");
    private JTextField jtfSize = new JTextField("23");
    private String[] options = {"PLAIN","BOLD","ITALIC","BOLD+ITALIC"};
    private JComboBox jcbFStyle = new JComboBox(options);
 //Add computer
 private Container cp1;
    private JPanel jp = new JPanel(new GridLayout(4, 3));
    private JPanel jp1 = new JPanel(new GridLayout(0, 2));
    private JPanel jp2 = new JPanel(new GridLayout(4, 1));
    private JButton cn = new JButton("C");
    private JButton amo = new JButton("=");
    private JButton ad = new JButton("+");
    private JButton sub = new JButton("-");
    private JButton mul = new JButton("*");
    private JButton div = new JButton("/");
    private long num;
    private byte op;
    long result;
    private JInternalFrame jif1 = new JInternalFrame();
    private JTextField jtf = new JTextField();
    private JButton jbts[] = new JButton[10];



    public MainFrame() {
        initcomp();
        lotoGenerate();
    }

    private void initcomp() {
        screenH = dim.height;
        screenW = dim.width;
        this.setBounds(screenW / 2 - frmW / 2, screenH / 2 - frmH / 2, frmW, frmH);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        this.setJMenuBar(jmb);
        this.setContentPane(jdp);
        jmb.add(jmF);
        jmF.add(jmiLOTO);
        jmF.add(jmiCP);
        jmF.add(jmiSetFont);

        jpanel1.add(jlbFontFamily);
        jpanel1.add(jlbFontStyle);
        jpanel1.add(jlbFontSize);
        jpanel1.add(jtfFamily);
        jpanel1.add(jcbFStyle);
        jpanel1.add(jtfSize);
        jif.setBounds(0, 0, 300, 100);
        jif1.setBounds(0, 0, 500, 500);

        jmF.add(jmiExit);
        jmiExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jmiExit.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
//Add Font
        this.setTitle("Main Frame");

        jmiSetFont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(MainFrame.this,
                        jpanel1,
                        "Font setting",
                        JOptionPane.OK_CANCEL_OPTION);
                int fontStyle = 0;
                switch (jcbFStyle.getSelectedIndex()){
                    case 0:
                        fontStyle = Font.PLAIN;
                        break;
                    case 1:
                        fontStyle = Font.BOLD;
                        break;
                    case 2:
                        fontStyle = Font.ITALIC;
                        break;
                    case 3:
                        fontStyle = Font.BOLD + Font.ITALIC;
                        break;
                }
                if (result == JOptionPane.OK_CANCEL_OPTION){
                    UIManager.put("Menu.font",new Font(jtfFamily.getText(),fontStyle,
                            Integer.parseInt(jtfSize.getText())));
                    UIManager.put("MenuItem.font",new Font(jtfFamily.getText(),fontStyle,
                            Integer.parseInt(jtfSize.getText())));
                }
            }
        });
//Add computer
        cp1 = jif1.getContentPane();
        cp1.setLayout(new BorderLayout(5, 3));
        cp1.add(jtf, BorderLayout.NORTH);
        cp1.add(jp, BorderLayout.CENTER);
        cp1.add(jp1, BorderLayout.SOUTH);
        cp1.add(jp2, BorderLayout.EAST);
        jtf.setEditable(false);

        jmiCP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jdp.add(jif1);
                jif1.setVisible(true);
            }
        });

        jp1.add(cn);
        cn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn=(JButton) e.getSource();

                if (btn == cn){
                    result=0L;
                    num=0L;
                    op=0;
                    jtf.setText(Long.toString(num));
                }
            }
        });

        jp2.add(ad);
        ad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton) e.getSource();
                if (btn == ad){
                    save_num(ad);
                    op=1;
                }
            }
        });
        jp2.add(sub);
        sub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton) e.getSource();
                if (btn == sub) {
                    save_num(sub);
                    op = 2;
                }
            }
        });
        jp2.add(mul);
        mul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton) e.getSource();
                if (btn == mul) {
                    save_num(mul);
                    op = 3;
                }
            }
        });
        jp2.add(div);
        div.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton) e.getSource();
                if (btn == div) {
                    save_num(div);
                    op = 4;
                }
            }
        });
        jp1.add(amo);
        amo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                result=Long.parseLong(jtf.getText());

                switch(op){
                    case 1:
                        num+=result;
                        break;
                    case 2:
                        num-=result;
                        break;
                    case 3:
                        num*=result;
                        break;
                    case 4:
                        num/=result;
                        break;
                    default:
                }
                result=0L;
                jtf.setText(Long.toString(num));
            }

        });

        for (int i = 0; i <= 9; i++) {
            jbts[i] = new JButton(Integer.toString(i));
            jp.add(jbts[i]);
            jbts[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton tmpButton = (JButton) e.getSource();
                    jtf.setText(jtf.getText() + tmpButton.getText());
                }
            });
        }



//Add LOTO
        cp = jif.getContentPane();
        cp.setLayout(new BorderLayout(5, 5));
        cp.setLayout(new BorderLayout(5, 5));
        cp.add(jpn, BorderLayout.CENTER);
        cp.add(jpn1, BorderLayout.SOUTH);
        jpn1.add(jbnclose);
        jpn1.add(jbnRegen);
        jmiLOTO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jdp.add(jif);
                jif.setVisible(true);
            }
        });

        for (int i = 0; i < 6; i++) {
            jlbs[i] = new JLabel();
            jlbs[i].setFont(new Font(null, Font.PLAIN, 22));
            jlbs[i].setBackground(Color.pink);
            jlbs[i].setHorizontalAlignment(JLabel.CENTER);
            jpn.add(jlbs[i]);
        }

        lotoGenerate();
        jbnRegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lotoGenerate();
            }
        });
        jbnclose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jif.dispose();
            }
        });
    }

    private void lotoGenerate() {
        int i = 0;
        while (i < 6) {
            data[i] = rnd.nextInt(42) + 1;
            int j = 0;
            boolean flag = true;
            while (j < i && flag) {
                if (data[i] == data[j]) {
                    flag = false;
                }
                j++;
            }
            if (flag) {
                jlbs[i].setText(Integer.toString(data[i]));
                i++;
            }
        }
    }
    private void save_num(JButton oper){
        num=Long.parseLong(jtf.getText());
        jtf.setText(Long.toString(0L));
    }
}



