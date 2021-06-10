#include <iostream>
#include <algorithm> // *max_element사용하려면 필요함
#include <cmath> // sqrt
using namespace std;
// std(클래스)에 정의되어있는 함수들을 사용

int main(void){
    // std::cout << "Hello world, CPP!" << std::endl;

    int N;
    // endl = 줄바꿈 
    // cout = 출력 , cin = 입력 
    cin >> N;
    int arr[N+1];
    arr[0] = 0;
    arr[1] = 1;

    for(int i=2; i<=N; i++){
        int root = sqrt(i);
        if ((root * root) == i){ // 제곱일때는 1
            arr[i] = 1;
        }
        else{
            arr[i] = arr[i-1] + 1; // 제곱수가 아닐때는 그 전의 개수에 + 1
            for (int j=1; j*j<i; j++){
                arr[i] = min( arr[i] , arr[j*j] + arr[i - j*j]);
            }
            // 예를들어 15일때
            // 
            // 1 + 14
            // 4 + 11
            // 9 + 6
            // 3가지중 최솟값이 arr[15] 가 됨 
        }
    }
    

    // for(int i=1; i<=N; i++){
    //     cout << "arr[" << i <<"] :  " << arr[i] << endl;
    // }
    cout << arr[N];

    return 0;
}

// arr[1] :  1
// arr[2] :  2
// arr[3] :  3
// arr[4] :  1
// arr[5] :  2
// arr[6] :  3
// arr[7] :  4
// arr[8] :  2
// arr[9] :  1
// arr[10] :  2
// arr[11] :  3
// arr[12] :  3
// arr[13] :  2

