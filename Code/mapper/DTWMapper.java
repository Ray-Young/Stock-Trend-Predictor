package lab.mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DTWMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {

	// public double[] sourceArray = {28.621, 27.875, 29.673, 28.397, 27.398,
	// 27.254,
	// 28.054, 27.245, 26.526, 26.022, 23.891, 24.53, 25.707, 26.975, 25.897,
	// 27.299, 27.138, 26.039, 26.617,
	// 26.455, 26.203 };
	public double[] sourceArray = { 14.793, 14.813, 14.96, 14.911, 14.695, 14.557, 15.383, 14.842, 14.881, 14.646,
			14.616, 14.754, 14.793, 14.823, 14.901, 14.842, 14.911, 14.744, 14.754, 14.852, 14.685, 14.911, 14.734,
			14.646, 14.695, 14.626, 14.615, 14.724, 14.646, 14.41, 14.44, 14.528, 14.714, 14.744, 14.665, 14.685,
			14.449, 14.164, 14.184, 14.164, 14.066, 14.007, 13.958, 14.095, 14.252, 14.38, 14.4, 14.302, 14.223, 14.105,
			13.938, 13.938, 14.184, 14.272, 14.223, 14.144, 13.977, 13.702, 13.849, 13.997, 13.8, 13.613, 13.299,
			10.586, 12.739, 12.346, 12.719, 12.925, 12.562, 12.945, 12.817, 12.296, 11.874, 12.149, 12.65, 12.739,
			12.69, 12.828, 12.827, 12.66, 12.719, 13.004, 12.896, 12.916, 13.211 };

	@Override
	public void map(LongWritable key, Text record, Context context) throws IOException, InterruptedException {
		String line = record.toString();
		StringTokenizer s = new StringTokenizer(line, ",");
		String title = s.nextToken();
		List<Double> targetList = new ArrayList<Double>();

		while (s.hasMoreTokens()) {
			targetList.add(Double.parseDouble(s.nextToken()));
		}

		double[] targetArray = new double[targetList.size()];
		for (int i = 0; i < targetList.size(); i++) {
			targetArray[i] = targetList.get(i);
		}

		double min_dis = getMinDisByDTW(sourceArray, targetArray);
		context.write(new Text(title), new DoubleWritable(min_dis));
	}

	public double getMinDisByDTW(double[] sourceArray, double[] targetArray) {
		int s = sourceArray.length;
		int t = targetArray.length;
		double[][] D0 = new double[s + 1][t + 1];

		for (int i = 1; i < D0.length; i++) {
			D0[i][0] = Double.MAX_VALUE;
		}

		for (int i = 1; i < D0[0].length; i++) {
			D0[0][i] = Double.MAX_VALUE;
		}

		for (int i = 0; i < s; i++) {
			for (int j = 0; j < t; j++) {
				D0[i + 1][j + 1] = dist(sourceArray[i], targetArray[j]);
			}
		}

		for (int i = 0; i < s; i++) {
			for (int j = 0; j < t; j++) {
				D0[i + 1][j + 1] += Math.min(D0[i][j], Math.min(D0[i + 1][j], D0[i][j + 1]));
			}
		}

		return D0[s][t] / (s + t);
	}

	public double dist(double x, double y) {
		return Math.sqrt((x - y) * (x - y));
	}
}
