package ofedorova.jms.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.QueueConnectionFactory;
import javax.jms.Session;

@Configuration
@EnableJms
public class JmsConfig {

	@Value("${servers.mq.timeout}")
	private long timeout;
	@Value("${servers.mq.sessionCacheSize}")
	private int sessionCacheSize;
	@Value("${spring.activemq.broker-url}")
	private String url;


	@Bean
	public QueueConnectionFactory queueConnectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		connectionFactory.getPrefetchPolicy().setQueuePrefetch(1);
		RedeliveryPolicy redeliveryPolicy = connectionFactory.getRedeliveryPolicy();
		redeliveryPolicy.setUseExponentialBackOff(true);
		redeliveryPolicy.setMaximumRedeliveries(4);
		return connectionFactory;
	}

	@Bean
	@Primary
	public CachingConnectionFactory queueCachingConnectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
		cachingConnectionFactory.setTargetConnectionFactory(queueConnectionFactory());
		cachingConnectionFactory.setSessionCacheSize(sessionCacheSize);
		cachingConnectionFactory.setReconnectOnException(true);
		return cachingConnectionFactory;
	}

	@Bean
	@Primary
	public JmsTemplate queueTemplate(CachingConnectionFactory queueCachingConnectionFactory) {
		JmsTemplate jmsTemplate = new JmsTemplate(queueCachingConnectionFactory);
		jmsTemplate.setReceiveTimeout(timeout);
		jmsTemplate.setSessionTransacted(true);
		jmsTemplate.setSessionAcknowledgeMode(Session.SESSION_TRANSACTED);
		return jmsTemplate;
	}

}
