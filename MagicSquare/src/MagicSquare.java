/**
 * Created by delll on 2018/3/5.
 */
import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class MagicSquare {

    public static boolean isLegalMagicSquare(String fileName){
        File f=new File(fileName);
        BufferedReader r = null;
        ArrayList<ArrayList< Integer >> magic = new ArrayList<>();
        int n = 0;//n*n的矩阵
        int val;
        boolean result = true;
        try {
            r=new BufferedReader(new FileReader(f));
            String str = null;
            while ((str = r.readLine()) != null) {
                String[] s= str.split("\t");
                ArrayList< Integer > line= new ArrayList<>();

                for(String e : s) {
                    val = Integer.valueOf(e);
                    line.add(val);
                }
                magic.add(line);
            }
            r.close();
        } catch (IOException e) {e.printStackTrace();}
        catch (NumberFormatException e){System.out.println("format exception");return false;}

        n = magic.size();
        for(ArrayList<Integer> i : magic){
            if(i.size() != n)
                result  = false;
            break;
        }


        if(result == true)
        {
            int[] sum_row = new int[n];//每一行的和
            int[] sum_col = new int[n];//每一列的和
            int[] sum_dia = new int[2];//对角线的和

            for(int i=0;i<n;i++)
            {
                sum_dia[0] += magic.get(i).get(i);
            }

            for(int i=n-1;i>=0;i--)
            {
                sum_dia[1] += magic.get(i).get(i);
            }

            if(sum_dia[0] != sum_dia[1])
                result = false;
            else{
                int sum = sum_dia[0];

                for(int i = 0;i < n;i++)
                {
                    for(int j = 0;j < n;j++)
                    {
                        sum_row[i] += magic.get(i).get(j);  //计算第i行的和
                    }
                    if(sum_row[i] != sum)
                        result = false;
                    break;
                }

                if(result == true){
                    for(int j=0;j<n;j++)
                    {
                        for(int i=0;i<n;i++)
                        {
                            sum_col[j] += magic.get(j).get(i);//记录第j列的值
                        }
                        if(sum_col[j]!=sum)
                           result = false;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static boolean generateMagicSquare(int n) {
        PrintWriter outputStream = null;
        try
        {
            outputStream = new PrintWriter(new
                    FileOutputStream("stuff.txt"));
        }catch (FileNotFoundException e)
        {
            System.out.println("Error opening the file stuff.txt");
            System.exit(0);
        }

        int magic[][] = new int[n][n];
        int row = 0, col = n / 2, i, j, square = n * n;
        for (i = 1; i <= square; i++) {
            magic[row][col] = i;
            if (i % n == 0)
                row++;
            else {
                if (row == 0)
                    row = n - 1;
                else
                    row--;
                if (col == (n - 1))
                    col = 0;
                else
                    col++;
            }
        }



        return true;
    }


    public static void main(String[] args)
    {
        boolean[] a = new boolean[6];
        for(int i=0;i<5;i++) {
            a[i] = isLegalMagicSquare("D:\\MagicSquare\\src\\" + (i + 1) + ".txt");
            System.out.println(a[i]);
        }
        int n;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        if(n < 0||(n % 2 == 0))
            System.out.println("false");
        else a[6] = generateMagicSquare(n);
            System.out.println(a[6]);
    }
}



