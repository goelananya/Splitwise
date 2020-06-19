package com.splitwise;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.splitwise.bo.Expense;
import com.splitwise.exception.UserNotFoundException;
import com.splitwise.service.UserService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileReader;
import java.io.IOException;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExpenseTest {

    private static ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    private static UserService userServiceMock;

    private static Expense expense;

    @BeforeClass
    public void init() throws IOException {
        FileReader reader = new FileReader(System.getProperty("user.dir") + "src/test/resources/TestExpense.json");
        expense = objectMapper.readValue(reader, Expense.class);
    }


    @Test(expected = UserNotFoundException.class)
    public void validateAddExpenseParticipantNotExist() throws UserNotFoundException {
        when(userServiceMock.doUsersExist(expense.getParticipantUsersList())).thenReturn(false);
    }

    @Test
    public void validateExpenseCreatorNotExist() throws UserNotFoundException {
        when(userServiceMock.doUsersExist(expense.getParticipantUsersList())).thenReturn(true);
    }


}