import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import svy21.LatLonCoordinate;
import svy21.SVY21;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransformToLatLon {
    public static void main(String... args) {
        if (args.length != 1 || !args[0].endsWith(".csv")) {
            System.out.println("Invalid path to csv file");
            System.exit(0);
        }
        else {
            getData(args[0]);
        }
    }
    private static void getData(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr);
             FileOutputStream fos = new FileOutputStream(getTransformedFileName(fileName));
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             CSVWriter writer = new CSVWriter(osw)) {

            String[] headerLine = reader.readNext();
            if (headerLine != null) {
                List<String> headerList = new ArrayList<>(Arrays.asList(headerLine));
                headerList.add(4, "latitude");
                headerList.add(5, "longitude");
                headerLine = headerList.toArray(new String[0]);
                //writer.writeNext(headerLine);

                String[] dataLine;
                while ((dataLine = reader.readNext()) != null) {
                    List<String> dataList = new ArrayList<>(Arrays.asList(dataLine));
                    LatLonCoordinate latLonCoordinate = SVY21.computeLatLon(Double.valueOf(dataLine[3]), Double.valueOf(dataLine[2]));
                    dataList.add(4, String.valueOf(latLonCoordinate.getLatitude()));
                    dataList.add(5, String.valueOf(latLonCoordinate.getLongitude()));

                    dataLine = dataList.toArray(new String[0]);
                    writer.writeNext(dataLine, false);
                }
            }
            else
            {
                System.out.println("No data in file");
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getTransformedFileName(String fileName)
    {
        return new File(fileName).getParent().concat(File.separator).concat("transformed-".concat(new File(fileName).getName()));
    }
}
