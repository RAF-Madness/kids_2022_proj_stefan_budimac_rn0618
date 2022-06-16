package servent.handler;

import servent.message.ImageInfoRequestMessage;

public class ImageInfoRequestHandler implements MessageHandler {
    private ImageInfoRequestMessage clientMessage;

    public ImageInfoRequestHandler(ImageInfoRequestMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {

    }
}
