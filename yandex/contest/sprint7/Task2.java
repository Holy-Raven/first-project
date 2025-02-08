package yandex.contest.sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task2 {

    private static Integer n;

    private static List<Job> jobLine;

    private static Set<Job> result;

    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            StringTokenizer line1 = new StringTokenizer(reader.readLine());
            n = Integer.parseInt(line1.nextToken());

            jobLine = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringTokenizer line = new StringTokenizer(reader.readLine());
                jobLine.add(new Job(Double.parseDouble(line.nextToken()), Double.parseDouble(line.nextToken())));
            }
        }

        result = calculate();

        System.out.println(result.size());
        result.forEach(job -> {
            String a = String.format("%s %s", job.getStart(), job.getEnd());
            System.out.println(a.replaceAll("\\.0",""));
        });
    }

    static class Job implements Comparable<Job> {
        private final double start;

        public double getEnd() {
            return end;
        }

        public double getStart() {
            return start;
        }

        private final double end;

        public Job(double start, double end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Job o) {
            int cmp = Double.compare(this.end, o.end);
            if (cmp == 0) {
                return Double.compare(this.start, o.start);
            }
            return cmp;
        }
    }

    private static Set<Job> calculate() {
        Set<Job> result = new LinkedHashSet<>();

        Collections.sort(jobLine);

        Job lastJob = jobLine.get(0);
        result.add(lastJob);

        for (int i = 1; i < n; i++) {
            if (jobLine.get(i).getStart() >= lastJob.getEnd()) {
                result.add(jobLine.get(i));
                lastJob = jobLine.get(i);
            }
        }

        return result;
    }
}
