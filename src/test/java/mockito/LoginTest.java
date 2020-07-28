package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class LoginTest {

    @InjectMocks
    private Login login;

    @Mock
    private WebService webService;

    @Captor
    private ArgumentCaptor<Callback> callbackArgumentCaptor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doLoginTest() {

        doAnswer(invocationOnMock -> {

            String user = (String) invocationOnMock.getArguments()[0];
            assertEquals("kristian",user);
            String password = (String) invocationOnMock.getArguments()[1];
            assertEquals("lopez",password);
            Callback callback = (Callback) invocationOnMock.getArguments()[2];
            callback.onSuccess("OK");
            return null;
        }).when(webService).login(anyString(), anyString(), any(Callback.class));

        login.doLogin();
        verify(webService, times(1)).login(anyString(), anyString(), any(Callback.class));
        assertTrue(login.isLogin);
    }

    @Test
    public void doLoginErrorTest() {

        doAnswer(invocationOnMock -> {

            String user = (String) invocationOnMock.getArguments()[0];
            assertEquals("kristian",user);
            String password = (String) invocationOnMock.getArguments()[1];
            assertEquals("lopez",password);
            Callback callback = (Callback) invocationOnMock.getArguments()[2];
            callback.onFail("Error");
            return null;
        }).when(webService).login(anyString(), anyString(), any(Callback.class));

        login.doLogin();
        verify(webService, times(1)).login(anyString(), anyString(), any(Callback.class));
        assertFalse(login.isLogin);
    }

    @Test
    void doLoginCaptorTest() {
        login.doLogin();
        verify(webService, times(1)).login(anyString(),anyString(),callbackArgumentCaptor.capture());
        assertFalse(login.isLogin);

        Callback callback = callbackArgumentCaptor.getValue();
        callback.onSuccess("OK");
        assertTrue(login.isLogin);

        callback.onFail("Error");
        assertFalse(login.isLogin);

    }

}