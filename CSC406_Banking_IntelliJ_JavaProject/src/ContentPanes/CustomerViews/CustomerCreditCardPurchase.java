package ContentPanes.CustomerViews;

import ContentPanes.EzItems.EzText;
import DatabaseObjects.CreditCard;
import DatabaseObjects.PurchasesThisMonth;
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
public class CustomerCreditCardPurchase extends GridPane {
    public CreditCard card;
    public CustomerCreditCardPurchase(){
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(25, 25, 25, 25));
        EzText title1 = new EzText("Make A Credit Card Purchase");
        title1.setFont(Font.font("Gabriola", FontWeight.NORMAL, 20));
        add(title1, 0, 0, 4, 1);

        EzText cardText = new EzText("Card #: ");
        add(cardText,0,2);
        TextField cardField = new TextField();
        add(cardField, 1, 2);

        EzText titleText = new EzText("Purchase Title: ");
        add(titleText,0,3);
        TextField titleField = new TextField();
        add(titleField, 1, 3);

        EzText locText = new EzText("Purchase Location: ");
        add(locText,0,4);
        TextField locField = new TextField();
        add(locField, 1, 4);

        EzText amtText = new EzText("Purchase Amt: ");
        add(amtText,0,5);
        TextField amtField = new TextField();
        add(amtField, 1, 5);

        final Text actionTarget = new Text();
        add(actionTarget, 1, 6);

        Button signButton = new Button("Complete Purchase");
        signButton.setFont(Font.font("Gabriola", FontWeight.NORMAL, 20));
        add(signButton, 0, 7, 4, 1);
        signButton.setOnAction(event -> {
            String amount = amtField.getText();
            card=database.getcardByNum(cardField.getText());
            String s =card.getCreditCardID()+"1"+card.getPurchasesThisMonth().size()+1;
            if(TryParseDouble(amount)){
                if(card!=null) {
                    card.getPurchasesThisMonth().add(new PurchasesThisMonth(s, titleField.getText(), locField.getText(), Double.parseDouble(amount)));
                    actionTarget.setFill(Color.FIREBRICK);
                    actionTarget.setText("purchase made");
                }else{
                    actionTarget.setFill(Color.FIREBRICK);
                    actionTarget.setText("Card does not exist in records");
                }
            }else{
                actionTarget.setFill(Color.FIREBRICK);
                actionTarget.setText("Invalid Purchase amt");
            }
        });
    }
}
