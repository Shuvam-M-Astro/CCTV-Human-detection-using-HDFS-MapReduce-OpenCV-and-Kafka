# CCTV-Human-detection-using-HDFS-MapReduce-OpenCV-and-Kafka

The input for this process can be any CCTV video converted into frames. 
1) The frames have to be denoised with 'join.java' and 'split.java', which use JAVACV for denoising. 
2) The frame can then be used for human detection using 'haarcascade_fullbody.xml' in Python.
3) This can either be store in HDFS or streamed via Kafka to preferred IOT device like alarm / lights.
