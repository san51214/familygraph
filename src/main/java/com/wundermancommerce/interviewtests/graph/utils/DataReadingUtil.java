package com.wundermancommerce.interviewtests.graph.utils;

import com.wundermancommerce.interviewtests.graph.model.Person;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataReadingUtil {




    public static  Iterable<CSVRecord> readData(File file) {

        List<Person> persons = new ArrayList<>();

        String dataReadingErrorMessage = "";
        try {
            Reader in = null;
            in = new FileReader(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
            Iterable<CSVRecord> records = null;
//        try {
            records = CSVFormat.EXCEL.parse(in);

            return records;

        } catch (FileNotFoundException e) {
            dataReadingErrorMessage = e.getMessage();
        } catch (IOException e) {
            dataReadingErrorMessage = e.getMessage();
        }
        throw new RuntimeException(dataReadingErrorMessage);
    }

}
