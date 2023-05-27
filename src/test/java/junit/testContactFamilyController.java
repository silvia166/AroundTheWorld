package junit;

import com.example.aroundtheworld.appcontroller.ContactFamilyController;
import com.example.aroundtheworld.bean.FamilyRequestBean;
import com.example.aroundtheworld.dao.FamilyDAO;
import com.example.aroundtheworld.model.Family;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class testContactFamilyController {
    /* il seguente test verifica che venga calcolata la giusta compatibilità tra
      la richiesta per una famiglia e una determinata famiglia */

    @Test
    void testCalculateCompatibility() {
        float compatibility = 0;
        FamilyRequestBean familyRequestBean = new FamilyRequestBean("Rome","2024-01-10","2024-01-20",0,1,5);
        familyRequestBean.setHobbies(1,1,0,0,1,0,1);
        familyRequestBean.setHouse("single");
        familyRequestBean.setFood(1,0);
        try {
            Family family = FamilyDAO.retrieveFamily("cesaroni@gmail.com");
            ContactFamilyController contactFamilyController = new ContactFamilyController();
            compatibility = contactFamilyController.calculateCompatibility(familyRequestBean,family);
        } catch (Exception ignore) {
        }

        assertEquals(50.0, compatibility);
    }
}
