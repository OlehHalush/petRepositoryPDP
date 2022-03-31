//package helpers.listeners;
//
//import com.customertimes.config.AppUser;
//import com.customertimes.config.Driver;
//import com.customertimes.util.ReportUtils;
//import com.customertimes.util.ThreadUtils;
//import io.cucumber.plugin.event.*;
//import io.qameta.allure.Allure;
//import io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm;
//
//public class ExtendedAllureLister extends AllureCucumber6Jvm {
//
//    @Override
//    public void setEventPublisher(EventPublisher publisher) {
//
//        publisher.registerHandlerFor(TestStepFinished.class, event -> {
//            if (ReportUtils.isVideoRecEnabled()) {
//                try {
//                    if (event.getTestStep() instanceof PickleStepTestStep) {
//                        PickleStepTestStep pickleStepTestStep = (PickleStepTestStep) event.getTestStep();
//                        String text = pickleStepTestStep.getStep().getText().replaceAll(
//                                "[\\s+.^:,'\"]", "") + '_' + System.currentTimeMillis() + ".mp4";
//                        if (!event.getResult().getStatus().equals(Status.SKIPPED)) {
//                            Allure.getLifecycle().addAttachment("video", "video/mp4", "mp4",
//                                    ReportUtils.takeVideo(text));
//                        }
//                    }
//                } catch (Exception e) {
//                    Allure.getLifecycle().addAttachment("failed to attach video", "text/plain", "txt",
//                            e.getMessage().getBytes());
//                }
//            }
//            if (event.getResult().getStatus().equals(Status.FAILED)) {
//                try {
//                    Allure.getLifecycle().addAttachment("user info", "text/plain", "txt",
//                            (AppUser.getCurrentUser().getUsername() + "\n" + AppUser.getCurrentUser().getPassword()).getBytes());
//                } catch (Exception e) {
//                    Allure.getLifecycle().addAttachment("failed to get user info", "text/plain", "txt",
//                            e.getMessage().getBytes());
//                }
//                try {
//                    Allure.getLifecycle().addAttachment("session id", "text/plain", "txt",
//                            Driver.getDriver().getSessionId().toString().getBytes());
//                } catch (Exception e) {
//                    Allure.getLifecycle().addAttachment("failed to get session id", "text/plain", "txt",
//                            e.getMessage().getBytes());
//                }
//                try {
//                    Allure.getLifecycle().addAttachment("screenshot on failure", "image/png", "png",
//                            ReportUtils.takeScreenshot());
//                } catch (Exception e) {
//                    Allure.getLifecycle().addAttachment("failed to attach screenshot", "text/plain", "txt",
//                            e.getMessage().getBytes());
//                    try {
//                        Driver.quitDriver();
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        publisher.registerHandlerFor(TestCaseStarted.class, event -> {
//            try {
//                ThreadUtils.registerCurrentThread();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//
//        publisher.registerHandlerFor(TestCaseFinished.class, event -> {
//            try {
//                ThreadUtils.quitIdleThreadsDrivers();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//
//        publisher.registerHandlerFor(TestRunFinished.class, event -> {
//            try {
//                ThreadUtils.quitDrivers();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        });
//
//        super.setEventPublisher(publisher);
//    }
//}