package servent.handler;

import servent.message.ImageInfoMessage;

public class ImageInfoHandler implements MessageHandler {
    private ImageInfoMessage clientMessage;

    public ImageInfoHandler(ImageInfoMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {

    }
}
