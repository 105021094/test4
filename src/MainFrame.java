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
    private JMenuItem jmiLOTO = new JMenuItem("LOTO");
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
        jif.setBounds(0, 0, 300, 100);


        jmF.add(jmiExit);
        jmiExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jmiExit.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

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
}



