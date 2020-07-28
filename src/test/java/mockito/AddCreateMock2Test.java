package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class AddCreateMock2Test {

    @InjectMocks
    private Add add;

    @Mock
    private ValidNumber validNumber;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addTest() {
        add.add(3, 2);
        Mockito.verify(validNumber).check(3);
    }

}
