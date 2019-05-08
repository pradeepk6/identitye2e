package utils;

import com.google.inject.Guice;
import com.mycompany.domain.Vehicle;
import com.mycompany.service.FileServiceImpl;
import com.mycompany.service.FileServiceModule;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class TestDataUtils {

    private static final Logger log = LoggerFactory.getLogger(TestDataUtils.class);
    static FileServiceImpl fileServiceImpl;

    /**
     * read files from configured directory and build a list of vehicles
     * @return
     */
    public static List<Vehicle> getTestData() {
        fileServiceImpl = Guice.createInjector(new FileServiceModule()).getInstance(FileServiceImpl.class);
        List<File> files = fileServiceImpl.getFiles("csv");
        List<Vehicle> vehicleList = new ArrayList<>();
        for (File file : files) {
            Iterable<CSVRecord> records = null;
            try{
                Reader in = new FileReader(file);
                records = CSVFormat.DEFAULT.withFirstRecordAsHeader().
                            withHeader( "RegNum", "Make", "Colour").parse(in);
            }catch(IOException e){
                log.error("");
            }
            for (CSVRecord record : records) {
                Vehicle vehicle = new Vehicle();
                vehicle.setRegNum(record.get("RegNum"));
                vehicle.setMake(record.get("Make"));
                vehicle.setColour(record.get("Colour"));
                vehicleList.add(vehicle);
            }
        }
        return vehicleList;
    }

    /**
     * insert test data into the DataTable of the cucumber feature file
     * @param featureFilePath
     * @param vehicleList
     */
    public static void insertTestDataInFeatureFile(String featureFilePath, List<Vehicle> vehicleList){
        StringBuffer dataStrBuf = new StringBuffer();
        for(Vehicle vehicle : vehicleList) {
            dataStrBuf.append("|").append(vehicle.getRegNum()).append("|").
                            append(vehicle.getMake()).append("|").
                            append(vehicle.getColour()).append("|").
                            append(System.getProperty("line.separator"));
        }
        try{
            String contentWithData =  getFeatureContentWithoutData(featureFilePath) +
                                        System.getProperty("line.separator") + dataStrBuf.toString();
            //log.debug("Feature file with appended data : \n" + contentWithData);
            FileUtils.writeStringToFile(new File(featureFilePath), contentWithData,StandardCharsets.UTF_8.name());
        }catch (IOException e) {
            log.error("Error while appending data to feature file: " + featureFilePath, e);
        }
    }

    /**
     * remove data from DataTable of the feature file
     * @param featureFilePath
     */
    public static void removeTestDataFromFeatureFile(String featureFilePath){
        try{
            String fileContentWithoutData = getFeatureContentWithoutData(featureFilePath);
            fileContentWithoutData +=  (System.getProperty("line.separator") + "||||");
            FileUtils.writeStringToFile(new File(featureFilePath), fileContentWithoutData,StandardCharsets.UTF_8.name());
        }catch (IOException e) {
            log.error("Error while appending data to feature file: " + featureFilePath, e);
        }
    }

    private static String getFeatureContentWithoutData(String featureFilePath) {
        String featureContentWithoutData = null;
        try{
            String fileString = FileUtils.readFileToString(new File(featureFilePath), StandardCharsets.UTF_8.name());
            int index = fileString.lastIndexOf("|regNum|make|colour|");
            featureContentWithoutData = fileString.substring(0,index+"|regNum|make|colour|".length());
        }catch (IOException e) {
            log.error("Error while appending data to feature file: " + featureFilePath, e);
        }
        //log.debug("Feature content without data : " + featureContentWithoutData);
        return featureContentWithoutData;
    }

    /**
     * fetches data and appends to the feature file DataTable
     */
    public static void testDataSetUp(){
        String featureFilePath = "src/acceptancetest/resources/features/VerifyVehicle.feature";
        log.info("Getting test data...");
        List<Vehicle> vehicleList = TestDataUtils.getTestData();
        log.info("Inserting test data into feature file : " + featureFilePath );
        TestDataUtils.insertTestDataInFeatureFile(featureFilePath, vehicleList);
        log.info(" *** Finished test data setup *** ");

    }

    /**
     * removes data from feature file DataTable
     */
    public static void testDataTearDown(){
        String featureFilePath = "src/acceptancetest/resources/features/VerifyVehicle.feature";
        log.info(" *** Tear down data i.e deleting data from feature file *** ");
        removeTestDataFromFeatureFile(featureFilePath);
    }

    public static void main(String... args){
        if(args[0].equalsIgnoreCase("setup")) {
            testDataSetUp();
        }
        else if(args[0].equalsIgnoreCase("teardown")) {
            testDataTearDown();
        }
    }

}
