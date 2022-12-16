// Time Complexity : O(Logm) -> m is length of smaller array , we do binary search on smaller array and calculate partitions
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes

package com.madhurima;

public class MedianOfTwoArrays {

    //binary search approach
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if((nums1 == null && nums2 == null) || (nums1.length == 0 && nums2.length == 0)){
            return 0.0;
        }

        int m = nums1.length;
        int n = nums2.length;

        if(n < m){
            return findMedianSortedArrays(nums2, nums1); // need smaller array as nums1
        }

        int low = 0;
        int high = m;

        while(low <= high){
            int partX = low + (high - low)/2; //to avoid integer overflow
            int partY = (m+n)/2 - partX;

            double l1 = (partX == 0) ? Integer.MIN_VALUE : nums1[partX - 1];
            double l2 = (partY == 0) ? Integer.MIN_VALUE : nums2[partY - 1];
            double r1 = (partX == m) ? Integer.MAX_VALUE : nums1[partX];
            double r2 = (partY == n) ? Integer.MAX_VALUE : nums2[partY];

            if(l1 <= r2 && l2 <= r1){
                if((m+n)%2 == 0){ //even
                    return (Math.max(l1,l2) + Math.min(r1,r2))/2.0;
                }else{ //odd
                    return Math.min(r1,r2);
                }
            }else if(l1 > r2){
                high = partX - 1;
            }else{
                low = partX + 1;
            }
        }
        return 0.0;
    }

}

