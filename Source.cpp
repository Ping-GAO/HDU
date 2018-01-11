#include <iostream>
#include <stack>
using namespace std;
int main() {
	int N, R;
	char arr[] = { '0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

	while (cin>>N>>R)
	{
		stack<char> s;
		bool flag = true;
		if (N < 0) {
			flag = false;
			N = N*-1;
		}




		while (N!=0)
		{	
			if (N > R) {
				int temp = N % R;
				s.push(arr[temp]);
				// cout << temp << endl;
				N  = (N - temp) / R;
				//cout << N << endl;
			}
			else {
				s.push(arr[N]);
				N = 0;
			}


		}
		if (flag==false) {
			s.push('-');
		}

		while (!s.empty())
		{
			cout << s.top();
			s.pop();
		}
		cout << endl;

	}

	return 0;
}