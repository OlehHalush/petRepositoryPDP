import org.junit.Test;

public class Runner {

    public static void main(String[] args) {

        new Thread(() -> {
            Tests.beforeTest();
            Tests.openDownloadPage();
            Tests.afterClass();
        }).start();

        new Thread(() -> {
            Tests.beforeTest();
            Tests.openFeaturesPage();
            Tests.afterClass();
        }).start();

    }

}
