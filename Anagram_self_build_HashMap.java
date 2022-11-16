//1.use txt profile to test the Hash Function
//reference:https://dotblogs.com.tw/cylcode/2018/09/21/170510 / https://www.youtube.com/watch?v=KyUTuwz_b7Q
import java.util.*;
import java.io.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        HashFunction fun1=new HashFunction(1000);

        FileReader fr = new FileReader("C:\\Users\\user\\Desktop\\美國留學\\UC_Irvine\\修課資料\\data structure\\作業/words-shuffled.txt");
        BufferedReader br = new BufferedReader(fr);

        while (br.ready()) {
            fun1.insert(br.readLine());
        }
        fr.close();

        for(int i=0;i<fun1.array_size;i++)
        {
            for(int j=0;j<fun1.array[i].size;j++)     System.out.print(fun1.array[i].get(j).str+" ");
            System.out.println();
        }
        System.out.println(fun1.size());


//        String[] str={"abc","asd","zxc","bca","cba"};
//        HashFunction fun1=new HashFunction(10);
//        for(int i=0;i<str.length;i++)  fun1.insert(str[i]);
//
//        for(int i=0;i<fun1.array_size;i++)
//        {
//            for(int j=0;j<fun1.array[i].size;j++)     System.out.print(fun1.array[i].get(j).str+" ");
//            System.out.println();
//        }
//        System.out.println(fun1.size());
    }
}


class HashFunction
{
    MyLinkedList[] array;
    int array_size=0, component=0;
    HashFunction(int size)
    {
        array_size = size;
        array=new MyLinkedList[array_size];

        for(int i=0;i<array_size;i++)   array[i]=new MyLinkedList();
    }

    public void insert(String str)
    {
        component++;

        int ascii=hash(str);
        array[ascii].add(str);
    }

    public int hash(String str)
    {
        char[] ch_array=str.toCharArray();
        int ascii=0;

        for(int i=0;i<ch_array.length;i++)
        {
            ascii=ascii+(int)ch_array[i];
        }
        ascii=ascii%array_size;

        return ascii;
    }
    public int size()
    {
        return component;
    }
}

class MyLinkedList
{
    //size存储链表元素的个数
    int size;
    //虚拟头结点
    ListNode head;												//另外自己將head加在鏈結前面，ex. 1->2->4加入head變成	head->1->2->4
    //****加入head之前和之後size都是3
    Queue<Integer> queue=new LinkedList<>();
    //初始化链表

    public MyLinkedList()
    {
        size = 0;
        head = new ListNode("");
    }

    public ListNode get(int index)
    {
        //如果index非法，返回-1
        if (index < 0 || index >= size) 						//ex. 	1->2->4，鏈表size為3，但是index為
        {														//index 0, 1, 2
            return null;
        }
        ListNode currentNode = head;
        //包含一个虚拟头节点，所以查找第 index+1 个节点
        for (int i = 0; i < index + 1; i++) 					//ex. 	1->2->4加入head變成	head->1->2->4	//如果寫for (int i = 0; i < index; i++)代表找到index的左邊一個數字
        {														//index 0, 1, 2			index  0, 1, 2, 3	//若寫for (int i = 0; i < index + 1; i++)代表找到index
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    // 在第 index 个节点之前插入一个新节点，例如index为0，那么新插入的节点为链表的新头节点。
    // 如果 index 等于链表的长度，则说明是新插入的节点为链表的尾结点
    // 如果 index 大于链表的长度，则返回空
    public void addAtIndex(int index, String str)
    {
        if (index > size)
        {
            return;
        }
        if (index < 0)
        {
            index = 0;
        }

        //找到要插入节点的前驱
        ListNode pred = head;
        for (int i = 0; i < index; i++) 			//找到index的前1個數字
        {
            pred = pred.next;
        }
        ListNode toAdd = new ListNode(str);
        toAdd.next = pred.next;
        pred.next = toAdd;

        size++;
    }

    //在链表的最后插入一个节点
    public void add(String str)
    {
        addAtIndex(size, str);
    }
}

class ListNode
{
    String str;
    ListNode next;
    ListNode(){}				    //constructor建構元
    ListNode(String str) 			//constructor建構元
    {
        this.str=str;
    }
}


//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//2.Anagram字謎, cannot split string
import java.util.*;
import java.io.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        HashFunction fun1=new HashFunction(100);

        FileReader fr = new FileReader("C:\\Users\\user\\Desktop\\美國留學\\UC_Irvine\\修課資料\\data structure\\作業/words-shuffled.txt");
        BufferedReader br = new BufferedReader(fr);

        while (br.ready()) {
            fun1.insert(br.readLine());
        }
        fr.close();

        for(int i=0;i<fun1.array_size;i++)
        {
            for(int j=0;j<fun1.array[i].size;j++)     System.out.print(fun1.array[i].get(j).str+" ");
            System.out.println();
        }
        System.out.println(fun1.size());
    }
}


