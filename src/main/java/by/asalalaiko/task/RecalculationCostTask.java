package by.asalalaiko.task;

import by.asalalaiko.domain.Flight;
import by.asalalaiko.listener.RecalculationCost;
import by.asalalaiko.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimerTask;

public class RecalculationCostTask extends TimerTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecalculationCost.class);

    @Override
    public void run() {
        try {
            LOGGER.info("RecalculationCostTask started.");
            System.out.println("+++++++++++++++++++++RUN TASK+++++++++++++++++++++++"+ LocalDateTime.now());
            List<Flight> flights = (List<Flight>) FlightService.findAll();
            if (flights.size() > 0) {
                flights.stream().forEach(t -> {
                    FlightService.getInstance().updateToCost(t.getId(), BigDecimal.valueOf(t.getCost().doubleValue()*1.1));
                });
                System.out.println("+++++++++++++++++++++RUN TASK+++++++++++++++++++++++"+ LocalDateTime.now());
            }

        } catch (Exception e) {
            LOGGER.error("Error running RecalculationCostTask", e);
        }
    }
}
