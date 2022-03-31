package helpers.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.URL;
import java.net.UnknownHostException;

public class NetUtils {

    private static Integer lastWdaLocalPort;

    public static int nextFreeWdaPort() {
        int from = 8100;
        int to = 9000;

        if (lastWdaLocalPort == null) {
            lastWdaLocalPort = from;
        } else {
            lastWdaLocalPort++;
        }

        while (lastWdaLocalPort < to) {
            if (isLocalPortFree(lastWdaLocalPort)) {
                return lastWdaLocalPort;
            }
            lastWdaLocalPort++;
        }

        throw new IllegalStateException("No free port for WDA");
    }

    private static boolean isLocalPortFree(int port) {
        try {
            new ServerSocket(port).close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean isLocalHost(URL url) throws UnknownHostException {
        return InetAddress.getByName(url.getHost()).getHostAddress().equals("127.0.0.1");
    }
}
