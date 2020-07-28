package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class AddTest {

    @InjectMocks
    private Add add;

    @Mock
    private ValidNumber validNumber;

    @Mock
    private Print print;

    @Captor
    private ArgumentCaptor<Integer> captor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addTest() {
        when(validNumber.check(3)).thenReturn(false);
        boolean checkNumber = validNumber.check(3);
        assertFalse(checkNumber);
    }

    @Test
    void addMockExceptionTest() {
        when(validNumber.checkZero(0)).thenThrow(new ArithmeticException("No podemos aceptar cero"));
        Exception exception = null;
        try {
            validNumber.checkZero(0);
        } catch (ArithmeticException e) {
            exception = e;
        }

        assertNotNull(exception);
    }

    @Test
    void addRealMethodTest() {
        when(validNumber.check(3)).thenCallRealMethod();
        assertTrue(validNumber.check(3));

        when(validNumber.check("3")).thenCallRealMethod();
        assertFalse(validNumber.check("3"));
    }

    @Test
    void addDoubleToIntThenAnswerTest() {
        Answer<Integer> answer = invocation -> 7;

        when(validNumber.doubleTotInt(7.7)).thenAnswer(answer);
        assertEquals(14, add.addInt(7.7));
    }

    //Arrange
    //Act
    //Assert

    //Given
    //When
    //Then

    @Test
    void patterTest() {
        //Arrange
        when(validNumber.check(4)).thenReturn(true);
        when(validNumber.check(5)).thenReturn(true);
        //Act
        int result = add.add(4, 5);
        //Assert
        assertEquals(9, result);
    }

    @Test
    void patterBDDTest() {
        //Given
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        //When
        int result = add.add(4, 5);
        //Then
        assertEquals(9, result);
    }

    @Test
    void argumentMatcherTest() {
        given(validNumber.check(anyInt())).willReturn(true);
        int result = add.add(4, 5);
        assertEquals(9, result);
    }

    @Test
    void addPrintTest() {
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        add.addPrint(4, 5);
        verify(validNumber).check(4);
//        verify(validNumber, times(2)).check(4);
        verify(validNumber, never()).check(99);
        verify(validNumber, atLeast(1)).check(4);
        verify(validNumber, atMost(1)).check(4);

        verify(print).showMessage(9);
        verify(print, never()).showError();
    }

    @Test
    void captorTest() {
        //Given
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        //when
        add.addPrint(4, 5);
        //Then
        verify(print).showMessage(captor.capture());
        assertEquals(captor.getValue().intValue(), 9);
    }

    @Spy
    List<String> spyList = new ArrayList<>();

    @Mock
    List<String> mockList = new ArrayList<>();

    @Test
    void spyTest() {
        spyList.add("1");
        spyList.add("2");
        verify(spyList).add("1");
        verify(spyList).add("2");
        assertEquals(2, spyList.size());

    }
    @Test
    void mockTest() {
        mockList.add("1");
        mockList.add("2");
        verify(mockList).add("1");
        verify(mockList).add("2");
        //Es importante declarar un mock cuando sea necesario validar alguna funcionalidad.
        given(mockList.size()).willReturn(2);
        assertEquals(2, spyList.size());

    }
}