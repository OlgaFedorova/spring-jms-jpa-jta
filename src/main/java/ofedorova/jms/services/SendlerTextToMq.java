package ofedorova.jms.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.TextMessage;

@Service
@Slf4j
public class SendlerTextToMq implements SendlerToMq {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${servers.mq.queue}")
    private String queue;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void sendMessage(String message) {
        log.debug("Send text to MQ!");
        this.jmsTemplate.send(this.queue, session -> {
            TextMessage msg = session.createTextMessage();
            msg.setText(message);
            return msg;
        });
    }
}
