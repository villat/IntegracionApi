package ar.com.kecat.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //Se ejecuta todos los días a la medianoche
    @Scheduled(cron = "0 0 0 * * ?")
    public void reportCurrentTime() {
        log.info("Procesamiento de liquidaciones en curso {}", dateFormat.format(new Date()));

        log.info("Procesamiento de liquidaciones finalizado {}", dateFormat.format(new Date()));
    }
}