#include <iostream>
#include <algorithm> // *max_element사용하려면 필요함
using namespace std;

long long how_many(long long arr[], long long K, long long N){
    
    long long low = 1;
    long long high = *max_element(arr, arr + K);
    

    while(low<=high){
        long long count = 0;
        long long length = (low + high) / 2;
        for (int i = 0; i < K; i++){
            count = count + (arr[i] / length);
        }
        if(count>=N)
            low = length+1;
        else if(count<N)
            high = length-1;
    }
    return high;
}

int main(void){
    // std::cout << "Hello world, CPP!" << std::endl;

     // std(클래스)에 정의되어있는 함수들을 사용
    long long K;
    long long N;
    long long temp;
    // endl = 줄바꿈 
    // cout = 출력 , cin = 입력 
    cin >> K;
    cin >> N;

    long long arr[K];
    for (int i=0; i<K; i++){
        cin >> temp;
        arr[i] = temp;
        //cout << arr[i] << endl;
    }

    long long answer = how_many(arr,K,N);
    cout << answer;

    return 0;
}



