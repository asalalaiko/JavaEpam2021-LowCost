package by.asalalaiko.listener;

import by.asalalaiko.listener.task.RecalculationCostTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Timer;


@WebListener
public class RecalculationCost implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecalculationCost.class);
    private Timer timer = null;

    @Override
    public void contextInitialized(ServletContextEvent sce){
        LOGGER.info("Server Initialized. RecalculationCost started.");
        timer = new java.util.Timer(true);
        //int period = 24*60*60*1000;
        int period = 60*1000;
        timer.schedule(new RecalculationCostTask(), 0, period);

    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        LOGGER.info("Server Destroyed. RecalculationCost stopped.");
    }
}
