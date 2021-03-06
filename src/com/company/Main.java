package com.company;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Main {

    static List<Integer> getIntegersFromList(List<Object> unfilteredList) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (var element: unfilteredList) {
            if(element instanceof Integer){

                arr.add((Integer) element);
            }
        }
        return arr;
    }

    static List<Object> getIntegersFromListStreamed(List<Object> unfilteredList) {
        return unfilteredList.stream()
                .filter(obj -> obj instanceof Integer)
                .collect(Collectors.toList());
    }

    private static String first_non_repeating_letter(String s) {
        LinkedHashMap<Character,Integer> sortedChar2Idx
                = new LinkedHashMap<>() {};
        String lower = s.toLowerCase();
        for (int i=0; i<lower.length(); i++) {
            Character c = lower.charAt(i);
            if(sortedChar2Idx.containsKey(c)){
//              If there is a duplicate, set the value to -1 as an index flag
                sortedChar2Idx.put(c,-1);
            } else {
//              Otherwise, set the index in the original string
                sortedChar2Idx.put(c,i);
            }
        }
        List<Character> keys = new LinkedList<>(sortedChar2Idx.keySet());
        for (int i = 0; i < sortedChar2Idx.size(); i++) {
            Character c = keys.get(i);
            if(sortedChar2Idx.get(c)!=-1){
                Integer characterIdx = sortedChar2Idx.get(c);
                return String.valueOf(s.charAt(characterIdx));
            }
        }
        return "";
    }
    static  Integer digitalRoot (Integer number){
        int result = 0;
        while (number!=0){
            result += number%10;
            number = number/10;
        }
        if(result/10!=0) return digitalRoot(result);
        else {return result;}
    }

    static int getPairsCount(ArrayList<Integer> arr, Integer sum)
    {
        HashMap<Integer, Integer> counter = new HashMap<>();
        for (Integer element: arr) {
            if (!counter.containsKey(element)) {
                counter.put(element, 0);
            }
            counter.put(element, counter.get(element) + 1);
        }
        int twice_count = 0;
        for (Integer element: arr) {
            if (counter.get(sum - element) != null)
                twice_count += counter.get(sum - element);
//            Remove sum of identicals
            if (sum - element == element) {
                twice_count--;
            }
        }
        return twice_count/2;
    }

    static int getPairsCountStream(ArrayList<Integer> arr, Integer sum)
    {
        HashMap<Integer, Integer> counter = new HashMap<>();
        arr.stream().forEach(element -> {
            if (!counter.containsKey(element)) {
                counter.put(element, 0);
            }
            counter.put(element, counter.get(element) + 1);
        });
        int twice_count = arr.stream()
                .filter(element -> counter.get(sum - element) != null)
                .mapToInt(element -> counter.get(sum - element))
                .sum();
        return twice_count / 2;
    }

    static List<String[]> sortUpperList(ArrayList<List<String>> list)
    {
        return list.stream()
                .map(fullName -> String.join(" ",String.valueOf(fullName).toUpperCase()))
                .sorted()
                .map(fullName -> fullName.split(" ")).collect(Collectors.toList());
    }


    static Integer getBiggerRearrangedNumber(Integer number)
    {
        Integer numberOfDigits = number.toString().length();
        StringBuilder numberStr = new StringBuilder(number.toString());
        for (int i = numberOfDigits-1; i >=0 ; i--) {
            for (int j = i-1; j>=0; j--) {
                if(numberStr.charAt(i)>numberStr.charAt(j)){
                    Character temp = numberStr.charAt(i);
                    numberStr.setCharAt(i,numberStr.charAt(j));
                    numberStr.setCharAt(j,temp);
                    String s =  numberStr.substring(j + 1)
                                         .chars()
                                         .mapToObj(e->(char)e)
                                         .sorted()
                                         .map(String::valueOf)
                                         .collect(Collectors.joining());
                    return Integer.valueOf(numberStr.substring(0,j+1)+s);
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        extraTask1();
    }

    static void task1(){
        System.out.println("--------------------- Task 1 --------------------- ");
        System.out.println("loop");
        System.out.println(getIntegersFromList(asList(1,2,'a','b')));
        System.out.println(getIntegersFromList(asList(1,2,'a','b',0,15)));
        System.out.println(getIntegersFromList(asList(1,2,'a','b', "aasf",'1', "123",231)));
        System.out.println("streams");
        System.out.println(getIntegersFromListStreamed(asList(1,2,'a','b')));
        System.out.println(getIntegersFromListStreamed(asList(1,2,'a','b',0,15)));
        System.out.println(getIntegersFromListStreamed(asList(1,2,'a','b', "aasf",'1', "123",231)));
    }

    static void task2(){
        System.out.println("--------------------- Task 2 --------------------- ");
        String s = "test advanCED nothInG ElSe MaTteRs. super task.";
        System.out.println(first_non_repeating_letter(s));
    }

    static void task3(){
        System.out.println("--------------------- Task 3 --------------------- ");
        System.out.print("16 ");
        System.out.println(digitalRoot(16));
        System.out.print("942 ");
        System.out.println(digitalRoot(942));
        System.out.print("132189 ");
        System.out.println(digitalRoot(132189));
        System.out.print("493193 ");
        System.out.println(digitalRoot(493193));

    }

    static void task4(){
        System.out.println("--------------------- Task 4 --------------------- ");
        Integer [] arr =  {1, 3, 6, 2, 2, 0, 4, 5};
        ArrayList<Integer> arrlist = (ArrayList<Integer>) Arrays.stream(arr)
                .collect(Collectors.toList());
        System.out.println("loop");
        System.out.println(getPairsCount(arrlist,5));
        System.out.println("streams");
        System.out.println(getPairsCountStream(arrlist,5));
    }

    static void task5(){
        System.out.println("--------------------- Task 5 --------------------- ");
        ArrayList<List<String>> arr = (ArrayList<List<String>>)
                    new ArrayList<>(Arrays.asList(Arrays.asList("Corwill", "Alfred"),
                Arrays.asList("Tornbull", "Betty"),
                Arrays.asList("Tornbull", "Barney"),
                Arrays.asList("Corwill", "Raphael"),
                Arrays.asList("Corwill", "Wilfred"),
                Arrays.asList("Tornbull", "Bjon")));
        sortUpperList(arr).forEach(fullName->System.out.println(fullName[0]+" "+fullName[1]));
    }

    static void extraTask1(){
        System.out.println("--------------------- ExtraTask 1 --------------------- ");
        System.out.print("12 ");
        System.out.println(getBiggerRearrangedNumber(12));
        System.out.print("513 ");
        System.out.println(getBiggerRearrangedNumber(513));
        System.out.print("2017 ");
        System.out.println(getBiggerRearrangedNumber(2017));
        System.out.print("1234 ");
        System.out.println(getBiggerRearrangedNumber(1234));
        System.out.print("4321 ");
        System.out.println(getBiggerRearrangedNumber(4321));
        System.out.print("534976 ");
        System.out.println(getBiggerRearrangedNumber(534976));
    }

}
