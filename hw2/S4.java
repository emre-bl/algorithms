import java.util.Arrays;
import java.util.Random;

public class S4 {
    public static void main(String[] args) {
        int adet=10;
        int[] arr = new int[adet];
        for(int i=0;i<adet;i++)
            arr[i] = adet-i;
        int[] s = findClose(arr,5,2);
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(s));
    }

    public static int[] findClose(int[] A,int k, int l)
    {
        if(l == 0)
            return new int[]{};
        int r = A.length-1;
        int mid = findOrderStatistic(A,0,r,k);
        int sayi = A[mid];
        if(l==1)
            return new int[]{A[mid]};
        int rightsize=r-mid;
        int leftsize = mid;
        l = l-1;
        int ls = l;
        int rs = l;
        if(leftsize<ls)
        {
            ls = leftsize;
            //rs = l-leftsize;
        }else if(rightsize<rs)
        {
            rs = rightsize;
            //ls = l-rs;
        }

        int left = findOrderStatistic(A,0,mid,mid-ls+1);
        int right = findOrderStatistic(A,mid+1,r,rs);

        if(right>r)
            right--;
        if(left<0)
            left = 0;
        int la[] = Arrays.copyOfRange(A,left,mid+1);
        int ra[] = Arrays.copyOfRange(A,mid+1,right+1);
        int sayilar[] = new int[l+1];
        Arrays.sort(la);
        Arrays.sort(ra);


        int ri=0,li=la.length-1,si=0;
        while(ri<ra.length && li>=0 && si<l+1)
        {
            int a = la[li];
            int b = ra[ri];
            if(Math.abs(sayi-a)<Math.abs(sayi-b))
                sayilar[si++] = la[li--];
            else
                sayilar[si++] = ra[ri++];
        }
        while(ri<ra.length && si<l+1)
            sayilar[si++] = ra[ri++];
        while(li>=0 && si<l+1)
            sayilar[si++] = la[li--];
        Arrays.sort(sayilar);
        return sayilar;
    }


    public static int findOrderStatistic(int[] A, int l,int r,int k)
    {
        if(r<=l)
            return l;

        int p = partition(A,l,r);
        int p2=p-l+1;
        if(p2==k)
            return p;
        else if(p2<k)
            return findOrderStatistic(A,p+1,r,k-p2);
        else
            return findOrderStatistic(A,l,p-1,k);
    }

    public static int partition(int[] A,int l,int r)
    {

        int pivot = rand(l,r);
        swap(A,pivot,r);
        pivot = A[r];
        int idx=l-1;
        for(int i=l;i<r;i++)
            if(A[i]<pivot)
                swap(A,++idx,i);

        swap(A,++idx,r);

        return idx;
    }

    public static int rand(int min,int max)
    {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static void swap(int[] A,int i,int j)
    {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

}
