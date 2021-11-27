#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

int N;
int shark_size;
int eat_count;
int arr[21][21];
int move_distance;
int start_x;
int start_y;

struct cmp{
	bool operator()(pair<pair<int, int>, int> a, pair<pair<int, int>, int> b){
		if (a.second == b.second)
		{
			if (a.first.first == b.first.first)
				return a.first.second > b.first.second;
			return a.first.first > b.first.first;
		}
		return a.second > b.second;
	}
	// 거리가 가까운 순으로 정렬
	// 거리가 같으면 x좌표 오름차순으로 정렬
	// 거리가 같고 x좌표가 같으면 y좌표 오름차순으로 정렬
};

int DFS(int pos_x, int pos_y, int target){
	// pq < < x_좌표 , y_좌표 > , 거리 >
	priority_queue < pair<pair<int,int>,int>, vector<pair<pair<int,int>,int> >, cmp> pq;

	bool visited[N][N];
	for (int i = 0; i < N; i++){
		for (int j = 0; j < N; j++)
			visited[i][j] = false;
	}
	// 방문한곳 초기화
	pq.push(make_pair(make_pair(pos_x , pos_y), 0));


	while(!pq.empty()){
		pair<pair<int, int>, int>  poll;
		poll = pq.top();
		int x = poll.first.first;
		int y = poll.first.second;
		int dis = poll.second;
		pq.pop();

		// cout <<"arr[" << x << "][" << y << "]: " << arr[x][y] << " d: " << dis << endl;

		if (arr[x][y] != 0 && target >= arr[x][y]){ 
			// 가장 가까운 먹이 찾았을때 상어 위치 바꿔줌
			start_x = x;
			start_y = y;

			// cout << endl;
			// for (int i = 0; i < N; i++){
			// 	for (int j = 0; j < N; j++){
			// 		cout << arr[i][j] << " ";
			// 	}
			// 	cout << endl;
			// }

			// cout << "arr[" << x << "][" << y << "]: " << arr[x][y] << endl;
			// cout << "eat: " << eat_count << " size: " << shark_size << endl;

			// 먹은 물고기 위치 0으로 바꿔줌
			arr[x][y] = 0;
			// 먹을 횟수++
			eat_count++;
			// 이동한 거리 리턴
			return dis;
		}

		
		// 이동하려는 곳이 배열의 내부 일때 && 이동하려는 곳이 상어사이즈보다 작거나같을때 && 방문하지 않은곳일때
		// 방문한곳 ture를 원래 큐에서 꺼내고 설정해주었는데 그러면 의미가 없이 중복된다 
		// 큐에 넣을때 설정해주어야 중복처리가 된다
		if(x + 1 <= N - 1 && shark_size >= arr[x + 1][y] && !visited[x + 1][y]){
			pq.push(make_pair(make_pair(x + 1, y), dis + 1));
			visited[x + 1][y] = true;
		}

		if (y + 1 <= N - 1 && shark_size >= arr[x][y + 1] && !visited[x][y + 1]){
			pq.push(make_pair(make_pair(x, y + 1), dis + 1));
			visited[x][y + 1] = true;
		}

		if (x - 1 >= 0 && shark_size >= arr[x - 1][y] && !visited[x - 1][y]){
			pq.push(make_pair(make_pair(x - 1, y), dis + 1));
			visited[x - 1][y] = true;
		}

		if (y - 1 >= 0 && shark_size >= arr[x][y - 1] && !visited[x][y - 1]){
			pq.push(make_pair(make_pair(x, y - 1), dis + 1));
			visited[x][y - 1] = true;
		}
	}
	return -1;
}



int main(void){
	
	cin >> N;

	shark_size = 2;
	eat_count = 0;
	move_distance = 0;

	int baby_shark;

	for (int i=0; i<N; i++){
		for (int j=0; j<N; j++){
			cin >> baby_shark;
			arr[i][j] = baby_shark;
			if(baby_shark == 9){
				start_x = i;
				start_y = j;
			} 
		}
	}
	// 상어 위치 기억한다음 0으로 바꿔줌
	arr[start_x][start_y] = 0;

	int find = 1;
	// 맨 처음 찾을 먹이
	while (true){ 
		int move = DFS(start_x, start_y, find);
		// cout <<"move : " << move << endl;
		// cout << endl;
		if (move == -1){ // 먹을 물고기가없거나 혼자 사냥을 못할때
			cout << move_distance << endl;
			return 0;
		}
		if (eat_count == shark_size){ // 상어 크기만큼 물고기 먹었을때
			shark_size++;  // 상어 사이즈 업
			eat_count = 0; // 먹은횟수 초기화
			find++;        // 사냥가능한 물고기 최대 레벨업
		}
		move_distance += move; // 총 이동거리 추가
	}
	cout << move_distance << endl;
	return 0;
}