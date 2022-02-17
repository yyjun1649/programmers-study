# Programmers - 전력망을 둘로 나누기

- 문제

~~~
문제 설명
n개의 송전탑이 전선을 통해 하나의 트리 형태로 연결되어 있습니다. 당신은 이 전선들 중 하나를 끊어서 현재의 전력망 네트워크를 2개로 분할하려고 합니다. 이때, 두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추고자 합니다.

송전탑의 개수 n, 그리고 전선 정보 wires가 매개변수로 주어집니다. 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나누었을 때, 두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)를 return 하도록 solution 함수를 완성해주세요.

제한사항
n은 2 이상 100 이하인 자연수입니다.
wires는 길이가 n-1인 정수형 2차원 배열입니다.
wires의 각 원소는 [v1, v2] 2개의 자연수로 이루어져 있으며, 이는 전력망의 v1번 송전탑과 v2번 송전탑이 전선으로 연결되어 있다는 것을 의미합니다.
1 ≤ v1 < v2 ≤ n 입니다.
전력망 네트워크가 하나의 트리 형태가 아닌 경우는 입력으로 주어지지 않습니다.
입출력 예
n	wires	result
9	[[1,3],[2,3],[3,4],[4,5],[4,6],[4,7],[7,8],[7,9]]	3
4	[[1,2],[2,3],[3,4]]	0
7	[[1,2],[2,7],[3,7],[3,4],[4,5],[6,7]]	1
입출력 예 설명
입출력 예 #1

다음 그림은 주어진 입력을 해결하는 방법 중 하나를 나타낸 것입니다.
ex1.png
4번과 7번을 연결하는 전선을 끊으면 두 전력망은 각 6개와 3개의 송전탑을 가지며, 이보다 더 비슷한 개수로 전력망을 나눌 수 없습니다.
또 다른 방법으로는 3번과 4번을 연결하는 전선을 끊어도 최선의 정답을 도출할 수 있습니다.
입출력 예 #2

다음 그림은 주어진 입력을 해결하는 방법을 나타낸 것입니다.
ex2.png
2번과 3번을 연결하는 전선을 끊으면 두 전력망이 모두 2개의 송전탑을 가지게 되며, 이 방법이 최선입니다.
입출력 예 #3

다음 그림은 주어진 입력을 해결하는 방법을 나타낸 것입니다.
ex3.png
3번과 7번을 연결하는 전선을 끊으면 두 전력망이 각각 4개와 3개의 송전탑을 가지게 되며, 이 방법이 최선입니다.
~~~



- **코드**

~~~
#include <string>
#include <vector>
#include <cmath>
#include <iostream>
using namespace std;
/*
l, r 로 나눈다.
l = 전체, r = 0 min = min(min,abs(l-r)) => 이런식으로 검사하면 나오지않을까?
l = 전체 -1 , r = 1 abs(1-r)

l값만 카운트해서 구해보자.
전체 - l = r
l - (전체-l) = 2l - 전체
*/


void dfs(vector<vector<int>> nodes, vector<bool> visited, int index, int& count) {
    visited[index] = true;
    for (int i = 1; i < nodes[index].size(); i++) {
        if (nodes[index][i] == 1 && !visited[i]) {
            count++;
            dfs(nodes, visited, i, count);
        }
    }
}

int solution(int n, vector<vector<int>> wires) {
    int answer = 100;
    int count = 1;
    vector<vector<int>> node(n + 1, vector<int>(n + 1));
    vector<bool> visited(n + 1, false);

    for (int i = 0; i < wires.size(); i++) {
        node[wires[i][0]][wires[i][1]] = node[wires[i][1]][wires[i][0]] = 1;
    }

    for (int i = 0; i < wires.size(); i++) {
        node[wires[i][0]][wires[i][1]] = node[wires[i][1]][wires[i][0]] = 0;
        dfs(node, visited, 1, count);
        answer = min(answer,abs(count *2 - n));
        node[wires[i][0]][wires[i][1]] = node[wires[i][1]][wires[i][0]] = 1;
        count = 1;
    }
    return answer;
}
~~~

