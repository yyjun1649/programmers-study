import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        double[] user = new double[N + 1];
        double[] failer = new double[N + 1];
        HashMap<Integer,Double> map = new HashMap<>();

        for(int i : stages){
            if(i > N){
                i = N;
                failer[i]--;
            }
            for(int j = i; j > 0; j--){
                user[j]++;
            }

            failer[i]++;    
        }

        for(int i = 1; i <= N; i++){
            if(user[i] == 0){
                map.put(i,0.0);
            }else{
                map.put(i, failer[i] / user[i]);
            }  
        }

        for(int i = 0; i < N; i++){
            double max = -1;
            int k = 0;

            for(int key : map.keySet()){
                if(max < map.get(key)){
                    max = map.get(key);
                    k = key;
                }
            }

            answer[i] = k;
            map.remove(k);

        }

        return answer;
    }
}