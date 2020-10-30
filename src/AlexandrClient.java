import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AlexandrClient {

    JTextField inputText;
    JTextArea chatWindow;

    public static void main(String[] args) {
        new AlexandrClient().go();
    }

    public void go(){
        JFrame frame = new JFrame("Virtual Alexandr");
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.setBorder(BorderFactory.createEmptyBorder(0,30,0,30));

        JPanel northPanel = new JPanel();
        northPanel.setBorder(BorderFactory.createEmptyBorder(30, 0,0,0));

        chatWindow = new JTextArea(10, 34);
        JScrollPane scrollPane = new JScrollPane(chatWindow);
        chatWindow.setEditable(false);
        chatWindow.setLineWrap(true);
        chatWindow.setWrapStyleWord(true);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        northPanel.add(scrollPane);
        frame.getContentPane().add(BorderLayout.NORTH, northPanel);
        inputText = new JTextField(13);
        Font fontBigger = new Font("bigger", Font.BOLD, 20);
        inputText.setFont(fontBigger);
        panel.add(inputText);

        JButton sendButton = new JButton("Спросить Александра");
        sendButton.addActionListener(new sendActionListener());
        panel.add(BorderLayout.CENTER, sendButton);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500,400);
    }


    public void sendMessage(){
        try {
            Socket socket = new Socket("127.0.0.1", 6666);
            BufferedReader bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            chatWindow.append(bfr.readLine() + "\n");
            bfr.close();
        }
        catch (Exception exc){exc.printStackTrace();}
    }

    public class sendActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sendMessage();
        }
    }

}


