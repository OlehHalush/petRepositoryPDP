public class Runner {

    public static void mainOneThread() {
        Tests.beforeClass();
        Tests.beforeTest();
        Tests.openDownloadPage();
        Tests.afterClass();

    }

    public static void waitABit() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Tests.beforeClass();

        new Thread(() -> {
            Tests.beforeTest();
            Tests.openDownloadPage();
        }).start();

        new Thread(() -> {
            Tests.beforeTest();
            Tests.openFeaturesPage();
        }).start();


//        Tests.afterClass();
    }


}
