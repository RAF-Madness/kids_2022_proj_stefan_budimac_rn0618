package servent.handler;

import servent.message.StartJobGenesisMessage;

public class StartJobGenesisHandler implements MessageHandler {
    public StartJobGenesisMessage clientMessage;

    public StartJobGenesisHandler(StartJobGenesisMessage clientMessage) {
        this.clientMessage = clientMessage;
    }

    @Override
    public void run() {

    }
}
