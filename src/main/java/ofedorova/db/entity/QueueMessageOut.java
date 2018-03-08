package ofedorova.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "queuemessageout")
@Getter
@Setter
public class QueueMessageOut {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "message", nullable = false)
    private String message;
}
