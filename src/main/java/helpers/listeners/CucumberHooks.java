//package helpers.listeners;
//
//import com.customertimes.api.WSC;
//import com.customertimes.api.services.UserAPIServices;
//import com.customertimes.config.*;
//import io.cucumber.plugin.ConcurrentEventListener;
//import io.cucumber.plugin.event.EventHandler;
//import io.cucumber.plugin.event.EventPublisher;
//import io.cucumber.plugin.event.TestRunFinished;
//import io.cucumber.plugin.event.TestRunStarted;
//
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import static com.customertimes.api.services.AccountAPIServices.getAllExistedAutoAccountIds;
//import static com.customertimes.api.services.BrandAPIServices.getAllExistedAutoBrandAndLabelsIds;
//import static com.customertimes.api.services.EventApiServices.getAllExistedAutoEventIds;
//import static com.customertimes.api.services.KPILineAPIServices.getAllExistedAutoKPILineIds;
//import static com.customertimes.api.services.SurveyAPIServices.getAllExistedAutoSurveyIds;
//import static com.customertimes.api.services.SurveyQuestionAPIServices.getAllExistedAutoSurveyQuestionIds;
//import static com.customertimes.api.services.SurveySummaryAPIServices.getAllExistedAutoSurveySummaryIds;
//import static com.customertimes.config.RunParameters.REMOVE_ALL_TOGGLE;
//
//public class CucumberHooks implements ConcurrentEventListener {
//    @Override
//    public void setEventPublisher(EventPublisher eventPublisher) {
//        eventPublisher.registerHandlerFor(TestRunStarted.class, beforeAll);
//        eventPublisher.registerHandlerFor(TestRunFinished.class, afterAll);
//    }
//
//    private final EventHandler<TestRunStarted> beforeAll = event -> {
//        // something that needs doing before everything
//        String removeAllProperty = ConfigReader.getRunParameter(REMOVE_ALL_TOGGLE);
//        if ("true".equals(removeAllProperty)) {
//            AppUser previousUser = AppUser.getCurrentUser();
//            AppUser.switchToUser(AppUser.ADMIN);
//            String ambassadorId = UserAPIServices.getIdByUsername(ConfigReader.getRunParameter(RunParameters.AMBASSADOR_USERNAME));
//            String backOfficeId = UserAPIServices.getIdByUsername(ConfigReader.getRunParameter(RunParameters.BACK_OFFICE_USERNAME));
//
//            List<String> accountIds = getAllExistedAutoAccountIds(ambassadorId, backOfficeId);
//            if (accountIds.size() > 0) {
//                for (String acct : accountIds) {
//                    WSC.delete(acct);
//                }
//            }
//
//            List<String> surveyIds = getAllExistedAutoSurveyIds(ambassadorId, backOfficeId);
//            if (surveyIds.size() > 0) {
//                for (String surveyId : surveyIds) {
//                    WSC.delete(surveyId);
//                }
//            }
//
//            List<String> Ids = getAllExistedAutoBrandAndLabelsIds(ambassadorId, backOfficeId);
//            if (Ids.size() > 0) {
//                for (String id : Ids) {
//                    WSC.delete(id);
//                }
//            }
//
//            List<String> IdsKpi = getAllExistedAutoKPILineIds(ambassadorId, backOfficeId);
//            if (IdsKpi.size() > 0) {
//                for (String id : IdsKpi) {
//                    WSC.delete(id);
//                }
//            }
//
//            List<String> IdsSQ = getAllExistedAutoSurveyQuestionIds(ambassadorId, backOfficeId);
//            if (IdsSQ.size() > 0) {
//                for (String id : IdsSQ) {
//                    WSC.delete(id);
//                }
//            }
//
//            List<String> IdsEvent = getAllExistedAutoEventIds(ambassadorId, backOfficeId);
//            if (IdsEvent.size() > 0) {
//                for (String id : IdsEvent) {
//                    WSC.delete(id);
//                }
//            }
//
//            List<String> IdsSurveySummary = getAllExistedAutoSurveySummaryIds(ambassadorId, backOfficeId);
//            if (IdsSurveySummary.size() > 0) {
//                for (String id : IdsSurveySummary) {
//                    WSC.delete(id);
//                }
//            }
//
//            AppUser.switchToUser(previousUser);
//        }
//    };
//
//    private final EventHandler<TestRunFinished> afterAll = event -> {
//        List<AppUser> activeUsers = Driver.getMobileDrivers().entrySet().stream()
//                .filter(e -> e.getValue() != null)
//                .map(Map.Entry::getKey)
//                .collect(Collectors.toList());
//        for (AppUser appUser : activeUsers) {
//            AppUser.switchToUser(appUser);
//            if (Driver.getMobileDrivers().get(AppUser.getCurrentUser()).getMobileDriver() != null)
//                Driver.quitMobileDriver();
//        }
//    };
//}
