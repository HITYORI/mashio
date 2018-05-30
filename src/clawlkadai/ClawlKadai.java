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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ClawlKadai {
    public static void main(String[] args)throws IOException{
        Path filePath=Paths.get("C:\\TechTraining\\danceMoney.csv");
        //Files.createFile(filePath);//このコードを入れるとエラーになる
        BufferedWriter bw=Files.newBufferedWriter(filePath);
        List<Map<String,Integer>>studioMoneyMaps=new ArrayList<>();//スタジオごとの料金をソートするため
        Map<String,Integer> studioMoneyMap=new HashMap<>();//スタジオの種類,料金
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
            System.out.println("aille");
            for(Entry<String,Integer>entry:studioMoneyMap.entrySet()) {
                System.out.println(entry.getKey()+","+entry.getValue());
            }
        }
        studioMoneyMaps.add(studioMoneyMap);
        if(studioMoneyMap.containsKey(s)) {
        }else {
            String rootUrl="https://sonysdance.com/";
            Document doc=Jsoup.connect(rootUrl).get();
            Element el=doc.select("#studio > div > div").get(0);
            String A=el.select("div").get(0).text();//スタジオ一覧
            String sinjuku="[新宿]";//新宿にあるスタジオだけ抽出
            Pattern p=Pattern.compile(sinjuku);
            Matcher m=p.matcher(A);
            if(m.find()) {
            System.out.println(A);
            }else {
                System.out.println("out");
            }

        }
    }
}
