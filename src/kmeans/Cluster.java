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

import java.util.Vector;

/**
 *
 * @author Gabe
 */
public class Cluster 
{
    
	private int id_cluster;
	private Vector<Double> central_values = new Vector<Double>();
	private Vector<Point> points = new Vector<Point>();


	public Cluster(int id_cluster, Point point)
	{
		this.id_cluster = id_cluster;

		int total_values = point.getTotalValues();

		for (int i = 0; i < total_values; i++)
			central_values.add(point.getValue(i));

		points.add(point);
	}

	void addPoint(Point point)
	{
		points.add(point);
	}

	boolean removePoint(int id_point)
	{
		int total_points = points.size();

		for (int i = 0; i < total_points; i++)
		{
			if (points.get(i).getID() == id_point)
			{
                            // could cause problems
				points.remove(i);
				return true;
			}
		}
		return false;
	}

	double getCentralValue(int index)
	{
		return central_values.get(index);
	}

	void setCentralValue(int index, double value)
	{
		central_values.set(index, value);
	}

	Point getPoint(int index)
	{
		return points.get(index);
	}

	int getTotalPoints()
	{
		return points.size();
	}

	int getID()
	{
		return id_cluster;
	}
}
