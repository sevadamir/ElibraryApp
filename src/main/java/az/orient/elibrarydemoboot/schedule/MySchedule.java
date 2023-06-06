package az.orient.elibrarydemoboot.schedule;

import az.orient.elibrarydemoboot.service.impl.CustomerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MySchedule {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySchedule.class);

    @Scheduled(fixedRate = 3000)
    public void fixedRate() {
        LOGGER.info("hello schedule");

    }

}
