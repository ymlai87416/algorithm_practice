package Leetcode.MicrosoftMock;

import java.util.ArrayList;
import java.util.List;

public class OnsiteQ3 {

    public List<String> removeComments(String[] source) {
        boolean blockComment = false;
        String beforeBlock = null;
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < source.length; i++) {

            if(blockComment){
                if(source[i].contains("*/")) {
                    String newS = removeBlockEndComment(source[i]);
                    blockComment = false;

                    if(newS.contains("//"))
                        newS = removeLineComment(source[i]);
                    addToList(result,beforeBlock + newS);
                }
                else
                    continue; //whole line removed
            }
            else{
                int posLine = source[i].indexOf("//");
                int blockOpen = source[i].indexOf("/*");

                if(posLine != -1 && blockOpen != -1){
                    if(posLine < blockOpen){
                        String newS = removeLineComment(source[i]);
                        addToList(result, newS);
                    }
                    else{
                        if(source[i].contains("/*") && source[i].contains("*/")){
                            String newS = removeBlockInLineComment(source[i]);

                            if(newS.contains("//"))
                                newS = removeLineComment(source[i]);
                            addToList(result, newS);

                        }
                        else if(source[i].contains("/*")){
                            String newS = removeBlockStartComment(source[i]);
                            //addToList(result, newS);
                            beforeBlock = newS;
                            blockComment = true;
                        }
                    }
                }
                else if(posLine != -1){
                    String newS = removeLineComment(source[i]);
                    addToList(result, newS);
                }
                else if(blockOpen != -1) {
                    if (source[i].contains("/*") && source[i].contains("*/")) {
                        String newS = removeBlockInLineComment(source[i]);

                        if (newS.contains("//"))
                            newS = removeLineComment(source[i]);
                        addToList(result, newS);

                    } else if (source[i].contains("/*")) {
                        String newS = removeBlockStartComment(source[i]);
                        //addToList(result, newS);
                        beforeBlock = newS;
                        blockComment = true;
                    }
                }

                else
                    addToList(result, source[i]);
            }

        }

        return result;
    }

    private void addToList(List<String> a, String b){

        if(b.length() == 0) return;

        else a.add(b);
    }


    private String removeLineComment(String s){
        int pos = s.indexOf("//");
        return s.substring(0, pos);
    }

    private String removeBlockStartComment(String s){
        int pos = s.indexOf("/*");
        return s.substring(0, pos);
    }

    private String removeBlockEndComment(String s){
        int pos = s.indexOf("*/");
        return s.substring(pos+2);
    }

    private String removeBlockInLineComment(String s){
        int pos = s.indexOf("/*");
        int pos2 = s.indexOf("*/");
        String ss = s.replaceAll("/\\*(.*)\\*/", "");
        return ss;
        //return s.substring(0, pos) + s.substring(pos2+2);
    }

    public static void main(String[] args){
        String[] input = {"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"};
        OnsiteQ3 s = new OnsiteQ3();
        //System.out.println(s.removeComments(input));

        input = new String[] {"void func(int k) {", "// this function does nothing /*", "   k = k*2/4;", "   k = k/2;*/", "}"};
        System.out.println(s.removeComments(input));
    }
}
