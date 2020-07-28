package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

class WebServiceTest {

    private WebService webService;

    @Mock
    private Callback callback;

    @BeforeEach
    void setup() {
        webService = new WebService();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void checkLoginTest() {
        assertTrue(webService.checkLogin("kristian", "lopez"));
    }

    @Test
    void checkoLoginErrorTest() {
        assertFalse(webService.checkLogin("kristian", "incorrect"));
    }

    @Test
    void loginTest() {
        webService.login("kristian", "lopez", callback);
        verify(callback).onSuccess("Correct user");
    }

    @Test
    void loginErrorTest() {
        webService.login("kristian", "123", callback);
        verify(callback).onFail("Error");
    }

}
