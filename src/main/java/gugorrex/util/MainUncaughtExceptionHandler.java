package gugorrex.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainUncaughtExceptionHandler extends ExceptionHandler implements Thread.UncaughtExceptionHandler {

    private static final Logger logger = LogManager.getLogger(MainUncaughtExceptionHandler.class);

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        exception(logger, Level.FATAL, t, e);
    }
}
