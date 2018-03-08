package ofedorova.jms.services;

public interface SendlerToMq {

    void sendMessage(String message) throws Exception;
}
