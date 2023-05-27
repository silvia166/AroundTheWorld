package junit;

import com.example.aroundtheworld.appcontroller.BookingResidenceController;
import com.example.aroundtheworld.bean.ResidenceRequestBean;
import com.example.aroundtheworld.exception.MessageException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class testBookingResidenceController {

    /* SILVIA PERELLI
       Il seguente test verifica che venga sollevata un'eccezione quando
       viene inviata una richiesta con una data di arrivo precedente alla data corrente */

    @Test
    void testSendRequest(){
        int validDate = 0;
        ResidenceRequestBean residenceRequestBean = new ResidenceRequestBean("Rome","2023-05-26","2023-06-26","single",3,0);
        BookingResidenceController bookingResidenceController = new BookingResidenceController();
        try {
            bookingResidenceController.sendRequest(residenceRequestBean);
            validDate = 1;
        } catch (MessageException e){
            validDate = 2;
        } catch (Exception ignored){
        }

        assertEquals(2,validDate); //il test ha successo perchè la data di arrivo è precedente al giorno corrente e viene sollevata l'eccezione
    }
}
