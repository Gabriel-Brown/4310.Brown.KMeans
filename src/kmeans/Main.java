/******************************************************
‘***  KMeans
‘***  Gabriel Brown
‘******************************************************
‘*** To Demonstrate kmeans clustering
‘***
‘******************************************************
‘*** 10/20/2017
‘******************************************************
‘*****************************************************/
package kmeans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Gabe
 */
public class Main {
    
    
     public static void main(String[] args) {
        //Random rand = new Random(System.currentTimeMillis());
        

	int total_points, total_values, K, max_iterations, has_name;

	total_points = 50;
	total_values = 4;
	K = 20;
	max_iterations = 50;
	

	Vector<Point> points = new Vector<Point>();

        System.out.println(System.getProperty("user.dir"));
	ReadData(points, "USArrests.csv");

	KMeans kmeans = new KMeans(K, total_points, total_values, max_iterations);
	kmeans.run(points);

	
	System.out.print("Run Complete...");
	
     }
     

static void ReadData(Vector<Point> points, String fileName)
{
	String point_name,line,data1,data2,data3,data4;
	
        try {
			File file = new File(fileName);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
                        
			for (int i = 0; (line = bufferedReader.readLine()) != null; i++) {
				Vector<Double> values = new Vector<Double>();
                                String[] data = line.split(",");
                                point_name = data[0];
				values.add(Double.parseDouble(data[1]));
				values.add(Double.parseDouble(data[2]));
				values.add(Double.parseDouble(data[3]));
				values.add(Double.parseDouble(data[4]));
				points.add(new Point(i, values, point_name));
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


	
}
}
