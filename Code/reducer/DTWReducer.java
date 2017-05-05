package lab.reducer;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DTWReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
	double min = Double.MAX_VALUE;

	@Override
	public void reduce(Text key, Iterable<DoubleWritable> records, Context context)
			throws IOException, InterruptedException {

		for (DoubleWritable record : records) {
			if (record.get() < min) {
				min = record.get();
				context.write(key, new DoubleWritable(min));
			}
		}

	}
}
