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

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Gabe
 */
public class KMeans 
{
  
	private int K; // number of clusters
	private int total_values, total_points, max_iterations;
	private Vector<Cluster> clusters = new Vector<Cluster>();

	// return ID of nearest center (uses euclidean distance)
	int getIDNearestCenter(Point point)
	{
		double sum = 0.0, min_dist;
		int id_cluster_center = 0;

		for (int i = 0; i < total_values; i++)
		{
			sum += pow(clusters.get(0).getCentralValue(i) -
				point.getValue(i), 2.0);
		}

		min_dist = sqrt(sum);

		for (int i = 1; i < K; i++)
		{
			double dist;
			sum = 0.0;

			for (int j = 0; j < total_values; j++)
			{
				sum += pow(clusters.get(i).getCentralValue(j) -
					point.getValue(j), 2.0);
			}

			dist = sqrt(sum);

			if (dist < min_dist)
			{
				min_dist = dist;
				id_cluster_center = i;
			}
		}

		return id_cluster_center;
	}


	public KMeans(int K, int total_points, int total_values, int max_iterations)
	{
		this.K = K;
		this.total_points = total_points;
		this.total_values = total_values;
		this.max_iterations = max_iterations;
	}

	public void run(Vector<Point> points)
	{
            Random rand = new Random(System.currentTimeMillis());

            
		if (K > total_points)
			return;

		Vector<Integer> prohibited_indexes = new Vector<Integer>();

		// choose K distinct values for the centers of the clusters
		for (int i = 0; i < K; i++)
		{
			while (true)
			{
                            //May be a problem area??
				int index_point = rand.nextInt(total_points); //rand between 0 and total_points
				if (prohibited_indexes.indexOf(index_point) < 0)
				{
					prohibited_indexes.add(index_point);
					points.get(index_point).setCluster(i);
					Cluster cluster = new Cluster(i, points.get(index_point));
					clusters.add(cluster);
					break;
				}
			}
		}

		int iter = 1;

		while (true)
		{
			boolean done = true;

			// associates each point to the nearest center
			for (int i = 0; i < total_points; i++)
			{
				int id_old_cluster = points.get(i).getCluster();
				int id_nearest_center = getIDNearestCenter(points.get(i));

				if (id_old_cluster != id_nearest_center)
				{
					if (id_old_cluster != -1)
						clusters.get(id_old_cluster).removePoint(points.get(i).getID());

					points.get(i).setCluster(id_nearest_center);
					clusters.get(id_nearest_center).addPoint(points.get(i));
					done = false;
				}
			}

			// recalculating the center of each cluster
			for (int i = 0; i < K; i++)
			{
				for (int j = 0; j < total_values; j++)
				{
					int total_points_cluster = clusters.get(i).getTotalPoints();
					double sum = 0.0;

					if (total_points_cluster > 0)
					{
						for (int p = 0; p < total_points_cluster; p++)
							sum += clusters.get(i).getPoint(p).getValue(j);
						clusters.get(i).setCentralValue(j, sum / total_points_cluster);
					}
				}
			}

			if (done == true || iter >= max_iterations)
			{
                            System.out.print("Nothing moved in iteration " + iter + "\n\n");
                            break;
			}

			iter++;
		}

		// shows elements of clusters
		for (int i = 0; i < K; i++)
		{
			int total_points_cluster = clusters.get(i).getTotalPoints();

			System.out.println("Cluster " + clusters.get(i).getID() + 1);
			for (int j = 0; j < total_points_cluster; j++)
			{
				System.out.print("Point " + (clusters.get(i).getPoint(j).getID() + 1) + ": ");
				for (int p = 0; p < total_values; p++)
					System.out.print(clusters.get(i).getPoint(j).getValue(p) + " ");

				String point_name = clusters.get(i).getPoint(j).getName();

				if (point_name != "")
					System.out.print("- " + point_name);

				System.out.print("\n");
			}

			System.out.print("Centroid: ");

			for (int j = 0; j < total_values; j++)
                            System.out.printf("%.2f ", clusters.get(i).getCentralValue(j));
				

			System.out.print("\n\n");
		}
	}
}
