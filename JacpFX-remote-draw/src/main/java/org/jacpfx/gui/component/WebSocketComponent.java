package org.jacpfx.gui.component;

import javafx.event.Event;
import org.jacpfx.api.annotations.Resource;
import org.jacpfx.api.annotations.component.Component;
import org.jacpfx.api.annotations.lifecycle.PostConstruct;
import org.jacpfx.api.annotations.lifecycle.PreDestroy;
import org.jacpfx.api.message.Message;
import org.jacpfx.dto.CanvasPoint;
import org.jacpfx.dto.ConnectionProperties;
import org.jacpfx.dto.FragmentNavigation;
import org.jacpfx.gui.configuration.BaseConfig;
import org.jacpfx.rcp.component.CallbackComponent;
import org.jacpfx.rcp.context.Context;
import org.jacpfx.util.MessageUtil;
import org.jacpfx.util.Serializer;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.VertxFactory;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpClient;
import org.vertx.java.core.http.WebSocket;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * This component communicates with the Vertx server through websockets
 * Created by Andy Moncsek on 16.12.13.
 */
@Component(id = BaseConfig.WEBSOCKET_COMPONENT, name = "WebSocketComponent", active = false)
public class WebSocketComponent implements CallbackComponent {
    @Resource
    private Context context;
    private WebSocket webSocket;
    private HttpClient client;

    @Override
    public Object handle(Message<Event, Object> message) throws Exception {
        if (message.isMessageBodyTypeOf(CanvasPoint.class)) {
            webSocket.writeBinaryFrame(new Buffer(Serializer.serialize(message.getMessageBody())));

        } else if (message.isMessageBodyTypeOf(ConnectionProperties.class)) {
            createConnection(message.getTypedMessageBody(ConnectionProperties.class));
        }
        return null;
    }

    private void createConnection(final ConnectionProperties prop) {
        try {
            connect(prop.getIp(), prop.getPort());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void connect(final String ip, final String port) throws InterruptedException {
        final CountDownLatch connected = new CountDownLatch(1);
        final Vertx vertx = VertxFactory.newVertx();
        client = vertx.
                createHttpClient().
                setHost(ip).
                setPort(Integer.valueOf(port)).
                connectWebsocket("/",
                        (ws) -> {
                            connected.countDown();
                            this.webSocket = ws;
                            ws.dataHandler(this::sendPixelDataToCanvas);
                        });
        connected.await(5000, TimeUnit.MILLISECONDS);
        context.send(BaseConfig.DRAWING_PERSPECTIVE, FragmentNavigation.FINISH);
    }

    private void sendPixelDataToCanvas(Buffer data) {
        try {
            context.send(BaseConfig.getGlobalId(BaseConfig.DRAWING_PERSPECTIVE, BaseConfig.CANVAS_COMPONENT), MessageUtil.getMessage(data.getBytes(), CanvasPoint.class));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void onStart() {

    }

    @PreDestroy
    public void onClose() {


    }
}
