package utils;//package com.customertimes.util;
//
//import com.customertimes.config.Driver;
//import com.customertimes.screens.SeleniumInteraction;
//import io.appium.java_client.MobileElement;
//import io.appium.java_client.PerformsTouchActions;
//import io.appium.java_client.TouchAction;
//import io.appium.java_client.ios.IOSDriver;
//import io.appium.java_client.touch.WaitOptions;
//import io.appium.java_client.touch.offset.PointOption;
//import org.openqa.selenium.*;
//
//import java.time.Duration;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//public class TouchUtils extends SeleniumInteraction {
//
//    private static int SCROLL_LIMIT = 10;
//
//    private TouchUtils(IOSDriver<MobileElement> driver) {
//        super(driver);
//    }
//
//    public static TouchUtils get() {
//        return new TouchUtils(Driver.getDriver());
//    }
//
//    public void dragAndDropAboveElementCenter(MobileElement from, MobileElement to) {
//        dragAndDropWithHeightRatio(from, to, 0.9);
//    }
//
//    private void dragAndDropWithHeightRatio(MobileElement from, MobileElement to, double ratio) {
//        PointOption fromPoint = getElementCenter(from);
//        PointOption toPoint = PointOption.point(to.getRect().x + (to.getRect().width / 2),
//                (int) ((to.getRect().y + (to.getRect().height / 2)) * ratio));
//        dragAndDrop(fromPoint, toPoint);
//    }
//
//    private void dragAndDropWithHeightRatio(MobileElement from, MobileElement to, double fromRation, double toRatio) {
//        PointOption fromPoint = PointOption.point(from.getCenter().x,
//                (int) ((from.getRect().y + (from.getRect().height / 2) * fromRation)));
//        PointOption toPoint = PointOption.point(to.getCenter().x,
//                (int) ((to.getRect().y + (to.getRect().height / 2) * toRatio)));
//        dragAndDrop(fromPoint, toPoint);
//    }
//
//    public void dragAndDrop(MobileElement from, MobileElement to) {
//        dragAndDrop(getElementCenter(from), getElementCenter(to));
//    }
//
//    public void dragAndDrop(PointOption from, PointOption to) {
//        getTouchAction()
//                .longPress(from)
//                .moveTo(to)
//                .release()
//                .perform();
//    }
//
//    public void dragFromToForDuration(Duration duration, int fromX, int fromY, int toX, int toY) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("duration", duration.toMillis() / 1000.0);
//        params.put("fromX", fromX);
//        params.put("fromY", fromY);
//        params.put("toX", toX);
//        params.put("toY", toY);
//        Driver.getDriver().executeScript("mobile: dragFromToForDuration", params);
//    }
//
//    public void tap(int x, int y) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("x", x);
//        params.put("y", y);
//        Driver.getDriver().executeScript("mobile: tap", params);
//    }
//
//    public void tap(MobileElement element) {
//        getTouchAction()
//                .tap(getElementCenter(element))
//                .perform();
//    }
//
//    public void longTap(MobileElement element) {
//        getTouchAction()
//                .press(getElementCenter(element))
//                .waitAction(WaitOptions.waitOptions(Timeouts.getTimeoutM()))
//                .release()
//                .perform();
//    }
//
//    private TouchAction getTouchAction() {
//        return new TouchAction(driver);
//    }
//
//    private PointOption getElementCenter(WebElement element) {
//        return PointOption.point(
//                element.getRect().x + (element.getRect().width / 2),
//                element.getRect().y + (element.getRect().height / 2)
//        );
//    }
//
//    public void scrollDown() {
//        scrollFromScreenCenter(0.3);
//    }
//
//    public void scrollUp() {
//        scrollFromScreenCenter(0.8);
//    }
//
//    private void scrollFromScreenCenter(double screenHeight) {
//        Dimension dimension = Driver.getDriver().manage().window().getSize();
//        int xCenter = (int) (dimension.getWidth() * 0.5);
//        int scrollStart = (int) (dimension.getHeight() * 0.5);
//        int scrollEnd = (int) (dimension.getHeight() * screenHeight);
//        getTouchAction().longPress(PointOption.point(xCenter, scrollStart)).waitAction().moveTo(PointOption.point(xCenter, scrollEnd)).release().perform();
//    }
//
//    public void scrollDown(double scrollValue) {
//        scrollFromScreenCenter(scrollValue);
//    }
//
//    public void scrollUp(double scrollValue) {
//        scrollFromScreenCenter(scrollValue);
//    }
//
//    public void scrollToElement(MobileElement element, boolean intoView) {
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(" + intoView + ");", element);
//    }
//
//    @Deprecated
//    public void scrollUpUntilElementIsDisplayed(By element, double value) {
//        int i = 0;
//        while (Driver.getDriver().findElement(element).isDisplayed() && ++i < SCROLL_LIMIT) {
//            scrollUp(value);
//        }
//    }
//
//    @Deprecated
//    public static void swipeUpUntilElementIsDisplayed(By element) {
//        new TouchAction((PerformsTouchActions) Driver.getDriver())
//                .longPress(PointOption.point(Driver.getDriver().findElement(element).getCenter()))
//                .waitAction()
//                .moveTo(PointOption.point(Driver.getDriver().findElement(element).getCenter().x, Driver.getDriver().findElement(element).getCenter().y - 40))
//                .release()
//                .perform();
//    }
//
//    public void swipeElementUp(MobileElement element) {
//        swipeElementVertically(element, 0.01);
//    }
//
//    public void swipeElementDown(MobileElement element) {
//        swipeElementVertically(element, 0.99);
//    }
//
//    private void swipeElementHorizontally(MobileElement element, double screenWidth) {
//        Dimension dimension = driver.manage().window().getSize();
//        int swipeEndWidth = (int) (dimension.getWidth() * screenWidth);
//        getTouchAction()
//                .longPress(getElementCenter(element))
//                .waitAction()
//                .moveTo(PointOption.point(swipeEndWidth, element.getCenter().getY()))
//                .release()
//                .perform();
//    }
//
//    private void swipeElementVertically(MobileElement element, double screenHeight) {
//        Dimension dimension = driver.manage().window().getSize();
//        int swipeEndHeight = (int) (dimension.getHeight() * screenHeight);
//        getTouchAction()
//                .longPress(PointOption.point(element.getCenter()))
//                .waitAction()
//                .moveTo(PointOption.point(element.getCenter().x, swipeEndHeight))
//                .release()
//                .perform();
//    }
//
//    public void selectAllAndCut(By by) {
//        new TouchAction<>(driver)
//                .longPress(PointOption.point($(by).getCenter()))
//                .release().perform();
//        Driver.switchCurrentDriverContextToNativeApp();
//        try {
//            WaitUtils.tryWaitUntil(() -> $(By.id("Select All")).isDisplayed());
//            $(By.id("Select All")).click();
//        } catch (NoSuchElementException ignored) {
//        }
//        try {
//            WaitUtils.tryWaitUntil(() -> $(By.id("Cut")).isDisplayed());
//            $(By.id("Cut")).click();
//        } catch (NoSuchElementException ignored) {
//        }
//        Driver.switchCurrentDriverContextToWebApp();
//    }
//
//    public void swipeElementLeft(MobileElement element) {
//        swipeElementHorizontally(element, 0.3);
//    }
//
//    public void swipeElementRight(MobileElement element) {
//        swipeElementHorizontally(element, 0.8);
//    }
//
//    public void deleteSymbolsUsingKeyboard(int amountOfSymbols, MobileElement elementToClear) {
//        List<Keys> keys = IntStream.range(0, amountOfSymbols).boxed()
//                .map(e -> Keys.BACK_SPACE)
//                .collect(Collectors.toList());
//        keys.add(0, Keys.END);
//        WaitUtils.waitUntil(() -> {
//            elementToClear.sendKeys(keys.toArray(new CharSequence[amountOfSymbols]));
//            return true;
//        });
//    }
//
//    public void deleteSymbolsUsingKeyboard(int amountOfSymbols, By elementToClear) {
//        deleteSymbolsUsingKeyboard(amountOfSymbols, $(elementToClear));
//    }
//}
