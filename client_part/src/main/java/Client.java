import changingClasses.Arguments;
import changingClasses.ServerReply;
import layoutsControllers.ScenesController;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    public static SocketChannel ch;
    private static String login = null;

    public static void main(String[] args) {
        try {
            ch = SocketChannel.open();
            while (true) {
                try {
                    ch.connect(new InetSocketAddress("localhost", 4044));
                    System.out.println("Connection established");
                    ch.finishConnect();
                    ByteBuffer buffer = ByteBuffer.allocate(70000);
                    ch.read(buffer);
                    InputStream byteStream = new ByteArrayInputStream(buffer.array(), 0, buffer.limit());
                    ObjectInputStream objStream = new ObjectInputStream(byteStream);
                    ArrayList<String> commands_with_element = (ArrayList<String>) objStream.readObject();
                    byteStream.close();
                    objStream.close();
                    ScenesController scenesController = new ScenesController();
                    ScenesController.getInstance().ch = ch;
                    ScenesController.getInstance().commands_with_elements = commands_with_element;
                    ScenesController.launch(ScenesController.class);
                    //registration();
                    //listenersOfCommands.CommandListener commandListener = new listenersOfCommands.CommandListener(ch, System.in, commands_with_element);
                    //commandListener.start_listening();
                } catch (ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                }finally {
                    System.out.println("Disconnected");
                    if (ch != null) {
                        ch.close();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Can't connect to server");
        }
    }

    private static void registration() throws IOException, InterruptedException {
        boolean registered = false;
        Scanner scanner = new Scanner(System.in);
        while (!registered) {
            System.out.println("You need to log in to the system or make new account. Enter login:");
            System.out.print("$");
            String login = scanner.nextLine();
            Arguments arguments = new Arguments("check_login", login, null);
            ch.write(Arguments.toBuffer(arguments));
            ServerReply about_login = getServerReply();
            if (about_login.massages.get(0).equals("enter_password")) {
                System.out.println("Enter password:");
                System.out.print("$");
                String password = scanner.nextLine();
                arguments = new Arguments("check_password", password, null);
                ch.write(Arguments.toBuffer(arguments));
                ServerReply about_password = getServerReply();
                if (about_password.massages.get(0).equals("correct")) {
                    System.out.println("You entered system successfully with login " + login + " !");
                    return;
                }else {
                    System.out.println("Incorrect password, repeat registration.");
                }
            }
            if(about_login.massages.get(0).equals("unknown_login")){
                System.out.println("You entered unknown login. Enter password to register new user with this login or type & to restart registration:");
                System.out.print("$");
                String password = scanner.nextLine();
                if(password.equals("&")){
                    continue;
                }
                arguments = new Arguments("register_new_user", password, null);
                ch.write(Arguments.toBuffer(arguments));
                ServerReply about_password = getServerReply();
                if (about_password.massages.get(0).equals("correct")) {
                    System.out.println("You entered system successfully with login " + login + " !");
                    return;
                }else {
                    System.out.println("Incorrect password, repeat registration.");
                }
            }
        }
    }

    private static ServerReply getServerReply() throws InterruptedException {
        ServerReply serverReply = null;
        int tries = 0;
        while (serverReply == null) {
            try {
                Thread.sleep(500);
                tries++;
                if (tries >= 30) {
                    System.out.println("Server doesn't reply for 15 seconds, disconnecting this client,try again later");
                    break;
                }
                ByteBuffer buffer = ByteBuffer.allocate(ch.socket().getReceiveBufferSize());
                ch.read(buffer);
                serverReply = ServerReply.toServerReply(buffer);
            } catch (SocketException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return serverReply;
    }
}