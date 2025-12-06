package utilities;
import java.io.IOException;

import org.testng.annotations.DataProvider;
public class DataProviders {
	@DataProvider(name = "LoginData")
	public Object[][] getData() throws IOException {
		String path = System.getProperty("user.dir") +"\\testData\\opencartdata.xlsx";
		ExcelUtility xl = new ExcelUtility(path);
		int totalRows = xl.getRowCount("Sheet1");
		int totalCols = xl.getCellCount("Sheet1", 0);
		Object[][] loginData = new Object[totalRows][totalCols];
		for (int i = 1; i <= totalRows; i++) {
			for (int j = 0; j < totalCols; j++) {
				loginData[i - 1][j] = xl.getCellData("Sheet1", i, j);
			}
		}
		return loginData;
	}
}
