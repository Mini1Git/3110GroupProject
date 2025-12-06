package testFilesTyler.test1;

public class arrMethods{
    static void arrRev(int[] a){
        for(int i = 0; i < a.length/2; i++){
            int temp = a[i];
            a[i] = a[a.length - 1 - i];
            a[a.length - 1 - i] = temp;
        }
    }
    
    static int search(int[] a, int n){
        int l = 0;
        int r = a.length - 1;
        int m = a.length/2;

        while(l <= r){
            if(a[m] == n){
                return m;
            }
            else if(a[m] > n){
                r = m - 1;
            }
            else if(a[m] < n){
                l = m + 1;
            }
        }

        return -1;
    }
    
    public static void main(String[] args) {
        int[] arr = {10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

        System.out.println("Array:");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + ", ");
        }

        arrRev(arr);

        System.out.println("Reversed array:");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + ", ");
        }

        System.out.println("Search for 11: " + search(arr, 11));

        for(int i = 0; i < arr.length; i++){
            arr[i]++;
        }

        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i] + ", ");
        }
    }
}