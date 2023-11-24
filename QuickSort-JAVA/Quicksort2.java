import java.util.Arrays;

public class Quicksort2 {
    public static double[] quickSort(double [] arr) {
        if (arr == null || arr.length == 0) return arr;
        return quickSort2(arr, 0, arr.length - 1);
    }

    private static double[] quickSort2(double[] arr, int left, int right) {
        if (left < right) {
            //partition
            if (arr[left] > arr[right]) { // pivot1 in pivot2 den küçük-eşit olmasını istiyoruz
                swap(arr, left, right);
            }
            double pivot1 = arr[left]; double pivot2 = arr[right]; 
            int i = left + 1; // pivot1 - pivot2 arasındaki elemanlarda dolancak
            int lt = left + 1; // pivot1 den küçük elemanları tutacak. en son lt yi pivot1 ile değiştireceğiz
            int rt = right - 1; // pivot2 den büyük elemanları tutacak. en son rt yi pivot2 ile değiştireceğiz
            while (i <= rt) {
                if (arr[i] < pivot1) { // pivot1 den küçükse pivot1 den küçüklerin sonuna gönder
                    swap(arr, i++, lt++); 
                } else if (arr[i] > pivot2) { // pivot2 den büyükse pivot2 den büyüklerin başına gönder
                    swap(arr, i, rt--);
                } else {  // pivot1 <= arr[i] <= pivot2 ise i yi artır
                    i++;
                }
            }
            swap(arr, left, --lt); //pivot1'i doğru yerine alıyoruz
            swap(arr, right, ++rt);  //pivot2'yi doğru yerine alıyoruz
            quickSort2(arr, left, lt - 1); // pivot1 den küçükler
            quickSort2(arr, lt + 1, rt - 1); // pivot1 ile pivot2 arasındakiler
            quickSort2(arr, rt + 1, right); // pivot2 den büyükler
        }

        return arr;
    }

    private static void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        double[] arr = { 3.0, 1.0, 4.0, 1.0, 5.0, 9.0, 2.0, 6.0, 5.0, 3.0, 21.0, 15.0};
        quickSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    
}


