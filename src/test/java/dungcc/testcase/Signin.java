package dungcc.testcase;

import dungcc.base.ValidateHelper;
import dungcc.pages.SigninPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Signin {
    private WebDriver driver;
    private ValidateHelper validateHelper;
    private By loginBtn= By.id("username");
    private By submitBtn= By.id("submit");
    private SigninPage signinPage;
    @Test
    public void abc() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        validateHelper = new ValidateHelper(driver);
        signinPage = new SigninPage(driver);
        driver.get("http://visionai.yottateck.com/sign-in?redirectURL=%2Fanalysis-customer%2Fdashboard");
        signinPage.singIn("vinfast_admin01","vbdi@123");
//        validateHelper.clickElement(loginBtn);
    }
    @Test
    public void read()throws Exception  {
        File src = new File("D:\\test\\testcase.xlsx");
        FileInputStream file = new FileInputStream(src);

        XSSFWorkbook xsf = new XSSFWorkbook(file);
        XSSFSheet sheet=xsf.getSheetAt(0);
        System.out.println(sheet.getRow(0).getCell(0));
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
//        String entry1 = sheet.getRow(1).getCell(1).getStringCellValue();
//        Row row1 = sheet.getRow(1);
//        Cell cell1 = row1.getCell(1);
//        Row row2 = sheet.getRow(1);
//        Cell cell2 = row2.getCell(1);
//        Row row3 = sheet.getRow(1);
//        Cell cell3 = row3.getCell(1);
        System.out.println(sheet.getRow(0).getCell(0));
        System.out.println(sheet.getRow(0).getCell(1));
        System.out.println(sheet.getRow(1).getCell(2));
        System.out.println(sheet.getRow(1).getCell(1));
String cellval = cell.getStringCellValue();
System.out.println(cellval);
    }
    @Test
    public void write()throws Exception  {
        File src = new File("D:\\test\\testcase.xlsx");
        String path = "D://DemoFile.xlsx";
        FileInputStream fs = new FileInputStream(src);
        Workbook wb = new XSSFWorkbook(fs);
        Sheet sheet1 = wb.getSheetAt(0);
        int lastRow = sheet1.getLastRowNum();
        System.out.println(lastRow);
        for(int i=0; i<=lastRow; i++){
            Row row = sheet1.getRow(i);
            Cell cell = row.createCell(6);

            cell.setCellValue("WriteintoExcel");

        }

        FileOutputStream fos = new FileOutputStream(src);
        wb.write(fos);
        fos.close();
    }
    @Test
    public void write1()throws Exception  {
        File src = new File("D:\\test\\Book2.xlsx");
        FileInputStream fs = new FileInputStream(src);
        Workbook wb = new XSSFWorkbook(fs);
        Sheet sheet1 = wb.getSheetAt(0);
        int lastRow = sheet1.getLastRowNum();
        for(int i=0; i<=lastRow; i++){
            Row row = sheet1.getRow(i);
            Cell cell = row.createCell(0);

            cell.setCellValue("WriteintoExcel");

        }
        FileOutputStream fos = new FileOutputStream(src);
        wb.write(fos);
        fos.close();
    }
    @Test
    public void abcd() throws Exception{
        WebDriverManager.chromedriver().setup();

        //Create an object of File class to open xls file
        File file =    new File("D:\\test\\Book2.xlsx");

        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);

        //creating workbook instance that refers to .xls file
        XSSFWorkbook wb=new XSSFWorkbook(inputStream);

        //creating a Sheet object
        XSSFSheet sheet=wb.getSheet("STUDENT_DATA");

        //get all rows in the sheet
        int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum();

        //Creating an object of ChromeDriver
        WebDriver driver = new ChromeDriver();

        //Navigate to the URL
        driver.get("https://demoqa.com/automation-practice-form");


        //Identify the WebElements for the student registration form
        WebElement firstName=driver.findElement(By.id("firstName"));
        WebElement lastName=driver.findElement(By.id("lastName"));
        WebElement email=driver.findElement(By.id("userEmail"));
        WebElement genderMale= driver.findElement(By.id("gender-radio-1"));
        WebElement mobile=driver.findElement(By.id("userNumber"));
        WebElement address=driver.findElement(By.id("currentAddress"));
        WebElement submitBtn=driver.findElement(By.id("submit"));



        //iterate over all the rows in Excel and put data in the form.
        for(int i=1;i<=rowCount;i++) {
            //Enter the values read from Excel in firstname,lastname,mobile,email,address
            firstName.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
            lastName.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
            email.sendKeys(sheet.getRow(i).getCell(2).getStringCellValue());
            System.out.println(sheet.getRow(i).getCell(4).getNumericCellValue());
            mobile.sendKeys("0348055428");
            address.sendKeys(sheet.getRow(i).getCell(5).getStringCellValue());

            //Click on the gender radio button using javascript
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", genderMale);

            //Click on submit button
            submitBtn.click();
//            driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();
//            new WebDriverWait(getWebDriver(), 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@formcontrolname='reportingDealPermission' and @ng-reflect-name='reportingDealPermission']"))).click();

            //Verify the confirmation message
            WebElement confirmationMessage = driver.findElement(By.xpath("//div[text()='Thanks for submitting the form']"));

            //create a new cell in the row at index 6
            XSSFCell cell = sheet.getRow(i).createCell(6);

            //check if confirmation message is displayed
            if (confirmationMessage.isDisplayed()) {
                // if the message is displayed , write PASS in the excel sheet
                cell.setCellValue("PASS");

            } else {
                //if the message is not displayed , write FAIL in the excel sheet
                cell.setCellValue("FAIL");
            }

            // Write the data back in the Excel file
            FileOutputStream outputStream = new FileOutputStream("D:\\test\\Book2.xlsx");
            wb.write(outputStream);

            //close the confirmation popup
            WebElement closebtn = driver.findElement(By.id("closeLargeModal"));
            closebtn.click();

            //wait for page to come back to registration page after close button is clicked
            driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
        }

        //Close the workbook
        wb.close();

        //Quit the driver
//        driver.quit();
    }
}