class HashFunction
{
    MyLinkedList[] array;
    int array_size=0, component=0,flag=0;
    char[] ch_array;
    HashFunction(int size)
    {
        array_size = size;
        array=new MyLinkedList[array_size];

        for(int i=0;i<array_size;i++)   array[i]=new MyLinkedList();
    }

    public void insert(String str)
    {
        int ascii=hash(str);
        if(array[ascii].size==0)
        {
            component++;
            array[ascii].add(new String(ch_array));
        }
        else
        {
            flag=0;
            for(int i=0;i<array[ascii].size;i++)
            {
                if(array[ascii].get(i).str.equals(new String(ch_array)))
                {
                    flag=1;
                    break;
                }
            }
            if(flag==0)
            {
                component++;
                array[ascii].add(new String(ch_array));
            }
        }
    }

    public int hash(String str)
    {
        ch_array=str.toCharArray();
        Arrays.sort(ch_array);
        int ascii=0;

        for(int i=0;i<ch_array.length;i++)
        {
            ascii=ascii+(int)ch_array[i];
        }
        ascii=ascii%array_size;

        return ascii;
    }
    public int size()
    {
        return component;
    }
}

class MyLinkedList
{
    //size存储链表元素的个数
    int size;
    //虚拟头结点
    ListNode head;												//另外自己將head加在鏈結前面，ex. 1->2->4加入head變成	head->1->2->4
    //****加入head之前和之後size都是3
    Queue<Integer> queue=new LinkedList<>();
    //初始化链表

    public MyLinkedList()
    {
        size = 0;
        head = new ListNode("");
    }

    public ListNode get(int index)
    {
        //如果index非法，返回-1
        if (index < 0 || index >= size) 						//ex. 	1->2->4，鏈表size為3，但是index為
        {														//index 0, 1, 2
            return null;
        }
        ListNode currentNode = head;
        //包含一个虚拟头节点，所以查找第 index+1 个节点
        for (int i = 0; i < index + 1; i++) 					//ex. 	1->2->4加入head變成	head->1->2->4	//如果寫for (int i = 0; i < index; i++)代表找到index的左邊一個數字
        {														//index 0, 1, 2			index  0, 1, 2, 3	//若寫for (int i = 0; i < index + 1; i++)代表找到index
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    // 在第 index 个节点之前插入一个新节点，例如index为0，那么新插入的节点为链表的新头节点。
    // 如果 index 等于链表的长度，则说明是新插入的节点为链表的尾结点
    // 如果 index 大于链表的长度，则返回空
    public void addAtIndex(int index, String str)
    {
        if (index > size)
        {
            return;
        }
        if (index < 0)
        {
            index = 0;
        }

        //找到要插入节点的前驱
        ListNode pred = head;
        for (int i = 0; i < index; i++) 			//找到index的前1個數字
        {
            pred = pred.next;
        }
        ListNode toAdd = new ListNode(str);
        toAdd.next = pred.next;
        pred.next = toAdd;

        size++;
    }

    //在链表的最后插入一个节点
    public void add(String str)
    {
        addAtIndex(size, str);
    }
}

class ListNode
{
    String str;
    ListNode next;
    ListNode(){}				    //constructor建構元
    ListNode(String str) 			//constructor建構元
    {
        this.str=str;
    }
}

//--------------------------------------------------------------------------------------------------------------------------------------------------------------------
//3.Anagram, can split string
import java.util.*;
import java.io.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        String str;
        HashFunction fun1=new HashFunction(100);
        String[] each_string=new String[1000000];

        FileReader fr = new FileReader("C:\\Users\\user\\Desktop\\美國留學\\UC_Irvine\\修課資料\\data structure\\作業/pride-and-prejudice.txt");
        BufferedReader br = new BufferedReader(fr);

        while (br.ready()) {
            str=br.readLine();
            each_string=fun1.str_process(str);
            for(int j=0;j<each_string.length;j++)   fun1.insert(each_string[j]);
        }
        fr.close();

        for(int i=0;i<fun1.array_size;i++)
        {
            for(int j=0;j<fun1.array[i].size;j++)     System.out.print(fun1.array[i].get(j).str+" ");
            System.out.println();
        }
        System.out.println(fun1.size());

//        String[] str={"a   big mic/digit","log*LRU-pointer 123"};
//        HashFunction fun1=new HashFunction(10);
//        String[] each_string=new String[1000];
//
//        for(int i=0;i<str.length;i++)
//        {
//            each_string=fun1.str_process(str[i]);
//            for(int j=0;j<each_string.length;j++)   fun1.insert(each_string[j]);
//        }
//
//
//        for(int i=0;i<fun1.array_size;i++)
//        {
//            for(int j=0;j<fun1.array[i].size;j++)     System.out.print(fun1.array[i].get(j).str+" ");
//            System.out.println();
//        }
//        System.out.println(fun1.size());
    }
}


