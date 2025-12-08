package testFilesTyler.test1;

public class arrMethods{
    //reverse array
    static void arrRev(int[] a){
        //loop
        for(int i = 0; i < a.length/2; i++){
            int temp = a[i];
            a[i] = a[a.length - 1 - i];
            a[a.length - 1 - i] = temp;
        }
    }

    //search in an array
    static int search(int[] a, int n){
        int l = 0;
        int r = a.length - 1;
        int m = a.length/2;

        //loop
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
    
    //driver
    public static void main(String[] args) {
        int[] arr = {15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65};

        //normal
        System.out.println("Array:");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + ", ");
        }
        System.out.println("Array printed");

        //reverse
        arrRev(arr);
        System.out.println("Reversed array:");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + ", ");
        }
        System.out.println("Array printed");

        //search
        System.out.println("Search for 15:");
        if(search(arr, 15) != -1){
            System.out.println("Found");
        }

        //divide each element by 2
        for(int i = 0; i < arr.length; i++){
            arr[i] = arr[i]/2;
        }

        //print new results
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + ", ");
        }
        System.out.println("Array printed");
    }
}