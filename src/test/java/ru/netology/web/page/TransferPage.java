package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amountField = $("[data-test-id='amount'] input");
    private SelenideElement formField = $("[data-test-id='from'] input");
    private SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private SelenideElement cancelButton = $("[data-test-id='action-cancel']");

    public DashBoardPage makeTransfer(int amount, DataHelper.CardInfo formCard) {
        amountField.setValue(String.valueOf(amount));
        formField.setValue(formCard.getNumber());
        transferButton.click();
        return new DashBoardPage();
    }

    public DashBoardPage cancelTransfer() {
        cancelButton.click();
        return new DashBoardPage();
    }
}
