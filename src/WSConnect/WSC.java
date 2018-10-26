package WSConnect;

import mainlogic.TransportDataThread;
import util.TextAreaScrollUtil;

import javax.swing.*;

/**
 * Created by rio on 2018/10/26.
 */
public class WSC {
    private JPanel mainjp;
    private JTextArea textArea1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("WSC");
        WSC wsc = new WSC();
        frame.setContentPane(wsc.mainjp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        new TransportDataThread(wsc.textArea1).start();
    }
}
