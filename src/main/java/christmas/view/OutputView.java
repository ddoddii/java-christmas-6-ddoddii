package christmas.view;

public class OutputView {

    public void displayEventMessage(int date){
        String formattedMessage = String.format(ViewMessage.EVENT.getMessage(), date);
        System.out.println(formattedMessage);
    }
    
}
