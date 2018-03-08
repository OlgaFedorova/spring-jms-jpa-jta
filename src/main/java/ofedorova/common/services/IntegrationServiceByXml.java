package ofedorova.common.services;


import lombok.extern.slf4j.Slf4j;
import ofedorova.db.dao.QueueMessageOutDao;
import ofedorova.db.entity.QueueMessageOut;
import ofedorova.jms.services.SendlerToMq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class IntegrationServiceByXml implements IntegrationService {

    @Autowired
    private SendlerToMq sendlerToMq;

    @Autowired
    private QueueMessageOutDao queueMessageOutDao;

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void formMessageAndSendToMQ() throws Exception {
        log.debug("Start: Form and send message to MQ.");
        String text = "Hello! This is test-message!";

        QueueMessageOut queueMessageOut = new QueueMessageOut();
        queueMessageOut.setMessage(text);

        this.queueMessageOutDao.add(queueMessageOut);
        this.sendlerToMq.sendMessage(text);
        log.debug("End: Form and send message to MQ.");
    }

}
