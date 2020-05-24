package HomeWork_4L2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top");
    private final JTextField tfLogin = new JTextField("ivan");
    private final JPasswordField tfPassword = new JPasswordField("123");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton btnDisconnect = new JButton("<html><b>Disconnect</b></html>");
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    private final JList<String> userList = new JList<>();
    private PrintStream  out;       // объявляем переменную для записи информации в поток вывода

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI();
            }
        });
    }

    private ClientGUI() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        JScrollPane scrollUser = new JScrollPane(userList);
        String[] users = {"user1", "user2", "user3", "user4", "user5",
                "user_with_an_exceptionally_long_name_in_this_chat"};
        userList.setListData(users);
        scrollUser.setPreferredSize(new Dimension(100, 0));
        cbAlwaysOnTop.addActionListener(this);

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);

        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        btnSend.addActionListener(this);

        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) sendMsg();
            }
        });

        add(scrollLog, BorderLayout.CENTER);
        add(scrollUser, BorderLayout.EAST);
        add(panelTop, BorderLayout.NORTH);
        add(panelBottom, BorderLayout.SOUTH);

        setVisible(true);
    }

    void sendMsg()
    {
        if (!tfLogin.getText().isEmpty() && !tfMessage.getText().isEmpty())
            log.append(msgLog());       // запись сообщение в лог - файл и выводим в поле

        tfMessage.setText("");
        tfMessage.grabFocus();
    }

    private String msgLog()
    {
        String add = tfLogin.getText() + ": " + tfMessage.getText() + "\n";
        try {
            out = new PrintStream(new FileOutputStream("log.txt", true)); // создаём поток вывода
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        out.print(add);   // пишем в наш поток то что ввели
        out.flush();      // производим отчистку потока
        out.close();      // закрываем поток
        return add;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == cbAlwaysOnTop)
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        else if (src == btnSend)
            sendMsg();
        else
            throw new RuntimeException("Unknown source: " + src);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        msg = String.format("Exception in thread \"%s\" %s: %s\n\t at %s",
                t.getName(), e.getClass().getCanonicalName(), e.getMessage(), ste[0]);
        JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
}
