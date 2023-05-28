package junit;

import com.example.aroundtheworld.appcontroller.QuoteController;
import com.example.aroundtheworld.bean.QuoteBean;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestQuoteController {

    /* SILVIA PERELLI
       Il seguente test verifica che venga restituito il preventivo aspettato
       tramite l'uso del pattern decorator, secondo specifiche caratteristiche scelte */

    @Test
    void testCalculateQuote(){
        QuoteBean quotebean = new QuoteBean("London", 12,"residence","single");
        quotebean.setActivity1(1);
        quotebean.setActivity2(1);
        quotebean.setActivity3(0);
        quotebean.setSport(1);

        QuoteController quoteController = new QuoteController();
        int quote = quoteController.calculateQuote(quotebean);

        assertEquals(7280, quote); //il test ha successo in quanto il prezzo calcolato corrisponde a quello previsto

    }
}