class HashFunction
{
    MyLinkedList[] array;
    int array_size=0, component=0,flag=0;
    char[] ch_array;
    HashFunction(int size)
    {
        array_size = size;
        array=new MyLinkedList[array_size];

        for(int i=0;i<array_size;i++)   array[i]=new MyLinkedList();
    }

    public String[] str_process(String str)
    {
        int left=0,right=0;
        List<String> list=new ArrayList<>();
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
        String[] ans_array= new String[list.size()];
        list.toArray(ans_array);
        return ans_array;
    }

    public void insert(String str)
    {
        int ascii=hash(str);
        if(array[ascii].size==0)
        {
            component++;
            array[ascii].add(new String(ch_array));
        }
        else
        {
            flag=0;
            for(int i=0;i<array[ascii].size;i++)
            {
                if(array[ascii].get(i).str.equals(new String(ch_array)))
                {
                    flag=1;
                    break;
                }
            }
            if(flag==0)
            {
                component++;
                array[ascii].add(new String(ch_array));
            }
        }
    }

    public int hash(String str)
    {
        ch_array=str.toCharArray();
        Arrays.sort(ch_array);
        int ascii=0;

        for(int i=0;i<ch_array.length;i++)
        {
            ascii=ascii+(int)ch_array[i];
        }
        ascii=ascii%array_size;

        return ascii;
    }
    public int size()
    {
        return component;
    }
}

class MyLinkedList
{
    //size存储链表元素的个数
    int size;
    //虚拟头结点
    ListNode head;												//另外自己將head加在鏈結前面，ex. 1->2->4加入head變成	head->1->2->4
    //****加入head之前和之後size都是3
    Queue<Integer> queue=new LinkedList<>();
    //初始化链表

    public MyLinkedList()
    {
        size = 0;
        head = new ListNode("");
    }

    public ListNode get(int index)
    {
        //如果index非法，返回-1
        if (index < 0 || index >= size) 						//ex. 	1->2->4，鏈表size為3，但是index為
        {														//index 0, 1, 2
            return null;
        }
        ListNode currentNode = head;
        //包含一个虚拟头节点，所以查找第 index+1 个节点
        for (int i = 0; i < index + 1; i++) 					//ex. 	1->2->4加入head變成	head->1->2->4	//如果寫for (int i = 0; i < index; i++)代表找到index的左邊一個數字
        {														//index 0, 1, 2			index  0, 1, 2, 3	//若寫for (int i = 0; i < index + 1; i++)代表找到index
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    // 在第 index 个节点之前插入一个新节点，例如index为0，那么新插入的节点为链表的新头节点。
    // 如果 index 等于链表的长度，则说明是新插入的节点为链表的尾结点
    // 如果 index 大于链表的长度，则返回空
    public void addAtIndex(int index, String str)
    {
        if (index > size)
        {
            return;
        }
        if (index < 0)
        {
            index = 0;
        }

        //找到要插入节点的前驱
        ListNode pred = head;
        for (int i = 0; i < index; i++) 			//找到index的前1個數字
        {
            pred = pred.next;
        }
        ListNode toAdd = new ListNode(str);
        toAdd.next = pred.next;
        pred.next = toAdd;

        size++;
    }

    //在链表的最后插入一个节点
    public void add(String str)
    {
        addAtIndex(size, str);
    }
}

class ListNode
{
    String str;
    ListNode next;
    ListNode(){}				    //constructor建構元
    ListNode(String str) 			//constructor建構元
    {
        this.str=str;
    }
}
