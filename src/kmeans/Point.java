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
public class Point 
{
	private int id_point;
	private int id_cluster;
	private Vector<Double> values = new Vector<Double>();
	private int total_values;
	private String name;
        
        
	public Point(int id_point, Vector<Double> values)
	{
		this(id_point, values, "");
	}

	public Point(int id_point, Vector<Double> values, String name)
	{
		this.id_point = id_point;
		total_values = values.size();
                
                this.values = values;

		

		this.name = name;
		id_cluster = -1;
	}

	public final int getID()
	{
		return id_point;
	}

	public final void setCluster(int id_cluster)
	{
		this.id_cluster = id_cluster;
	}

	public final int getCluster()
	{
		return id_cluster;
	}

	public final double getValue(int index)
	{
		return values.get(index);
	}

	public final int getTotalValues()
	{
		return total_values;
	}

	public final void addValue(double value)
	{
		values.add(value);
	}

	public final String getName()
	{
		return name;
	}
}

