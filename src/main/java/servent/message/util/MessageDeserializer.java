package servent.message.util;

import com.google.gson.*;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import servent.message.*;

public class MessageDeserializer {
    private RuntimeTypeAdapterFactory<Message> adapterFactory;

    public MessageDeserializer(String messageTypeElement) {
        adapterFactory = RuntimeTypeAdapterFactory.of(Message.class, messageTypeElement);
        adapterFactory.registerSubtype(HailMessage.class, "HAIL");
        adapterFactory.registerSubtype(ContactMessage.class, "CONTACT");
        adapterFactory.registerSubtype(SystemKnockMessage.class, "SYSTEM_KNOCK");
        adapterFactory.registerSubtype(WelcomeMessage.class, "WELCOME");
        adapterFactory.registerSubtype(ConnectionRequestMessage.class, "CONNECTION_REQUEST");
        adapterFactory.registerSubtype(ConnectionResponseMessage.class, "CONNECTION_RESPONSE");
        adapterFactory.registerSubtype(EnterMessage.class, "ENTER");
        adapterFactory.registerSubtype(JoinMessage.class, "JOIN");
    }

    public void registerMessageType(Class<? extends Message<?>> messageType, String messageTypeName) {
        adapterFactory = adapterFactory.registerSubtype(messageType, messageTypeName);
    }

    public Message<?> deserialize(String json) {
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(adapterFactory).create();
        return gson.fromJson(json, Message.class);
    }
}
