package ru.netology.web.test;

import com.codeborne.selenide.Selenide;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;

import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DataHelper.*;

public class MoneyTransferTest {



    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        var info = getAuthInfo();
        var verificationCode = DataHelper.getVerificationCode(info);

        var LoginPage = open("http://localhost:9999", LoginPage.class);
        var verificationPage = LoginPage.validLogin(info);
        var dashBoardPage = verificationPage.validVerify(verificationCode);

        var firstCard = getFirstCardInfo();
        var secondCard = getSecondCard();

        int initialFirstBalance = dashBoardPage.getCardBalance(firstCard);
        int initialSecondBalance = dashBoardPage.getCardBalance(secondCard);

        int transferAmount = getRandomTransferAmount(initialFirstBalance / 2);

        var transferPage = dashBoardPage.selectCard(firstCard);
        dashBoardPage = transferPage.makeTransfer(transferAmount, secondCard);

        int expectedFirstCardBalance = initialFirstBalance + transferAmount;
        int expectedSecondCardBalance = initialSecondBalance - transferAmount;

        assertEquals(expectedFirstCardBalance, dashBoardPage.getCardBalance(firstCard));
        assertEquals(expectedSecondCardBalance, dashBoardPage.getCardBalance(secondCard));
    }
}