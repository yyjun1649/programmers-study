import java.util.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for(int i = 0; i<n; i++){
            StringBuilder sb = new StringBuilder();

            for(int j = 0; j<n; j++){
                if(arr1[i] % 2 == 1 || arr2[i] % 2 == 1){
                    sb.append("#");
                }else{
                    sb.append(" ");
                }
                arr1[i] /= 2;
                arr2[i] /= 2;
                
            }
            answer[i] = sb.reverse().toString();
        }
        
        
        return answer;
    }
}