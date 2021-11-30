#include <iostream>
#include <string>
#include <map>

using namespace std;
// namespace: 이름 공간
// std 클래스
// namespace에 있는 std 클래스에 정의되어있는 함수를 사용하겠다.

int main(){

    map<string, int> m;
    int count = 0;

    string str;
    while (getline(cin, str)){
        m[str] += 1;
        count++;
    }

    cout << fixed; // 소수점 고정시켜 표현
    cout.precision(4); // 소수점 4자리까지 표현
    // 반올림

    map<string, int>::iterator it;
    for (it = m.begin(); it != m.end(); it++)
        cout << it->first << " " << (double)it->second/count*100 << endl;
}
