package junit;

import com.example.aroundtheworld.dao.FamilyRequestDAO;
import com.example.aroundtheworld.model.FamilyPreferences;
import com.example.aroundtheworld.model.FamilyRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestFamilyRequestDAO {

      /* SARA MALASPINA
         Il seguente test verifica che dopo l'aggiunta di una nuova richiesta per una famiglia, chiamando un
         metodo del DAO che restituisce il numero di richieste per quella famiglia, questo venga incrementato di 1 */

    @Test
    void testNewRequest(){
        int count = FamilyRequestDAO.getNumberOfRequests(1);
        FamilyRequest familyRequest = new FamilyRequest("Rome","2024-01-10","2024-01-20",0,1,5,0);
        familyRequest.setIdFamily(1);
        familyRequest.setCompatibility(80);

        FamilyPreferences familyPreferences = new FamilyPreferences();
        familyPreferences.setHouse("shared");
        familyPreferences.setFood(0,0);
        familyPreferences.setHobbies(1,0,0,0,1,1,1);
        familyRequest.setFamilyPreferences(familyPreferences);
        try {
            FamilyRequestDAO.newRequest(familyRequest);
        } catch (Exception ignore) {
        }

        assertEquals(count+1, FamilyRequestDAO.getNumberOfRequests(1)); //il test ha successo in quanto la richiesta viene aggiunta correttamente
    }
}
