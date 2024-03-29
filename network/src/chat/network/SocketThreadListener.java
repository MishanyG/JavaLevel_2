package chat.network;

import java.net.Socket;
import java.net.SocketException;

public interface SocketThreadListener {
    void onSocketStart(SocketThread thread, Socket socket);
    void onSocketStop(SocketThread thread);
    void onSocketReady(SocketThread thread, Socket socket);
    void onReceiveString(SocketThread thread, Socket socket, String msg) throws SocketException;
    void onSocketException(SocketThread thread, Throwable throwable);
}