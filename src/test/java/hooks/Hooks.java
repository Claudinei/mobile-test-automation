package hooks;

import drivers.DriverFactory;
import io.cucumber.java.Before;
import io.cucumber.java.After;

public class Hooks {
    @Before
    public void setUp() {
        System.out.println("Hooks: Inicializando driver");
        DriverFactory.initialize();
    }

    @After
    public void tearDown() {
        System.out.println("Hooks: Finalizando driver");
        DriverFactory.quitDriver();
    }
}