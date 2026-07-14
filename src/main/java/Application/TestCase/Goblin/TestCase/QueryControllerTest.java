package Application.TestCase.Goblin.TestCase;


import Data.ApisLayer.Goblin.QueryController;
import Data.Ro.Goblin.QueryControllerExportCollectionCasesRo;
import Service.Core.BaseTestCase;
import org.testng.annotations.Test;


public class QueryControllerTest extends BaseTestCase {

    QueryController queryController = new QueryController();


    @Test(enabled = false)
    public void exportCollectionCases() {
        queryController.convert2RoAndRequest("", QueryControllerExportCollectionCasesRo.class);
    }

    @Test
    public void getFileQueryCollectionCase() {
        queryController.getFileQueryCollectionCase();
    }

}



