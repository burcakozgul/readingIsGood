package com.example.readingisgood.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import java.util.stream.Stream;
import com.example.readingisgood.repository.OrderRepository;
import com.example.readingisgood.repository.UserRepository;
import com.example.readingisgood.security.JwtUtils;
import com.example.readingisgood.service.data.StatisticsServiceTestData;
import com.example.readingisgood.service.data.UserServiceTestData;
import com.example.readingisgood.types.responses.MonthlyOrderStatisticsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StatisticsServiceTest {

    @InjectMocks
    StatisticsService statisticsService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    JwtUtils jwtUtils;

    private static Stream<Arguments> argument_getMonthlyOrderStaticsByUserId() {
        return Stream.of(Arguments.of(1),
            Arguments.of(2),
            Arguments.of(3),
            Arguments.of(4),
            Arguments.of(5),
            Arguments.of(6),
            Arguments.of(7),
            Arguments.of(8),
            Arguments.of(9),
            Arguments.of(10),
            Arguments.of(11),
            Arguments.of(12));
    }

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest(name = "#{index}- arguments={arguments}")
    @MethodSource("argument_getMonthlyOrderStaticsByUserId")
    @DisplayName("Test getMonthlyOrderStaticsByUserId.")
    void getMonthlyOrderStaticsByUserId(int month) {
        double totalAmount = 500;
        doReturn(UserServiceTestData.get_User2()).when(userRepository).findUserByMail(any());
        doReturn(UserServiceTestData.get_User()).when(userRepository).findById(any());
        doReturn(StatisticsServiceTestData.get_OrderList(month)).when(orderRepository).findByUserIdAndDateBetween(any(), any(), any());

        MonthlyOrderStatisticsResponse response = statisticsService.getMonthlyOrderStaticsByUserId(1L, month, "efesf323e323r");
        assertEquals(totalAmount, response.getTotalAmount());
    }
}
