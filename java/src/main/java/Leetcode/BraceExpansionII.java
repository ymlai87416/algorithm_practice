package Leetcode;

import java.util.*;

public class BraceExpansionII {
    String expression;

    public List<String> braceExpansionII(String expression) {
        //my strategy: create a list
        //we encounte a { } we push a Set to the list
        this.expression  =expression;

        HashSet<String> result = parseList(expression);
        List<String> fresult = new ArrayList<String>();

        for(String s: result)
            fresult.add(s);
        Collections.sort(fresult);
        return fresult;
    }

    private HashSet<String> parseList(String input){
        System.out.println("PL " + input);

        List<Object> r = new ArrayList<Object>();

        //build up some base case here
        //if the list does not contain any {}, then it can be easily return
        if(input.indexOf('{') == -1){
            var result =  new HashSet<String>();
            result.add(input);
            return result;
        }

        int ptr = 0;
        while(ptr < input.length() ){
            char c = input.charAt(ptr);
            if(c == '{'){
                //then we advance the ptr until we find the close one
                int cc=1;
                int start = ptr;
                while(cc != 0){
                    ++ptr;
                    char nc = input.charAt(ptr);
                    if(nc == '{') ++cc;
                    if(nc == '}') --cc;
                }

                r.add(parseSet(input.substring(start, ptr+1)));
            }
            else
                r.add(""+c);

            ++ptr;
        }

        //now generate all the string and return
        return generateList(r, 0);

    }

    private HashSet<String> generateList(List<Object> list, int i){
        if(i == list.size()){
            String s = "";
            HashSet<String> rr = new HashSet<>();
            rr.add(s);
            return rr;
        }

        HashSet<String> result =new HashSet<String>();
        HashSet<String> sub = generateList(list, i+1);

        Object obj = list.get(i);
        if(obj instanceof HashSet){
            HashSet<String> curr = (HashSet<String>)obj;
            for(String fs: curr){
                for(String ns: sub){
                    result.add(fs+ns);
                }
            }
        }
        else{
            String c = (String)(list.get(i));
            for(String ns: sub){
                result.add(c + ns);
            }
        }

        return result;
    }

    private HashSet<String> parseSet(String s){
        System.out.println("PS " + s);

        HashSet<String> r = new HashSet<>();

        List<String> ss = splitSet(s);

        for(int i=0; i<ss.size(); ++i){
            HashSet<String> inter = parseList(ss.get(i));
            r.addAll(inter);
        }

        return r;
    }

    private List<String> splitSet(String s){
        List<String> result = new ArrayList<String>();


        s = s.substring(1, s.length()-1);
        int start=0;
        int ptr=0;
        int bracketC = 0;

        while(ptr < s.length()){

            if(s.charAt(ptr) == '{'){
                bracketC = 0;
                do{
                    if(s.charAt(ptr) == '{')
                        bracketC++;
                    else if(s.charAt(ptr) == '}')
                        bracketC--;

                    ++ptr;
                }while(bracketC > 0);
                --ptr;
            }
            else if(s.charAt(ptr) == ','){
                result.add(s.substring(start, ptr));
                start = ptr+1;
            }

            ++ptr;
        }
        result.add(s.substring(start, ptr));

        //System.out.println("D " + s + " " + result);
        return result;
    }

    public static void main(String[] args){
        BraceExpansionII c =new BraceExpansionII();
        System.out.println(c.braceExpansionII("{{a,z},a{b,c},{ab,z}}"));
        System.out.println(c.braceExpansionII("{a,b}{c,{d,e}}"));
    }
}
