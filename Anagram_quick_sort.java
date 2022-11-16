//Assignment_2_sorting

import java.util.*;
import java.io.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        String[] str={"bucket","rat","mango","tango","ogtan","tar"};

        List<String> list=new ArrayList<>();
        List<List<String>> ans=new ArrayList<>();


        for(int i=0;i<str.length;i++)   list.add(str[i]);

        ans=groupAnagram(list);
        System.out.println(ans);
    }

    public static List<List<String>> groupAnagram(List<String> strings)
    {
        List<List<String>> result=new ArrayList<>();
        String[] str=new String[strings.size()];
        HashMap<String,List<String>> map=new HashMap<>();

        for(int i=0;i<str.length;i++)
        {
            str[i]=strings.get(i);

            char[] ch_array=str[i].toCharArray();
            Arrays.sort(ch_array);

            String rearrange=new String(ch_array);
            if(map.containsKey(rearrange))
            {
                map.get(rearrange).add(str[i]);
            }
            else
            {
                map.put(rearrange,new ArrayList<>());
                map.get(rearrange).add(str[i]);
            }
        }

        for(Map.Entry<String, List<String>> entry:map.entrySet())
        {
            result.add(entry.getValue());
        }
        return result;
    }
}


//-----------------------------------------------------------------------------------------------------------------------------------------------------------
//use quicksort and read self data
//reference:https://www.geeksforgeeks.org/quick-sort/
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        String[] str={"bucket","rat","mango","tango","ogtan","tar"};

        List<String> list=new ArrayList<>();
        List<List<String>> ans=new ArrayList<>();


        for(int i=0;i<str.length;i++)   list.add(str[i]);

        ans=groupAnagram(list);

        System.out.println(ans);
    }

    static void swap(char[] arr, int i, int j)
    {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /* This function takes last element as pivot, places
       the pivot element at its correct position in sorted
       array, and places all smaller (smaller than pivot)
       to left of pivot and all greater elements to right
       of pivot */
    static int partition(char[] arr, int low, int high)
    {

        // pivot
        int pivot = arr[high];

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller
            // than the pivot
            if (arr[j] < pivot) {

                // Increment index of
                // smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    /* The main function that implements QuickSort
              arr[] --> Array to be sorted,
              low --> Starting index,
              high --> Ending index
     */
    static void quickSort(char[] arr, int low, int high)
    {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }


    public static List<List<String>> groupAnagram(List<String> strings)
    {
        List<List<String>> result=new ArrayList<>();
        String[] str=new String[strings.size()];
        HashMap<String,List<String>> map=new HashMap<>();

        for(int i=0;i<str.length;i++)
        {
            str[i]=strings.get(i);

            char[] ch_array=str[i].toCharArray();
            quickSort(ch_array,0, ch_array.length-1);
//            Arrays.sort(ch_array);

            String rearrange=new String(ch_array);
            if(map.containsKey(rearrange))
            {
                map.get(rearrange).add(str[i]);
            }
            else
            {
                map.put(rearrange,new ArrayList<>());
                map.get(rearrange).add(str[i]);
            }
        }

        for(Map.Entry<String, List<String>> entry:map.entrySet())
        {
            result.add(entry.getValue());
        }
        return result;
    }
}

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//use quicksort and read the txt file
import java.util.*;
import java.io.*;

public class Main
{
    static List<String> list=new ArrayList<>();

    public static void main(String[] args) throws IOException
    {
        List<List<String>> ans=new ArrayList<>();

        FileReader fr = new FileReader("C:\\Users\\user\\Desktop\\美國留學\\UC_Irvine\\修課資料\\data structure\\作業/pride-and-prejudice.txt");
        BufferedReader br = new BufferedReader(fr);

        while (br.ready())
        {
            String str=br.readLine();
            str_process(str);
        }
        fr.close();

        ans=groupAnagram(list);

        System.out.println(ans);


//        String[] str={"bucket","rat","mango","tango","ogtan","tar","ognma"};
//
//        List<String> list=new ArrayList<>();
//        List<List<String>> ans=new ArrayList<>();
//
//
//        for(int i=0;i<str.length;i++)   list.add(str[i]);
//
//        ans=groupAnagram(list);
//        for(int i=0;i<ans.size();i++)
//        {
//            for(int j=0;j<ans.get(i).size();j++)
//            {
//                System.out.print(ans.get(i).get(j)+" ");
//            }
//            System.out.println();
//        }
    }

    public static List<String> str_process(String str)
    {
        int left=0,right=0;
        String ans;


        while(left<str.length() && right<str.length())
        {
            while((left<str.length() && right<str.length()) && ((str.charAt(left)<48 && str.charAt(left)>57) || (str.charAt(left)<65 && str.charAt(left)>90) || (str.charAt(left)<97 && str.charAt(left)>122)))
            {
                left++;
            }

            right=left;

            while((left<str.length() && right<str.length()) && ((str.charAt(right)>=48 && str.charAt(right)<=57) || (str.charAt(right)>=65 && str.charAt(right)<=90) || (str.charAt(right)>=97 && str.charAt(right)<=122)))
            {
                right++;
            }

            ans=str.substring(left,right);
            list.add(ans);

            left=right+1;
        }
        return list;
    }

    static void swap(char[] arr, int i, int j)
    {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /* This function takes last element as pivot, places
       the pivot element at its correct position in sorted
       array, and places all smaller (smaller than pivot)
       to left of pivot and all greater elements to right
       of pivot */
    static int partition(char[] arr, int low, int high)
    {

        // pivot
        int pivot = arr[high];

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller
            // than the pivot
            if (arr[j] < pivot) {

                // Increment index of
                // smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    /* The main function that implements QuickSort
              arr[] --> Array to be sorted,
              low --> Starting index,
              high --> Ending index
     */
    static void quickSort(char[] arr, int low, int high)
    {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }


    public static List<List<String>> groupAnagram(List<String> strings)
    {
        List<List<String>> result=new ArrayList<>();
        String[] str=new String[strings.size()];
        HashMap<String,List<String>> map=new HashMap<>();

        for(int i=0;i<str.length;i++)
        {
            str[i]=strings.get(i);

            char[] ch_array=str[i].toCharArray();
            quickSort(ch_array,0, ch_array.length-1);
//            Arrays.sort(ch_array);

            String rearrange=new String(ch_array);
            if(map.containsKey(rearrange))
            {
                map.get(rearrange).add(str[i]);
            }
            else
            {
                map.put(rearrange,new ArrayList<>());
                map.get(rearrange).add(str[i]);
            }
        }

        for(Map.Entry<String, List<String>> entry:map.entrySet())
        {
            result.add(entry.getValue());
        }
        return result;
    }
}

