package executor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * The type Executor service
 * executor service for running java threads
 */
public class ExecutorService {

    private static final int EXECUTOR_COUNT = 20;
    private static final int DEFAULT_KEEP_ALIVE_TIME = 1000;
    private final java.util.concurrent.ExecutorService executorservice;

    /**
     * Instantiates a new Executor service.
     */
    public ExecutorService() {
        this.executorservice = new ThreadPoolExecutor(EXECUTOR_COUNT,
                EXECUTOR_COUNT * 2, DEFAULT_KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    }

    /**
     * Submit list.
     *
     * @param <T>   the type parameter
     * @param tasks the tasks
     * @return the list
     */
    public <T> List<T> submit(Collection<Callable<T>> tasks) {
        if (tasks.isEmpty()) {
            return Collections.emptyList();
        }
        try {
            final List<Future<T>> futures = executorservice.invokeAll(tasks);
            return getResults(futures);
        } catch (InterruptedException | java.util.concurrent.ExecutionException e) {
            System.out.println("exception when invoking tasks" + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    private <T> List<T> getResults(List<Future<T>> futures)
            throws InterruptedException, java.util.concurrent.ExecutionException {
        List<T> results = new ArrayList<>();
        for (Future<T> future : futures) {
            results.add(future.get());
        }
        return results;
    }

}
