package me.yonezu.ws;

import com.google.gson.JsonObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import com.google.gson.Gson;

import java.net.URI;

public class WSClient extends WebSocketClient {
    public WSClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connected to JellyLabs websocket server");
    }

    @Override
    public void onMessage(String message) {
        // parse to json object
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
        System.out.println("Received: " + jsonObject.get("command").getAsString());
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Disconnected from JellyLabs websocket server");
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("Error from websocket connection: " + ex.getMessage());
    }
}