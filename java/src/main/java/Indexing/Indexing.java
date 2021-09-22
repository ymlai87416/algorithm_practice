package Indexing;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Indexing {

    private static final Pattern TAG_PATTERN =
            Pattern.compile(".*?\\s#(\\w+).*?");

    private static final Pattern COMMENT_PATTERN =
            Pattern.compile("//.*|(\"(?:\\\\[^\"]|\\\\\"|.)*?\")|(?s)/\\*.*?\\*/");

    private static final String rootDirectory = "C:\\GitProjects\\algorithm_practice\\java";
    private static final String githubRootLink = "https://github.com/ymlai87416/algorithm_practice/blob/master/java";
    private static final String outputPath = "C:\\GitProjects\\algorithm_practice\\index";
    private static final String mySiteUrl = "https://ymlai87416.github.io/algorithm/";
    /*
        To view a code: https://github.com/ymlai87416/algorithm_practice/blob/master/src/main/java/Introduction/Anagram/UVA454.java
                        https://github.com/ymlai87416/algorithm_practice/blob/master/src/main/java/GoogleCodeJam/Y2008/Round1A/B/Solution.java
     */

    public static void main(String[] args) throws Exception{
        TreeMap<String, List<String>> result = new TreeMap<>();
        File[] root = new File[1];
        root[0] =new File(rootDirectory);
        List<File> codeFiles = getAllCodeFile(root);

        for(File codeFile: codeFiles) {

            String fullPath = codeFile.getAbsolutePath();
            String gitPath = convertToGitHubLink(fullPath);

            System.out.println("Scanning file: " + fullPath);

            String allLine = Files.readString(Path.of(fullPath));
            Matcher matcher = COMMENT_PATTERN.matcher(allLine);

            while (matcher.find()) {
                String foundComment = matcher.group(0);
                //System.out.println(foundComment);

                //process the comments
                List<String> hashTagz = getHashTag(foundComment);

                for (String hashTag : hashTagz) {

                    if (result.containsKey(hashTag)) {
                        List<String> subList = result.get(hashTag);
                        //but I want to link to the github instead of root
                        subList.add(gitPath);
                    } else {
                        List<String> newList = new ArrayList<>();
                        newList.add(gitPath);
                        result.put(hashTag, newList);
                    }

                }

            }

        }

        generateMainIndexHtml(result);
    }

    private static void generateMainIndexHtml(Map<String, List<String>> book) throws IOException{
        Set<String> allTopic = book.keySet();
        String outputHtml = outputPath + File.separator + "index.html";
        String content = "<html><body>";

        for(String topic: allTopic){
            String topicUrl = String.format("./%s.html", topic);
            content += String.format("<a href='%s'>%s</a><br/>", topicUrl, topic);

            generateSubTagHtml(topic, book.get(topic));
        }

        content += "</body>";

        FileWriter fw = new FileWriter(outputHtml);
        fw.write(content);
        fw.close();
    }

    private static void generateSubTagHtml(String key, List<String> pathz) throws IOException{

        String content = "<html><body><h1></h1>";
        String outputHtml = outputPath + File.separator + String.format("%s.html", key);

        for(String path: pathz){
            String[] token = path.split("/");
            String filename = token[token.length-1];
            content += String.format("<a href='%s'>%s</a><br/>", path, filename);
        }

        content += "</body>";

        FileWriter fw = new FileWriter(outputHtml);
        fw.write(content);
        fw.close();
    }

    private static String convertToGitHubLink(String path){
        String a = path.replace(rootDirectory, githubRootLink);
        a = a.replace("\\", "/");
        return a;
    }

    private static List<String> getHashTag(String input){

        Matcher matcher = TAG_PATTERN.matcher(input);
        ArrayList<String> result = new ArrayList<>();
        while (matcher.find()) {
            //System.out.println(matcher.group(0));
            result.add(matcher.group(1));
        }

        return result;
    }

    public static List<File> getAllCodeFile(File[] files) {
        ArrayList<File> result = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                List<File> subResult = getAllCodeFile(file.listFiles()); // Calls same method again.
                result.addAll(subResult);
            } else {
                if(file.getName().endsWith("java"))
                    result.add(file);
            }
        }

        return result;
    }
}
