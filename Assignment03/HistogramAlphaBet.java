package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;

public class HistogramAlphaBet {
    String data;
    HashMap<Character, Integer> charCountMap;
    float total = 0;
    List<Map.Entry<Character, Integer>> sorted_map;
    List<Map.Entry<Character, Float>> sorted_probabilitymap;
    HashMap<Character, Float> probability_map;

    public HistogramAlphaBet() {
        Path path = Paths.get("D:\\Users\\pug\\IdeaProjects\\project_3\\Alice in Wonderland.txt");
        try {
            this.data = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.data = this.data.toLowerCase();
        this.data = this.data.replaceAll("[^a-zA-Z]", "");

        this.charCountMap = new HashMap<Character, Integer>();
        this.probability_map = new HashMap<Character, Float>();
        char[] strArray = this.data.toCharArray();

        for (char c : strArray) {
            if (this.charCountMap.containsKey(c)) {
                this.charCountMap.put(c, this.charCountMap.get(c) + 1);
                this.probability_map.put(c, this.probability_map.get(c) + 1);
            }
            else {
                this.charCountMap.put(c, 1);
                this.probability_map.put(c, (float)1);
            }
        }
        //counts the total entry
        for (int entry : this.charCountMap.values()) {
            this.total += entry;
        }

        this.sorted_map = this.charCountMap.entrySet()
                .stream()
                .sorted(reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toList());

        for (Map.Entry<Character, Float> entry: probability_map.entrySet()) {
            //divides hash by total
            float prob = entry.getValue()/this.total;
            //replace values in hash map with result
            probability_map.put(entry.getKey(), prob);
        }
        this.sorted_probabilitymap = this.probability_map.entrySet()
                .stream()
                .sorted(reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toList());
    }

    public static class MyPieChart {
        double x, y, height, width, StartAngle;
        int n;
        float total;

        List<Map.Entry<Character, Float>> pieChartMap;

        public MyPieChart(double x, double y, double height, double width, int n){
            this.x = x;
            this.y = y;
            this.height = height;
            this.width = width;
            this.StartAngle = 0;
            this.n = n;

            HistogramAlphaBet histoalphabet = new HistogramAlphaBet();
            //total is one since all the values in piechart add to 1
            this.total = 1;
            this.pieChartMap = (List<Map.Entry<Character, Float>>) histoalphabet.sorted_probabilitymap;
        }

        public void draw(GraphicsContext gc){
            int i = 0;
            float sum_total = 0;
            float rest_total = 0;
            Slice [] slice_pie_chart = new Slice[this.n+1];
            MyColor [] color_slice = MyColor.values();

            float [] float_value = new float[25];
            //copy map values to float array for easy indexing
            int p = 0;
            for(Map.Entry<Character, Float> entry : pieChartMap){
                float_value[p] = entry.getValue();
                p++;
                if (p == 25){
                    break;
                }
            }

            while (i < n){
                sum_total += float_value[i];
                slice_pie_chart[i] = new Slice(this.x, this.y, this.width,this.StartAngle,float_value[i]*360 , color_slice[i]);
                this.StartAngle += float_value[i]*360;
                i++;
                if (i >= n){
                    rest_total = this.total - sum_total;
                    //last slice
                    slice_pie_chart[this.n] = new Slice(this.x, this.y, this.width,this.StartAngle,rest_total*360 , MyColor.HOTPINK);
                    slice_pie_chart[this.n].key = String.valueOf("Remaining");
                    slice_pie_chart[this.n].value = String.valueOf(rest_total);
                    break;
                }
            }

            int u = 0;
            for(Map.Entry<Character, Float> entry : pieChartMap) {
                slice_pie_chart[u].key = String.valueOf(entry.getKey());
                slice_pie_chart[u].value = String.valueOf(entry.getValue());
                u++;
                if (u == this.n){
                    break;
                }
            }

            int space = 50;
            for (int o = 0; o < slice_pie_chart.length; o++){
                slice_pie_chart[o].draw(gc);
                gc.setFont(new Font(20));
                gc.fillText(String.valueOf(slice_pie_chart[o].key), 50, space);
                gc.fillText(slice_pie_chart[o].value, 200, space);
                space += 20;
            }
        }
    }
}



