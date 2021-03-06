package ContentPanes.CustomerViews;

import ContentPanes.EzItems.EzText;
import DatabaseObjects.Check;
import DatabaseObjects.CheckingAccount;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static ContentPanes.EzItems.TryParse.TryParseDouble;
import static Master.Main.database;

/**
 * Created by user on 11/28/2016.
 * DONE
 */
public class CustomerCheckWriterPane extends GridPane {
    public CustomerCheckWriterPane() {
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));
        EzText title1 = new EzText("Write a Check");
        title1.setFont(Font.font("Gabriola", FontWeight.NORMAL, 20));
        add(title1, 0, 0, 4, 1);

        EzText dateText = new EzText("Date: ");
        add(dateText, 0, 2);
        TextField dateField = new TextField();
        add(dateField, 1, 2);

        EzText orderOfText = new EzText("Order Of: ");
        add(orderOfText, 0, 3);
        TextField orderOfField = new TextField();
        add(orderOfField, 1, 3);

        EzText ammountText = new EzText("Amt: ");
        add(ammountText, 0, 4);
        TextField ammountField = new TextField();
        add(ammountField, 1, 4);

        EzText accountNumText = new EzText("Account Num: ");
        add(accountNumText, 0, 5);
        TextField accountNumField = new TextField();
        add(accountNumField, 1, 5);

        EzText checkNumText = new EzText("Check Num: ");
        add(checkNumText, 0, 6);
        TextField checkNumField = new TextField();
        add(checkNumField, 1, 6);

        EzText forText = new EzText("For: ");
        add(forText, 0, 7);
        TextField forField = new TextField();
        add(forField, 1, 7);

        final Text actionTarget = new Text();
        add(actionTarget, 1, 8);

        Button signButton = new Button("Sign and send Check");
        signButton.setFont(Font.font("Gabriola", FontWeight.NORMAL, 20));
        add(signButton, 0, 9, 4, 1);

        signButton.setOnAction(event -> {
            Boolean checker =false;
            String amount = ammountField.getText();
            String checknum = checkNumField.getText();
            for (CheckingAccount account : database.getCheckingAccounts()) {
                if (account.getAccountID().compareToIgnoreCase(accountNumField.getText()) == 0 && TryParseDouble(amount) && TryParseDouble(checknum)) {
                    database.checks.add(new Check(dateField.getText(), orderOfField.getText(), Double.parseDouble(amount), accountNumField.getText(), Integer.parseInt(checknum), forField.getText()));
                    actionTarget.setFill(Color.FIREBRICK);
                    actionTarget.setText("Check Sent");
                    checker=true;
                }
            }
            if(!checker){
                actionTarget.setFill(Color.FIREBRICK);
                actionTarget.setText("Invalid Checking Account");
            }
        });
    }
}
