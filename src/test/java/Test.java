public class Test {
    static boolean flag = false;
    static boolean res = true;
    public static void main(String[] args) {
        int[] a = new int[5];
        a[0]=0;a[1]=0;a[2]=8;a[3]=5;
        a[4]=4;
        paixu(a);
        for (int i : a) {
            System.out.println(i);
        }
        System.out.println(res);
        System.out.println(flag);
    }

    private static void paixu(int[] nums){
        int len = nums.length;
        for(int i =0;i<len-1;i++){
            int min = i;
            for(int j =i+1;j<len;j++){
                if(nums[min]!=0&&nums[min]==nums[j]){
                    res = false;
                    return;
                }
                if(nums[min]>nums[j]){
                    min = j;
                }
            }
            if(min!=i){
                int temp ;
                temp = nums[min];
                nums[min]= nums[i];
                nums[i] = temp ;
            }
            if(nums[i]==0){
                flag = true;
            }
        }
    }

}
