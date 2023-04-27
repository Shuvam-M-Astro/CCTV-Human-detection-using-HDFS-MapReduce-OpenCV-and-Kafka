public static void main(String[] args) throws IOException {
    Path inputPath = new Path(args[0]);
    Path outputPath = new Path(args[1]);
    Configuration conf = new Configuration();

    Job job = Job.getInstance(conf);
    job.setJarByClass(DenoiseImage.class);
    job.setMapperClass(DenoiseImageMapper.class);
    job.setReducerClass(DenoiseImageReducer.class);
    job.setInputFormatClass(ImageInputFormat.class);
    job.setOutputFormatClass(ImageOutputFormat.class);
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(ImageSplit.class);
    job.setOutputKeyClass(NullWritable.class);
    job.setOutputValueClass(Mat.class);
    FileInputFormat.setInputPaths(job, inputPath);
    FileOutputFormat.setOutputPath(job, outputPath);
    System.exit(job.waitForCompletion(true) ? 0 : 1);
}

public static class DenoiseImageMapper extends Mapper<Text, ImageSplit, Text, ImageSplit> {
    @Override
    protected void map(Text key, ImageSplit value, Context context) throws IOException, InterruptedException {
        context.write(key, value);
    }
}

public static class DenoiseImageReducer extends Reducer<Text, ImageSplit, NullWritable, Mat> {
    @Override
    protected void reduce(Text key, Iterable<ImageSplit> values, Context context) throws IOException, InterruptedException {
        for (ImageSplit value : values) {
            // denoise the image block here
            // e.g., using OpenCV's fastNlMeansDenoising method
            Mat input = value.getMat();
            Mat output = new Mat();
            Photo.fastNlMeansDenoising(input, output);
            context.write(NullWritable.get(), output);
        }
    }
}
