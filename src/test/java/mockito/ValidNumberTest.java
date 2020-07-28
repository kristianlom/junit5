package mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidNumberTest {

    private ValidNumber validNumber;

    @BeforeEach
    void setUp() {
        validNumber = new ValidNumber();
    }

    @AfterEach
    void tearDown() {
        validNumber = null;
    }

    @Test
    void checkTest() {
        assertTrue(validNumber.check(5));
    }

    @Test
    void checkNegativeTest() {
        assertFalse(validNumber.check(-5));
    }

    @Test
    void checkStringTest() {
        assertFalse(validNumber.check("5"));
    }

    @Test
    void checkZeroTest() {
        assertTrue(validNumber.checkZero(-57));
    }

    @Test
    void checkZeroStringTest() {
        assertFalse(validNumber.check("5"));
    }

    @Test
    void checkZero0Test() {
        assertThrows(ArithmeticException.class, () -> validNumber.checkZero(0));
    }

    @Test
    void doubleToIntTest() {
        assertEquals(9, validNumber.doubleTotInt(9.999));
    }

    @Test
    void doubleToIntErrorTest() {
        assertEquals(0, validNumber.doubleTotInt("9.999"));
    }

}