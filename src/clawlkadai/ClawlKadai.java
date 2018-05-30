package clawlkadai;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ClawlKadai {
    public static void main(String[] args)throws IOException{
        Path filePath=Paths.get("C:\\TechTraining\\danceMoney.csv");
        //Files.createFile(filePath);//このコードを入れるとエラーになる
        BufferedWriter bw=Files.newBufferedWriter(filePath);
        List<Map<String,Integer>>studioMoneyMaps=new ArrayList<>();//スタジオごとの料金をソートするため
        Map<String,String>studioNameMap=new HashMap<>();//スタジオのグレード,スタジオ名
        Map<String,Integer> studioMoneyMap=new HashMap<>();//スタジオのグレード,料金
        String s = null;
        if(studioMoneyMap.containsKey(s)) {//ブロックの作成
        }else {
            String rootUrl="http://www.dancestudio-ailee.jp/charges/";
            Document doc =Jsoup.connect(rootUrl).get();
            Element el = doc.select("#content > section > table:nth-child(4) > tbody").get(0);
            String A=el.select("th").get(1).text();//スタジオ
            String B=el.select("th").get(2).text();
            String moneyA =el.select("td").get(0).text();//料金
            String moneyB =el.select("td").get(1).text();
            String realMoneyA=moneyA.replaceAll("[,円]", "");
            String realMoneyB=moneyB.replaceAll("[,円]", "");
            int a =Integer.parseInt(realMoneyA);
            int b =Integer.parseInt(realMoneyB);
            studioMoneyMap.put(A, a);
            studioMoneyMap.put(B, b);
            
            for(Entry<String,Integer>entry:studioMoneyMap.entrySet()) {
                
            }
        }
        if(studioMoneyMap.containsKey(s)) {//ブロック(2つ目)
        }else {
            String rootUrl="https://sonysdance.com/";
            Document doc=Jsoup.connect(rootUrl).get();
            Element el=doc.select("#studio > div > div").get(0);
            Element child = el.select("div").get(2);
            String A=el.select("div").get(1).text();//スタジオ一覧
            
            for(Element children:el.children()) {
                if(children.children().size()<1) {
                    continue;
                }
                String studio =children.select("dt").get(0).text();
                if(studio.contains("新宿")) {
                    String money =children.select("dd").get(1).text();//ddが3つある中の2番目
                    String realMoney=money.replaceAll("[1時間：,円～]", "");
                    int m=Integer.parseInt(realMoney);
                    studioMoneyMap.put(studio, m);
                }
                
            }
            studioMoneyMaps.add(studioMoneyMap);
            System.out.println(studioMoneyMaps);
            
        }
    }
}
