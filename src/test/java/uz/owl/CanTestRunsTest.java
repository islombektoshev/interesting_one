package uz.owl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CanTestRunsTest {

    @Test
    void canTestsRun() {
        System.out.println("test can run!");
        assertEquals(2, 1 + 1);

    }
}
