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
        adapterFactory.registerSubtype(RejectMessage.class, "REJECT");
        adapterFactory.registerSubtype(SystemKnockMessage.class, "SYSTEM_KNOCK");
        adapterFactory.registerSubtype(WelcomeMessage.class, "WELCOME");
        adapterFactory.registerSubtype(ConnectionRequestMessage.class, "CONNECTION_REQUEST");
        adapterFactory.registerSubtype(ConnectionResponseMessage.class, "CONNECTION_RESPONSE");
        adapterFactory.registerSubtype(EnterMessage.class, "ENTER");
        adapterFactory.registerSubtype(JoinMessage.class, "JOIN");
        adapterFactory.registerSubtype(LeaveMessage.class, "LEAVE");
        adapterFactory.registerSubtype(QuitMessage.class, "QUIT");
        adapterFactory.registerSubtype(PurgeMessage.class, "PURGE");
        adapterFactory.registerSubtype(StopShareJobMessage.class, "STOP_SHARE_JOB");
        adapterFactory.registerSubtype(StoppedJobInfoMessage.class, "STOPPED_JOB_INFO");
        adapterFactory.registerSubtype(StartJobGenesisMessage.class, "START_JOB_GENESIS");
        adapterFactory.registerSubtype(ApproachClusterMessage.class, "APPROACH_CLUSTER");
        adapterFactory.registerSubtype(ClusterKnockMessage.class, "CLUSTER_KNOCK");
        adapterFactory.registerSubtype(ClusterWelcomeMessage.class, "CLUSTER_WELCOME");
        adapterFactory.registerSubtype(EnteredClusterMessage.class, "ENTERED_CLUSTER");
        adapterFactory.registerSubtype(ClusterConnectionRequestMessage.class, "CLUSTER_CONNECTION_REQUEST");
        adapterFactory.registerSubtype(ClusterConnectionResponseMessage.class, "CLUSTER_CONNECTION_RESPONSE");
        adapterFactory.registerSubtype(StartJobMessage.class, "START_JOB");
        adapterFactory.registerSubtype(ImageInfoRequestMessage.class, "IMAGE_INFO_REQUEST");
        adapterFactory.registerSubtype(ImageInfoMessage.class, "IMAGE_INFO");
        adapterFactory.registerSubtype(JobStatusRequestMessage.class, "JOB_STATUS_REQUEST");
        adapterFactory.registerSubtype(JobStatusMessage.class, "JOBS_STATUS");
    }

    public void registerMessageType(Class<? extends Message<?>> messageType, String messageTypeName) {
        adapterFactory = adapterFactory.registerSubtype(messageType, messageTypeName);
    }

    public Message<?> deserialize(String json) {
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(adapterFactory).create();
        return gson.fromJson(json, Message.class);
    }
}
