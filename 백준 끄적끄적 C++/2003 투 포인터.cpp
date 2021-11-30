#include <iostream>
#include <string>

using namespace std;
// namespace: 이름 공간
// std 클래스
// namespace에 있는 std 클래스에 정의되어있는 함수를 사용하겠다.

// 투 포인터

// 이중 포문 O(n^2) 대신 O(n) 사용
int main()
{
    int N;
    cin >> N;

    int M;
    cin >> M;

    int arr[N];
    for (int i = 0; i < N; i++)
        cin >> arr[i];

    int count = 0;

    int left = 0;
    int right = 0;
    int sum = arr[0];

    while (left <= right && right < N)
    {

        if (sum < M)
        {
            right++;
            if (right >= N) // c에서 int배열은 EOF를 찾을수없다
                break;
            sum += arr[right];
        }
        else if (sum == M)
        {
            count++;
            right++;
            if (right >= N) // c에서 int배열은 EOF를 찾을수없다
                break;
            sum += arr[right];
        }
        else if (sum > M)
        {
            sum -= arr[left];
            left++;
            if (left >= N) // c에서 int배열은 EOF를 찾을수없다
                break;
            if (left > right && left < N)
            { // left가 right역전 하였을때
                right = left;
                sum = arr[right];
            }
        }
        // cout << "sum: " << sum << " ";
        // cout << "cou: " << count << endl;
    }

    cout << count << endl;
}
