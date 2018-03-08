package ofedorova.common.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ofedorova.common.services.IntegrationService;

@RestController
@Slf4j
public class IntegrationContoller {

    @Autowired
    private IntegrationService integrationService;

    @RequestMapping("/out")
    public void formMessageAndSendToMQ(){
        try {
            integrationService.formMessageAndSendToMQ();
        } catch (Exception e) {
            log.error("Failed to generate and send a message in MQ!", e);
        }
    }
}
