package gugorrex.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExceptionHandler {

    private static final Logger logger = LogManager.getLogger(ExceptionHandler.class);

    /**
     * !!! Assumes that the exception occurred in the same thread as this method is called !!!
     * automatically logs exceptions in convenient format as ERROR.
     * @param e occurred exception
     */
    public static void exception(Throwable e) {
        exception(Thread.currentThread(), e);
    }

    /**
     * !!! Assumes that the exception occurred in the same thread as this method is called !!!
     * automatically logs exceptions in convenient format as FATAL.
     * @param e occurred exception
     */
    public static void exceptionFatal(Throwable e) {
        exceptionFatal(Thread.currentThread(), e);
    }

    /**
     * automatically logs exceptions in convenient format as ERROR
     * @param t thread in which exception occurred
     * @param e occurred exception
     */
    public static void exception(Thread t, Throwable e) {
        exception(logger, Level.ERROR, t, e);
    }

    /**
     * automatically logs exceptions in convenient format as FATAL
     * @param t thread in which exception occurred
     * @param e occurred exception
     */
    public static void exceptionFatal(Thread t, Throwable e) {
        exception(logger, Level.FATAL, t, e);
    }

    protected static void exception(Logger logger, Level level, Thread t, Throwable e) {
        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append(e).append("\n\tin thread: '").append(t.getName()).append("' with id: ").append(t.getId()).append("\n");
        logBuilder.append("\tStackTrace:\n").append("\t[\n");
        for (StackTraceElement trace : e.getStackTrace()) {
            logBuilder.append("\t\t").append(trace.toString()).append("\n");
        }
        logBuilder.append("\t]");
        logger.log(level, logBuilder);
    }
}
