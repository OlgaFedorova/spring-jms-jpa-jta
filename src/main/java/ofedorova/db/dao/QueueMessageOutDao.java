package ofedorova.db.dao;

import lombok.extern.slf4j.Slf4j;
import ofedorova.db.entity.QueueMessageOut;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Slf4j
public class QueueMessageOutDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public QueueMessageOut add(QueueMessageOut queueMessageOut){
        log.debug("Insert entity QueueMessageOut in db: %s!", queueMessageOut);
        entityManager.persist(queueMessageOut);
        return queueMessageOut;
    }
}
