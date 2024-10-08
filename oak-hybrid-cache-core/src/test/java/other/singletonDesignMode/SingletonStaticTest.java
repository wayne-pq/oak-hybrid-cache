package other.singletonDesignMode;

/**
 * 单例模式
 *
 * @author qian.pan on 2024/9/5.
 */
public class SingletonStaticTest {
    private SingletonStaticTest() {
    }

    public static SingletonStaticTest getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args) {
        SingletonStaticTest.getInstance();
    }

    private static class SingletonHolder {
        private static final SingletonStaticTest INSTANCE = new SingletonStaticTest();
    }
}
