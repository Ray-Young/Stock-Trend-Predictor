DTW, mapper and reducer are the source code of Hadoop and MapReduce. DTW is implemented in mapper. I used the cloudera environment for the Hadoop environment, so the dependencies are the lastest Hadoop version on Cloudera.

Tracker is another file using python to calculate the path to find out the start and end point of the target time series.

In one word, DTW, mapper and reducer is the program to find out which stock has the most similar trend, and tracker is the program to find out which range is the most similar time series.

The execution sequence should be: Run DataParser first, get the trained data from original data. Then use run the jar file in MapReduce. Here will present the stock with most similar time series. Finally run tracker to get the which range is most similar.