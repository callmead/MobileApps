package edu.utep.cs.cs4330.mychat;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.net.InetSocketAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    //private static String LOCAL_HOST = "10.0.2.2"; //not 127.0.0.1 on emulator!
    private static String LOCAL_HOST = "opuntia.cs.utep.edu";
    //private static String LOCAL_HOST = "192.168.0.13"; // use ipconfig to find out your IP number

    private static String CHAT_SERVER = LOCAL_HOST;
    private static final int PORT_NUMBER = 8000;
    private static final int CONNECTION_TIMEOUT = 5000; // in milliseconds

    /** Socket connected to a chat server. */
    private Socket socket;

    /** Handler associated with the UI thread. */
    private Handler handler;

    /** View to display all received/sent messages. */
    private ListView msgView;

    /** List adapter associated with the msgView view. */
    private ArrayAdapter<String> msgList;

    private Button connectButton;
    private EditText msgEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();

        EditText serverEdit = findViewById(R.id.serverEdit);
        serverEdit.setText(CHAT_SERVER);
        connectButton = findViewById(R.id.connectButton);
        connectButton.setOnClickListener(this::connectClicked);

        msgView = (ListView) findViewById(R.id.msgView);
        msgList = new ArrayAdapter<>(this, R.layout.text_received_msg);
        msgView.setAdapter(msgList);

        msgEdit = findViewById(R.id.msgEdit);
        Button button = findViewById(R.id.sendButton);
        button.setOnClickListener(view -> sendMessage(msgEdit.getText().toString()));
    }

    /** Called when the connect button is clicked. */
    private void connectClicked(View view) {
        if (isConnected()) {
            showToast("Already connected!");
        } else {
            EditText serverEdit = findViewById(R.id.serverEdit);
            connectToServer(serverEdit.getText().toString(), PORT_NUMBER);
        }
    }

    /** Is connected to a chat server? */
    private boolean isConnected() {
        return socket != null;
    }

    /** Connect to the given chat server. */
    private void connectToServer(String server, int port) {
        new Thread(() -> {
            socket = createSocket(server, port);
            if (socket != null) {
                //--
                // TODO: WRITE YOUR CODE HERE
                //--

            }
            handler.post(() -> showToast(socket != null ? "Connected." : "Failed to connect!"));
        }).start();
    }

    /** Create a socket to the given host and port number. */
    private Socket createSocket(String host, int port) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), CONNECTION_TIMEOUT);
            return socket;
        } catch (Exception e) {
            Log.d("MyChat", e.toString());
        }
        return null;
    }

    /** Send the given message to the chat server.
     * To be called when the send button is clicked. */
    private void sendMessage(String msg) {
        if (socket == null) {
            showToast("Not connected!");
            return;
        }

        //--
        // TODO: WRITE YOUR CODE HERE
        //--

    }

    /** Display the given message by appending it to the message list view. */
    private void displayMessage(String msg) {
        msgList.add(msg);
        msgView.smoothScrollToPosition(msgList.getCount() - 1);
    }

    /** Show a toast message. */
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //--
    //-- TODO: WRITE YOUR CODE HERE
    //--

}