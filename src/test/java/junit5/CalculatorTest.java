package junit5;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CalculatorTest")
class CalculatorTest {

    private static Calculator calculatorStatic;
    private Calculator calculator;
    private Calculator calculatorNull;

    @BeforeAll
    static void beforeAllTests() {
        calculatorStatic = new Calculator();
        System.out.println("@BeforeAll -> beforeAllTests()");
    }

    @AfterAll
    static void afterAllTests() {
        calculatorStatic = null;
        System.out.println("@AfterAll -> afterAllTests()");
    }

    private static Stream<Arguments> addProviderData() {
        return Stream.of(
                Arguments.of(6, 2, 8),
                Arguments.of(-6, -2, -8),
                Arguments.of(6, -2, 4),
                Arguments.of(-6, 2, -4),
                Arguments.of(6, 0, 6)
        );
    }

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
        calculator = null;
    }

    @Test
    void calculatorNotNullTest() {
        assertNotNull(calculatorStatic, "Calculator debe ser not null");
        assertNotNull(calculator, "Calculator debe ser not null");
    }

    @Test
    void calculatorNullTest() {
        assertNull(calculatorNull);
    }

    @Test
    void addTest() {
        assertEquals(30, calculator.add(10, 20));
    }

    @Test
    void assertTypesTest() {
        assertTrue(1 == 1);

        assertNull(calculatorNull);
        assertNotNull(calculator);

        Calculator calculator1 = new Calculator();
        Calculator calculator2 = new Calculator();
        Calculator calculator3 = calculator1;

        assertSame(calculator1, calculator3);

        assertNotSame(calculator1, calculator2);
//        assertNotSame(calculator1, calculator3);

        assertEquals("kristian", "kristian");
//        assertEquals("kristi", "kristian", "Ha fallado nuestro metodo String");

        assertEquals(1, 1.4, 0.5);
//        assertEquals(1,1.6,0.5);
    }

    @Test
    void addValidInputValidExpectedTest() {
        assertEquals(11, calculator.add(7, 4));
    }

    @Test
    void subtractValidInputValidExpectedTest() {
        assertEquals(11, calculator.subtract(15, 4));
    }

    @Test
    void subtractValidInputValidNegativeExpectedTest() {
        assertEquals(-10, calculator.subtract(10, 20));
    }

    @Test
    void divideValidInputValidResultExpectedTest() {
        assertEquals(2, calculator.divide(10, 5));
    }

    @Test
    void divideInValidInputTest() {
        fail("Fallo detectado manualmente - No se puede dividir por cero");
        assertEquals(2, calculator.divide(10, 0));
    }

    @Test
    void divideInValidInputExpectedExceptionTest() {
        assertThrows(ArithmeticException.class, () -> calculator.divideByZero(2, 0), "No se puede dividir por cero");
    }

    @Disabled("Disabled until bug 23 resolved")
    @Test
    void divideInvalidInputTest() {
        assertEquals(2, calculator.divide(5, 0));
    }

    @Test
    @DisplayName("Método dividir -> Funciona")
    void divideValidInputValidResultExpectedNameTest() {
        assertEquals(2, calculator.divide(10, 5));
    }

    @Test
    void addAssertAllTest() {
        assertAll(
                () -> assertEquals(31, calculator.add(11, 20)),
                () -> assertEquals(20, calculator.add(10, 20)),
                () -> assertEquals(2, calculator.add(1, 1))
        );
    }

    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @MethodSource("addProviderData")
    void addParameterizedTest(int a, int b, int sum) {
        assertEquals(sum, calculator.add(a, b));
    }

    @Test
    void timeOutTest() {
        assertTimeout(Duration.ofMillis(500), () -> {
            calculator.longTaskOperation();
        });
    }

    @Nested
    class AddTest {
        @Test
        void addPositiveTest() {
            assertEquals(30, calculator.add(15, 15));
        }

        @Test
        void addNegativeTest() {
            assertEquals(-30, calculator.add(-15, -15));
        }

        @Test
        void addZeroTest() {
            assertEquals(0, calculator.add(0, 0));
        }
    }

}