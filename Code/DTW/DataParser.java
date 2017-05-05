package lab.DTW;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DataParser {
	public static void main(String[] args) throws IOException {
		List<File> files = new ArrayList<File>();
		listf("/Users/lei/data", files);

		PrintWriter writer = new PrintWriter("new_file.txt", "UTF-8");
		for (File f : files) {
			boolean first = true;
			BufferedReader br = new BufferedReader(new FileReader(f.getAbsolutePath()));
			String line = br.readLine();
			StringBuilder sb = new StringBuilder();
			sb.append(f.getName().split("\\.")[0] + ",");
			System.out.println("parsing: " + f.getName());
			while (line != null) {
				if (!first) {
					String tmp = line.split(",")[1] + ",";
					sb.append(tmp);
				}
				first = false;
				line = br.readLine();
			}
			writer.write(sb.toString() + "\n");
			br.close();
		}
		writer.close();
	}

	public static void listf(String directoryName, List<File> files) {
		File directory = new File(directoryName);

		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile() && !file.getName().contains("DS_Store")) {
				files.add(file);
			} else if (file.isDirectory()) {
				listf(file.getAbsolutePath(), files);
			}
		}
	}
}
