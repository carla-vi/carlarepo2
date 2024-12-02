package es.upm.dit.apsv.transportationorderserver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import es.upm.dit.apsv.transportationorderserver.repository.TransportationOrderRepository;
import es.upm.dit.apsv.transportationorderserver.model.TransportationOrder;


@SpringBootApplication
public class TransportationOrderServerApplication {
        public static final Logger log = LoggerFactory.getLogger(TransportationOrderServerApplication.class);
        private TransportationOrderRepository torderRepository;
        public static void main(String[] args) {
                SpringApplication.run(TransportationOrderServerApplication.class, args);
        }
}