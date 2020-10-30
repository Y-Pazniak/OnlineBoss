import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class AlexandrServer {

    String[] advices = new String[]{"Составьте план работ на сегодня, я через 20 минут подойду, обсудим.", "необходимо форсировать работы", "данная проблема решается правильно принятым решением", "необходимо всесторонне изучить данный вопрос", "создайте общий чат, подключите меня", "я вас услышал", "поставьте задачу по SMART"};

    public static void main(String[] args) {
        new AlexandrServer().go();
    }

    public void go() {
        try {
            ServerSocket ss = new ServerSocket(6666);

            while(true) {
                Socket sock = ss.accept();
                PrintWriter pr = new PrintWriter(sock.getOutputStream());
                pr.print(getAdvice());
                pr.close();
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }
    }

    public String getAdvice() {
        int number = (int)(Math.random() * (double)this.advices.length);
        return this.advices[number];
    }
}
