import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ListaClient {
    private final ArrayList<Socket> listaSockets;

    public ListaClient() {
        listaSockets = new ArrayList<>();
    }

    public synchronized void addClient(Socket c) {
        listaSockets.add(c);
    }

    public synchronized void removeClient(int i) throws IOException {
        listaSockets.get(i).close();
        listaSockets.remove(i);
    }

    public synchronized void sendAll(String message, Socket client) throws IOException {
        for (Socket socket : listaSockets) {
            if (socket != client) {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(message);
            }
        }
    }
}