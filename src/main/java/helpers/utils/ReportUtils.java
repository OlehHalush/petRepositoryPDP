package helpers.utils;//package com.customertimes.util;
//
//import com.customertimes.config.AppUser;
//import com.customertimes.config.ConfigReader;
//import com.customertimes.config.Driver;
//import com.customertimes.config.RunParameters;
//import io.appium.java_client.ios.IOSStartScreenRecordingOptions;
//import io.qameta.allure.Allure;
//import org.apache.commons.codec.binary.Base64;
//import org.openqa.selenium.NoSuchSessionException;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//public class ReportUtils {
//    public static byte[] writeVideoBase64StringToFile(String base64String, String videoDirPath, String videoFileName) throws IOException {
//        byte[] data = Base64.decodeBase64(base64String);
//        Files.createDirectories(Paths.get(videoDirPath));
//        Files.write(Paths.get(videoDirPath + videoFileName), data);
//        return data;
//    }
//
//    public static void startScreenRecordingIfEnabled() {
//        if (!ReportUtils.isVideoRecEnabled())
//            return;
//        try {
//            Driver.getDriver().startRecordingScreen(
//                    new IOSStartScreenRecordingOptions()
//                            .withVideoType("h264")
//                            .withVideoQuality(IOSStartScreenRecordingOptions.VideoQuality.LOW)
//                            .withVideoScale("800:600")
//                            .withVideoFilters("transpose=2")
//                            .enableForcedRestart());
//        } catch (IllegalStateException | NullPointerException | NoSuchSessionException ignored) {
//        }
//    }
//
//    public static byte[] takeVideo(String videoFileName) throws IOException {
//        String targetPath = "target/";
//        String videoDirName = "video/";
//        String fileContent = Driver.getDriver().stopRecordingScreen();
//        ReportUtils.startScreenRecordingIfEnabled();
//
//        return ReportUtils.writeVideoBase64StringToFile(fileContent, targetPath + videoDirName, videoFileName);
//    }
//
//    public static byte[] takeScreenshot() {
//        Driver.getDriver().setSetting("screenshotOrientation", "landscapeLeft");
//        return ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
//    }
//
//    public static boolean isVideoRecEnabled() {
//        return Boolean.parseBoolean(ConfigReader.getRunParameter(RunParameters.REPORTER_VIDEO))
//                && !AppUser.getCurrentUser().equals(AppUser.ADMIN);
//    }
//
//    public static void addAttachment(String name, String content) {
//        Allure.addAttachment(name, content == null ? "null" : content);
//    }
//}
