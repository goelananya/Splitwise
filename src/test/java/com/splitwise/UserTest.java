package com.splitwise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.splitwise.bo.BalanceBook;
import com.splitwise.bo.User;
import com.splitwise.builder.UserBuilder;
import com.splitwise.dao.UserDao;
import com.splitwise.exception.UserExistsException;
import com.splitwise.service.BalanceBookService;
import com.splitwise.service.UserService;
import com.splitwise.util.TestJson;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    private static User user;
    private static BalanceBook balanceBook;
    private static ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    UserDao userDaoMock;
    @Mock
    BalanceBookService balanceBookService;
    @InjectMocks
    UserService userService;

    @BeforeClass
    public static void init() throws JsonProcessingException {
        user = new UserBuilder().setUserName("John").setPhoneNumber("9999988888").setEmail("john@gmail.com").build();
        balanceBook = objectMapper.readValue(TestJson.BALANCE_BOOK_JSON, BalanceBook.class);
    }


    @Test(expected = UserExistsException.class)
    public void validateAddingDuplicateUser() throws UserExistsException {
        when(userDaoMock.findByUsername("John")).thenReturn(user);
        userService.addUser(new UserBuilder().setUserName("John").setPhoneNumber("7777766666").setEmail("otherjohn@gmail.com"));
    }

    @Test
    public void validateAddingValidUser() throws UserExistsException {
        when(userDaoMock.findByUsername("Arya")).thenReturn(null);
        when(balanceBookService.addBalanceBook()).thenReturn(balanceBook);
        userService.addUser(new UserBuilder().setUserName("Arya").setPhoneNumber("666666666").setEmail("arya@gmail.com"));
    }
}