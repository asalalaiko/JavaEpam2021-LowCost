package by.asalalaiko.listener.task;

import by.asalalaiko.domain.Flight;
import by.asalalaiko.listener.RecalculationCost;
import by.asalalaiko.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.TimerTask;

public class RecalculationCostTask extends TimerTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecalculationCost.class);

    @Override
    public void run() {
        try {
            LOGGER.info("RecalculationCostTask started.");

            List<Flight> flights = (List<Flight>) FlightService.findAll();

            if (flights.size() > 0) {
                flights.stream().forEach(t -> {
                    FlightService.getInstance().updateToCost(t.getId(), BigDecimal.valueOf(t.getCost().doubleValue()*1.1));
                });
            }

        } catch (Exception e) {
            LOGGER.error("Error running RecalculationCostTask", e);
        }
    }
}
