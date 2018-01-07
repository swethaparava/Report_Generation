package report;

import DBConnections.DBConnectionPool;
import executor.ExecutorService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;


/**
 * The type Report generation processor.
 */
public class ReportGenerationProcessor {

    private static DBConnectionPool dbConnectionPool = new DBConnectionPool();
    private static ExecutorService executorService = new ExecutorService();
    private static int TOTAL_NUMBER_OF_TASKS = 5000;
    private static int NUMBER_OF_PARALLEL_THREADS = 100;


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        // create report generation requests
        List<ReportGenerationRequest> reportGenerationRequestList = getReportGenerationRequests();

        List<List<Callable<Map<String, Object>>>> executionReport1 = new ArrayList<>();
        List<List<Callable<Map<String, Object>>>> executionReport2 = new ArrayList<>();

        List<Callable<Map<String, Object>>> list1 = new ArrayList<>();
        List<Callable<Map<String, Object>>> list2 = new ArrayList<>();

        // split the requests into multiple batches
        for (ReportGenerationRequest reportGenerationRequest : reportGenerationRequestList) {
            if (!list1.isEmpty() && list1.size() % NUMBER_OF_PARALLEL_THREADS == 0) {
                executionReport1.add(new ArrayList<>(list1));
                list1.clear();
            } else {
                addToList(list1, reportGenerationRequest.getReportGenerationRequestId(), reportGenerationRequest.getReport1(), "QUERY1");
            }

            if (!list2.isEmpty() && list2.size() % NUMBER_OF_PARALLEL_THREADS == 0) {
                executionReport2.add(new ArrayList<>(list2));
                list2.clear();
            } else {
                addToList(list2, reportGenerationRequest.getReportGenerationRequestId(), reportGenerationRequest.getReport2(), "QUERY2");
            }
        }
        if (!list1.isEmpty()) {
            executionReport1.add(new ArrayList<>(list1));
        }
        if (!list2.isEmpty()) {
            executionReport2.add(new ArrayList<>(list2));
        }

        long starttime = System.currentTimeMillis();
        for (int i = 0; i < executionReport1.size(); i++) {
            List<Callable<Map<String, Object>>> list = new ArrayList();
            list.addAll(executionReport1.get(i));
            list.addAll(executionReport2.get(i));
            List<Map<String, Object>> res = executorService.submit(list);
            for (Map<String, Object> map : res) {
                //map.forEach((key, value) -> System.out.println(key + " " + value));
            }
        }

        long endtime = System.currentTimeMillis();
        System.out.println("total time taken:" + (endtime - starttime));
    }

    private static void addToList(List<Callable<Map<String, Object>>> list, String requestID, String report, String query) {
        list.add(new Callable<Map<String, Object>>() {
            @Override
            public Map<String, Object> call() throws Exception {
                Map<String, Object> reportIdVsData = new HashMap<>();
                reportIdVsData
                        .put(requestID, callReport(report, query));
                return reportIdVsData;
            }
        });
    }

    private static List<ReportGenerationRequest> getReportGenerationRequests() {
        List<ReportGenerationRequest> reportGenerationRequestList = new ArrayList<>();
        for (int i = 0; i < TOTAL_NUMBER_OF_TASKS; i++) {
            reportGenerationRequestList.add(new ReportGenerationRequest());
        }
        return reportGenerationRequestList;
    }

    private static Object callReport(String report, String queryType) {
        if (queryType.equalsIgnoreCase("QUERY1")) {
            return dbConnectionPool.executequery1(report);
        }

        if (queryType.equalsIgnoreCase("QUERY2")) {
            return dbConnectionPool.executequery2(report);
        }
        return null;
    }
}
